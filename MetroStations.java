
import com.google.gson.*;
import java.net.*;
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

	private Map<String, StationInfo> stationMap = new LinkedHashMap<>();

	public MetroStations() {
		retrieveStations();
		createStationLists();
		sortStationLists();
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
				JsonObject obj;
				String name, code;
				ArrayList<String> colors;
				StationInfo stationInfo;

				for (int i = 0; i < stationsArray.size(); i++) {
					obj = stationsArray.get(i).getAsJsonObject();
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

					if (!stationMap.containsKey(name)) {
						stationInfo = new StationInfo(name, colors, code);
						stationInfos.add(stationInfo);
						stationMap.put(name, new StationInfo(name, colors, code));

					} else {
						stationInfos.get(indexOfName(name)).addLineColors(colors);
						stationMap.get(name).addLineColors(colors);
					}
				}
			} else {
				System.out.println("Error: " + statusCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * Convert to hash maps for easier look up
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

	// public ArrayList<StationInfo> getStationsInfos() {
	// return stationInfos;
	// }

	// public ArrayList<String> getRed() {
	// // Iterator<StationInfo> names = redMap.keySet().iterator();
	// // StringBuilder s = new StringBuilder();

	// // while (names.hasNext()) {
	// // s.append(names.next().getName() + "\n");
	// // }
	// // s.replace(s.length() - 1, s.length(), "");

	// // return s.toString();
	// return red;
	// }

	// public ArrayList<String> getBlue() {
	// return blue;
	// }

	// public ArrayList<String> getYellow() {
	// return yellow;
	// }

	// public ArrayList<String> getOrange() {
	// return orange;
	// }

	// public ArrayList<String> getGreen() {
	// return green;
	// }

	// public ArrayList<String> getSilver() {
	// return silver;
	// }

	public void createStationLists() {
		for (int i = 0; i < stationInfos.size(); i++) {
			if (stationInfos.get(i).getLineColors().contains("RD")) {
				red.add(stationInfos.get(i).getName());

			}
			if (stationInfos.get(i).getLineColors().contains("BL")) {
				blue.add(stationInfos.get(i).getName());

			}
			if (stationInfos.get(i).getLineColors().contains("YL")) {
				yellow.add(stationInfos.get(i).getName());

			}
			if (stationInfos.get(i).getLineColors().contains("OR")) {
				orange.add(stationInfos.get(i).getName());

			}
			if (stationInfos.get(i).getLineColors().contains("GR")) {
				green.add(stationInfos.get(i).getName());

			}
			if (stationInfos.get(i).getLineColors().contains("SV")) {
				silver.add(stationInfos.get(i).getName());

			}
		}
	}

	private void sortRed() {
		ArrayList<String> tempRed = new ArrayList<>();
		int x = red.indexOf("Shady Grove");

		for (int i = x; i >= 0; i--) {
			tempRed.add(red.get(i));
		}

		x = red.indexOf("Gallery Pl-Chinatown");

		for (int i = x; i < x + 3; i++) {
			tempRed.add(red.get(i));
		}

		tempRed.add("NoMa-Gallaudet U");

		for (int i = x + 3; i < red.size() - 1; i++) {
			tempRed.add(red.get(i));
		}

		red = tempRed;
	}

	private void sortBlue() {
		ArrayList<String> tempBlue = new ArrayList<>();

		tempBlue.add(blue.get(blue.size() - 1));
		tempBlue.add(blue.get(blue.size() - 2));

		int x = blue.indexOf("King St-Old Town");

		for (int i = x; i >= 0; i--) {
			tempBlue.add(blue.get(i));
		}

		x++;

		for (int i = x; i < blue.size() - 2; i++) {
			tempBlue.add(blue.get(i));
		}

		blue = tempBlue;
	}

	private void sortYellow() {
		ArrayList<String> tempYellow = new ArrayList<>();
		int x = yellow.indexOf("Pentagon");
		int y = yellow.indexOf("Huntington");

		for (int i = y; i >= x; i--) {
			tempYellow.add(yellow.get(i));
		}

		tempYellow.add("L'Enfant Plaza");
		tempYellow.add("Archives-Navy Memorial-Penn Quarter");
		tempYellow.add("Gallery Pl-Chinatown");

		x = yellow.indexOf("Mt Vernon Sq 7th St-Convention Center");

		for (int i = x; i < x + 5; i++) {
			tempYellow.add(yellow.get(i));
		}

		tempYellow.add("Fort Totten");

		yellow = tempYellow;
	}

	private void sortOrange() {
		ArrayList<String> tempOrange = new ArrayList<>();
		int x = orange.indexOf("Court House");

		for (int i = orange.size() - 1; i >= x; i--) {
			tempOrange.add(orange.get(i));
		}

		x = orange.indexOf("Rosslyn");

		for (int i = x; i >= 0; i--) {
			tempOrange.add(orange.get(i));
		}

		x = orange.indexOf("Federal Triangle");

		for (int i = x; i < x + 13; i++) {
			tempOrange.add(orange.get(i));
		}

		orange = tempOrange;
	}

	private void sortGreen() {
		ArrayList<String> tempGreen = new ArrayList<>();
		int x = green.indexOf("Waterfront");

		for (int i = green.size() - 1; i >= x; i--) {
			tempGreen.add(green.get(i));
		}

		tempGreen.add("L'Enfant Plaza");
		tempGreen.add("Archives-Navy Memorial-Penn Quarter");
		tempGreen.add("Gallery Pl-Chinatown");

		x = green.indexOf("Mt Vernon Sq 7th St-Convention Center");

		for (int i = x; i < x + 5; i++) {
			tempGreen.add(green.get(i));

		}

		tempGreen.add("Fort Totten");

		x = green.indexOf("West Hyattsville");

		for (int i = x; i < 12; i++) {
			tempGreen.add(green.get(i));
		}

		green = tempGreen;
	}

	private void sortSilver() {
		ArrayList<String> tempSilver = new ArrayList<>();
		int x = silver.indexOf("Court House");

		for (int i = silver.size() - 1; i >= x; i--) {
			tempSilver.add(silver.get(i));
		}

		x = silver.indexOf("Rosslyn");

		for (int i = x; i >= 0; i--) {
			tempSilver.add(silver.get(i));
		}

		for (int i = silver.indexOf("Federal Triangle"); i < silver.indexOf("Court House"); i++) {
			tempSilver.add(silver.get(i));
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
	}

	public static void main(String[] args) {
		// Dijkstra's Algorithm
		// create spendings tracker
		// Queue for most recent searches (LinkedList with only addFirst and removeLast)
		// React app
		MetroStations metroStations = new MetroStations();
		System.out.println(metroStations.stationInfos);
		// Path test case
		// System.out.println(metroStations.pathBetweenStations("Ashburn", "SV",
		// "Wiehle-Reston"));
	}

}
