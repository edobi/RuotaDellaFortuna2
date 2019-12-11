package GUI;

import RdF.ServerRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpserAdmin {
    private String name, surname, nickname, pswd, mail;
    private String msg = "";
    private boolean adm = true;
    private boolean ok = true;
    private JTextField textFieldNAME;
    private JTextField textFieldNICKNAME;
    private JTextField textFieldSURNAME;
    private JTextField textFieldEMAIL;
    private JPasswordField passwordField;
    private JButton INDIETROButton;
    private JButton REGISTRATIButton;

    public SignUpserAdmin(ServerRdF serv) {

        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        REGISTRATIButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                name = textFieldNAME.getText();
                surname = textFieldSURNAME.getText();
                mail = textFieldEMAIL.getText();
                pswd = passwordField.getText();
                nickname = textFieldNICKNAME.getText();
                if (name.length() < 1) {
                    msg += "\n nome non valido";
                    ok = false;
                }

                if (surname.length() < 1) {
                    msg += "\n cognome non valido";
                    ok = false;
                }

                if (mail.length() < 1) {
                    msg += "\n email non valida";
                    ok = false;
                }

                if (pswd.length() < 1) {
                    msg += "\n password non valida";
                }

                if (nickname.length() < 1) {
                    msg += "\n nickname non valido";
                }
                System.out.println("signup: msg: " + msg + " length: " + msg.length());
                if (msg.isEmpty()) {
                    serv.Register(name, surname, mail, pswd, nickname);
                    if(serv.Adminex()==1) {
                        InsertServer is = new InsertServer(serv);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, msg);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }
            }

        });

    }
}
