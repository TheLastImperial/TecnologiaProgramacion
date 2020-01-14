import java.util.ArrayList;
import java.util.Objects;

public class Institute {
    private String name;
    private ArrayList<Author> authors;
    public Institute(String name) {
        this.name = name;
        this.authors = new ArrayList<Author>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institute institute = (Institute) o;
        return Objects.equals(name, institute.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void addAuthor(Author author){
        authors.add(author);
    }
}
