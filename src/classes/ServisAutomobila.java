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
	protected boolean statusServisa;
	
	public ServisAutomobila() {
		super();
		this.automobil=new Automobil();
		this.serviser=new Serviser();
		this.termin=new Date();
		this.opis="";
		this.deoZaServis=new ArrayList<ServisniDeo>();
		this.statusServisa=false;
	}

	public ServisAutomobila(int id, Automobil automobil, Serviser serviser, Date termin, String opis,
			boolean statusServisa  ,ArrayList<ServisniDeo> deoZaServis) {
		super(id);
		this.automobil = automobil;
		this.serviser = serviser;
		this.termin = termin;
		this.opis = opis;
		this.statusServisa = statusServisa;
		this.deoZaServis = deoZaServis;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
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
		String pattern = "dd/MM/yyyy HH:mm";
		DateFormat df = new SimpleDateFormat(pattern);
		Date date = df.parse(stringDate);
		return date;
	}
	
	
	@Override
	public String WriteToString() {
		var line = new StringJoiner("|");
		line.add(this.automobil.getId()+"").add(this.serviser.getId()+"").add(this.getTermin()+"").add(this.getOpis());/*(this.ListToString(this.getDeoZaServis()));*/
		/*ovo ce ovako dok ne bude bolje*/
		line.add(this.isStatusServisa()+"");
		for(ServisniDeo s: this.getDeoZaServis()) {
			line.add(s.getId()+"");
		}
		return line.toString();
	}
	
	
	public static Automobil NadjiAutomobil (int id) throws Exception {
		var auto = LoadDatabase.sviAutomobili.get(id);
		return auto;
		
	}
	
	public static Serviser NadjiServisera (int id) throws Exception {
		var serviser = LoadDatabase.sviServiseri.get(id);
		return serviser;
		
	}
	
	
	public static ServisniDeo AppendDeo(int id) throws Exception {		
		var deo = LoadDatabase.sviDelovi.get(id);
		return deo;
	}
	
	

	public static ServisAutomobila CreateFromString(String line) throws Exception {
	
		var sc = new Scanner(line);
		var servis = new ServisAutomobila();
		sc.useDelimiter("\\|");
		
		servis.setIdFromFile(sc.nextInt());
		servis.setAutomobil(NadjiAutomobil(sc.nextInt()));
		servis.setServiser(NadjiServisera(sc.nextInt()));
		servis.setTermin(ConvertStringToDate(sc.next()));
		servis.setOpis(sc.next());
		servis.setStatusServisa(sc.nextBoolean());
		ArrayList<ServisniDeo> delovi = new ArrayList<>();
		while (sc.hasNextInt()) {
			delovi.add(AppendDeo(sc.nextInt()));
		}
		servis.setDeoZaServis(delovi);
		/* set lista delova */
		sc.close();
		
		return servis;
	}	
	
	
}
