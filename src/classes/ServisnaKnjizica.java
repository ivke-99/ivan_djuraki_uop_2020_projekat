package classes;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import controller.FileHandling;
import dao.LoadDatabase;

public class ServisnaKnjizica extends Identifiable {
	protected Automobil auto;
	protected ArrayList<ServisAutomobila> servisi;
	
	public ServisnaKnjizica() {
		super();
		this.auto=new Automobil();
		this.servisi=new ArrayList<ServisAutomobila>();
	}

	public ServisnaKnjizica(int id, Automobil auto, ArrayList<ServisAutomobila> servisi, boolean deleted) {
		super(id, deleted);
		this.auto = auto;
		this.servisi = servisi;
	}

	public Automobil getAuto() {
		return auto;
	}

	public void setAuto(Automobil auto) {
		this.auto = auto;
	}

	public ArrayList<ServisAutomobila> getServisi() {
		return servisi;
	}

	public void setServisi(ArrayList<ServisAutomobila> servisi) {
		this.servisi = servisi;
	}

	@Override
	public String WriteToString() {
		var line=new StringJoiner("|");
		line.add(this.getId()+"").add(this.SrediAuto());
		line.add(this.isDeleted()+"");
		for(ServisAutomobila s: this.getServisi()) {
			line.add(s.getId()+"");
		}
		
		return line.toString();
	}
	
	
	public String SrediAuto () {
		String auto=this.getAuto().getId()+"";
		return auto;
	}
	
	public static ServisAutomobila NadjiServisAutomobila(int id) throws Exception {
		var servis = LoadDatabase.sviServisi.get(id);
		return servis;
	}
	
	public ServisnaKnjizica AddServis(ServisnaKnjizica serv,ServisAutomobila s) {
		var servisi=serv.getServisi();
		servisi.add(s);
		serv.setServisi(servisi);
		return serv;
	}
	
	
	public static void UpdateKnjizica(ServisAutomobila s) throws Exception {
		
		/*prilikom dodavanja novog servisa automobila automatski se u knjizicu upisuje 
		 * isti servis, ideja je da se servisna knjizica ne moze dirati, ne moze se u nju direktno dodavati
		 * vec se moze samo obrisati
		 */
		
		LoadDatabase.UcitajCeluBazu();
		ServisnaKnjizica knjiz = null;
		
		/*trazimo knjizicu u hash mapi */
		
		knjiz = (ServisnaKnjizica) LoadDatabase.sveKnjizice.entrySet().stream().filter(f-> 
		f.getValue().getAuto().getId() == s.getAutomobil().getId())
				.map(g -> (ServisnaKnjizica)g.getValue()).findFirst().get();
		
		
		/*stara linija*/
		String oldLine= knjiz.WriteToString();
		
		/*zameni staru knjizicu sa novom u hashmapi*/
		if(knjiz.getAuto().getId() == s.getAutomobil().getId()) {
			knjiz.AddServis(knjiz, s);
			LoadDatabase.sveKnjizice.replace(knjiz.getId(), knjiz);
			
			
			/*upisi updateovanu u fajl*/
			String lines = LoadDatabase.LoadLinesFromFile(FileHandling.servisnaKnjizicaPath);
			
			String[] NewLines = lines.split("\n");
			for( String line : NewLines) {
	
				if(line.equals(oldLine)) {
					line = line + "|" + s.getId()+"";
					lines = lines.replace(oldLine, line);
				}
				
			}
			/*za sada cemo da overwritujemo ceo fajl
			 * medjutim bi bilo mnogo bolje kad bi tacno dirali tu liniju zbog
			 * brzeg rada programa, no polako
			 */
			FileHandling.OverWriteFile(lines, FileHandling.servisnaKnjizicaPath);
			
			}
		
		
		else {
			/*ovo ne bi trebalo nikad da se dogodi, ali cemo ga ostaviti za sada 
			 * radi debugginga
			 */
			System.out.println("Relacije izmedju klasa ne valjaju.");
		}
	
	}
	
	public static void NewKnjizicaForAutomobil(Automobil a) throws Exception {
		
			/*prilikom kreiranja automobila on automatski dobija svoju servisnu knjizicu
			 * u kojoj su servisi postavljeni na vrednost null
			 */
			ServisnaKnjizica newk = new ServisnaKnjizica();
			newk.setId(FileHandling.servisnaKnjizicaPath);
			newk.setAuto(a);
			newk.setServisi(null);
			String write2=newk.WriteToString();
			FileHandling.WriteToFile(write2, FileHandling.servisnaKnjizicaPath);
		
		
	}
	
	
	
	
	public static ServisnaKnjizica CreateFromString(String line) throws Exception {
		
		var sc = new Scanner(line);
		var knjizica = new ServisnaKnjizica();
		sc.useDelimiter("\\|");
		
		knjizica.setIdFromFile(sc.nextInt());
		knjizica.setAuto(ServisAutomobila.NadjiAutomobil(sc.nextInt()));
		knjizica.setDeleted(sc.nextBoolean());
		try {
		ArrayList<ServisAutomobila> servisi = new ArrayList<>();
		while (sc.hasNextInt()) {
			servisi.add(NadjiServisAutomobila(sc.nextInt()));
		}
		knjizica.setServisi(servisi);
		}catch(Exception e1) {
			knjizica.setServisi(null);
		}
		sc.close();
		
		return knjizica;
	}

	
	
	
	
}
