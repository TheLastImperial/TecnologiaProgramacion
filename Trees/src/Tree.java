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
        root = insert(root, n);
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

    public int father(int dato){
        return father(root, dato);
    }

    public void inorden(){
        inorden(root);
        System.out.println();
    }

    private void inorden(Node n){
        if(n != null){
            inorden(n.left);
            System.out.println(n.dato);
            inorden(n.right);
        }
    }

    public void preorden(){
        preorden(root);
        System.out.println();
    }

    private void preorden(Node n){
        if( n != null){
            System.out.println(root.dato);
            preorden(n.left);
            preorden(n.right);
        }
    }

    public void postorden(){
        postorden(root);
        System.out.println();
    }

    public void postorden(Node n){
        if(n != null){
            postorden(n.left);
            postorden(n.right);
            System.out.println(n.dato);
        }
    }

    public void delete(int dato){
        delete(root, null, dato);
    }

    private void delete(Node node, Node father, int dato){
        if(node == null)
            return;
        if(node.dato != dato){
            if(dato <= node.dato)
                delete(node.left, node, dato);
            else
                delete(node.right, node, dato);
        }else if(node.dato == dato){
            // Caso en que es una hoja
            if(node.left == null && node.right == null){
                if(father.left != null && father.left == node)
                    father.left = null;
                else
                    father.right = null;
            // Caso en que el nodo tiene ambos nodos izquierdo y derecho.
            }else if(node.right != null && node.left != null){
                Node aux = node.right;
                Node auxF = node;
                while(aux.left != null){
                    auxF = aux;
                    aux = auxF.left;
                }
                if(father != null){
                    aux.left = node.left;
                    if(father.left == node){
                        father.left = aux;
                    }
                    else{
                        father.right = aux;
                    }
                } else{
                    aux.left = node.left;
                    root = aux;
                }
                auxF.left = null;
            // Caso en que solo tiene una hoja.
            }else{
                if(node.left == null){
                    if(father.left.dato == node.dato)
                        father.left = node.right;
                    else
                        father.right = node.right;
                }else{
                    if(father.left.dato == node.dato)
                        father.left = node.left;
                    else
                        father.right = node.left;
                }
            }
        }
    }

    private int father(Node node, int dato){
        if(node == null || node.dato == dato)
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
        if(node == null)
            return 0;
        return sum + node.dato + sum(node.left, 0) + sum(node.right, 0);
    }

    private void genNodes(ArrayList<Node> nodes, Node node, int index, int gen) {
        if(node == null)
            return;
        if(index == gen){
            nodes.add(node);
            return;
        }
        genNodes(nodes, node.left, index + 1, gen);
        genNodes(nodes, node.right, index + 1, gen);
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
            return new Node(n);
        }else{
            if(n <= node.dato)
                node.left = insert(node.left, n);
            else
                node.right = insert(node.right, n);
        }
        return node;
    }
}
