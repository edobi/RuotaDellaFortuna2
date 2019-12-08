package RdF;

public class Player {
	

	private static int score;
	static int user;
	static boolean jolly;
//--------------------------------------------------------------------------------------------------
	
	public Player(int iD) {
		this.user=iD;
		this.jolly=false;
		this.score=0;
		
	}
	
//--------------------------------------------------------------------------------------------------
	
	public static boolean get_jolly(){
		return jolly;
	}
	
//--------------------------------------------------------------------------------------------------
	
	public static void set_jolly() {
		jolly=true;
	}
	
//--------------------------------------------------------------------------------------------------
	public static void reset_jolly() {
		jolly=false;
	}
	
//--------------------------------------------------------------------------------------------------
	
	public static int get_money() {
		return score;
	}
	
//--------------------------------------------------------------------------------------------------
	
	public void change_money(int points) {
		score= points;
	}
	
//--------------------------------------------------------------------------------------------------
	public static int getIdUser() {
		return user;
	}
	
}
