package Csv;

	import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import RdF.Sentence;

	public class ReadCsv {
		static int i;
		static String[] sentenceCsv;
		static String line;
		static ArrayList<Sentence> arr= new ArrayList<Sentence>();
		static Scanner scan= new Scanner(System.in);
		
		public ReadCsv(){
			
		}
		
	    public static ArrayList<Sentence> importSentences(String percorso) {
	    	percorso= percorso.replace("\\" , "/");//conversione degli slash del percorso
	        String csvFile = percorso ;//Prendo percorso file csv
	        line = "";
	        String cvsSplitBy = ";";//definisco elemento per dividere
	        File sentenceFile= new File(percorso);
		        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {//creo buffer per leggere
		        	
		            while ((line = br.readLine()) != null) {//finche la linea non è nulla
		            	System.out.println(line);
		                sentenceCsv = line.split(cvsSplitBy);//frase con elementi separati da ;
		                String arg= sentenceCsv[0].toString();//elemento in prima "colonna
		                String sent =sentenceCsv[1].toString();//elemento in seconda "colonna"
		                if(arg.length()>0 && sent.length()>0) {
		                		Sentence sen=new Sentence(arg, sent);//creo frase
		               			arr.add(sen);}
		                else {
		                	JOptionPane.showMessageDialog(null, "Errore nell'importazione delle frasi. Controllare file");
		                }
		            	
		            	
		            }
		      
		        } catch (IOException e) { }
		     return arr;
			
	}
	    	
	}

	