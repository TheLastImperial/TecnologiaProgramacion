import java.util.ArrayList;
import java.util.Hashtable;

public class Node {
    private String name;
    private String lastName;
    private String sex;
    private String birthday;
    private String key;
    private Hashtable<String, Node> edges;
    public Node(String name, String lastName, String sex, String birthday){
        this.name = name.trim();
        this.lastName = lastName.trim();
        this.sex = sex.trim();
        this.birthday = birthday.trim();
        this.key = this.name.replace(" ", "")
                + this.lastName.replace(" ", "")
                + this.sex.replace(" ", "")
                + this.birthday.replace(" ", "");
        this.edges = new Hashtable<String, Node>();
    }

    public void addEdge(Node node){
        if(!edges.containsKey(node.getKey()) && !this.equals(node))
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
        return edges.containsKey(node.getKey());
    }

    public String getName(){
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public String toString(){
        // return name;
        return name + " " + lastName + " " + sex + " " + birthday;
    }

    public String getKey(){
        return key;
    }

    public ArrayList<Node> getdestinies(){
        return new ArrayList<Node>(edges.values());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Node))
            return false;
        Node node = (Node) obj;
        if(!this.name.equalsIgnoreCase(node.name))
            return false;
        if(!this.lastName.equalsIgnoreCase(node.lastName))
            return false;
        if(!this.sex.equalsIgnoreCase(node.sex))
            return false;
        if(!this.birthday.equalsIgnoreCase(node.birthday))
            return false;
        if(!this.key.equalsIgnoreCase(node.key))
            return false;
        return true;
    }
}
