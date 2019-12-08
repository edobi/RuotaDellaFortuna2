package RdF;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Registration {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    char tipo;
    String name, surname, email, nickname, psw, activation;
    int id;
    long hour;
    DatabaseConnector db = new DatabaseConnector(activation, activation, activation);//qua poi va tolto che deve passare tutto dal server

    //**********************************************
    public Registration() {
    }

    //**********************************************
    public void setName(String s) {
        name = s;
    }

    //**********************************************
    public void setSurname(String s) {
        surname = s;
    }

    //**********************************************
    public void setEmail(String s) {
        email = s;
    }

    //**********************************************
    public boolean checkEmail(String s) {
        return db.checkEmail(s);
    }

    //**********************************************
    public void setNickname(String s) {
        nickname = s;
    }

    //**********************************************
    public boolean checkNickname(String s) {
        return db.checkNickname(s);
    }

    //**********************************************
    public void setPassword(String s) {
        psw = s;
    }

    //**********************************************
    public void setId() {
        id = db.getMaxIdUser() + 1;
    }

    //**********************************************
    public void setHour() {
        hour = System.currentTimeMillis();
    }

    //**********************************************
    public void createActivation() {
        final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int LENGHT = 8;

        StringBuilder s = new StringBuilder(LENGHT);
        int max = ALPHABET.length();
        int min = 0;
        int range = max - min + 1;
        for (int y = 0; y < LENGHT; y++) {
            int i = (int) (Math.random() * range) + min;
            s.append(ALPHABET.charAt(i));
        }
        activation = s.toString();
    }

    //**********************************************
    public boolean validEmail(String mail) {
        boolean mailOk = true;
        try {
            InternetAddress emailAddr = new InternetAddress(mail);
            emailAddr.validate();
        } catch (AddressException e) {
            mailOk = false;
        }
        return mailOk;
    }

    //**********************************************
    public void registerPlayer() {
        db.registerUser(id, name, surname, email, psw, nickname, activation, hour);
        db.registerPlayer(id);
    }

    //**********************************************
    public void registerAdmin() {
        db.registerUser(id, name, surname, email, psw, nickname, activation, hour);
        db.registerAdmin(id);
    }
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
//**********************************************
/*
	public static void main(String []args) throws IOException {
		Registration r=new Registration('P');
		
	}
*/
}//fine class
