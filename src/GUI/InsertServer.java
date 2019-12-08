package GUI;

import javax.swing.*;

import Mail.Mail_Helper;
import RdF.ServerRdF;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.mail.internet.InternetAddress;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertServer {

    private JFrame frmRdf;
    private JTextField textMail;
    private JTextField textPswd;
    private JButton btnConferma;
    private JButton btnExit;
    private boolean ok = true;
    private String msgMailErr = "";


    public InsertServer(ServerRdF server) {
        frmRdf = new JFrame();
        frmRdf.setTitle("RdF: Inserisci server");
        frmRdf.getContentPane().setBackground(Color.WHITE);
        frmRdf.setBackground(Color.BLUE);
        frmRdf.setBounds(100, 100, 640, 264);
        frmRdf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{56, 73, 149, 106, 116, 208, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmRdf.getContentPane().setLayout(gridBagLayout);

        JLabel lblMail = new JLabel("Inserisci mail server");
        GridBagConstraints gbc_lblMail = new GridBagConstraints();
        gbc_lblMail.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblMail.anchor = GridBagConstraints.SOUTH;
        gbc_lblMail.insets = new Insets(0, 0, 5, 5);
        gbc_lblMail.gridx = 1;
        gbc_lblMail.gridy = 1;
        frmRdf.getContentPane().add(lblMail, gbc_lblMail);

        textMail = new JTextField();
        GridBagConstraints gbc_textMail = new GridBagConstraints();
        gbc_textMail.gridwidth = 4;
        gbc_textMail.insets = new Insets(0, 0, 5, 5);
        gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
        gbc_textMail.gridx = 1;
        gbc_textMail.gridy = 2;
        frmRdf.getContentPane().add(textMail, gbc_textMail);
        textMail.setColumns(10);

        JLabel lblPswd = new JLabel("Inserisci password server");
        GridBagConstraints gbc_lblPswd = new GridBagConstraints();
        gbc_lblPswd.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblPswd.insets = new Insets(0, 0, 5, 5);
        gbc_lblPswd.gridx = 1;
        gbc_lblPswd.gridy = 4;
        frmRdf.getContentPane().add(lblPswd, gbc_lblPswd);

        textPswd = new JPasswordField();
        GridBagConstraints gbc_textPswd = new GridBagConstraints();
        gbc_textPswd.gridwidth = 4;
        gbc_textPswd.insets = new Insets(0, 0, 5, 5);
        gbc_textPswd.fill = GridBagConstraints.HORIZONTAL;
        gbc_textPswd.gridx = 1;
        gbc_textPswd.gridy = 5;
        frmRdf.getContentPane().add(textPswd, gbc_textPswd);
        textPswd.setColumns(10);

        btnConferma = new JButton("Conferma");
        btnConferma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mail = textMail.getText();
                String pswd = textPswd.getText();

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
                    frmRdf.setVisible(false);
					server.avvia();
                } else {
                    JOptionPane.showMessageDialog(null, msgMailErr, "Attenzione", JOptionPane.ERROR_MESSAGE);
                    textMail.setText("");
                    textPswd.setText("");
                    ok = true;
                    msgMailErr = "";
                }
            }
        });
        GridBagConstraints gbc_btnConferma = new GridBagConstraints();
        gbc_btnConferma.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnConferma.insets = new Insets(0, 0, 5, 5);
        gbc_btnConferma.gridx = 3;
        gbc_btnConferma.gridy = 7;
        frmRdf.getContentPane().add(btnConferma, gbc_btnConferma);

        btnExit = new JButton("Esci");
        GridBagConstraints gbc_btnExit = new GridBagConstraints();
        gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnExit.insets = new Insets(0, 0, 5, 5);
        gbc_btnExit.gridx = 4;
        gbc_btnExit.gridy = 7;
        frmRdf.getContentPane().add(btnExit, gbc_btnExit);

		System.out.println(frmRdf);
        frmRdf.show();
    }


}