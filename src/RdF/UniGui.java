package RdF;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class UniGui 
{
	JTextField text;
	JButton ok;
	private JFrame uniframe;
	GridBagLayout gridBagLayout;
	
	public static void main(String[] args)throws IOException
	{
		new UniGui();
	}
	
	public UniGui() 
	{
		uniframe = new JFrame();
		uniframe.setBackground(Color.BLUE);
		uniframe.getContentPane().setBackground(Color.WHITE);;
		uniframe.setTitle("universal frame");
		uniframe.setBounds(100, 100, 815, 486);
		uniframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 44, 29, 39, 96, 89, 91, 218, 77, 0 };
		gridBagLayout.rowHeights = new int[] { 49, 14, 23, 26, 96, 1, 46, 33, 23, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		uniframe.getContentPane().setLayout(gridBagLayout);
		uniframe.show();
		Text();
		BtnSolut();
		
	}
	
	private void Text() 
	{
		text = new JTextField("universal_frame");
		GridBagConstraints gbc_text = new GridBagConstraints();
		gbc_text.anchor = GridBagConstraints.NORTHWEST;
		gbc_text.insets = new Insets(0, 0, 5, 5);
		gbc_text.gridx = 1;
		gbc_text.gridy = 1;
		uniframe.getContentPane().add(text, gbc_text);
	}
	
	private void  BtnSolut() {
		ok = new JButton("SOLUZIONE");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.fill = GridBagConstraints.BOTH;
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridwidth = 1;
		gbc_btnOk.gridx = 6;
		gbc_btnOk.gridy = 6;
		uniframe.getContentPane().add(ok, gbc_btnOk);
		}
	
}
