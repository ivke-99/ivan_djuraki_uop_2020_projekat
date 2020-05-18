package view.AdminPages.AdminTools;

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

import classes.Admin;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.AdminMain;

@SuppressWarnings("serial")
public class PregledajAdminePage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajAdminePage dialog = new PregledajAdminePage();
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
	public PregledajAdminePage() {
		setTitle("Svi Admini");
		setBounds(100, 100, 784, 423);
		getContentPane().setLayout(null);
		
		JTable t = new JTable(toTableModel(LoadDatabase.sviAdmini));
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(10, 11, 748, 242);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton("IZADJI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminMain().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(639, 335, 119, 38);
		getContentPane().add(btnNewButton);

	}
	
	public static TableModel toTableModel(HashMap<Integer,Admin> map) {
	    DefaultTableModel model = new DefaultTableModel(
	    		new Object[] { "Id", "Ime" , "Prezime", "JMBG", "Pol" , "Adresa" , "Broj telefona" , "Korisnicko ime, "
		        		, "Lozinka", "Tip Korisnika" , "Broj bodova"}, 0
		    );
	    
	    for (HashMap.Entry<Integer,Admin> entry : map.entrySet()) {
	    		
	    	model.addRow(new Object[] { entry.getKey(), entry.getValue().getIme(), entry.getValue().getPrezime()
	        		,entry.getValue().getJmbg(),entry.getValue().isPol(),entry.getValue().getAdresa(),entry.getValue().getBrtel(),
	        		entry.getValue().getKorisnickoIme(),entry.getValue().getLozinka(),entry.getValue().getTipkorisnika(),
	        		entry.getValue().getPlata()});
	    	
	    }
	    
	    return model;
   }
}
