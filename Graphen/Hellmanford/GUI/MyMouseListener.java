package GUI;

import Algorithm.Edge;
import Algorithm.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    final Graph m;
    @org.jetbrains.annotations.Nullable
    Node selSta,selEnd;
    MyMouseListener(Graph a){
        m = a;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

        JComponent jc = (JComponent)e.getSource();
        jc.requestFocus();
        if(!m.started) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (m.strg) {
                    for (int i = 0; i < m.nodes.size(); i++) {
                        if (m.nodes.get(i).clicked(e.getX(), e.getY())) {
                            m.moveing = m.nodes.get(i);
                            m.moving = true;
                            return;
                        }
                    }
                } else if (m.cre) {
                    Node n = new Node(e.getX(), e.getY(), m);

                    for (int i = 0; i < m.nodes.size(); i++) {
                        n.createEdge(m.nodes.get(i));
                    }
                    m.nodes.add(n);
                    return;
                } else if (m.e) {
                    for (int i = 0; i < m.nodes.size(); i++) {
                        if (m.nodes.get(i).clicked(e.getX(), e.getY())) {
                            if (selSta != null) {

                                selSta.createEdge(m.nodes.get(i));
                                selSta.toRender = null;
                                selSta = null;
                                return;
                            } else {
                                selSta = m.nodes.get(i);
                                selSta.toRender = Color.green;
                            }
                            return;
                        }
                    }
                    for (int i = 0; i < m.edges.size(); i++) {
                        if (m.edges.get(i).isBetween(e.getX(), e.getY()) < 90) {
                            m.edges.get(i).changeDirection();
                            return;
                        }
                    }


                    if (selSta != null) {
                        selSta.toRender = null;
                        selSta = null;
                        return;
                    }
                } else {
                    for (int i = 0; i < m.edges.size(); i++) {
                        if (m.edges.get(i).isBetween(e.getX(), e.getY()) < 90) {
                            m.edges.get(i).capacity++;
                            return;
                        }
                    }
                }


            }
            if (e.getButton() == MouseEvent.BUTTON3) {

                for (int i = 0; i < m.nodes.size(); i++) {
                    if (m.nodes.get(i).clicked(e.getX(), e.getY())) {
                        m.nodes.get(i).clearEdges();
                        for(int j = 0;j<m.edges.size();j++){
                            Edge ed = m.edges.get(j);
                            if(ed.end.equals(m.nodes.get(i))){
                                ed.remove();
                                j--;
                            }
                        }
                        m.nodes.remove(i);
                        return;
                    }
                }
                for (int i = 0; i < m.edges.size(); i++) {
                    if (m.edges.get(i).isBetween(e.getX(), e.getY()) < 90) {
                        if (m.strg) {
                            m.edges.get(i).remove();
                        } else if (m.edges.get(i).capacity == 0) {
                            int an = JOptionPane.showConfirmDialog(null,
                                    "Sicher, dass du diese Kante löschen möchtest??");
                            if (an == JOptionPane.YES_OPTION)
                                m.edges.get(i).remove();
                        } else {
                            m.edges.get(i).capacity--;
                        }
                        return;
                    }
                }

            }
        }
        else{
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (m.strg && !m.aStar.isRunning()) {
                    for (int i = 0; i < m.nodes.size(); i++) {
                        if (m.nodes.get(i).clicked(e.getX(), e.getY())) {
                            m.moveing = m.nodes.get(i);
                            m.moving = true;
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(m.moving){
            m.moving = false;
            m.moveing = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


