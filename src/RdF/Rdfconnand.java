package RdF;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Rdfconnand implements  Runnable
{
    RdfGamePOC poc;
    Socket socket;
    public Rdfconnand(Socket sock, RdfGamePOC pocrefer)
    {
        socket=sock;
        poc=pocrefer;
    }

    @Override
    public void run()
    {
        ObjectInputStream istemp=null;
        ObjectOutputStream outemp=null;
        try {
            istemp=new ObjectInputStream(socket.getInputStream());
            outemp=new ObjectOutputStream(socket.getOutputStream());
            outemp.writeObject("test");
            outemp.flush();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            System.out.println(poc.count);
            boolean temp=(boolean)istemp.readObject();//true giocatore false osservatore
            String ntemp= (String) istemp.readObject();
            poc.newPO(temp,socket,ntemp,istemp,outemp);
            System.out.println(temp +""+ socket.getPort()+""+ntemp);
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
