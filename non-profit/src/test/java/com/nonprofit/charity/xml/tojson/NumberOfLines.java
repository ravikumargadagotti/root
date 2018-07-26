package com.nonprofit.charity.xml.tojson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class NumberOfLines {

	public static void main(String[] args) {
		try {
		int numberOfLines = countLinesNew("D:\\PostHistory.xml");
		System.out.println(numberOfLines);
		}catch(IOException io) {
			io.printStackTrace();
		}
		
		int sizeofrecordinbytes = 40;
		 // for this example this is 1 based, not zero based 
		int recordIWantToStartAt = 1000;
		int totalRecordsIWant = 5000;
		
		File myfile = new File("D:\\PostHistory.xml");
		
		// where to seek to
		long seekToByte =  (recordIWantToStartAt == 1 ? 0 : ((recordIWantToStartAt-1) * sizeofrecordinbytes));
		 
		// byte the reader will jump to once we know where to go
		long startAtByte = 0;

		// seek to that position using a RandomAccessFile
		try {
		        // NOTE since we are using fixed length records, you could actually skip this 
		        // and just use our seekToByte as the value for the BufferedReader.skip() call below
		 
		    RandomAccessFile rand = new RandomAccessFile(myfile,"r");
		    rand.seek(seekToByte);
		    startAtByte = rand.getFilePointer();
		    rand.close();
		     
		} catch(IOException e) {
		    // do something
		}
		
		// Do it using the BufferedReader 
		BufferedReader reader = null;
		try {
		    // lets fire up a buffered reader and skip right to that spot.
		    reader = new BufferedReader(new FileReader(myfile));
		    reader.skip(startAtByte);
		     
		    String line;
		    long totalRead = 0;
		    char[] buffer = new char[sizeofrecordinbytes];
		    BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\PostHistory1.xml"));
		    
		    while(totalRead < totalRecordsIWant && (-1 != reader.read(buffer, 0, sizeofrecordinbytes))) {
		        System.out.println(new String(buffer));
		        
		        writer.write(buffer);
			     
			    totalRead++;
		    }
		    writer.close();
		       
		} catch(Exception e) {
		    // handle this
		     
		} finally {
		    if (reader != null) {
		        try {reader.close();} catch(Exception ignore) {}
		    }
		}
		
	}
	
	public static int countLinesNew(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    
	    
	    try {
	        byte[] c = new byte[1024];

	        int readChars = is.read(c);
	        if (readChars == -1) {
	            // bail out if nothing to read
	            return 0;
	        }

	        // make it easy for the optimizer to tune this loop
	        int count = 0;
	        while (readChars == 1024) {
	            for (int i=0; i<1024;) {
	                if (c[i++] == '\n') {
	                	++count;
	                }
	            }
	            readChars = is.read(c);
	        }

	        // count remaining characters
	        while (readChars != -1) {
	            System.out.println(readChars);
	            for (int i=0; i<readChars; ++i) {
	                if (c[i] == '\n') {
	                	++count;
	                }
	            }
	            readChars = is.read(c);
	        }

	        return count == 0 ? 1 : count;
	    } finally {
	        is.close();
	    }
	}

}
