package GUI;

import Csv.ReadCsv;
import RdF.ProxyRdF;
import RdF.Sentence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ImportSentence {
    private JButton CONFERMAButton;
    private JTextArea textArea1;


    public ImportSentence(ProxyRdF prox) {

        CONFERMAButton.addActionListener(new ActionListener() {
            private int i;

            public void actionPerformed(ActionEvent e) {
                String filePath;
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(fc);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    filePath = fc.getSelectedFile().getPath();
                    System.out.println(filePath);
                    if (filePath.endsWith(".csv")) {
                        ArrayList<Sentence> arraySentences = new ArrayList<Sentence>();
                        arraySentences = ReadCsv.importSentences(filePath);
                        if (arraySentences != null) {
                            //for(i=0;i<arraySentences.size();i++) {
                            //System.out.println(arraySentences.get(i));
                            //}
                            prox.CsVSentTrans(arraySentences);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "File non accettato");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operazione annullata");
                }
            }
        });
    }
}