package AuxTables;

public class ArticuleAuthor {
    private String articule;
    private String author;

    public ArticuleAuthor(String articule, String author) {
        this.articule = articule;
        this.author = author;
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString(){
        return articule + " " + author;
    }
}
