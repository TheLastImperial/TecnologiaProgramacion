import java.util.ArrayList;
import java.util.Objects;

public class PuzzleState {
    private ArrayList<String> state;

    public PuzzleState(ArrayList<String> input) {
        this.state = input;
    }

    public String toString(){
        return String.join("", state);
    }

    public String toString(String delimiter){
        return String.join(delimiter, state);
    }

    public ArrayList<PuzzleState> nextMoves(int size){
        ArrayList<PuzzleState> result = new ArrayList<PuzzleState>();
        int indexZero = state.indexOf("0");

        int i = indexZero/size;
        int j = indexZero%size;

        String aux = "";
        ArrayList<String> newState;

        if(j - 1 >= 0)
            result.add(newState(i, j, i, (j-1), size ));
        if(( j + 1) < size)
            result.add(newState(i, j, i, (j+1), size ));
        if((i + 1) < size)
            result.add(newState(i, j, (i + 1), j, size ));
        if((i - 1) >= 0)
            result.add(newState(i, j, (i - 1), j, size ));
        return result;
    }

    private PuzzleState newState(int iZero, int jZero, int iNew, int jNew, int size){
        String aux = state.get((iNew * size) + jNew);
        ArrayList<String> newState = (ArrayList<String>) this.state.clone();
        newState.set((iNew * size) + jNew, "0");
        newState.set((iZero * size) + jZero, aux);
        return new PuzzleState(newState);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleState that = (PuzzleState) o;
        return that.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}
