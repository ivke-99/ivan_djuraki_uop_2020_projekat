package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.swing.JOptionPane;

import classes.Automobil;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.ServisnaKnjizica;
import classes.ServisniDeo;
import dao.LoadDatabase;

public class SpecificObjectCreation {
	/* ova klasa je trenutno potrebna, jer kreiranje objekta iz comboboxa
	 * pravi odredjene probleme
	 */
	
	public static void CreateServisFromUserInput(ServisAutomobila s) {
			
			
			
			var line=new StringJoiner("|");
			line.add(s.getId()+"");
			line.add(s.getAutomobil().getId()+"");
			line.add(s.getServiser().getId()+"");
			line.add(ServisAutomobila.ConvertDateToString(s.getTermin()));
			line.add(s.getOpis());
			line.add(s.getCena()+"");
			line.add(s.isStatusServisa()+"");
			for(ServisniDeo sa : s.getDeoZaServis()) {
				line.add(sa.getId()+"");
			}
			FileHandling.WriteToFile(line.toString(), FileHandling.servisAutomobilaPath);
			
			try {
				ServisnaKnjizica.UpdateKnjizica(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}

