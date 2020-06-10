package view.AdminPages.ServisTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import classes.Automobil;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.ServisniDeo;
import controller.FileHandling;
import controller.FillingControl;
import controller.Validator;
import dao.LoadDatabase;
import view.AdminMain;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class DodajServisPage extends JDialog {
	private JTextField txtOpis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajServisPage dialog = new DodajServisPage();
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
	public DodajServisPage() throws ParseException {
		setTitle("Dodaj Servis Automobila");
		setBounds(100, 100, 466, 483);
		getContentPane().setLayout(null);

		MaskFormatter dateMask = new MaskFormatter("##/##/#### ##:##");
		dateMask.setValidCharacters("0123456789");
		dateMask.setPlaceholderCharacter('_');

		JComboBox<Automobil> cbAuto = new JComboBox<Automobil>();
		cbAuto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbAuto.setBounds(165, 38, 177, 30);
		getContentPane().add(cbAuto);
		FillingControl.PopuniComboBoxSviAutomobili(cbAuto);
		cbAuto.setSelectedIndex(-1);

		JLabel lblNewLabel = new JLabel("Izaberi auto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 36, 106, 30);
		getContentPane().add(lblNewLabel);

		JComboBox<Serviser> cbServiser = new JComboBox<Serviser>();
		cbServiser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbServiser.setBounds(165, 81, 177, 30);
		getContentPane().add(cbServiser);
		FillingControl.PopuniComboBoxSviServiseri(cbServiser);
		cbServiser.setSelectedIndex(-1);

		JLabel lblIzaberiServisera = new JLabel("Izaberi servisera:");
		lblIzaberiServisera.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIzaberiServisera.setBounds(10, 79, 145, 30);
		getContentPane().add(lblIzaberiServisera);

		JLabel lblIzaberiDatum = new JLabel("Izaberi datum:");
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

		JLabel lblIzaberiDelove = new JLabel("Izaberi delove:");
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

		JButton btnDodaj = new JButton("DODAJ");
		btnDodaj.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDodaj.setBounds(10, 408, 101, 30);
		getContentPane().add(btnDodaj);

		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.setBounds(121, 408, 101, 30);
		getContentPane().add(btnReset);

		cbAuto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				listModel.removeAllElements();
				FillingControl.PopuniListuDelova(list, listModel, cbAuto);
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAuto.setSelectedIndex(-1);
				cbServiser.setSelectedIndex(-1);
				txtDatum.setText("");
				txtOpis.setText("");
				listModel.removeAllElements();
			}
		});

		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					if(cbAuto.getSelectedIndex() == -1 || cbServiser.getSelectedIndex() == -1 || txtDatum.getText().isEmpty() || 
							txtOpis.getText().isEmpty() || list.getSelectedIndex() == -1 ) {
						JOptionPane.showMessageDialog(null, "Polja ne mogu biti prazna.");
					}
					
					else if(Validator.isThisDateValid(txtDatum.getText(), "dd/MM/yyyy") == false) {
						JOptionPane.showMessageDialog(null, "Date nije pravilno unesen. Mora biti tipa dd/MM/yyyy");
					}
					else {
					try {
						ServisAutomobila s = new ServisAutomobila();
						s.setId(FileHandling.servisAutomobilaPath);
						s.setAutomobil((Automobil) cbAuto.getSelectedItem());
						s.setServiser((Serviser) cbServiser.getSelectedItem());
						s.setStatusServisa(true);
						s.setTermin(ServisAutomobila.ConvertStringToDate(txtDatum.getText()));
						s.setOpis(txtOpis.getText());
						ArrayList<ServisniDeo> delovi = null;
						delovi = (ArrayList<ServisniDeo>) list.getSelectedValuesList();
						s.setDeoZaServis(delovi);
						String wrt = s.WriteToString();
						LoadDatabase.sviServisi.put(s.getId(), s);
						FileHandling.WriteToFile(wrt, FileHandling.servisAutomobilaPath);
						JOptionPane.showMessageDialog(null, "Uspesan upis!");
						DajIzlazneOpcije();

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena.Pokusajte ponovo.");
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					}

				}
			}
		});
	}
	
	public void DajIzlazneOpcije() {
		int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite dodati jos?", "Izaberi Opciju",
				JOptionPane.YES_NO_OPTION);
		if (opcija2 == 0) {
			dispose();
			try {
				new DodajServisPage().setVisible(true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			dispose();
			new AdminMain().setVisible(true);
		}
	}

}
