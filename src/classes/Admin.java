

package classes;

import java.util.Scanner;
import java.util.StringJoiner;


public class Admin extends Osoba {
	private int plata;
	public Admin() {
		super();
		this.plata=0;
	}

	public Admin(int id, String ime, String prezime, String jmbg, boolean pol, String adresa, String brtel,
			String korisnickoIme, String lozinka, TipoviKorisnika tipkorisnika, int plata) {
		super(id, ime, prezime, jmbg, pol, adresa, brtel, korisnickoIme, lozinka, tipkorisnika);
		this.plata=plata;
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
		line.add(super.WriteToString()).add(this.getPlata()+"");
		return line.toString();
	}
	
	
	public static Admin CreateFromString(String line) throws Exception {
		 
		var sc = new Scanner(line);
		var admin = new Admin();
		sc.useDelimiter("\\|");
		
		admin.setIdFromFile(sc.nextInt());
		admin.setIme(sc.next());
		admin.setPrezime(sc.next());
		admin.setJmbg(sc.next());
		admin.setPol(Boolean.parseBoolean(sc.next()));
		admin.setAdresa(sc.next());
		admin.setBrtel(sc.next());
		admin.setKorisnickoIme(sc.next());
		admin.setLozinka(sc.next());
		admin.setTipkorisnika(TipoviKorisnika.admin);
		admin.setPlata(sc.nextInt());
		
		sc.close();
		
		return admin;
	}
	
}
