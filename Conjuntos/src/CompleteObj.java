import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class CompleteObj implements Serializable {
    private ArrayList<SimpleObj> simples;
    private String h;
    private SimpleObj otro;
    public CompleteObj(ArrayList<SimpleObj> simp, String h, SimpleObj otro){
        this.simples = simp;
        this.h = h;
        this.otro = otro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompleteObj that = (CompleteObj) o;
        return Objects.equals(simples, that.simples) &&
                Objects.equals(h, that.h) &&
                Objects.equals(otro, that.otro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simples, h, otro);
    }
    public String toString(){
        String result = "";
        for (int i =0; i<simples.size(); i++)
            result += simples.get(i).toString() + "-";
        result = "[" + result + "]";
        result += ", h: " + h;
        result += ", otr: " + otro;
        return "/" + result + "/";
    }
}
