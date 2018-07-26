package com.nonprofit.charity.xml.tojson;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Convert2 {

	public static void main(String[] args) throws JSONException, IOException {
		String fileName = "D:\\PostHistory.xml"; //this path is on my local
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
	
	//JSONObject jsondata = XML.toJSONObject(something);
	
	BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\PostHistory1.xml"));
    writer.write(something);
     
    writer.close();

	}

}
