package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

import classes.Osoba.TipoviKorisnika;
import dao.LoadDatabase;


public class Automobil extends Identifiable {
	

	protected Musterija vlasnik;
	private int godinaProizvodnje;
	private int kubikazaMotora;
	private int snagaMotora;
	private VrstaGoriva gorivo;
	protected MarkaiModel marka;
	
	
	public enum VrstaGoriva{
		benzin,
		dizel,
		voda,
		plin,
		benzinPlin,
		none
	}
	
	public enum MarkaiModel{ 
		peugot_307,
		fiat_punto,
		volkswagen_golf5,
		mercedes_cla,
		bmw_e39,
		none
	}
	
	
	public Automobil() {
		super();
		this.vlasnik=new Musterija();
		this.godinaProizvodnje=0;
		this.kubikazaMotora=0;
		this.snagaMotora=0;
		this.gorivo=VrstaGoriva.none;
		this.marka=MarkaiModel.none;
	}

	public Automobil(int id, Musterija vlasnik, int godinaProizvodnje, int kubikazaMotora, int snagaMotora,
			VrstaGoriva gorivo, MarkaiModel marka) {
		super(id);
		this.vlasnik = vlasnik;
		this.godinaProizvodnje = godinaProizvodnje;
		this.kubikazaMotora = kubikazaMotora;
		this.snagaMotora = snagaMotora;
		this.gorivo = gorivo;
		this.marka = marka;
	}

	public Musterija getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Musterija vlasnik) {
		this.vlasnik = vlasnik;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) throws Exception{
		if( godinaProizvodnje < 1800 || godinaProizvodnje > 2050 ) {
		throw new Exception("Godina proizvodnje mora biti izmedju 1800 i 2050 godine.");
		}
		else {
			this.godinaProizvodnje = godinaProizvodnje;
		}
	}

	public int getKubikazaMotora() {
		return kubikazaMotora;
	}

	public void setKubikazaMotora(int kubikazaMotora) throws Exception{
		if(kubikazaMotora == 0 || kubikazaMotora > 100000) {
			throw new Exception("Ne moze biti manja od 0, ni veca od 100k.");
		}
		else {
		this.kubikazaMotora = kubikazaMotora;
		}
	}

	public int getSnagaMotora() {
		return snagaMotora;
	}

	public void setSnagaMotora(int snagaMotora) throws Exception{
		if(snagaMotora == 0 || snagaMotora > 10000) {
			throw new Exception("Ne moze biti manja od 0, ni veca od 10k.");
		}
		this.snagaMotora = snagaMotora;
	}

	public VrstaGoriva getGorivo() {
		return gorivo;
	}

	public void setGorivo(VrstaGoriva gorivo) {
		this.gorivo = gorivo;
	}

	

	public MarkaiModel getMarka() {
		return marka;
	}

	public void setMarka(MarkaiModel marka) {
		this.marka = marka;
	}

	@Override
	public String WriteToString() {
		var line = new StringJoiner("|");
		line.add(this.getId()+"").add(this.srediVlasnika()).add(this.getGodinaProizvodnje()+"").add(this.getGorivo()+"");
		line.add(this.getKubikazaMotora()+"").add(this.getMarka()+"").add(this.getSnagaMotora()+"");
		return line.toString();
	}
	
	public String srediVlasnika () {
		String vlasnik=this.getVlasnik().getId()+"";
		return vlasnik;
	}
	
	public static Musterija NadjiVlasnika (int id) throws Exception {
		/* pull from map*/
		var vlasnik = LoadDatabase.sveMusterije.get(id);
		return vlasnik;
		
	}
	
	public static Automobil CreateFromString(String line) throws Exception {
		 
		var sc = new Scanner(line);
		var auto = new Automobil();
		sc.useDelimiter("\\|");
		
		auto.setIdFromFile(sc.nextInt());
		auto.setVlasnik(NadjiVlasnika(sc.nextInt()));
		auto.setGodinaProizvodnje(sc.nextInt());
		auto.setGorivo(VrstaGoriva.valueOf(sc.next()));
		auto.setKubikazaMotora(sc.nextInt());
		auto.setMarka(MarkaiModel.valueOf(sc.next()));
		auto.setSnagaMotora(sc.nextInt());
		
		
		sc.close();
		
		return auto;
	}
	
	@Override
	public String toString() {
		return "[marka=" + marka + ", id=" + id + "]";
	}
}
