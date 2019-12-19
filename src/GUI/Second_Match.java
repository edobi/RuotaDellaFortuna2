package GUI;

import RdF.Comands;
import RdF.GuiProxtest;
import RdF.ProxyRdF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Second_Match {
    private JTextField textField1;
    private JTable table1;
    private JButton GIRALARUOTAButton;
    private JList list1;
    private JButton CONFERMAButton;
    private JButton COMPRAVOCALEButton;
    private JButton ABBANDONAButton;
    private JButton DALASOLUZIONEButton;
    private JLabel LabelARGOMENTO, LabelNUMMANCHE, JLABELTEMP, LABELPOINTS;
    private JLabel jargomento;
    JLabel[] ppos;
    private ProxyRdF prox;
    private Comands com;
    Long clock;

    boolean solution=false;



    public Second_Match(ProxyRdF pro) {
        ppos=new JLabel[3];
        com= Comands.END;
        prox=pro;
        initialize();
        prox.setLccom(com.getCode());
        prox.setObs(false);
        new Thread(new GuiProxtest(this.table1, prox,LabelARGOMENTO,LabelNUMMANCHE,JLABELTEMP,ppos,LABELPOINTS,DALASOLUZIONEButton,COMPRAVOCALEButton,CONFERMAButton,GIRALARUOTAButton)).start();
    }
    public Second_Match()
    {
        initialize();
        // TODO Auto-generated constructor stub
    }
    private void initialize() {
        table1.setModel(new DefaultTableModel(
                new String[][] { { null, null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null, null },
                        { null, null, null, null, null, null, null, null, null, null }, },
                new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
                        "New column", "New column", "New column", "New column" }));

        for(int i=0;i<3;i++)
        {
            int x=5;
            int y=3;
            JLabel temp = new JLabel("");
            GridBagConstraints gtemp = new GridBagConstraints();
            gtemp.anchor = GridBagConstraints.SOUTH;
            gtemp.insets = new Insets(0, 0, 5, 5);
            gtemp.gridx = x;
            gtemp.gridy = y+i;
            ppos[i]=temp;
        }

        JButton GIRALARUOTAButton = new JButton("GIRA LA RUOTA");
        GIRALARUOTAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //metodo gira la ruota
                System.out.println(com);
                com=ProxyRdF.getCombyCode(prox.getLccom());
                if((com==Comands.YT)) {
                    System.out.println(com);
                    JOptionPane.showMessageDialog(null, "Gira la ruota");
                    com=Comands.SPIN;
                    prox.setLccom(com.getCode());
                    prox.Confirm(prox.getLccom(),"",table1);
                }
            }
        });

        GIRALARUOTAButton.setVisible(false);
        GIRALARUOTAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                com=prox.getCombyCode(prox.getLccom());
                if(com.equals(Comands.YT)&&(!prox.getconse()))
                {
                    com=Comands.SPIN;
                    prox.setLccom(com.getCode());
                    prox.Confirm(prox.getLccom(),"",table1);
                    JOptionPane.showMessageDialog(null, "Gira la ruota");
                    GIRALARUOTAButton.setEnabled(true);
                }
            }
        });


        JButton COMPRAVOCALEButton = new JButton("COMPRA VOCALE");
        COMPRAVOCALEButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                com=prox.getCombyCode(prox.getLccom());
                if(com.equals(Comands.YT))
                {
                    com=Comands.VOWEL;
                    prox.setLccom(com.getCode());
                    CONFERMAButton.setVisible(true);
                }
            }
        });

        JButton DALASOLUZIONEButton = new JButton("DA LA SOLUZIONE");
        DALASOLUZIONEButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //metodo soluzione
                com=prox.getCombyCode(prox.getLccom());
                if(com.equals(Comands.YT))
                {
                    com=Comands.SOLUT;
                    prox.setLccom(com.getCode());
                    //JOptionPane.showMessageDialog(null, "Dai soluzione");
                    //lblChoose.setVisible(true);
                    //lblText.setVisible(true);
                    //textFieldAnsware.setVisible(true);
                    CONFERMAButton.setVisible(true);
                    solution=true;
                }
            }
        });

        JButton CONFERMAButton = new JButton("CONFERMA");
        CONFERMAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(com.equals(Comands.SOLUT)) {

                    prox.Confirm(prox.getLccom(), JOptionPane.showInputDialog(null, "inserisci la soluzione"), table1);
                }
                if(com.equals(Comands.VOWEL)) {
                    prox.setltime(System.currentTimeMillis());
                    prox.Confirm(prox.getLccom(), JOptionPane.showInputDialog(null, "inserisci la vocale"), table1);
                }
                System.out.println(solution);
            }
        });

        JButton ABBANDONAButton = new JButton("ABBANDONA");
        ABBANDONAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prox.setLccom(Comands.DISCONNECT.getCode());
                prox.Confirm(prox.getLccom(),"", table1);
            }
        });
    }
}

