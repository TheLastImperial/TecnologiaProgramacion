import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Minesweeper extends JPanel {
    private JCell cells[][];
    private int len;
    private int bombs;
    private int discovered;
    private MainFrame father;
    public Minesweeper(MainFrame father, int len, int bombs){
        super();
        this.len = len;
        this.bombs = bombs;
        this.discovered = 0;
        this.setLayout(new GridLayout(len, len));
        this.father = father;
        setButtons();
    }

    private void setButtons(){
        cells = new JCell[len][len];
        for (int i = 0; i< len; i++)
            for (int j = 0; j< len; j++){
                cells[i][j] = new JCell(this, i, j);
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
        setBombsCount();
    }

    public void showCell(int i, int j){
        if((i < 0 || i > (this.len - 1)) || (j < 0 || j > (this.len - 1)))
            return;
        if(!cells[i][j].isHiden())
            return;
        cells[i][j].setHiden(false);
        this.discovered++;
        if(this.discovered + this.bombs == Math.pow(this.len, 2)){
            this.father.endGame();
            return;
        }
        if(cells[i][j].getBombCounter() != 0)
            return;
        showCell(i, j + 1);
        showCell(i, j - 1);

        showCell(i + 1, j);
        showCell(i - 1, j);

        showCell(i + 1, j + 1);
        showCell(i - 1, j - 1);

        showCell(i + 1, j - 1);
        showCell(i - 1, j + 1);
    }

    public void setBombsCount(){

        for (int i = 0; i < this.len; i++)
            for (int j = 0; j < this.len; j++){
                if(cells[i][j].isBomb())
                    continue;
                if(j < (this.len - 1) && cells[i][j + 1].isBomb())
                    cells[i][j].plusBombCounter();

                if(j > 0 && cells[i][j - 1].isBomb())
                    cells[i][j].plusBombCounter();

                if(i < (this.len - 1) && cells[i + 1][j].isBomb())
                    cells[i][j].plusBombCounter();

                if(i > 0 && cells[i - 1][j].isBomb())
                    cells[i][j].plusBombCounter();

                if(i < (this.len - 1) && j < (this.len - 1) && cells[i + 1][j + 1].isBomb())
                    cells[i][j].plusBombCounter();

                if( i> 0 && j > 0 && cells[i - 1][j - 1].isBomb())
                    cells[i][j].plusBombCounter();

                if(i < this.len - 1 && j > 0 && cells[i + 1][j - 1].isBomb())
                    cells[i][j].plusBombCounter();

                if(i > 0 && j < (this.len - 1) && cells[i - 1][j + 1].isBomb())
                    cells[i][j].plusBombCounter();
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

    public int getLen() {
        return len;
    }

    public int getBombs() {
        return bombs;
    }
}
