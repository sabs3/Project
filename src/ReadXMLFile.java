import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//parsing XML Document using DOM

public class ReadXMLFile {

	public static void main(String[] args) {

		// Using treemap to store the data

		// Tree for storing the PosTypeId 1
		TreeMap<String, Integer> treemap = new TreeMap<String, Integer>();
		// Tree for storing the POStypeId 2
		TreeMap<String, Integer> treemap1 = new TreeMap<String, Integer>();
		// Tree for storing the Users and their ID
		TreeMap<String, String> utreemap = new TreeMap<String, String>();

		try {

			File fXmlFile = new File("/Users/sabinakhanal/Desktop/posts.xml");
			File userfXmlFile = new File("/Users/sabinakhanal/Desktop/users.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(fXmlFile);
			Document userdoc = dBuilder.parse(userfXmlFile);

			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("row");
			NodeList uList = userdoc.getElementsByTagName("row");

			// System.out.println("---------" + nList.getLength());

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String postType = eElement.getAttribute("PostTypeId");

					// method to use the separate tree map according to posttype
					// and counting the occurance as we go along

					if (postType.equals("1")) {
						String s = eElement.getAttribute("OwnerUserId");

						if (treemap.containsKey(s)) {
							treemap.put(s, treemap.get(s) + 1);
						} else {
							treemap.put(s, 1);
						}
					} else {
						String s = eElement.getAttribute("OwnerUserId");

						if (treemap1.containsKey(s)) {
							treemap1.put(s, treemap1.get(s) + 1);
						} else {
							treemap1.put(s, 1);
						}
					}

				}
			}
			// Maptree for users
			for (int temp = 0; temp < uList.getLength(); temp++) {
				Node nNode = uList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String id = eElement.getAttribute("Id");
					String name = eElement.getAttribute("DisplayName");
					utreemap.put(id, name);

				}
			}

			// Calling the method to sortByvalues for posts tree
			Map<String, Integer> sortedMap = TreeMapDemo.sortByValues(treemap);
			Map<String, Integer> sortedMap1 = TreeMapDemo.sortByValues(treemap1);

			// Get a set of the entries on the sorted map for post tree
			Set<Entry<String, Integer>> set = sortedMap.entrySet();
			Set<Entry<String, Integer>> set1 = sortedMap1.entrySet();

			// Get an iterator
			Iterator<Entry<String, Integer>> i = set.iterator();
			Iterator<Entry<String, Integer>> j = set1.iterator();

			// Display elements

			int iCounter = 0;
			System.out.println("Tope 10 Id for PostType Id 1");
			System.out.println("----------------------------");

			// set a counter to print the top 10 ID for PostTypeId 1
			while (i.hasNext()) {
				if (iCounter == 10) {
					break;
				}
				iCounter++;
				Map.Entry<String, Integer> u1 = i.next();
				String user = u1.getKey();

				if (utreemap.containsKey(user)) {
					System.out.print(utreemap.get(user) + " appeared ");
					System.out.println(u1.getValue() + " times");
				}
			}

			int jcounter = 0;

			System.out.println("----------------------------");
			System.out.println("Tope 10 Id for PostType Id 2");
			System.out.println("----------------------------");

			// set a counter to print the top 10 Id for PostType Id 2
			while (j.hasNext()) {
				if (jcounter == 10) {
					break;
				}
				jcounter++;
				Map.Entry<String, Integer> id = j.next();
				String user = id.getKey();

				if (utreemap.containsKey(user)) {
					System.out.print(utreemap.get(user) + " appeared ");
					System.out.println(id.getValue() + " times");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
