
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Class that retrieves and stores all Metro Stations and categorizes them by
 * line color.
 */
public class MetroStations {
	private static final String WMATA_API_KEY = "58dc2560ab8e4627854e8b8c93526816";
	private ArrayList<StationInfo> stationInfos = new ArrayList<StationInfo>();

	// temp lists to help with creating maps
	private ArrayList<String> red = new ArrayList<>();
	private ArrayList<String> blue = new ArrayList<>();
	private ArrayList<String> yellow = new ArrayList<>();
	private ArrayList<String> orange = new ArrayList<>();
	private ArrayList<String> green = new ArrayList<>();
	private ArrayList<String> silver = new ArrayList<>();

	private static final String[] LINE_COLORS = { "RD", "BL", "YL", "OR", "GR", "SV" };
	private ArrayList<ArrayList<String>> lineLists = new ArrayList<>();

	// graph representation of all the Metro stations
	private Map<StationInfo, ArrayList<StationInfo>> metroMap = new LinkedHashMap<>();

	private ArrayList<Map<StationInfo, ArrayList<StationInfo>>> stationMaps = new ArrayList<>();

	// maps to list all stations in order as well assign their adjacent stations
	private Map<StationInfo, ArrayList<StationInfo>> redMap = new LinkedHashMap<>();
	private Map<StationInfo, ArrayList<StationInfo>> blueMap = new LinkedHashMap<>();
	private Map<StationInfo, ArrayList<StationInfo>> yellowMap = new LinkedHashMap<>();
	private Map<StationInfo, ArrayList<StationInfo>> orangeMap = new LinkedHashMap<>();
	private Map<StationInfo, ArrayList<StationInfo>> greenMap = new LinkedHashMap<>();
	private Map<StationInfo, ArrayList<StationInfo>> silverMap = new LinkedHashMap<>();

	public MetroStations() {
		retrieveStations();
		createStationLists();
		createMap();
		createLineMaps();
	}

	public void retrieveStations() {
		try {
			String wmataURL = "https://api.wmata.com/Rail.svc/json/jStations";
			URL url = new URL(wmataURL + "?api_key=" + WMATA_API_KEY);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			int statusCode = connection.getResponseCode();

			if (statusCode == 200) {

				StringBuilder sb = new StringBuilder();
				Scanner scan = new Scanner(url.openStream());

				while (scan.hasNext()) {
					sb.append(scan.nextLine() + " ");
				}

				sb.replace(sb.length() - 1, sb.length(), "");

				scan.close();

				JsonParser parser = new JsonParser();
				JsonObject object = (JsonObject) parser.parse(sb.toString());

				JsonArray stationsArray = object.getAsJsonArray("Stations");
				JsonElement element;
				JsonObject obj;
				String name, code;
				ArrayList<String> colors;
				StationInfo stationInfo;

				for (int i = 0; i < stationsArray.size(); i++) {
					element = stationsArray.get(i);
					obj = element.getAsJsonObject();
					name = obj.get("Name").getAsString();
					colors = new ArrayList<>();
					code = obj.get("Code").getAsString();

					colors.add(obj.get("LineCode1").getAsString());

					if (!obj.get("LineCode2").isJsonNull()) {
						colors.add(obj.get("LineCode2").getAsString());
					}
					if (!obj.get("LineCode3").isJsonNull()) {
						colors.add(obj.get("LineCode3").getAsString());
					}

					if (!containsName(name)) {
						stationInfo = new StationInfo(name, colors, code);
						stationInfos.add(stationInfo);

					} else {
						stationInfos.get(indexOfName(name)).addLineColors(colors);
					}
				}
			} else {
				System.out.println("Error: " + statusCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<StationInfo> getStationsInfos() {
		return stationInfos;
	}

	public ArrayList<String> redStations() {
		// Iterator<StationInfo> names = redMap.keySet().iterator();
		// StringBuilder s = new StringBuilder();

		// while (names.hasNext()) {
		// s.append(names.next().getName() + "\n");
		// }
		// s.replace(s.length() - 1, s.length(), "");

		// return s.toString();
		return red;
	}

	public ArrayList<String> getBlue() {
		return blue;
	}

	public ArrayList<String> getYellow() {
		return yellow;
	}

	public ArrayList<String> getOrange() {
		return orange;
	}

	public ArrayList<String> getGreen() {
		return green;
	}

	public ArrayList<String> getSilver() {
		return silver;
	}

	private void createLineLists() {
		lineLists.add(red);
		lineLists.add(blue);
		lineLists.add(yellow);
		lineLists.add(orange);
		lineLists.add(green);
		lineLists.add(silver);
	}

	public void createStationLists() {
		createLineLists();

		for (int i = 0; i < LINE_COLORS.length; i++) {
			for (int j = 0; j < stationInfos.size(); j++) {
				if (stationInfos.get(j).getLineColors().contains(LINE_COLORS[i])) {
					lineLists.get(i).add(stationInfos.get(j).getName());
				}
			}
		}
	}

	private void sortRed() {
		ArrayList<String> tempRed = new ArrayList<>();
		int x = red.indexOf("Shady Grove");

		for (int i = x; i >= 0; i--) {
			tempRed.add(red.get(i));
			redMap.put(findStationInfo(red.get(i)), new ArrayList<StationInfo>());
		}

		x = red.indexOf("Gallery Pl-Chinatown");

		for (int i = x; i < x + 3; i++) {
			tempRed.add(red.get(i));
			redMap.put(findStationInfo(red.get(i)), new ArrayList<StationInfo>());

		}

		tempRed.add("NoMa-Gallaudet U");
		redMap.put(findStationInfo("NoMa-Gallaudet U"), new ArrayList<StationInfo>());

		for (int i = x + 3; i < red.size() - 1; i++) {
			tempRed.add(red.get(i));
			redMap.put(findStationInfo(red.get(i)), new ArrayList<StationInfo>());

		}

		red = tempRed;
	}

	private void sortBlue() {
		ArrayList<String> tempBlue = new ArrayList<>();

		tempBlue.add(blue.get(blue.size() - 1));
		blueMap.put(findStationInfo(blue.get(blue.size() - 1)), new ArrayList<StationInfo>());

		tempBlue.add(blue.get(blue.size() - 2));
		blueMap.put(findStationInfo(blue.get(blue.size() - 2)), new ArrayList<StationInfo>());

		int x = blue.indexOf("King St-Old Town");

		for (int i = x; i >= 0; i--) {
			tempBlue.add(blue.get(i));
			blueMap.put(findStationInfo(blue.get(i)), new ArrayList<>());
		}

		x++;

		for (int i = x; i < blue.size() - 2; i++) {
			tempBlue.add(blue.get(i));
			blueMap.put(findStationInfo(blue.get(i)), new ArrayList<>());
		}

		blue = tempBlue;
	}

	private void sortYellow() {
		ArrayList<String> tempYellow = new ArrayList<>();
		int x = yellow.indexOf("Pentagon");
		int y = yellow.indexOf("Huntington");

		for (int i = y; i >= x; i--) {
			tempYellow.add(yellow.get(i));
			yellowMap.put(findStationInfo(yellow.get(i)), new ArrayList<>());
		}

		tempYellow.add("L'Enfant Plaza");
		yellowMap.put(findStationInfo("L'Enfant Plaza"), new ArrayList<>());

		tempYellow.add("Archives-Navy Memorial-Penn Quarter");
		yellowMap.put(findStationInfo("Archives-Navy Memorial-Penn Quarter"), new ArrayList<>());

		tempYellow.add("Gallery Pl-Chinatown");
		yellowMap.put(findStationInfo("Gallery Pl-Chinatown"), new ArrayList<>());

		x = yellow.indexOf("Mt Vernon Sq 7th St-Convention Center");

		for (int i = x; i < x + 5; i++) {
			tempYellow.add(yellow.get(i));
			yellowMap.put(findStationInfo(yellow.get(i)), new ArrayList<>());

		}

		tempYellow.add("Fort Totten");
		yellowMap.put(findStationInfo("Fort Totten"), new ArrayList<>());

		yellow = tempYellow;
	}

	private void sortOrange() {
		ArrayList<String> tempOrange = new ArrayList<>();
		int x = orange.indexOf("Court House");

		for (int i = orange.size() - 1; i >= x; i--) {
			tempOrange.add(orange.get(i));
			orangeMap.put(findStationInfo(orange.get(i)), new ArrayList<StationInfo>());
		}

		x = orange.indexOf("Rosslyn");

		for (int i = x; i >= 0; i--) {
			tempOrange.add(orange.get(i));
			orangeMap.put(findStationInfo(orange.get(i)), new ArrayList<StationInfo>());

		}

		x = orange.indexOf("Federal Triangle");

		for (int i = x; i < x + 13; i++) {
			tempOrange.add(orange.get(i));
			orangeMap.put(findStationInfo(orange.get(i)), new ArrayList<StationInfo>());
		}

		orange = tempOrange;
	}

	private void sortGreen() {
		ArrayList<String> tempGreen = new ArrayList<>();
		int x = green.indexOf("Waterfront");

		for (int i = green.size() - 1; i >= x; i--) {
			tempGreen.add(green.get(i));
			greenMap.put(findStationInfo(green.get(i)), new ArrayList<>());
		}

		tempGreen.add("L'Enfant Plaza");
		greenMap.put(findStationInfo("L'Enfant Plaza"), new ArrayList<>());

		tempGreen.add("Archives-Navy Memorial-Penn Quarter");
		greenMap.put(findStationInfo("Archives-Navy Memorial-Penn Quarter"), new ArrayList<>());

		tempGreen.add("Gallery Pl-Chinatown");
		greenMap.put(findStationInfo("Gallery Pl-Chinatown"), new ArrayList<>());

		x = green.indexOf("Mt Vernon Sq 7th St-Convention Center");

		for (int i = x; i < x + 5; i++) {
			tempGreen.add(green.get(i));
			greenMap.put(findStationInfo(green.get(i)), new ArrayList<>());
		}

		tempGreen.add("Fort Totten");
		greenMap.put(findStationInfo("Fort Totten"), new ArrayList<>());

		x = green.indexOf("West Hyattsville");

		for (int i = x; i < 12; i++) {
			tempGreen.add(green.get(i));
			greenMap.put(findStationInfo(green.get(i)), new ArrayList<>());

		}

		green = tempGreen;
	}

	private void sortSilver() {
		ArrayList<String> tempSilver = new ArrayList<>();
		int x = silver.indexOf("Court House");

		for (int i = silver.size() - 1; i >= x; i--) {
			tempSilver.add(silver.get(i));
			silverMap.put(findStationInfo(silver.get(i)), new ArrayList<>());
		}

		x = silver.indexOf("Rosslyn");

		for (int i = x; i >= 0; i--) {
			tempSilver.add(silver.get(i));
			silverMap.put(findStationInfo(silver.get(i)), new ArrayList<>());

		}

		for (int i = silver.indexOf("Federal Triangle"); i < silver.indexOf("Court House"); i++) {
			tempSilver.add(silver.get(i));
			silverMap.put(findStationInfo(silver.get(i)), new ArrayList<>());

		}

		silver = tempSilver;
	}

	public void sortStationLists() {
		sortRed();
		sortSilver();
		sortBlue();
		sortYellow();
		sortOrange();
		sortGreen();

		lineLists.clear();
		createLineLists();
	}

	public int indexOfName(String name) {
		for (int i = 0; i < stationInfos.size(); i++) {
			if (stationInfos.get(i).getName().equals(name)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Finds the StationInfo object corresponding to given name in the sorted lists.
	 * 
	 * @param name
	 * @return
	 */
	public StationInfo findStationInfo(String name) {
		for (int i = 0; i < stationInfos.size(); i++) {
			if (stationInfos.get(i).getName().equals(name)) {
				return stationInfos.get(i);
			}
		}

		return null;
	}

	public boolean containsName(String name) {
		for (int i = 0; i < stationInfos.size(); i++) {
			if (stationInfos.get(i).getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	public void createMap() {
		// load all StationInfos as keys
		for (int i = 0; i < stationInfos.size(); i++) {
			metroMap.put(stationInfos.get(i), new ArrayList<StationInfo>());
		}

		// add values by checking connecting stations for each line
		int index = 0;
		StationInfo prev, cur, next;

		for (int i = 0; i < lineLists.size(); i++) {
			for (int j = 0; j < lineLists.get(i).size() - 1; j++) {
				cur = findStationInfo(lineLists.get(i).get(index));

				if (j == 0) {
					next = findStationInfo(lineLists.get(i).get(index + 1));
					if (!metroMap.get(cur).contains(next)) {
						metroMap.get(cur).add(next);
					}
				} else if (j != lineLists.get(i).size() - 1) {
					prev = findStationInfo(lineLists.get(i).get(index - 1));
					next = findStationInfo(lineLists.get(i).get(index + 1));

					if (!metroMap.get(cur).contains(prev)) {
						metroMap.get(cur).add(prev);
					}
					if (!metroMap.get(cur).contains(next)) {
						metroMap.get(cur).add(next);
					}
				} else {
					prev = findStationInfo(lineLists.get(i).get(index - 1));
					if (!metroMap.get(cur).contains(prev)) {
						metroMap.get(cur).add(prev);
					}
				}
				index++;
			}
			index = 0;
		}
		
	}

	public ArrayList<StationInfo> pathBetweenStations(String start, String end) {
		ArrayList<StationInfo> path = new ArrayList<>();

		// System.out.println(findStationInfo(start));

		return path;
	}

	public void createLineMaps() {
		int index = 0;
		StationInfo prev, cur, next;

		sortStationLists();

		stationMaps.add(redMap);
		stationMaps.add(blueMap);
		stationMaps.add(yellowMap);
		stationMaps.add(orangeMap);
		stationMaps.add(greenMap);
		stationMaps.add(silverMap);

		for (int i = 0; i < stationMaps.size(); i++) {
			for (int j = 0; j < stationMaps.get(i).size() - 1; j++) {
				cur = findStationInfo(lineLists.get(i).get(index));

				if (j == 0) {
					next = findStationInfo(lineLists.get(i).get(index + 1));
					stationMaps.get(i).get(cur).add(next);

				} else if (j != lineLists.get(i).size() - 1) {
					prev = findStationInfo(lineLists.get(i).get(index - 1));
					next = findStationInfo(lineLists.get(i).get(index + 1));

					stationMaps.get(i).get(cur).add(prev);
					stationMaps.get(i).get(cur).add(next);

				} else {
					prev = findStationInfo(lineLists.get(i).get(index - 1));
					stationMaps.get(i).get(cur).add(prev);
				}
				index++;
			}
			index = 0;
		}
	}

	public void printMap(Map<StationInfo, ArrayList<StationInfo>> map) {

		StringBuilder s = new StringBuilder();

		for (StationInfo key : map.keySet()) {
			s.append(key + ": ");

			for (StationInfo val : map.get(key)) {
				s.append(val + ", ");
			}
			s.replace(s.length() - 2, s.length(), "");
			s.append("\n");

		}

		s.replace(s.length() - 1, s.length(), "");

		System.out.println(s.toString());
	}

	public static void main(String[] args) {
		// Dijkstra's Algorithm
		// create spendings tracker
		// Queue for most recent searches (LinkedList with only addFirst and removeLast)
		// React app
		MetroStations metroStations = new MetroStations();
		
		System.out.println(metroStations.metroMap);
		metroStations.printMap(metroStations.silverMap);
		// metroStations.retrieveTest();
		// Path test case
		// System.out.println(metroStations.pathBetweenStations("Ashburn", "Wiehle-Reston"));
	}

}
