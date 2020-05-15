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
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.MusterijaMain;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MusterijaPregledajServisePage extends JDialog {
	private static String delovi;
	public JTable t;
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
		setTitle("Servisi Automobila");
		setBounds(100, 100, 644, 437);
		getContentPane().setLayout(null);
		
		t = new JTable(toTableModel(LoadDatabase.sviServisi));
		t.setCellSelectionEnabled(false);
		
		t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(34, 11, 551, 309);
		getContentPane().add(scrollPane);
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();
		
		
		
		JButton btnNewButton = new JButton("Izadji");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MusterijaMain().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(529, 364, 89, 23);
		getContentPane().add(btnNewButton);
		
		

	}
	
	
	
	public static TableModel toTableModel(HashMap<Integer,ServisAutomobila> map) {
		/*still in work*/
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Id Servisa", "Automobil", "Termin", "Opis", "Status servisa", "Delovi za servis"}, 0
	    );
	    
	    for (HashMap.Entry<Integer,ServisAutomobila> entry : map.entrySet()) {
	    	
	    	if(entry.getValue().getAutomobil().getVlasnik().getId() == LoginHandling.trenutniKorisnik.getId()) 
	    	{	
	    		
	        model.addRow(new Object[] { entry.getKey(), entry.getValue().getAutomobil().getMarka()
	        		,entry.getValue().getTermin(),entry.getValue().getOpis(),entry.getValue().isStatusServisaString(), 
	        		entry.getValue().getDeoZaServis()});
	    	}
	    	
	    	
	    	
	    }
	   
	    
	    
	    
	    return model;
	}
}
