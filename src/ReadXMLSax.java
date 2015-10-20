import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//parsing the elements using  SAX parser
public class ReadXMLSax extends DefaultHandler {

	TreeMap<String, Integer> treemap = new TreeMap<String, Integer>();
	TreeMap<String, Integer> treemap1 = new TreeMap<String, Integer>();

	/** The main method sets things up for parsing */
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

		// Create a "parser factory" for creating SAX parsers
		SAXParserFactory spfac = SAXParserFactory.newInstance();

		// Now use the parser factory to create a SAXParser object
		SAXParser sp = spfac.newSAXParser();

		// Create an instance of this class; it defines all the handler methods
		ReadXMLSax handler = new ReadXMLSax();

		// Finally, tell the parser to parse the input and notify the handler
		sp.parse("/Users/sabinakhanal/Desktop/posts.xml", handler);

	}

	/*
	 * When the parser encounters plain text (not XML elements), it calls(this
	 * method, which accumulates them in a string buffer
	 */
	public void characters(char[] buffer, int start, int length) {
		new String(buffer, start, length);
	}

	/*
	 * Every time the parser encounters the beginning of a new element, it calls
	 * this method, which resets the string buffer
	 */

	public void startDocument() throws SAXException {

	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("row")) {
			String postType = attributes.getValue("PostTypeId");
			String user = attributes.getValue("OwnerUserId");

			if (postType.equals("1")) {
				if (treemap.containsKey(user)) {
					treemap.put(user, treemap.get(user) + 1);
				} else {
					treemap.put(user, 1);
				}
			} else {
				if (treemap1.containsKey(user)) {
					treemap1.put(user, treemap1.get(user) + 1);
				} else {
					treemap1.put(user, 1);
				}
			}

		}
	}

	/*
	 * When the parser encounters the end of an element, it calls this method
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// Calling the method sortByvalues
		Map<String, Integer> sortedMap = TreeMapDemo.sortByValues(treemap);
		Map<String, Integer> sortedMap1 = TreeMapDemo.sortByValues(treemap1);

		// Get a set of the entries on the sorted map
		Set<Entry<String, Integer>> set = sortedMap.entrySet();
		Set<Entry<String, Integer>> set1 = sortedMap1.entrySet();

		// Get an iterator
		Iterator<Entry<String, Integer>> i = set.iterator();
		Iterator<Entry<String, Integer>> j = set1.iterator();

		// Display elements

		int icounter = 0;
		System.out.println("Tope 10 Id for PostType Id 1");
		while (j.hasNext()) {
			if (icounter == 10) {
				break;
			}
			icounter++;
			Map.Entry<String, Integer> me = j.next();
			System.out.print(me.getKey() + " appears ");
			System.out.println(me.getValue() + " times");
		}

		int jcounter = 0;
		System.out.println("Tope 10 Id for PostType Id 2");
		while (j.hasNext()) {
			if (jcounter == 10) {
				break;
			}
			jcounter++;
			Map.Entry<String, Integer> me = j.next();
			System.out.print(me.getKey() + " appears ");
			System.out.println(me.getValue() + " times");
		}

	}
}
