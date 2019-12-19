package GUI;

import RdF.ProxyRdF;
import RdF.Sentence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add_Sentence {
    private JTextField textArgument;
    private JTextArea textSentence;
    String sent, arg;
    private JTextField textField1;
    private JButton indietroButton;
    private JButton aggiungiButton;
    private JTextArea textArea1;

    public Add_Sentence(ProxyRdF prox) {

        JButton aggiungiButton = new JButton("AGGIUNGI");
        aggiungiButton.addActionListener(new ActionListener() {
            /*-------------------*/
            public void actionPerformed(ActionEvent e) {
				/*try {
					ous.writeObject(Comands.NFRASE);
				} catch (IOException enf) {
					// TODO Auto-generated catch block
					enf.printStackTrace();
				}
				*/
                prox.NewFrase();
                arg = textArgument.getText();
                sent = textSentence.getText();
                boolean b = Sentence.checkLength(sent);
                if (b == true) {
					/*if((sent!=null)&&(arg!=null))
					{
						Sentence nfrase=new Sentence();
						nfrase.sent=sent;
						nfrase.theme=arg;
						try {
							ous.writeObject(nfrase);
						} catch (IOException ens) {
							// TODO Auto-generated catch block
							ens.printStackTrace();
						}
					}
					*/
                    if ((sent != "") && (arg != "")) {
                        Sentence nsent = new Sentence(sent, arg);
                        prox.NCFrase(nsent);
                    }
                } else {
                    textSentence.setText("");
                    System.out.println("Frase troppo lunga");
                }
            }
        });
        JButton indietroButton = new JButton("INDIETRO");
        indietroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}

