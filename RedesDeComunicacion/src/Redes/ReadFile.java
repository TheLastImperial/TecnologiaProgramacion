package Redes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {

    public static ArrayList<NetCommand> getCommands(String path){
        ArrayList<NetCommand> result = new ArrayList<NetCommand>();
        ArrayList<String> strComs = getStrings(path);
        for (String com: strComs) {
            NetCommand rc = stringToCommand(com);
            if(rc != null)
                result.add(rc);
        }
        return result;
    }
    private static ArrayList<String> getStrings(String path){
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
    private static NetCommand stringToCommand(String str){
        NetCommand result = null;
        Pattern pattern = Pattern.compile(NetCommand.REGEX);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            result = new NetCommand(matcher.group());
        }
        return result;
    }
}
