package RdF;
import java.io.Serializable;
import java.util.ArrayList;

public class Sentence implements Serializable {
		public String sent, theme;
		int id;
		//***********************************************
		public Sentence() {}
		public Sentence(String s, String t) {
			sent=s;
			theme=t;
		}
		public Sentence(String s, String t, int id) {
			sent=s;
			theme=t;
			this.id=id;
		}
		//***********************************************
		String getSentence() {
			return sent;
		}
		//***********************************************
		String getTheme() {
			return theme;
		}
		//***********************************************
		int getId() {
			return id;
		}
		//***********************************************
		public String toString() {
			return theme+" - "+sent;
		}
		//***********************************************
		public static boolean checkLength(String sent)
		{
			if(sent.length()>60) {
				return false;
			}
			else {
				return true;
			}
		}
		//***********************************************
		public boolean nullSent() {
			if(sent.equals("")) {
				return false;
			}
			else {
				return true;
			}
		}
		//***********************************************
		public boolean checkSymbol() {//***************************da fare
			boolean b=true;
			return b;
		}
		//***********************************************
		public boolean checkTheme() {
			return !theme.isEmpty();
		}
		//***********************************************
		public void upCase() {
			sent=sent.toUpperCase();
			theme=theme.toUpperCase();
		}
		//***********************************************
		/*public static void main(String []args) {
			String stringa="ciao";
			System.out.println(stringa.length());
			System.out.println(stringa.isEmpty());
			Sentence s=new Sentence();
			ArrayList<Letter> al=new ArrayList<Letter>();
			al=s.getArray();
			System.out.println(al.remove(0).getLetter());
			System.out.println(al.remove(0).getLetter());
			System.out.println(al.remove(0).getLetter());
			System.out.println(al.remove(0).getLetter());
		}*/
		
		
		
	}//fine class
