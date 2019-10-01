package Redes;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
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

        ArrayList<NetCommand> commands = ReadFile.getCommands(path);
        for (NetCommand com: commands)
            System.out.println(com);
    }
}
