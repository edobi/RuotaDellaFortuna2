package GUI;
import Csv.*;
import RdF.ProxyRdF;
import RdF.Sentence;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ImpotSentences {

	private JFrame frmImportSentences;
	private JTextField textCsv;

	public ImpotSentences(ProxyRdF prox) {
		frmImportSentences = new JFrame();
		frmImportSentences.setTitle("RdF: IMPORT FILE CSV");
		frmImportSentences.setBackground(Color.BLUE);
		frmImportSentences.getContentPane().setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmImportSentences.getContentPane().setLayout(gridBagLayout);
//--------------------------------------------------------------------
		
		JLabel lblCsv = new JLabel("Inserisci percorso file cvs:");
		GridBagConstraints gbc_lblCsv = new GridBagConstraints();
		gbc_lblCsv.insets = new Insets(0, 0, 5, 5);
		gbc_lblCsv.gridx = 1;
		gbc_lblCsv.gridy = 1;
		frmImportSentences.getContentPane().add(lblCsv, gbc_lblCsv);
//-----------------------------------------------------------------------------	
		
		textCsv = new JTextField();
		GridBagConstraints gbc_textCsv = new GridBagConstraints();
		gbc_textCsv.insets = new Insets(0, 0, 5, 5);
		gbc_textCsv.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCsv.gridx = 1;
		gbc_textCsv.gridy = 2;
		frmImportSentences.getContentPane().add(textCsv, gbc_textCsv);
		textCsv.setColumns(10);
	//--------------------------------------------------------------------------
		
		JButton btnCsv = new JButton("INVIA");
		btnCsv.addActionListener(new ActionListener() {
			private int i;

			public void actionPerformed(ActionEvent e) {
			String filePath;
				JFileChooser fc = new JFileChooser();
				  int returnVal = fc.showOpenDialog(fc);
				  if(returnVal == JFileChooser.APPROVE_OPTION) {
					   filePath=fc.getSelectedFile().getPath();
					   System.out.println(filePath);
					   if(filePath.endsWith(".csv")) {
						   ArrayList<Sentence> arraySentences=new ArrayList<Sentence>();
						   arraySentences=ReadCsv.importSentences(filePath); 
							if(arraySentences!=null) {
					    		//for(i=0;i<arraySentences.size();i++) {
					    			//System.out.println(arraySentences.get(i));
					    		//}
								prox.CsVSentTrans(arraySentences);
				    	}
					   }else {
						   JOptionPane.showMessageDialog(null,"File non accettato");
					   }
				  }else{
					  JOptionPane.showMessageDialog(null,"Operazione annullata");
            }
		}
		});
		GridBagConstraints gbc_btnCsv = new GridBagConstraints();
		gbc_btnCsv.insets = new Insets(0, 0, 0, 5);
		gbc_btnCsv.gridx = 1;
		gbc_btnCsv.gridy = 4;
		frmImportSentences.getContentPane().add(btnCsv, gbc_btnCsv);
		frmImportSentences.setBounds(100, 100, 450, 300);
		frmImportSentences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//--------------------------------------------------------------------------
		frmImportSentences.show();
		
	}

}