import java.util.ArrayList;

public class Main {
    private static Conacyt conacyt = new Conacyt();
    public static void main(String [] args){
        String path = FileHelper.readPathFromUser("autores.txt");
        ArrayList<String> lines = FileHelper.getLines(path);
        createAuthors(lines);
        path = FileHelper.readPathFromUser("articulos.txt");
        lines = FileHelper.getLines(path);
        createArticules(lines);
        System.out.println(conacyt);
        System.out.println("Hola mundo");
    }

    public static void createAuthors(ArrayList<String> input) {
        conacyt.addInstitute(new Institute(input.get(0)));
        Institute institute = conacyt.lastInstitution();
        Author authorAux;
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
            authorAux = new Author(input.get(i), input.get(i + 1));
            conacyt.addAuthor(authorAux);
            institute.addAuthor(authorAux);
        }
    }

    public static void createArticules(ArrayList<String> input){
        int nLines = Integer.parseInt(input.get(0));
        input.remove(0);
        input.remove(0);
        Articule articule;
        for (int i = 0; i < input.size(); i++){
            String str = input.get(i);
            if(str.isBlank())
                continue;
            conacyt.addArticule(new Articule(input.get(i), input.get(i + 1), input.get(i + 2)));
            articule = conacyt.lastArticule();
            i+=3;
            String line = input.get(i);
            do{
                Author author = conacyt.findAuthor(line);
                articule.addAuthor(author);
                i++;
                if(i >= input.size())
                    break;
                line = input.get(i);
            }while(!line.isBlank());
        }
    }
}
