import com.sun.source.tree.ArrayAccessTree;

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
        PuzzleState puzzleFinish = new PuzzleState(
                new ArrayList<String>(Arrays.asList(finish.trim().split(this.delimiter) )),
                null,
                null
        );

        PuzzleState puzzleStart = new PuzzleState(
                new ArrayList<String>(Arrays.asList(start.trim().split(this.delimiter) )),
                puzzleFinish,
                null
        );

        /*
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
        */

        BFS(puzzleStart, puzzleFinish);

//        ArrayList<PuzzleState> frontera = new ArrayList<PuzzleState>();
//        frontera.add(puzzleStart);
//        ArrayList<PuzzleState> visitados = new ArrayList<PuzzleState>();
//        visitados.add(puzzleStart);
//
//        PuzzleState end = BFS2(frontera, visitados, puzzleFinish);
//
//        if(end == null)
//            System.out.println("No se encontro solucion");
//        while(end != null){
//            System.out.println(end.toString());
//            end = end.getFather();
//        }
    }

    public PuzzleState BFS2(ArrayList<PuzzleState> frontera,ArrayList<PuzzleState> visitados, PuzzleState meta){
        if(frontera.isEmpty()){
            // No se encontro solucion
            return null;
        }
        PuzzleState first = frontera.get(0);
        frontera.remove(0);
        if(first.equals(meta)){
            // Si se encontro solucion;
            return first;
        }
        ArrayList<PuzzleState> nodos = first.nextMoves();
        for (PuzzleState ps: nodos)
            if(!visitados.contains(ps))
                frontera.add(ps);
        return BFS2(frontera,visitados, meta);
    }

    public void BFS(PuzzleState origin, PuzzleState destiny){
        ArrayList<PuzzleState> visitados = new ArrayList<PuzzleState>();
        // HashMap<String, PuzzleState> visitados = new HashMap<String, PuzzleState>();
        Queue<PuzzleState> cola = new LinkedList<PuzzleState>();
        cola.add(origin);
        visitados.add(origin);
        //visitados.put(origin.toString(), origin);
        PuzzleState resp = null;
        while(!cola.isEmpty()){
            PuzzleState tmp = cola.poll();
            if(tmp.equals(destiny)){
                resp = tmp;
                break;
            }
            ArrayList<PuzzleState> sons = tmp.nextMoves();
            for (PuzzleState ps: sons){
                if(!visitados.contains(ps.toString())){
                    cola.add(ps);
                    visitados.add(ps);
                }
            }
        }
        while(resp != null){
            System.out.println(resp);
            resp = resp.getFather();
        }
    }

    public boolean DFS(PuzzleState origin, PuzzleState destiny, ArrayList<PuzzleState> visited, Stack<PuzzleState> stack){
        visited.add(origin);
        boolean result = false;
        if(origin.equals(destiny)){
            return true;
        }
        ArrayList<PuzzleState> nodes = origin.nextMoves();
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

    private boolean AEstrella(PuzzleState init, PuzzleState goal){
        ArrayList<PuzzleState> abiertos = new ArrayList<PuzzleState>();
        ArrayList<PuzzleState> cerrados = new ArrayList<PuzzleState>();
        abiertos.add(init);
        while(true){
            Collections.sort(abiertos);
            if(abiertos.isEmpty()) // No se encontro respuesta.
                break;
            PuzzleState current = abiertos.get(0);
            abiertos.remove(0);
            cerrados.add(current);
            if(current.equals(goal))
                break;

            ArrayList<PuzzleState> tempNexts = current.nextMoves();
            for (PuzzleState ps: tempNexts){
                if(abiertos.contains(ps)){
                    // Recalcular el valor de ps

                }else if(cerrados.contains(ps)){
                    abiertos.add(ps);
                }
            }
        }
        return true;
    }

    private PuzzleState getPS(ArrayList<PuzzleState> puzzles, PuzzleState search){
        for (PuzzleState ps: puzzles) {
            if(ps.equals(search)){
                return ps;
            }
        }
        return null;
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
