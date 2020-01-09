import javax.swing.*;
import java.awt.*;

public class JCell extends JButton {
    private boolean bomb;
    private boolean hiden;
    public JCell(){
        super();
        bomb = false;
        hiden = false;
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
        if(hiden)
            setBackground(null);
        else{
            if(bomb)
                setBackground(Color.RED);
            else
                setBackground(Color.BLUE);
        }
        this.hiden = hiden;
    }
}
