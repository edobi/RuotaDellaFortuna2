package RdF;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import GUI.AddDatabase;
import Mail.Mail_Helper;

public class ServerRdF {
    private DatabaseConnector Gdbcon;
    HashMap<String,Socket> logStat;

    public static void main(String[] args) throws IOException, NullPointerException {
        Login login = new Login();
        String hostDb;
        boolean bool = true;
        //ConnectProp games;
        //Server sergui;
        java.awt.EventQueue.invokeLater(() -> new AddDatabase(new ServerRdF()));
    }

    public void setDbcon(DatabaseConnector dbcon) {
        Gdbcon = dbcon;
    }

    public void avvia() {
		ConnectProp games = new ConnectProp(Gdbcon);
		ServerSocket serv = null;
		logStat=new HashMap<String,Socket>();
		try {
			serv = new ServerSocket(ServerRdFInterface.PORT);
			while (true) {
				Socket socket = serv.accept();
				new Thread(new SkeletonRdF(socket, games,logStat)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public DatabaseConnector getDbcon() {
        return Gdbcon;
    }

    private int Registration(String name, String surn, String email, String psw, String nick, String acti) {
        long data = System.currentTimeMillis();
        int id = Gdbcon.getMaxIdUser() + 1;//dummy.
        Gdbcon.registerUser(id, name, surn, email, psw, nick, acti, data);
        return id;
    }

    public int Adminex() {
        return Gdbcon.adminExist();
    }

    public int Login(String email, String psw) {
        return Gdbcon.login(email, psw);
    }

    public void Register(String name, String surn, String email, String psw, String nick) {
        String ac = Mail_Helper.code();
        int id = Registration(name, surn, email, psw, nick, ac);
        Gdbcon.registerAdmin(id);
        Gdbcon.activateAccount(email);
    }
}
