package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import RdF.Comands;
import RdF.ProxyRdF;

import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class SignUpAdmin {

    private JFrame frameRegistrationAdmin;
    private JTextField textname;
    private JTextField textsurname;
    private JTextField textnickname;
    private JLabel lblEmail;
    private JTextField textMail;
    private JLabel lblPassword;
    private JPasswordField textPassword;
    private JButton btnRegistration;
    private JButton btnBack;
    private JLabel lblInserisciIDati;
    String name, surname, nickname, pswd, mail, msg;
    boolean adm = true;

    public SignUpAdmin(ProxyRdF prox) {
        //Registration reg = new Registration();
        frameRegistrationAdmin = new JFrame();
        frameRegistrationAdmin.setTitle("Rdf: Registrazione Admin");
        frameRegistrationAdmin.getContentPane().setBackground(Color.WHITE);
        frameRegistrationAdmin.setBackground(Color.BLUE);
        frameRegistrationAdmin.setBounds(100, 100, 670, 383);
        frameRegistrationAdmin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 78, 0, 0, 0, 190, 0, 0, 0, 0, 60, 0};
        gridBagLayout.rowHeights = new int[]{41, 0, 36, 20, 20, 20, 20, 0, 20, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        frameRegistrationAdmin.getContentPane().setLayout(gridBagLayout);

        lblInserisciIDati = new JLabel("Inserisci i dati per completare la registrazione al gioco");
        GridBagConstraints gbc_lblInserisciIDati = new GridBagConstraints();
        gbc_lblInserisciIDati.gridwidth = 5;
        gbc_lblInserisciIDati.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblInserisciIDati.insets = new Insets(0, 0, 5, 5);
        gbc_lblInserisciIDati.gridx = 3;
        gbc_lblInserisciIDati.gridy = 1;
        frameRegistrationAdmin.getContentPane().add(lblInserisciIDati, gbc_lblInserisciIDati);

// ---------------------------NAME---------------------------
        JLabel lblName = new JLabel("Nome");
        GridBagConstraints gbc_lblName = new GridBagConstraints();
        gbc_lblName.anchor = GridBagConstraints.EAST;
        gbc_lblName.insets = new Insets(0, 0, 5, 5);
        gbc_lblName.gridx = 1;
        gbc_lblName.gridy = 3;
        frameRegistrationAdmin.getContentPane().add(lblName, gbc_lblName);

        textname = new JTextField();
        textname.setSize(new Dimension(108, 0));
        GridBagConstraints gbc_name = new GridBagConstraints();
        gbc_name.fill = GridBagConstraints.HORIZONTAL;
        gbc_name.gridwidth = 6;
        gbc_name.insets = new Insets(0, 0, 5, 5);
        gbc_name.gridx = 4;
        gbc_name.gridy = 3;
        frameRegistrationAdmin.getContentPane().add(textname, gbc_name);
        textname.setColumns(10);
        name = textname.getText();

//---------------------------SURNAME---------------------------				
        JLabel lblSurname = new JLabel("Cognome");
        GridBagConstraints gbc_lblSurname = new GridBagConstraints();
        gbc_lblSurname.anchor = GridBagConstraints.EAST;
        gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
        gbc_lblSurname.gridx = 1;
        gbc_lblSurname.gridy = 4;
        frameRegistrationAdmin.getContentPane().add(lblSurname, gbc_lblSurname);

        textsurname = new JTextField();
        GridBagConstraints gbc_Surname = new GridBagConstraints();
        gbc_Surname.fill = GridBagConstraints.HORIZONTAL;
        gbc_Surname.gridwidth = 6;
        gbc_Surname.anchor = GridBagConstraints.NORTH;
        gbc_Surname.insets = new Insets(0, 0, 5, 5);
        gbc_Surname.gridx = 4;
        gbc_Surname.gridy = 4;
        frameRegistrationAdmin.getContentPane().add(textsurname, gbc_Surname);
        textsurname.setColumns(10);
        surname = textsurname.getText();

// --------------------------NICKNAME----------------------------
        JLabel lblNickname = new JLabel("Nickname");
        GridBagConstraints gbc_lblNickname = new GridBagConstraints();
        gbc_lblNickname.anchor = GridBagConstraints.EAST;
        gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickname.gridx = 1;
        gbc_lblNickname.gridy = 5;
        frameRegistrationAdmin.getContentPane().add(lblNickname, gbc_lblNickname);

        textnickname = new JTextField();
        GridBagConstraints gbc_nickname = new GridBagConstraints();
        gbc_nickname.fill = GridBagConstraints.HORIZONTAL;
        gbc_nickname.gridwidth = 6;
        gbc_nickname.anchor = GridBagConstraints.NORTH;
        gbc_nickname.insets = new Insets(0, 0, 5, 5);
        gbc_nickname.gridx = 4;
        gbc_nickname.gridy = 5;
        frameRegistrationAdmin.getContentPane().add(textnickname, gbc_nickname);
        textnickname.setColumns(10);
        nickname = textnickname.getText();

// --------------------------EMAIL----------------------------
        lblEmail = new JLabel("Email");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.anchor = GridBagConstraints.EAST;
        gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail.gridx = 1;
        gbc_lblEmail.gridy = 6;
        frameRegistrationAdmin.getContentPane().add(lblEmail, gbc_lblEmail);

        textMail = new JTextField();
        GridBagConstraints gbc_textMail = new GridBagConstraints();
        gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
        gbc_textMail.gridwidth = 6;
        gbc_textMail.anchor = GridBagConstraints.NORTH;
        gbc_textMail.insets = new Insets(0, 0, 5, 5);
        gbc_textMail.gridx = 4;
        gbc_textMail.gridy = 6;
        frameRegistrationAdmin.getContentPane().add(textMail, gbc_textMail);
        textMail.setColumns(10);
        mail = textMail.getText();

// --------------------------PASSWORD----------------------------
        lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 7;
        frameRegistrationAdmin.getContentPane().add(lblPassword, gbc_lblPassword);

        textPassword = new JPasswordField();
        GridBagConstraints gbc_txtPassword = new GridBagConstraints();
        gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPassword.gridwidth = 6;
        gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
        gbc_txtPassword.anchor = GridBagConstraints.NORTH;
        gbc_txtPassword.gridx = 4;
        gbc_txtPassword.gridy = 7;
        frameRegistrationAdmin.getContentPane().add(textPassword, gbc_txtPassword);
        textPassword.setColumns(10);

// ---------------------------BACK---------------------------
        btnBack = new JButton("Indietro");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameRegistrationAdmin.setVisible(false);
            }
        });
        GridBagConstraints gbc_btnBack = new GridBagConstraints();
        gbc_btnBack.anchor = GridBagConstraints.EAST;
        gbc_btnBack.insets = new Insets(0, 0, 5, 5);
        gbc_btnBack.gridx = 5;
        gbc_btnBack.gridy = 9;
        frameRegistrationAdmin.getContentPane().add(btnBack, gbc_btnBack);

// --------------------------REGISTER----------------------------
        btnRegistration = new JButton("Registrati");
        btnRegistration.addActionListener(new ActionListener() {
            /*-------------------*/
            public void actionPerformed(ActionEvent e) {
                name = textname.getText();
                surname = textsurname.getText();
                mail = textMail.getText();
                pswd = textPassword.getPassword().toString();
                nickname = textnickname.getText();
                if (name.length() < 1) {
                    msg += "\n nome non valido";
                }
                //****************************
                if (surname.length() < 1) {
                    msg += "\n cognome non valido";
                }
                //****************************
                if (mail.length() < 1) {
                    msg += "\n email non valida";
                }
		     	/*else {
		     		if(!reg.validEmail(mail)) {
		     			msg+="\n email non valida";
		     		}
		     		else {
		     			if(reg.checkEmail(mail)) {
		 					msg+="\n email gi� in uso";
		 				}
		     		}
		     	}
		     	*/
                //****************************
                if (pswd.length() < 1) {
                    msg += "\n password non valida";
                }
                //****************************
                if (nickname.length() < 1) {
                    msg += "\n nickname non valido";
                } else {
		     		/*if(reg.checkNickname(nickname)) {
		 				msg+="\n nickname gi� in uso";
		 			}*/
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
        GridBagConstraints gbc_btnRegistration = new GridBagConstraints();
        gbc_btnRegistration.insets = new Insets(0, 0, 5, 5);
        gbc_btnRegistration.gridx = 7;
        gbc_btnRegistration.gridy = 9;
        frameRegistrationAdmin.getContentPane().add(btnRegistration, gbc_btnRegistration);

        frameRegistrationAdmin.show();
    }

}