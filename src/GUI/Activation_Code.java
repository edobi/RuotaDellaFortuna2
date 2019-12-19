package GUI;

import RdF.ProxyRdF;

import javax.swing.*;

public class Activation_Code {
    private JLabel JLABELCODE;
    private JTextField textCODE;
    private JButton CONFERMAButton;

    public Activation_Code(ProxyRdF prox) {
        JButton CONFERMAButton = new JButton("CONFERMA");
        CONFERMAButton.addActionListener(e -> prox.ActivCode(textCODE.getText()));

    }
}
