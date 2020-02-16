package GUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyListener extends KeyAdapter {
    private final Graph m;
    MyKeyListener(Graph a){
        m = a;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_F){
            if(m.started){
                m.setStarted(false);
                if(m.end != null) {
                    m.end.toRender = null;
                    m.end = null;
                }
                if(m.start!= null){
                    m.start.toRender=null;
                    m.start = null;
                }
                m.info.setText("");
            }else {
                m.info.setText("Select start and end Algorithm.Node (Press S or E and select a Algorithm.Node)");
                m.setStarted(true);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_CONTROL)
            m.strg = true;
        if(e.getKeyCode()==KeyEvent.VK_C){
            m.cre=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_E){
            m.e=true;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            m.s = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_CONTROL)
            m.strg = false;
        if(e.getKeyCode()==KeyEvent.VK_C){
            m.cre=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_E){
            m.e=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            m.s = false;
        }
    }

}