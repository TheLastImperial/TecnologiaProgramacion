import java.util.ArrayList;

public class Tree<T extends Number> {
    private Node<T> root;
    public Tree(){
        this.root = null;
    }

    public boolean search(T d){
        return search(root, d) != null;
    }

    public int size(){
        return size(root);
    }

    public void insert(T n){
        root = insert(root, n);
    }

    public int nSuns(Node node){
        return size(node) - 1;
    }

    public int[] balance(Node node){
        return new int[]{
            size(node.getLeft()),
            size(node.getRight())
        };
    }

    public ArrayList<Node> genNodes(int gen){
        ArrayList<Node> result = new ArrayList<Node>();
        genNodes(result, root, 0, gen);
        return result;
    }

    public double sum(){
        return sum(root, 0);
    }

    public double avg(){
        return sum(root, 0) / size();
    }

    public T max(){
        return max(root);
    }

    public T father(T dato){
        return father(root, dato);
    }

    public void inorden(){
        inorden(root);
        System.out.println();
    }

    private void inorden(Node n){
        if(n != null){
            inorden(n.getLeft());
            System.out.println(n.getValue());
            inorden(n.getRight());
        }
    }

    public void preorden(){
        preorden(root);
        System.out.println();
    }

    private void preorden(Node n){
        if( n != null){
            System.out.println(root.getValue());
            preorden(n.getLeft());
            preorden(n.getRight());
        }
    }

    public void postorden(){
        postorden(root);
        System.out.println();
    }

    public void postorden(Node n){
        if(n != null){
            postorden(n.getLeft());
            postorden(n.getRight());
            System.out.println(n.getValue());
        }
    }

    public void delete(T dato){
        delete(root, null, dato);
    }

    private void delete(Node node, Node father, T dato){
        if(node == null)
            return;
        if(node.getValue() != dato){
            if(dato.doubleValue() <= node.getValue().doubleValue())
                delete(node.getLeft(), node, dato);
            else
                delete(node.getRight(), node, dato);
        }else if(node.getValue().doubleValue() == dato.doubleValue()){
            // Caso en que es una hoja
            if(node.getLeft() == null && node.getRight() == null){
                if(father.getLeft() != null && father.getLeft() == node)
                    father.setLeft(null);
                else
                    father.setRight(null);
            // Caso en que el nodo tiene ambos nodos izquierdo y derecho.
            }else if(node.getRight() != null && node.getLeft() != null){
                Node aux = node.getRight();
                Node auxF = node;
                while(aux.getLeft() != null){
                    auxF = aux;
                    aux = auxF.getLeft();
                }
                if(father != null){
                    aux.setLeft(node.getLeft());
                    if(father.getLeft().getValue().doubleValue() == node.getValue().doubleValue()){
                        father.setLeft(aux);
                    }
                    else{
                        father.setRight(aux);
                    }
                } else{
                    aux.setLeft(node.getLeft());
                    root = aux;
                }
                auxF.setLeft(null);
            // Caso en que solo tiene una hoja.
            }else{
                if(node.getLeft() == null){
                    if(father.getLeft().getValue().doubleValue() == node.getValue().doubleValue())
                        father.setLeft(node.getRight());
                    else
                        father.setRight(node.getRight());
                }else{
                    if(father.getLeft().getValue().doubleValue() == node.getValue().doubleValue())
                        father.setLeft(node.getLeft());
                    else
                        father.setRight(node.getLeft());
                }
            }
        }
    }

    private T father(Node node, T dato){
        if(node.getLeft().getValue().doubleValue() == dato.doubleValue()
            || node.getRight().getValue().doubleValue() == dato.doubleValue()) {
            if(node == null)
                return (T) coalesce(null, 0);
            return (T) node.getValue();
        }
        if(dato.doubleValue() <= node.getValue().doubleValue())
            return father(node.getLeft(), dato);
        else
            return father(node.getRight(), dato);
    }

    private T max(Node node){
        if(node  == null )
            return (T) coalesce(null, 0);
        if(node.getRight() == null)
            return (T) node.getValue();
        return max(node.getRight());
    }

    private Double sum(Node node, Number sum){
        if(node == null)
            return coalesce(null, 0).doubleValue();
        return sum.doubleValue() + node.getValue().doubleValue()
            + sum(node.getLeft(), 0) + sum(node.getRight(), 0);
    }

    private void genNodes(ArrayList<Node> nodes, Node node, int index, int gen) {
        if(node == null)
            return;
        if(index == gen){
            nodes.add(node);
            return;
        }
        genNodes(nodes, node.getLeft(), index + 1, gen);
        genNodes(nodes, node.getRight(), index + 1, gen);
    }

    private Node search(Node node, T d){
        if (node==null)
            return null;
        if (d.doubleValue() == node.getValue().doubleValue())
            return node;
        else
            if (d.doubleValue() <node.getValue().doubleValue())
                return search(node.getLeft(), d);
            else
                return search(node.getRight(), d);
    }

    private int size(Node node){
        if(node == null)
            return 0;
        else
            return 1 + size(node.getLeft()) + size(node.getRight());
    }

    private Node insert(Node node, T value){
        if(node == null){
            return new Node(value);
        }else{
            if(node.getValue().doubleValue() <= value.doubleValue())
                node.setLeft(insert(node.getLeft(), value));
            else
                node.setRight(insert(node.getRight(), value));
        }
        return node;
    }

    private <T extends Number> T coalesce(T a, T b){
        return a == null ? b : a;
    }
}
