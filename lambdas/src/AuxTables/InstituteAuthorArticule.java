package AuxTables;

import java.util.Objects;

public class InstituteAuthorArticule {
    private String institute;
    private String author;
    private String articule;
    public InstituteAuthorArticule(String author, String articule){
        this.author = author;
        this.articule = articule;
    }

    private InstituteAuthorArticule(String institute, String author, String articule){
        this.institute = institute;
        this.author = author;
        this.articule = articule;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArticule() {
        return articule;
    }

    public void setArticule(String articule) {
        this.articule = articule;
    }

    public InstituteAuthorArticule clone(){
        return new InstituteAuthorArticule(
                this.institute, this.author, this.articule
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstituteAuthorArticule that = (InstituteAuthorArticule) o;
        return Objects.equals(institute, that.institute) &&
                Objects.equals(author, that.author) &&
                Objects.equals(articule, that.articule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institute, author, articule);
    }

    @Override
    public String toString() {
        return "InstituteAuthorArticule{" +
                "institute='" + institute + '\'' +
                ", author='" + author + '\'' +
                ", articule='" + articule + '\'' +
                '}';
    }
}
