package Redes;

import java.util.*;

public class Graph {
    private Hashtable<String, Node> nodes;
    private ArrayList<Node> path;
    private boolean foundPath;

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
        foundPath = false;

        // No existe alguno de los 2 nodos.
        if (!nodes.containsKey(left) || !nodes.containsKey(right)) {
            System.out.println("- " + left + " => " + right);
            return;
        }
        // El origen no tiene ninguna relacion
        if (origin.isEmpty()) {
            System.out.println(pathToString());
            return;
        } else if (origin.hasDestiny(destiny)) { // En caso de que el origen ya tenga el destino.
            foundPath = true;
            System.out.println(pathToString());
            return;
        }
        // Lista resultado en caso de que se encuentre un camino
/*
        ArrayList<Node> result = pathWay2(origin, destiny);
        if (foundPath)
            path = result;
        System.out.println(pathToString());
*/

        Stack<Node> stack = new Stack<Node>();
        boolean t = pathWay(origin, destiny, new ArrayList<Node>(), stack);
        String result = stackToString(t, origin, destiny, stack);
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

    public ArrayList<Node> pathWay2(Node origen, Node destiny){
        ArrayList<Node> visited = new ArrayList<Node>();
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> path = new ArrayList<Node>();
        stack.push(origen);
        visited.add(origen);
        // Se realiza un ciclo hasta que no hayan mas nodos.
        while(!stack.isEmpty()) {
            Node node = stack.pop();
            path.add(node);
            // En caso de encontrar ell destino. Se termina la ejecucion del while.
            if(destiny.equals(node)){
                foundPath = true;
                break;
            }
            ArrayList<Node> destinities = node.getdestinies();
            if(destinities.isEmpty()){
                path.remove(path.size() - 1);
            }
            for (Node n : destinities)
                if(!visited.contains(n)){
                    visited.add(n);
                    stack.push(n);
                }
        }
        if(foundPath){
            int size = path.size()- 1;
            int index = 0;
            while(index <= size ){
                if(index == size){
                    Node n = path.get(index - 1);
                    if(!n.hasDestiny(path.get(index)))
                        path.remove(n);
                    index++;
                    continue;
                }
                Node n = path.get(index);
                if(!n.hasDestiny(path.get(index + 1))){
                    size--;
                    path.remove(n);
                    continue;
                }
                index++;
            }
        }
        return path;
    }

    private String pathToString(){
        String result = "";
        if(foundPath)
            result = "+ ";
        else
            result = "- ";
        for (int i= 0; i< path.size(); i++){
            if(path.size() -1 == i)
                result += path.get(i).getName();
            else
                result += path.get(i).getName() + " => ";
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
