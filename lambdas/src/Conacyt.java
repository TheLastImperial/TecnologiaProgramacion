import java.util.ArrayList;

public class Conacyt {
    private ArrayList<Institute> institutes;
    private ArrayList<Author> authors;
    private ArrayList<Articule> articules;

    public Conacyt() {
        this.institutes = new ArrayList<Institute>();
        this.authors = new ArrayList<Author>();
        this.articules = new ArrayList<Articule>();
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }

    public void addInstitute(Institute institute) {
        institutes.add(institute);
    }

    public Institute lastInstitution(){
        if(institutes.isEmpty())
            return null;
        return institutes.get(institutes.size() - 1);
    }

    public void addArticule(Articule articule){
        this.articules.add(articule);
    }

    public Articule lastArticule(){
        if(this.articules.isEmpty())
            return null;
        return this.articules.get(this.articules.size() - 1);
    }

    public String toString(){
        return "Soy conacyt";
    }
}
