import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHelper {
    private static final String REGEX = "([a-zA-Z]{1,20})\\s*,\\s*([a-zA-Z]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{4}-\\d{2}-\\d{2})";

    public static Graph createNode(String path){
        return stringToNode(getLines(path));
    }

    private static Graph stringToNode(ArrayList<String> strs){
        Pattern pattern = Pattern.compile(REGEX);
        Graph result = new Graph();
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
            }
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
}
