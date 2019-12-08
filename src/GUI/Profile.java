package GUI;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import RdF.Comands;
import RdF.ProxyRdF;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Profile {

	private JFrame frmProfile;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textNickname;
	private JTextField textMail;
	JButton btnUpdate;
	private String password="",email="",name="",surname="",nickname="";
	ProxyRdF prox;
	private ObjectOutputStream ous;
	
	public Profile(ProxyRdF prox)
	{
		this.prox=prox;
		prox.Prof(this);
		initialize();
	}

	public void setEmail(String temp)
	{
		email=temp;
	}

	public  void setName(String temp)
	{
		name=temp;
	}

	public void setSurname(String temp)
	{
		surname=temp;
	}

	public void setNickname(String temp)
	{
		nickname=temp;
	}

	private void initialize()
	{
		frmProfile = new JFrame();
		frmProfile.setBackground(Color.BLUE);
		frmProfile.getContentPane().setBackground(Color.WHITE);
		frmProfile.setTitle("RdF: Profilo");
		frmProfile.setBounds(100, 100, 592, 351);
		frmProfile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//-----------------------------------------------------		
		frmProfile.show();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 75, 47, 58, 151, 87, 81, 60, 0 };
		gridBagLayout.rowHeights = new int[] { 18, 26, 23, 20, 20, 20, 20, 1, 26, 23, 26, 23, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		frmProfile.getContentPane().setLayout(gridBagLayout);

		JLabel lblInfo = new JLabel("Qui puoi modificare le tue informazioni");
		GridBagConstraints gbc_lblInfo = new GridBagConstraints();
		gbc_lblInfo.gridwidth = 3;
		gbc_lblInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblInfo.gridx = 2;
		gbc_lblInfo.gridy = 1;
		frmProfile.getContentPane().add(lblInfo, gbc_lblInfo);
		// -----------------------------------------------------

		JLabel lblName = new JLabel("Nome");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 3;
		frmProfile.getContentPane().add(lblName, gbc_lblName);
		// -----------------------------------------------------

		JButton btnModify = new JButton("MODIFICA");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdate.setVisible(true);
				textName.setEditable(true);
				textSurname.setEditable(true);
				textMail.setEditable(true);
				btnModify.setVisible(false);
			}
		});
		// -----------------------------------------------------

		textName = new JTextField(name);
		textName.setEditable(false);
		textName.setColumns(10);
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.anchor = GridBagConstraints.NORTH;
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.gridwidth = 2;
		gbc_textName.gridx = 3;
		gbc_textName.gridy = 3;
		frmProfile.getContentPane().add(textName, gbc_textName);
		name=textName.getText();
		
// -----------------------------------------------------
		JLabel lblSurname = new JLabel("Cognome");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.WEST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 4;
		frmProfile.getContentPane().add(lblSurname, gbc_lblSurname);

		textSurname = new JTextField(surname);
		textSurname.setEditable(false);
		textSurname.setColumns(10);
		GridBagConstraints gbc_textSurname = new GridBagConstraints();
		gbc_textSurname.anchor = GridBagConstraints.NORTH;
		gbc_textSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSurname.insets = new Insets(0, 0, 5, 5);
		gbc_textSurname.gridwidth = 2;
		gbc_textSurname.gridx = 3;
		gbc_textSurname.gridy = 4;
		frmProfile.getContentPane().add(textSurname, gbc_textSurname);
		surname=textSurname.getText();
		// -----------------------------------------------------

		JLabel lblNickname = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.WEST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 1;
		gbc_lblNickname.gridy = 5;
		frmProfile.getContentPane().add(lblNickname, gbc_lblNickname);

		textNickname = new JTextField(nickname);
		textNickname.setEditable(false);
		textNickname.setColumns(10);
		GridBagConstraints gbc_textNickname = new GridBagConstraints();
		gbc_textNickname.anchor = GridBagConstraints.NORTH;
		gbc_textNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNickname.insets = new Insets(0, 0, 5, 5);
		gbc_textNickname.gridwidth = 2;
		gbc_textNickname.gridx = 3;
		gbc_textNickname.gridy = 5;
		frmProfile.getContentPane().add(textNickname, gbc_textNickname);

		// -----------------------------------------------------

		JLabel lblMail = new JLabel("Mail");
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.WEST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 1;
		gbc_lblMail.gridy = 6;
		frmProfile.getContentPane().add(lblMail, gbc_lblMail);

		textMail = new JTextField(email);
		textMail.setEditable(false);
		textMail.setColumns(10);
		GridBagConstraints gbc_textMail = new GridBagConstraints();
		gbc_textMail.anchor = GridBagConstraints.NORTH;
		gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMail.insets = new Insets(0, 0, 5, 5);
		gbc_textMail.gridwidth = 2;
		gbc_textMail.gridx = 3;
		gbc_textMail.gridy = 6;
		frmProfile.getContentPane().add(textMail, gbc_textMail);
		email=textMail.getText();
		// -----------------------------------------------------

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 7;
		frmProfile.getContentPane().add(label, gbc_label);

		// -----------------------------------------------------
		JButton btnResetPassword = new JButton("REIMPOSTA PASSWORD");
		btnResetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Change_Pswd rp = new Change_Pswd(prox);
			}
		});
		// -----------------------------------------------------

		btnUpdate = new JButton("AGGIORNA");
		btnUpdate.addActionListener(new ActionListener() {
			/*-------------------*/
			public void actionPerformed(ActionEvent e)
			{
				nickname=textNickname.getText();
				name=textName.getText();
				surname=textSurname.getText();
				email=textMail.getText();
				prox.ProfChange(email,name,surname,nickname);
			}
		});
		btnUpdate.setVisible(false);
		btnUpdate.setVisible(false);
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 4;
		gbc_btnUpdate.gridy = 9;
		frmProfile.getContentPane().add(btnUpdate, gbc_btnUpdate);
		GridBagConstraints gbc_btnResetPassword = new GridBagConstraints();
		gbc_btnResetPassword.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnResetPassword.insets = new Insets(0, 0, 0, 5);
		gbc_btnResetPassword.gridx = 3;
		gbc_btnResetPassword.gridy = 11;
		frmProfile.getContentPane().add(btnResetPassword, gbc_btnResetPassword);
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnModify.insets = new Insets(0, 0, 0, 5);
		gbc_btnModify.gridx = 4;
		gbc_btnModify.gridy = 11;
		frmProfile.getContentPane().add(btnModify, gbc_btnModify);
		// -----------------------------------------------------

		JButton btnBack = new JButton("INDIETRO");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProfile.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBack.gridx = 5;
		gbc_btnBack.gridy = 11;
		frmProfile.getContentPane().add(btnBack, gbc_btnBack);
	}


}