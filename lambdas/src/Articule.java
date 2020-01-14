import java.util.ArrayList;

public class Articule {
    private String articuleType;
    private String publicationDate;
    private String title;
    private ArrayList<Author> authors;

    public Articule(String articuleType, String publicationDate, String title) {
        this.articuleType = articuleType;
        this.publicationDate = publicationDate;
        this.title = title;
    }

    public String getArticuleType() {
        return articuleType;
    }

    public void setArticuleType(String articuleType) {
        this.articuleType = articuleType;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
}
