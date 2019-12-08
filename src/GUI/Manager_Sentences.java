package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Csv.ReadCsv;
import RdF.Comands;
import RdF.ProxyRdF;
import RdF.Sentence;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class Manager_Sentences {
	
	//private ObjectOutputStream ous;
	private static JFrame frmRdfMenFrasi;
	static String  str;
//public static void main(String[] args){	
	public Manager_Sentences(ProxyRdF prox) {
        ArrayList<String> labels = new ArrayList<>(25);
        for (int index = 0; index < 100; index++) {
            labels.add("Frase " + index);
        }
		frmRdfMenFrasi = new JFrame();
		frmRdfMenFrasi.setBackground(Color.BLUE);
		frmRdfMenFrasi.getContentPane().setBackground(Color.WHITE);
		frmRdfMenFrasi.setTitle("RdF: Men\u00F9 Frasi");
		frmRdfMenFrasi.setBounds(100, 100, 436, 220);
		frmRdfMenFrasi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 289, 60, 0};
		gridBagLayout.rowHeights = new int[]{23, 23, 23, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmRdfMenFrasi.getContentPane().setLayout(gridBagLayout);
		

//--------------------------------------------------------
		JButton btnModificaFrase = new JButton("MODIFICA FRASE");
		btnModificaFrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str="Modifica";
				Select_Sentence ss= new Select_Sentence(str,prox);
			}
		});	
		GridBagConstraints gbc_btnModificaFrase = new GridBagConstraints();
		gbc_btnModificaFrase.insets = new Insets(0, 0, 5, 5);
		gbc_btnModificaFrase.anchor = GridBagConstraints.NORTH;
		gbc_btnModificaFrase.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModificaFrase.gridx = 1;
		gbc_btnModificaFrase.gridy = 3;
		frmRdfMenFrasi.getContentPane().add(btnModificaFrase, gbc_btnModificaFrase);

//--------------------------------------------------------		
		JButton btnEliminaFrase = new JButton("ELIMINA FRASE");
		btnEliminaFrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				str="Elimina";
				Select_Sentence ss= new Select_Sentence(str,prox);
			}
		});
		GridBagConstraints gbc_btnEliminaFrase = new GridBagConstraints();
		gbc_btnEliminaFrase.anchor = GridBagConstraints.NORTH;
		gbc_btnEliminaFrase.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEliminaFrase.insets = new Insets(0, 0, 5, 5);
		gbc_btnEliminaFrase.gridx = 1;
		gbc_btnEliminaFrase.gridy = 2;
		frmRdfMenFrasi.getContentPane().add(btnEliminaFrase, gbc_btnEliminaFrase);

//--------------------------------------------------------		
		JButton btnInserisciFrase = new JButton("INSERISCI FRASE");
		btnInserisciFrase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Sentence aS= new Add_Sentence(prox);
			}
		});
		GridBagConstraints gbc_btnInserisciFrase = new GridBagConstraints();
		gbc_btnInserisciFrase.anchor = GridBagConstraints.NORTH;
		gbc_btnInserisciFrase.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInserisciFrase.insets = new Insets(0, 0, 5, 5);
		gbc_btnInserisciFrase.gridx = 1;
		gbc_btnInserisciFrase.gridy = 1;
		frmRdfMenFrasi.getContentPane().add(btnInserisciFrase, gbc_btnInserisciFrase);

//--------------------------------------------------------------------
		JButton btnImportaDaFile = new JButton("IMPORTA DA FILE CSV ");
		btnImportaDaFile.addActionListener(new ActionListener() {
			int i;
			public void actionPerformed(ActionEvent e) {
				String filePath;
				JFileChooser fc = new JFileChooser();// la directory di default � Documents
				  int returnVal = fc.showOpenDialog(null);// apre finestra modale 
				  
				  if(returnVal == JFileChooser.APPROVE_OPTION) {//  Il valore restituito se approvato (s�, ok) � scelto
					   filePath=fc.getSelectedFile().getPath();//ottendo path file
					   
					   if(filePath.endsWith(".csv")) {//controllo se il file � csv. Se si importo altrimenti il file non � in cvs e non puo essere utilizzato
						   ArrayList<Sentence> arraySentences=new ArrayList<Sentence>();
						   arraySentences=ReadCsv.importSentences(filePath); 
						   System.out.println(arraySentences.size());
							if(arraySentences.size()>0) {//se array contiene qualcosa trasfersci. Altrimenti vuoto
					    		for(i=0;i<arraySentences.size();i++) {
					    			//System.out.println(arraySentences.get(i));//Vladi qui lavora te******************************************************
									prox.CsVSentTrans(arraySentences);
						    		}
					    	}else {
					    		JOptionPane.showMessageDialog(null,"File vuoto");
					    	}
							
					   }else {
						   JOptionPane.showMessageDialog(null,"File non compatibile");
					   }
					   
				  }else{
					  JOptionPane.showMessageDialog(null,"Operazione annullata");
            }
			}
		});
		GridBagConstraints gbc_btnImportaDaFile = new GridBagConstraints();
		gbc_btnImportaDaFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnImportaDaFile.insets = new Insets(0, 0, 0, 5);
		gbc_btnImportaDaFile.gridx = 1;
		gbc_btnImportaDaFile.gridy = 4;
		frmRdfMenFrasi.getContentPane().add(btnImportaDaFile, gbc_btnImportaDaFile);

//--------------------------------------------------------
		frmRdfMenFrasi.show();
	}
}