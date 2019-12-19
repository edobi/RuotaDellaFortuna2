package GUI;

import RdF.Comands;
import RdF.GuiProxtest;
import RdF.ProxyRdF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class Obs {
    private JTextField textField1;
    private JTable table1;
    private JList list1;
    private JLabel Temp;
    private JLabel Label1;
    private JLabel Label2;
    boolean solution=false;
    JLabel[] ppos;
    JLabel LABELARGOMENTO;
    JLabel NUMMANCHE;
    JLabel TEXTSOLUZIONE;
    JLabel LABELPOINTS;
    //private ObjectInputStream ins;
    //private static ObjectOutputStream ous;
    private ProxyRdF prox;
    private Comands com;
    //private int ccom;
    Long clock;

    public Obs(ProxyRdF pro)
    {
        ppos=new JLabel[3];
        com= Comands.END;
        prox=pro;
        initialize();
        prox.setLccom(com.getCode());
        prox.setObs(false);
        new Thread(new GuiProxtest(this.table1, prox,LABELARGOMENTO,NUMMANCHE,TEXTSOLUZIONE,ppos,LABELPOINTS)).start();
    }
    public Obs()
    {
        initialize();
        // TODO Auto-generated constructor stub
    }

    private void initialize() {
        Label1 = new JLabel(prox.getNick()+" Punti:");
        Label2 = new JLabel("");

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
            int x=6;
            int y=3;
            JLabel Temp = new JLabel();
            GridBagConstraints gtemp = new GridBagConstraints();
            gtemp.anchor = GridBagConstraints.SOUTH;
            gtemp.insets = new Insets(0, 0, 5, 5);
            gtemp.gridx = x;
            gtemp.gridy = y+i;
            ppos[i]=Temp;
        }
        JList list1 = new JList();

    }
}
