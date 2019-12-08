package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import RdF.ProxyRdF;
import RdF.Sentence;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Modify_Sentence {

	private JFrame frmModifySentence;
	private JTextField textSentence;
	String sent;
	private JTextField textArg;
	private ObjectOutputStream ous;
	
	public Modify_Sentence(Sentence i, ProxyRdF prox) {

		frmModifySentence = new JFrame();
		frmModifySentence.setBackground(Color.BLUE);
		frmModifySentence.getContentPane().setBackground(Color.WHITE);
		frmModifySentence.setTitle("RdF: Modifica frase");
		frmModifySentence.setBounds(100, 100, 720, 305);
		frmModifySentence.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frmModifySentence.show();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 56, 320, 81, 89, 60, 0};
		gridBagLayout.rowHeights = new int[]{14, 14, 20, 26, 23, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmModifySentence.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblSelected = new JLabel("Hai selezionato:");
		GridBagConstraints gbc_lblSelected = new GridBagConstraints();
		gbc_lblSelected.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelected.gridx = 1;
		gbc_lblSelected.gridy = 1;
		frmModifySentence.getContentPane().add(lblSelected, gbc_lblSelected);
		
		textArg = new JTextField(i.theme);
		GridBagConstraints gbc_textArg = new GridBagConstraints();
		gbc_textArg.insets = new Insets(0, 0, 5, 5);
		gbc_textArg.fill = GridBagConstraints.BOTH;
		gbc_textArg.gridx = 2;
		gbc_textArg.gridy = 1;
		frmModifySentence.getContentPane().add(textArg, gbc_textArg);
		textArg.setColumns(10);
		
		
		JLabel lblInsertNewSentence = new JLabel("Inserisci nuova frase:");
		GridBagConstraints gbc_lblInsertNewSentence = new GridBagConstraints();
		gbc_lblInsertNewSentence.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblInsertNewSentence.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsertNewSentence.gridwidth = 2;
		gbc_lblInsertNewSentence.gridx = 1;
		gbc_lblInsertNewSentence.gridy = 3;
		frmModifySentence.getContentPane().add(lblInsertNewSentence, gbc_lblInsertNewSentence);
		
		textSentence = new JTextField(i.sent);
		textSentence.setColumns(10);
		GridBagConstraints gbc_textSentence = new GridBagConstraints();
		gbc_textSentence.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSentence.insets = new Insets(0, 0, 5, 5);
		gbc_textSentence.gridwidth = 3;
		gbc_textSentence.gridx = 2;
		gbc_textSentence.gridy = 4;
		frmModifySentence.getContentPane().add(textSentence, gbc_textSentence);
	
		JButton btnBack = new JButton("INDIETRO");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmModifySentence.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 5;
		frmModifySentence.getContentPane().add(btnBack, gbc_btnBack);
		
		JButton btnModify = new JButton("CONFERMA");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sentence nsent=new Sentence(textSentence.getText().toString(),textArg.getText().toString());
				boolean c=Sentence.checkLength(nsent.theme);
				boolean b=Sentence.checkLength(nsent.sent);
				if(b==c==true) {
					//DummyClassGuiFunctions.NCFrase();
					prox.CFraseConf(nsent,i);
					/*try {
						ous.writeObject(Comands.CFRASE);
						ous.writeObject(i);
					} catch (IOException err) {
						err.printStackTrace();
					}
					*/
				System.out.println("Frase aggiunta");
				}
				else {
					textSentence.setText("");
					System.out.println("Frase troppo lunga");
				}
			}
		});
		GridBagConstraints gbc_btnModify = new GridBagConstraints();
		gbc_btnModify.insets = new Insets(0, 0, 0, 5);
		gbc_btnModify.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnModify.gridx = 3;
		gbc_btnModify.gridy = 5;
		frmModifySentence.getContentPane().add(btnModify, gbc_btnModify);
		frmModifySentence.show();
	}

}