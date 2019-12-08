package RdF;

import java.util.ArrayList;
import java.util.Scanner;

public class Ordine {
	static Scanner scan= new Scanner(System.in);
	static int i;
	public static void main(String[] args) {
		ArrayList arr = new ArrayList(3);
		arr.size();
		for(i=0; i<4; i++) {
			arr.add(i, "Cane");
			System.out.println(i);
		}	
		
		System.out.println("Scegli l'elemento da eliminare: ");
		String del = scan.nextLine();
		int delete= Integer.parseInt(del);
		arr.remove(delete);
		ordina(arr);
	}
		public static void ordina(ArrayList array) {
			System.out.println("Array dopo eliminazione "+array.size());
			for(i=0; i<array.size();i++) {
				System.out.println(array.get(i));
			}
		}
		
		
	
}
