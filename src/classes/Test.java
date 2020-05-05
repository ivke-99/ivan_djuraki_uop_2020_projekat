package classes;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import classes.Automobil.MarkaiModel;
import classes.Automobil.VrstaGoriva;
import classes.Osoba.TipoviKorisnika;
import classes.Serviser.Specijalizacija;
import controller.FileHandling;
import dao.LoadDatabase;
public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*testing instances*/
		
		
		Admin a=new Admin();
		a.setId(FileHandling.adminPath);
		a.setIme("Ivan");
		a.setPrezime("Djuraki");
		a.setAdresa("Kis Ferenca 16");
		a.setBrtel("+381621756295");
		a.setJmbg("0312999800094");
		a.setKorisnickoIme("ivke99");
		a.setLozinka("Ivancar123.");
		a.setPlata(50000);
		a.setPol(true);
		a.setTipkorisnika(TipoviKorisnika.admin);
		/*
		String writing;
		writing=a.WriteToString()+'\n';
		System.out.println(writing);
		controller.FileHandling.WriteToFile(writing,controller.FileHandling.adminPath);
		
		*/
		/*
		String line1= dao.LoadDatabase.LoadLinesFromFile(controller.FileHandling.musterijaPath);
		LoadDatabase.CreateAllMusterijaFromFile(line1);
		
		String line=dao.LoadDatabase.LoadLinesFromFile(controller.FileHandling.automobilPath);
		LoadDatabase.CreateAllAutomobilFromFile(line);
		String line3=dao.LoadDatabase.LoadLinesFromFile(controller.FileHandling.serviserPath);
		LoadDatabase.CreateAllServiserFromFile(line3);
		*/
		
		Musterija mus=new Musterija();
		mus.setId(FileHandling.musterijaPath);
		mus.setAdresa("Marka Oreskovica 25");
		mus.setBrtel("+38142333212");
		mus.setBrojBodova(55);
		mus.setIme("Marina");
		mus.setJmbg("0312999800094");
		mus.setKorisnickoIme("marina232");
		mus.setLozinka("marina1234");
		mus.setPol(false);
		mus.setPrezime("Marinovic");
		mus.setTipkorisnika(TipoviKorisnika.musterija);
		
		Serviser serviser1=new Serviser();
		serviser1.setIme("Dejan");
		serviser1.setPrezime("Zarkovic");
		serviser1.setAdresa("Neka adresa 23");
		serviser1.setBrtel("032122223");
		serviser1.setId(FileHandling.serviserPath);
		serviser1.setJmbg("0312999800094");
		serviser1.setKorisnickoIme("dejca1222");
		serviser1.setLozinka("dejca123");
		serviser1.setPlata(23000);
		serviser1.setPol(true);
		serviser1.setSpec(Specijalizacija.automehanicar);
		serviser1.setTipkorisnika(TipoviKorisnika.serviser);
		
		Automobil auto=new Automobil();
		auto.setId(FileHandling.automobilPath);
		auto.setGodinaProizvodnje(2002);
		auto.setGorivo(VrstaGoriva.benzin);
		auto.setKubikazaMotora(1300);
		auto.setMarka(MarkaiModel.bmw_e39);
		auto.setSnagaMotora(55);
		auto.setVlasnik(mus);
		
		ServisniDeo s1=new ServisniDeo();
		s1.setId(FileHandling.servisniDeoPath);
		s1.setCena(5000);
		s1.setMarka(MarkaiModel.bmw_e39);
		s1.setNazivDela("osigurac");
		
		ServisniDeo s2=new ServisniDeo();
		s2.setId(FileHandling.servisniDeoPath);
		s2.setCena(1000);
		s2.setMarka(MarkaiModel.bmw_e39);
		s2.setNazivDela("zamajac");
		
		
		ServisAutomobila servis1=new ServisAutomobila();
		servis1.setId(FileHandling.servisAutomobilaPath);
		servis1.setAutomobil(auto);
		Date d = Calendar.getInstance().getTime();
		servis1.setTermin(d);
		ArrayList<ServisniDeo> lista = new ArrayList<ServisniDeo>();
		lista.add(s1);
		lista.add(s2);
		servis1.setDeoZaServis(lista);
		servis1.setOpis("menja se zamajac i neki osigurac");
		servis1.setServiser(serviser1);
		servis1.setStatusServisa(true);
		/*
		String nekistring=servis1.ConvertDateToString(servis1.getTermin());
		System.out.println(nekistring);
		*/
		/*
		String nekiString="22/05/2020 15:30";
		System.out.println(servis1.ConvertDateToString(servis1.getTermin()));
		System.out.println(servis1.ConvertStringToDate(nekiString));
		
		
		*/
		LoadDatabase.UcitajCeluBazu();
		
		
		
		/**
		
		
		
		
		Admin a1=new Admin();
		a1.setId(FileHandling.adminPath);
		a1.setIme("Marko");
		a1.setPrezime("Saric");
		a1.setAdresa("Neka Adresa");
		a1.setBrtel("+3816244423");
		a1.setJmbg("0312999800094");
		a1.setKorisnickoIme("markodarko99");
		a1.setLozinka("marko123");
		a1.setPlata(32000);
		a1.setPol(false);
		a1.setTipkorisnika(TipoviKorisnika.admin);
		
		
		
		
		
		
		
		/*set termin*/
		
		/*
		
		
		
		
		/*
		Admin a=(Admin) Admin.CreateFromString();
		System.out.println(a.getAdresa());
		*/
		
		
		
		/*Admin a=(Admin) Admin.CreateFromString(line);*/
	
		
		/*
		String line=dao.LoadDatabase.LoadLinesFromFile(controller.FileHandling.adminPath);
		LoadDatabase.CreateAdminsFromFile(line);
		
		*/
		
	}
	
}
