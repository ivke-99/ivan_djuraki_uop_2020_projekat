package controller;
import javax.swing.JOptionPane;

import classes.Admin;
import classes.Osoba;
import classes.Osoba.TipoviKorisnika;
import classes.Serviser;
import dao.LoadDatabase;
import view.AdminMain;
import view.MainWindow;
import view.MusterijaMain;
import view.ServiserMain;


public class LoginHandling {
	public static Osoba trenutniKorisnik = null;
	
	
	public static Osoba CheckLogin1(String username,String password) throws Exception {
	Osoba o = null;
	LoadDatabase.UcitajCeluBazu();
	
	try {
	o = (Osoba) LoadDatabase.sviAdmini.entrySet().stream().filter(f -> ((Osoba) f.getValue()).getKorisnickoIme().equals(username) 
			&& ((Osoba) f.getValue()).getLozinka().equals(password)).map(f -> ((Osoba) f.getValue())).findFirst().get();
	} catch(Exception f) {
		
		
		try {
			
		
			o = (Osoba) LoadDatabase.sviServiseri.entrySet().stream().filter(l -> ((Osoba) l.getValue()).getKorisnickoIme().equals(username) 
					&& ((Osoba) l.getValue()).getLozinka().equals(password)).map(l -> ((Osoba) l.getValue())).findFirst().get();
		} catch(Exception l) {
				
			try {
				
				o = (Osoba) LoadDatabase.sveMusterije.entrySet().stream().filter(m -> ((Osoba) m.getValue()).getKorisnickoIme().equals(username) 
						&& ((Osoba) m.getValue()).getLozinka().equals(password)).map(m -> ((Osoba) m.getValue())).findFirst().get();
				
				
			}catch(Exception m) {
				return null;
			}
		
		}
	}
	return o;
}
	
	
	
		
	public static void CheckLogin(String username,String password) throws Exception {
		
		trenutniKorisnik=CheckLogin1(username,password);
		
		if (trenutniKorisnik != null) {
			switch (trenutniKorisnik.getTipkorisnika()) {
			case admin:
				new AdminMain().setVisible(true);
				break;
			
			case serviser:
				new ServiserMain().setVisible(true);
				break;
				
			case musterija:
				new MusterijaMain().setVisible(true);
				break;
			
				
			}
		}
		
		else {
			JOptionPane.showMessageDialog(null, "Pogresan username ili pw.");
		}
			
		
	}
	
	
	
}
