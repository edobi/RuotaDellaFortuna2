package GUI;


import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.LineBorder;

import RdF.ProxyRdF;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Activation_Code {

    private JFrame frmActivationCode;
    private JTextField textCode;

    /*public static void main(String[] args) {
          EventQueue.invokeLater(new Runnable() {
              public void run() {
                  try {
                      Activation_Code window = new Activation_Code();
                      window.frmActivationCode.setVisible(true);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
          });
      }
   */
    public Activation_Code(ProxyRdF prox) {
        frmActivationCode = new JFrame();
        frmActivationCode.setTitle("RdF: CODICE DI ATTIVAZIONE");
        frmActivationCode.setBackground(Color.BLUE);
        frmActivationCode.getContentPane().setBackground(Color.WHITE);
        frmActivationCode.setBounds(100, 100, 389, 190);
        frmActivationCode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{60, 187, 60, 0};
        gridBagLayout.rowHeights = new int[]{14, 16, 28, 38, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frmActivationCode.getContentPane().setLayout(gridBagLayout);

        JLabel lblInsert = new JLabel("Inserisci codice di attivazone ottenuto tramite mail:");
        GridBagConstraints gbc_lblInsert = new GridBagConstraints();
        gbc_lblInsert.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblInsert.anchor = GridBagConstraints.NORTH;
        gbc_lblInsert.insets = new Insets(0, 0, 5, 5);
        gbc_lblInsert.gridx = 1;
        gbc_lblInsert.gridy = 1;
        frmActivationCode.getContentPane().add(lblInsert, gbc_lblInsert);

        textCode = new JTextField();
        textCode.setBorder(new LineBorder(Color.BLACK));
        textCode.setColumns(10);
        GridBagConstraints gbc_textCode = new GridBagConstraints();
        gbc_textCode.fill = GridBagConstraints.HORIZONTAL;
        gbc_textCode.insets = new Insets(0, 0, 5, 5);
        gbc_textCode.gridx = 1;
        gbc_textCode.gridy = 2;
        frmActivationCode.getContentPane().add(textCode, gbc_textCode);

        JButton btnConfirm = new JButton("CONFERMA");
        btnConfirm.addActionListener(e -> prox.ActivCode(textCode.getText()));
        GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
        gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
        gbc_btnConfirm.anchor = GridBagConstraints.NORTH;
        gbc_btnConfirm.gridx = 1;
        gbc_btnConfirm.gridy = 4;
        frmActivationCode.getContentPane().add(btnConfirm, gbc_btnConfirm);

        frmActivationCode.show();
    }
}