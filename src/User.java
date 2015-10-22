
import java.io.IOException;
import java.util.TreeMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class User {

	// Separate method to implement users

	// parsing the elements using SAX parser

	public TreeMap<String, String> userfile() {

		// Tree for storing key and value pairs for user
		TreeMap<String, String> utreemap = new TreeMap<String, String>();

		// Create a "parser factory" for creating SAX parsers
		SAXParserFactory spfac = SAXParserFactory.newInstance();

		// Now use the parser factory to create a SAXParser object
		SAXParser sp = null;
		try {
			sp = spfac.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create an instance of this class; it defines all the handler methods
		ReadXMLSax handler = new ReadXMLSax() {

			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {

				if (qName.equalsIgnoreCase("row")) {
					String id = attributes.getValue("Id");
					String name = attributes.getValue("DisplayName");
					utreemap.put(id, name);

				}
			}

			public void endElement(String uri, String localName, String qName) throws SAXException {

			}

			public void endDocument() throws SAXException {

			}
		};
		try {

			// Finally, tell the parser to parse the input and notify the
			// handler
			sp.parse("/Users/sabinakhanal/Desktop/users.xml", handler);
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return utreemap;
	}

}
