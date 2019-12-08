package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import RdF.ProxyRdF;
import RdF.Sentence;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Add_Sentence {

	private JFrame frmAggiungiFrase;
	private JTextField textSentence;
	private JTextField textArgument;
	//private ObjectInputStream ins;
	//private ObjectOutputStream ous;
	String sent, arg;
	public Add_Sentence(ProxyRdF prox)
	{
		
		frmAggiungiFrase = new JFrame();
		frmAggiungiFrase.setBackground(Color.BLUE);
		frmAggiungiFrase.getContentPane().setBackground(Color.WHITE);
		frmAggiungiFrase.setTitle("RdF: AGGIUNGI FRASE");
		frmAggiungiFrase.setBounds(100, 100, 831, 328);
		frmAggiungiFrase.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{34, 132, 81, 83, 459, 0};
		gridBagLayout.rowHeights = new int[]{46, 14, 26, 26, 23, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmAggiungiFrase.getContentPane().setLayout(gridBagLayout);
		
//--------------------------ARGOMENTO----------------------------		
		JLabel lblArgument = new JLabel("Aggiungi argomento");
		GridBagConstraints gbc_lblArgument = new GridBagConstraints();
		gbc_lblArgument.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_lblArgument.insets = new Insets(0, 0, 5, 5);
		gbc_lblArgument.gridx = 1;
		gbc_lblArgument.gridy = 2;
		frmAggiungiFrase.getContentPane().add(lblArgument, gbc_lblArgument);
		
		textArgument = new JTextField();
		GridBagConstraints gbc_textArgument = new GridBagConstraints();
		gbc_textArgument.gridwidth = 3;
		gbc_textArgument.insets = new Insets(0, 0, 5, 0);
		gbc_textArgument.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArgument.gridx = 2;
		gbc_textArgument.gridy = 2;
		frmAggiungiFrase.getContentPane().add(textArgument, gbc_textArgument);
		textArgument.setColumns(10);
		arg= textArgument.getText();
//-----------------------FRASE-------------------------------------
		JLabel lblAddSentence = new JLabel("Aggiungi frase");
		GridBagConstraints gbc_lblAddSentence = new GridBagConstraints();
		gbc_lblAddSentence.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblAddSentence.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddSentence.gridx = 1;
		gbc_lblAddSentence.gridy = 4;
		frmAggiungiFrase.getContentPane().add(lblAddSentence, gbc_lblAddSentence);
		
		textSentence = new JTextField();
		textSentence.setColumns(10);
		GridBagConstraints gbc_textSentence = new GridBagConstraints();
		gbc_textSentence.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSentence.anchor = GridBagConstraints.NORTH;
		gbc_textSentence.insets = new Insets(0, 0, 5, 0);
		gbc_textSentence.gridwidth = 4;
		gbc_textSentence.gridx = 1;
		gbc_textSentence.gridy = 5;
		frmAggiungiFrase.getContentPane().add(textSentence, gbc_textSentence);
		sent= textSentence.getText();
		
//-----------------------BOTTONE----------------------------
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addActionListener(new ActionListener() {
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
				arg=textArgument.getText();
				sent=textSentence.getText();
				boolean b=Sentence.checkLength(sent);
				if(b==true) {
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
					if((sent!="")&&(arg!=""))
					{
					Sentence nsent=new Sentence(sent,arg);
					prox.NCFrase(nsent);
					}
				}
				else {
					textSentence.setText("");
					System.out.println("Frase troppo lunga");
				}
			}
		});
//------------------BACK-----------------------		
		JButton btnIndietro = new JButton("INDIETRO");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAggiungiFrase.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnIndietro = new GridBagConstraints();
		gbc_btnIndietro.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnIndietro.insets = new Insets(0, 0, 5, 5);
		gbc_btnIndietro.gridx = 3;
		gbc_btnIndietro.gridy = 8;
		frmAggiungiFrase.getContentPane().add(btnIndietro, gbc_btnIndietro);
		GridBagConstraints gbc_btnAggiungi = new GridBagConstraints();
		gbc_btnAggiungi.insets = new Insets(0, 0, 5, 0);
		gbc_btnAggiungi.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnAggiungi.gridx = 4;
		gbc_btnAggiungi.gridy = 8;
		frmAggiungiFrase.getContentPane().add(btnAggiungi, gbc_btnAggiungi);
		frmAggiungiFrase.show();
	}
}