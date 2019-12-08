package RdF;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class GuiProxtest extends Thread
{
	Comands Lcom;
	JTable tab;
	ProxyRdF prox;
	JLabel themelab,manchelab,text,pointslab;
	int manche;
	boolean yt;
	JButton solut,vow,conf,spin;
	JLabel[] ppos;
    public GuiProxtest(JTable table, ProxyRdF prox, JLabel labT,JLabel labM,JLabel text,JLabel[] lppos,JLabel labP)
	{
		pointslab=labP;
        ppos=lppos;
		yt=false;
        this.text=text;
		this.prox=prox;
		tab=table;
		themelab=labT;
		manchelab=labM;
		manche=0;
	}

	public GuiProxtest(JTable table, ProxyRdF prox, JLabel labT,JLabel labM,JLabel text,JLabel[] lppos,JLabel labP,JButton bsolut,JButton bvow,JButton bconf,JButton bspin)

	{
		pointslab=labP;
		ppos=lppos;
		this.text=text;
		solut=bsolut;
		vow=bvow;
		conf=bconf;
		spin=bspin;
		yt=false;
		this.prox=prox;
		tab=table;
		themelab=labT;
		manchelab=labM;
		manche=0;

	}
	
	public GuiProxtest(JTable table) 
	{
		tab=table;
	}
	
	public void run()
	{
		while(true) {
			Lcom = Comands.ACTIV;
			while (true) {
				//Lcom=prox.Threadcom();
				try {
					Lcom=ProxyRdF.getCombyCode(prox.Read());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println(Lcom+"guiprox");
				switch (Lcom) {
					case SPIN:
						String str="";
						str=prox.SpinRead();
						System.out.println(str);
						switch (str) {
							case "Perde":
							case "Passa":
								Loss(str);
								break;
							case "Jolly":
								Jolly();
								break;
							case "":
								break;
							default:
								Cons();
								break;
						}
						break;
					case YT:
						prox.setLccom(Lcom.getCode());
						manchelab.setText("YT");
						if(yt==false)
                        JOptionPane.showMessageDialog(null, "Your Turn");
						yt=true;
                        prox.setltime(System.currentTimeMillis());
						break;
					case VOWEL:
					case CONS:
						prox.lettobs(tab);
						break;
					case SOLUT:
						prox.SolutVisual(tab);
						break;
					case DISCONNECT:
						this.interrupt();
						break;
					case JOLLY:
						prox.setLccom(Lcom.getCode());
						prox.JollyPup();
						break;
					case END:
						prox.setLccom(Lcom.getCode());
						break;
					case NEW:
						prox.setLccom(Lcom.getCode());
						int temp=manche;
						manche=prox.GameStr(themelab,manchelab,ppos,pointslab,text);
						if(manche!=temp)
						{
							if(!prox.getObs())
							{
								vow.setEnabled(false);
								solut.setEnabled(false);
								conf.setEnabled(false);
								spin.setEnabled(true);
							}
							prox.setconse(false);
							for(int count=0;count<60;count++)
							tab.setValueAt("", count / 10, count % 10);
						}
						break;
					case DFRASE:
						if(!prox.getObs())
						spin.setEnabled(false);
						prox.setconse(true);
						break;
					case PING:
						break;
					default:
						break;

				}
			}
		}
	}

	private void Loss(String str)
	{
		JOptionPane.showMessageDialog(null, "è uscito "+str);
		yt=false;
	}

	private void Jolly()
	{
		JOptionPane.showMessageDialog(null, "hai guadagnato un jolly");
	}

	private void Cons()
	{
		System.out.println("AO");
		prox.setltime(System.currentTimeMillis());
		String dialogResult=JOptionPane.showInputDialog(null,"inserisci una consonante");
		if(!dialogResult.isEmpty())
		{
			prox.Confirm(24,dialogResult,tab);
		}
	}

	public boolean Ping()
	{
		boolean temp=false;
		return temp;
	}
	/*for(int i=0;i<10;i++)
			for(int j=0;j<6;j++)
			tab.setValueAt("GAY", j, i);
		for(int i=0;i<10;i++)
			for(int j=0;j<6;j++)
			tab.setValueAt(null, i, j);
			*/

}
