package Redes;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Graph graph;
    public static void main(String args[]){
        graph = new Graph();
        //String path = readPathFromUser();
        String path = "D:\\RImperialRojo\\Maestria\\Semestre001\\TecProg\\projects\\RedesDeComunicacion\\input";
        ArrayList<NetCommand> commands = ReadFile.getCommands(path);
        for (NetCommand com: commands)
            executeCommand(com);
    }
    private static void executeCommand(NetCommand com){
        if(!com.isQuestion()){
            graph.addNode(com.getLeftCommand());
            graph.addNode(com.getRightCommand());
        }
        if(com.getCommand() == Command.CREATE_RIGHT)
            graph.addEdge(com.getLeftCommand(), com.getRightCommand());
        else if(com.getCommand() == Command.CREATE_LEFT)
            graph.addEdge(com.getRightCommand(), com.getLeftCommand());
        else if(com.getCommand() == Command.DELETE){
            graph.deleteEdge(com.getLeftCommand(), com.getRightCommand());
        }else if(com.getCommand() == Command.ASK_RIGHT){
            graph.searchPath(com.getLeftCommand(), com.getRightCommand());
        }else if(com.getCommand() == Command.ASK_LEFT){
            graph.searchPath(com.getRightCommand(), com.getLeftCommand());
        }
    }
    public static String readPathFromUser(){
        Scanner scanner = new Scanner(System.in);
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/input";
        System.out.println("Se buscara un archivo en la ruta: " + path);
        System.out.print("Presiona e si deseas colocar tu propio path, presiona cualquier tecla para continuar con el path anterior: ");
        String aux = scanner.nextLine();

        if(aux.equals("e")){
            while(true){
                System.out.print("Ingrese el path: ");
                aux = scanner.nextLine();
                File file = new File(aux);
                if(!file.exists()){
                    System.out.println("No se encontro el archivo.");
                }else{
                    path = aux;
                    break;
                }
            }
        }
        return path;
    }
}
