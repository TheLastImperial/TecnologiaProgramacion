import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Graph graph;
    private static int startIndex;
    public static void main(String []args){
        readStartIndex();
        String path = readPathFromUser("input");
        graph = FileHelper.createGraph(path);
        path = readPathFromUser("commands");
        ArrayList<FriendCommand> commands = FileHelper.getCommands(path);
        for (FriendCommand cmd: commands)
            executeCommand(cmd);
    }

    private static void executeCommand(FriendCommand com){
        if(com.getCommand() == Command.CF_BY_INDEX){
            graph.addEdge(com.getLeftIndex() - startIndex, com.getRightIndex() - startIndex);
        }else if(com.getCommand() == Command.CF_BY_ATTR){
            graph.addEdge(com.getLeftFriend(), com.getRightFriend());
        }else if(com.getCommand() == Command.DEL_BY_INDEX){
            graph.deleteEdge(com.getLeftIndex() - startIndex, com.getRightIndex() - startIndex);
        }else if(com.getCommand() == Command.DEL_BY_ATTR){
            graph.deleteEdge(com.getLeftFriend(), com.getRightFriend());
        }else if(com.getCommand() == Command.ASK_F_BY_INDEX){
            graph.hasNode(com.getLeftIndex() - startIndex, com.getRightIndex() - startIndex);
        }else if(com.getCommand() == Command.ASK_F_BY_ATTR){
            graph.hasNode(com.getLeftFriend(), com.getRightFriend());
        }else if(com.getCommand() == Command.ASK_L_BY_INDEX){
            graph.searchLevel(com.getLeftIndex() - startIndex, com.getRightIndex());
        }else if(com.getCommand() == Command.ASK_L_BY_ATTR){
            graph.searchLevel(com.getRightFriend(), com.getRightIndex());
        }else if(com.getCommand() == Command.CF_BY_ATTR_INDEX){
            graph.addEdge(com.getRightFriend(), com.getRightIndex() - startIndex);
        }else if(com.getCommand() == Command.DEL_BY_ATTR_INDEX){
            graph.deleteEdge(com.getRightFriend(), com.getRightIndex() - startIndex);
        }else if(com.getCommand() == Command.ASK_F_BY_ATTR_INDEX){
            graph.hasNode(com.getRightFriend(), com.getRightIndex() - startIndex);
        }
    }

    public static String readPathFromUser(String opt){
        Scanner scanner = new Scanner(System.in);
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/" + opt;
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

    public static void readStartIndex(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Teclee un 1 si desea que el indice del catalogo comience en 1 cualquier digito para comenzar en cero: ");
            try{
                startIndex = scanner.nextInt();
                if(startIndex != 1)
                    startIndex = 0;
            }catch(Exception e){
                System.out.println("Debe ingresar solo numeros");
                scanner.nextLine();
                continue;
            }
            break;
        }
    }
}
