package GUI;



import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import RdF.ProxyRdF;

import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MatchName {

    private JFrame frmMatchName;
    private JTextField textField;

    public MatchName(ProxyRdF prox) {
        frmMatchName = new JFrame();
        frmMatchName.getContentPane().setBackground(Color.WHITE);
        frmMatchName.setBackground(Color.BLUE);
        frmMatchName.setTitle("RdF: nuova partita");
        frmMatchName.setBounds(100, 100, 412, 228);
        frmMatchName.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{48, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmMatchName.getContentPane().setLayout(gridBagLayout);

        JLabel lblMatchName = new JLabel("Inserisci nome partita");
        GridBagConstraints gbc_lblMatchName = new GridBagConstraints();
        gbc_lblMatchName.insets = new Insets(0, 0, 5, 5);
        gbc_lblMatchName.gridx = 1;
        gbc_lblMatchName.gridy = 1;
        frmMatchName.getContentPane().add(lblMatchName, gbc_lblMatchName);
        frmMatchName.show();

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 5;
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 2;
        frmMatchName.getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);

        JButton btnBack = new JButton("INDIETRO");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frmMatchName.setVisible(false);
            }
        });
        GridBagConstraints gbc_btnBack = new GridBagConstraints();
        gbc_btnBack.insets = new Insets(0, 0, 0, 5);
        gbc_btnBack.gridx = 2;
        gbc_btnBack.gridy = 4;
        frmMatchName.getContentPane().add(btnBack, gbc_btnBack);

        JButton btnMatchName = new JButton("OK");
        btnMatchName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String namematch = textField.getText();
                if (namematch.length() < 0) {
                    JOptionPane.showMessageDialog(null, "Nome troppo corto");
                    textField.setText("");
                } else {
                    frmMatchName.setVisible(false);
                    try {
                        int temp=prox.NewGame(namematch);
                        if(temp>0)
                        {
                            SecondMatch match = new SecondMatch(prox);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        GridBagConstraints gbc_btnMatchName = new GridBagConstraints();
        gbc_btnMatchName.insets = new Insets(0, 0, 0, 5);
        gbc_btnMatchName.gridx = 4;
        gbc_btnMatchName.gridy = 4;
        frmMatchName.getContentPane().add(btnMatchName, gbc_btnMatchName);
    }

}