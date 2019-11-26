public class Puzzle {
    private String delimiter;
    private int size;
    public Puzzle(String delimiter, int size){
        this.delimiter = delimiter;
        this.size = size;
    }
    public void play(String start, String finish){

    }

    public boolean isValid(String str){
        return str.split(this.delimiter).length != (this.size * this.size);
    }
}
