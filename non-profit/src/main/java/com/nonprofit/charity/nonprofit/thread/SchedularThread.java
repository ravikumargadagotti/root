package com.nonprofit.charity.nonprofit.thread;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.nonprofit.charity.nonprofit.email.SendEmail;

@EnableScheduling
@ManagedBean
public class SchedularThread {

	Logger logg = Logger.getLogger(SchedularThread.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	SendEmail sendEmail;

	@Scheduled(cron = "0 0 10 1 * ?")
	public void something() {
		String lotterySystemGroupName = null;
		// Get list of lottery's
		List<Map<String, Object>> list = selectLotteryGroup();

		for (Map<String, Object> row : list) {
			lotterySystemGroupName = (String) row.get("lotteryGroupName");
			if (lotterySystemGroupName != null) {
				// Start the process of each lottery
				startOrStopTheLottery(lotterySystemGroupName);
			}
		}
	}

	private List<Map<String, Object>> selectLotteryGroup() {
		logg.info("Select all the lottery system group");
		try {
			String sql = "select * from LotterySystemGroup";
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
			return list;
		} catch (InvalidResultSetAccessException ie) {
			logg.error(ie);
		} catch (DataAccessException de) {
			logg.error(de);
		}

		return null;

	}

	private List<Map<String, Object>> selectLotteryParticipants(String lotterySystemGroupName) {
		logg.info("Select all lottery participants");
		try {
			String selectParticipants = "select firstName from LotteryParticipants where lotteryGroupName = ? and lotteryPicked=?";
			String lotteryPicked = "NO";
			List<Map<String, Object>> participantsList = jdbcTemplate.queryForList(selectParticipants,
					lotterySystemGroupName, lotteryPicked);
			return participantsList;
		} catch (InvalidResultSetAccessException ie) {
			logg.error(ie);
		} catch (DataAccessException de) {
			logg.error(de);
		}

		return null;

	}

	private String selectLotteryPerson(String[] listNames) {
		logg.info("Select random person from the array list");
		try {
			Random random = new Random();
			String selectedName = listNames[random.nextInt(listNames.length)];
			logg.info("Selected person: " + selectedName);
			return selectedName;
		} catch (NullPointerException ne) {
			logg.error(ne);
		} catch (ArrayIndexOutOfBoundsException ae) {
			logg.error(ae);
		}
		return null;
	}

	private List<Map<String, Object>> pickedPersonDetails(String lotterySystemGroupName, String pickedParticipant) {
		logg.info("Return the results of the picked person: " + pickedParticipant);
		try {
			String pickedParticipantDetails = "select * from LotteryParticipants where lotteryGroupName = ? and firstName=?";
			List<Map<String, Object>> pickedPersonDetails = jdbcTemplate.queryForList(pickedParticipantDetails,
					lotterySystemGroupName, pickedParticipant);
			return pickedPersonDetails;
		} catch (InvalidResultSetAccessException ie) {
			logg.error(ie);
		} catch (DataAccessException de) {
			logg.error(de);
		}
		return null;

	}

	private int updateRecordInTheHistoryTable(String firstName, String lastName, String email, String phone,
			String lotteryGroupName) {
		logg.info("Insert data into history table and picked person is " + firstName);
		try {
			String pickedParticipantInsert = "INSERT INTO LotteryParticipantsHistory(firstName" + ",lastName" + ",email"
					+ ",phone" + ",lotteryGroupName" + ",lotteryPickDate)" + "VALUES( ?,?,?,?,?,current_time())";
			int numberOfRowsEffected = jdbcTemplate.update(pickedParticipantInsert, firstName, lastName, email, phone,
					lotteryGroupName);
			return numberOfRowsEffected;
		} catch (InvalidResultSetAccessException ie) {
			logg.error(ie);
		} catch (DataAccessException de) {
			logg.error(de);
		}

		return 0;

	}

	private void updateAllTheRecordInTheSameGroup(String selectedPersonName, String lotteryGroupName) {
		logg.info("Update the selected person lottery picked as yes and the person is " + selectedPersonName);
		try {
			String updateRecord = "update LotteryParticipants set lotteryPicked='YES' where firstname=? and lotteryGroupName=?";
			jdbcTemplate.update(updateRecord, selectedPersonName, lotteryGroupName);
		} catch (InvalidResultSetAccessException ie) {
			logg.error(ie);
		} catch (DataAccessException de) {
			logg.error(de);
		}

	}

	private void startOrStopTheLottery(String lotterySystemGroupName) {
		try {
			String selectFromLotteryState = "select * from LotteryState where lotteryName = ? order by startDate desc LIMIT 1";
			List<Map<String, Object>> lotteryStateList = jdbcTemplate.queryForList(selectFromLotteryState,
					lotterySystemGroupName);
			String lotteryStateGroupNameFromTable = null, startDate = null, endDate = null;
			int lotteryGroupNameId = 0;
			for (Map<String, Object> lotteryStateListIndividual : lotteryStateList) {
				lotteryStateGroupNameFromTable = (String) lotteryStateListIndividual.get("lotteryName");
				startDate = (String) lotteryStateListIndividual.get("startDate").toString();
				lotteryGroupNameId = (int) lotteryStateListIndividual.get("lId");
				if (lotteryStateListIndividual.get("endDate") != null) {
					endDate = (String) lotteryStateListIndividual.get("endDate").toString();
				} else {
					endDate = (String) lotteryStateListIndividual.get("endDate");
				}
			}

			String selectAllParticipantsOfThatLotteryGroup = "select * from LotteryParticipants where lotteryGroupName = ?";

			if (lotteryStateGroupNameFromTable == null && startDate == null && endDate == null) {
				// Start new lottery
				String insertIntoLotteryState = "INSERT INTO LotteryState (lotteryName,startDate,endDate) values (?,current_time(),null)";
				int numberOfRowsEffected = (int) jdbcTemplate.update(insertIntoLotteryState, lotterySystemGroupName);
				List<Map<String, Object>> participantsList = selectLotteryParticipants(lotterySystemGroupName);
				int numberOfParticipants = participantsList.size();
				String[] listNames = new String[numberOfParticipants];
				if (numberOfParticipants > 1) {
					int i = 0;
					for (Map<String, Object> participants : participantsList) {
						listNames[i] = (String) participants.get("firstName");
						i++;
					}
					String pickedParticipant = selectLotteryPerson(listNames);
					List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
							pickedParticipant);
					for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
						String firstName = (String) participantDetailedList.get("firstName");
						String lastName = (String) participantDetailedList.get("lastName");
						String email = (String) participantDetailedList.get("email");
						String phone = Long.toString((long) participantDetailedList.get("phone"));
						updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
						updateAllTheRecordInTheSameGroup(pickedParticipant, lotterySystemGroupName);

						List<Map<String, Object>> allParticipants = jdbcTemplate
								.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
						String[] emaillist = new String[allParticipants.size()];
						int j = 0;
						for (Map<String, Object> eachParticipant : allParticipants) {

							emaillist[j] = (String) eachParticipant.get("email");
							j++;
						}
						StringBuffer messageBody = new StringBuffer();
						messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
						messageBody.append("<thead>");
						messageBody.append("<tr>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
						messageBody.append("</tr>");
						messageBody.append("</thead>");
						
						messageBody.append("<tr bgcolor=\"white\">");
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(pickedParticipant);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(lastName);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(email);
						messageBody.append("</font></td>");
						messageBody.append("</tr>");
						messageBody.append("</table>");
						
						sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");
					}
				} else if (numberOfParticipants == 1) {
					for (Map<String, Object> participants : participantsList) {
						listNames[0] = (String) participants.get("firstName");
					}
					List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
							listNames[0]);
					for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
						String firstName = (String) participantDetailedList.get("firstName");
						String lastName = (String) participantDetailedList.get("lastName");
						String email = (String) participantDetailedList.get("email");
						String phone = (String) Long.toString((long) participantDetailedList.get("phone"));
						updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
						updateAllTheRecordInTheSameGroup(listNames[0], lotterySystemGroupName);

						List<Map<String, Object>> allParticipants = jdbcTemplate
								.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
						String[] emaillist = new String[allParticipants.size()];
						int j = 0;
						for (Map<String, Object> eachParticipant : allParticipants) {

							emaillist[j] = (String) eachParticipant.get("email");
							j++;
						}
						StringBuffer messageBody = new StringBuffer();
						messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
						messageBody.append("<thead>");
						messageBody.append("<tr>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
						messageBody.append("</tr>");
						messageBody.append("</thead>");
						
						messageBody.append("<tr bgcolor=\"white\">");
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(listNames[0]);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(lastName);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(email);
						messageBody.append("</font></td>");
						messageBody.append("</tr>");
						messageBody.append("</table>");
						
						sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");
					}
					String updateLotteryStateSql = "update LotteryState set endDate=current_timestamp() where endDate=null and lotteryName=?";
					jdbcTemplate.update(updateLotteryStateSql, lotterySystemGroupName);
				}
			} else if (lotteryStateGroupNameFromTable != null && startDate != null && endDate == null) {
				List<Map<String, Object>> participantsList = selectLotteryParticipants(lotterySystemGroupName);
				int numberOfParticipants = participantsList.size();
				String[] listNames = new String[numberOfParticipants];
				if (numberOfParticipants > 1) {
					int i = 0;
					for (Map<String, Object> participants : participantsList) {
						listNames[i] = (String) participants.get("firstName");
						i++;
					}
					String pickedParticipant = selectLotteryPerson(listNames);
					List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
							pickedParticipant);
					for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
						String firstName = (String) participantDetailedList.get("firstName");
						String lastName = (String) participantDetailedList.get("lastName");
						String email = (String) participantDetailedList.get("email");

						String phone = (String) Long.toString((long) participantDetailedList.get("phone"));
						updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
						updateAllTheRecordInTheSameGroup(pickedParticipant, lotterySystemGroupName);

						List<Map<String, Object>> allParticipants = jdbcTemplate
								.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
						String[] emaillist = new String[allParticipants.size()];
						int j = 0;
						for (Map<String, Object> eachParticipant : allParticipants) {

							emaillist[j] = (String) eachParticipant.get("email");
							j++;
						}
						StringBuffer messageBody = new StringBuffer();
						messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
						messageBody.append("<thead>");
						messageBody.append("<tr>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
						messageBody.append("</tr>");
						messageBody.append("</thead>");
						
						messageBody.append("<tr bgcolor=\"white\">");
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(pickedParticipant);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(lastName);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(email);
						messageBody.append("</font></td>");
						messageBody.append("</tr>");
						messageBody.append("</table>");
						
						sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");
					}

				} else if (numberOfParticipants == 1) {
					for (Map<String, Object> participants : participantsList) {
						listNames[0] = (String) participants.get("firstName");
					}
					List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
							listNames[0]);
					for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
						String firstName = (String) participantDetailedList.get("firstName");
						String lastName = (String) participantDetailedList.get("lastName");
						String email = (String) participantDetailedList.get("email");
						String phone = (String) Long.toString((long) participantDetailedList.get("phone"));
						updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
						updateAllTheRecordInTheSameGroup(listNames[0], lotterySystemGroupName);

						List<Map<String, Object>> allParticipants = jdbcTemplate
								.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
						String[] emaillist = new String[allParticipants.size()];
						int j = 0;
						for (Map<String, Object> eachParticipant : allParticipants) {

							emaillist[j] = (String) eachParticipant.get("email");
							j++;
						}
						StringBuffer messageBody = new StringBuffer();
						messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
						messageBody.append("<thead>");
						messageBody.append("<tr>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
						messageBody.append("</tr>");
						messageBody.append("</thead>");
						
						messageBody.append("<tr bgcolor=\"white\">");
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(listNames[0]);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(lastName);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(email);
						messageBody.append("</font></td>");
						messageBody.append("</tr>");
						messageBody.append("</table>");
						
						sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");
					}
					String updateLotteryStateSql = "update LotteryState set endDate=current_timestamp() where endDate is null and lotteryName=? and lId=?";
					jdbcTemplate.update(updateLotteryStateSql, lotterySystemGroupName, lotteryGroupNameId);
				} else {
					// End the lottery and start new one
					String updateLotteryStateSql = "update LotteryState set endDate=current_timestamp() where endDate is NULL and lotteryName=? and lId=?";
					jdbcTemplate.update(updateLotteryStateSql, lotterySystemGroupName, lotteryGroupNameId);
					String insertIntoLotteryState = "INSERT INTO LotteryState (lotteryName,startDate,endDate) values (?,current_time(),null)";
					int numberOfRowsEffected = (int) jdbcTemplate.update(insertIntoLotteryState,
							lotterySystemGroupName);
					String updateLotteryParticepents = "update LotteryParticipants set lotteryPicked='NO' where lotteryGroupName=?";
					jdbcTemplate.update(updateLotteryParticepents, lotterySystemGroupName);
					List<Map<String, Object>> participantsNewList = selectLotteryParticipants(lotterySystemGroupName);
					int numberOfNewParticipants = participantsNewList.size();

					String[] listNewNames = new String[numberOfNewParticipants];
					if (numberOfNewParticipants > 1) {
						int i = 0;
						for (Map<String, Object> participants : participantsNewList) {
							listNewNames[i] = (String) participants.get("firstName");
							i++;
						}
						String pickedParticipant = selectLotteryPerson(listNewNames);
						List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
								pickedParticipant);
						for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
							String firstName = (String) participantDetailedList.get("firstName");
							String lastName = (String) participantDetailedList.get("lastName");
							String email = (String) participantDetailedList.get("email");
							String phone = Long.toString((long) participantDetailedList.get("phone"));
							updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
							updateAllTheRecordInTheSameGroup(pickedParticipant, lotterySystemGroupName);

							List<Map<String, Object>> allParticipants = jdbcTemplate
									.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
							String[] emaillist = new String[allParticipants.size()];
							int j = 0;
							for (Map<String, Object> eachParticipant : allParticipants) {

								emaillist[j] = (String) eachParticipant.get("email");
								j++;
							}
							StringBuffer messageBody = new StringBuffer();
							messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
							messageBody.append("<thead>");
							messageBody.append("<tr>");
							messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
							messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
							messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
							messageBody.append("</tr>");
							messageBody.append("</thead>");
							
							messageBody.append("<tr bgcolor=\"white\">");
							messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
							messageBody.append(pickedParticipant);
							messageBody.append("</font></td>");
							
							
							messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
							messageBody.append(lastName);
							messageBody.append("</font></td>");
							
							
							messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
							messageBody.append(email);
							messageBody.append("</font></td>");
							messageBody.append("</tr>");
							messageBody.append("</table>");
							
							sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");
						}
					} else if (numberOfParticipants == 1) {
						for (Map<String, Object> participants : participantsList) {
							listNames[0] = (String) participants.get("firstName");
						}
						List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
								listNames[0]);
						for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
							String firstName = (String) participantDetailedList.get("firstName");
							String lastName = (String) participantDetailedList.get("lastName");
							String email = (String) participantDetailedList.get("email");
							String phone = (String) participantDetailedList.get("phone");
							updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
							updateAllTheRecordInTheSameGroup(listNames[0], lotterySystemGroupName);

							List<Map<String, Object>> allParticipants = jdbcTemplate
									.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
							String[] emaillist = new String[allParticipants.size()];
							int j = 0;
							for (Map<String, Object> eachParticipant : allParticipants) {

								emaillist[j] = (String) eachParticipant.get("email");
								j++;
							}
							StringBuffer messageBody = new StringBuffer();
							messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
							messageBody.append("<thead>");
							messageBody.append("<tr>");
							messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
							messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
							messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
							messageBody.append("</tr>");
							messageBody.append("</thead>");
							
							messageBody.append("<tr bgcolor=\"white\">");
							messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
							messageBody.append(listNames[0]);
							messageBody.append("</font></td>");
							
							
							messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
							messageBody.append(lastName);
							messageBody.append("</font></td>");
							
							
							messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
							messageBody.append(email);
							messageBody.append("</font></td>");
							messageBody.append("</tr>");
							messageBody.append("</table>");
							
							sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");
						}
						String updateLotteryStateSql1 = "update LotteryState set endDate=current_timestamp() where endDate=null and lotteryName=?";
						jdbcTemplate.update(updateLotteryStateSql1, lotterySystemGroupName);
					}
				}
			} else if (lotteryStateGroupNameFromTable != null && startDate != null && endDate != null) {
				// Start new lottery
				String insertIntoLotteryState = "INSERT INTO LotteryState (lotteryName,startDate,endDate) values (?,current_time(),null)";
				int numberOfRowsEffected = (int) jdbcTemplate.update(insertIntoLotteryState, lotterySystemGroupName);
				String updateLotteryParticepents = "update LotteryParticipants set lotteryPicked='NO' where lotteryGroupName=?";
				jdbcTemplate.update(updateLotteryParticepents, lotterySystemGroupName);
				List<Map<String, Object>> participantsList = selectLotteryParticipants(lotterySystemGroupName);
				int numberOfParticipants = participantsList.size();

				String[] listNames = new String[numberOfParticipants];
				if (numberOfParticipants > 1) {
					int i = 0;
					for (Map<String, Object> participants : participantsList) {
						listNames[i] = (String) participants.get("firstName");
						i++;
					}
					String pickedParticipant = selectLotteryPerson(listNames);
					List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
							pickedParticipant);
					for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
						String firstName = (String) participantDetailedList.get("firstName");
						String lastName = (String) participantDetailedList.get("lastName");
						String email = (String) participantDetailedList.get("email");
						String phone = Long.toString((long) participantDetailedList.get("phone"));
						updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
						updateAllTheRecordInTheSameGroup(pickedParticipant, lotterySystemGroupName);

						List<Map<String, Object>> allParticipants = jdbcTemplate
								.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
						String[] emaillist = new String[allParticipants.size()];
						int j = 0;
						for (Map<String, Object> eachParticipant : allParticipants) {

							emaillist[j] = (String) eachParticipant.get("email");
							j++;
						}
						StringBuffer messageBody = new StringBuffer();
						messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
						messageBody.append("<thead>");
						messageBody.append("<tr>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
						messageBody.append("</tr>");
						messageBody.append("</thead>");
						
						messageBody.append("<tr bgcolor=\"white\">");
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(pickedParticipant);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(lastName);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(email);
						messageBody.append("</font></td>");
						messageBody.append("</tr>");
						messageBody.append("</table>");
						
						sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");

					}
				} else if (numberOfParticipants == 1) {
					for (Map<String, Object> participants : participantsList) {
						listNames[0] = (String) participants.get("firstName");
					}
					List<Map<String, Object>> pickedParticipantDetails = pickedPersonDetails(lotterySystemGroupName,
							listNames[0]);

					for (Map<String, Object> participantDetailedList : pickedParticipantDetails) {
						String firstName = (String) participantDetailedList.get("firstName");
						String lastName = (String) participantDetailedList.get("lastName");
						String email = (String) participantDetailedList.get("email");
						String phone = (String) participantDetailedList.get("phone");
						updateRecordInTheHistoryTable(firstName, lastName, email, phone, lotterySystemGroupName);
						updateAllTheRecordInTheSameGroup(listNames[0], lotterySystemGroupName);

						List<Map<String, Object>> allParticipants = jdbcTemplate
								.queryForList(selectAllParticipantsOfThatLotteryGroup, lotterySystemGroupName);
						String[] emaillist = new String[allParticipants.size()];
						int j = 0;
						for (Map<String, Object> eachParticipant : allParticipants) {

							emaillist[j] = (String) eachParticipant.get("email");
							j++;
						}
						StringBuffer messageBody = new StringBuffer();
						messageBody.append("<table width=\"100%\" border= \"1\" cellpadding=\"0\" cellspacing=\"0\">");
						messageBody.append("<thead>");
						messageBody.append("<tr>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">First Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Last Name</font></th>");
						messageBody.append("<th><font size = \"2\" face = \"calibri\">Email</font></th>");
						messageBody.append("</tr>");
						messageBody.append("</thead>");
						
						messageBody.append("<tr bgcolor=\"white\">");
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(listNames[0]);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(lastName);
						messageBody.append("</font></td>");
						
						
						messageBody.append("<td><font size = \"2\" face = \"calibri\" color=\"black\">");
						messageBody.append(email);
						messageBody.append("</font></td>");
						messageBody.append("</tr>");
						messageBody.append("</table>");
						
						sendEmail.emailSend(emaillist, "Lucky Person Of The Month", messageBody.toString(), "html");
					}
					String updateLotteryStateSql = "update LotteryState set endDate=current_timestamp() where endDate=null and lotteryName=?";
					jdbcTemplate.update(updateLotteryStateSql, lotterySystemGroupName);
				}

			}
		} catch (NullPointerException ne) {
			logg.error(ne);
		} catch (ClassCastException ce) {
			logg.error(ce);
		} catch (InvalidResultSetAccessException ie) {
			logg.error(ie);
		} catch (DataAccessException de) {
			logg.error(de);
		}

	}
}