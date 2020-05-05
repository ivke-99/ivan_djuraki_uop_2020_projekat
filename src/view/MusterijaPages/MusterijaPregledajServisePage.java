package view.MusterijaPages;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.Automobil;
import classes.ServisAutomobila;
import controller.FillingControl;
import controller.LoginHandling;
import dao.LoadDatabase;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class MusterijaPregledajServisePage extends JDialog {
	private static String delovi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusterijaPregledajServisePage dialog = new MusterijaPregledajServisePage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public MusterijaPregledajServisePage() {
		setTitle("Servisna Knjizica");
		setBounds(100, 100, 644, 437);
		getContentPane().setLayout(null);
		
		JComboBox<Automobil> cbAuto = new JComboBox<Automobil>();
		cbAuto.setBounds(169, 65, 211, 22);
		getContentPane().add(cbAuto);
		
		FillingControl.PopuniComboBoxAuto(cbAuto);
		
		JTable t = new JTable(toTableModel(LoadDatabase.sviServisi,cbAuto));
		
		
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(37, 127, 548, 193);
		getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Izaberite auto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(37, 66, 122, 14);
		getContentPane().add(lblNewLabel);
		
		
		
		JButton btnNewButton = new JButton("Izadji");
		btnNewButton.setBounds(529, 364, 89, 23);
		getContentPane().add(btnNewButton);
		
		

	}
	
	
	
	public static TableModel toTableModel(HashMap<Integer,ServisAutomobila> map,JComboBox<Automobil> cbAuto) {
		/*still in work*/
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Id Servisa", "Automobil", "Termin", "Opis", "Status servisa", "Delovi za servis"}, 0
	    );
	    var autocombo = (Automobil)cbAuto.getSelectedItem();
	   
	    
	    for (HashMap.Entry<Integer,ServisAutomobila> entry : map.entrySet()) {
	    	
	    	if(entry.getValue().getAutomobil().getVlasnik().getId() == LoginHandling.trenutniKorisnik.getId()) 
	    	{
	    	delovi="";
	    	for(int i=0; i<entry.getValue().getDeoZaServis().size(); i++) {
	    		
	    		if ( i == entry.getValue().getDeoZaServis().size() - 1) {
	    			delovi=delovi+ entry.getValue().getDeoZaServis().get(i).getNazivDela();
	    		}
	    		else {
	    			delovi=delovi+ entry.getValue().getDeoZaServis().get(i).getNazivDela()+",";
	    		}
	    	}
	    		
	    		
	        model.addRow(new Object[] { entry.getKey(), entry.getValue().getAutomobil().getMarka()
	        		,entry.getValue().getTermin(),entry.getValue().getOpis(),entry.getValue().isStatusServisaString(), delovi});
	    	}
	    	
	    }
	   
	    
	    
	    
	    return model;
	}
}
