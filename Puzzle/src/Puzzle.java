import java.util.*;

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

        long startTime = System.currentTimeMillis();
        ArrayList<PuzzleState> lista = BFS(puzzleStart, puzzleFinish);
        long endTime = System.currentTimeMillis();
        long timeExec = (endTime - startTime);
        System.out.println("Time: " + timeExec + " ms");
        System.out.println("Movimientos: " + lista.size());
        for (PuzzleState ps: lista) {
            System.out.println(ps.prettyString());
        }
    }

    public PuzzleState BFS2(ArrayList<PuzzleState> frontera, ArrayList<PuzzleState> visitados, PuzzleState meta){
        if(frontera.isEmpty()){
            // No se encontro solucion
            return null;
        }
        PuzzleState first = frontera.get(0);
        frontera.remove(0);
        visitados.add(first);
        if(first.equals(meta)){
            // Si se encontro solucion;
            return first;
        }
        ArrayList<PuzzleState> nodos = first.nextMoves();
        for (PuzzleState ps: nodos)
            if(!visitados.contains(ps) && !frontera.contains(ps))
                frontera.add(ps);
        return BFS2(frontera,visitados, meta);
    }

    public ArrayList<PuzzleState> BFS(PuzzleState origin, PuzzleState destiny){
        ArrayList<PuzzleState> visitados = new ArrayList<PuzzleState>();
        ArrayList<PuzzleState> cola = new ArrayList<PuzzleState>();
        cola.add(origin);
        PuzzleState resp = null;
        while(!cola.isEmpty()){
            PuzzleState tmp = cola.get(0);
            cola.remove(0);
            visitados.add(tmp);
            if(tmp.equals(destiny)){
                resp = tmp;
                break;
            }
            ArrayList<PuzzleState> sons = tmp.nextMoves();
            for (PuzzleState ps: sons){
                if(!visitados.contains(ps)){
                    cola.add(ps);
                }
            }
            Collections.sort(cola);
        }
        ArrayList<PuzzleState> result = new ArrayList<PuzzleState>();
        while(resp != null){
            result.add(resp);
            resp = resp.getFather();
        }
        Collections.reverse(result);
        return result;
    }

}
