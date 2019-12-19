package GUI;

import RdF.ProxyRdF;
import RdF.Sentence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Select_Sentence {
    private ObjectInputStream ins;
    private ObjectOutputStream ous;
    private JList list1;
    private JButton INDIETROButton;

    public Select_Sentence(String str, ProxyRdF prox) {
        ArrayList<Sentence> labels= prox.loadSentence(); //DummyClassGuiFunctions.loadSentences();
        final JList list1 = new JList(Listreturn(labels));
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selected = (String)list1.getSelectedValue();
                int ind=list1.getSelectedIndex();
                System.out.println(ind);
                if(str.equals("Elimina")) {
                    /*-------------------*/
                    int dialogButton = JOptionPane.showConfirmDialog(null, "Vuoi eliminare la frase?");
                    System.out.println(dialogButton);
                    if (dialogButton == 0) {
                        //ous.writeObject(Comands.DFRASE);
                        //ous.writeObject(selected.sent);
                        prox.DFraseConf(selected);
                        labels.remove(selected);
                        //JOptionPane.showInternalMessageDialog(null, "Frase eliminata");
                    }
                }
                if(str.equals("Modifica")) {
                    Modify_Sentence ms= new Modify_Sentence(labels.get(ind),prox);}

            }});

        JButton INDIETROButton = new JButton("INDIETRO");
        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    private String[] Listreturn(ArrayList<Sentence> labels)
    {
        int size=labels.size();
        String[] temp=new String[size];
        for(int i=0;i<size;i++)
        {
            temp[i]=labels.get(i).sent;
        }
        return temp;
    }

    }
