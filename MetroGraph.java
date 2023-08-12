import java.util.*;

public class MetroGraph {
    private class MetroNode {
        private StationInfo stationInfo;
        private List<MetroNode> adjStations;

        public MetroNode(StationInfo stationInfo) {
            this.stationInfo = stationInfo;
            adjStations = new LinkedList<>();
        }

        public String toString() {
            return stationInfo.toString();
        }

    }

    private class MetroEdge {

    }

    private MetroStations metroStations = new MetroStations();
    private Map<String, StationInfo> map = metroStations.getStationMap();
    ArrayList<String> redStations = metroStations.getRed();
    ArrayList<String> blueStations = metroStations.getBlue();
    ArrayList<String> yellowStations = metroStations.getYellow();
    ArrayList<String> orangeStations = metroStations.getOrange();
    ArrayList<String> greenStations = metroStations.getGreen();
    ArrayList<String> silverStations = metroStations.getSilver();

    // private ArrayList<MetroNode> stationNodes;
    private Map<StationInfo, MetroNode> stationNodes;
    private Map<StationInfo, LinkedHashSet<MetroNode>> graph;

    public MetroGraph() {
        stationNodes = new HashMap<>();
        graph = new LinkedHashMap<>();
    }

    public void populateNodes() {
        ArrayList<StationInfo> infos = metroStations.getStationsInfos();
        MetroNode temp;
        for (int i = 0; i < infos.size(); i++) {
            temp = new MetroNode(infos.get(i));
            if (!stationNodes.containsKey(temp)) {
                stationNodes.put(infos.get(i), temp);
            }
        }
    }

    public void createGraph() {
        StationInfo curInfo, prevInfo, nextInfo;
        MetroNode curNode, prevNode, nextNode;

        LinkedHashSet<MetroNode> tempList;

        for (int i = 0; i < redStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(redStations.get(i));
            curNode = stationNodes.get(curInfo);

            if (i == 0) {
                nextInfo = map.get(redStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(nextNode);
            } else if (i == redStations.size() - 1) {
                prevInfo = map.get(redStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(prevNode);
            } else {
                nextInfo = map.get(redStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);
                prevInfo = map.get(redStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(prevNode);
                tempList.add(nextNode);
            }
            graph.put(curInfo, tempList);

        }

        for (int i = 0; i < blueStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(blueStations.get(i));
            curNode = stationNodes.get(curInfo);

            if (i == 0) {
                nextInfo = map.get(blueStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == blueStations.size() - 1) {
                prevInfo = map.get(blueStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(blueStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);
                prevInfo = map.get(blueStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(nextNode);

                }

            }

        }
        for (int i = 0; i < yellowStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(yellowStations.get(i));
            curNode = stationNodes.get(curInfo);

            if (i == 0) {
                nextInfo = map.get(yellowStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == yellowStations.size() - 1) {
                prevInfo = map.get(yellowStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(yellowStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);
                prevInfo = map.get(yellowStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(nextNode);

                }

            }

        }
        for (int i = 0; i < orangeStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(orangeStations.get(i));
            curNode = stationNodes.get(curInfo);

            if (i == 0) {
                nextInfo = map.get(orangeStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == orangeStations.size() - 1) {
                prevInfo = map.get(orangeStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(orangeStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);
                prevInfo = map.get(orangeStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(nextNode);
                }
            }
        }
        for (int i = 0; i < greenStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(greenStations.get(i));
            curNode = stationNodes.get(curInfo);

            if (i == 0) {
                nextInfo = map.get(greenStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == greenStations.size() - 1) {
                prevInfo = map.get(greenStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(greenStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);
                prevInfo = map.get(greenStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(nextNode);

                }

            }
        }
        for (int i = 0; i < silverStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(silverStations.get(i));
            curNode = stationNodes.get(i);

            if (i == 0) {
                nextInfo = map.get(silverStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);

                tempList = new LinkedHashSet<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == silverStations.size() - 1) {
                prevInfo = map.get(silverStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(silverStations.get(i + 1));
                nextNode = stationNodes.get(nextInfo);
                prevInfo = map.get(silverStations.get(i - 1));
                prevNode = stationNodes.get(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new LinkedHashSet<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(nextNode);
                }
            }
        }
    }

    public LinkedHashSet<MetroNode> getConnectedStations(StationInfo stationInfo) {
        return graph.get(stationInfo);
    }

    public String toString(MetroNode head) {
        return head.toString();
    }

    public static void main(String[] args) {
        // adjacency matrix
        MetroGraph metroGraph = new MetroGraph();
        MetroStations metroStations = metroGraph.metroStations;
        metroGraph.populateNodes();
        metroGraph.createGraph();
        System.out.println(metroGraph.graph);
        System.out.println(metroStations.getStationsInfos().get(0));
        LinkedHashSet<MetroNode> test = metroGraph.getConnectedStations(metroStations.getStationsInfos().get(0));
        System.out.println(test);

    }

}
