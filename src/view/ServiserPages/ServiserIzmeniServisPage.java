package view.ServiserPages;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import classes.Identifiable;
import classes.ServisAutomobila;
import classes.ServisniDeo;
import controller.FileHandling;
import controller.FillingControl;
import controller.LoginHandling;
import dao.LoadDatabase;
import view.ServiserMain;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ServiserIzmeniServisPage extends JDialog {
	private JTextField txtID;
	private JTextField txtAuto;
	private JTextField txtTermin;
	private JTextField txtCena;
	private ServisAutomobila servis;

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
				
				int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
				
				if (opcija != 1) {
					
				if (!(textAreaOpis.getText().equals("") || txtCena.getText().equals(""))) {
					
					if (textAreaOpis.isEditable() == false || txtCena.isEditable() == false) {
						JOptionPane.showMessageDialog(null, "Servis je zavrsen, nije ga moguce izmeniti.");
					}
					
					else {
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
				
				String lines="";
				try {
					lines = LoadDatabase.LoadLinesFromFile(FileHandling.servisAutomobilaPath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String[] NewLines = lines.split("\n");
				for( String line : NewLines) {
						
					if(line.equals(oldLine)) {
						
						lines = lines.replace(oldLine, writing);
					}
				}
				
				FileHandling.OverWriteFile(lines, FileHandling.servisAutomobilaPath);
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Polja ne mogu biti prazna.");
				}
				
				}
			}
		});
		btnNewButton_1.setBounds(78, 356, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JComboBox<ServisAutomobila> cbServis = new JComboBox<ServisAutomobila>();
		/*ostar debugging potreban ovde */
		
		FillingControl.PopuniServiseZaServisera(cbServis);
		cbServis.setBounds(150, 69, 199, 22);
		getContentPane().add(cbServis);
		cbServis.setSelectedIndex(-1);
		
		JButton btnNewButton_2 = new JButton("Otkazi");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*ovo radi,ne pitatajte me kako radi,
				 * ni zasto tako radi
				 */
				
				int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
				if (opcija != 1) {
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
				brisanje=null;
				
					}catch(Exception unknown) {
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
		
		JButton btnNewButton_3 = new JButton("Zavrsi");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton_3.setBounds(276, 356, 89, 23);
		getContentPane().add(btnNewButton_3);
		
		
		cbServis.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					servis= null;
					servis = (ServisAutomobila)cbServis.getSelectedItem();
					txtID.setText(servis.getId()+"");
					txtAuto.setText(servis.getAutomobil().getMarka()+"");
					txtTermin.setText(ServisAutomobila.ConvertDateToString(servis.getTermin()));
					
					if(servis.isStatusServisa() == false ) {
						textAreaOpis.setEditable(false);
						txtCena.setEditable(false);
					}
					
					else {
						textAreaOpis.setEditable(true);
						txtCena.setEditable(true);
					}
					textAreaOpis.setText(servis.getOpis());
					txtCena.setText(servis.getCena()+"");
					}catch(Exception none) {
						txtID.setText("");
						txtAuto.setText("");
						txtTermin.setText("");
						txtCena.setText("");
						textAreaOpis.setText("");
					}
			}
		});
		

	}
}
