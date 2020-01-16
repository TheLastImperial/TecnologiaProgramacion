package SegundoIntento;

public class Table {
    public String articuleType;
    public String publicationDate;
    public String articuleTitle;
    public String authorType;
    public String authorName;
    public String instituteName;

    public Table clone(){
        Table result = new Table();

        result.authorType = this.articuleType;
        result.publicationDate = this.publicationDate;
        result.articuleTitle = this.articuleTitle;
        result.authorType = this.authorType;
        result.authorName = this.authorName;
        result.instituteName = this.instituteName;

        return result;
    }

    public String getArticuleType() {
        return articuleType;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getArticuleTitle() {
        return articuleTitle;
    }

    public String getAuthorType() {
        return authorType;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getInstituteName() {
        return instituteName;
    }
}
