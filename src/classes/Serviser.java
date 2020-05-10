package classes;

import java.util.Scanner;
import java.util.StringJoiner;

import classes.Osoba.TipoviKorisnika;

public class Serviser extends Osoba {
	
	public enum Specijalizacija {
		automehanicar,
		autoelektricar,
		vulkanizer,
		limar,
		none
	}
	
	private Specijalizacija spec;
	private int plata;
	
	
	public Serviser() {
		super();
		this.spec=Specijalizacija.none;
		this.plata=0;
	}

	public Serviser(int id, String ime, String prezime, String jmbg, boolean pol, String adresa, String brtel,
			String korisnickoIme, String lozinka, TipoviKorisnika tipkorisnika, Specijalizacija spec, int plata, boolean deleted) {
		super(id, ime, prezime, jmbg, pol, adresa, brtel, korisnickoIme, lozinka, tipkorisnika, deleted);
		this.plata=plata;
		this.spec=spec;
	}

	public Specijalizacija getSpec() {
		return spec;
	}

	public void setSpec(Specijalizacija spec) {
		this.spec = spec;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	@Override
	public String WriteToString() {
		var line = new StringJoiner("|");
		line.add(super.WriteToString()).add(this.getSpec()+"").add(this.getPlata()+"");
		return line.toString();
	}
	
	public static Serviser CreateFromString(String line) throws Exception {
		var sc = new Scanner(line);
		var serviser = new Serviser();
		sc.useDelimiter("\\|");
		
		serviser.setIdFromFile(sc.nextInt());
		serviser.setIme(sc.next());
		serviser.setPrezime(sc.next());
		serviser.setJmbg(sc.next());
		serviser.setPol(sc.nextBoolean());
		serviser.setAdresa(sc.next());
		serviser.setBrtel(sc.next());
		serviser.setKorisnickoIme(sc.next());
		serviser.setLozinka(sc.next());
		serviser.setTipkorisnika(TipoviKorisnika.serviser);
		serviser.setSpec(Specijalizacija.valueOf(sc.next()));
		serviser.setDeleted(sc.nextBoolean());
		serviser.setPlata(sc.nextInt());
		
		sc.close();
		
		return serviser;
	}
}
