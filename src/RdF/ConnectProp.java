package RdF;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectProp implements ServerRdFInterface {
    HashMap<String, Lobby> lobblist;
    HashMap<String, Lobby> Osslist;
    int portcounter = 49152;
    int idc = 0;
    DatabaseConnector dbcon;

    public ConnectProp(DatabaseConnector data) {
        dbcon = data;
        lobblist = new HashMap<String, Lobby>();
        Osslist = new HashMap<String, Lobby>();
        idc = dbcon.getMaxIdUser();
    }

    @Override
    public synchronized int NewGame(String name) {
        Lobby temp = new Lobby(portcounter);
        if (!lobblist.containsKey(name) || Osslist.containsKey(name)) {
            try {
                new Thread(new RdFGame(portcounter, temp, this, name)).start();
                lobblist.putIfAbsent(name, temp);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return portcounter;
        } else
            return -1;
    }

    @Override
    public void JoinGame(String name) {
        lobblist.get(name);
    }

    public int Registration(String name, String surn, String email, String psw, String nick, String acti) {
        long data = System.currentTimeMillis();
        int id = dbcon.getMaxIdUser() + 1;//dummy.
        dbcon.registerUser(id, name, surn, email, psw, nick, acti, data);
        return id;
    }

    public boolean Login(String email, String pass) {
        System.out.println(dbcon.checkPsw(pass, email));
        return dbcon.checkPsw(pass, email);
    }

    public void RegisterAdmin(String name, String surn, String email, String psw, String nick, String acti) {
        int id = Registration(name, surn, email, psw, nick, acti);
        dbcon.registerAdmin(id);
    }

    public boolean Ping(ObjectOutputStream ou)
    {
        boolean temp=true;
        try {
            ou.writeObject(Comands.PING);
            //ou.writeObject(true);
        } catch (IOException e) {
            e.printStackTrace();
            temp=false;
        }
        return temp;
    }

    //*****************************
    public String globalMostPoints() {//1.1
        ArrayList<Integer> al = new ArrayList<Integer>();
        String s;
        al = dbcon.globalMostPoints();
        if (al.isEmpty()) {
            s = "nessuno";
        } else {
            s = dbcon.getUserNickname(al.remove(0));
        }
        return s;
    }

    //*****************************
    public String globalMostManchePlayed() {//1.2
        ArrayList<Integer> al = new ArrayList<Integer>();
        String s;
        al = dbcon.globalMostManchePlayed();
        if (al.isEmpty()) {
            s = "nessuno";
        } else {
            s = dbcon.getUserNickname(al.remove(0));
        }
        return s;
    }

    //*****************************
    public String globalHighAvgManche() {//1.3
        ArrayList<Integer> al = new ArrayList<Integer>();
        String s;
        al = dbcon.globalHighAvgManche();
        if (al.isEmpty()) {
            s = "nessuno";
        } else {
            s = dbcon.getUserNickname(al.remove(0));
        }
        return s;
    }

    //*****************************
    public String globalHighSkip() {//1.4
        ArrayList<Integer> al = new ArrayList<Integer>();
        String s;
        al = dbcon.globalHighSkip();
        if (al.isEmpty()) {
            s = "nessuno";
        } else {
            s = dbcon.getUserNickname(al.remove(0));
        }
        return s;
    }

    //*****************************
    public String globalHighCrack() {//1.5
        ArrayList<Integer> al = new ArrayList<Integer>();
        String s;
        al = dbcon.globalHighCrack();
        if (al.isEmpty()) {
            s = "nessuno";
        } else {
            s = dbcon.getUserNickname(al.remove(0));
        }
        return s;
    }

    //*****************************
    public ArrayList<String> globalConsSentUser() {//2 modificare la sez select con quello che mi serve per gamare tuttos
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> all = new ArrayList<String>();
        al = dbcon.globalConsSentUser();
        if (al.isEmpty()) {
            all.add("...");//utente
            all.add("...");//frase
            all.add("...");//consonante
        } else {
            all.add(dbcon.getUserNickname(Integer.parseInt(al.remove(0))));//utente
            Sentence s = new Sentence();
            s = dbcon.getSentence(dbcon.getIdDemandedSentence(Integer.parseInt(al.remove(0))));
            all.add(s.getSentence());//frese
            all.add(al.remove(0));//consonante
        }
        return all;
    }

    //*****************************
    public int globalAvgGuessSentence() {//3
        ArrayList<Integer> al = new ArrayList<Integer>();
        al = dbcon.globalAvgGuessSentence();
        int div = al.size();
        int avg = 0;
        while (!al.isEmpty()) {
            avg += al.remove(0);
        }
        avg = avg / div;
        return avg;
    }

    //*****************************
    public String individualPlayedManche(String temp) {//4.1m
        ArrayList<Integer> al = new ArrayList<Integer>();
        int idUser = dbcon.getIdUserByNick(temp);
        al = dbcon.individualPlayedManche(idUser);
        return String.valueOf(al.size());
    }

    //*****************************
    public String individualPlayedMatch(String temp) {//4.1p
        int idUser = dbcon.getIdUserByNick(temp);
        return String.valueOf(dbcon.individualPlayedMatch(idUser));
    }

    //*****************************
    public String individualObservedManche(String temp) {//4.2m
        int idUser = dbcon.getIdUserByNick(temp);
        return String.valueOf(dbcon.individualObservedManche(idUser));
    }

    //*****************************
    public String individualObservedMatch(String temp) {//4.2p
        ArrayList<Integer> manObs = new ArrayList<Integer>();
        int idUser = dbcon.getIdUserByNick(temp);
        manObs = dbcon.getObservedManche(idUser);
        ArrayList<Integer> matObs = new ArrayList<Integer>();
        int idm, idp;
        while (!manObs.isEmpty()) {
            idm = manObs.remove(0);
            idp = dbcon.individualObservedMatch(idm);
            if (!matObs.contains(idp)) {
                matObs.add(idp);
            }
        }
        return String.valueOf(matObs.size());
    }

    //*****************************
    public String individualMancheWon(String temp) {//4.3m
        int idUser = dbcon.getIdUserByNick(temp);
        return String.valueOf(dbcon.individualMancheWon(idUser));
    }

    //*****************************
    public String individualMatchWon(String temp) {//4.3p
        int idUser = dbcon.getIdUserByNick(temp);
        return String.valueOf(dbcon.individualMatchWon(idUser));
    }

    //*****************************
    public String individualAvgMoney(String temp) {//4.4
        int idUser = dbcon.getIdUserByNick(temp);
        return String.valueOf(dbcon.individualAvgMoney(idUser));
    }

    //*****************************
    public String individualAvgSkip(String temp) {//4.5
        String s = "", m, p;
        int idUser = dbcon.getIdUserByNick(temp);
        m = String.valueOf(individualAvgSkipM(idUser)) + "m";
        p = individualAvgSkipP(idUser) + "p";
        s = m + ", " + p;
        return s;
    }

    //*****************************
    String individualAvgSkipM(int idUser) {//4.5m
        ArrayList<Integer> al = new ArrayList<Integer>();
        al = dbcon.individualAvgSkipM(idUser);
        int avg = 0;
        int div = al.size();
        while (!al.isEmpty()) {
            avg += al.remove(0);
        }
        if (div != 0) {
            avg /= div;
        }
        return String.valueOf(avg);
    }

    //*****************************
    String individualAvgSkipP(int idUser) {//4.5p
        return String.valueOf(dbcon.individualAvgSkipP(idUser));
    }

    //*****************************
    public String individualAvgCrack(String temp) {//4.6
        String s = "", m, p;
        int idUser = dbcon.getIdUserByNick(temp);
        m = String.valueOf(individualAvgCrackM(idUser)) + "m";
        p = individualAvgCrackP(idUser) + "p";
        s = m + ", " + p;
        return s;
    }

    //*****************************
    String individualAvgCrackM(int idUser) {//4.6m
        ArrayList<Integer> al = new ArrayList<Integer>();
        al = dbcon.individualAvgCrackM(idUser);
        int avg = 0;
        int div = al.size();
        while (!al.isEmpty()) {
            avg += al.remove(0);
        }
        if (div != 0) {
            avg /= div;
        }
        return String.valueOf(avg);
    }

    //*****************************
    String individualAvgCrackP(int idUser) {//4.6p
        return String.valueOf(dbcon.individualAvgCrackP(idUser));
    }
    //*****************************
}