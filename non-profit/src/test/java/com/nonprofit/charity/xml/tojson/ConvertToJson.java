package com.nonprofit.charity.xml.tojson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class ConvertToJson {
	static String line = "", str = "";

	public static void main(String[] args) throws JSONException, IOException {
		String link = "D:\\Posts.xml";
		BufferedReader br = new BufferedReader(new FileReader(link));
		while ((line = br.readLine()) != null) {
			str += line;
		}
		JSONObject jsondata = XML.toJSONObject(str);
		System.out.println(jsondata);
		br.close();
	}

}
