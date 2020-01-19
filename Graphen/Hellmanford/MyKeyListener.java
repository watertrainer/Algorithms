import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {
    private final Hellmanford m;
    MyKeyListener(Hellmanford a){
        m = a;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_CONTROL)
            m.strg = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_CONTROL)
            m.strg = false;
        if(e.getKeyCode()==KeyEvent.VK_ALT){
            m.toggleNodeMode();
            e.consume();
        }
    }

}