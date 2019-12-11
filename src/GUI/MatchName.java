package GUI;

import RdF.ProxyRdF;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MatchName {
    private JTextField textField1;
    private JButton CONFERMAButton;
    private JButton INDIETROButton;

    public MatchName(ProxyRdF prox) {

    CONFERMAButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String nameMatch = textField1.getText();
            if (nameMatch.length() < 0) {
                JOptionPane.showMessageDialog(null, "Nome troppo corto");
                textField1.setText("");
            }
                try {
                    int temp=prox.NewGame(nameMatch);
                    if(temp>0)
                    {
                        Second_Match match = new Second_Match(prox);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        INDIETROButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
}
}
