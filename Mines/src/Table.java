import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Table extends JPanel {
    private JCell cells[][];
    private int len;
    private int bombs;
    public Table(int len, int bombs){
        super();
        this.len = len;
        this.bombs = bombs;
        this.setLayout(new GridLayout(len, len));
        setButtons();
    }

    private void setButtons(){
        cells = new JCell[len][len];
        for (int i = 0; i< len; i++)
            for (int j = 0; j< len; j++){
                cells[i][j] = new JCell();
                this.add(cells[i][j]);
            }
        int currentBombs = this.bombs;
        Random rand = new Random();
        while(currentBombs > 0){
            int i, j;
            i = rand.nextInt(this.len);
            j = rand.nextInt(this.len);
            if(!cells[i][j].isBomb()){
                cells[i][j].setBomb(true);
                currentBombs--;
            }
        }
    }

    public void showCells(){
        for (int i = 0; i< len; i++)
            for (int j = 0; j< len; j++)
                cells[i][j].setHiden(false);
    }

    public void hideCells(){
        for (int i = 0; i< len; i++)
            for (int j = 0; j< len; j++)
                cells[i][j].setHiden(true);
    }
    public void startGame(){
        hideCells();
    }
}
