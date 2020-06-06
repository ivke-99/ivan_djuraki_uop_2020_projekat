package view.AdminPages.ServisniDeoTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.ServisniDeo;
import controller.TableColumnAdjuster;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PregledajDelovePage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajDelovePage dialog = new PregledajDelovePage();
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
	public PregledajDelovePage() {
		setTitle("Pregledaj Sve Servisne Delove");
		setBounds(100, 100, 708, 457);
		getContentPane().setLayout(null);

		JTable t = new JTable(toTableModel(LoadDatabase.sviDelovi));
		t.setEnabled(false);
		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setBounds(10, 11, 672, 312);
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
		btnNewButton.setBounds(579, 370, 103, 37);
		getContentPane().add(btnNewButton);

	}

	public static TableModel toTableModel(HashMap<Integer, ServisniDeo> map) {
		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "Id Dela", "Naziv Dela", "Marka i Model", "Cena" }, 0);

		for (HashMap.Entry<Integer, ServisniDeo> entry : map.entrySet()) {

			model.addRow(new Object[] { entry.getKey(), entry.getValue().getNazivDela(), entry.getValue().getMarka(),
					entry.getValue().getCena() });

		}
		return model;

	}
}
