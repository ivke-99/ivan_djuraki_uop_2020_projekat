package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import classes.Automobil;
import classes.Identifiable;
import classes.Musterija;
import classes.ServisAutomobila;
import classes.ServisnaKnjizica;
import controller.FileHandling;

public class DeleteDAO {
	protected static ArrayList<Automobil> sviAutiMusterije = null;
	/*hijerarhija brisanja objekata(veze) === Automobil -> Servis automobila ->Servisna Knjizica
	 * 										  Musterija -> Automobil -> Servis Automobila -> Servisna Knjizica
	 * Sve instance klasa se mogu manipulisati sem servisne knjizice, koja se moze samo pregledati 									  
	 * Obrisati zavrsen servis moze samo admin, ne zavrsen moze i serviser
	 * Sve ostale instance klasa ne bi trebalo da imaju nikakvu vezu ni sa cim drugim
	 */
	
	public static void DeleteAutomobil (Automobil brisanje) {
		try {
			LoadDatabase.UcitajCeluBazu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*brisanje servisne knjizice vezane za automobil*/
		ServisnaKnjizica knjizZaBrisanje =  (ServisnaKnjizica) LoadDatabase.sveKnjizice.entrySet().stream().filter(f -> f.getValue().getAuto().getId()
				== brisanje.getId()).map(gb -> (ServisnaKnjizica)gb.getValue()).findAny().get();
	
		String oldLine = knjizZaBrisanje.WriteToString();
		knjizZaBrisanje.setDeleted(true);
		String newLine = knjizZaBrisanje.WriteToString();
		LoadDatabase.sveKnjizice.remove(((Identifiable) knjizZaBrisanje).getId());
		FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.servisnaKnjizicaPath);
		
		/*obrisi sve servise vezane za izabrani auto*/
		ArrayList<ServisAutomobila> servisiZaBrisanje = (ArrayList<ServisAutomobila>) LoadDatabase.sviServisi.entrySet().stream()
				.filter(f -> f.getValue().getAutomobil().getId() == brisanje.getId())
				.map(g -> (ServisAutomobila)g.getValue()).collect(Collectors.toList());
		
		if (!(servisiZaBrisanje.isEmpty())) {
		for(ServisAutomobila s : servisiZaBrisanje) {
			String o = s.WriteToString();
			s.setDeleted(true);
			String n = s.WriteToString();
			LoadDatabase.sviServisi.remove(((Identifiable) s).getId());
			FileHandling.ReplaceLineInFile(o, n, FileHandling.servisAutomobilaPath);
		}
		}
		
		/*i tek na kraju brisanje automobila*/
		String old = brisanje.WriteToString();
		brisanje.setDeleted(true);
		String newl = brisanje.WriteToString();
		LoadDatabase.sviAutomobili.remove(((Identifiable) brisanje).getId());
		FileHandling.ReplaceLineInFile(old, newl, FileHandling.automobilPath);
	}
	
	public static void DeleteMusterija (Musterija brisanje) {
		
		for(Automobil a : LoadDatabase.sviAutomobili.values()) {
			
			try {
				/*ovo ce tek da pravi problem al za sada radi*/
			if(a.getVlasnik().getId() == brisanje.getId()) {
				DeleteAutomobil(a);
			}
			}catch(Exception programnevalja) {
				
			}
		}
		/*koristimo gornju funkciju da obrisemo automobile i sve ostalo*/
		
		/*brisemo musteriju*/
		String old = brisanje.WriteToString();
		brisanje.setDeleted(true);
		String newl = brisanje.WriteToString();
		LoadDatabase.sveMusterije.remove(((Identifiable) brisanje).getId());
		FileHandling.ReplaceLineInFile(old, newl, FileHandling.musterijaPath);
		
		}
	
}
