import java.io.Serializable;
import java.util.Objects;

public class SimpleObj implements Serializable {
    private String name;
    private int age;

    public SimpleObj(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return "(" + name + "," + age + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleObj simpleObj = (SimpleObj) o;
        return age == simpleObj.age &&
                Objects.equals(name, simpleObj.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
