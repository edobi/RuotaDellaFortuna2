package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class List_Match {
    private JButton indietroButton;
    private JList list1;

    public List_Match(boolean bool, ProxyRdF prox) throws ClassNotFoundException, IOException {
        String[] labels = new String[0];
        try {
            labels = prox.View(bool);//prendo dal server la lista dei match disponibili
            //listArea = new JList(labels.toArray(new String[labels.size()]));
            list1 = new JList(labels);
            list1.setVisibleRowCount(10);
            list1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String selected;
                    //dati match preso .getSelectedValue();
                    if (bool == true) {
                        selected = (String) list1.getSelectedValue();
                        try {
                            prox.JoinGame(selected, bool);
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        Second_Match m = new Second_Match(prox);//inserire i dati della partita
                    } else {
                        selected = (String) list1.getSelectedValue();
                        try {
                            prox.JoinGame(selected, bool);
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        Obs o = new Obs(prox);//inserire i dati della partita
                    }
                }


            });
        } catch(Exception e) {e.printStackTrace();}

        indietroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}