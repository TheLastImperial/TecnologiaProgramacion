import java.lang.reflect.Array;
import java.time.temporal.TemporalField;
import java.util.*;
import java.time.LocalDate;

public class Puzzle {
    private String delimiter;
    private int size;
    public Puzzle(String delimiter, int size){
        this.delimiter = delimiter;
        this.size = size;
    }
    public void play(String start, String finish){
        PuzzleState puzzleStart = new PuzzleState(
                new ArrayList<String>(Arrays.asList(start.trim().split(this.delimiter) ))
        );
        PuzzleState puzzleFinish = new PuzzleState(
                new ArrayList<String>(Arrays.asList(finish.trim().split(this.delimiter) ))
        );

        Stack<PuzzleState> stack = new Stack<PuzzleState>();
        // stack.push(puzzleStart);
        long startTime = System.currentTimeMillis();
        boolean isAWay = DFS(puzzleStart, puzzleFinish, new ArrayList<PuzzleState>(), stack);
        long endTime = System.currentTimeMillis();
        int movimientos = stack.size();
        String result = stackToString(isAWay, puzzleStart, puzzleFinish, stack);

        long timeExec = (endTime - startTime)/1000000;

        if(isAWay)
            System.out.println("Se encontro resultado");
        else
            System.out.println("No se encontro resultado");
        System.out.println(result);
        System.out.println("Movimientos: " + movimientos);
        System.out.println("Time: " + timeExec + " ms");
    }

    public boolean DFS(PuzzleState origin, PuzzleState destiny, ArrayList<PuzzleState> visited, Stack<PuzzleState> stack){
        visited.add(origin);
        boolean result = false;
        if(origin.equals(destiny)){
            return true;
        }
        System.out.println(origin);
        ArrayList<PuzzleState> nodes = origin.nextMoves(this.size);
        if(nodes.isEmpty())
            return false;
        for (PuzzleState node: nodes) {
            if(!visited.contains(node)){
                result = DFS(node, destiny, visited, stack);
                if(result){
                    stack.push(node);
                    return true;
                }
            }
        }
        return result;
    }


    private String stackToString(boolean found, PuzzleState origin, PuzzleState destiny, Stack<PuzzleState> stack){
        String result = origin.toString() + "\n";
        while(found && !stack.isEmpty()){
            result += stack.pop().toString() + "\n";
        }
        return result;
    }

    public boolean isValid(String str){
        return str.split(this.delimiter).length != (this.size * this.size);
    }
}
