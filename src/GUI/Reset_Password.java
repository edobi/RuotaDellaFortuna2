package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reset_Password {
    private JTextField textField1;
    private JButton INDIETROButton;
    private JButton CONFERMAButton;

    public Reset_Password(ProxyRdF prox) {
        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

    CONFERMAButton.addActionListener(new ActionListener() {
        /*-------------------*/
        public void actionPerformed(ActionEvent e) {
            prox.PswReset(textField1.getText());
        }
    });
}
}
