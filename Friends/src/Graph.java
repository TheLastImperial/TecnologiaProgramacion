import javax.sound.midi.SysexMessage;
import java.util.*;

public class Graph {
    private Hashtable<String, Node> nodes;
    private ArrayList<String> indexes;
    public Graph() {
        indexes = new ArrayList<String>();
        nodes = new Hashtable<String, Node>();
    }
    // Agrega un nuevo nodo al grafo, en caso de existir no lo
    // agrega y en caso de no existir lo crea y luego lo agrega.
    public void addNode(Node node) {
        if (!nodes.containsKey(node.getKey())){
            nodes.put(node.getKey(), node);
            indexes.add(node.getKey());
        }
    }
    /*
     * Agrega un nuevo vertice al nodo. En caso de ya existir no hace nada.
     * En caso de no existir lo crea y luego lo agrega.
     * */
    public void addEdge(Node left, Node right) {
        Node leftNode = nodes.get(left.getKey());
        Node rightNode = nodes.get(right.getKey());
        if(leftNode == null || rightNode == null || leftNode.equals(rightNode))
            return;
        leftNode.addEdge(rightNode);
        rightNode.addEdge(leftNode);
    }

    public void addEdge(Node left, int right) {
        Node leftNode = nodes.get(left.getKey());
        if(leftNode == null || right >= indexes.size())
            return;
        Node rightNode = nodes.get(indexes.get(right));
        if(leftNode.equals(rightNode))
            return;
        leftNode.addEdge(rightNode);
        rightNode.addEdge(leftNode);
    }

    public void addEdge(int left, int right) {
        if(left >= indexes.size() || right >= indexes.size())
            return;
        Node leftNode = nodes.get(indexes.get(left));
        Node rightNode = nodes.get(indexes.get(right));
        leftNode.addEdge(rightNode);
        rightNode.addEdge(leftNode);
    }
    /*
     * Si existe un vertice lo elimina.
     * */
    public void deleteEdge(Node left, Node right) {
        Node leftNode = nodes.get(left.getKey());
        Node rightNode = nodes.get(right.getKey());
        if(leftNode == null || rightNode == null)
            return;
        leftNode.deleteEdge(rightNode);
        rightNode.deleteEdge(leftNode);
    }

    public void deleteEdge(Node left, int right) {
        Node leftNode = nodes.get(left.getKey());
        if(leftNode == null || right >= indexes.size())
            return;
        Node rightNode = nodes.get(indexes.get(right));
        leftNode.deleteEdge(rightNode);
        rightNode.deleteEdge(leftNode);
    }

    public void deleteEdge(int left, int right) {
        if(left >= indexes.size() || right >= indexes.size())
            return;
        Node leftNode = nodes.get(indexes.get(left));
        Node rightNode = nodes.get(indexes.get(right));
        leftNode.deleteEdge(rightNode);
        rightNode.deleteEdge(leftNode);
    }

    public void hasNode(Node left, Node right){
        Node leftNode = nodes.get(left.getKey());
        Node rightNode = nodes.get(right.getKey());
        if(leftNode == null || right == null){
            System.out.println("false");
            return;
        }
        if(leftNode.hasDestiny(rightNode))
            System.out.println("true");
        else
            System.out.println("false");
    }

    public void hasNode(Node left, int right){
        Node leftNode = nodes.get(left.getKey());
        if(leftNode == null || right >= indexes.size()){
            System.out.println("false");
            return;
        }
        Node rightNode = nodes.get(indexes.get(right));
        if(leftNode.hasDestiny(rightNode))
            System.out.println("true");
        else
            System.out.println("false");
    }

    public void hasNode(int left, int right){
        if(left >= indexes.size() || right >= indexes.size()){
            System.out.println("false");
            return;
        }
        Node leftNode = nodes.get(indexes.get(left));
        Node rightNode = nodes.get(indexes.get(right));
        if(leftNode.hasDestiny(rightNode))
            System.out.println("true");
        else
            System.out.println("false");
    }

    public void searchLevel(Node right, int level){
        Node rightNode = nodes.get(right.getKey());
        System.out.println(friendsLevel(rightNode, level));
    }

    public void searchLevel(int right, int level){
        Node rightNode = nodes.get(indexes.get(right));
        if(rightNode == null){
            System.out.println("[]");
            return;
        }
        ArrayList<Node> arr = friendsLevel(rightNode, level);
        System.out.println(arr);
    }

    public ArrayList<Node> friendsLevel(Node origin, int level){
        ArrayList<Node> resultList = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        pathWay(origin, 0, level, visited, resultList);
        resultList.removeIf(ele -> ele.equals(origin));
        return resultList;
    }

    public boolean pathWay(Node origin, int currentLevel, int level, ArrayList<Node> visited, ArrayList<Node> resultList){
        boolean result = false;
        if(currentLevel == level){
            if(!resultList.contains(origin))
                resultList.add(origin);
            return true;
        }
        visited.add(origin);
        ArrayList<Node> nodes = origin.getdestinies();
        if(nodes.isEmpty())
            return false;
        for (Node node: nodes)
            if(!visited.contains(node)){
                result = pathWay(node, currentLevel + 1, level, visited, resultList);
            }
        return result;
    }

    public String toString(){
        return new ArrayList<Node>(nodes.values()).toString();
        /*ArrayList<String>  result = Collections.list(nodes.keys());
        return result.toString();
        */
    }
}
