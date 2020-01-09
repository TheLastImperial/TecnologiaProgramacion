import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JCell extends JButton implements ActionListener {
    private boolean bomb;
    private boolean hiden;
    private int i;
    private int j;
    private Minesweeper father;
    private int bombCounter;
    public JCell(Minesweeper father, int i, int j){
        super();
        bomb = false;
        hiden = false;
        this.i = i;
        this.j = j;
        this.father = father;
        this.bombCounter = 0;
        setForeground(Color.WHITE);
        addActionListener(this);
    }

    public boolean isBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isHiden() {
        return hiden;
    }

    public void setHiden(boolean hiden) {
        if(hiden){
            setBackground(null);
            setText("");
        } else{
            if(bomb)
                setBackground(Color.RED);
            else
                setBackground(Color.BLUE);

            if(bombCounter > 0)
                setText(bombCounter + "");
        }
        this.hiden = hiden;
    }

    public int getBombCounter() {
        return bombCounter;
    }

    public void plusBombCounter() {
        this.bombCounter++;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!hiden)
           return;
        if(bomb){
            setHiden(false);
            JOptionPane.showMessageDialog(null, "Encontraste una bomba. Fin del juego.");
        } else{
            father.showCell(i, j);
        }
    }
}
