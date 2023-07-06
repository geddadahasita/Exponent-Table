import java.util.*;


/**
 * Class that stores Station Information for each WMATA (Metro) Station. Includes name, color line, and code.
 */
public class StationInfo {
	
	private String name; 
	private ArrayList<String> lineColors;
	private String stationCode;

	public StationInfo(String name, ArrayList<String> lineColors, String stationCode) {
		this.name = name;
		this.lineColors = lineColors;
		this.stationCode = "(" + stationCode + ")";
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getLineColors() {
		return lineColors;
	}

	public String getStationCode() {
		return stationCode;
	}


	public void addLineColors(ArrayList<String> lineCols) {
		for(int i = 0; i < lineCols.size(); i++) {
			lineColors.add(lineCols.get(i));
		}
	}

	public String toString() {
		StringBuilder stationInfo = new StringBuilder();

		stationInfo.append(name + " " + lineColors + " " + stationCode);

		return stationInfo.toString();
	}
}