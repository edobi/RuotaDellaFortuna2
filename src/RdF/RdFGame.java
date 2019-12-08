package RdF;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import javax.swing.JOptionPane;
import javax.swing.JTable;

//import soluzione.GUI.Choice;
//import soluzione.GUI.Server;



public class RdFGame implements Runnable
{
	String[] cp = {"300","400","500","600","700","800","900","1000","Jolly","Passa","Perde"};//cp(8) Jolly | cp(9) Passa | cp(10) Perde
	//ServerSocket serv;
	//int port;
	//Long time;
	//String[] playerName=new String[3];
	//int[] point= {0,0,0};
	//ArrayList<PlayOss> osser=new ArrayList<PlayOss>();
	//PlayOss[] players=new PlayOss[3];
	//int count=0;
	HashMap<String,Integer>ppos;
	RdfGamePOC poc;
	ArrayList<Integer> usablefrase;
	Sentence[] frase;
	String solution=new String();
	Random random=new Random();
	Map<Character,ArrayList<Integer>> frasemap;
	HashMap<Character,Boolean> lettlist;
	ConnectProp conn;
	String matchname;
	int idmatch,manche,consn;
	boolean solut=false;
	Comands com=null;
	String move;
	int j;
	char ch='?';

	public RdFGame(int port,Lobby lobby,ConnectProp db, String name) throws IOException
	{
		matchname=name;
		conn=db;
		System.out.println(db);
		poc=new RdfGamePOC();
		new Thread(new RdfGSkele(port,poc)).start();

	}

	@Override
	public void run() {
		do
		{
			if(poc.count>0)
			{
				for(PlayOss pl:poc.players)
				{
					try {
						if(!conn.Ping(pl.oos))
							poc.count--;
					}catch(NullPointerException e)
					{

					}
				}
				if(poc.count==0)
				{poc.count=-1;}
			}
		}
		while(poc.count<3&&poc.count>=0);
		if(!(poc.count<3)) {
			ppos=new HashMap<String,Integer>();
			for(PlayOss p:poc.players)
				ppos.put(p.name,p.points);
			conn.Osslist.put(matchname, conn.lobblist.get(matchname));
			conn.lobblist.remove(matchname);
			for (int i = 0; i < 3; i++)
				poc.players[i].id = conn.dbcon.getIdUserByNick(poc.players[i].name);
			idmatch = conn.dbcon.createMatch(poc.players[0].id, poc.players[1].id, poc.players[2].id, matchname);
			//while(poc.count==3)
			//{
			j = ThreadLocalRandom.current().nextInt(0, 3); //0 min 3 max
			usablefrase = getSentenceForMatch(idmatch);
			frase = new Sentence[5];
			for (int i = 0; i < 5; i++) {
				ArrayList<Integer> l = new ArrayList<Integer>();
				int c = 0;
				while (c < 5) {
					int k = ThreadLocalRandom.current().nextInt(0, usablefrase.size());

					if (!l.contains(k)) {
						l.add(k);
						frase[i] = conn.dbcon.getSentence(usablefrase.get(k));
						c++;
					}
				}
			}
			for (manche = 0; manche < 5; manche++)  //Manche Loop Start
			{
				consn = 0;
				lettlist = new HashMap<Character, Boolean>();
				frasemap = FraseAnalysis(frase[manche].sent);
				solut = false;
				com = Comands.NEW;
				//System.out.println(com.getCode());
				int temp = com.getCode();
				//System.out.println(temp);
				visual(frase[manche].sent, frase[manche].theme, temp);
				do {
					try {
						poc.players[j].oos.writeObject(Comands.YT);
						System.out.println(Comands.YT);
						turn(j);
						visual(frase[manche].sent, frase[manche].theme, com.getCode());
						if(poc.count<3)
							break;
						com = Comands.NEW;
						visual(frase[manche].sent, frase[manche].theme, com.getCode());
						move="";
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (!solut);
				if(poc.count<3)
					break;
				conn.dbcon.insertManche(idmatch, manche);
				Endturn(j);
				for (PlayOss p : poc.players)
					p.points = p.matchpoi;
			}
			PlayOss pla = poc.players[0];
			for (PlayOss pl : poc.players) {
				if (pla.points < pl.points)
					pla = pl;
			}
			if(poc.count>3)
			conn.dbcon.updateMatchWinner(idmatch, pla.id);
			//}
		}
		if(conn.Osslist.containsKey(matchname))
			conn.Osslist.remove(matchname);
		else if (conn.lobblist.containsKey(matchname))
			conn.lobblist.remove(matchname);
	}

	private void visual(String s,String t,int co)
	{
		//System.out.println(co);

		for(int c=0;c<3;c++)
		{
			if(!conn.Ping(poc.players[c].oos))
				poc.count--;
		}
		if(poc.count<3)
			co=Comands.DISCONNECT.getCode();
			for(int c=0;c<3;c++)
				new Visualizer(poc.players[c].oos, s, t, co, frasemap.get(ch), manche, ch, solut,ppos,poc.players[c].points,move).run();
			for (int c = 0; c < poc.osser.size(); c++) {
				System.out.println("obs");
				new Thread(new Visualizer(poc.osser.get(c).oos, s, t, co, frasemap.get(ch), manche, ch, solut,ppos,0,move)).start();
			}

	}

	private void turn(int i)
	{
		Comands com= null;
		try {
			com = (Comands) poc.players[j].ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String str="";
		System.out.println(com);
		switch(com)
		{
		case SPIN:
			str=spint(i);
			move="Gira la ruota: "+str;
			try
			{
				System.out.println(str+"p");
				poc.players[i].oos.writeObject(com);
				Thread.sleep(200);
				poc.players[i].oos.writeObject(str);
				System.out.println(str+"d");
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			switch(str)
			{
			case "Perde":
				//conn.dbcon.executeMove(poc.players[i].id,manche,idmatch,str,);
				Endturn(i);
			break;
			case "Passa":
				EndJturn(i);
			break;
			case "Jolly":
				poc.players[i].jolly++;
				conn.dbcon.executeMove(poc.players[i].id,manche,idmatch,"spin",poc.players[i].points);
				break;
			default:
				try {
					System.out.println("Spin points");
					if(poc.players[i].ois.readObject().equals(Comands.CONS))
					consonant(i,str);
					else
					EndJturn(i);
				} catch (ClassNotFoundException | IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				conn.dbcon.executeMove(poc.players[i].id, manche, idmatch,"spin",poc.players[i].points);
				break;
			}
			break;
		case SOLUT:
			move="Soluzione: ";
			try {
				//poc.players[i].oos.writeObject(Comands.YT);
				str=(String) poc.players[i].ois.readObject();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str=str.toLowerCase();
			visual(str,"",com.getCode());
			conn.dbcon.executeMove(poc.players[i].id, manche, idmatch,"Solut",poc.players[i].points);
			System.out.println(frase[manche].sent);
			if(str.equals(frase[manche].sent.toLowerCase()))
			{
				System.out.println(str);
				solut=true;
				move=move+str;
				poc.players[i].matchpoi+=poc.players[i].points;
				System.out.println(solut);
			}
			else
			{
				EndJturn(i);
			}
			break;
		case VOWEL:
			char c='?';
			move="Compra vocale: ";
			try {
				c= (char) poc.players[i].ois.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if(poc.players[i].points>1000)
			{
				poc.players[i].points-=1000;
				if(isVowel(c))
				{
					move=move+c;
					if(lettlist.containsKey(c))
					if(!lettlist.get(c))
					{
						ch=c;
						lettlist.replace(c,true);
						this.com=Comands.VOWEL;
						String temp=c+"";
						//visual(temp,"",com.getCode());
					}
					/*try {
						ch=c;
						lettlist.replace(c,true);
						String temp=c+"";
						visual(temp,"",com.getCode());
						//poc.players[i].oos.writeBoolean(true);
						//poc.players[i].oos.writeObject(frasemap.get(c));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};

					 */
				}
				else
				{
					EndJturn(i);
				}
				conn.dbcon.executeMove(poc.players[i].id, manche, idmatch,"Vowel",poc.players[i].points);
			}
			else
				EndJturn(i);
			break;
		default:
			EndJturn(i);
			break;
		}
	}

	private String spint(int npl)
	{
		int spin = RdFGame.spin();
		String premio = cp[spin];
		//move = new Move(1, 2, 3, 800, "Chiama la consonante");
		//Observer.addMove(move);
		return premio;
	}

	private void consonant(int npl, String str)
	{
		char c='?';
		try {
			 c=(char)poc.players[npl].ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!isVowel(c))
		{
			ch=c;
			move=move+" consonante: "+c;
			c=Character.toLowerCase(c);
			LettPos(c,npl,str,Comands.CONS.getCode());
		}
		else
			EndJturn(npl);
	}

	public static int spin() {//scelgo un numero casuale per il risultato dello spin
		int risultato;
		int max = 9;
        int min = 0;
     //  int range = max - min + 1;
	//	risultato= (int)(Math.random() * range) + min;
        risultato=ThreadLocalRandom.current().nextInt(min, max + 1);
		return risultato;
	}

	private static boolean  isVowel(char c)
	{
		/*
		 *
		 * if(pla.ppool>1000)
		 * {
		 * 		pla.ppool-=1000;
		 * 		switch(charing.touppercase())
		 * 		{
		 * 			case A:
		 * 			break;
		 * 			case E:
		 * 			break;
		 * 			case I:
		 * 			break;
		 * 			case O:
		 * 			break;
		 * 			case U:
		 * 			break;
		 * 			default:
		 * 		}
		 * }
		 *
		*/
			boolean b=false;
			c=Character.toUpperCase(c);
				if(c=='A' || c=='E'|| c=='I'|| c=='O'|| c=='U') {
					b=true;
				}
			return b;
		}

	private void EndJturn(int i)
	{
		if((poc.players[i].jolly>0))
		{
			boolean temp=false;
			try {
				poc.players[i].oos.writeObject(Comands.JOLLY);
				temp= (boolean) poc.players[i].ois.readObject();
				if(temp)
				{
					poc.players[i].jolly--;
					//poc.players[i].oos.writeObject(Comands.YT);
				}
				else
				{
					Endturn(i);
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			Endturn(i);
		}

	}

	private void Endturn(int i)
	{
		try {
			poc.players[i].oos.writeObject(Comands.END);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(i==2)
		{
			j=0;
			/*try {
				poc.players[i].oos.writeObject(Comands.YT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 */
		}
		else
		{
			j++;
			/*
			try {
				poc.players[i].oos.writeObject(Comands.YT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			 */
		}


	}

	private void isNewCons()
	{

	}

	private HashMap<Character, ArrayList<Integer>> FraseAnalysis(String fra)
	{
		char[] temp;
		System.out.println(fra);
		HashMap<Character, ArrayList<Integer>> map=new HashMap<Character, ArrayList<Integer>>();
		temp=fra.toCharArray();
		Integer i=0;
		for(char c:temp)
		{
			c=Character.toLowerCase(c);
			if(map.containsKey(c))
			{
				map.get(c).add(i);
			}
			else {
				lettlist.put(c,false);
				map.put(c, new ArrayList<Integer>());
				map.get(c).add(i);
				if(!isVowel(c))
					consn++;
			}
			i++;
		}
		return map;
	}

	private void LettPos(char c,int npl,String prem,int com)
	{
		if(lettlist.containsKey(c))
		{
			if(!lettlist.get(c))
			{
				this.com=Comands.CONS;
				ArrayList<Integer> temp;
				temp=frasemap.get(c);
				poc.players[npl].points+=temp.size()*Integer.parseInt(prem);
				ppos.replace(poc.players[npl].name,poc.players[npl].points);
				String tems=c+"";
				//visual(tems,"",com);
				/*try {
					String tems=c+"";
					visual(tems,"",com);
					//poc.players[npl].oos.writeBoolean(true);
					//poc.players[npl].oos.writeObject(temp);
					//poc.players[npl].oos.writeObject(new GuiUpd(c,temp,poc.players[0].points,poc.players[1].points,poc.players[2].points));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 */
				consn--;
				lettlist.replace(c,true);
				if(consn==0)
				visual(frase[manche].sent,frase[manche].theme,Comands.DFRASE.getCode());
			}
			else
				EndJturn(npl);
		}
		else
		{
				EndJturn(npl);
		}
	}
	
	ArrayList<Integer> getSentenceForMatch(int idM){
		int idMatch=idM;
		int idP, id;
		ArrayList<Integer> appo= new ArrayList<Integer>();//al d'appoggio per tenere dentro i dati isi
		ArrayList<Integer> sentences = new ArrayList<Integer>();//lista totale delle sentenze
		ArrayList<Integer> players = new ArrayList<Integer>();//al con gli id dei giocatori del match attuale
		ArrayList<Integer> avoidMatch = new ArrayList<Integer>();//match giocati/osservato dai giocatori del match attuale
		ArrayList<Integer> avoidSentence = new ArrayList<Integer>();//sentenze da evitare per la pesca delle 5 da usare nel match attuale
		sentences.addAll(conn.dbcon.getAllSentenceId());//aggiungo tutti gli id delle sentenze
		players.addAll(conn.dbcon.getPlayers(idMatch));//ottengo gli id dei giocatori
		while(!players.isEmpty()){
			idP=players.remove(0);
			appo.addAll(conn.dbcon.getPlayedMatchId(idP));//ottengo id di tutti i match giocati dal giocatore
			while(!appo.isEmpty()) {
				id=appo.remove(0);
				if(!avoidMatch.contains(id)) {
					avoidMatch.add(id);
				}
			}
			appo.clear();
			appo.addAll(conn.dbcon.getObservedMatchId(idP));//ottengo id di tutti i match osservati dal giocatore
			while(!appo.isEmpty()) {
				id=appo.remove(0);
				if(!avoidMatch.contains(id)) {
					avoidMatch.add(id);
				}
			}
		}//ciclo si salvataggio idMatch giocati e osservati dai giocatori e li metto in un solo arrayList contenente tutti i match da cui non prendere le sentenze//ora in appo3 ho tutti gli id dei match da evitare
		appo.clear();//pulisto appo
		while(!avoidMatch.isEmpty()) {
			id=avoidMatch.remove(0);//rimuovo il match da cui estrapolare le sentenze usate
			appo.addAll(conn.dbcon.getUsedSentence(id));//ottengo tutte le sentenze usate in quel match
			while(!appo.isEmpty()) {
				id=appo.remove(0);
				if(!avoidSentence.contains(id)) {
					avoidSentence.add(id);
				}
			}
		}//fine ciclo
		while(!avoidSentence.isEmpty()) {//ciclo di rimozione sentenze non utilizzabili
			sentences.remove(avoidSentence.remove(0));
		}//fine ciclo rimozione
		return sentences;
	}

}