package com.nonprofit.charity.xml.tojson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class ConvertXMLToJSON {
	static String line = "", str = "";

	public static void main(String[] args) throws JSONException, IOException {
		String link = "D:\\Comments.xml";
		BufferedReader br = new BufferedReader(new FileReader(link));
		while ((line = br.readLine()) != null) {
			str += line;
		}
		JSONObject jsondata = XML.toJSONObject(str);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Comments.json"));
	    writer.write(jsondata.toString());
	     
	    writer.close();
		//System.out.println(jsondata);
		br.close();
	}

}
