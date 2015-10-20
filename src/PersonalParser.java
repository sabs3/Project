import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalParser {
	static TreeMap<String, Integer> treemap = new TreeMap<String, Integer>();
	static TreeMap<String, Integer> treemap1 = new TreeMap<String, Integer>();
	//static TreeMap<String, String> utreemap = new TreeMap<String, String>();

	public static void main(String[] args) {
		Scanner scan;
		Scanner uscan;
		try {
			scan = new Scanner(new File("/Users/sabinakhanal/Desktop/posts.xml"));
			uscan = new Scanner(new File("/Users/sabinakhanal/Desktop/users.xml"));
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				// System.out.println(line);
				Pattern postId = Pattern.compile("PostTypeId+\\=\"(\\d)");
				Pattern userId = Pattern.compile("OwnerUserId+\\=\"(\\d{1,10})");
				Matcher m1 = postId.matcher(line);
				Matcher m2 = userId.matcher(line);
				if (m1.find() & m2.find()) {
					String postType = m1.group(1);
					String userType = m2.group(1);
					if (postType.equals("1")) {
						if (treemap.containsKey(userType)) {
							treemap.put(userType, treemap.get(userType) + 1);
						} else {
							treemap.put(userType, 1);
						}

					} else {
						if (treemap1.containsKey(userType)) {
							treemap1.put(userType, treemap1.get(userType) + 1);
						} else {
							treemap1.put(userType, 1);
						}
					}
				} else {
					// System.out.println("no match found");
				}
			}
			/*
			 * while(uscan.hasNextLine()){ String line = scan.nextLine();
			 * //System.out.println(line); Pattern userId =
			 * Pattern.compile("Id+\\=\"(-?\\d+)"); Pattern userName =
			 * Pattern.compile("DisplayName\\=\"(.*?)\""); Matcher m1 =
			 * userId.matcher(line); Matcher m2 = userName.matcher(line); if
			 * (m1.find() & m2.find()) { String id= m1.group(1); String name =
			 * m2.group(1); utreemap.put(name, id); } else { //
			 * System.out.println("no match found"); } }
			 */

			Map<String, Integer> sortedMap = TreeMapDemo.sortByValues(treemap);
			Map<String, Integer> sortedMap1 = TreeMapDemo.sortByValues(treemap1);
			// System.out.println("Treemap :" + treemap);

			// Get a set of the entries on the sorted map
			Set<Entry<String, Integer>> set = sortedMap.entrySet();
			Set<Entry<String, Integer>> set1 = sortedMap1.entrySet();
			// Set<Entry<String, String>> uset = utreemap.entrySet();

			// Get an iterator
			Iterator<Entry<String, Integer>> i = set.iterator();
			Iterator<Entry<String, Integer>> j = set1.iterator();
			// Iterator<Entry<String, String>> k = uset.iterator();

			// Display elements

			int counter = 0;
			System.out.println("Tope 10 Id for PostType Id 1");
			while (i.hasNext()) {
				if (counter == 10) {
					break;
				}
				counter++;
				Map.Entry<String, Integer> me = i.next();
				System.out.print(me.getKey() + "  appears ");
				System.out.println(me.getValue() + " times");
			}
			System.out.println("Top 10 Id for PostType Id 2");
			int jcounter = 0;
			while (j.hasNext()) {
				if (jcounter == 10) {
					break;
				}
				jcounter++;
				Map.Entry<String, Integer> me = j.next();

				System.out.print(me.getKey() + " appears : ");
				System.out.println(me.getValue() + " times");
			}
			/*
			 * int kcounter =0; while (k.hasNext()) { if(kcounter ==10) { break;
			 * } kcounter++; Entry<String, String> me = k.next();
			 * 
			 * System.out.print("Names" + me.getKey()); System.out.println("Id"
			 * +me.getValue()); }
			 */

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
