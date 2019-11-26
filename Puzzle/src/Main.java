import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String []args){
        String path = FileHelper.getInstance().readPathFromUser("input");
        ArrayList<String> lines = FileHelper.getInstance().getLines(path);

        System.out.println(lines);
        int nGames = Integer.parseInt(lines.get(0));
        String delimiter = lines.get(1);
        int size = Integer.parseInt(lines.get(2));
        Puzzle puzzle = new Puzzle(delimiter, size);
        lines.remove(0);
        lines.remove(0);
        lines.remove(0);
        puzzle.play(lines.get(1), lines.get(0));

        /*
        for (int i = 0; i < nGames; i++){
            puzzle.play(lines.get(0), lines.get(1));
            lines.remove(0);
            lines.remove(1);
        }
        */
    }
}
