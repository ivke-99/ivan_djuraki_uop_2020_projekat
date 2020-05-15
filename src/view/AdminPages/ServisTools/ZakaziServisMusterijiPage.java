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
import javax.swing.text.MaskFormatter;

import classes.Automobil;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.ServisniDeo;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.AdminMain;

public class ZakaziServisMusterijiPage extends JDialog {
	private JTextField txtOpis;
	private ServisAutomobila currentServis;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZakaziServisMusterijiPage dialog = new ZakaziServisMusterijiPage();
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
	 * @throws ParseException 
	 */
	public ZakaziServisMusterijiPage() throws ParseException {
		setTitle("Zakazi Musteriji Servis");
		setBounds(100, 100, 466, 483);
		getContentPane().setLayout(null);
		
		MaskFormatter dateMask = new MaskFormatter("##/##/#### ##:##");
		dateMask.setValidCharacters("0123456789");
		dateMask.setPlaceholderCharacter('_');
		
		JComboBox<ServisAutomobila> cbServis = new JComboBox<ServisAutomobila>();
		cbServis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbServis.setBounds(165, 38, 177, 30);
		getContentPane().add(cbServis);
		FillingControl.PopuniNeZakazaneServise(cbServis);
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
		
		DefaultListModel<ServisniDeo> listModel=new DefaultListModel<ServisniDeo>();
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
		
		cbServis.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				currentServis=null;
				listModel.removeAllElements();
				FillingControl.PopuniListuDelova2(list, listModel, cbServis);
				currentServis = (ServisAutomobila)cbServis.getSelectedItem();
				txtOpis.setText(currentServis.getOpis());
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbServis.setSelectedIndex(-1);
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
				delovi=(ArrayList<ServisniDeo>) list.getSelectedValuesList();
				s.setDeoZaServis(delovi);
				String newLine=s.WriteToString();
				LoadDatabase.sviServisi.replace(currentServis.getId(), s);
				FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.servisAutomobilaPath);
				JOptionPane.showMessageDialog(null, "Uspesan upis!");
				int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite dodati jos?", "Izaberi Opciju", JOptionPane.YES_NO_OPTION);
				if(opcija2 == 0) {
					dispose();
					new DodajServisPage().setVisible(true);
				}
				else {
					dispose();
					new AdminMain().setVisible(true);
				}
				
				}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena.Pokusajte ponovo.");
				}
				
				
			}	
		}
		});
	}

	}


