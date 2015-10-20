import java.util.*;
//method for testing purpose only
public class Testing {

	static void loop(List<Map<String, String>> list) {
		for (Map<String, String> map : list) {
			System.out.println("map:");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				System.out.println(key + ":" + value);
			}
		}
	}

	public static void main(String[] args) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("1", "one");
		map0.put("3", "three");
		list.add(map0);
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("2", "two");
		map1.put("4", "four");
		list.add(map1);
		loop(list);
	}
}
