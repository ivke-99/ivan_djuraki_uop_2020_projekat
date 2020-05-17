package dao;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

import classes.Admin;
import classes.Automobil;
import classes.Musterija;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.ServisnaKnjizica;
import classes.ServisniDeo;
import controller.FileHandling;

public class LoadDatabase {
	public static HashMap<Integer, Admin> sviAdmini = null;
	public static HashMap<Integer, Musterija> sveMusterije = null;
	public static HashMap<Integer, Automobil> sviAutomobili = null;
	public static HashMap<Integer, ServisAutomobila> sviServisi = null;
	public static HashMap<Integer, Serviser> sviServiseri = null;
	public static HashMap<Integer, ServisnaKnjizica> sveKnjizice = null;
	public static HashMap<Integer, ServisniDeo> sviDelovi = null;
	
	
	public static void UcitajCeluBazu() throws Exception {
		
		LoadAllAdmin();
		LoadAllMusterija();
		LoadAllServiser();
		LoadAllAutomobil();
		LoadAllServisniDeo();
		LoadAllServisAutomobila();
		LoadAllServisnaKnjizica();
	}	
	
	public static void LoadAllAdmin() throws Exception {
		String line = LoadLinesFromFile(FileHandling.adminPath);
		sviAdmini = CreateAdminsFromFile(line); 
	}
	
	public static void LoadAllAutomobil() throws Exception {
		String line = LoadLinesFromFile(FileHandling.automobilPath);
		sviAutomobili = CreateAllAutomobilFromFile(line); 
	}
	
	public static void LoadAllMusterija() throws Exception {
		String line=LoadLinesFromFile(FileHandling.musterijaPath);
		sveMusterije = CreateAllMusterijaFromFile(line);
	}
	
	public static void LoadAllServisAutomobila() throws Exception {
		String line=LoadLinesFromFile(FileHandling.servisAutomobilaPath);
		sviServisi = CreateAllServisAutomobilaFromFile(line);
	}
	
	public static void LoadAllServiser() throws Exception {
		String line=LoadLinesFromFile(FileHandling.serviserPath);
		sviServiseri = CreateAllServiserFromFile(line);
	}
	
	public static void LoadAllServisnaKnjizica() throws Exception {
		String line=LoadLinesFromFile(FileHandling.servisnaKnjizicaPath);
		sveKnjizice = CreateAllServisnaKnjizicaFromFile(line);
	}
	
	public static void LoadAllServisniDeo() throws Exception {
		String line=LoadLinesFromFile(FileHandling.servisniDeoPath);
		sviDelovi = CreateAllServisniDeoFromFile(line);
	}
	
	
	
	
	public static String LoadLinesFromFile(String filePath) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(filePath));
		String lines;
		lines =  bf.lines().collect(Collectors.joining("\n"));
		bf.close();
		return lines;
	}
	
	
	public static HashMap<Integer,Admin> CreateAdminsFromFile(String lines) throws Exception {
		String[] NewLines = lines.split("\n");
		HashMap<Integer,Admin> sviAdmini=new HashMap<>();
		for (int i=0;i<NewLines.length;i++) {
			var admin=Admin.CreateFromString(NewLines[i]);
			if(!(admin.isDeleted() == true))
				sviAdmini.put(admin.getId(), admin); 
		}
		
		return sviAdmini;
	}
	
	public static HashMap<Integer,Musterija> CreateAllMusterijaFromFile(String lines) throws Exception {
		String[] NewLines = lines.split("\n");
		HashMap<Integer,Musterija> sveMusterije=new HashMap<>();
		try {
		for (int i=0;i<NewLines.length;i++) {
			var musterija=Musterija.CreateFromString(NewLines[i]);
			if(!(musterija.isDeleted() == true))
				sveMusterije.put(musterija.getId(), musterija); 
		}
		}catch(Exception none) {
			return sveMusterije;
		}
		return sveMusterije;
	}
	
	public static HashMap<Integer,Serviser> CreateAllServiserFromFile(String lines) throws Exception {
		String[] NewLines = lines.split("\n");
		HashMap<Integer,Serviser> sviServiseri=new HashMap<>();
		try {
		for (int i=0;i<NewLines.length;i++) {
			var serviser=Serviser.CreateFromString(NewLines[i]);
			if(!(serviser.isDeleted() == true))
				sviServiseri.put(serviser.getId(), serviser); 
		}
		}catch(Exception none) {
			return sviServiseri;
		}
		return sviServiseri;
	}
	
	public static HashMap<Integer,Automobil> CreateAllAutomobilFromFile(String lines) throws Exception {
		String[] NewLines = lines.split("\n");
		HashMap<Integer,Automobil> sviAutomobili=new HashMap<>();
		try {
		for (int i=0;i<NewLines.length;i++) {
			var auto=Automobil.CreateFromString(NewLines[i]);
			if(!(auto.isDeleted() == true))
				sviAutomobili.put(auto.getId(), auto);
		}
		}catch(Exception none) {
			return sviAutomobili;
		}
		return sviAutomobili;
	}
	
	public static HashMap<Integer,ServisniDeo> CreateAllServisniDeoFromFile(String lines) throws Exception {
		String[] NewLines = lines.split("\n");
		HashMap<Integer,ServisniDeo> sviServisniDelovi=new HashMap<>();
		try {
		for (int i=0;i<NewLines.length;i++) {
			var deo=ServisniDeo.CreateFromString(NewLines[i]);
			if(!(deo.isDeleted() == true))
				sviServisniDelovi.put(deo.getId(), deo);
		}
		}catch(Exception none) {
			return sviServisniDelovi;
		}
		return sviServisniDelovi;
	}
	
	public static HashMap<Integer,ServisAutomobila> CreateAllServisAutomobilaFromFile(String lines) throws Exception {
		String[] NewLines = lines.split("\n");
		HashMap<Integer,ServisAutomobila> sviServisiAutomobila=new HashMap<>();
		try {
		for (int i=0;i<NewLines.length;i++) {
			var servis=ServisAutomobila.CreateFromString(NewLines[i]);
			if(!(servis.isDeleted() == true))
				sviServisiAutomobila.put(servis.getId(), servis);
		}
		}catch(Exception none) {
			return sviServisiAutomobila;
		}
		return sviServisiAutomobila;
	}
	
	public static HashMap<Integer,ServisnaKnjizica> CreateAllServisnaKnjizicaFromFile(String lines) throws Exception {
		String[] NewLines = lines.split("\n");
		HashMap<Integer,ServisnaKnjizica> sveServisneKnjizice=new HashMap<>();
		try {
		for (int i=0;i<NewLines.length;i++) {
			var knjizica=ServisnaKnjizica.CreateFromString(NewLines[i]);
			if(!(knjizica.isDeleted() == true))
				sveServisneKnjizice.put(knjizica.getId(), knjizica);
		}
		}catch(Exception none) {
			return sveServisneKnjizice;
		}
		
		return sveServisneKnjizice;
	}
	
}
