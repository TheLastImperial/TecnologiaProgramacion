import java.util.ArrayList;

public class Main {
    private static Conacyt conacyt = new Conacyt();
    public static void main(String [] args){
        String path = FileHelper.readPathFromUser("autores.txt");
        ArrayList<String> lines = FileHelper.getLines(path);
        createAuthors(lines);
        System.out.println(conacyt);
        System.out.println("Hola mundo");
    }

    public static void createAuthors(ArrayList<String> input) {

        conacyt.addInstitute(new Institute(input.get(0)));
        Institute institute = conacyt.lastInstitution();
        for (int i = 1; i < input.size(); i += 2) {
            if (i + 1 >= input.size())
                break;
            String str = input.get(i);
            boolean flag = input.get(i).isEmpty();
            if (input.get(i).isEmpty() && i + 1 < input.size()) {
                conacyt.addInstitute(new Institute(input.get(i + 1)));
                institute = conacyt.lastInstitution();
                i += 2;
            }
            institute.addAuthor(new Author(input.get(i), input.get(i + 1)));
        }
    }
}
