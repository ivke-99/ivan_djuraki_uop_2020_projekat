package controller;

import javax.swing.JOptionPane;

import classes.Musterija;
import classes.ServisAutomobila;
import dao.LoadDatabase;

public class CenaHandling {
	protected static double cenaDelova;
	protected static double smanjenaCena;
	
	public static Double IzracunajCenu(ServisAutomobila currentServis) {
		cenaDelova=0;
		currentServis.getDeoZaServis().stream().forEach(g -> cenaDelova = cenaDelova + g.getCena());
		return cenaDelova;
	}
	
	public static Double SmanjiCenu(double cena,Musterija m) {
		
		
			smanjenaCena = cena;
			for(int i = 0; i<=m.getBrojBodova(); i++) {
				smanjenaCena = cena*0.98;
			}
		
		
		return smanjenaCena;
	}
	
	public static void UvecajBodove(Musterija m) {
		if( m.getBrojBodova() < 10) {
			String oldLine = m.WriteToString();
			m.setBrojBodova(m.getBrojBodova()+1);
			String newLine = m.WriteToString();
			LoadDatabase.sveMusterije.replace(m.getId(), m);
			FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.musterijaPath);
		}
		else {
			JOptionPane.showMessageDialog(null, "Musterija ima maksimalan broj bodova.");
		}
	}
	
	public static void NulirajBodove(Musterija m) {
		String oldLine = m.WriteToString();
		m.setBrojBodova(0);
		String newLine = m.WriteToString();
		LoadDatabase.sveMusterije.replace(m.getId(), m);
		FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.musterijaPath);
	}
}
