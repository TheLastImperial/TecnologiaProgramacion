import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper {
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

    public static ArrayList<String> getLines(String path){
        ArrayList<String> result = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null)
                result.add(line);
            reader.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido una exception. " + e.getMessage());
        }
        return result;
    }
}
