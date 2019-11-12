import java.util.Objects;

public class Par<T, S> {
    private T tType;
    private S sType;
    public Par(T inputT, S inputS){
        this.tType = inputT;
        this.sType = inputS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Par<?, ?> par = (Par<?, ?>) o;
        return Objects.equals(tType, par.tType) &&
                Objects.equals(sType, par.sType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tType, sType);
    }

    @Override
    public String toString() {
        return "(" + tType + "," + sType +  ")";
    }
}
