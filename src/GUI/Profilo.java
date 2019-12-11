package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Profilo {
    private JPanel registrationPanel;
    private JPanel westpanel;
    private JPanel southpanel;
    private JLabel NomeLabel;
    private JLabel CognomeLabel;
    private JTextArea textNome;
    private JTextArea textSurname;
    private JTextArea NicknameText;
    private JPanel eastPanel;
    private JButton AGGIORNAButton;
    private JButton INDIETROButton;
    private JTextArea textEmail;
    private JButton MODIFICAButton;
    private JButton REIMPOSTAPASSWORDButton;
    private String password="",email="",name="",surname="",nickname="";
    ProxyRdF prox;
    private ObjectOutputStream ous;

    public Profilo(ProxyRdF prox)
    {
        this.prox=prox;
        prox.Prof(this);
        initialize();
    }

    public void setEmail(String temp)
    {
        email=temp;
    }

    public  void setName(String temp)
    {
        name=temp;
    }

    public void setSurname(String temp)
    {
        surname=temp;
    }

    public void setNickname(String temp) { nickname=temp; }

    private void initialize()
    {
        MODIFICAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AGGIORNAButton.setVisible(true);
                textNome.setEditable(true);
                textSurname.setEditable(true);
                textEmail.setEditable(true);
                MODIFICAButton.setVisible(false);
            }
        });
        textNome = new JTextArea(name);
        textSurname = new JTextArea(surname);
        textEmail = new JTextArea(email);



        REIMPOSTAPASSWORDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChangePassword rp = new ChangePassword(prox);
            }
        });

        AGGIORNAButton.addActionListener(new ActionListener() {
                                             /*-------------------*/
                                             public void actionPerformed(ActionEvent e) {
                                                 nickname = NicknameText.getText();
                                                 name = textNome.getText();
                                                 surname = textSurname.getText();
                                                 email = textEmail.getText();
                                                 prox.ProfChange(email, name, surname, nickname);
                                             }
                                         });
            INDIETROButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                }
            });



    }


}
