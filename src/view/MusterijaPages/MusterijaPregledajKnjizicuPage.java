package view.MusterijaPages;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.ServisAutomobila;
import classes.ServisnaKnjizica;
import controller.LoginHandling;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.MusterijaMain;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
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
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();

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

	public static TableModel toTableModel(HashMap<Integer, ServisnaKnjizica> map) {
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Id Knjizice", "Lista Servisa" }, 0);

		for (HashMap.Entry<Integer, ServisnaKnjizica> entry : map.entrySet()) {
			ArrayList<ServisAutomobila> filtriraniServisi = new ArrayList<ServisAutomobila>();
			if (entry.getValue().getAuto().getVlasnik().getId() == LoginHandling.trenutniKorisnik.getId()) {
				for (ServisAutomobila k : entry.getValue().getServisi()) {
					if (k != null && k.isDeleted() != true) {
						filtriraniServisi.add(k);
					}

				}
				model.addRow(new Object[] { entry.getKey(), entry.getValue().getAuto(),
						filtriraniServisi.toString().replace("[", "").replace("]", "") });

			}

		}

		return model;
	}
}
