import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorType, author.authorType) &&
                Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorType, name);
    }
}
