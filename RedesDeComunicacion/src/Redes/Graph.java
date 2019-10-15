package Redes;

import java.util.*;

public class Graph {
    private Hashtable<String, Node> nodes;
    private ArrayList<Node> path;

    public Graph() {
        nodes = new Hashtable<String, Node>();
    }
    // Agrega un nuevo nodo al grafo, en caso de existir no lo
    // agrega y en caso de no existir lo crea y luego lo agrega.
    public void addNode(String nodeName) {
        nodeName = nodeName.toLowerCase();
        if (!nodes.containsKey(nodeName)) {
            nodes.put(nodeName, new Node(nodeName));
        }
    }
    /*
    * Agrega un nuevo vertice al nodo. En caso de ya existir no hace nada.
    * En caso de no existir lo crea y luego lo agrega.
    * */
    public void addEdge(String left, String right) {
        left = left.toLowerCase();
        right = right.toLowerCase();
        Node leftNode = nodes.get(left);
        Node rightNode = nodes.get(right);
        leftNode.addEdge(rightNode);
    }
    /*
    * Si existe un vertice lo elimina.
    * */
    public void deleteEdge(String left, String right) {
        left = left.toLowerCase();
        right = right.toLowerCase();
        Node leftNode = nodes.get(left);
        Node rightNode = nodes.get(right);
        leftNode.deleteEdge(rightNode);
    }

    /*
    * Este metodo se encarga de validar que los nodos puedan ser buscados.
    * Si se cumple se ejecuta el metodo pathWay para encontrar el camino.
    * */
    public void searchPath(String left, String right) {
        left = left.toLowerCase();
        right = right.toLowerCase();

        // Se obtienen los nodos origen y destino.
        Node origin = nodes.get(left);
        Node destiny = nodes.get(right);
        this.path = new ArrayList<Node>();
        path.add(origin);
        path.add(destiny);

        // No existe alguno de los 2 nodos.
        if (!nodes.containsKey(left) || !nodes.containsKey(right)) {
            System.out.println("- " + left + " => " + right);
            return;
        }
        // El origen no tiene ninguna relacion con alguna ciudad.
        if (origin.isEmpty()) {
            System.out.println("- " + left + " => " + right);
            return;
        } else if (origin.hasDestiny(destiny)) { // En caso de que el origen ya tenga el destino.
            System.out.println("+ " + left + " => " + right);
            return;
        }
        // Lista resultado en caso de que se encuentre un camino
        Stack<Node> stack = new Stack<Node>();
        boolean isAWay = pathWay(origin, destiny, new ArrayList<Node>(), stack);
        String result = stackToString(isAWay, origin, destiny, stack);
        System.out.println(result);
    }
    /*
    * Busca en los nodos hasta encontrar el destino o que ya se
    * haya recorrido todos los posibles caminos.
    * */
    public boolean pathWay(Node origin, Node destiny, ArrayList<Node> visited, Stack<Node> stack){
        visited.add(origin);
        boolean result = false;
        if(origin.equals(destiny)){
            return true;
        }
        ArrayList<Node> nodes = origin.getdestinies();
        if(nodes.isEmpty())
            return false;
        for (Node node: nodes) {
            if(!visited.contains(node)){
                result = pathWay(node, destiny, visited, stack);
                if(result){
                    stack.push(node);
                    return true;
                }
            }
        }
        return result;
    }

    private String stackToString(boolean found, Node origin, Node destiny, Stack<Node> stack){
        String result = "";
        if(found)
            result = "+ " + origin.getName() + " => ";
        else
            result = "- " + origin.getName() + " => " + destiny.getName();
        while(found && !stack.isEmpty()){
            result += stack.pop().getName();
            if(!stack.isEmpty())
                result += " => ";
        }
        return result;
    }
    public String toString(){
        return new ArrayList<Node>(nodes.values()).toString();
    }
}
