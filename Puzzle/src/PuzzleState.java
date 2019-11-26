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
        if(j-1 >= 0){
            aux = state.get(i + (j-1));
            newState = (ArrayList<String>) this.state.clone();
            newState.set(i + (j-1), "0");
            newState.set(i + j, aux);
            result.add(new PuzzleState(newState));
        }else if()

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleState that = (PuzzleState) o;
        return that.equals(this.toString());
    }
}
