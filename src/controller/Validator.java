package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import dao.LoadDatabase;

public class Validator {
	/* takodje potpuno bespotrebno svi regexi i cik pogodi jos necu da ih obrisem */
	/*
	 * ime moze imati izmedju 1 i 25 chareva, moze poceti sa[a ili z] i tako se
	 * zavrsiti,nema spec karaktera i nema brojeva
	 */
	public static final String STRING_PATTERN = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";

	/*
	 * korisnicko ime moze imati slova od a do z brojeve od 0 do 9, od 0 do 30
	 * karaktera i ne moze imati specijalne karaktere
	 */
	public static final String USERNAME_PATTERN = "[a-zA-Z0-9\\._\\-]{1,30}";

	/*
	 * sifra mora imati min 6 znakova, jedan specijalni jedno veliko slovo mala
	 * slova i broj
	 */
	public static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,40}";

	/*
	 * skace iz hashmape u hashmapu dok ne nadje korisnicko ime ako se ne podudara
	 * pusta da prodje
	 */
	public static boolean CheckForIme(String korisnickoIme) throws Exception {
		boolean existence = true;

		try {
			LoadDatabase.sviAdmini.entrySet().stream()
					.filter(ad -> ad.getValue().getKorisnickoIme().equals(korisnickoIme)).findAny().get();
			existence = false;

		} catch (Exception ad) {
			existence = true;

			try {
				LoadDatabase.sviServiseri.entrySet().stream()
						.filter(sa -> sa.getValue().getKorisnickoIme().equals(korisnickoIme)).findAny().get();
				existence = false;
			} catch (Exception sa) {
				if (existence != false)
					existence = true;

				try {
					LoadDatabase.sveMusterije.entrySet().stream()
							.filter(mu -> mu.getValue().getKorisnickoIme().equals(korisnickoIme)).findAny().get();
					existence = false;

				} catch (Exception mu) {
					if (existence != false)
						existence = true;
				}

			}

		}

		return existence;
	}
	
	@SuppressWarnings("unused")
	public static boolean isThisDateValid(String dateToValidate, String dateFromat){

		if(dateToValidate == null){
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			Date date = sdf.parse(dateToValidate);

		} catch (ParseException e) {

			return false;
		}

		return true;
	}

}
