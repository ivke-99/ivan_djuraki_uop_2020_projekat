package view.AdminPages.AutomobilTools;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import classes.Automobil;
import classes.Musterija;
import classes.Automobil.MarkaiModel;
import classes.Automobil.VrstaGoriva;
import controller.FileHandling;
import controller.FillingControl;
import dao.DeleteDAO;
import dao.LoadDatabase;
import view.AdminMain;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class IzmeniAutomobilPage extends JDialog {
	protected JComboBox<Automobil.MarkaiModel> cbMarkaiModel;
	protected JComboBox<Musterija> cbMusterija;
	protected JComboBox<Automobil.VrstaGoriva> cbTipGoriva;
	protected Automobil currentAuto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmeniAutomobilPage dialog = new IzmeniAutomobilPage();
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
	 * 
	 * @throws ParseException
	 */
	public IzmeniAutomobilPage() throws ParseException {
		setTitle("Izmeni automobil");
		setBounds(100, 100, 450, 379);
		getContentPane().setLayout(null);

		MaskFormatter godMask = new MaskFormatter("####");
		godMask.setValidCharacters("0123456789");

		cbMarkaiModel = new JComboBox<Automobil.MarkaiModel>();
		cbMarkaiModel.setBounds(117, 48, 152, 28);
		getContentPane().add(cbMarkaiModel);
		FillingControl.PopuniComboBoxMarkaiModel(cbMarkaiModel);
		cbMarkaiModel.setSelectedIndex(-1);

		JLabel lblNewLabel = new JLabel("Marka i model:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 48, 103, 28);
		getContentPane().add(lblNewLabel);

		JLabel lblMusterija = new JLabel("Musterija:");
		lblMusterija.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMusterija.setBounds(10, 93, 103, 22);
		getContentPane().add(lblMusterija);

		cbMusterija = new JComboBox<Musterija>();
		cbMusterija.setBounds(117, 87, 152, 28);
		getContentPane().add(cbMusterija);
		FillingControl.PopuniComboBoxMusterija(cbMusterija);
		cbMusterija.setSelectedIndex(-1);

		JTextField txtKubikaza = new JTextField();
		txtKubikaza.setBounds(117, 126, 88, 28);
		getContentPane().add(txtKubikaza);

		JLabel lblKubikaza = new JLabel("Kubikaza:");
		lblKubikaza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKubikaza.setBounds(10, 126, 103, 28);
		getContentPane().add(lblKubikaza);

		JLabel lblSnaga = new JLabel("Snaga:");
		lblSnaga.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSnaga.setBounds(10, 165, 103, 28);
		getContentPane().add(lblSnaga);

		JTextField txtSnaga = new JTextField();
		txtSnaga.setBounds(117, 165, 88, 28);
		getContentPane().add(txtSnaga);

		JLabel lblTipGoriva = new JLabel("Tip Goriva:");
		lblTipGoriva.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipGoriva.setBounds(10, 246, 103, 28);
		getContentPane().add(lblTipGoriva);

		cbTipGoriva = new JComboBox<Automobil.VrstaGoriva>();
		cbTipGoriva.setBounds(117, 246, 152, 28);
		getContentPane().add(cbTipGoriva);
		FillingControl.PopuniComboBoxGorivo(cbTipGoriva);
		cbTipGoriva.setSelectedIndex(-1);

		JButton btnIzmeni = new JButton("IZMENI");
		btnIzmeni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIzmeni.setBounds(10, 294, 103, 35);
		getContentPane().add(btnIzmeni);

		JButton btnObrisi = new JButton("OBRISI");
		btnObrisi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnObrisi.setBounds(166, 294, 103, 35);
		getContentPane().add(btnObrisi);

		JButton btnIzadji = new JButton("IZADJI");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminMain().setVisible(true);
			}
		});
		btnIzadji.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIzadji.setBounds(321, 294, 103, 35);
		getContentPane().add(btnIzadji);

		JLabel lblGodinaProizvodnje = new JLabel("Godiste:");
		lblGodinaProizvodnje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGodinaProizvodnje.setBounds(10, 205, 103, 28);
		getContentPane().add(lblGodinaProizvodnje);

		JFormattedTextField txtGodiste = new JFormattedTextField(godMask);
		txtGodiste.setBounds(117, 204, 88, 28);
		getContentPane().add(txtGodiste);

		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(321, 11, 103, 35);
		getContentPane().add(btnReset);

		JLabel lblAutomobil = new JLabel("Automobil:");
		lblAutomobil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutomobil.setBounds(10, 9, 103, 28);
		getContentPane().add(lblAutomobil);

		JComboBox<Automobil> cbAuto = new JComboBox<Automobil>();
		FillingControl.PopuniComboBoxSviAutomobili(cbAuto);
		cbAuto.setSelectedIndex(-1);
		cbAuto.setBounds(117, 11, 152, 28);
		getContentPane().add(cbAuto);

		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					if (cbAuto.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Morate izabrati automobil.");
					}

					try {
						var brisanje = (Automobil) cbAuto.getSelectedItem();
						DeleteDAO.DeleteAutomobil(brisanje);
						cbAuto.setSelectedIndex(-1);
						cbMusterija.setSelectedIndex(-1);
						cbTipGoriva.setSelectedIndex(-1);
						cbMarkaiModel.setSelectedIndex(-1);
						txtKubikaza.setText("");
						txtSnaga.setText("");
						txtGodiste.setText("");
						cbAuto.setSelectedIndex(-1);
						cbAuto.removeItem(brisanje);
						cbAuto.setSelectedIndex(-1);
						JOptionPane.showMessageDialog(null, "Uspesno brisanje!");
					} catch (Exception unknown) {
						JOptionPane.showMessageDialog(null, "Neuspelo brisanje.");
						unknown.printStackTrace();
					}
				}

			}
		});

		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean opcija = FillingControl.PrintOpcija();

				if (!(opcija == false)) {
					if (cbAuto.getSelectedIndex() == -1 || cbMusterija.getSelectedIndex() == -1
							|| cbTipGoriva.getSelectedIndex() == -1 || cbMarkaiModel.getSelectedIndex() == -1
							|| txtKubikaza.getText().equals("") || txtSnaga.getText().equals("")
							|| txtGodiste.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Neka polja su prazna.Pokusajte ponovo.");
					}

					else {

						try {
							IzmeniAuto(currentAuto, txtGodiste, txtSnaga, txtKubikaza);
							JOptionPane.showMessageDialog(null, "Uspesna izmena.");
							dispose();
							new AdminMain().setVisible(true);
						} catch (Exception a) {
							JOptionPane.showMessageDialog(null, a.getMessage());
						}

					}

				}

			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAuto.setSelectedIndex(-1);
				cbMusterija.setSelectedIndex(-1);
				cbTipGoriva.setSelectedIndex(-1);
				cbMarkaiModel.setSelectedIndex(-1);
				txtKubikaza.setText("");
				txtSnaga.setText("");
				txtGodiste.setText("");
			}
		});

		cbAuto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				currentAuto = null;
				currentAuto = (Automobil) cbAuto.getSelectedItem();
				try {
					cbMusterija.setSelectedItem(currentAuto.getVlasnik());
					cbTipGoriva.setSelectedItem(currentAuto.getGorivo());
					cbMarkaiModel.setSelectedItem(currentAuto.getMarka());
					txtKubikaza.setText(currentAuto.getKubikazaMotora() + "");
					txtSnaga.setText(currentAuto.getSnagaMotora() + "");
					txtGodiste.setText(currentAuto.getGodinaProizvodnje() + "");
				} catch (Exception deleted) {
					/**/
				}
			}
		});
	}

	public void IzmeniAuto(Automobil old, JFormattedTextField txtGodiste, JTextField txtSnaga, JTextField txtKubikaza)
			throws Exception {

		String oldLine = old.WriteToString();

		Automobil novi = new Automobil();
		novi.setIdFromFile(old.getId());
		novi.setMarka((MarkaiModel) cbMarkaiModel.getSelectedItem());
		novi.setVlasnik((Musterija) cbMusterija.getSelectedItem());
		novi.setGorivo((VrstaGoriva) cbTipGoriva.getSelectedItem());
		novi.setKubikazaMotora(Integer.parseInt(txtKubikaza.getText()));
		novi.setSnagaMotora(Integer.parseInt(txtSnaga.getText()));
		novi.setGodinaProizvodnje(Integer.parseInt(txtGodiste.getText()));

		LoadDatabase.sviAutomobili.replace(currentAuto.getId(), novi);

		String newLine = novi.WriteToString();
		FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.automobilPath);
	}

}
