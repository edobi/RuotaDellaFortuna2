package GUI;

import javax.swing.*;
import java.awt.GridBagLayout;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import RdF.ProxyRdF;
import RdF.Registration;

import java.awt.Dimension;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class SignUpPlayer {

    private JFrame frmRegistration;
    private JTextField name;
    private JTextField surname;
    private JTextField nickname;
    private JLabel lblEmail;
    private JTextField textMail;
    private JLabel lblPassword;
    private JPasswordField password;
    private JButton btnRegistration;
    private JButton btnBack;
    private JLabel lblInserisciIDati;
    String txtName, txtSurname, txtMail, txtPswd, txtNick;
    private ObjectInputStream ins;
    private ObjectOutputStream ous;
    private boolean adm = false;
    private String msg = "";

    public SignUpPlayer(ProxyRdF prox) {
        //Registration reg = new Registration();
        frmRegistration = new JFrame();
        frmRegistration.setTitle("Rdf: Registrazione");
        frmRegistration.getContentPane().setBackground(Color.WHITE);
        frmRegistration.setBackground(Color.BLUE);
        frmRegistration.setBounds(100, 100, 670, 383);
        frmRegistration.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 78, 0, 0, 0, 190, 0, 0, 0, 0, 60, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 36, 20, 20, 20, 20, 0, 20, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        frmRegistration.getContentPane().setLayout(gridBagLayout);

        lblInserisciIDati = new JLabel("Inserisci i dati per completare la registrazione al gioco");
        GridBagConstraints gbc_lblInserisciIDati = new GridBagConstraints();
        gbc_lblInserisciIDati.gridwidth = 5;
        gbc_lblInserisciIDati.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblInserisciIDati.insets = new Insets(0, 0, 5, 5);
        gbc_lblInserisciIDati.gridx = 3;
        gbc_lblInserisciIDati.gridy = 1;
        frmRegistration.getContentPane().add(lblInserisciIDati, gbc_lblInserisciIDati);
        // ------------------------------------------------------
        JLabel lblName = new JLabel("Nome");
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.insets = new Insets(0, 0, 5, 5);
        gbc_lblName.gridx = 1;
        gbc_lblName.gridy = 3;
        frmRegistration.getContentPane().add(lblName, gbc_lblName);

//------------------------------------------------------		
        name = new JTextField();
        name.setSize(new Dimension(108, 0));
        GridBagConstraints gbc_name = new GridBagConstraints();
        gbc_name.fill = GridBagConstraints.HORIZONTAL;
        gbc_name.gridwidth = 6;
        gbc_name.insets = new Insets(0, 0, 5, 5);
        gbc_name.gridx = 4;
        gbc_name.gridy = 3;
        frmRegistration.getContentPane().add(name, gbc_name);
        name.setColumns(10);
        //txtName= name.getText();
//------------------------------------------------------				
        JLabel lblSurname = new JLabel("Cognome");
        GridBagConstraints gbc_lblSurname = new GridBagConstraints();
        gbc_lblSurname.anchor = GridBagConstraints.EAST;
        gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
        gbc_lblSurname.gridx = 1;
        gbc_lblSurname.gridy = 4;
        frmRegistration.getContentPane().add(lblSurname, gbc_lblSurname);

        // ------------------------------------------------------
        surname = new JTextField();
        GridBagConstraints gbc_Surname = new GridBagConstraints();
        gbc_Surname.fill = GridBagConstraints.HORIZONTAL;
        gbc_Surname.gridwidth = 6;
        gbc_Surname.anchor = GridBagConstraints.NORTH;
        gbc_Surname.insets = new Insets(0, 0, 5, 5);
        gbc_Surname.gridx = 4;
        gbc_Surname.gridy = 4;
        frmRegistration.getContentPane().add(surname, gbc_Surname);
        surname.setColumns(10);
        //txtSurname= surname.getText();
        // ------------------------------------------------------
        JLabel lblNickname = new JLabel("Nickname");
        GridBagConstraints gbc_lblNickname = new GridBagConstraints();
        gbc_lblNickname.anchor = GridBagConstraints.EAST;
        gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickname.gridx = 1;
        gbc_lblNickname.gridy = 5;
        frmRegistration.getContentPane().add(lblNickname, gbc_lblNickname);

//------------------------------------------------------		
        nickname = new JTextField();
        GridBagConstraints gbc_nickname = new GridBagConstraints();
        gbc_nickname.fill = GridBagConstraints.HORIZONTAL;
        gbc_nickname.gridwidth = 6;
        gbc_nickname.anchor = GridBagConstraints.NORTH;
        gbc_nickname.insets = new Insets(0, 0, 5, 5);
        gbc_nickname.gridx = 4;
        gbc_nickname.gridy = 5;
        frmRegistration.getContentPane().add(nickname, gbc_nickname);
        nickname.setColumns(10);
        //txtNick= nickname.getText();
        // ------------------------------------------------------
        lblEmail = new JLabel("Email");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.anchor = GridBagConstraints.EAST;
        gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail.gridx = 1;
        gbc_lblEmail.gridy = 6;
        frmRegistration.getContentPane().add(lblEmail, gbc_lblEmail);

        textMail = new JTextField();
        GridBagConstraints gbc_textMail = new GridBagConstraints();
        gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
        gbc_textMail.gridwidth = 6;
        gbc_textMail.anchor = GridBagConstraints.NORTH;
        gbc_textMail.insets = new Insets(0, 0, 5, 5);
        gbc_textMail.gridx = 4;
        gbc_textMail.gridy = 6;
        frmRegistration.getContentPane().add(textMail, gbc_textMail);
        textMail.setColumns(10);
//------------------------------------------------------------------
        lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 7;
        frmRegistration.getContentPane().add(lblPassword, gbc_lblPassword);

        password = new JPasswordField();
        GridBagConstraints gbc_txtPassword = new GridBagConstraints();
        gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPassword.gridwidth = 6;
        gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
        gbc_txtPassword.anchor = GridBagConstraints.NORTH;
        gbc_txtPassword.gridx = 4;
        gbc_txtPassword.gridy = 7;
        frmRegistration.getContentPane().add(password, gbc_txtPassword);
        password.setColumns(10);
//----------------------------------------------------------------------		
        btnBack = new JButton("Indietro");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmRegistration.setVisible(false);
            }
        });
        GridBagConstraints gbc_btnBack = new GridBagConstraints();
        gbc_btnBack.anchor = GridBagConstraints.EAST;
        gbc_btnBack.insets = new Insets(0, 0, 5, 5);
        gbc_btnBack.gridx = 5;
        gbc_btnBack.gridy = 9;
        frmRegistration.getContentPane().add(btnBack, gbc_btnBack);
        /*-------------------*/
        btnRegistration = new JButton("Registrati");
        btnRegistration.addActionListener(e -> {
                    txtName = name.getText();
                    txtSurname = surname.getText();
                    txtMail = textMail.getText();
                    txtPswd = password.getText();
                    txtNick = nickname.getText();
                    if (txtName.length() < 1) {
                        msg += "\n nome non valido";
                    }
                    //****************************
                    if (txtSurname.length() < 1) {
                        msg += "\n cognome non valido";
                    }
                    //****************************
                    if (txtMail.length() < 1) {
                        msg += "\n email non valida";
                    }
                    //****************************
                    if (txtPswd.length() < 1) {
                        msg += "\n password non valida";
                    }
                    //****************************
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
        GridBagConstraints gbc_btnRegistration = new GridBagConstraints();
        gbc_btnRegistration.insets = new Insets(0, 0, 5, 5);
        gbc_btnRegistration.gridx = 7;
        gbc_btnRegistration.gridy = 9;
        frmRegistration.getContentPane().add(btnRegistration, gbc_btnRegistration);

        frmRegistration.show();
    }

}