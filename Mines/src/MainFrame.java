import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private Minesweeper table;
    private JPanel pOpts;
    private JTextField txtRows;
    private JComboBox cbLevel;
    private JButton btnCreate;
    private JButton btnStart;
    private JLabel lbl;
    public MainFrame(){
        super("Minesweeper");
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setElements();
        this.setVisible(true);
    }
    private void setElements(){
        this.setLayout(null);
        pOpts = new JPanel();
        pOpts.setBounds(0, 0, 600, 50);
        pOpts.setLayout(new GridLayout(1, 5));
        lbl = new JLabel();
        txtRows = new JTextField();



        String s1[] = { "Facil", "Medio", "Dificil" };
        cbLevel = new JComboBox(s1);
        btnCreate = new JButton("Create");
        btnStart = new JButton("Start");

        btnStart.addActionListener(this);
        btnCreate.addActionListener(this);
        pOpts.add(lbl);
        pOpts.add(txtRows);
        pOpts.add(cbLevel);
        pOpts.add(btnCreate);
        pOpts.add(btnStart);
        this.add(pOpts);
    }

    private void createPanel(){
        int rows = 0;
        try{
            rows = Integer.parseInt(txtRows.getText());
        }catch(Exception e){
            lbl.setText("Debe ingresar solo numeros");
            return;
        }
        int bombs = (int)((cbLevel.getSelectedIndex() + 1) * (0.25) * Math.pow(rows, 2));
        // Tablero de juego
        table = new Minesweeper(this, rows, (int)bombs);
        table.showCells();
        table.setBounds(0, 50, 600, 320);
        this.add(table);
        revalidate();
        repaint();
    }

    public void endGame(){
        int points = ((int)Math.pow(table.getLen(), 2) - table.getBombs()) * (this.cbLevel.getSelectedIndex() + 1);
        JOptionPane.showMessageDialog(null, "Felicidades has terminado el juego. Tus puntos son: " + points);
        this.remove(table);
        table = null;
        revalidate();
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnCreate){
            createPanel();
        }else if(actionEvent.getSource() == btnStart){
            table.startGame();
        }
    }
}
