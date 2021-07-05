package com.sample;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;

import org.xml.sax.SAXException;

import java_cup.internal_error;

public class XmlComparatorclass {



 public static void main(String args[]) throws FileNotFoundException, 
    SAXException, IOException, ParserConfigurationException {
	 GetXMLtagdetails getXMLtagdetails = new GetXMLtagdetails();	
	 getXMLtagdetails.start();
	
	 HashMap<String, String> map = new HashMap<String,String>();
	 HashMap<String, String> missingtag = new HashMap<String,String>();
	 
	 // reading two xml file to compare in Java program
	FileInputStream fis1 = new FileInputStream("./Files/Source_File.xml");
	FileInputStream fis2 = new FileInputStream("./Files/Target_File.xml");
	
	// using BufferedReader for improved performance
	BufferedReader  source = new BufferedReader(new InputStreamReader(fis1));
	BufferedReader  target = new BufferedReader(new InputStreamReader(fis2));
	
	//configuring XMLUnit to ignore white spaces
	XMLUnit.setIgnoreWhitespace(true);
	
	//comparing two XML using XMLUnit in Java
	List differences = compareXML(source, target);
	
	 Iterator i = differences.iterator();
	 int count = 0;
     while (i.hasNext()) {
       count = count +1 ;    
        Difference difference = (Difference)i.next();
         if (!difference.isRecoverable()) {
            if(difference.toString().contains("Expected text"))
            {
            	difference.getControlNodeDetail().getValue();
            	String a = difference.getControlNodeDetail().getValue();       
            	// convert to integer
            	try {
            		if (a.contains(".")) {
	            		float aint = Float.parseFloat(a);
	            	}else {
	            		int aint=Integer.parseInt(a);
	            	}
				} catch (Exception e) {
					// this is string value 
				}
		            	
            	map.put(difference.toString(), difference.getControlNodeDetail().getValue());
            }else
       		   missingtag.put(difference.toString(), difference.getControlNodeDetail().getValue());
         
         }else {
        	 missingtag.put(difference.toString(), difference.getControlNodeDetail().getValue());
         }
      
     }
     int icount = 0 ;
	     for (String keys : map.keySet())
	     {
	    	icount = icount +1;
	        System.out.println(icount +" Difference No. :"+ keys);
	        } 
     icount = 0 ;
	     for (String keys : missingtag.keySet())
	     {
	    	 icount = icount +1;
	    	 System.out.println(icount +" Missing tag No. :"+ keys);
	      } 
	 		//showing differences found in two xml files
	     printDifferences(differences);

}    


public static List compareXML(Reader source, Reader target) throws
SAXException, IOException{

	//creating Diff instance to compare two XML files
	Diff xmlDiff = new Diff(source, target);
	
	//for getting detailed differences between two xml files
	DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
	
	return detailXmlDiff.getAllDifferences();
}

public static void printDifferences(List diff){
	int totalDifferences = diff.size();
	System.out.println("===============================");
	System.out.println("Total differences : " + totalDifferences);
	System.out.println("================================");

	}
}
