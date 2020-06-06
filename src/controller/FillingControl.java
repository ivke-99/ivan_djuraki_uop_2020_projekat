package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import classes.Admin;
import classes.Automobil;
import classes.Automobil.MarkaiModel;
import classes.Automobil.VrstaGoriva;
import classes.Musterija;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.ServisniDeo;
import classes.Serviser.Specijalizacija;
import dao.LoadDatabase;

public class FillingControl {

	public static void PopuniComboBoxAuto(JComboBox<Automobil> cbAuto) {
		/* koristi se za musteriju */
		ArrayList<Automobil> automobili = (ArrayList<Automobil>) LoadDatabase.sviAutomobili.entrySet().stream()
				.filter(f -> f.getValue().getVlasnik().getId() == LoginHandling.trenutniKorisnik.getId())
				.map(g -> (Automobil) g.getValue()).collect(Collectors.toList());

		for (Automobil automobil : automobili) {
			cbAuto.addItem(automobil);
		}
	}

	public static void PopuniComboBoxSviAutomobili(JComboBox<Automobil> cbAuto) {
		LoadDatabase.sviAutomobili.values().stream().forEach(f -> cbAuto.addItem(f));
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

	public static void PopuniServiseZaServisera(JComboBox<ServisAutomobila> cbServis) {

		ArrayList<ServisAutomobila> servisi = (ArrayList<ServisAutomobila>) LoadDatabase.sviServisi.entrySet().stream()
				.filter(f -> f.getValue().getServiser() != null
						&& f.getValue().getServiser().getId() == LoginHandling.trenutniKorisnik.getId())
				.map(g -> (ServisAutomobila) g.getValue()).collect(Collectors.toList());

		for (ServisAutomobila servis : servisi) {
			cbServis.addItem(servis);
		}
	}

	public static void PopuniComboBoxMusterija(JComboBox<Musterija> cbMusterija) {
		LoadDatabase.sveMusterije.values().stream().forEach(f -> cbMusterija.addItem(f));
	}

	public static void PopuniComboBoxAdmin(JComboBox<Admin> cbAdmin) {
		LoadDatabase.sviAdmini.values().stream().forEach(f -> cbAdmin.addItem(f));
	}

	public static void PopuniComboBoxSviServiseri(JComboBox<Serviser> cbServiser) {
		LoadDatabase.sviServiseri.values().stream().forEach(f -> cbServiser.addItem(f));
	}

	public static void PopuniSpecijalizaciju(JComboBox<Serviser.Specijalizacija> cbSpecijalizacija) {
		for (Specijalizacija s : Serviser.Specijalizacija.values())
			if (!(s.equals(Specijalizacija.none))) {
				cbSpecijalizacija.addItem(s);
			}
	}

	public static boolean PrintOpcija() {
		int opcija = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Izaberite opciju",
				JOptionPane.YES_NO_OPTION);
		if (opcija != 1) {
			return true;
		}

		else {
			return false;
		}
	}

	public static void PopuniListuDelova(JList<ServisniDeo> lista, DefaultListModel<ServisniDeo> listModel,
			JComboBox<Automobil> cbAuto) {
		for (HashMap.Entry<Integer, ServisniDeo> entry : LoadDatabase.sviDelovi.entrySet()) {

			var auto = (Automobil) cbAuto.getSelectedItem();
			if (auto.getMarka().equals(entry.getValue().getMarka())) {
				listModel.addElement(entry.getValue());
			}
		}

	}

	public static List<ServisniDeo> PopuniListuDelova2(JList<ServisniDeo> lista,
			DefaultListModel<ServisniDeo> listModel, JComboBox<ServisAutomobila> cbServis) {

		var servis = (ServisAutomobila) cbServis.getSelectedItem();

		var listadelova = LoadDatabase.sviDelovi.values().stream()
				.filter(f -> servis.getAutomobil().getMarka().equals(f.getMarka())).collect(Collectors.toList());
		listadelova.stream().forEach(d -> listModel.addElement(d));
		return listadelova;

	}

	public static void PopuniComboDelovi(JComboBox<ServisniDeo> cbDeo) {
		LoadDatabase.sviDelovi.values().stream().forEach(f -> cbDeo.addItem(f));
	}

	public static void PopuniNeZakazaneServise(JComboBox<ServisAutomobila> cbServis) {

		for (HashMap.Entry<Integer, ServisAutomobila> entry : LoadDatabase.sviServisi.entrySet()) {

			if (entry.getValue().getServiser() == null) {
				cbServis.addItem(entry.getValue());
			}
		}
	}

	public static void PopuniSveZakazaneServise(JComboBox<ServisAutomobila> cbServis) {
		for (HashMap.Entry<Integer, ServisAutomobila> entry : LoadDatabase.sviServisi.entrySet()) {
			if (entry.getValue().getServiser() != null) {
				cbServis.addItem((ServisAutomobila) entry.getValue());
			}
		}
	}

	public static List<ServisniDeo> PopuniDeloveSimetrija() {
		var listaDelova = LoadDatabase.sviDelovi.values().stream()
				.filter(f -> f.getNazivDela().contains("Desna Strana") || f.getNazivDela().contains("Leva Strana"))
				.collect(Collectors.toList());
		return listaDelova;
	}

	public static void FiltrirajListu(List<ServisniDeo> listaDelova, MarkaiModel currentAuto,
			JComboBox<ServisniDeo> cbDeo) {
		for (ServisniDeo s : listaDelova) {
			try {
				if (s.getMarka().equals(currentAuto))
					cbDeo.addItem(s);
			} catch (Exception nula) {
				/* mora biti ovde jer auto moze da nema deo */
			}
		}
	}

}
