package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import RdF.ProxyRdF;

import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class Reset_Pswd {

	private JFrame frmChangePswd;
	private JTextField textmail;

	public Reset_Pswd(ProxyRdF prox) {
		frmChangePswd = new JFrame();
		frmChangePswd.getContentPane().setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmChangePswd.getContentPane().setLayout(gridBagLayout);
		frmChangePswd.setVisible(true);
//-------------------MAIL----------------		
		JLabel lblMail = new JLabel("Inserisci email:");
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.WEST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 1;
		gbc_lblMail.gridy = 1;
		frmChangePswd.getContentPane().add(lblMail, gbc_lblMail);
		
		textmail = new JTextField();
		GridBagConstraints gbc_textmail = new GridBagConstraints();
		gbc_textmail.gridwidth = 4;
		gbc_textmail.insets = new Insets(0, 0, 5, 5);
		gbc_textmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textmail.gridx = 1;
		gbc_textmail.gridy = 2;
		frmChangePswd.getContentPane().add(textmail, gbc_textmail);
		textmail.setColumns(10);
		
//-------------------BACK----------------			
		JButton btnBack = new JButton("INDIETRO");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChangePswd.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 4;
		frmChangePswd.getContentPane().add(btnBack, gbc_btnBack);
		
//-------------------BOTTON----------------	
		JButton btnConfirm = new JButton("CONFERMA");
		btnConfirm.addActionListener(new ActionListener() {
			/*-------------------*/
			public void actionPerformed(ActionEvent e) {
				prox.PswReset(textmail.getText());
			}
		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.anchor = GridBagConstraints.EAST;
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 4;
		gbc_btnConfirm.gridy = 4;
		frmChangePswd.getContentPane().add(btnConfirm, gbc_btnConfirm);
		frmChangePswd.setForeground(Color.BLUE);
		frmChangePswd.setBounds(100, 100, 741, 212);
		frmChangePswd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}