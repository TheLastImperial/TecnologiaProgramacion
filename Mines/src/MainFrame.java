import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Table table;

    public MainFrame(){
        super("Hadamard");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        table = new Table(10);
        table.setBounds(0, 50, 600, 320);
        this.add(table);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
