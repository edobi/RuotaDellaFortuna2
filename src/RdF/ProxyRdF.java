package RdF;

import GUI.Profile;
import GUI.Stats;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class ProxyRdF {
    private ObjectInputStream in, Gin;
    private ObjectOutputStream out, Gout;
    private Socket socket, Gsocket;
    private int gameport;
    private String unicode;
    private boolean ingame = false;
    private String name;
    private String email;
    private String pass;
    private boolean player;
    private int Lccom;
    private Long ltime;
    private int manche;
    private boolean obs=false;
    private boolean conse=false;
    private String move=" ";

    public ProxyRdF() throws IOException {
        Lccom=0;
        ltime=System.currentTimeMillis();
        IniConnection(ServerRdFInterface.PORT);
        try {
            unicode = (String) in.readObject();
            System.out.println("prova " + unicode);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setMove(String temp){move=temp;}

    public String getMove(){return move;}

    public void setObs(boolean bool) { obs=bool; }

    public boolean getObs() {return obs;}

    public void setconse(boolean bool) { conse=bool; }

    public boolean getconse() { return conse; }

    public void setltime(Long i)
    {
        System.out.println("setltime");
        ltime=i;
    }

    public Long getltime()
    {
        System.out.println("getltime");
        return ltime;
    }

    public void setLccom(int i)
    {
        System.out.println("setLccom");
        Lccom=i;
    }

    public int getLccom()
    {
        System.out.println("getLccom");
        return Lccom;
    }

    public String getemail() {
        System.out.println("getEmail");
        return email;
    }

    public String getNick() {
        System.out.println("getNick");
        return name;
    }

    public boolean getPoA() {
        System.out.println("getPoA");
        return player;
    }
    public void setPoA(boolean temp)
    {
        System.out.println("setPOA");
        player=temp;
    }

	private void IniConnection(int port) {
		try {
            System.out.println("Iniconnector");
			InetAddress addr = InetAddress.getByName(null);
			socket = new Socket(addr, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			System.out.println("Ellole nel ploxy!");
			e.printStackTrace();
		}
	}

	private void IniConnectionGame(int port) {
        Gin=null;
		try {
            System.out.println("IniconnectorGame");
			InetAddress addr = InetAddress.getByName(null);
			Gsocket = new Socket(addr, port);
            Gout = new ObjectOutputStream(Gsocket.getOutputStream());
            Gin = new ObjectInputStream(Gsocket.getInputStream());
            System.out.println(Gin.readObject());
			//Gout.flush();
		} catch (Exception e) {
			System.out.println("Ellole nel ploxy!");
			e.printStackTrace();
		}
	}

    public int NewGame(String name) throws IOException, ClassNotFoundException {
        System.out.println("NewGame");
        out.writeObject(Comands.NEW);
        //out.writeUTF(name);
        System.out.println(name);
        out.writeObject(name);
        gameport = (int) in.readObject();
        if (gameport != -1) {
            IniConnectionGame(gameport);
            Gout.writeObject(true);
            Gout.writeObject(this.name);
        }
        else {
            //errore
        }
        return gameport;
    }

    public void JoinGame(String name, boolean bool) throws IOException, ClassNotFoundException {
        System.out.println("JoinGame");
        out.writeObject(Comands.JOIN);
        System.out.println(name);
        out.writeObject(name);
        out.writeObject(bool);
        gameport = (int) in.readObject();
        if (gameport != -1) {
            IniConnectionGame(gameport);
            Gout.writeObject(bool);
            Gout.writeObject(this.name);
        }
    }

    public void View() throws IOException {
        out.writeObject(Comands.VIEW);

    }

    public void close() throws IOException {
        Gsocket.close();
        socket.close();

    }

    public int LoginBtn(String email, String psw) {
        System.out.println("Ricevuto: " + email + " " + psw);
        try {
            out.writeObject(Comands.LOGIN);
            out.writeObject(email);
            out.writeObject(psw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int logres = 0;
        try {
            logres = (int) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println(logres);
        if (logres>0 ) {
            try {
                name = (String) in.readObject();
                System.out.println(name);
                this.email = email;
                pass = psw;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return logres;

    }

    public int RegistrBtn(boolean adm, String name, String surname, String email, String txtPswd, String nick) {
		System.out.println(" " + adm + " " + name + " " + surname + " " + email + " " + txtPswd + " " + nick);
        int res = 0;
        System.out.println("RegisterBtn");
        try {
            out.writeObject(Comands.REGIST);
            out.writeUTF(name);
            out.writeUTF(surname);
            out.writeUTF(email);
            out.writeUTF(txtPswd);
            out.writeUTF(nick);
            out.writeObject(adm);
            res = (int) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("res " + res);
        return res;
    }

    public void JoinG(HashMap<String, Lobby> Gmap) {
        boolean player = false;//valore esterno
        System.out.println("JoinG");
        if (Gmap != null) {
            String name = "";//gui text field
            InetAddress addr = null;//external server address
            try {
                addr = InetAddress.getByName(null);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                Socket socket = new Socket(addr, Gmap.get(name).getPort());
                //IniConnection(socket,ous,ins,port);
                //ous.writeBoolean(player);
                //ous.writeObject(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String[] View(boolean b)//player true observer false
    {
        System.out.println("View");
        //ArrayList<String> nalis = new ArrayList<String>();
        String[] nalis = new String[0];
        try {
            out.writeObject(Comands.VIEW);
            //out.writeBoolean(b);
            out.writeObject(b);
            nalis = (String[])in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return nalis;
    }

    public void ActivCode(String act) {
        try {
            System.out.println("activcode");
        	out.writeObject(Comands.ACTIV);
            out.writeObject(act);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PswReset(String email) {
        try {
            System.out.println("pswreset");
            out.writeObject(Comands.PSWRESE);
            out.writeObject(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ProfChange(String email, String name, String surname,String nickname) {
        //String password=null,email=null,name=null,surname=null;//gui text fields
        System.out.println("profchange");
        String[] obj = new String[4];
        obj[0] = email;
        obj[1] = name;
        obj[2] = surname;
        obj[3] = nickname;
        try {
            out.writeObject(Comands.CPROF);
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean PswChange(String psw, String email) {
        boolean res = false;
        System.out.println("pswchange");
        try {
            out.writeObject(Comands.CPSW);
            out.writeObject(psw);
            out.writeObject(email);
            res = (boolean) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void Stats(Stats window, String name) {
        System.out.println("stats");
        if (name.isEmpty())
            name = this.name;//login nickname 
        try {
            out.writeObject(Comands.STAT);
            out.writeObject(name);
            //globalAvgGuessSentence
            String temp=Integer.toString((int)in.readObject());
            window.setLblAvgMoves(temp);
            //globalConsSentUser
            temp=(String)in.readObject();
            //globalHighAvgManche
            temp=(String)in.readObject();
            window.setLblMaxAvg(temp);
            //globalHighCrack
            temp=(String)in.readObject();
            window.setLblPLose(temp);
            //globalHighSkip
            temp=(String)in.readObject();
            //globalMostManchePlayed
            temp=(String)in.readObject();
            window.setLblPManche(temp);
            //globalMostPoint
            temp=(String)in.readObject();
            window.setlblPMax(temp);
            //individualPlayedMatch
            temp=(String)in.readObject();
            window.setLblUsernpart(temp);
            //individualPlayedManche
            temp=(String)in.readObject();
            window.setLblUsernman(temp);
            //individualObservedMatch
            temp=(String)in.readObject();
            window.setLblMatchobs(temp);
            //individualObservedManche
            temp=(String)in.readObject();
            window.setLblManobs(temp);
            //individualMatchWon
            temp=(String)in.readObject();
            window.setLblNwin(temp);
            //individualMancheWon
            temp=(String)in.readObject();
            window.setLblNmanwin(temp);
            //individualAvgMoney
            temp=(String)in.readObject();
            window.setLblAvgpunwin(temp);
            //individualAvgCrack
            temp=(String)in.readObject();
            window.setLblAvglose(temp);
            //individualAvgSkip
            temp=(String)in.readObject();
            window.setLblAvgnext(temp);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Discon() {
        try {
            System.out.println("disconn");
            out.writeObject(Comands.DISCONNECT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void NewFrase() {
        try {
            System.out.println("newfrase");
            out.writeObject(Comands.NFRASE);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //NCFrase();
    }


    public void CFraseConf(Sentence nsent, Sentence osent) {
        try {
            System.out.println("cfraseconf");
            out.writeObject(Comands.CFRASE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        NCFrase(nsent);
        try {
            out.writeObject(osent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void NCFrase(Sentence nfrase) {
        try {
            System.out.println("ncfrase");
            out.writeObject(nfrase);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void DFraseConf(String dsent) {
        try {
            System.out.println("dfraseconf");
            out.writeObject(Comands.DFRASE);
            out.writeObject(dsent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Confirm(int com, String text, JTable table)
    {
        Comands Lcom;
        Lcom=getCombyCode(com);
        System.out.println("Confirm");
        System.out.println(Lcom);
        switch (Lcom) {
            case VOWEL:
            case CONS:
                System.out.println("Cons&Vowel");
                if (System.currentTimeMillis() < (ltime + 50000)) {
                    try {
                        Gout.writeObject(Lcom);
                        Gout.writeObject(text.charAt(0));
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }else
                try {
                    Gout.writeObject(Comands.YT);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case SOLUT:
                if (System.currentTimeMillis() < (ltime + 100000)) {
                    try {
                        Gout.writeObject(Lcom);
                        Gout.writeObject(text);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else
                try {
                    Gout.writeObject(Comands.YT);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case SPIN:
                if (System.currentTimeMillis() < (ltime + 50000)) {

                    try {
                        Gout.writeObject(Lcom);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else
                    try {
                        Gout.writeObject(Comands.YT);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                break;
            case END:
                break;
            case DISCONNECT:
                break;
            default:
                break;
        }
    }

    public void Game() {

        Comands com = Comands.NEW;
        try {
            com = (Comands) Gin.readObject();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (com) {
            case YT:
                break;
            case STAT:
                break;
            case DISCONNECT:
                break;
            default:
                break;
        }
    }

    public void CsVSentTrans(ArrayList<Sentence> arraySentences) {
        try {
            System.out.println("csvsenttrans");
            out.writeObject(Comands.CSVLOAD);
            out.writeObject(arraySentences);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void lettvisual(char c, JTable table) {
        System.out.println("lettvisual");
        try {
            Gout.writeChar(c);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if (Gin.readBoolean()) {
                ArrayList<Integer> list = (ArrayList<Integer>) Gin.readObject();
                for (Integer i : list)
                    table.setValueAt(c, i / 10, i % 10);
                table.updateUI();
            }
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void lettobs(JTable table) {
        System.out.println("lettobs");
        char c = 0;
        try {
            String temp = (String)Gin.readObject();
            c=temp.charAt(0);
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            list = (ArrayList<Integer>) Gin.readObject();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (Integer i : list)
            table.setValueAt(c, i / 10, i % 10);
        table.updateUI();
    }

    public void SolutVisual(JTable table) {
        System.out.println("solutvisual");
        String temp = "";
        try {
            temp = (String) Gin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(temp!="")
        {
            char[] tempc = temp.toCharArray();
            int count = 0;
            for (char c : tempc) {
                table.setValueAt(c, count / 10, count % 10);
                count++;
            }
        }
    }

    public ArrayList<Sentence> loadSentence() {
        System.out.println("loadsentence");
        ArrayList<Sentence> arrsent = null;
        try {
            out.writeObject(Comands.SLOAD);
            arrsent = (ArrayList<Sentence>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arrsent;
    }

    public Comands Threadcom() {
        Comands c = null;
        try {
            c = (Comands) Gin.readObject();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return c;
    }

    public void Guiupd() {

    }

    public void JollyPup() {
        boolean temp = false;
        int dialogButton = 0;
        //int dialogResult= JOptionPane.showConfirmDialog (null, "Vuoi usare il jolly? ");
        int dialogResult = JOptionPane.showConfirmDialog(null, "Vuoi usare il jolly? ", null, dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            //System.out.println("Tasto si");
            temp = true;
        }
        if (dialogResult == (JOptionPane.NO_OPTION | JOptionPane.CLOSED_OPTION | JOptionPane.CANCEL_OPTION)) {
            //System.out.println("Tasto no");
            temp = false;
        }
        try {
            Gout.writeObject(temp);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int Read() throws IOException, ClassNotFoundException
    {
        System.out.println("Read");
        Comands com=(Comands)Gin.readObject();
      return com.getCode();
    }

    public int GameStr(JLabel labT,JLabel labM,JLabel[] ppos,JLabel labP,JLabel text)
    {
        System.out.println("GameStr");
        try {

            labT.setText((String)Gin.readObject());
            manche=(int)Gin.readObject();
            String[] temp= (String[]) Gin.readObject();
            int tempi= (int) Gin.readObject();
            String mo= (String) Gin.readObject();
            text.setText(mo);
            labP.setText(String.valueOf(tempi));
            labM.setText(String.valueOf(manche));
            for(int i=0;i<3;i++)
                ppos[i].setText(temp[i]);
        } catch (IOException e) {
            labT.updateUI();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return manche;
    }

    public static Comands getCombyCode(int i) {
        Comands com = null;
        System.out.println("getcombycode");
        for (Comands c : Comands.values()) {
            if (i == c.getCode())
                return c;
        }
        return com;
    }

    public String SpinRead()
    {
        String str="";
        try {
            System.out.println("SpinRead");
            str=(String)Gin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void Prof(Profile profile)
    {
        profile.setEmail(email);
        try {
            out.writeObject(Comands.CHANGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            profile.setName((String) in.readObject());
            profile.setNickname((String) in.readObject());
            profile.setSurname((String) in.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}