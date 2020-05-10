package classes;

import java.util.Scanner;
import java.util.StringJoiner;

import classes.Automobil.MarkaiModel;
import classes.Automobil.VrstaGoriva;

public class ServisniDeo extends Identifiable {
	
	protected String nazivDela;
	protected double cena;
	protected MarkaiModel marka;
	
	public ServisniDeo() {
		super();
		this.nazivDela="";
		this.cena=0;
		this.marka=marka.none;
	}

	public ServisniDeo(int id, String nazivDela, double cena, MarkaiModel marka, boolean deleted) {
		super(id, deleted);
		this.nazivDela = nazivDela;
		this.cena = cena;
		this.marka = marka;
	}

	public String getNazivDela() {
		return nazivDela;
	}

	public void setNazivDela(String nazivDela) {
		this.nazivDela = nazivDela;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
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
		line.add(this.getId()+"").add(this.getNazivDela()).add(this.getCena()+"");
		line.add(this.isDeleted()+"");
		line.add(this.getMarka()+"");
		return line.toString();
	}
	
	public static ServisniDeo CreateFromString(String line) throws Exception {
		
		 
		var sc = new Scanner(line);
		var servisnideo = new ServisniDeo();
		sc.useDelimiter("\\|");
		servisnideo.setIdFromFile(sc.nextInt());
		servisnideo.setNazivDela(sc.next());
		servisnideo.setCena(sc.nextDouble());
		servisnideo.setMarka(MarkaiModel.valueOf(sc.next()));
		servisnideo.setDeleted(sc.nextBoolean());
		sc.close();
		
		return servisnideo;
	}

	@Override
	public String toString() {
		return "" + nazivDela + "";
	}

	
	
	
}
