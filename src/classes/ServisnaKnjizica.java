package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

import dao.LoadDatabase;

public class ServisnaKnjizica extends Identifiable {
	protected Automobil auto;
	protected ArrayList<ServisAutomobila> servisi;
	
	public ServisnaKnjizica() {
		super();
		this.auto=new Automobil();
		this.servisi=new ArrayList<ServisAutomobila>();
	}

	public ServisnaKnjizica(int id, Automobil auto, ArrayList<ServisAutomobila> servisi) {
		super(id);
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
		for(ServisAutomobila s: this.getServisi()) {
			line.add(s.getId()+"");
		}
		return line.toString();
	}
	
	
	public String SrediAuto () {
		String auto=this.getAuto().getId()+"";
		return auto;
	}
	
	public static ServisAutomobila AppendDeo(int id) throws Exception {
		var servis = LoadDatabase.sviServisi.get(id);
		return servis;
	}
	
	
	
	
	
	public static ServisnaKnjizica CreateFromString(String line) throws Exception {
		
		var sc = new Scanner(line);
		var knjizica = new ServisnaKnjizica();
		sc.useDelimiter("\\|");
		
		knjizica.setIdFromFile(sc.nextInt());
		knjizica.setAuto(ServisAutomobila.NadjiAutomobil(sc.nextInt()));
		
		ArrayList<ServisAutomobila> servisi = new ArrayList<>();
		while (sc.hasNextInt()) {
			servisi.add(AppendDeo(sc.nextInt()));
		}
		knjizica.setServisi(servisi);
		sc.close();
		
		return knjizica;
	}
	
}
