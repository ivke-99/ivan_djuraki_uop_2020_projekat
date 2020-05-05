package controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JComboBox;

import classes.Automobil;
import classes.Musterija;
import dao.LoadDatabase;

public class FillingControl {
	
	public static void PopuniComboBoxAuto(JComboBox<Automobil> cbAuto) {
		/*koristi se za musteriju i servisera */
		ArrayList<Automobil> automobili = (ArrayList<Automobil>)LoadDatabase.sviAutomobili.entrySet().stream().filter(f -> f.getValue().getVlasnik().getId() == 
				LoginHandling.trenutniKorisnik.getId()).map(g -> (Automobil)g.getValue()).collect(Collectors.toList());
		
		for (Automobil automobil : automobili) {
			cbAuto.addItem(automobil);
		}
	}
}
