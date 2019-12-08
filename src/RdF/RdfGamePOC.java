package RdF;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RdfGamePOC 
{
	ArrayList<PlayOss> osser;
	PlayOss[] players;
	int count;
	
	public RdfGamePOC()
	{
		osser=new ArrayList<PlayOss>();
		players=new PlayOss[3];
		count=0;
	}
	
	public synchronized void newPO(boolean pob, Socket posock, String name, ObjectInputStream istemp, ObjectOutputStream outemp)
	{
		System.out.println("i");
		if(pob)//true giocatore false osservatore 
		{
			System.out.println("Gif1");
			if(count<3)
			{
				System.out.println("Gif2");
				players[count]=new PlayOss(posock,name,istemp,outemp);
				System.out.println(name);
				count++;
				System.out.println(count);
			}
			System.out.println("Gif3");
		}
		else
		{
			System.out.println("Oss");
		osser.add(new PlayOss(posock,name,istemp,outemp));
		}
		System.out.println("f");
	}
}