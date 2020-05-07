package controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import classes.Admin;
import classes.Automobil;
import classes.Automobil.MarkaiModel;
import classes.Automobil.VrstaGoriva;
import classes.Musterija;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.Serviser.Specijalizacija;
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
	
	public static void PopuniComboBoxMarkaiModel(JComboBox<Automobil.MarkaiModel> cbMarkaiModel) {
		for (MarkaiModel a : Automobil.MarkaiModel.values()) 
			if (!(a.equals(MarkaiModel.none))) {
				cbMarkaiModel.addItem(a);
			}
	}
	
	public static void PopuniComboBoxGorivo(JComboBox<Automobil.VrstaGoriva> cbTipGoriva) {
		for (VrstaGoriva a : Automobil.VrstaGoriva.values()) 
			if (!(a.equals(VrstaGoriva.none))) {
				cbTipGoriva.addItem(a);
			}
	}
	
	public static void PopuniServiseZaServisera (JComboBox<ServisAutomobila> cbServis ) {
		
		
		
		
		ArrayList<ServisAutomobila> servisi = (ArrayList<ServisAutomobila>)LoadDatabase.sviServisi.entrySet().stream().filter(f -> f.getValue().getServiser() != null 
				&& f.getValue().getServiser().getId() == 
				LoginHandling.trenutniKorisnik.getId()).map(g -> (ServisAutomobila)g.getValue()).collect(Collectors.toList());
		
		for (ServisAutomobila servis : servisi) {
			cbServis.addItem(servis);
		}
	}
	
	public static void PopuniComboBoxMusterija (JComboBox<Musterija> cbMusterija)  {
		ArrayList<Musterija> musterije = (ArrayList<Musterija>) LoadDatabase.sveMusterije.entrySet().stream().map(f -> (Musterija)f.getValue()).collect(Collectors.toList());
		for(Musterija musterija : musterije) {
			cbMusterija.addItem(musterija);
		}
	}
	
	public static void PopuniComboBoxAdmin (JComboBox<Admin> cbAdmin) {
		ArrayList<Admin> admini = (ArrayList<Admin>) LoadDatabase.sviAdmini.entrySet().stream().map(f -> (Admin)f.getValue()).collect(Collectors.toList());
		for(Admin admin : admini) {
			cbAdmin.addItem(admin);
		}
	}
	
	public static void PopuniSpecijalizaciju(JComboBox<Serviser.Specijalizacija> cbSpecijalizacija) {
		/*ArrayList<Serviser.Specijalizacija> serviserSpec = (ArrayList<Serviser.Specijalizacija>) LoadDatabase.sviServiseri.entrySet().stream().map(f -> f.getValue().getSpec()).collect(Collectors.toList());*/
		for (Specijalizacija s : Serviser.Specijalizacija.values()) 
			if (!(s.equals(Specijalizacija.none))) {
					cbSpecijalizacija.addItem(s);
			}
	}
	
	public static void PopuniComboBoxSviServiseri(JComboBox<Serviser> cbServiser) {
		ArrayList<Serviser> serviseri = (ArrayList<Serviser>) LoadDatabase.sviServiseri.entrySet().stream().map(f -> (Serviser)f.getValue()).collect(Collectors.toList());
		for(Serviser serviser : serviseri) {
			cbServiser.addItem(serviser);
		}
	}
	
	public static boolean PrintOpcija() {
		int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
		if (opcija != 1) {
			return true;
		}
		
		else {
			return false;
		}
	}
}
