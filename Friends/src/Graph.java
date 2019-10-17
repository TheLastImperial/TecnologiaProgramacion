import java.util.*;

public class Graph {
    private Hashtable<String, Node> nodes;
    private ArrayList<Node> path;

    public Graph() {
        nodes = new Hashtable<String, Node>();
    }
    // Agrega un nuevo nodo al grafo, en caso de existir no lo
    // agrega y en caso de no existir lo crea y luego lo agrega.
    public void addNode(Node node) {
        if (!nodes.containsKey(node.getKey()))
            nodes.put(node.getKey(), node);
    }
    /*
     * Agrega un nuevo vertice al nodo. En caso de ya existir no hace nada.
     * En caso de no existir lo crea y luego lo agrega.
     * */
    public void addEdge(Node left, Node right) {
        Node leftNode = nodes.get(left.getKey());
        Node rightNode = nodes.get(right.getKey());
        if(leftNode == null || rightNode == null)
            return;
        leftNode.addEdge(rightNode);
        rightNode.addEdge(leftNode);
    }
    public void addEdge(int left, int right) {
        ArrayList<Node> nodeList = new ArrayList<Node>(nodes.values());
        Node leftNode = nodeList.get(left);
        Node rightNode = nodeList.get(right);
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

    public void deleteEdge(int left, int right) {
        ArrayList<Node> nodeList = new ArrayList<Node>(nodes.values());
        Node leftNode = nodeList.get(left);
        Node rightNode = nodeList.get(right);
        if(leftNode == null || rightNode == null)
            return;
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

    public void hasNode(int left, int right){
        ArrayList<Node> nodeList = new ArrayList<Node>(nodes.values());
        if(left >= nodeList.size() || right >= nodeList.size()){
            System.out.println("false");
            return;
        }
        Node leftNode = nodeList.get(left);
        Node rightNode = nodeList.get(right);
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
        ArrayList<Node> nodeList = new ArrayList<Node>(nodes.values());
        if(right >= nodeList.size()){
            System.out.println("[]");
            return;
        }
        Node rightNode = nodeList.get(right);
        ArrayList<Node> arr = friendsLevel(rightNode, level);
        System.out.println(friendsLevel(rightNode, level));
    }

    public ArrayList<Node> friendsLevel(Node origin, int level){
        ArrayList<Node> resultList = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        pathWay(origin, 0, level, visited, resultList);
        return resultList;
    }

    public boolean pathWay(Node origin, int currentLevel, int level, ArrayList<Node> visited, ArrayList<Node> resultList){
        boolean result = false;
        if(currentLevel == level - 1){
            resultList.addAll(origin.getdestinies());
            return true;
        }
        visited.add(origin);
        ArrayList<Node> nodes = origin.getdestinies();
        if(nodes.isEmpty())
            return false;
        for (Node node: nodes)
            if(!visited.contains(node))
                result = pathWay(origin, currentLevel++, level, visited, resultList);
        return result;
    }

    public String toString(){
        return new ArrayList<Node>(nodes.values()).toString();
        /*ArrayList<String>  result = Collections.list(nodes.keys());
        return result.toString();
        */
    }
}
