package src.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import src.gui.GUI;

public class AlgosKeyListener implements KeyListener {
    public static boolean e;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_E) {
            AlgosKeyListener.e = true;
            GUI.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_E){
            AlgosKeyListener.e = false;
            GUI.repaint();
        }
    }
}
