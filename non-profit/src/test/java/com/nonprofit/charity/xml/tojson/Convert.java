package com.nonprofit.charity.xml.tojson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Convert {
	static String line = "", str = "";
	public static void main(String[] args) throws IOException, JSONException {
		String link = "D:\\Comments.xml";
		BufferedReader br = new BufferedReader(new FileReader(link));
		Long i=0L;
		while ((line = br.readLine()) != null) {
			i++;
			str += line;
			System.out.println(i);
		}
		JSONObject jsondata = XML.toJSONObject(str);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Comments1.json"));
	    writer.write(jsondata.toString());
	     
	    writer.close();
		//System.out.println(jsondata);
		br.close();

	}

}
