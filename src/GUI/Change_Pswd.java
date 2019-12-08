package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import RdF.ProxyRdF;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class Change_Pswd {

	private JFrame frmChangePswd;
	private JTextField textNewPswd;
	private JTextField textConfPswd;

	public Change_Pswd(ProxyRdF prox) {

		frmChangePswd = new JFrame();
		frmChangePswd.setBackground(Color.BLUE);
		frmChangePswd.getContentPane().setBackground(Color.WHITE);
		frmChangePswd.setTitle("RdF: CAMBIO PASSWORD");
		frmChangePswd.setBounds(100, 100, 545, 302);
		frmChangePswd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		frmChangePswd.show();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{42, 129, 47, 96, 81, 0};
		gridBagLayout.rowHeights = new int[]{78, 20, 20, 31, 23, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmChangePswd.getContentPane().setLayout(gridBagLayout);
	
//-------------------------------------------------------------		
		JLabel lblNewPswd = new JLabel("Nuova password");
		GridBagConstraints gbc_lblNewPswd = new GridBagConstraints();
		gbc_lblNewPswd.anchor = GridBagConstraints.WEST;
		gbc_lblNewPswd.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPswd.gridx = 1;
		gbc_lblNewPswd.gridy = 1;
		frmChangePswd.getContentPane().add(lblNewPswd, gbc_lblNewPswd);
		
//-------------------------------------------------------------			
		textNewPswd = new JTextField();
		textNewPswd.setColumns(10);
		GridBagConstraints gbc_textNewPswd = new GridBagConstraints();
		gbc_textNewPswd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNewPswd.anchor = GridBagConstraints.NORTH;
		gbc_textNewPswd.insets = new Insets(0, 0, 5, 5);
		gbc_textNewPswd.gridx = 3;
		gbc_textNewPswd.gridy = 1;
		frmChangePswd.getContentPane().add(textNewPswd, gbc_textNewPswd);
		
//-------------------------------------------------------------		
		JLabel lblConfPswd = new JLabel("Conferma nuova password");
		GridBagConstraints gbc_lblConfPswd = new GridBagConstraints();
		gbc_lblConfPswd.anchor = GridBagConstraints.WEST;
		gbc_lblConfPswd.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfPswd.gridx = 1;
		gbc_lblConfPswd.gridy = 2;
		frmChangePswd.getContentPane().add(lblConfPswd, gbc_lblConfPswd);
		
		
//-------------------------------------------------------------		
		textConfPswd = new JTextField();
		textConfPswd.setColumns(10);
		GridBagConstraints gbc_textConfPswd = new GridBagConstraints();
		gbc_textConfPswd.fill = GridBagConstraints.HORIZONTAL;
		gbc_textConfPswd.anchor = GridBagConstraints.NORTH;
		gbc_textConfPswd.insets = new Insets(0, 0, 5, 5);
		gbc_textConfPswd.gridx = 3;
		gbc_textConfPswd.gridy = 2;
		frmChangePswd.getContentPane().add(textConfPswd, gbc_textConfPswd);
		
//-------------------------------------------------------------		
		JButton btnBack = new JButton("INDIETRO");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChangePswd.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 5;
		frmChangePswd.getContentPane().add(btnBack, gbc_btnBack);
		
//-------------------------------------------------------------		
		JButton btnConferm = new JButton("CONFERMA");
		btnConferm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((textNewPswd.getText().length()>1)&& textNewPswd.getText().equals(textConfPswd.getText())) 
				{
					//String object="Cambio password";
					//String msg="Il cambio della password � andato a buon fine!";
					//String ind_mail= prelevo la mail da db
					prox.PswChange(textConfPswd.getText(), prox.getemail());
						/*try {
							Mail_Helper.setMail(ind_mail, object,msg);
						} catch (MessagingException e1) {
							e1.printStackTrace();}
						}else
						 {
				JOptionPane.showMessageDialog(null, "LE PASSWORD NON COMBACIANO");
				}
			*/
				}
			}
					});
		GridBagConstraints gbc_btnConferm = new GridBagConstraints();
		gbc_btnConferm.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnConferm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConferm.gridx = 3;
		gbc_btnConferm.gridy = 5;
		frmChangePswd.getContentPane().add(btnConferm, gbc_btnConferm);
	}
}