package view.AdminPages.ServisTools;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import classes.Identifiable;
import classes.Musterija;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.ServisnaKnjizica;
import classes.ServisniDeo;
import controller.CenaHandling;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.AdminMain;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class IzmeniObrisiServisPage extends JDialog {
	private JTextField txtOpis;
	private ServisAutomobila currentServis;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmeniObrisiServisPage dialog = new IzmeniObrisiServisPage();
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
	public IzmeniObrisiServisPage() throws ParseException {
		setTitle("Izmeni/Obrisi/Zavrsi Servis");
		setBounds(100, 100, 466, 483);
		getContentPane().setLayout(null);

		MaskFormatter dateMask = new MaskFormatter("##/##/#### ##:##");
		dateMask.setValidCharacters("0123456789");
		dateMask.setPlaceholderCharacter('_');

		JComboBox<ServisAutomobila> cbServis = new JComboBox<ServisAutomobila>();
		cbServis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbServis.setBounds(165, 38, 177, 30);
		getContentPane().add(cbServis);
		FillingControl.PopuniSveZakazaneServise(cbServis);
		cbServis.setSelectedIndex(-1);

		JLabel lblNewLabel = new JLabel("Izaberi servis:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 36, 106, 30);
		getContentPane().add(lblNewLabel);

		JComboBox<Serviser> cbServiser = new JComboBox<Serviser>();
		cbServiser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbServiser.setBounds(165, 81, 177, 30);
		getContentPane().add(cbServiser);
		FillingControl.PopuniComboBoxSviServiseri(cbServiser);
		cbServiser.setSelectedIndex(-1);

		JLabel lblIzaberiServisera = new JLabel("Serviser:");
		lblIzaberiServisera.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIzaberiServisera.setBounds(10, 79, 145, 30);
		getContentPane().add(lblIzaberiServisera);

		JLabel lblIzaberiDatum = new JLabel("Datum:");
		lblIzaberiDatum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIzaberiDatum.setBounds(10, 122, 145, 28);
		getContentPane().add(lblIzaberiDatum);

		JFormattedTextField txtDatum = new JFormattedTextField(dateMask);
		txtDatum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtDatum.setBounds(165, 122, 177, 28);
		getContentPane().add(txtDatum);

		JLabel lblKratakOpis = new JLabel("Kratak opis:");
		lblKratakOpis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKratakOpis.setBounds(10, 163, 111, 26);
		getContentPane().add(lblKratakOpis);

		txtOpis = new JTextField();
		txtOpis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOpis.setBounds(165, 161, 253, 29);
		getContentPane().add(txtOpis);
		txtOpis.setColumns(10);

		JLabel lblIzaberiDelove = new JLabel("Delovi:");
		lblIzaberiDelove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIzaberiDelove.setBounds(10, 200, 111, 26);
		getContentPane().add(lblIzaberiDelove);

		DefaultListModel<ServisniDeo> listModel = new DefaultListModel<ServisniDeo>();
		JList<ServisniDeo> list = new JList<ServisniDeo>(listModel);
		list.setBounds(165, 201, 253, 141);
		getContentPane().add(list);

		JButton btnIzadji = new JButton("IZADJI");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnIzadji.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIzadji.setBounds(339, 408, 101, 30);
		getContentPane().add(btnIzadji);

		JButton btnDodaj = new JButton("IZMENI");
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDodaj.setBounds(10, 408, 101, 30);
		getContentPane().add(btnDodaj);

		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.setBounds(121, 408, 101, 30);
		getContentPane().add(btnReset);

		JButton btnZavrsi = new JButton("ZAVRSI");
		btnZavrsi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnZavrsi.setBounds(228, 408, 101, 30);
		getContentPane().add(btnZavrsi);

		JButton btnObrisi = new JButton("OBRISI");
		btnObrisi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnObrisi.setBounds(339, 367, 101, 30);
		getContentPane().add(btnObrisi);

		JLabel lblStatusServisa = new JLabel("Status Servisa:");
		lblStatusServisa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatusServisa.setBounds(10, 367, 101, 30);
		getContentPane().add(lblStatusServisa);

		JRadioButton rdZavrsen = new JRadioButton("Zavrsen");
		rdZavrsen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdZavrsen.setEnabled(false);
		buttonGroup.add(rdZavrsen);
		rdZavrsen.setBounds(147, 368, 86, 28);
		getContentPane().add(rdZavrsen);

		JRadioButton rdZapocet = new JRadioButton("Zapocet");
		rdZapocet.setEnabled(false);
		rdZapocet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonGroup.add(rdZapocet);
		rdZapocet.setBounds(235, 368, 86, 28);
		getContentPane().add(rdZapocet);

		cbServis.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				try {
					currentServis = null;
					listModel.removeAllElements();
					currentServis = (ServisAutomobila) cbServis.getSelectedItem();

					var listadelova = FillingControl.PopuniListuDelova2(list, listModel, cbServis);
					txtOpis.setText(currentServis.getOpis());
					cbServiser.setSelectedItem(currentServis.getServiser());
					txtDatum.setText(ServisAutomobila.ConvertDateToString(currentServis.getTermin()));
					/* set selected items */
					list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

					/* ovo ispod je izmisljanje tople vode */
					ArrayList<Integer> integeri = new ArrayList<Integer>();
					for (int i = 0; i < listadelova.size(); i++) {
						if (currentServis.getDeoZaServis().contains(listadelova.get(i))) {
							integeri.add(i);
						}
					}
					int[] niz = new int[integeri.size()];
					for (int i2 = 0; i2 < integeri.size(); i2++) {
						niz[i2] = integeri.get(i2);
					}
					list.setSelectedIndices(niz);
					if (currentServis.isStatusServisa() == true) {
						rdZapocet.setSelected(true);
					} else {
						rdZavrsen.setSelected(true);
					}
				} catch (Exception reset) {
					/* mora zbog reseta */
				}

			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentServis = null;
				listModel.removeAllElements();
				cbServiser.setSelectedIndex(-1);
				cbServis.setSelectedIndex(-1);
				txtDatum.setText("");
				txtOpis.setText("");
				rdZavrsen.setSelected(false);
				rdZapocet.setSelected(false);
			}
		});

		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {

					try {
						String oldLine = currentServis.WriteToString();
						ServisAutomobila s = new ServisAutomobila();
						s.setIdFromFile(currentServis.getId());
						s.setAutomobil(currentServis.getAutomobil());
						s.setServiser((Serviser) cbServiser.getSelectedItem());
						s.setStatusServisa(true);
						s.setTermin(ServisAutomobila.ConvertStringToDate(txtDatum.getText()));
						s.setOpis(txtOpis.getText());
						s.setCena(0);
						ArrayList<ServisniDeo> delovi = null;
						delovi = (ArrayList<ServisniDeo>) list.getSelectedValuesList();
						s.setDeoZaServis(delovi);
						String newLine = s.WriteToString();
						LoadDatabase.sviServisi.replace(currentServis.getId(), s);
						FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.servisAutomobilaPath);
						JOptionPane.showMessageDialog(null, "Uspesan upis!");
						int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite da izvrsite jos neku operaciju?",
								"Izaberi Opciju", JOptionPane.YES_NO_OPTION);
						if (opcija2 == 0) {
							dispose();
							new IzmeniObrisiServisPage().setVisible(true);
						} else {
							dispose();
							new AdminMain().setVisible(true);
						}

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena.Pokusajte ponovo.");
						e1.printStackTrace();
					}

				}
			}
		});

		btnZavrsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					if (currentServis.isStatusServisa() == false) {
						JOptionPane.showMessageDialog(null, "Servis je zavrsen.");
					} else {
						try {
							String oldLine = currentServis.WriteToString();
							currentServis.setStatusServisa(false);

							double ukupnaCena = CenaHandling.IzracunajCenu(currentServis);

							int opcija3 = JOptionPane.showConfirmDialog(null,
									"Da li musterija zeli iskoristiti nagradne bodove?", "Izaberi Opciju",
									JOptionPane.YES_NO_OPTION);

							if (opcija3 == 0) {
								Musterija mus = currentServis.getAutomobil().getVlasnik();
								if (mus.getBrojBodova() != 0) {
									double novaCena = CenaHandling.SmanjiCenu(ukupnaCena, mus);
									CenaHandling.NulirajBodove(mus);
									JOptionPane.showMessageDialog(null, "Cena servisa sa popustom je = " + novaCena);
									currentServis.setCena(novaCena);
								}

								else {
									JOptionPane.showMessageDialog(null, "Musterija ima 0 bodova.");
									CenaHandling.UvecajBodove(currentServis.getAutomobil().getVlasnik());
								}
							}

							else {
								CenaHandling.UvecajBodove(currentServis.getAutomobil().getVlasnik());
							}

							String newLine = currentServis.WriteToString();
							LoadDatabase.sviServisi.replace(currentServis.getId(), currentServis);
							FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.servisAutomobilaPath);
							ServisnaKnjizica.UpdateKnjizica(currentServis);
							JOptionPane.showMessageDialog(null, "Uspesno zavrsen servis.");

							int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite da izvrsite jos neku operaciju?",
									"Izaberi Opciju", JOptionPane.YES_NO_OPTION);
							if (opcija2 == 0) {
								dispose();
								new IzmeniObrisiServisPage().setVisible(true);
							} else {
								dispose();
								new AdminMain().setVisible(true);
							}
						} catch (Exception not) {
							JOptionPane.showMessageDialog(null, "Morate odabrati servis.");
							not.printStackTrace();
						}
					}
				}
			}
		});

		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					try {
						String oldLine = currentServis.WriteToString();
						currentServis.setDeleted(true);
						String newLine = currentServis.WriteToString();
						LoadDatabase.sviServisi.remove(((Identifiable) currentServis).getId());
						FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.servisAutomobilaPath);
						JOptionPane.showMessageDialog(null, "Uspesno obrisan servis.");
						int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite da izvrsite jos neku operaciju?",
								"Izaberi Opciju", JOptionPane.YES_NO_OPTION);
						if (opcija2 == 0) {
							dispose();
							new IzmeniObrisiServisPage().setVisible(true);
						} else {
							dispose();
							new AdminMain().setVisible(true);
						}
					} catch (Exception none) {
						JOptionPane.showMessageDialog(null, "Izaberite servis.");
					}
				}
			}
		});

	}
}
