package view.AdminPages.ServisniDeoTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import classes.Automobil.MarkaiModel;
import classes.ServisniDeo;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DodajServisniDeoPage extends JDialog {
	private JTextField txtNaziv;
	private JTextField txtCena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajServisniDeoPage dialog = new DodajServisniDeoPage();
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
	public DodajServisniDeoPage() {
		setTitle("Dodaj Servisni Deo");
		setBounds(100, 100, 342, 296);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Naziv dela:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 23, 84, 35);
		getContentPane().add(lblNewLabel);

		JComboBox<MarkaiModel> cbMarkaiModel = new JComboBox<MarkaiModel>();
		cbMarkaiModel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbMarkaiModel.setBounds(154, 69, 137, 31);
		FillingControl.PopuniComboBoxMarkaiModel(cbMarkaiModel);
		getContentPane().add(cbMarkaiModel);
		cbMarkaiModel.setSelectedIndex(-1);

		JLabel lblMarkaIModel = new JLabel("Marka i Model:");
		lblMarkaIModel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMarkaIModel.setBounds(10, 69, 108, 31);
		getContentPane().add(lblMarkaIModel);

		JLabel lblCena = new JLabel("Cena dela:");
		lblCena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCena.setBounds(10, 111, 108, 31);
		getContentPane().add(lblCena);

		txtNaziv = new JTextField();
		txtNaziv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNaziv.setBounds(154, 23, 137, 31);
		getContentPane().add(txtNaziv);
		txtNaziv.setColumns(10);

		JButton btnNewButton = new JButton("DODAJ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					if (cbMarkaiModel.getSelectedIndex() == -1 || txtCena.getText().equals("")
							|| txtNaziv.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Polja ne mogu biti prazna.");

					else {
						try {
							ServisniDeo deo = new ServisniDeo();
							deo.setId(FileHandling.servisniDeoPath);
							deo.setCena(Double.parseDouble(txtCena.getText()));
							deo.setNazivDela(txtNaziv.getText());
							deo.setMarka((MarkaiModel) cbMarkaiModel.getSelectedItem());
							LoadDatabase.sviDelovi.put(deo.getId(), deo);
							String write = deo.WriteToString();
							FileHandling.WriteToFile(write, FileHandling.servisniDeoPath);
							JOptionPane.showMessageDialog(null, "Uspesan upis!");

							cbMarkaiModel.setSelectedIndex(-1);
							txtCena.setText("");
							txtNaziv.setText("");

						} catch (Exception cena) {
							JOptionPane.showMessageDialog(null, cena.getMessage());
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(10, 222, 97, 31);
		getContentPane().add(btnNewButton);

		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.setBounds(112, 222, 97, 31);
		getContentPane().add(btnReset);

		JButton btnIzadji = new JButton("IZADJI");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnIzadji.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIzadji.setBounds(219, 222, 97, 31);
		getContentPane().add(btnIzadji);

		txtCena = new JTextField();
		txtCena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCena.setColumns(10);
		txtCena.setBounds(154, 111, 139, 31);
		getContentPane().add(txtCena);

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbMarkaiModel.setSelectedIndex(-1);
				txtCena.setText("");
				txtNaziv.setText("");
			}
		});

	}
}
