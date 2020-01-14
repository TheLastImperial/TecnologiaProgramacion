import java.util.ArrayList;

public class Tree {
    private Node root;
    public Tree(){
        this.root = null;
    }

    public boolean search(int d){
        return search(root, d) != null;
    }

    public int size(){
        return size(root);
    }

    public void insert(int n){
        insert(root, n);

    }

    public int nSuns(Node node){
        return size(node) - 1;
    }

    public int[] balance(Node node){
        return new int[]{
            size(node.left),
            size(node.right)
        };
    }

    public ArrayList<Node> genNodes(int gen){
        ArrayList<Node> result = new ArrayList<Node>();
        genNodes(result, root, 0, gen);
        return result;
    }

    public int sum(){
        return sum(root, 0);
    }

    public double avg(){
        return sum(root, 0) / size();
    }

    public int max(){
        return max(root);
    }

    private int father(int dato){
        return father(root, dato);
    }

    private int father(Node node, int dato){
        if(node == null)
            return 0;
        if(node.left.dato == dato || node.right.dato == dato)
            return node.dato;
        if(dato <= node.dato)
            return father(node.left, dato);
        else
            return father(node.right, dato);
    }

    private int max(Node node){
        if(node  == null )
            return 0;
        if(node.right == null)
            return node.dato;
        return max(node.right);
    }

    private int sum(Node node, int sum){
        return sum + node.dato + sum(node.left, 0) + sum(node.right, 0);
    }

    private void genNodes(ArrayList<Node> nodes, Node node, int index, int gen) {
        if(index == gen){
            nodes.add(node);
            return;
        }
        genNodes(nodes, node.left, index++, gen);
        genNodes(nodes, node.right, index++, gen);
    }

    private Node search(Node node, int d){
        if (node==null)
            return null;
        if (d == node.dato)
            return node;
        else
            if (d<node.dato)
                return search(node.left, d);
            else
                return search(node.right, d);
    }

    private int size(Node node){
        if(node == null)
            return 0;
        else
            return 1 + size(node.left) + size(node.right);
    }

    private Node insert(Node node, int n){
        if(node == null){
            node = new Node(n);
        }else{
            if(n <= node.dato)
                node.left = insert(node.left, n);
            else
                node.right = insert(node.right, n);
        }
        return node;
    }
}
