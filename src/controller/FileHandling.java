package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import dao.LoadDatabase;

public class FileHandling {
	
	public static String defaultPath= ".\\src\\database\\";
	public static String musterijaPath= defaultPath+"\\musterija.txt";
	public static String adminPath= defaultPath+"\\admin.txt";
	public static String automobilPath= defaultPath+"\\automobil.txt";
	public static String servisAutomobilaPath= defaultPath+"\\servisAutomobila.txt";
	public static String serviserPath= defaultPath+"\\serviser.txt";
	public static String servisnaKnjizicaPath= defaultPath+"\\servisnaKnjizica.txt";
	public static String servisniDeoPath= defaultPath+"\\servisniDeo.txt";
	
	public static void WriteToFile(String object,String filePath) {
		Writer wr;
		try {
			wr = new FileWriter(filePath, true);
			boolean vr = CheckIfFirstLine(filePath);
			if( vr == false) {
			wr.append("\n"+object);
			}
			
			else {
				wr.append(object);
			}
			wr.flush();
			wr.close();
		} catch (IOException e) {
				/**/
			e.printStackTrace();
		}
		
	}
	
	public static boolean CheckIfFirstLine(String filePath) throws IOException {
		
		
		String line = LoadDatabase.LoadLinesFromFile(filePath);
		if(line.equals("")) {
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public static void OverWriteFile(String object,String filePath) {
		Writer wr;
		try {
			wr = new FileWriter(filePath);
			wr.write(object);
			wr.flush();
			wr.close();
		} catch (IOException e) {
			/**/
			e.printStackTrace();
		}
	}
	
	
	public static boolean ReplaceLineInFile(String oldLine,String newLine,String filePath) {
		String lines="";
		try {
			lines = LoadDatabase.LoadLinesFromFile(filePath);
		} catch (IOException e1) {
			return false;
		}
		
		try {
			String[] NewLines = lines.split("\n");
			for( String line : NewLines) {
				if(line.equals(oldLine)) 
					lines = lines.replace(oldLine, newLine);	
			}
			FileHandling.OverWriteFile(lines, filePath);
		
		}catch(Exception e2) {
			e2.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
}
