import java.util.*;

public class MetroGraph {
    private class MetroNode {
        private StationInfo stationInfo;
        private List<MetroNode> adjStations;

        public MetroNode(StationInfo stationInfo) {
            this.stationInfo = stationInfo;
            adjStations = new LinkedList<>();
        }

        // public String getNode() {
        // return stationName;
        // }
        // public void setNode(String stationInfo) {
        // this.stationName = stationInfo;
        // }
        // public MetroNode getNext() {
        // return next;
        // }
        // public void setNext(MetroNode next) {
        // this.next = next;
        // }
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

    private Map<StationInfo, ArrayList<MetroNode>> graph;

    public MetroGraph() {
        graph = new LinkedHashMap<>();
    }

    public void createGraph() {
        StationInfo curInfo, prevInfo, nextInfo;
        MetroNode curNode, prevNode, nextNode;

        ArrayList<MetroNode> tempList;

        for (int i = 0; i < redStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(redStations.get(i));
            curNode = new MetroNode(curInfo);

            if (i == 0) {
                nextInfo = map.get(redStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);

                tempList = new ArrayList<>();
                tempList.add(nextNode);
            } else if (i == redStations.size() - 1) {
                prevInfo = map.get(redStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                tempList = new ArrayList<>();
                tempList.add(prevNode);
            } else {
                nextInfo = map.get(redStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);
                prevInfo = map.get(redStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                tempList = new ArrayList<>();
                tempList.add(prevNode);
                tempList.add(nextNode);
            }
            graph.put(curInfo, tempList);

        }

        for (int i = 0; i < blueStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(blueStations.get(i));
            curNode = new MetroNode(curInfo);

            if (i == 0) {
                nextInfo = map.get(blueStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);

                tempList = new ArrayList<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == blueStations.size() - 1) {
                prevInfo = map.get(blueStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(blueStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);
                prevInfo = map.get(blueStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(prevNode);

                }

            }

        }
        for (int i = 0; i < yellowStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(yellowStations.get(i));
            curNode = new MetroNode(curInfo);

            if (i == 0) {
                nextInfo = map.get(yellowStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);

                tempList = new ArrayList<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == yellowStations.size() - 1) {
                prevInfo = map.get(yellowStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(yellowStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);
                prevInfo = map.get(yellowStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(prevNode);

                }

            }

        }
        for (int i = 0; i < orangeStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(orangeStations.get(i));
            curNode = new MetroNode(curInfo);

            if (i == 0) {
                nextInfo = map.get(orangeStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);

                tempList = new ArrayList<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == orangeStations.size() - 1) {
                prevInfo = map.get(orangeStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(orangeStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);
                prevInfo = map.get(orangeStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(prevNode);

                }
            }
        }
        for (int i = 0; i < greenStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(greenStations.get(i));
            curNode = new MetroNode(curInfo);

            if (i == 0) {
                nextInfo = map.get(greenStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);

                tempList = new ArrayList<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == greenStations.size() - 1) {
                prevInfo = map.get(greenStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(greenStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);
                prevInfo = map.get(greenStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(prevNode);

                }

            }
        }
        for (int i = 0; i < silverStations.size(); i++) {
            // maybe make type stationInfo
            curInfo = map.get(silverStations.get(i));
            curNode = new MetroNode(curInfo);

            if (i == 0) {
                nextInfo = map.get(silverStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);

                tempList = new ArrayList<>();
                tempList.add(nextNode);
                graph.put(curInfo, tempList);
            } else if (i == silverStations.size() - 1) {
                prevInfo = map.get(silverStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                }

            } else {
                // add if contains key if statement here. structure code better
                nextInfo = map.get(silverStations.get(i + 1));
                nextNode = new MetroNode(nextInfo);
                prevInfo = map.get(silverStations.get(i - 1));
                prevNode = new MetroNode(prevInfo);

                if (!graph.containsKey(curInfo)) {
                    tempList = new ArrayList<>();
                    tempList.add(prevNode);
                    tempList.add(nextNode);
                    graph.put(curInfo, tempList);

                } else {
                    graph.get(curInfo).add(prevNode);
                    graph.get(curInfo).add(prevNode);
                }
            }
        }
    }

    public ArrayList<MetroNode> getConnectedStations(StationInfo stationInfo) {
        return graph.get(stationInfo);
    }
    public String toString(MetroNode head) {
        return head.toString();
    }

    public static void main(String[] args) {
        // adjacency matrix
        MetroGraph graph = new MetroGraph();
        graph.createGraph();
        System.out.println(graph.graph);
        
        // while (graph.redHead != null) {
        // System.out.println(graph.toString(graph.redHead));
        // // graph.redHead = graph.redHead.next;

        // }

    }

}