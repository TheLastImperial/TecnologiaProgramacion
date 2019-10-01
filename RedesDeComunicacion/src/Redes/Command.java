package Redes;

import com.sun.source.tree.SwitchTree;

import javax.management.monitor.CounterMonitorMBean;

public enum Command {
    CREATE_RIGHT("->"),
    CREATE_LEFT("<-"),
    ASK_RIGHT("=>"),
    ASK_LEFT("<=");
    private final String command;

    Command(String command){
        this.command = command;
    }
    public String getCommand(){
        return command;
    }

    public static Command commandByString(String com){
        Command command = Command.CREATE_LEFT;

        if(com.equals(Command.CREATE_LEFT.getCommand()))
            command = Command.CREATE_LEFT;
        else if(com.equals(Command.CREATE_RIGHT.getCommand()))
            command = Command.CREATE_RIGHT;
        else if(com.equals(Command.ASK_LEFT.getCommand()))
            command = Command.ASK_LEFT;
        else if(com.equals(Command.ASK_RIGHT.getCommand()))
            command = Command.ASK_RIGHT;
        else
            command = null;

        return command;
    }
}
