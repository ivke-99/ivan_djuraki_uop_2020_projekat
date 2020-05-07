package classes;

import java.io.IOException;
import java.util.StringJoiner;

import controller.FileHandling;
import controller.Validator;
import dao.LoadDatabase;

public abstract class Osoba extends Identifiable {
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected boolean pol;
	protected String adresa;
	protected String brtel;
	protected String korisnickoIme;
	protected String lozinka;
	protected TipoviKorisnika tipkorisnika;
	
	
	public enum TipoviKorisnika {
		admin,
		musterija,
		serviser,
		none
	}

	public Osoba() {
		super();
		this.ime="";
		this.prezime="";
		this.jmbg="";
		this.pol=false;
		this.adresa="";
		this.brtel="";
		this.korisnickoIme="";
		this.lozinka="";
		this.tipkorisnika=TipoviKorisnika.none;
	}

	public Osoba(int id, String ime, String prezime, String jmbg, boolean pol, String adresa, String brtel,
			String korisnickoIme, String lozinka, TipoviKorisnika tipkorisnika) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.brtel = brtel;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.tipkorisnika = tipkorisnika;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) throws Exception {
		if (ime.matches(super.STRING_PATTERN)) {
			this.ime=ime;
		}
		else {
			throw new Exception("Ime nije pravilno uneseno.");
		}
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) throws Exception {
		if (prezime.matches(super.STRING_PATTERN)) {
			this.prezime = prezime;
		}
		else {
			throw new Exception("Prezime nije pravilno uneseno.");
		}
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) throws Exception {/*za ovo mozda napravim checker ako mi bude dosadno*/
		if (jmbg.length() != 13) {
			throw new Exception("JMBG mora imati 13 karaktera.");
	}
		else {
			this.jmbg = jmbg;
		}
	}

	public String isPol() { 
		if (pol == true) {
			return "musko";
		}
		else {
			return "zensko";
		}
	}
	/*temp setter*/
	public String isPolTemp() {
		if (pol == true) {
			return "true";
		}
		else {
			return "false";
		}
	}

	public void setPol(boolean pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) throws Exception { 
		if(!adresa.equals("")) {
			this.adresa = adresa;
		}
		
		else {
			throw new Exception("Niste ispravno uneli adresu.");
		}
	}

	public String getBrtel() {
		return brtel;
	}

	public void setBrtel(String brtel) {/* za ovo ce se praviti setter prilikom izrade GUIa */
		this.brtel = brtel;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	

	public void setKorisnickoIme(String korisnickoIme) throws Exception {
		if(korisnickoIme.matches(super.USERNAME_PATTERN)) {
			
			if(Validator.CheckForIme(korisnickoIme) == true) {
				this.korisnickoIme = korisnickoIme;
				
			}
			
		}
		
		else {
			throw new Exception("Korisnicko ime ili vec postoji, ili nije pravilno uneseno.");
		}
	}
	
	/*mora postojati zbog izmene, mislice da pokusavam da dodam novo korisnicko ime i odma ce ga odbiti*/
	public void setKorisnickoImeUnique(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) throws Exception {
		/*Lozinka mora imati mala i velika slova,jedan broj,min 6 karaktera*/
		/*if(lozinka.equals(super.PASSWORD_PATTERN)) {
			
		}*/
		/*else {
			throw new Exception("Lozinka mora sadrzati mala i velika slova,jedan broj,jedan specijalni karakter i mora biti duzine min 6 karaktera.");
		}*/
		this.lozinka = lozinka;
	}

	public TipoviKorisnika getTipkorisnika() {
		return tipkorisnika;
	}

	public void setTipkorisnika(TipoviKorisnika tipkorisnika) {
		this.tipkorisnika = tipkorisnika;
	}
	
	
	@Override
	public String WriteToString() {
		
		var line = new StringJoiner("|");
		line.add(this.getId()+"").add(this.getIme()).add(this.getPrezime()).add(this.getJmbg()).add(this.isPolTemp()).add(this.getAdresa()).add(this.getBrtel());
		line.add(this.getKorisnickoIme()).add(this.getLozinka());
		return line.toString();
		
	}

	@Override
	public String toString() {
		return "" + korisnickoIme + ", id=" + id + "";
	}
}




