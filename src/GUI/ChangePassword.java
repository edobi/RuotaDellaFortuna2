package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword {


    private JTextField textnuovapass;
    private JTextField textconfermapass;
    private JButton indietroButton;
    private JButton confermaButton;


    public ChangePassword(ProxyRdF prox) {

        JButton indietroButton = new JButton("INDIETRO");
        indietroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton confermaButton = new JButton("CONFERMA");
        confermaButton.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent e){
            if ((textnuovapass.getText().length() > 1) && textnuovapass.getText().equals(textconfermapass.getText())) {
                prox.PswChange(textconfermapass.getText(), prox.getemail());

            }
        }
    });
}
}