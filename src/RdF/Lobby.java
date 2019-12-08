package RdF;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Lobby implements Runnable, Serializable
{
	private ArrayList<String> player=new ArrayList<String>();
	private ArrayList<String> observer;
	private int port;
	LocalDateTime date;
	public Lobby(int portnumber) 
	{
		date=LocalDateTime.now();
		player.add(null);
		port=portnumber;
	}
	
	
	public void run() 
	{
		
	}
	
	public void Join(String pjoin)
	{
		if(player.size()<=3)
		player.add(pjoin);
	}
	
	public void Obbs(String pjoin)
	{
		observer.add(pjoin);
	}
	
	public void Discc(String pleave)
	{
		if(player.contains(pleave))
		player.remove(pleave);
		else
			observer.remove(pleave);
	}


	public int getPort() {
		return port;
	}



}