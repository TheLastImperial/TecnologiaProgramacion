package Redes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetCommand {
    private static final String REGEX_COMMAND = "([^<]-[^>])|(->|<-|=>|<=)";
    private static final String REGEX_LEFT = "([a-zA-Z])([a-zA-Z0-9]{0,14})(([^<]-[^>])|(->|<-|=>|<=))";
    private static final String REGEX_RIGHT = "([a-zA-Z])([a-zA-Z0-9]{0,14})(\\.|\\?)";
    private static final String REGEX_END_COMMAND = "(\\.|\\?)";

    public static final String REGEX = "^((([a-zA-Z])([a-zA-Z0-9]{0,14})\\s*(-|->|<-)\\s*([a-zA-Z])([a-zA-Z0-9]{0,14})\\s*\\.)|(([a-zA-Z])([a-zA-Z0-9]{0,14})\\s*(=>|<=)\\s*([a-zA-Z])([a-zA-Z0-9]{0,14})\\s*\\?))";

    private String commandString;
    private boolean isQuestion;
    private String leftCommand;
    private String rightCommand;
    private Command command;

    public NetCommand(String comStr){
        this.commandString = comStr.replace(" ", "");
        Pattern pattern = Pattern.compile(REGEX_LEFT);
        Matcher matcher = pattern.matcher(this.commandString);
        matcher.find();
        this.leftCommand = matcher.group().replaceAll(REGEX_COMMAND, "");
        pattern = Pattern.compile(REGEX_RIGHT);
        matcher = pattern.matcher(this.commandString);
        matcher.find();
        this.rightCommand = matcher.group().replaceAll(REGEX_END_COMMAND, "");

        pattern = Pattern.compile(REGEX_COMMAND);
        matcher = pattern.matcher(this.commandString);
        matcher.find();
        isQuestion = matcher.group().equals("?");

        pattern = Pattern.compile(REGEX_COMMAND);
        matcher = pattern.matcher(this.commandString);
        matcher.find();
        String auxCom = matcher.group();

        command = Command.commandByString(auxCom);
    }

    public String toString(){
        String result = commandString + "\n";
        result += leftCommand + "\n";
        result += rightCommand + "\n";
        result += command;
        return result;
    }
}
