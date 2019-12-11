package GUI;

import Csv.ReadCsv;
import RdF.ProxyRdF;
import RdF.Sentence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Manager_Sentence {
    static String str;
    private JButton IMPORTADAFILECSVButton;
    private JButton ELIMINAFRASEButton;
    private JButton MODIFICAFRASEButton;
    private JButton INSERISCIFRASEButton;

    //public static void main(String[] args){
    public Manager_Sentence(ProxyRdF prox) {
        ArrayList<String> labels = new ArrayList<>(25);
        for (int index = 0; index < 100; index++) {
            labels.add("Frase " + index);
        }
        MODIFICAFRASEButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                str = "Modifica";
                Select_Sentence ss = new Select_Sentence(str, prox);
            }
        });

        ELIMINAFRASEButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                str = "Elimina";
                Select_Sentence ss = new Select_Sentence(str, prox);
            }
        });
        INSERISCIFRASEButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add_Sentence aS = new Add_Sentence(prox);
            }
        });
        IMPORTADAFILECSVButton.addActionListener(new ActionListener() {
            int i;
            public void actionPerformed(ActionEvent e) {
                String filePath;
                JFileChooser fc = new JFileChooser();// la directory di default � Documents
                int returnVal = fc.showOpenDialog(null);// apre finestra modale

                if(returnVal == JFileChooser.APPROVE_OPTION) {//  Il valore restituito se approvato (s�, ok) � scelto
                    filePath=fc.getSelectedFile().getPath();//ottendo path file

                    if(filePath.endsWith(".csv")) {//controllo se il file � csv. Se si importo altrimenti il file non � in cvs e non puo essere utilizzato
                        ArrayList<Sentence> arraySentences=new ArrayList<Sentence>();
                        arraySentences= ReadCsv.importSentences(filePath);
                        System.out.println(arraySentences.size());
                        if(arraySentences.size()>0) {//se array contiene qualcosa trasfersci. Altrimenti vuoto
                            for(i=0;i<arraySentences.size();i++) {
                                //System.out.println(arraySentences.get(i));//Vladi qui lavora te******************************************************
                                prox.CsVSentTrans(arraySentences);
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"File vuoto");
                        }

                    }else {
                        JOptionPane.showMessageDialog(null,"File non compatibile");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"Operazione annullata");
                }
            }
        });
    }
}
