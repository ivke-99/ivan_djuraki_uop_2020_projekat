package view.AdminPages.AutomobilTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.Automobil;
import classes.Musterija;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JButton;
import java.awt.Font;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PregledajAllAutomobilPage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajAllAutomobilPage dialog = new PregledajAllAutomobilPage();
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
	public PregledajAllAutomobilPage() {
		setTitle("Pregledaj sve automobile");
		setBounds(100, 100, 744, 427);
		getContentPane().setLayout(null);
		
		JTable t = new JTable(toTableModel(LoadDatabase.sviAutomobili));
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(10, 11, 708, 306);
		getContentPane().add(scrollPane);
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();
		
		JButton btnNewButton = new JButton("IZADJI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminMain().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(611, 342, 107, 35);
		getContentPane().add(btnNewButton);

	}
	
	public static TableModel toTableModel(HashMap<Integer,Automobil> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Id", "Marka i Model" , "Vlasnik", "Gorivo", "Kubikaza Motora" , "Snaga Motora" , "Godiste"}, 0
	    );
	    for (HashMap.Entry<Integer,Automobil> entry : map.entrySet()) {
	        model.addRow(new Object[] { entry.getKey(), entry.getValue().getMarka(), entry.getValue().getVlasnik()
	        		,entry.getValue().getGorivo(),entry.getValue().getKubikazaMotora(),entry.getValue().getSnagaMotora(),
	        		entry.getValue().getGodinaProizvodnje()
	        		});
	    }
	    return model;
	}
}
