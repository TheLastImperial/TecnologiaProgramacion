package Redes;

import java.util.*;

public class Graph {
    private Hashtable<String, Node> nodes;
    private ArrayList<Node> path;
    private boolean foundPath;
    public Graph(){
        nodes = new Hashtable<String, Node>();
    }

    public void addNode(String nodeName){
        nodeName = nodeName.toLowerCase();
        if(!nodes.containsKey(nodeName)){
            nodes.put(nodeName, new Node(nodeName));
        }
    }

    public void addEdge(String left, String right){
        left = left.toLowerCase();
        right = right.toLowerCase();
        Node leftNode = nodes.get(left);
        Node rightNode = nodes.get(right);
        leftNode.addEdge(rightNode);
    }

    public void deleteEdge(String left, String right){
        left = left.toLowerCase();
        right = right.toLowerCase();
        Node leftNode = nodes.get(left);
        Node rightNode = nodes.get(right);
        leftNode.deleteEdge(rightNode);
    }

    public void searchPath(String left, String right){
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
        if(!nodes.containsKey(left) || !nodes.containsKey(right)){
            System.out.println("- " + left + " => " + right);
            return;
        }
        // El origen no tiene ninguna relacion
        if(origin.isEmpty()){
            System.out.println(pathToString());
            return;
        }else if(origin.hasDestiny(destiny)){ // En caso de que el origen ya tenga el destino.
            foundPath = true;
            System.out.println(pathToString());
            return;
        }
        // Lista resultado en caso de que se encuentre un camino
        ArrayList<Node> result = pathWay(origin, destiny);
        if(foundPath)
            path = result;

        System.out.println(pathToString());
    }

    public ArrayList<Node> pathWay(Node origen, Node destiny){
        ArrayList<Node> way = new ArrayList<Node>();
        ArrayList<Node> visited = new ArrayList<Node>();
        ArrayList<Node> path = new ArrayList<Node>();
        way.add(origen);
        visited.add(origen);
        // Se reaaliza un ciclo hasta que no hayan mas nodos.
        while(!way.isEmpty()) {
            int index = way.size() -1;
            Node node = way.get(index);
            way.remove(index);
            path.add(node);
            // En caso de encontrar ell destino. Se termina la ejecucion del while.
            if(destiny.equals(node)){
                foundPath = true;
                break;
            }
            ArrayList<Node> destinities = node.getdestinies();
            for (Node n : destinities)
                if(!visited.contains(n)){
                    visited.add(n);
                    if(way.isEmpty())
                        way.add(n);
                    else
                        way.set(0, n);
                }
        }
        return path;
    }

    public ArrayList<Node> pathWay(ArrayList<Node> path, ArrayList<Node> visited, ArrayList<Node> edges, Node origin, Node destiny){
        if(origin.isEmpty()){
            path.add(origin);
            path.add(destiny);
            return path;
        }else if(origin.hasDestiny(destiny)){
            path.add(origin);
            path.add(destiny);
            return path;
        }
        for (Node node: origin.getdestinies()) {
            if(visited.contains(node) || visited.contains(origin))
                visited.add(node);
            path.add(origin);
            System.out.println(path);
            pathWay(path, node.getdestinies(), visited, node, destiny);
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
    public String toString(){
        return new ArrayList<Node>(nodes.values()).toString();
    }
}
