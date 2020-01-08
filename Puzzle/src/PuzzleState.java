import java.util.ArrayList;
import java.util.Objects;

public class PuzzleState implements Comparable{
    private ArrayList<String> state;
    private PuzzleState father;
    private PuzzleState goal;
    private int g;
    private int h;
    private int size;

    public PuzzleState(ArrayList<String> input, PuzzleState goal, PuzzleState father){
        this.state = input;
        this.size = (int)Math.sqrt(this.state.size());
        this.father = father;
        this.goal = goal;
        this.h = 0;
        if(father == null){
            this.g = 0;
        }else{
            this.g = this.father.getG() + 1;
            setH();
        }
    }

    public PuzzleState getFather() {
        return father;
    }

    public void setFather(PuzzleState father) {
        this.father = father;
    }

    public ArrayList<String> getState() {
        return state;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }
    public int getF(){
        return this.g + this.h;
    }

    public void setH() {
        for (int i = 0; i < size; i++)
            if(!this.state.get(i).equals(this.goal.getState().get(i)))
                this.h ++;
    }

    public String toString(){
        return String.join("", state);
    }

    public String prettyString(){
        String result = "";
        for(int i = 0; i< state.size(); i++){
            result += state.get(i);
            if(((i + 1) %size) == 0)
                result += "\n";
            else
                result += ",";
        }
        return result;
    }

    public String toString(String delimiter){
        return String.join(delimiter, state);
    }

    public ArrayList<PuzzleState> nextMoves(){
        ArrayList<PuzzleState> result = new ArrayList<PuzzleState>();
        int indexZero = state.indexOf("0");

        int i = indexZero/size;
        int j = indexZero%size;

        String aux = "";
        ArrayList<String> newState;

        if(j - 1 >= 0)
            result.add(newState(i, j, i, (j-1) ));
        if(( j + 1) < size)
            result.add(newState(i, j, i, (j+1) ));
        if((i + 1) < size)
            result.add(newState(i, j, (i + 1), j ));
        if((i - 1) >= 0)
            result.add(newState(i, j, (i - 1), j ));
        return result;
    }

    private PuzzleState newState(int iZero, int jZero, int iNew, int jNew){
        String aux = state.get((iNew * size) + jNew);
        ArrayList<String> newState = (ArrayList<String>) this.state.clone();
        newState.set((iNew * size) + jNew, "0");
        newState.set((iZero * size) + jZero, aux);
        return new PuzzleState(newState, this.goal,this);
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

    public int compareTo(PuzzleState o) {
        int result = 0;
        if(o.getH() > this.getH())
            result = -1;
        if(o.getH() < this.getH())
            result = 1;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
