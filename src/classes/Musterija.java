package classes;

import java.util.Scanner;
import java.util.StringJoiner;

import classes.Osoba.TipoviKorisnika;

public class Musterija extends Osoba {
	private int brojBodova;
	
	
	
	public Musterija() {
		super();
		this.brojBodova=0;
	}

	public Musterija(int id, String ime, String prezime, String jmbg, boolean pol, String adresa, String brtel,
			String korisnickoIme, String lozinka, TipoviKorisnika tipkorisnika, int brojBodova) {
		super(id, ime, prezime, jmbg, pol, adresa, brtel, korisnickoIme, lozinka, tipkorisnika);
		this.brojBodova=brojBodova;
	}

	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}

	
	@Override
	public String WriteToString() {
		var line = new StringJoiner("|");
		line.add(super.WriteToString()).add(this.getBrojBodova()+"");
		return line.toString();
	}
	
	
	public static Musterija CreateFromString(String line) throws Exception {
		var sc = new Scanner(line);
		var musterija = new Musterija();
		sc.useDelimiter("\\|");
		
		musterija.setIdFromFile(sc.nextInt());
		musterija.setIme(sc.next());
		musterija.setPrezime(sc.next());
		musterija.setJmbg(sc.next());
		if (sc.next().equals("musko")) {
			musterija.setPol(true);
		}
		else {
			musterija.setPol(false);
		}
		musterija.setAdresa(sc.next());
		musterija.setBrtel(sc.next());
		musterija.setKorisnickoIme(sc.next());
		musterija.setLozinka(sc.next());
		musterija.setTipkorisnika(TipoviKorisnika.musterija);
		musterija.setBrojBodova(sc.nextInt());
		
		sc.close();
		
		return musterija;
	}

	@Override
	public String toString() {
		return "" + korisnickoIme + ", id=" + id + "";
	}
	
	
}
