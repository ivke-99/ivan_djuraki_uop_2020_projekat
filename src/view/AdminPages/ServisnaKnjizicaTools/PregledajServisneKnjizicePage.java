package view.AdminPages.ServisnaKnjizicaTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.ServisAutomobila;
import classes.ServisnaKnjizica;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PregledajServisneKnjizicePage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajServisneKnjizicePage dialog = new PregledajServisneKnjizicePage();
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
	public PregledajServisneKnjizicePage() {
		setTitle("Pregledaj Sve Servisne Knjizice");
		setBounds(100, 100, 711, 445);
		getContentPane().setLayout(null);

		JTable t = new JTable(toTableModel(LoadDatabase.sveKnjizice));
		t.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(10, 11, 675, 280);
		getContentPane().add(scrollPane);
		t.setEnabled(false);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(t);
		tca.adjustColumns();

		JButton btnNewButton = new JButton("IZADJI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(586, 357, 99, 38);
		getContentPane().add(btnNewButton);

	}

	public static TableModel toTableModel(HashMap<Integer, ServisnaKnjizica> map) {
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Id", "Automobil", "Servisi" }, 0);
		for (HashMap.Entry<Integer, ServisnaKnjizica> entry : map.entrySet()) {
			ArrayList<ServisAutomobila> filtriraniServisi = new ArrayList<ServisAutomobila>();
			for (ServisAutomobila k : entry.getValue().getServisi()) {
				if (k != null && k.isDeleted() != true) {
					filtriraniServisi.add(k);
				}

			}
			model.addRow(new Object[] { entry.getKey(), entry.getValue().getAuto(),
					filtriraniServisi.toString().replace("[", "").replace("]", "") });

		}
		return model;
	}
}
