package view.AdminPages.MusterijaTools;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.Musterija;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JButton;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PregledajMusterijePage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajMusterijePage dialog = new PregledajMusterijePage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setResizable(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PregledajMusterijePage() {

		setTitle("Prikaz svih musterija page");
		setBounds(100, 100, 750, 436);
		getContentPane().setLayout(null);

		JTable t = new JTable(toTableModel(LoadDatabase.sveMusterije));
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(10, 11, 714, 247);
		getContentPane().add(scrollPane);
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();

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

	public static TableModel toTableModel(HashMap<Integer, Musterija> map) {
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Id", "Ime", "Prezime", "JMBG", "Pol", "Adresa",
				"Broj telefona", "Korisnicko ime, ", "Lozinka", "Tip Korisnika", "Broj bodova" }, 0);
		for (HashMap.Entry<Integer, Musterija> entry : map.entrySet()) {
			model.addRow(new Object[] { entry.getKey(), entry.getValue().getIme(), entry.getValue().getPrezime(),
					entry.getValue().getJmbg(), entry.getValue().isPol(), entry.getValue().getAdresa(),
					entry.getValue().getBrtel(), entry.getValue().getKorisnickoIme(), entry.getValue().getLozinka(),
					entry.getValue().getTipkorisnika(), entry.getValue().getBrojBodova() });
		}
		return model;
	}
}
