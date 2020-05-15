package view.AdminPages.ServisTools;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.ServisAutomobila;
import controller.LoginHandling;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PregledajSveServise extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajSveServise dialog = new PregledajSveServise();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public PregledajSveServise() {
		setTitle("Pregledaj Sve Servise");
		setBounds(100, 100, 807, 476);
		getContentPane().setLayout(null);
		
		JTable t = new JTable(toTableModel(LoadDatabase.sviServisi));
		JScrollPane scrollPane = new JScrollPane(t);
		t.setEnabled(false);
		scrollPane.setBounds(10, 11, 771, 317);
		getContentPane().add(scrollPane);
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();
		
		JButton btnIzadji = new JButton("IZADJI");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnIzadji.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIzadji.setBounds(679, 395, 102, 31);
		getContentPane().add(btnIzadji);

	}
	
	public static TableModel toTableModel(HashMap<Integer,ServisAutomobila> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Id Servisa","Serviser", "Automobil", "Termin", "Opis", "Status servisa", "Delovi za servis"}, 0
	    );
	    
	   
	    
	    for (HashMap.Entry<Integer,ServisAutomobila> entry : map.entrySet()) {
	    	
	        model.addRow(new Object[] { entry.getKey(), entry.getValue().getServiser(),entry.getValue().getAutomobil().getMarka()
	        		,entry.getValue().getTermin(),entry.getValue().getOpis(),entry.getValue().isStatusServisaString(), entry.getValue().getCena(), 
	        		entry.getValue().getDeoZaServis()});
	    	
	        
	    }
	    return model;
	  
   }
}
