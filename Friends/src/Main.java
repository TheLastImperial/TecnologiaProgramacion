import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Graph graph;

    public static void main(String []args){
        String path = readPathFromUser("input");
        graph = FileHelper.createGraph(path);
        path = readPathFromUser("commands");
        ArrayList<FriendCommand> commands = FileHelper.getCommands(path);
        for (FriendCommand cmd: commands)
            executeCommand(cmd);
    }

    private static void executeCommand(FriendCommand com){
        if(com.getCommand() == Command.CF_BY_INDEX){
            graph.addEdge(com.getLeftIndex(), com.getRightIndex());
        }else if(com.getCommand() == Command.CF_BY_ATTR){
            graph.addEdge(com.getLeftFriend(), com.getRightFriend());
        }else if(com.getCommand() == Command.DEL_BY_INDEX){
            graph.deleteEdge(com.getLeftIndex(), com.getRightIndex());
        }else if(com.getCommand() == Command.DEL_BY_ATTR){
            graph.deleteEdge(com.getLeftFriend(), com.getRightFriend());
        }else if(com.getCommand() == Command.ASK_F_BY_INDEX){
            graph.hasNode(com.getLeftIndex(), com.getRightIndex());
        }else if(com.getCommand() == Command.ASK_F_BY_ATTR){
            graph.hasNode(com.getLeftFriend(), com.getRightFriend());
        }else if(com.getCommand() == Command.ASK_L_BY_INDEX){
            graph.searchLevel(com.getLeftIndex(), com.getRightIndex());
        }else if(com.getCommand() == Command.ASK_L_BY_ATTR){
            graph.searchLevel(com.getRightFriend(), com.getRightIndex());
        }
    }

    public static String readPathFromUser(String opt){
        Scanner scanner = new Scanner(System.in);
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/" + opt;
        return path;
        /*
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
        */
    }
}
