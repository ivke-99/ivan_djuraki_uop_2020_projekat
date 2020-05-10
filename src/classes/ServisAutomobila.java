package classes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import classes.Osoba.TipoviKorisnika;
import classes.Serviser.Specijalizacija;
import dao.LoadDatabase;

public class ServisAutomobila extends Identifiable {

	protected Automobil automobil;
	protected Serviser serviser;
	protected Date termin;
	protected String opis;
	protected ArrayList<ServisniDeo> deoZaServis;
	protected double cena;
	protected boolean statusServisa;
	
	public ServisAutomobila() {
		super();
		this.automobil=new Automobil();
		this.serviser=new Serviser();
		this.termin=new Date();
		this.opis="";
		this.cena=0;
		this.deoZaServis=new ArrayList<ServisniDeo>();
		this.statusServisa=false;
	}

	public ServisAutomobila(int id, Automobil automobil, Serviser serviser, Date termin, String opis,
			boolean statusServisa  ,ArrayList<ServisniDeo> deoZaServis, double cena, boolean deleted) {
		super(id, deleted);
		this.automobil = automobil;
		this.serviser = serviser;
		this.termin = termin;
		this.opis = opis;
		this.cena = cena;
		this.statusServisa = statusServisa;
		this.deoZaServis = deoZaServis;
	}

	public Automobil getAutomobil() {
		return automobil;
	}
	
	
	
	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Serviser getServiser() {
		return serviser;
	}

	public void setServiser(Serviser serviser) {
		this.serviser = serviser;
	}

	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<ServisniDeo> getDeoZaServis() {
		return deoZaServis;
	}

	public void setDeoZaServis(ArrayList<ServisniDeo> deoZaServis) {
		this.deoZaServis = deoZaServis;
	}
	
	public String isStatusServisaString() {
		if (this.statusServisa == true) {
			return "Zakazan";
		}
		else {
			return "Zavrsen";
		}
	}

	public boolean isStatusServisa() {
		return statusServisa;
	}

	public void setStatusServisa(boolean statusServisa) {
		this.statusServisa = statusServisa;
	}
	
	
	public static String ConvertDateToString(Date date) {
		/*convert date to string */
		String stringDate;
		String pattern = "dd/MM/yyyy HH:mm";
		DateFormat df = new SimpleDateFormat(pattern);
		stringDate= df.format(date);
		return stringDate;
	}
	
	public static Date ConvertStringToDate(String stringDate) throws ParseException {
		try {
		String pattern = "dd/MM/yyyy HH:mm";
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = df.parse(stringDate);
		return date;
		}catch(Exception none) {
			return null;
		}
	}
	
	
	@Override
	public String WriteToString() {
		var line = new StringJoiner("|");
		line.add(this.getId()+"");
		line.add(this.automobil.getId()+"");
		try {
		line.add(this.serviser.getId()+"");
		}catch(Exception e1) {
			line.add("null");
		}
		try {
		line.add(ConvertDateToString(this.getTermin()));
		}catch(Exception e2) {
			line.add("null");
		}
		line.add(this.getOpis());
		line.add(this.getCena()+"");
		line.add(this.isStatusServisa()+"");
		line.add(this.isDeleted()+"");
		try {
		
		for(ServisniDeo s: this.getDeoZaServis()) {
			line.add(s.getId()+"");
		}
		}catch(Exception e4) {
			line.add("null");
		}
		return line.toString();
	}
	
	
	public static Automobil NadjiAutomobil (int id) throws Exception {
		try {
		var auto = LoadDatabase.sviAutomobili.get(id);
		return auto;
		}catch(Exception e) {
			return null;
		}
		
	}
	
	public static Serviser NadjiServisera (int id) throws Exception {
		try {
		var serviser = LoadDatabase.sviServiseri.get(id);
		return serviser;
		}catch(Exception e) {
			return null;
		}
		
		
	}
	
	
	public static ServisniDeo AppendDeo(int id) throws Exception {
		try {
		var deo = LoadDatabase.sviDelovi.get(id);
		return deo;
		}catch(Exception e) {
			return null;
		}
	}
	
	

	public static ServisAutomobila CreateFromString(String line) throws Exception {
	
		var sc = new Scanner(line);
		var servis = new ServisAutomobila();
		sc.useDelimiter("\\|");
		
		servis.setIdFromFile(sc.nextInt());
		servis.setAutomobil(NadjiAutomobil(sc.nextInt()));
		try {
		servis.setServiser(NadjiServisera(sc.nextInt()));
		}catch(Exception e5) {
			servis.setServiser(null);
			sc.skip("null");
		}
		try {
		servis.setTermin(ConvertStringToDate(sc.next()));
		}catch(Exception e6) {
			servis.setTermin(null);
			sc.skip("null");
		}
		servis.setOpis(sc.next());
		servis.setCena(sc.nextDouble());
		servis.setStatusServisa(sc.nextBoolean());
		servis.setDeleted(sc.nextBoolean());
		try {
		ArrayList<ServisniDeo> delovi = new ArrayList<>();
		while (sc.hasNextInt()) {
			delovi.add(AppendDeo(sc.nextInt()));
		}
		servis.setDeoZaServis(delovi);
		/* set lista delova */
		}catch(Exception e6) {
			servis.setDeoZaServis(null);
		}
		sc.close();
		
		return servis;
	}

	@Override
	public String toString() {
		return "id=" + id + ", marka=" + this.automobil.getMarka() + "";
	}
	
	
	
	
	
}
