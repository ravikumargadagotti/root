package com.nonprofit.charity.xml.tojson;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Convert1 {

	public static void main(String[] args) throws IOException, JSONException {
//		String str = new String(Files.readAllBytes(Paths.get("D:\\Comments.xml")),
//                StandardCharsets.UTF_8);
		
		 String fileName = "D:\\Comments.xml"; //this path is on my local
	        InputStream inputStream = new FileInputStream(fileName);
	        Long i=0L;
	       String something = null;
	        try(Scanner fileScanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())){
	            while (fileScanner.hasNextLine()){
	            	i++;
	            	something +=fileScanner.nextLine();
	            	System.out.println(i);
	            }
	        }
		
		JSONObject jsondata = XML.toJSONObject(something);
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Comments.json"));
	    writer.write(jsondata.toString());
	     
	    writer.close();

	}

}
