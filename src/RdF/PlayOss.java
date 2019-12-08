package RdF;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayOss
{
	Socket sock;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	int id;
	String name;
	int points,matchpoi,jolly;
	public PlayOss(Socket sork,String n,ObjectInputStream istemp, ObjectOutputStream outemp)
	{
		matchpoi=0;
		name=n;
		points=0;
		jolly=0;
		sock=sork;
		ois=istemp;
		oos=outemp;
	}

}