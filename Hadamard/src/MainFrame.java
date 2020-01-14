import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JButton buttons[][];
    private int len;
    public MainFrame(int len){
        super("Hadamard");
        this.len = len;
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(len, len));
        createButtons();
        fillHadamard(buttons, 0, 0, len, true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createButtons(){
        buttons = new JButton[len][len];
        for (int i = 0; i < len; i++)
            for (int j = 0; j<len; j++){
                buttons[i][j] = new JButton();
                this.add(buttons[i][j]);
            }
    }
    private void fillHadamard (JButton[][] btns, int x, int y, int size, boolean flag)
    {
        if (size == 1)
            if(flag)
                btns[x][y].setBackground(Color.BLUE);
            else
                btns[x][y].setBackground(Color.RED);
        else
        {
            fillHadamard (btns, x, y, size/2, flag);
            fillHadamard (btns, x+size/2, y, size/2, flag);
            fillHadamard (btns, x, y+size/2, size/2, flag);
            fillHadamard (btns, x+size/2, y+size/2, size/2, !flag);
        }
    }
}
