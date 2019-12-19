package GUI;

import Mail.Mail_Helper;
import RdF.ServerRdF;

import javax.mail.internet.InternetAddress;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertServer {
    private JTextField textField1;
    private JFrame frmRdf;
    private JPasswordField passwordField1;
    private JButton ACCEDIButton;
    private JButton ESCIButton;
    private boolean ok = true;
    private String msgMailErr = "";

    public InsertServer(ServerRdF server) {

        JButton ACCEDIButton = new JButton("ACCEDI");
        ACCEDIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mail = textField1.getText();
                String pswd = passwordField1.getText();

                InternetAddress email;

                try {
                    email = new InternetAddress(mail);
                    email.validate();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    ok = false;
                    msgMailErr = msgMailErr + "- Mail non valida \n";
                }

                if (pswd.equals("")) {
                    ok = false;
                    msgMailErr = msgMailErr + "- Password non valida \n";
                }

                if (ok) {
                    Mail_Helper.setCredentials(mail, pswd);
                    JOptionPane.showMessageDialog(null, "Server connesso", "Server", JOptionPane.INFORMATION_MESSAGE);
                    server.avvia();
                } else {
                    JOptionPane.showMessageDialog(null, msgMailErr, "Attenzione", JOptionPane.ERROR_MESSAGE);
                    textField1.setText("");
                    passwordField1.setText("");
                    ok = true;
                    msgMailErr = "";
                }
            }
        });
        ESCIButton = new JButton("ESCI");
        System.out.println(frmRdf);
        frmRdf.show();
    }
}