package GUI;

import RdF.ProxyRdF;
import RdF.Sentence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

public class Modify_Sentence {
    private JTextField textField1;
    private JTextField textField2;
    private JButton INDIETROButton;
    private JButton CONFERMAButton;
    String sent;
    private ObjectOutputStream ous;

    public Modify_Sentence(Sentence i, ProxyRdF prox) {
        textField1 = new JTextField(i.theme);

        textField2 = new JTextField(i.sent);


        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        CONFERMAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sentence nsent = new Sentence(textField1.getText().toString(), textField2.getText().toString());
                boolean c = Sentence.checkLength(nsent.theme);
                boolean b = Sentence.checkLength(nsent.sent);
                if (b == c == true) {

                    prox.CFraseConf(nsent, i);

                    System.out.println("Frase aggiunta");
                } else {
                    textField2.setText("");
                    System.out.println("Frase troppo lunga");
                }
            }
        });
    }
}
