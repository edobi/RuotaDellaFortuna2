package RdF;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import Mail.Mail_Helper;

public class SkeletonRdF implements Runnable {
    private Socket socket;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    //private ServerRdF serv;
    private String username;
    private String email;
    private int iduser;
    //private String password;
    private ConnectProp gm;
    private String unifrase = "159753852456";
    private boolean adm = false;
    private HashMap<String, Socket> logstat;

    public SkeletonRdF(Socket sock, ConnectProp game, HashMap<String, Socket> logins) {
        gm = game;
        socket = sock;
        logstat = logins;
        try {
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
            os.writeObject(unifrase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            //adm = is.readBoolean();
            Comands myOper = null;
            while (!socket.isClosed()) {
                try {
                    myOper = (Comands) is.readObject();
                } catch (SocketException e) {
                    e.printStackTrace();
                    if (logstat.containsKey(email))
                        logstat.remove(email);
                }
                System.out.println(myOper);
//********************************************************************************************
                if (myOper.equals(Comands.LOGIN)) {
                    //serv.Login(unifrase);
                    String tempE = "";
                    String tempP = "";
                    tempE = (String) is.readObject();
                    tempP = (String) is.readObject();
                    if (gm.Login(tempE, tempP)) {
                        //System.out.println("if");
                        email = tempE;
                        iduser = gm.dbcon.getIdUser(email);
                        boolean k = false;
                        if (gm.dbcon.checkAccountState(email)) {
                            if (gm.dbcon.isAdm(iduser)) {
                                if (!logstat.containsKey(email)) {
                                    adm = true;
                                    k = setLogstat(3);
                                    logstat.put(email, socket);
                                } else if (logstat.get(email).isClosed()) {
                                    adm = true;
                                    k = setLogstat(3);
                                    logstat.replace(email, socket);
                                } else
                                    os.writeObject(0);
                            } else {
                                if (!logstat.containsKey(email)) {
                                    k = setLogstat(1);
                                    logstat.put(email, socket);
                                } else if (logstat.get(email).isClosed()) {
                                    k = setLogstat(1);
                                    logstat.replace(email, socket);
                                } else
                                    os.writeObject(0);
                            }
                        } else {
                            os.writeObject(2);
                            k = true;
                        }
                        if (k)
                            username = gm.dbcon.getUserNickname(iduser);
                        os.writeObject(username);

                    } else {
                        System.out.println("else");
                        os.writeObject(0);
                    }
                }
//********************************************************************************************
                else if (myOper.equals(Comands.REGIST)) {
                    boolean temp = false;
                    String name = is.readUTF();
                    String surn = (String) is.readUTF();
                    String email = (String) is.readUTF();
                    String psw = is.readUTF();
                    String nick = (String) is.readUTF();
                    String ac = Mail_Helper.code();
                    if (!(boolean) is.readObject()) {
                        System.out.println("user");
                        gm.Registration(name, surn, email, psw, nick, ac);
                    } else {
                        System.out.println("admin");
                        gm.RegisterAdmin(name, surn, email, psw, nick, ac);
                    }
                    System.out.println("login");
                    temp = gm.Login(email, psw);
                    if (temp) {
                        try {
                            Mail_Helper.send_mail_outlook(email, "Codice di attivazione", "" + ac);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                        if (gm.dbcon.checkAccountState(email)) {
                            os.writeObject(1);
                        } else {
                            os.writeObject(2);
                        }
                    } else {
                        os.writeObject(0);
                    }

                }
//********************************************************************************************
                else if (myOper.equals(Comands.JOIN)) {
                    String temp = (String) is.readObject();
                    boolean tb = (boolean) is.readObject();
                    int tempi = -1;
                    if (tb)
                        tempi = gm.lobblist.get(temp).getPort();
                    else
                        if(gm.Osslist.containsKey(temp))
                        tempi = gm.Osslist.get(temp).getPort();
                        else
                            tempi = gm.lobblist.get(temp).getPort();
                    os.writeObject(tempi);
                }
//********************************************************************************************
                else if (myOper.equals(Comands.VIEW)) {
                    if ((boolean) is.readObject()) {
                        os.writeObject(gm.lobblist.keySet().toArray(new String[gm.lobblist.keySet().size()]));
                    } else {
                        String[] temp = gm.lobblist.keySet().toArray(new String[gm.lobblist.keySet().size()]);
                        String[] temp1 = gm.Osslist.keySet().toArray(new String[gm.Osslist.keySet().size()]);
                        if(temp.length==0||temp==null)
                            os.writeObject(temp1);
                        else if(temp1.length==0||temp1==null)
                            os.writeObject(temp);
                        else
                            {
                            // temp.addAll(temp1);
                            String[] temp2 = merge(temp, temp1);
                            os.writeObject(temp2);
                        }
                    }
                }
//********************************************************************************************				
                else if (myOper.equals(Comands.ACTIV)) {
                    String ac = (String) is.readObject();
                    if ((System.currentTimeMillis() - gm.dbcon.getHourRegistration(email) < 600000 || true)) {//600.000s=10m
                        if (gm.dbcon.checkActivationCode(email, ac)) {
                            gm.dbcon.activateAccount(email);

                            Mail_Helper.confirMail(email);
                        } else {

                            System.out.println("Sbagliato");
                        }
                    } else {

                        System.out.println("Ritardato");
                    }
                }
//********************************************************************************************
                else if (myOper.equals(Comands.PSWRESE)) {
                    email = (String) is.readObject();
                    String temp = Mail_Helper.code();
                    gm.dbcon.updatePassword(email, temp);
                    try {
                        Mail_Helper.setMail(email, "Mail Reset", "your new mail is" + "" + temp);
                    } catch (SendFailedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (MessagingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else if (myOper.equals(Comands.CPROF))
                {
                    String[] temp = (String[]) is.readObject();//
                        if (temp[0] == null|| temp[0] == "")
                            temp[0] = gm.dbcon.getUserName(iduser);
                        if (temp[1] == null|| temp[1] == "")
                            temp[1] = gm.dbcon.getUserSurname(iduser);
                        if (temp[2] == null|| temp[2] == null)
                            temp[2] = email;
                        if (temp[3] == null||temp[3]== "")
                            temp[3]=gm.dbcon.getUserNickname(iduser);
                        gm.dbcon.updateUser(iduser,temp[0],temp[1], temp[2], temp[3]);

                }
                else if(myOper.equals(Comands.CHANGE))
                {
                    os.writeObject(gm.dbcon.getUserName(iduser));
                    os.writeObject(username);
                    os.writeObject(gm.dbcon.getUserSurname(iduser));
                }
//********************************************************************************************
                else if (myOper.equals(Comands.STAT))
                {
                    String tempr = (String) is.readObject();//default value login name
                    os.writeObject(gm.globalAvgGuessSentence()); //media
                    os.writeObject(gm.globalConsSentUser()); //
                    os.writeObject(gm.globalHighAvgManche()); //
                    os.writeObject(gm.globalHighCrack()); //
                    os.writeObject(gm.globalHighSkip()); //
                    os.writeObject(gm.globalMostManchePlayed()); //massimo numero di manche giocate
                    os.writeObject(gm.globalMostPoints()); //punti massimi
                    os.writeObject(gm.individualPlayedMatch(tempr)); //partite giocate
                    os.writeObject(gm.individualPlayedManche(tempr)); //mache giocate
                    os.writeObject(gm.individualObservedMatch(tempr)); //match
                    os.writeObject(gm.individualObservedManche(tempr)); //manche
                    os.writeObject(gm.individualMatchWon(tempr)); //
                    os.writeObject(gm.individualMancheWon(tempr)); //
                    os.writeObject(gm.individualAvgMoney(tempr)); //
                    os.writeObject(gm.individualAvgCrack(tempr)); //
                    os.writeObject(gm.individualAvgSkip(tempr)); //
                }
//********************************************************************************************
                else if (myOper.equals(Comands.DISCONNECT)) {
                    socket.close();
                    logstat.remove(email);
                } else if (myOper.equals(Comands.CPSW)) {
                    String temp = (String) is.readObject();
                    String email = (String) is.readObject();
                    if (temp != null) {
                        gm.dbcon.updatePassword(temp, email);
                        os.writeObject(true);
                    } else
                        os.writeObject(false);
                }
//********************************************************************************************
                else if (myOper.equals(Comands.NEW)) {
                    //int temp = gm.NewGame(is.readUTF());
                    int temp = gm.NewGame((String) is.readObject());
                    System.out.println(temp);
                    if (temp != -1) {
                        os.writeObject(temp);
                        gm.portcounter++;
                    } else
                        os.writeObject(temp);
                }
//********************************************************************************************
                else {
                    if (adm) {
                        if (myOper.equals(Comands.SLOAD)) {
                            os.writeObject(gm.dbcon.getAllSentence());
                        } else if (myOper.equals(Comands.NFRASE)) {
                            Sentence temp = (Sentence) is.readObject();
                            gm.dbcon.insertSentence(temp);
                        } else if (myOper.equals(Comands.CFRASE)) {
                            Sentence ntemp = (Sentence) is.readObject();
                            Sentence otemp = (Sentence) is.readObject();
                            gm.dbcon.modifySentence(ntemp, otemp);
                        } else if (myOper.equals(Comands.DFRASE)) {
                            String sent = (String) is.readObject();
                            int idSent = gm.dbcon.getIdSentence(sent);
                            gm.dbcon.deleteSentence(idSent);
                        } else if (myOper.equals(Comands.CSVLOAD)) {
                            ArrayList<Sentence> arrsent = (ArrayList<Sentence>) is.readObject();
                            for (Sentence sent : arrsent) {
                                if (gm.dbcon.getIdSentence(sent.sent) == -1)
                                    gm.dbcon.insertSentence(sent);
                            }
                        }
                    }
                }
            }
        } catch (EOFException e) {
            System.out.println("Client scollegato");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String[] merge(String[] t1, String[] t2)
    {
        ArrayList<String> t3=new ArrayList<String>();
        //String[] t3=new String[it1];
        for(String s:t1)
            t3.add(s);
        for(String s:t2)
            t3.add(s);
            return t3.toArray(new String[t3.size()]);
    }

    private boolean setLogstat(int j)
    {
        try {
            os.writeObject(j);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
