package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import RdF.ProxyRdF;
import RdF.Sentence;

import java.awt.Color;

public class Select_Sentence {

	private JFrame frame;
	private ObjectInputStream ins;
	private ObjectOutputStream ous;
	
	public Select_Sentence(String str,ProxyRdF prox) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  	JPanel panel = new JPanel(new BorderLayout());
	        JFrame frmSelectSentences = new JFrame("Frasi");
	        frmSelectSentences.setBackground(Color.BLUE);
	        frmSelectSentences.setTitle("RdF: gestione Frasi");
	        frmSelectSentences.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frmSelectSentences.getContentPane().add(panel);
	        frmSelectSentences.pack();
	        frmSelectSentences.setBounds(100, 100, 447, 365);
	        frmSelectSentences.setLocationRelativeTo(null);
	        frmSelectSentences.setVisible(true);
			ArrayList<Sentence> labels= prox.loadSentence(); //DummyClassGuiFunctions.loadSentences();
	        final JList listArea = new JList(Listreturn(labels));
	        listArea.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		String selected = (String)listArea.getSelectedValue();
	        		int ind=listArea.getSelectedIndex();
	        		System.out.println(ind);
	        		if(str.equals("Elimina")) {
						/*-------------------*/
						int dialogButton = JOptionPane.showConfirmDialog(null, "Vuoi eliminare la frase?");
						System.out.println(dialogButton);
						if (dialogButton == 0) {
							//ous.writeObject(Comands.DFRASE);
							//ous.writeObject(selected.sent);
							prox.DFraseConf(selected);
							labels.remove(selected);
							//JOptionPane.showInternalMessageDialog(null, "Frase eliminata");
							frmSelectSentences.setVisible(false);
						}
					}
	        		if(str.equals("Modifica")) {
	        		Modify_Sentence ms= new Modify_Sentence(labels.get(ind),prox);}

	        	}});
	        listArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        listArea.setFont(new Font("Serif", Font.ITALIC, 14));
	        JScrollPane listScroller = new JScrollPane();
	        listScroller.setViewportView(listArea);
	        listArea.setLayoutOrientation(JList.VERTICAL);
	        panel.add(listScroller);
	       
	        
	        JButton btnBack = new JButton("INDIETRO");
	        btnBack.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		 frmSelectSentences.setVisible(false);
	        	}
	        });
	        listScroller.setColumnHeaderView(btnBack);
	        
			frmSelectSentences.show();
	}

	private String[] Listreturn(ArrayList<Sentence> labels)
	{
		int size=labels.size();
		String[] temp=new String[size];
		for(int i=0;i<size;i++)
		{
			temp[i]=labels.get(i).sent;
		}
		return temp;
	}
}