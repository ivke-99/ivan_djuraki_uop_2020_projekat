package controller;



import dao.LoadDatabase;

public class Validator {
	/*
	 * skace iz hashmape u hashmapu dok ne nadje korisnicko ime ako
	 * se ne podudara pusta da prodje
	 */
	public static boolean CheckForIme(String korisnickoIme) throws Exception {
		boolean existence = true;
		
		
		try {
			LoadDatabase.sviAdmini.entrySet().stream().filter(ad -> ad.getValue().getKorisnickoIme()
					.equals(korisnickoIme)).findAny().get();
			existence = false;
			
		}catch(Exception ad) {
				existence = true;
			
				try {
					LoadDatabase.sviServiseri.entrySet().stream().filter(sa -> sa.getValue().getKorisnickoIme()
							.equals(korisnickoIme)).findAny().get();
					existence = false;
				}catch(Exception sa) {
					if (existence!=false)
						existence=true;
					
					try {
						LoadDatabase.sveMusterije.entrySet().stream().filter(mu -> mu.getValue().getKorisnickoIme()
								.equals(korisnickoIme)).findAny().get();
						existence=false;
						
					}catch(Exception mu) {
						if (existence != false)
							existence = true;
					}
					
				}
			
		}
			
		return existence;
	}
	
	
}
