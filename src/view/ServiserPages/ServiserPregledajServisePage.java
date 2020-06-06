package view.ServiserPages;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.ServisAutomobila;
import controller.LoginHandling;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.ServiserMain;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ServiserPregledajServisePage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiserPregledajServisePage dialog = new ServiserPregledajServisePage();
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
	public ServiserPregledajServisePage() {
		setTitle("Vasi Servisi");
		setBounds(100, 100, 784, 423);
		getContentPane().setLayout(null);

		JTable t = new JTable(toTableModel(LoadDatabase.sviServisi));
		t.setFont(new Font("Tahoma", Font.PLAIN, 13));
		t.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(10, 11, 748, 242);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		getContentPane().add(scrollPane);
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();

		JButton btnNewButton = new JButton("IZADJI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ServiserMain().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(639, 335, 119, 38);
		getContentPane().add(btnNewButton);

	}

	public static TableModel toTableModel(HashMap<Integer, ServisAutomobila> map) {
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Id Servisa", "Automobil", "Termin", "Opis",
				"Status servisa", "Cena", "Delovi za servis" }, 0);

		for (HashMap.Entry<Integer, ServisAutomobila> entry : map.entrySet()) {

			if (!(entry.getValue().getServiser() == null)) {

				if (entry.getValue().getServiser().getId() == LoginHandling.trenutniKorisnik.getId()) {
					model.addRow(new Object[] { entry.getKey(), entry.getValue().getAutomobil().getMarka(),
							entry.getValue().getTermin(), entry.getValue().getOpis(),
							entry.getValue().isStatusServisaString(), entry.getValue().getCena(),
							entry.getValue().getDeoZaServis().toString().replace("[", "").replace("]", "") });
				}
			}
		}
		return model;
	}

}
