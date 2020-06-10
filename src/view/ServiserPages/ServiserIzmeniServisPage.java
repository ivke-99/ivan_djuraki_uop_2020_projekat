package view.ServiserPages;

import java.awt.EventQueue;

import javax.swing.JDialog;

import classes.Identifiable;
import classes.Musterija;
import classes.ServisAutomobila;
import classes.ServisnaKnjizica;
import controller.CenaHandling;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.ServiserMain;
import view.AdminPages.ServisTools.IzmeniObrisiServisPage;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.event.ItemListener;
import java.text.ParseException;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class ServiserIzmeniServisPage extends JDialog {
	private JTextField txtID;
	private JTextField txtAuto;
	private JTextField txtTermin;
	private JTextField txtCena;
	private ServisAutomobila servis;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiserIzmeniServisPage dialog = new ServiserIzmeniServisPage();
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
	public ServiserIzmeniServisPage() {
		setTitle("Moji servisi");
		setBounds(100, 100, 527, 429);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Izadji");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ServiserMain().setVisible(true);
			}
		});
		btnNewButton.setBounds(412, 356, 89, 23);
		getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(78, 109, 38, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(150, 108, 62, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		JLabel lblAutomobil = new JLabel("Automobil");
		lblAutomobil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutomobil.setBounds(66, 147, 74, 14);
		getContentPane().add(lblAutomobil);

		txtAuto = new JTextField();
		txtAuto.setEditable(false);
		txtAuto.setBounds(150, 141, 175, 20);
		getContentPane().add(txtAuto);
		txtAuto.setColumns(10);

		JLabel lblTermin = new JLabel("Termin");
		lblTermin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTermin.setBounds(78, 177, 62, 14);
		getContentPane().add(lblTermin);

		txtTermin = new JTextField();
		txtTermin.setEditable(false);
		txtTermin.setBounds(150, 171, 175, 20);
		getContentPane().add(txtTermin);
		txtTermin.setColumns(10);

		JTextArea textAreaOpis = new JTextArea();
		textAreaOpis.setBounds(150, 218, 175, 62);
		getContentPane().add(textAreaOpis);

		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOpis.setBounds(78, 217, 67, 23);
		getContentPane().add(lblOpis);

		JButton btnNewButton_1 = new JButton("Izmeni");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean opcija = FillingControl.PrintOpcija();

				if (opcija != false) {

					if (!(textAreaOpis.getText().equals("") || txtCena.getText().equals(""))) {

						if (textAreaOpis.isEditable() == false || txtCena.isEditable() == false) {
							JOptionPane.showMessageDialog(null, "Servis je zavrsen, nije ga moguce izmeniti.");
						}
						
						else {
							try {
							ServisAutomobila noviObjekat = new ServisAutomobila();
							String oldLine = servis.WriteToString();
							noviObjekat.setIdFromFile(servis.getId());
							noviObjekat.setAutomobil(servis.getAutomobil());
							noviObjekat.setCena(Double.parseDouble(txtCena.getText()));
							noviObjekat.setOpis(textAreaOpis.getText());
							noviObjekat.setStatusServisa(servis.isStatusServisa());
							noviObjekat.setDeoZaServis(servis.getDeoZaServis());
							noviObjekat.setTermin(servis.getTermin());

							LoadDatabase.sviServisi.replace(servis.getId(), noviObjekat);
							String writing = noviObjekat.WriteToString();
							FileHandling.ReplaceLineInFile(oldLine, writing, FileHandling.servisAutomobilaPath);
							DajOpcije();
							}catch(Exception e3) {
								JOptionPane.showMessageDialog(null, e3.getMessage());
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Neka polja su prazna.");
					}

				}
			}
		});
		btnNewButton_1.setBounds(78, 356, 89, 23);
		getContentPane().add(btnNewButton_1);

		JComboBox<ServisAutomobila> cbServis = new JComboBox<ServisAutomobila>();

		FillingControl.PopuniServiseZaServisera(cbServis);
		cbServis.setBounds(150, 69, 199, 22);
		getContentPane().add(cbServis);
		cbServis.setSelectedIndex(-1);

		JButton btnNewButton_2 = new JButton("Otkazi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					if (textAreaOpis.isEditable() == false || txtCena.isEditable() == false) {
						JOptionPane.showMessageDialog(null, "Servis je zavrsen, nije ga moguce otkazati.");
					}

					else {
						try {
							var brisanje = cbServis.getSelectedItem();
							txtID.setText("");
							txtAuto.setText("");
							txtTermin.setText("");
							txtCena.setText("");
							textAreaOpis.setText("");

							LoadDatabase.sviServisi.remove(((Identifiable) brisanje).getId());
							cbServis.setSelectedIndex(-1);
							cbServis.removeItem(brisanje);
							cbServis.setSelectedIndex(-1);
							brisanje = null;

						} catch (Exception unknown) {
							JOptionPane.showMessageDialog(null, "Neuspelo brisanje.");
						}
					}
				}
			}
		});
		btnNewButton_2.setBounds(177, 356, 89, 23);
		getContentPane().add(btnNewButton_2);

		JLabel lblCena = new JLabel("Cena");
		lblCena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCena.setBounds(333, 341, 102, 23);

		JLabel lblIzaberiteServis = new JLabel("Izaberite servis:");
		lblIzaberiteServis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIzaberiteServis.setBounds(36, 71, 152, 14);
		getContentPane().add(lblIzaberiteServis);

		JLabel lblCena_1 = new JLabel("Cena");
		lblCena_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCena_1.setBounds(78, 293, 67, 23);
		getContentPane().add(lblCena_1);

		txtCena = new JTextField();
		txtCena.setBounds(150, 298, 118, 20);
		getContentPane().add(txtCena);
		txtCena.setColumns(10);

		JRadioButton rdZavrsen = new JRadioButton("Zavrsen");
		rdZavrsen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdZavrsen.setEnabled(false);
		buttonGroup.add(rdZavrsen);
		rdZavrsen.setBounds(134, 321, 86, 28);
		getContentPane().add(rdZavrsen);

		JRadioButton rdZapocet = new JRadioButton("Zapocet");
		rdZapocet.setEnabled(false);
		rdZapocet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonGroup.add(rdZapocet);
		rdZapocet.setBounds(222, 321, 86, 28);
		getContentPane().add(rdZapocet);

		JButton btnNewButton_3 = new JButton("Zavrsi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					if (servis.isStatusServisa() == false) {
						JOptionPane.showMessageDialog(null, "Servis je zavrsen.");
					} else {
						try {
							String oldLine = servis.WriteToString();
							servis.setStatusServisa(false);

							double ukupnaCena = CenaHandling.IzracunajCenu(servis);

							int opcija3 = JOptionPane.showConfirmDialog(null,
									"Da li musterija zeli iskoristiti nagradne bodove?", "Izaberi Opciju",
									JOptionPane.YES_NO_OPTION);

							if (opcija3 == 0) {
								Musterija mus = servis.getAutomobil().getVlasnik();
								if (mus.getBrojBodova() != 0) {
									double novaCena = CenaHandling.SmanjiCenu(ukupnaCena, mus);
									CenaHandling.NulirajBodove(mus);
									JOptionPane.showMessageDialog(null, "Cena servisa sa popustom je = " + novaCena);
									servis.setCena(novaCena);
								}

								else {
									JOptionPane.showMessageDialog(null, "Musterija ima 0 bodova.");
									CenaHandling.UvecajBodove(servis.getAutomobil().getVlasnik());
									servis.setCena(ukupnaCena);
								}
							}

							else {
								CenaHandling.UvecajBodove(servis.getAutomobil().getVlasnik());
								servis.setCena(ukupnaCena);
							}

							String newLine = servis.WriteToString();
							LoadDatabase.sviServisi.replace(servis.getId(), servis);
							FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.servisAutomobilaPath);
							ServisnaKnjizica.UpdateKnjizica(servis);
							JOptionPane.showMessageDialog(null, "Uspesno zavrsen servis.");
							
							DajOpcije();
							
						} catch (Exception not) {
							JOptionPane.showMessageDialog(null, "Morate odabrati servis.");
						}
					}
				}
			}
		});
		btnNewButton_3.setBounds(276, 356, 89, 23);
		getContentPane().add(btnNewButton_3);

		cbServis.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					servis = null;
					servis = (ServisAutomobila) cbServis.getSelectedItem();
					txtID.setText(servis.getId() + "");
					txtAuto.setText(servis.getAutomobil().getMarka() + "");
					txtTermin.setText(ServisAutomobila.ConvertDateToString(servis.getTermin()));

					if (servis.isStatusServisa() == false) {
						textAreaOpis.setEditable(false);
						txtCena.setEditable(false);
					}

					else {
						textAreaOpis.setEditable(true);
						txtCena.setEditable(true);
					}
					textAreaOpis.setText(servis.getOpis());
					txtCena.setText(servis.getCena() + "");

					if (servis.isStatusServisa() == true) {
						rdZapocet.setSelected(true);
					} else {
						rdZavrsen.setSelected(true);
					}

				} catch (Exception none) {
					txtID.setText("");
					txtAuto.setText("");
					txtTermin.setText("");
					txtCena.setText("");
					textAreaOpis.setText("");
				}
			}
		});

	}
	
	public void DajOpcije() {
		int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite da izvrsite jos neku operaciju?",
				"Izaberi Opciju", JOptionPane.YES_NO_OPTION);
		if (opcija2 == 0) {
			dispose();
			try {
				new IzmeniObrisiServisPage().setVisible(true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			dispose();
			new ServiserMain().setVisible(true);
		}
	}
}
