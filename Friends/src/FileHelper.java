import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHelper {
    private static final String REGEX = "([a-zA-Z]{1,20})\\s*,\\s*([a-zA-Z]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{4}-\\d{2}-\\d{2})";

    public static Graph createGraph(String path){
        return stringsToGraph(getLines(path));
    }

    public static ArrayList<FriendCommand> getCommands(String path){
        ArrayList<FriendCommand> result = new ArrayList<FriendCommand>();
        ArrayList<String> cmdStrs = getLines(path);
        Pattern pattern = Pattern.compile(FriendCommand.REGEX_INPUT_FILE);
        Matcher matcher;
        for (String cmdStr: cmdStrs) {
            matcher = pattern.matcher(cmdStr);
            if(matcher.find()){
                String aux = matcher.group();
                result.add(new FriendCommand(cmdStr));
            }else
                writeIgnoreFile(cmdStr);
        }
        return result;
    }

    private static Graph stringsToGraph(ArrayList<String> strs){
        Pattern pattern = Pattern.compile(REGEX);
        Graph result = new Graph();
        resetIgnoreFile();
        for (String str: strs){
            Matcher matcher = pattern.matcher(str);
            if(matcher.find()){
                String aux = matcher.group();
                aux = aux.replace(" ", "");
                String values[] = aux.split(",");
                result.addNode(new Node(
                        values[0],
                        values[1],
                        values[2],
                        values[3]
                ));
            }else
                writeIgnoreFile(str);
        }
        return result;
    }

    private static ArrayList<String> getLines(String path){
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

    private static void writeIgnoreFile(String input){
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/Ignorado.txt";
        try{
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(path, true));
            writer.append(input);
            writer.newLine();
            writer.close();
        }catch(Exception e){}
    }
    private static void resetIgnoreFile(){
        String path = Paths.get(".").toAbsolutePath().normalize().toString() + "/Ignorado.txt";
        try{
            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(path));
            writer.write("");
            writer.close();
        }catch(Exception e){}
    }
}
