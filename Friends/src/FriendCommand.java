import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FriendCommand {
    public static final String REGEX_NAME = "([a-zA-Z]+\\s*)+,\\s*([a-zA-Z]+\\s*)+,\\s*([MmFf])\\s*,\\s*(\\d{2}/\\d{2}/\\d{4})";
    // Expresion regular del comando para crear unaa relacion con los indices en el arreglo.
    private static final String REGEX_CF_BY_INDEX = "\\d+\\s*amigo\\s*\\d+";
    // Expresion regular del comando para crear una relacion con los atributos del nodo.
    private static final String REGEX_CF_BY_ATTR = REGEX_NAME + "\\s*amigo\\s*" + REGEX_NAME;
    // Expresion regular que se encarga de eliminar una relacion por su indice.
    private static final String REGEX_DEL_BY_INDEX = "\\d+\\s*eliminar\\s*\\d+";
    // Expresion regulaar que se encarga de eliminar una relacion por los atributos del nodo
    private static final String REGEX_DEL_BY_ATTR = REGEX_NAME + "\\s*eliminar\\s*" + REGEX_NAME;
    // Expresion regular para preguntar por una amitad directa usando sus indices.
    private static final String REGEX_ASK_F_BY_INDEX = "\\d+\\s*amigos\\s*\\d+";
    // Expresion regular para preguntar por una amitad directa usando los atributos del amigo.
    private static final String REGEX_ASK_F_BY_ATTR = REGEX_NAME + "\\s*amigos\\s*" + REGEX_NAME;
    // Expresion regular para preguntar por un nivel por el indice de un amigo.
    private static final String REGEX_ASK_L_BY_INDEX = "amigos\\s*\\d+\\s+\\d+";
    // Expresion regular para preguntar por un nivel por el indice de un amigo.
    private static final String REGEX_ASK_L_BY_ATTR = "amigos\\s*" + REGEX_NAME + "\\s+\\d+";
    // Expresion regular para todas las posibles preguntas o compandos.
    public static final String REGEX_INPUT_FILE = REGEX_CF_BY_INDEX + "|" + REGEX_CF_BY_ATTR + "|" + REGEX_DEL_BY_INDEX
            + "|" + REGEX_DEL_BY_ATTR + "|" + REGEX_ASK_F_BY_INDEX + "|" + REGEX_ASK_F_BY_ATTR
            + "|" + REGEX_ASK_L_BY_INDEX + "|" + REGEX_ASK_L_BY_ATTR;

    private String friendCommand;
    private int  leftIndex;
    private int rightIndex;
    private Node leftFriend;
    private Node rightFriend;
    private Command command;
    public FriendCommand(String friendCommand){
        this.friendCommand = friendCommand;
        Pattern pattern = Pattern.compile(REGEX_CF_BY_INDEX);
        Matcher matcher =  pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.CF_BY_INDEX;
            cfByIndex();
            return;
        }

        pattern = Pattern.compile(REGEX_CF_BY_ATTR);
        matcher = pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.CF_BY_ATTR;
            cfByAttr();
            return;
        }

        pattern = Pattern.compile(REGEX_DEL_BY_INDEX);
        matcher = pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.DEL_BY_INDEX;
            delByIndex();
            return;
        }

        pattern = Pattern.compile(REGEX_DEL_BY_ATTR);
        matcher = pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.DEL_BY_ATTR;
            delByAttr();
            return;
        }

        pattern = Pattern.compile(REGEX_ASK_F_BY_INDEX);
        matcher = pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.ASK_F_BY_INDEX;
            askFByIndex();
            return;
        }

        pattern = Pattern.compile(REGEX_ASK_F_BY_ATTR);
        matcher = pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.ASK_F_BY_ATTR;
            askFByAttr();
            return;
        }

        pattern = Pattern.compile(REGEX_ASK_L_BY_INDEX);
        matcher = pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.ASK_L_BY_INDEX;
            askLByIndex();
            return;
        }

        pattern = Pattern.compile(REGEX_ASK_L_BY_ATTR);
        matcher = pattern.matcher(friendCommand);
        if(matcher.find()){
            command = Command.ASK_L_BY_ATTR;
            askLByAAttr();
            return;
        }
    }

    // Estos son todos los posibles constructores para un comando.
    private void cfByIndex(){
        String splitStr[] = friendCommand.split("amigo");
        leftIndex = Integer.parseInt(splitStr[0].trim());
        rightIndex = Integer.parseInt(splitStr[1].trim());
    }

    private void cfByAttr(){
        String splitStr[] = friendCommand.split("amigo");
        String friend[] = splitStr[0].split(",");
        leftFriend = new Node(friend[0], friend[1], friend[2], friend[3]);
        friend = splitStr[1].split(",");
        rightFriend = new Node(friend[0], friend[1], friend[2], friend[3]);
    }

    private void delByIndex(){
        String splitStr[] = friendCommand.split("eliminar");
        leftIndex = Integer.parseInt(splitStr[0].trim());
        rightIndex = Integer.parseInt(splitStr[1].trim());
    }

    private void delByAttr(){
        String splitStr[] = friendCommand.split("eliminar");
        String friend[] = splitStr[0].split(",");
        leftFriend = new Node(friend[0], friend[1], friend[2], friend[3]);
        friend = splitStr[1].split(",");
        rightFriend = new Node(friend[0], friend[1], friend[2], friend[3]);
    }

    private void askFByIndex(){
        String splitStr[] = friendCommand.split("amigos");
        leftIndex = Integer.parseInt(splitStr[0].trim());
        rightIndex = Integer.parseInt(splitStr[1].trim());
    }

    private void askFByAttr(){
        String splitStr[] = friendCommand.split("amigos");
        String friend[] = splitStr[0].split(",");
        leftFriend = new Node(friend[0], friend[1], friend[2], friend[3]);
        friend = splitStr[1].split(",");
        rightFriend = new Node(friend[0], friend[1], friend[2], friend[3]);
    }

    private void askLByIndex(){
        String aux = friendCommand.replace("amigos", "").trim();
        aux = aux.replaceAll("\\s+", " ");
        String splitStr[] = aux.split(" ");
        leftIndex = Integer.parseInt(splitStr[0].trim());
        rightIndex = Integer.parseInt(splitStr[1].trim());
    }

    private void askLByAAttr(){
        String aux = friendCommand.replace("amigos", "").trim();
        Pattern pattern = Pattern.compile(REGEX_NAME);
        Matcher matcher = pattern.matcher(aux);
        matcher.find();
        String auxFriend =  matcher.group();
        String friend[] = auxFriend.split(",");
        rightFriend = new Node(friend[0], friend[1], friend[2], friend[3]);
        aux = aux.replaceAll(REGEX_NAME, " ");
        rightIndex = Integer.parseInt(aux.trim());
    }

    public String getFriendCommand() {
        return friendCommand;
    }

    public int getLeftIndex() {
        return leftIndex;
    }

    public int getRightIndex() {
        return rightIndex;
    }

    public Node getLeftFriend() {
        return leftFriend;
    }

    public Node getRightFriend() {
        return rightFriend;
    }

    public Command getCommand() {
        return command;
    }

    public String toString(){
        String result = command + " ";
        result += leftFriend + " ";
        result += rightFriend + " ";
        result += leftIndex + " ";
        result += rightIndex;
        return result;
    }
}
