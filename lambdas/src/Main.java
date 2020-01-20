import java.util.ArrayList;
import SegundoIntento.*;

public class Main {
    private static Conacyt conacyt = new Conacyt();
    private static DB database = new DB();
    public static void main(String [] args){
        primerIntento();
    }

    private static void segundoIntento(){
        String path = FileHelper.readPathFromUser("autores.txt");
        ArrayList<String> lines = FileHelper.getLines(path);
        createAuthors2(lines);
        path = FileHelper.readPathFromUser("articulos.txt");
        lines = FileHelper.getLines(path);
        createArticules2(lines);
        database.articulesByAuthor();
        database.iDontUnderstand();
        database.authorsByArticule();
        database.articulesWithStProfInv();
        database.articulesJustWithEst();
        database.articulesJustWithProfInv();
        database.articulesByType();
        database.articulesByInstitute();
        database.articulesByYear();
        System.out.println("Hola mundo");
    }

    private static void primerIntento(){
        String path = FileHelper.readPathFromUser("autores.txt");
        ArrayList<String> lines = FileHelper.getLines(path);
        createAuthors(lines);
        path = FileHelper.readPathFromUser("articulos.txt");
        lines = FileHelper.getLines(path);
        createArticules(lines);
        conacyt.articulesByAuthor();
        conacyt.authorsByArticule();
        conacyt.articulesWithStProfInv();
        conacyt.articulesJustWithEst();
        conacyt.articulesJustWithProfInv();
        conacyt.articulesByType();
        conacyt.articulesByInstitute();
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

    public static void createAuthors2(ArrayList<String> input) {
        String inst = input.get(0);
        for (int i = 1; i < input.size(); i +=2) {
            if(input.get(i).isBlank()){
                if((i+ 1) >= input.size())
                    return;
                inst = input.get(i + 1);
                continue;
            }
            Table tab = database.findByAuthor(input.get(i + 1), inst);
            if(tab != null)
                continue;
            tab = new Table();
            tab.instituteName = inst;
            tab.authorType = input.get( i );
            tab.authorName = input.get(i + 1);
            database.getTable().add(tab);
        }
    }

    public static void createArticules2(ArrayList<String> input){
        int nLines = Integer.parseInt(input.get(0));
        input.remove(0);
        input.remove(0);
        Articule articule;
        for (int i = 0; i < input.size(); i++){
            String artType = input.get(i);
            if(artType.isBlank())
                continue;
            Table tab = new Table();
            tab.articuleType = artType;
            tab.publicationDate = input.get(i + 1);
            tab.articuleTitle = input.get(i + 2);

            i+=3;
            String autName = input.get(i);
            do{
                Table aux = database.findArticule(tab.articuleTitle, autName);

                // El autor ya tiene ese articulo asignado.
                if(aux != null){
                    i++;
                    if(i >= input.size())
                        break;
                    autName = input.get(i);
                    continue;
                }
                aux = database.findByAuthor(autName);
                if(aux != null){
                    if(aux.articuleTitle == null){
                        aux.articuleType = tab.articuleType;
                        aux.publicationDate = tab.publicationDate;
                        aux.articuleTitle = tab.articuleTitle;

                    }else{
                        aux = aux.clone();
                        aux.articuleType = tab.articuleType;
                        aux.publicationDate = tab.publicationDate;
                        aux.articuleTitle = tab.articuleTitle;
                        database.getTable().add(aux);
                    }
                }
                i++;
                if(i >= input.size())
                    break;
                autName = input.get(i);
            }while(!autName.isBlank());
        }
    }
}
