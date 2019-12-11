package GUI;

import RdF.ProxyRdF;
import com.sun.org.glassfish.external.statistics.Stats;
import sun.java2d.cmm.Profile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Home {
    private JButton profiloButton;
    private JButton statisticheButton;
    private JButton nuovaPartitaButton;
    private JButton guardaPartitaButton;
    private JButton cercaPartitaButton;
    private JButton gestioneFrasiButton;
    private JButton registraAmministratoreButton;

    public Home(ProxyRdF prox) {


   gestioneFrasiButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Manager_Sentence ms = new Manager_Sentence(prox);
        }
    });
    statisticheButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Statistics s = new Statistics(prox);
        }
    });
    profiloButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Profilo profile = new Profilo(prox);
        }
    });
    guardaPartitaButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked (MouseEvent e){
            try {
                List_Match lmo = new List_Match(false, prox);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    });

        nuovaPartitaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MatchName mn = new MatchName(prox);
            }
        });
                cercaPartitaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            List_Match lm = new List_Match(true, prox);
                        } catch (ClassNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                });

                registraAmministratoreButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //SignUpAdmin sua = new SignUpAdmin(prox);
            String temp=JOptionPane.showInputDialog("inserisci email dell'utente da rendere admin");
        }
    });


}
}
