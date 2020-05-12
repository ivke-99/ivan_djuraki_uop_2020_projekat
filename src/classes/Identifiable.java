package classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


import dao.WriteToStringInterface;

public abstract class Identifiable implements WriteToStringInterface {
	protected int id;
	protected boolean deleted;
	
	public Identifiable() {
		this.id=0;
		this.deleted=false;
	}

	public Identifiable(int id,boolean deleted) {
		super();
		this.id = id;
		this.deleted = deleted;
	}
	

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}
	
	/*koristi se u prilici kreiranja hash-mape odnosno initial loada, iz tog 
	 * razloga moram imati 2 settera za ID
	 */
	public void setIdFromFile(int id) {
		this.id=id;
	}
	
	
	/*osnovni odnosno najbitniji setter za ID,otvara fajl
	 *  cita zadnji i vraca zadnji +1*/
	
	public void setId(String filePath) throws Exception {
		 List<String> lines = Collections.emptyList();
		    try {
		      lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		    } catch (IOException e) {
		      System.out.println("Error 404: File does not exist.");
		    }
		    
		    
		    /*postavljanje osnovnog tj. prvog id*/
		    if (lines.isEmpty()) {
		    	this.id=0;
		    	}
		    
		    
	else { /*generisanje id-a*/
		    String[] LastId=lines.get(lines.size()-1).split("\\|");
		    int newId=0;
		    newId=newId+Integer.parseInt(LastId[0])+1;
		    this.id = newId;
		    }
		    
	}
	
	
	
}
