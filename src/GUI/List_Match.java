package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import RdF.ProxyRdF;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

public class List_Match {

	private JFrame frame;
	JList listArea;
	public List_Match(boolean bool, ProxyRdF prox) throws ClassNotFoundException, IOException
	{

	        JPanel panel = new JPanel(new BorderLayout());
	        //ArrayList<String> labels =new ArrayList<String>();
		String[]labels=new String[0];
	       /* HashMap<String, Lobby> hm= DummyClassGuiFunctions.View();
	        labels.addAll(hm.values());*/
	        
//--------------------------------------------------------
	        JFrame frmListSentence = new JFrame("Partite disponibili");
	        frmListSentence.setSize(new Dimension(400, 400));
	        frmListSentence.setAlwaysOnTop(true);
	        frmListSentence.setBackground(Color.BLUE);
	        frmListSentence.setTitle("RdF: Partite disponibili");
	        frmListSentence.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frmListSentence.getContentPane().add(panel);
	        frmListSentence.setBounds(100, 100, 447, 365);
	        frmListSentence.setLocationRelativeTo(null);
	        frmListSentence.setVisible(true);

//--------------------------------------------------------
	        	try {
			        labels= prox.View(bool);//prendo dal server la lista dei match disponibili
			        //listArea = new JList(labels.toArray(new String[labels.size()]));
					listArea = new JList(labels);
			        listArea.setVisibleRowCount(10);
			        listArea.setBackground(Color.WHITE);
			        listArea.addMouseListener(new MouseAdapter() {
			        	@Override
			        	public void mouseClicked(MouseEvent e) {
			        		String selected;
			        		//dati match preso .getSelectedValue();
			        		if(bool==true)
			        		{
			        			selected=(String) listArea.getSelectedValue();
			        			try {
									prox.JoinGame(selected, bool);
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
			        		SecondMatch m= new SecondMatch(prox);//inserire i dati della partita
			        		frmListSentence.setVisible(false);
			        		}
			        		else
			        		{
			        			selected=(String) listArea.getSelectedValue();
			        			try {
									prox.JoinGame(selected, bool);
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
			        			Obs o = new Obs(prox);//inserire i dati della partita
				        		frmListSentence.setVisible(false);
			        		}
			        	}
			        });
	        	}catch(Exception e) {e.printStackTrace();}

	        listArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        listArea.setFont(new Font("Serif", Font.ITALIC, 14));
	        listArea.setLayoutOrientation(JList.VERTICAL);
//--------------------------------------------------------
	        JScrollPane listScroller = new JScrollPane();
	        listScroller.setViewportView(listArea);
	        panel.add(listScroller);
	       
//--------------------------------------------------------	        
	        JButton btnBack = new JButton("INDIETRO");
	        btnBack.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		 frmListSentence.setVisible(false);
	        	}
	        });
	        listScroller.setColumnHeaderView(btnBack);
	        
			frmListSentence.show();
		}
}

