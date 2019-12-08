package RdF;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RdfGSkele implements Runnable
{
	RdfGamePOC pocrefer;
	ServerSocket serv;
	public RdfGSkele(int port,RdfGamePOC poc) 
	{
		 try {
			serv=new ServerSocket(port);
			pocrefer=poc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() 
	{
		Socket socket;
		while(true)
		{socket=null;
			try {
				socket=serv.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			new Thread(new Rdfconnand(socket,pocrefer)).start();
		}
	}
	
}