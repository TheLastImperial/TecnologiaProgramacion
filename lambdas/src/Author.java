import java.util.ArrayList;

public class Author {
    private String authorType;
    private String name;

    public Author(String authorType, String name) {
        this.authorType = authorType;
        this.name = name;
    }

    public String getAuthorType() {
        return authorType;
    }

    public void setAuthorType(String authorType) {
        this.authorType = authorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
