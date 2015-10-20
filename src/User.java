import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class User {
//Separate method to implement users

	public static void main(String[] args) {
		TreeMap<String, String> treemap = new TreeMap<String, String>();
		try {

			File fXmlFile = new File("/Users/sabinakhanal/Desktop/users.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("row");

			//System.out.println("---------" + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				// System.out.println("\nCurrent Element :" +
				// nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String id = eElement.getAttribute("Id");
					String name = eElement.getAttribute("DisplayName");
					treemap.put(id,name);
	
			}
			}
			
			Set<Entry<String, String>> set = treemap.entrySet();
			Iterator<Entry<String, String>> i = set.iterator();
			
			while (i.hasNext()) {
				Map.Entry<String, String> me = i.next();
				System.out.print("Id: " + me.getKey());
				System.out.println(" name: " + me.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
}
		
