package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpAdmin {
    private JTextField textNAME;
    private JTextField textSURNAME;
    private JTextField textEMAIL;
    private JTextField textNICKNAME;
    private JButton INDIETROButton;
    private JButton REGISTRATIButton;
    private JPasswordField passwordField1;
    String name, surname, nickname, pswd, mail, msg;
    boolean adm = true;

    public SignUpAdmin(ProxyRdF prox) {
        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        REGISTRATIButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                name = textNAME.getText();
                surname = textSURNAME.getText();
                mail = textEMAIL.getText();
                pswd = passwordField1.getPassword().toString();
                nickname = textNICKNAME.getText();
                if (name.length() < 1) {
                    msg += "\n nome non valido";
                }

                if (surname.length() < 1) {
                    msg += "\n cognome non valido";
                }

                if (mail.length() < 1) {
                    msg += "\n email non valida";
                }

                if (pswd.length() < 1) {
                    msg += "\n password non valida";
                }

                if (nickname.length() < 1) {
                    msg += "\n nickname non valido";
                } else {
                }
                if (msg.equals("")) {
                    if (prox.RegistrBtn(true, name, surname, mail, pswd, nickname) > 0) {
                        Activation_Code actc = new Activation_Code(prox);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            }

        });
    }
}
