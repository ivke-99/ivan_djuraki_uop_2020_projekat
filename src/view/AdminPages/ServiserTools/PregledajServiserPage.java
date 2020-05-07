package view.AdminPages.ServiserTools;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.Musterija;
import classes.Serviser;
import dao.LoadDatabase;
import view.AdminMain;

public class PregledajServiserPage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajServiserPage dialog = new PregledajServiserPage();
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
	public PregledajServiserPage() {
		setBounds(100, 100, 450, 300);
		setTitle("Prikaz svih servisera");
		setBounds(100, 100, 750, 436);
		getContentPane().setLayout(null);
		
		JTable t = new JTable(toTableModel(LoadDatabase.sviServiseri));
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(10, 11, 714, 247);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("Izadji");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(612, 346, 112, 40);
		getContentPane().add(btnNewButton);
	}
	
	public static TableModel toTableModel(HashMap<Integer,Serviser> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Id", "Ime" , "Prezime", "JMBG", "Pol" , "Adresa" , "Broj telefona" , "Korisnicko ime, "
	        		, "Lozinka", "Tip Korisnika" , "Plata" , "Specijalizacija"}, 0
	    );
	    for (HashMap.Entry<Integer,Serviser> entry : map.entrySet()) {
	        model.addRow(new Object[] { entry.getKey(), entry.getValue().getIme(), entry.getValue().getPrezime()
	        		,entry.getValue().getJmbg(),entry.getValue().isPol(),entry.getValue().getAdresa(),entry.getValue().getBrtel(),
	        		entry.getValue().getKorisnickoIme(),entry.getValue().getLozinka(),entry.getValue().getTipkorisnika(),
	        		entry.getValue().getPlata(), entry.getValue().getSpec()});
	    }
	    return model;
	}
}
