package view.MusterijaPages;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.Automobil;
import classes.ServisAutomobila;
import classes.ServisnaKnjizica;
import controller.FillingControl;
import controller.LoginHandling;
import dao.LoadDatabase;
import view.MusterijaMain;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class MusterijaPregledajKnjizicuPage extends JDialog {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusterijaPregledajKnjizicuPage dialog = new MusterijaPregledajKnjizicuPage();
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
	public MusterijaPregledajKnjizicuPage() {
		setTitle("Servisna Knjizica");
		setBounds(100, 100, 656, 467);
		getContentPane().setLayout(null);
		

				JTable t = new JTable(toTableModel(LoadDatabase.sveKnjizice));
				JScrollPane scrollPane = new JScrollPane(t);
				scrollPane.setBounds(10, 92, 620, 251);
				getContentPane().add(scrollPane);
			
		
		
		
		JButton btnNewButton = new JButton("Izadji");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MusterijaMain().setVisible(true);
			}
		});
		btnNewButton.setBounds(541, 394, 89, 23);
		getContentPane().add(btnNewButton);

	}
	
	public static TableModel toTableModel(HashMap<Integer,ServisnaKnjizica> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Id Knjizice", "Lista Servisa"}, 0
	    );
	    
	    for (HashMap.Entry<Integer,ServisnaKnjizica> entry : map.entrySet()) {
	    	
	    	
	    	if(entry.getValue().getAuto().getVlasnik().getId() == LoginHandling.trenutniKorisnik.getId())
	    	{	
	        model.addRow(new Object[] { entry.getKey(), entry.getValue().getServisi()});
	    	}
	    	
	    }
	   
	    return model;
	}
}
