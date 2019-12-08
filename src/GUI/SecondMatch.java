package GUI;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import RdF.Comands;
import RdF.GuiProxtest;
import RdF.ProxyRdF;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class SecondMatch {

	private JFrame windowMatch;
	private JTable table;
	private JTextField textFieldAnsware;
	JButton AnotherSpinButton;
	JButton confirmButton;
	JButton vowelButton;
	JButton solutionButton;
	JButton spinButton;
	JLabel lblChoose;
	JLabel lblText;
    JLabel lblTheme2,lblNumManche;
    JLabel lblPoints;
	boolean solution=false;
	JLabel[] ppos;
	//private ObjectInputStream ins;
	//private static ObjectOutputStream ous;
	private ProxyRdF prox;
	private Comands com;
	//private int ccom;
	Long clock;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondMatch window = new SecondMatch();
					//window.windowMatch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	public SecondMatch(ProxyRdF pro) 
	{
		ppos=new JLabel[3];
        com=Comands.END;
		prox=pro;
		initialize();
		prox.setLccom(com.getCode());
		prox.setObs(false);
		new Thread(new GuiProxtest(this.table, prox,lblTheme2,lblNumManche,lblText,ppos,lblPoints,solutionButton,vowelButton,confirmButton,spinButton)).start();
		//this.windowMatch.setVisible(true);
	}
	public SecondMatch() 
	{
		initialize();
		//new Thread(new GuiProxtest(this.table)).start();
		//new Thread(new GuiProxtest(this.table, prox, com)).start();
		//this.windowMatch.setVisible(true);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		windowMatch = new JFrame();
		windowMatch.setTitle("RUOTA DELLA FORTUNA");
		windowMatch.getContentPane().setEnabled(false);
		windowMatch.getContentPane().setBackground(Color.WHITE);
		windowMatch.setBounds(100, 100, 1200, 499);
		windowMatch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{139, 255, 50, 378, 108, 130, 200, 0};
		gridBagLayout.rowHeights = new int[]{63, 100, 42, 42, 41, 40, 40, 40, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		windowMatch.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTheme = new JLabel("ARGOMENTO:");
		GridBagConstraints gbc_lblTheme = new GridBagConstraints();
		gbc_lblTheme.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblTheme.insets = new Insets(0, 0, 5, 5);
		gbc_lblTheme.gridx = 1;
		gbc_lblTheme.gridy = 0;
		windowMatch.getContentPane().add(lblTheme, gbc_lblTheme);
		
		lblTheme2 = new JLabel("//Argomento");
		GridBagConstraints gbc_lblTheme2 = new GridBagConstraints();
		gbc_lblTheme2.gridwidth = 2;
		gbc_lblTheme2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblTheme2.insets = new Insets(0, 0, 5, 5);
		gbc_lblTheme2.gridx = 2;
		gbc_lblTheme2.gridy = 0;
		windowMatch.getContentPane().add(lblTheme2, gbc_lblTheme2);
		
		JLabel lblManche = new JLabel("Manche");
		GridBagConstraints gbc_lblManche = new GridBagConstraints();
		gbc_lblManche.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblManche.insets = new Insets(0, 0, 5, 5);
		gbc_lblManche.gridx = 4;
		gbc_lblManche.gridy = 0;
		windowMatch.getContentPane().add(lblManche, gbc_lblManche);
		
		lblNumManche = new JLabel("//numManche");
		GridBagConstraints gbc_lblNumManche = new GridBagConstraints();
		gbc_lblNumManche.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNumManche.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumManche.gridx = 5;
		gbc_lblNumManche.gridy = 0;
		windowMatch.getContentPane().add(lblNumManche, gbc_lblNumManche);

        JLabel lblname = new JLabel(prox.getNick()+" Punti:");
        lblname.setVisible(true);
        GridBagConstraints gbc_lblname = new GridBagConstraints();
        gbc_lblname.anchor = GridBagConstraints.SOUTHWEST;
        gbc_lblname.insets = new Insets(0, 0, 5, 5);
        gbc_lblname.gridx = 3;
        gbc_lblname.gridy = 4;
        windowMatch.getContentPane().add(lblname, gbc_lblname);

		lblPoints = new JLabel("");
		lblPoints.setVisible(true);
		GridBagConstraints gbc_lblpoints = new GridBagConstraints();
		gbc_lblpoints.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblpoints.insets = new Insets(0, 0, 5, 5);
		gbc_lblpoints.gridx = 3;
		gbc_lblpoints.gridy = 5;
		windowMatch.getContentPane().add(lblPoints, gbc_lblpoints);
		
		lblText = new JLabel("temp");
		lblText.setVisible(true);
		GridBagConstraints gbc_lblText = new GridBagConstraints();
		gbc_lblText.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblText.insets = new Insets(0, 0, 5, 5);
		gbc_lblText.gridx = 3;
		gbc_lblText.gridy = 6;
		windowMatch.getContentPane().add(lblText, gbc_lblText);
		
		textFieldAnsware = new JTextField();
		textFieldAnsware.setVisible(false);
		GridBagConstraints gbc_textFieldAnsware = new GridBagConstraints();
		gbc_textFieldAnsware.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAnsware.fill = GridBagConstraints.BOTH;
		gbc_textFieldAnsware.gridx = 3;
		gbc_textFieldAnsware.gridy = 7;
		windowMatch.getContentPane().add(textFieldAnsware, gbc_textFieldAnsware);
		textFieldAnsware.setColumns(10);


		
		table = new JTable();
		table.setEnabled(false);
		table.setSize(10, 6);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(false);
		table.setBackground(Color.LIGHT_GRAY);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setModel(new DefaultTableModel(
				new String[][] { { null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column", "New column", "New column",
								"New column", "New column", "New column", "New column" }));
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 4;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		windowMatch.getContentPane().add(table, gbc_table);
		
		JLabel lblRanking = new JLabel("Classifica");
		GridBagConstraints gbc_lblRanking = new GridBagConstraints();
		gbc_lblRanking.anchor = GridBagConstraints.SOUTH;
		gbc_lblRanking.insets = new Insets(0, 0, 5, 5);
		gbc_lblRanking.gridx = 5;
		gbc_lblRanking.gridy = 2;
		windowMatch.getContentPane().add(lblRanking, gbc_lblRanking);

		for(int i=0;i<3;i++)
		{
			int x=5;
			int y=3;
			JLabel temp = new JLabel("");
			GridBagConstraints gtemp = new GridBagConstraints();
			gtemp.anchor = GridBagConstraints.SOUTH;
			gtemp.insets = new Insets(0, 0, 5, 5);
			gtemp.gridx = x;
			gtemp.gridy = y+i;
			ppos[i]=temp;
			windowMatch.getContentPane().add(temp, gtemp);
		}

		spinButton = new JButton("GIRA LA RUOTA");
		spinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//metodo gira la ruota
                System.out.println(com);
                com=ProxyRdF.getCombyCode(prox.getLccom());
				if((com==Comands.YT)) {
					System.out.println(com);
					JOptionPane.showMessageDialog(null, "Gira la ruota");
					com=Comands.SPIN;
					prox.setLccom(com.getCode());
					prox.Confirm(prox.getLccom(),"",table);
					//spinButton.setEnabled(false);
					lblChoose.setVisible(true);
					lblText.setVisible(true);
					confirmButton.setVisible(true);
					confirmButton.setEnabled(true);
					vowelButton.setVisible(true);
					vowelButton.setEnabled(true);
					solutionButton.setVisible(true);
					solutionButton.setEnabled(true);
					//AnotherSpinButton.setVisible(true);;
					//solutionButton.setVisible(true);;
					//vowelButton.setVisible(true);;
				}
			}
		});
		GridBagConstraints gbc_spinButton = new GridBagConstraints();
		gbc_spinButton.fill = GridBagConstraints.BOTH;
		gbc_spinButton.insets = new Insets(0, 0, 5, 5);
		gbc_spinButton.gridx = 1;
		gbc_spinButton.gridy = 3;
		windowMatch.getContentPane().add(spinButton, gbc_spinButton);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 2;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 5;
		gbc_list.gridy = 3;
		windowMatch.getContentPane().add(list, gbc_list);
		
		lblChoose = new JLabel("SCEGLI TRA");
		lblChoose.setVisible(false);
		GridBagConstraints gbc_lblChoose = new GridBagConstraints();
		gbc_lblChoose.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblChoose.insets = new Insets(0, 0, 5, 5);
		gbc_lblChoose.gridx = 0;
		gbc_lblChoose.gridy = 4;
		windowMatch.getContentPane().add(lblChoose, gbc_lblChoose);
		
		AnotherSpinButton = new JButton("GIRA LA RUOTA");
		AnotherSpinButton.setVisible(false);
		AnotherSpinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//metodo gira la ruota
				com=prox.getCombyCode(prox.getLccom());
				if(com.equals(Comands.YT)&&(!prox.getconse()))
				{
				com=Comands.SPIN;
				prox.setLccom(com.getCode());
				prox.Confirm(prox.getLccom(),"",table);
				JOptionPane.showMessageDialog(null, "Gira la ruota");
				spinButton.setEnabled(true);
				//AnotherSpinButton.setVisible(false);;
				//solutionButton.setVisible(false);;
				//vowelButton.setVisible(false);;
				}
			}
		});
		GridBagConstraints gbc_AnotherSpinButton = new GridBagConstraints();
		gbc_AnotherSpinButton.fill = GridBagConstraints.BOTH;
		gbc_AnotherSpinButton.insets = new Insets(0, 0, 5, 5);
		gbc_AnotherSpinButton.gridx = 1;
		gbc_AnotherSpinButton.gridy = 5;
		windowMatch.getContentPane().add(AnotherSpinButton, gbc_AnotherSpinButton);
		
		vowelButton = new JButton("COMPRA VOCALE");
		vowelButton.setVisible(false);
		vowelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//metodo chiamata vocale
				com=prox.getCombyCode(prox.getLccom());
				if(com.equals(Comands.YT))
				{
				com=Comands.VOWEL;
				prox.setLccom(com.getCode());
				//JOptionPane.showMessageDialog(null, "Compra vocale");
				//lblChoose.setVisible(true);
				//lblText.setVisible(true);
				confirmButton.setVisible(true);
				//textFieldAnsware.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_vowelButton = new GridBagConstraints();
		gbc_vowelButton.fill = GridBagConstraints.BOTH;
		gbc_vowelButton.insets = new Insets(0, 0, 5, 5);
		gbc_vowelButton.gridx = 1;
		gbc_vowelButton.gridy = 6;
		windowMatch.getContentPane().add(vowelButton, gbc_vowelButton);
		
		solutionButton = new JButton("SOLUZIONE");
		solutionButton.setVisible(false);
		solutionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//metodo soluzione
				com=prox.getCombyCode(prox.getLccom());
				if(com.equals(Comands.YT))
				{
				com=Comands.SOLUT;
				prox.setLccom(com.getCode());
				//JOptionPane.showMessageDialog(null, "Dai soluzione");
				//lblChoose.setVisible(true);
				//lblText.setVisible(true);
				//textFieldAnsware.setVisible(true);
				confirmButton.setVisible(true);
				solution=true;
				}
			}
		});
		GridBagConstraints gbc_solutionButton = new GridBagConstraints();
		gbc_solutionButton.fill = GridBagConstraints.BOTH;
		gbc_solutionButton.insets = new Insets(0, 0, 5, 5);
		gbc_solutionButton.gridx = 1;
		gbc_solutionButton.gridy = 7;
		windowMatch.getContentPane().add(solutionButton, gbc_solutionButton);
		
		confirmButton = new JButton("CONFERMA");
		confirmButton.setVisible(false);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//metodo conferma 
				//prox.Confirm(prox.getLccom(),textFieldAnsware.getText().toUpperCase(),table);
				spinButton.setEnabled(false);
				vowelButton.setEnabled(false);
				solutionButton.setEnabled(false);
				confirmButton.setEnabled(false);
                if(com.equals(Comands.SOLUT)) {

                    prox.Confirm(prox.getLccom(), JOptionPane.showInputDialog(null, "insserisci la soluzione"), table);
                }
                if(com.equals(Comands.VOWEL)) {
                    prox.setltime(System.currentTimeMillis());
                    prox.Confirm(prox.getLccom(), JOptionPane.showInputDialog(null, "insserisci la vocale"), table);
                }
				spinButton.setEnabled(true);
				vowelButton.setEnabled(true);
				solutionButton.setEnabled(true);
				confirmButton.setEnabled(true);
				//lblChoose.setVisible(false);
				//lblText.setVisible(false);
				//textFieldAnsware.setVisible(false);
				//confirmButton.setVisible(false);
				AnotherSpinButton.setVisible(false);;
				//solutionButton.setVisible(false);;
				//vowelButton.setVisible(false);;
				System.out.println(solution);
				/*if (solution==true) {
					//soluzione
					String solutionText= textFieldAnsware.getText().toUpperCase();
					JOptionPane.showMessageDialog(null, "hai inserito la frase: "+solutionText);
					textFieldAnsware.setText(null);
				}else {
					//controllo della vocale
					
					char solutionTextch= textFieldAnsware.getText().toUpperCase().charAt(0);
					JOptionPane.showMessageDialog(null, "hai inserito: "+solutionTextch);
					textFieldAnsware.setText(null);
				}
				*/
			}
		});
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.insets = new Insets(0, 0, 5, 5);
		gbc_confirmButton.gridx = 4;
		gbc_confirmButton.gridy = 7;
		windowMatch.getContentPane().add(confirmButton, gbc_confirmButton);
		
		JButton leaveButton = new JButton("ABBANDONA");
		leaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//metodo abbandona
				//prox.Discon();
				prox.setLccom(Comands.DISCONNECT.getCode());
				prox.Confirm(prox.getLccom(),"", table);
			}
		});
		GridBagConstraints gbc_leaveButton = new GridBagConstraints();
		gbc_leaveButton.insets = new Insets(0, 0, 0, 5);
		gbc_leaveButton.gridx = 5;
		gbc_leaveButton.gridy = 8;
		windowMatch.getContentPane().add(leaveButton, gbc_leaveButton);
		windowMatch.addWindowListener(new java.awt.event.WindowAdapter() {
										  @Override
										  public void windowClosing(java.awt.event.WindowEvent windowEvent) {
											  try {
												  prox.close();
											  } catch (IOException e) {
												  e.printStackTrace();
											  }
										  }
									  });
		windowMatch.setVisible(true);
		windowMatch.show();
	}


}