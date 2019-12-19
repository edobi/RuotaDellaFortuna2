package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.mail.internet.InternetAddress;

public class Authentication extends JFrame implements Runnable {
    private JFrame frmAuthentication;
    private JPanel Login;
    private JPanel south;
    private JPanel east;
    private JPanel west;
    private JLabel nicknameLabel;
    private JLabel passwordLabel;
    private JButton Esci;
    private JButton nonHaiUnAccountButton;
    private JTextField textEmail;
    private JTextField textPassword;
    private JLabel ForgotPassword;
    private JButton Accedi;
    InternetAddress mail;
    boolean mailOk = true;
    ProxyRdF server;
    private JPasswordField passwordField1;
    private ObjectInputStream ins;
    private ObjectOutputStream ous;

    public Authentication() {
        initialize();
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                debug();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void debug() {
        for (int i = 0; i < 4; i++)
            new Thread(new Authentication()).start();
    }



    @Override
    public void run() {

    }

    private void initialize() {
        try {
            server = new ProxyRdF();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        Accedi.addActionListener(e -> {
                    String email = textEmail.getText();//ottengo email
                    String psw = new String(passwordField1.getPassword());//ottengo password
                    System.out.println(email + " " + psw);
                    System.out.println(server);
                    int b = server.LoginBtn(email, psw);
                    if (b > 0) {
                        if (b == 2) new Activation_Code(server);
                        else {
                            if (b == 1) server.setPoA(true);
                            new Home(server);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Inserimento dati errato");
                    }
                }
        );
        Esci.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        ForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Reset_Password rp = new Reset_Password(server);
            }
        });

        nonHaiUnAccountButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //chiama gui per registrazione
                Sign_Up_Player sup = new Sign_Up_Player(server);
            }
        });
    }
}