package GUI;

import javax.swing.*;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Cursor;
import java.awt.Color;

import RdF.ProxyRdF;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Home {

    private static JFrame frmRuotaDellaFortuna;
    private static JButton btnEnter;

    public Home(ProxyRdF prox) {
        //boolean player= DatabaseConnector.isPlayerByNick(username);
        frmRuotaDellaFortuna = new JFrame();
        frmRuotaDellaFortuna.setBackground(Color.BLUE);
        frmRuotaDellaFortuna.getContentPane().setBackground(Color.WHITE);
        frmRuotaDellaFortuna.setTitle("RUOTA DELLA FORTUNA");
        frmRuotaDellaFortuna.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        frmRuotaDellaFortuna.getContentPane().setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        frmRuotaDellaFortuna.setBounds(100, 100, 618, 404);
        frmRuotaDellaFortuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 180, 60, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{26, 25, 25, 26, 26, 26, 26, 26, 26, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmRuotaDellaFortuna.getContentPane().setLayout(gridBagLayout);

        JLabel lblHome = new JLabel("HOME");
        lblHome.setHorizontalAlignment(SwingConstants.CENTER);
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHome.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        GridBagConstraints gbc_lblHome = new GridBagConstraints();
        gbc_lblHome.fill = GridBagConstraints.BOTH;
        gbc_lblHome.insets = new Insets(0, 0, 5, 5);
        gbc_lblHome.gridx = 1;
        gbc_lblHome.gridy = 1;
        frmRuotaDellaFortuna.getContentPane().add(lblHome, gbc_lblHome);


        JButton btnSentences = new JButton("GESTIONE FRASI");
        btnSentences.setVisible(false);
        btnSentences.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Manager_Sentences ms = new Manager_Sentences(prox);
            }
        });


        JButton btnStatistics = new JButton("STATISTICHE");
        btnStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Stats s = new Stats(prox);
            }
        });

        JButton btnProfile = new JButton("PROFILO");
        btnProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Profile profile = new Profile(prox);
            }
        });


        JButton btnShowMatch = new JButton("GUARDA PARTITA");
        btnShowMatch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    List_Match lmo = new List_Match(false, prox);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        JButton btnNewMatch = new JButton("NUOVA PARTITA");
        btnNewMatch.setVisible(false);
        btnNewMatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MatchName mn = new MatchName(prox);
            }
        });

        //JLabel lblUser = new JLabel("BENVENUTO "+ username);
        GridBagConstraints gbc_lblUser = new GridBagConstraints();
        gbc_lblUser.insets = new Insets(0, 0, 5, 5);
        gbc_lblUser.gridx = 5;
        gbc_lblUser.gridy = 1;
        //frmRuotaDellaFortuna.getContentPane().add(lblUser, gbc_lblUser);
        GridBagConstraints gbc_btnNewMatch = new GridBagConstraints();
        gbc_btnNewMatch.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnNewMatch.anchor = GridBagConstraints.NORTH;
        gbc_btnNewMatch.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewMatch.gridx = 1;
        gbc_btnNewMatch.gridy = 3;
        frmRuotaDellaFortuna.getContentPane().add(btnNewMatch, gbc_btnNewMatch);

        btnEnter = new JButton("CERCA PARTITA");
        btnEnter.setVisible(false);
        GridBagConstraints gbc_btnEnter = new GridBagConstraints();
        gbc_btnEnter.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnEnter.anchor = GridBagConstraints.NORTH;
        gbc_btnEnter.insets = new Insets(0, 0, 5, 5);
        gbc_btnEnter.gridx = 1;
        gbc_btnEnter.gridy = 4;
        frmRuotaDellaFortuna.getContentPane().add(btnEnter, gbc_btnEnter);
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List_Match lm = new List_Match(true, prox);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_btnShowMatch = new GridBagConstraints();
        gbc_btnShowMatch.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnShowMatch.anchor = GridBagConstraints.NORTH;
        gbc_btnShowMatch.insets = new Insets(0, 0, 5, 5);
        gbc_btnShowMatch.gridx = 1;
        gbc_btnShowMatch.gridy = 5;
        frmRuotaDellaFortuna.getContentPane().add(btnShowMatch, gbc_btnShowMatch);
        GridBagConstraints gbc_btnProfile = new GridBagConstraints();
        gbc_btnProfile.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnProfile.anchor = GridBagConstraints.NORTH;
        gbc_btnProfile.insets = new Insets(0, 0, 5, 5);
        gbc_btnProfile.gridx = 1;
        gbc_btnProfile.gridy = 6;
        frmRuotaDellaFortuna.getContentPane().add(btnProfile, gbc_btnProfile);
        GridBagConstraints gbc_btnStatistics = new GridBagConstraints();
        gbc_btnStatistics.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnStatistics.anchor = GridBagConstraints.NORTH;
        gbc_btnStatistics.insets = new Insets(0, 0, 5, 5);
        gbc_btnStatistics.gridx = 1;
        gbc_btnStatistics.gridy = 7;
        frmRuotaDellaFortuna.getContentPane().add(btnStatistics, gbc_btnStatistics);
        GridBagConstraints gbc_btnSentences = new GridBagConstraints();
        gbc_btnSentences.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnSentences.insets = new Insets(0, 0, 5, 5);
        gbc_btnSentences.anchor = GridBagConstraints.NORTH;
        gbc_btnSentences.gridx = 1;
        gbc_btnSentences.gridy = 8;
        frmRuotaDellaFortuna.getContentPane().add(btnSentences, gbc_btnSentences);

        JButton btnRegisterAdmin = new JButton("REGISTRA AMMINISTRATORE");
        btnRegisterAdmin.setVisible(false);
        btnRegisterAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //SignUpAdmin sua = new SignUpAdmin(prox);
                String temp=JOptionPane.showInputDialog("inserisci email dell'utente da rendere admin");
            }
        });
        GridBagConstraints gbc_btnRegisterAdmin = new GridBagConstraints();
        gbc_btnRegisterAdmin.insets = new Insets(0, 0, 0, 5);
        gbc_btnRegisterAdmin.gridx = 1;
        gbc_btnRegisterAdmin.gridy = 9;
        frmRuotaDellaFortuna.getContentPane().add(btnRegisterAdmin, gbc_btnRegisterAdmin);


        if (prox.getPoA()) {
            btnNewMatch.setVisible(true);
            btnEnter.setVisible(true);
        } else {
            btnRegisterAdmin.setVisible(true);
            btnSentences.setVisible(true);
        }

        frmRuotaDellaFortuna.show();
    }


}