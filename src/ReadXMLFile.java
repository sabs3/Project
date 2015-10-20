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
		
		//Using treemap to store the data 
		TreeMap<String, Integer> treemap = new TreeMap<String, Integer>(); // for storing the posttype 1
		TreeMap<String, Integer> treemap1 = new TreeMap<String, Integer>(); //Storing posttype 2
		TreeMap<String, String> utreemap = new TreeMap<String, String>(); // storing user and their Id

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
					
//method to use the separate tree map according to posttype and counting the apperance as we go along
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
//Maptree for users
			for (int temp = 0; temp < uList.getLength(); temp++) {
				Node nNode = uList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String id = eElement.getAttribute("Id");
					String name = eElement.getAttribute("DisplayName");
					utreemap.put(id, name);

				}
			}
			// Calling the method to sortByvalues
			Map<String, Integer> sortedMap = TreeMapDemo.sortByValues(treemap);
			Map<String, Integer> sortedMap1 = TreeMapDemo.sortByValues(treemap1);
			// System.out.println("Treemap :" + treemap);

			// Get a set of the entries on the sorted map
			Set<Entry<String, Integer>> set = sortedMap.entrySet();
			Set<Entry<String, Integer>> set1 = sortedMap1.entrySet();
			Set<Entry<String, String>> uset = utreemap.entrySet();

			// Get an iterator
			Iterator<Entry<String, Integer>> i = set.iterator();
			Iterator<Entry<String, Integer>> j = set1.iterator();
			Iterator<Entry<String, String>> k = uset.iterator();

			// Display elements

			int kcounter = 0;
			System.out.println("Users and their ID");
			while (k.hasNext()) {
				if (kcounter == 10) {
					break;
				}
				kcounter++;
				Entry<String, String> me = k.next();
				System.out.print("id: " + me.getKey());
				System.out.println(" name: " +me.getValue());
			}
			
			int iCounter=0;
			System.out.println("Tope 10 Id for PostType Id 2");
			while(i.hasNext()) {
				if (iCounter==10) {
					break;
				}
				iCounter++;
				Map.Entry<String, Integer> u1 = i.next();
				//while(k.hasNext()) {
					//if(u1.getKey().equals(u.getKey())){
				System.out.print(u1.getKey() + " appears ");
				System.out.println(u1.getValue() + " times");
					//}
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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
