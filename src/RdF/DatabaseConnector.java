package RdF;
//**********************************************

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//**********************************************
public class DatabaseConnector {
    String url = "jdbc:postgresql://";
    String user = "postgres";
    String password = "1234";
    Connection conn;
    //String url, user, password;

    public static void main(String[] args) {
        new DatabaseConnector("localhost:5432/postgres", "postgres", "1234").connect();
    }

// TODO: cambiare credenziali
    //**********************************************
    public String getUrl() {
        return url;
    }

    //**********************************************
    public String getUser() {
        return user;
    }

    //**********************************************
    public String getPswd() {
        return password;
    }

    //**********************************************
    public DatabaseConnector(String ur, String us, String ps) {
        /*url = url + us;
        user = ur;
        password = ps;*/
        url = url + ur;
        user = us;
        password = ps;
        //System.out.println(url + user + password);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = connect();
    }

    //**********************************************
    Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //**********************************************
    int adminExist() {
        String SQL = "SELECT COUNT(*) FROM \"MANAGE\"";
        int b = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") > 0) {
                b = 1;
            }
            return b;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            b = -1;
            return b;
        }
        catch (NullPointerException e)
        {
            b=-1;
            return b;
        }
    }

    //**********************************************
    int getMaxIdUser() {
        String SQL="SELECT MAX(\"idUser\") FROM \"USER\"";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("MAX");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    boolean checkEmail(String s) {
        String SQL="SELECT COUNT(*) FROM \"USER\" WHERE \"email\"='"+s+"'";
        boolean inuse = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") == 0) {
                inuse = false;
            } else {
                inuse = true;
            }
            return inuse;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            inuse = true;
            return inuse;
        }
    }

    //**********************************************
    boolean checkNickname(String s) {
        String SQL="SELECT COUNT(*) FROM \"USER\" WHERE \"nickname\"='"+s+"'";
        boolean inuse = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") == 0) {
                inuse = false;
            } else {
                inuse = true;
            }
            return inuse;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            inuse = true;
            return inuse;
        }
    }

    //**********************************************
    synchronized void registerUser(int id, String name, String surn, String email, String psw, String nick, String acti, long hour) {
        String SQL="INSERT INTO \"USER\" (\"idUser\", name, surname, email, password, nickname, activationcode, \"hourRegistration\") VALUES ("+id+", '"+name+"','"+surn+"','"+email+"','"+psw+"','"+nick+"','"+acti+"','"+hour+"')";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    synchronized void registerAdmin(int id) {
        String SQL="INSERT INTO \"MANAGE\" (\"idUser\") VALUES ("+id+")";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    synchronized void registerPlayer(int id) {
        String SQL = "INSERT INTO \"PLAY\" (\"idUser\") VALUES (" + id + ")";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    boolean checkPsw(String p, String e) {
        String SQL="SELECT COUNT(*) FROM \"USER\" WHERE \"email\"='"+e+"' AND \"password\"='"+p+"'";
        boolean inuse = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") == 1) {
                inuse = true;
            }
            return inuse;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            inuse = true;
            return inuse;
        }
    }

    //**********************************************
    boolean checkAccountState(String email) {
        String SQL="SELECT * FROM \"USER\" WHERE \"email\"='"+email+"'";
        boolean active = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getBoolean("accountstate")) {
                active = true;
            }
            return active;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return active;
        }
    }

    //**********************************************
    void deleteUserAccount(String email) {
        String SQL = "DELETE FROM \"USER\" WHERE \"email\"='" + email + "'";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    boolean checkActivationCode(String e, String a) {
        String SQL = "SELECT COUNT(*) FROM \"USER\" WHERE \"email\"='" + e + "' AND \"activationcode\"='" + a + "'";
        boolean inuse = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") == 1) {
                inuse = true;
            }
            return inuse;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            inuse = true;
            return inuse;
        }
    }

    //**********************************************
    long getHourRegistration(String email) {
        String SQL = "SELECT * FROM \"USER\" WHERE \"email\"='" + email + "'";
        long active = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            active = Long.parseLong(rs.getString("hourRegistration"));
            return active;
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return active;
        }
    }

    //**********************************************
    synchronized void activateAccount(String email) {
        String SQL = "UPDATE \"USER\" SET \"accountstate\"=true WHERE \"email\"='" + email + "'";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    synchronized void insertSentence(Sentence s) {
        int id = getMaxIdSentence() + 1;
        String SQL = "INSERT INTO \"SENTENCE\" (\"idSentence\", \"sentence\", \"theme\") VALUES (" + id + ", '" + s.getSentence() + "', '" + s.getTheme() + "')";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    int getMaxIdSentence() {
        String SQL = "SELECT MAX(\"idSentence\") FROM \"SENTENCE\"";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("MAX");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    void deleteSentence(int idSentence) {
        String SQL = "DELETE FROM \"SENTENCE\" WHERE \"idSentence\"=" + idSentence;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    void deleteAllSentence() {
        String SQL = "DELETE FROM \"SENTENCE\"";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    ArrayList<Sentence> getAllSentence() {
        String SQL = "SELECT * FROM \"SENTENCE\" order by theme asc";
        ArrayList<Sentence> al = new ArrayList<Sentence>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                Sentence s = new Sentence(rs.getString("sentence"), rs.getString("theme"));
                al.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    Sentence getSentence(int id) {
        Sentence s = null;
        String SQL = "SELECT * FROM \"SENTENCE\" WHERE \"idSentence\"=" + id;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            s = new Sentence(rs.getString("sentence"), rs.getString("theme"));
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return s;

    }

    //**********************************************
    ArrayList<Integer> getAllSentenceId() {
        String SQL = "SELECT * FROM \"SENTENCE\" order by \"idSentence\" asc";
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idSentence"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> getPlayers(int idMatch) {
        String SQL = "SELECT * FROM \"MATCH\" WHERE \"idMatch\"=" + idMatch;
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            al.add(rs.getInt("idP1"));
            al.add(rs.getInt("idP2"));
            al.add(rs.getInt("idP3"));
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> getPlayedMatchId(int idPlayer) {
        String SQL = "SELECT * FROM \"MATCH\" WHERE \"idP1\"=" + idPlayer + " OR \"idP2\"=" + idPlayer + " OR \"idP3\"=" + idPlayer;
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idMatch"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> getUsedSentence(int idMatch) {
        String SQL = "SELECT * FROM \"DEMAND\" WHERE \"idMatch\"=" + idMatch;
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idSentence"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> getAllLiveMatch() {//ottengo tutte le partite in corso (senza vincitore)
        String SQL = "SELECT * FROM \"MATCH\" WHERE \"winner\" is null order by \"idMatch\" asc";
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idMatch"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    synchronized void demandedSentence(int idSentence, int nManche, int idMatch) {//registra la sentenza come richiesta nella manche tot
        String SQL = "insert into \"DEMAND\" (\"idManche\", \"idMatch\", \"idSentence\") values(" + getManche(idMatch, nManche) + "," + idMatch + "," + idSentence + ")";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    String getMailUser(int id) {
        String SQL = "SELECT * FROM \"USER\" WHERE \"idUser\"=" + id;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getString("email");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return "";
        }
    }

    //**********************************************
    int getIdUser(String email) {//
        String SQL = "SELECT \"idUser\" FROM \"USER\" WHERE \"email\"=\'" + email + "\'";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("idUser");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    ArrayList<Integer> getNotFullMatch() {//ritorna tutti i match che non hanno 3 persone dentro e che sono in preparazione
        String SQL = "select * from \"MATCH\" where \"preparation\"=true and (\"idP1\" is null or \"idP2\" is null or \"idP3\" is null)";
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idMatch"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    int getManche(int idMatch, int nManche) {
        String SQL = "SELECT * FROM \"MANCHE\" WHERE \"idMatch\"=" + idMatch + " AND \"nManche\"=" + nManche;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("idManche");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    synchronized void endPreparation(int idMatch) {//mette preparation a false aka il match � iniziato
        String SQL = "UPDATE \"MATCH\" SET \"preparation\"=false WHERE \"idMatch\"=" + idMatch;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    int getIdSentence(String sent) {
        String SQL = "SELECT * FROM \"SENTENCE\" WHERE \"sentence\"= '" + sent + "'";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            int id = -1;
            while (rs.next()) {
                id = rs.getInt("idSentence");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //**********************************************
    synchronized void modifySentence(Sentence n, Sentence o) {
        deleteSentence(getIdSentence(o.getSentence()));
        insertSentence(n);
    }

    //**********************************************
    ArrayList<String> getMatchHistory(int idMatch) {//ritorna tutta la cronologia mosse della partita (sar� tosta lol)
        String SQL="SELECT \"idUser\", \"move\", \"score\" FROM \"EXECUTE\" WHERE \"idMatch\"="+idMatch+" ORDER BY   \"idExecution\"";
        ArrayList<String> al = new ArrayList<String>();
        String s;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                s = "";
                s = getUserNickname(rs.getInt("idUser")) + " ha eseguito " + rs.getString("move") + " e ha ottenuto un punteggio di: " + rs.getInt("score");
                al.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    String getUserNickname(int idUser) {
        String SQL="SELECT * FROM \"USER\" WHERE \"idUser\"= "+idUser;
        String nick="";
        try (   Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            if(rs.next()) {
                nick=rs.getString("nickname");
            }
            return nick;
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERRORE OTTENIMENTO NICKNAME";
        }
    }

    //**********************************************
    int login(String email, String password) {
        String SQL="SELECT \"idUser\" FROM \"USER\" WHERE \"email\"= '"+email+"' AND \"password\"='"+password+"'";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("idUser");

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    //**********************************************
    boolean isPlayer(String email) {
        String SQL="SELECT COUNT(*) FROM \"PLAY\" WHERE \"idUser\"="+getIdUser(email);
        boolean b = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") == 1) {
                b = true;
            }
            return b;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            b = false;
            return b;
        }
    }

    //**********************************************
    String getUserName(int id) {
        String SQL="SELECT \"name\" FROM \"USER\" WHERE \"idUser\"="+id;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getString("name");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return "";
        }
    }

    //**********************************************
    String getUserSurname(int id) {
        String SQL = "SELECT * FROM \"USER\" WHERE \"idUser\"=" + id;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getString("surname");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return "";
        }
    }

    //**********************************************
    String getUserPassword(int id) {
        String SQL = "SELECT * FROM \"USER\" WHERE \"idUser\"=" + id;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getString("password");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return "";
        }
    }

    //**********************************************
    synchronized void updateUser(int id, String n, String s, String e,String ni) {
        String SQL = "update \"USER\" set \"name\"='" + n + "',\"surname\"='" + s + "', \"email\"='" + e +"',\"nickname\"='" + ni + "' where \"idUser\"=" + id;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

    //**********************************************
    synchronized void updatePassword(String p, String e) {
        String SQL = "update \"USER\" set \"password\"='" + p + "' where \"email\"='" + e + "'";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException ee) {
            ee.printStackTrace();
        }
    }

    //**********************************************
    ArrayList<Integer> globalMostPoints() {
        String SQL = "select \"idUser\", max(\"moneywon\") from \"ENROL\" group by \"idUser\" order by max desc limit 1";
        ArrayList<Integer> al = new ArrayList<Integer>();
        int i;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("idUser");
                al.add(i);
            }
            if(al.isEmpty()) {
                al.add(-1);
            }
        } catch (SQLException e) {
            System.out.println("*************************1");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> globalMostManchePlayed() {
        String SQL = "select \"idUser\", count(distinct(\"idManche\")) from \"EXECUTE\" group by \"idUser\" order by count desc";
        ArrayList<Integer> al = new ArrayList<Integer>();
        int i;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("idUser");
                al.add(i);
            }
            if(al.isEmpty()) {
                al.add(-1);
            }
        } catch (SQLException e) {
            System.out.println("*************************2");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> globalHighAvgManche() {
        String SQL = "select \"idUser\", \"idManche\", avg(\"score\") from \"EXECUTE\" group by \"idManche\", \"idUser\" order by avg desc limit 1";
        ArrayList<Integer> al = new ArrayList<Integer>();
        int i;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("idUser");
                al.add(i);
            }
            if(al.isEmpty()) {
                al.add(-1);
            }
        } catch (SQLException e) {
            System.out.println("*************************3");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> globalHighSkip() {
        String SQL = "select \"idUser\", count(*) from \"EXECUTE\" where \"move\"='skip' group by \"idUser\" order by count desc limit 1";
        ArrayList<Integer> al = new ArrayList<Integer>();
        int i;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("idUser");
                al.add(i);
            }
            if(al.isEmpty()) {
                al.add(-1);
            }
        } catch (SQLException e) {
            System.out.println("*************************4");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> globalHighCrack() {
        String SQL = "select \"idUser\", count(*) from \"EXECUTE\" where \"move\"='crack' group by \"idUser\" order by count desc limit 1";
        ArrayList<Integer> al = new ArrayList<Integer>();
        int i;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("idUser");
                al.add(i);
            }
            if(al.isEmpty()) {
                al.add(-1);
            }
        } catch (SQLException e) {
            System.out.println("*************************5");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<String> globalConsSentUser() {
        String SQL = "select * from \"EXECUTE\" where \"move\" like 'consonante%' order by \"score\" desc limit 1";
        ArrayList<String> al = new ArrayList<String>();
        String s;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                s = String.valueOf(rs.getInt("idU"));
                al.add(s);
                s = String.valueOf(rs.getInt("idManche"));
                al.add(s);
                s = rs.getString("move");
                al.add(s);
            }
            if(al.isEmpty()) {
                al.add("-1");
                al.add("-1");
                al.add("---");
            }
        } catch (SQLException e) {
            System.out.println("*************************6");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    int getIdDemandedSentence(int idManche) {
        String SQL = "SELECT * FROM \"DEMAND\" WHERE \"idManche\"=" + idManche;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("idSentence");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    ArrayList<Integer> globalAvgGuessSentence() {
        String SQL = "select count(\"idManche\") from \"EXECUTE\" group by \"idManche\" order by count desc";
        ArrayList<Integer> al = new ArrayList<Integer>();
        int i;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("count");
                al.add(i);
            }
            if(al.isEmpty()) {
                al.add(-1);
            }
        } catch (SQLException e) {
            System.out.println("*************************7");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    ArrayList<Integer> individualPlayedManche(int idUser) {
        String SQL = "select distinct \"idManche\" from \"EXECUTE\" where \"idUser\"=" + idUser;
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idManche"));

            }
            if(al.isEmpty()) {
                al.add(-1);
            }
        } catch (SQLException e) {
            System.out.println("*************************8");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    int individualPlayedMatch(int idUser) {
        String SQL = "select count (*) from \"ENROL\" where \"idUser\"=" + idUser;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("count");

            }
        } catch (SQLException e) {
            System.out.println("*************************9");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    int individualObservedManche(int idUser) {
        String SQL = "select count(\"idMatch\") from \"OBSERVE\" where \"idUser\"=" + idUser;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("count");

            }
        } catch (SQLException e) {
            System.out.println("*************************10");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    ArrayList<Integer> getObservedManche(int idUser) {
        String SQL = "select \"idMatch\" from \"OBSERVE\" where \"idUser\"=" + idUser;
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idManche"));

            }
        } catch (SQLException e) {

            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    ArrayList<Integer> getObservedMatchId(int idUser) {
        String SQL = "select distinct \"idMatch\" from \"OBSERVE\" where \"idUser\"=" + idUser;
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("idMatch"));

            }
        } catch (SQLException e) {

            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    int individualObservedMatch(int idManche) {
        String SQL = "select \"idMatch\" from \"MANCHE\" where \"idManche\"=" + idManche;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("idMatch");
            }
        } catch (SQLException e) {
            System.out.println("*************************11");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    int individualMancheWon(int idUser) {
        String SQL = "select sum(\"nmanchewon\") from \"ENROL\" where \"idUser\"=" + idUser;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("sum");
            }
        } catch (SQLException e) {
            System.out.println("*************************12");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    int individualMatchWon(int idUser) {
        String SQL = "select count(\"winner\") from \"MATCH\" where \"winner\"=" + idUser;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("count");
            }
        } catch (SQLException e) {
            System.out.println("*************************13");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    int individualAvgMoney(int idUser) {
        String SQL = "select avg(\"moneywon\") from \"ENROL\" where \"idUser\"=" + idUser;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("avg");
            }
        } catch (SQLException e) {
            System.out.println("*************************14");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    ArrayList<Integer> individualAvgSkipM(int idUser) {
        String SQL = "select count(\"idManche\") from \"EXECUTE\" where \"idUser\"=" + idUser + " and \"move\"='skip' group by \"idManche\"";
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("count"));
            }
            if(al.isEmpty()) {
                al.add(0);
            }
        } catch (SQLException e) {
            System.out.println("*************************15");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    int individualAvgSkipP(int idUser) {
        String SQL = "select avg(\"nskip\") from \"ENROL\" where \"idUser\"=" + idUser;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("avg");
            }
        } catch (SQLException e) {
            System.out.println("*************************16");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    ArrayList<Integer> individualAvgCrackM(int idUser) {
        String SQL = "select count(\"idManche\") from \"EXECUTE\" where \"idUser\"=" + idUser + " and \"move\"='crack' group by \"idManche\"";
        ArrayList<Integer> al = new ArrayList<Integer>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                al.add(rs.getInt("count"));
            }
            if(al.isEmpty()) {
                al.add(0);
            }
        } catch (SQLException e) {
            System.out.println("*************************17");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return al;
    }

    //**********************************************
    int individualAvgCrackP(int idUser) {
        String SQL = "select avg(\"ncrack\") from \"ENROL\" where \"idUser\"=" + idUser;
        int i = 0;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            while (rs.next()) {
                i = rs.getInt("avg");
            }
        } catch (SQLException e) {
            System.out.println("*************************18");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
        }
        return i;
    }

    //**********************************************
    int getIdUserByNick(String n) {
        String SQL = "SELECT \"idUser\" FROM \"USER\" WHERE \"nickname\"=\'" + n + "\'";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("idUser");
        } catch (SQLException e) {
            System.out.println("*************************19");
            e.printStackTrace();
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    synchronized void executeMove(int idUser, int idManche, int idMatch, String move, int score) {
        int id = getMaxIdExecution() + 1;
        String SQL = "insert into \"EXECUTE\" (\"idExecution\", \"idUser\", \"idManche\", \"idMatch\", \"move\", \"score\") values (" + id + ", " + idUser + ", " + idManche + ", " + idMatch + ", '" + move + "', " + score + ")";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            //System.out.println(e.getMessage() + " " + SQL);
            e.printStackTrace();
        }
    }

    //**********************************************
    int getMaxIdExecution() {
        String SQL = "SELECT MAX(\"idExecution\") FROM \"EXECUTE\"";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("MAX");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    synchronized void insertManche(int idMatch, int nManche) {//nmanche � il numero del ciclo for (idk)
        int id = getMaxIdManche() + 1;
        String SQL = "insert into \"MANCHE\" (\"idManche\", \"idMatch\", \"nManche\") values(" + id + "," + idMatch + ", " + nManche + ")";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    int getMaxIdManche() {
        String SQL = "SELECT MAX(\"idManche\") FROM \"MANCHE\"";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("MAX");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    synchronized void userObserve(int idUser, int idMatch) {
        String SQL = "insert into \"OBSERVE\" (\"idUser\", \"idMatch\") values(" + idUser + "," + idMatch + ")";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    synchronized void enrolPlayer(int idUser, int idMatch) {//
        String SQL = "insert into \"ENROL\" (\"idUser\", \"idMatch\") values (" + idUser + ", " + idMatch + ")";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //**********************************************
    synchronized void updateEnrolMoney(int idUser, int money) {
        int current = getEnrolData(idUser, "moneywon");
        int mTot = current + money;
        String SQL = "UPDATE \"ENROL\" SET \"moneywon\"=" + mTot + " WHERE \"idUser\"=" + idUser;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    synchronized void updateEnrolSkip(int idUser) {
        int current = getEnrolData(idUser, "nskip");
        int tot = current++;
        String SQL = "UPDATE \"ENROL\" SET \"nskip\"=" + tot + " WHERE \"idUser\"=" + idUser;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    synchronized void updateEnrolCrack(int idUser) {
        int current = getEnrolData(idUser, "ncrack");
        int tot = current++;
        String SQL = "UPDATE \"ENROL\" SET \"ncrack\"=" + tot + " WHERE \"idUser\"=" + idUser;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    synchronized void updateEnrolMancheWon(int idUser) {
        int current = getEnrolData(idUser, "nmanchewon");
        int tot = current++;
        String SQL = "UPDATE \"ENROL\" SET \"nmanchewon\"=" + tot + " WHERE \"idUser\"=" + idUser;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    int getEnrolData(int idUser, String data) {
        String SQL = "SELECT " + data + " FROM \"ENROL\" WHERE \"idUser\"=" + idUser;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt(data);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    synchronized int createMatch(int idP1, int idP2, int idP3, String name) {
        int id = getMaxIdMatch() + 1;
        String SQL = "insert into \"MATCH\" (\"idMatch\", \"idP1\", \"idP2\", \"idP3\", \"preparation\", \"time\", \"name\") values(" + id + ", " + idP1 + "," + idP2 + "," + idP3 + ", false, '" + System.currentTimeMillis() + "', '" + name + "' )";
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return id;
    }

    //**********************************************
    int getMaxIdMatch() {
        String SQL = "SELECT MAX(\"idMatch\") FROM \"MATCH\"";
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            return rs.getInt("MAX");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " " + SQL);
            return -1;
        }
    }

    //**********************************************
    synchronized void updateMatchWinner(int idMatch, int idWinner) {
        String SQL = "UPDATE \"MATCH\" SET \"winner\"=" + idWinner + " WHERE \"idMatch\"=" + idMatch;
        try (
                PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //**********************************************
    boolean isPlayerByNick(String n) {
        String SQL = "SELECT COUNT(*) FROM \"PLAY\" WHERE \"idUser\"=" + getIdUserByNick(n);
        boolean b = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") == 1) {
                b = true;
            }
            return b;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            b = false;
            return b;
        }
    }

    boolean isAdm(int tid)
    {
        String SQL = "SELECT COUNT(*) FROM \"MANAGE\"  WHERE \"idUser\"="+ tid;
        boolean b = false;
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(SQL);) {
            rs.next();
            if (rs.getInt("COUNT") == 1) {
                b = true;
            }
            return b;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            b = false;
            return b;
        }
    }
//**********************************************
}