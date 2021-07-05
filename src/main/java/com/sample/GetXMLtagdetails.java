package com.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetXMLtagdetails {
	List<String>sourcelist_Tag;
	List<String>Target_Tag;
	public void start() throws ParserConfigurationException, SAXException, IOException {
		sourcelist_Tag = getTagDetails("./Files/Source_File.xml");
		Target_Tag = getTagDetails("./Files/Target_File.xml");
		System.out.println("SourceTage :" +sourcelist_Tag);
		System.out.println("tARGETtAG : " +Target_Tag);
	}
	
	public Boolean validatetag() {
		if (sourcelist_Tag.size()==Target_Tag.size()) {
			System.out.println("number of tags are same");
			return true;
		}else {
			System.out.println("Mismatch in the number of tags");
			/*
			 * System.out.println("Source Tag size is :" + sourcelist_Tag.size());
			 * System.out.println("target Tag size is :" + Target_Tag.size());
			 */		}
		return false;
	}
	public List<String> getTagDetails(String path) throws ParserConfigurationException, SAXException, IOException
	{
		// creating object of List<String>
        List<String> arrlist = new ArrayList<String>();
        // creating object of Vector<String>
        Vector<Element> v = new Vector<Element>();
        
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    Document doc = db.parse(path);
		    doc.getDocumentElement().normalize();
//		    System.out.println("Root element " + doc.getDocumentElement().getNodeName());

		    NodeList nodeList=doc.getElementsByTagName("*");
		    for (int i=0; i<nodeList.getLength(); i++) 
		    {
		        // Get element
		        Element element = (Element)nodeList.item(i);
//		        System.out.println(element.getNodeName());
		        String a = element.getNodeName();
		        arrlist.add(a);
		    }
		 // creating Enumeration
		/*
		 * Enumeration<Element> e = v.elements(); arrlist = Collections.list(e);
		 */
            System.out.println("Returned list: " + arrlist);
			return arrlist;
            
	}
	
}
