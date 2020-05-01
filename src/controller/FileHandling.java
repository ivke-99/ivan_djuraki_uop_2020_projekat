package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Scanner;

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
			wr.append(object);
			wr.flush();
			wr.close();
		} catch (IOException e) {
				/**/
			e.printStackTrace();
		}
		
	}
	
	
	public static void CreateFile(String fileName) {
		File file=new File(defaultPath+fileName);
	}
	
	public static void CheckExistence(String fileName) {
		/* check file existence*/
		
	}
	
}
