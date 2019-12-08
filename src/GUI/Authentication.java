package GUI;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.mail.internet.InternetAddress;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import RdF.ProxyRdF;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Authentication implements Runnable {

    private JFrame frmAuthentication;
    private JTextField textMail;
    private JPasswordField textPassword;
    private ObjectInputStream ins;
    private ObjectOutputStream ous;
    InternetAddress mail;
    boolean mailOk = true;
    ProxyRdF server;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                debug();
                //Authentication window = new Authentication();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void debug() {
        for (int i = 0; i < 4; i++)
            new Thread(new Authentication()).start();
    }

    public Authentication() {
        initialize();
    }

    @Override
    public void run()
    {

    }

    private void initialize() {
        try {
            server = new ProxyRdF();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        frmAuthentication = new JFrame();
        frmAuthentication.getContentPane().setBackground(Color.WHITE);
        frmAuthentication.setBackground(Color.BLUE);
        frmAuthentication.setTitle("RdF: ACCEDI");
        frmAuthentication.setBounds(100, 100, 483, 327);
        frmAuthentication.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmAuthentication.show();
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{57, 66, 35, 105, 63, 90, 0};
        gridBagLayout.rowHeights = new int[]{55, 20, 20, 54, 23, 31, 14, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmAuthentication.getContentPane().setLayout(gridBagLayout);
//------------------------------------------------------------		
        JLabel lblEmail = new JLabel("E-mail");
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.anchor = GridBagConstraints.WEST;
        gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
        gbc_lblEmail.gridx = 1;
        gbc_lblEmail.gridy = 1;
        frmAuthentication.getContentPane().add(lblEmail, gbc_lblEmail);
//------------------------------------------------------------		
        textMail = new JTextField();
        textMail.setColumns(10);
        GridBagConstraints gbc_textMail = new GridBagConstraints();
        gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
        gbc_textMail.insets = new Insets(0, 0, 5, 5);
        gbc_textMail.gridwidth = 2;
        gbc_textMail.gridx = 3;
        gbc_textMail.gridy = 1;
        frmAuthentication.getContentPane().add(textMail, gbc_textMail);

//------------------------------------------------------------	
        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.WEST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 1;
        gbc_lblPassword.gridy = 2;
        frmAuthentication.getContentPane().add(lblPassword, gbc_lblPassword);

//------------------------------------------------------------	
        textPassword = new JPasswordField();
        textPassword.setColumns(10);
        GridBagConstraints gbc_textPassword = new GridBagConstraints();
        gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_textPassword.anchor = GridBagConstraints.NORTH;
        gbc_textPassword.insets = new Insets(0, 0, 5, 5);
        gbc_textPassword.gridwidth = 2;
        gbc_textPassword.gridx = 3;
        gbc_textPassword.gridy = 2;
        frmAuthentication.getContentPane().add(textPassword, gbc_textPassword);
//*********************************************		
        JButton btnLogin = new JButton("ACCEDI");
        btnLogin.addActionListener(e -> {
                    /*-------------------*/
                    //	Login login=new Login();
                    String email = textMail.getText();//ottengo email
                    String psw = new String(textPassword.getPassword());//ottengo password
                    System.out.println(email + " " + psw);
                    System.out.println(server);
                    int b = server.LoginBtn(email, psw);
                    if (b > 0) {
                        if (b == 2) new Activation_Code(server);
                        else {
                            if (b == 1) server.setPoA(true);
                            frmAuthentication.setVisible(false);
                            new Home(server);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Inserimento dati errato");
                    }
                }//fine action perfermed
        );


//------------------------------------------------------------		
        JButton btnExit = new JButton("ESCI");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmAuthentication.setVisible(false);
            }
        });
        GridBagConstraints gbc_btnExit = new GridBagConstraints();
        gbc_btnExit.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnExit.insets = new Insets(0, 0, 5, 5);
        gbc_btnExit.gridx = 3;
        gbc_btnExit.gridy = 4;
        frmAuthentication.getContentPane().add(btnExit, gbc_btnExit);
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnLogin.insets = new Insets(0, 0, 5, 5);
        gbc_btnLogin.gridx = 4;
        gbc_btnLogin.gridy = 4;
        frmAuthentication.getContentPane().add(btnLogin, gbc_btnLogin);

        JLabel lblPasswordDimenticata = new JLabel("Password dimenticata");
        lblPasswordDimenticata.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Reset_Pswd rp = new Reset_Pswd(server);
            }
        });
        GridBagConstraints gbc_lblPasswordDimenticata = new GridBagConstraints();
        gbc_lblPasswordDimenticata.insets = new Insets(0, 0, 5, 5);
        gbc_lblPasswordDimenticata.gridx = 4;
        gbc_lblPasswordDimenticata.gridy = 6;
        frmAuthentication.getContentPane().add(lblPasswordDimenticata, gbc_lblPasswordDimenticata);

//------------------------------------------------------------	
        JLabel lblRegistrati = new JLabel("Non hai un account: Registrati");
        lblRegistrati.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //chiama gui per registrazione
                SignUpPlayer sup = new SignUpPlayer(server);
            }
        });
        GridBagConstraints gbc_lblRegistrati = new GridBagConstraints();
        gbc_lblRegistrati.insets = new Insets(0, 0, 0, 5);
        gbc_lblRegistrati.anchor = GridBagConstraints.NORTHEAST;
        gbc_lblRegistrati.gridx = 4;
        gbc_lblRegistrati.gridy = 7;
        frmAuthentication.getContentPane().add(lblRegistrati, gbc_lblRegistrati);
        frmAuthentication.show();
    }
}