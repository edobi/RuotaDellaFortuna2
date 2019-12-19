package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sign_Up_Player {

    String txtName, txtSurname, txtMail, txtPswd, txtNick;
    private ObjectInputStream ins;
    private ObjectOutputStream ous;
    private boolean adm = false;
    private String msg = "";
    private JTextField textFieldname;
    private JTextField textFieldnickname;
    private JTextField textFieldcognome;
    private JTextField textFieldemail;
    private JPasswordField passwordField;
    private JButton INDIETROButton;
    private JButton REGISTRATIButton;

    public Sign_Up_Player(ProxyRdF prox) {


        JButton INDIETROButton = new JButton("INDIETRO");
        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton REGISTRATIButton = new JButton("REGISTRATI");
        REGISTRATIButton.addActionListener(e -> {
                    txtName = textFieldname.getText();
                    txtSurname = textFieldcognome.getText();
                    txtMail = textFieldemail.getText();
                    txtPswd = passwordField.getText();
                    txtNick = textFieldnickname.getText();
                    if (txtName.length() < 1) {
                        msg += "\n nome non valido";
                    }
                    if (txtSurname.length() < 1) {
                        msg += "\n cognome non valido";
                    }
                    if (txtMail.length() < 1) {
                        msg += "\n email non valida";
                    }
                    if (txtPswd.length() < 1) {
                        msg += "\n password non valida";
                    }
                    if (txtNick.length() < 1) {
                        msg += "\n nickname non valido";
                    }
                    if (msg.isEmpty()) {
                        if (prox.RegistrBtn(false, txtName, txtSurname, txtMail, txtPswd, txtNick) > 0) {
                            //Activation_Code actc = new Activation_Code(prox);
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore registrazione");
                            msg = "";
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, msg);
                        msg = "";
                    }
                }
        );
    }
}