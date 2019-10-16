import java.util.ArrayList;
import java.util.Hashtable;

public class Node {
    private String name;
    private String lastName;
    private String sex;
    private String birthday;
    private Hashtable<String, Node> edges;
    public Node(String name, String lastName, String sex, String birthday){
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.birthday = birthday;
        this.edges = new Hashtable<String, Node>();
    }

    public void addEdge(Node node){
        if(!edges.containsKey(node.getKey()))
            edges.put(node.getKey(), node);
    }

    public void deleteEdge(Node node){
        if(edges.containsKey(node.getKey()))
            edges.remove(node.getKey());
    }

    public boolean isEmpty(){
        return edges.size() == 0;
    }

    public boolean hasDestiny(Node node){
        return edges.containsKey(node.getName());
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }

    public String getKey(){
        return name + lastName + birthday;
    }

    public ArrayList<Node> getdestinies(){
        return new ArrayList<Node>(edges.values());
    }
}
