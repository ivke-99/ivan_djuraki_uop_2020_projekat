package view.AdminPages.AutomobilTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import classes.Automobil;
import classes.Automobil.MarkaiModel;
import classes.Automobil.VrstaGoriva;
import classes.Musterija;
import classes.ServisnaKnjizica;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.AdminMain;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AddAutomobilPage extends JDialog {
	protected JComboBox<Automobil.MarkaiModel> cbMarkaiModel;
	protected JComboBox<Musterija> cbMusterija;
	protected JComboBox<Automobil.VrstaGoriva> cbTipGoriva;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAutomobilPage dialog = new AddAutomobilPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setResizable(false);
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
	public AddAutomobilPage() throws ParseException {
		setTitle("Dodaj automobil");
		setBounds(100, 100, 450, 351);
		getContentPane().setLayout(null);

		MaskFormatter godMask = new MaskFormatter("####");
		godMask.setValidCharacters("0123456789");

		cbMarkaiModel = new JComboBox<Automobil.MarkaiModel>();
		cbMarkaiModel.setBounds(117, 20, 152, 28);
		getContentPane().add(cbMarkaiModel);
		FillingControl.PopuniComboBoxMarkaiModel(cbMarkaiModel);
		cbMarkaiModel.setSelectedIndex(-1);

		JLabel lblNewLabel = new JLabel("Marka i model:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 20, 103, 28);
		getContentPane().add(lblNewLabel);

		JLabel lblMusterija = new JLabel("Musterija:");
		lblMusterija.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMusterija.setBounds(10, 65, 103, 22);
		getContentPane().add(lblMusterija);

		cbMusterija = new JComboBox<Musterija>();
		cbMusterija.setBounds(117, 59, 152, 28);
		getContentPane().add(cbMusterija);
		FillingControl.PopuniComboBoxMusterija(cbMusterija);
		cbMusterija.setSelectedIndex(-1);

		JTextField txtKubikaza = new JTextField();
		txtKubikaza.setBounds(117, 98, 88, 28);
		getContentPane().add(txtKubikaza);

		JLabel lblKubikaza = new JLabel("Kubikaza:");
		lblKubikaza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKubikaza.setBounds(10, 98, 103, 28);
		getContentPane().add(lblKubikaza);

		JLabel lblSnaga = new JLabel("Snaga:");
		lblSnaga.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSnaga.setBounds(10, 137, 103, 28);
		getContentPane().add(lblSnaga);

		JTextField txtSnaga = new JTextField();
		txtSnaga.setBounds(117, 137, 88, 28);
		getContentPane().add(txtSnaga);

		JLabel lblTipGoriva = new JLabel("Tip Goriva:");
		lblTipGoriva.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipGoriva.setBounds(10, 218, 103, 28);
		getContentPane().add(lblTipGoriva);

		cbTipGoriva = new JComboBox<Automobil.VrstaGoriva>();
		cbTipGoriva.setBounds(117, 218, 152, 28);
		getContentPane().add(cbTipGoriva);
		FillingControl.PopuniComboBoxGorivo(cbTipGoriva);
		cbTipGoriva.setSelectedIndex(-1);

		JButton btnDodaj = new JButton("DODAJ");
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDodaj.setBounds(10, 266, 103, 35);
		getContentPane().add(btnDodaj);

		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(166, 266, 103, 35);
		getContentPane().add(btnReset);

		JButton btnIzadji = new JButton("IZADJI");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminMain().setVisible(true);
			}
		});
		btnIzadji.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIzadji.setBounds(321, 266, 103, 35);
		getContentPane().add(btnIzadji);

		JLabel lblGodinaProizvodnje = new JLabel("Godiste:");
		lblGodinaProizvodnje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGodinaProizvodnje.setBounds(10, 177, 103, 28);
		getContentPane().add(lblGodinaProizvodnje);

		JFormattedTextField txtGodiste = new JFormattedTextField(godMask);
		txtGodiste.setBounds(117, 176, 88, 28);
		getContentPane().add(txtGodiste);

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbMusterija.setSelectedIndex(-1);
				cbTipGoriva.setSelectedIndex(-1);
				cbMarkaiModel.setSelectedIndex(-1);
				txtKubikaza.setText("");
				txtSnaga.setText("");
				txtGodiste.setText("");
			}
		});

		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean opcija = FillingControl.PrintOpcija();

				if (!(opcija == false)) {
					if (cbMusterija.getSelectedIndex() == -1 || cbTipGoriva.getSelectedIndex() == -1
							|| cbMarkaiModel.getSelectedIndex() == -1 || txtKubikaza.getText().equals("")
							|| txtSnaga.getText().equals("") || txtGodiste.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena.Pokusajte ponovo.");
					}

					else {

						try {
							DodajAutomobil(txtKubikaza, txtSnaga, txtGodiste);
							JOptionPane.showMessageDialog(null, "Uspesan upis.");
							dispose();
							new AdminMain().setVisible(true);
						} catch (Exception a) {
							JOptionPane.showMessageDialog(null, "Greska pri upisu.");
							a.printStackTrace();
						}
					}

				}

			}
		});
	}

	public void DodajAutomobil(JTextField k, JTextField sn, JFormattedTextField g) throws Exception {
		String wrt = "";
		Automobil s = new Automobil();
		s.setId(FileHandling.automobilPath);
		s.setVlasnik((Musterija) cbMusterija.getSelectedItem());
		s.setGorivo((VrstaGoriva) cbTipGoriva.getSelectedItem());
		s.setMarka((MarkaiModel) cbMarkaiModel.getSelectedItem());
		s.setKubikazaMotora(Integer.parseInt(k.getText()));
		s.setSnagaMotora(Integer.parseInt(sn.getText()));
		s.setGodinaProizvodnje(Integer.parseInt(g.getText()));
		wrt = s.WriteToString();
		FileHandling.WriteToFile(wrt, FileHandling.automobilPath);
		LoadDatabase.sviAutomobili.put(s.getId(), s);

		ServisnaKnjizica nova = ServisnaKnjizica.NewKnjizicaForAutomobil(s);
		LoadDatabase.sveKnjizice.put(nova.getId(), nova);

	}
}
