import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    private JButton cells[][];
    public Table(int len){
        super();
        this.setLayout(new GridLayout(len, len));
        setButtons();
    }

    private void setButtons(){
        cells = new JButton[10][10];
        for (int i = 0; i< 10; i++)
            for (int j = 0; j< 10; j++){
                cells[i][j] = new JButton();
                this.add(cells[i][j]);
            }
    }
}
