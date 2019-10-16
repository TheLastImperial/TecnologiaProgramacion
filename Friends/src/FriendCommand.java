public class FriendCommand {
    public static final String REGEX_NAME = "([a-zA-Z]{1,20})\\s*,\\s*([a-zA-Z]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{4}-\\d{2}-\\d{2})";
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

    String str;

    public FriendCommand(String str){
        this.str = str;
    }
}
