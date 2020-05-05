package view.ServiserPages;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import classes.Automobil;
import classes.ServisAutomobila;
import classes.Serviser;
import classes.ServisnaKnjizica;
import classes.ServisniDeo;
import controller.FileHandling;
import controller.LoginHandling;
import controller.SpecificObjectCreation;
import dao.LoadDatabase;
import view.ServiserMain;

import javax.swing.JScrollBar;
import javax.swing.text.MaskFormatter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DodajServisAutomobilaPage extends JDialog {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajServisAutomobilaPage dialog = new DodajServisAutomobilaPage();
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
	public DodajServisAutomobilaPage() throws ParseException {
		setTitle("Dodaj Servis Automobila");
		try {
			LoadDatabase.UcitajCeluBazu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MaskFormatter dateMask = new MaskFormatter("##/##/#### ##:##");
		dateMask.setValidCharacters("0123456789");
		dateMask.setPlaceholderCharacter('_');
		
		setBounds(100, 100, 365, 368);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Automobil : ");
		lblNewLabel.setBounds(10, 24, 84, 14);
		getContentPane().add(lblNewLabel);
		
		JComboBox<Automobil> cbAuto = new JComboBox<Automobil>();
		
		
		cbAuto.setBounds(86, 20, 197, 22);
		getContentPane().add(cbAuto);
		
		JLabel lblNewLabel_1 = new JLabel("Izaberi delove:");
		lblNewLabel_1.setBounds(10, 167, 72, 14);
		getContentPane().add(lblNewLabel_1);
		
		DefaultListModel<ServisniDeo> listModel=new DefaultListModel<ServisniDeo>();
		JList<ServisniDeo> list = new JList<ServisniDeo>(listModel);
		list.setBounds(92, 151, 191, 67);
		getContentPane().add(list);
		
		PopuniComboBoxAuto(cbAuto);
		
		JLabel lblNewLabel_2 = new JLabel("Datum :");
		lblNewLabel_2.setBounds(10, 60, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JFormattedTextField formattedTextField = new JFormattedTextField(dateMask);
		formattedTextField.setBounds(86, 57, 144, 20);
		getContentPane().add(formattedTextField);
		
		textField = new JTextField();
		textField.setBounds(96, 100, 187, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Kratak opis:");
		lblNewLabel_3.setBounds(10, 103, 84, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
				
				if (opcija != 1) {
				if (cbAuto.getSelectedIndex() == -1 || !(formattedTextField.getText().length() == 16 ) 
						|| textField.getText().equals("") || list.getSelectedIndex() == -1 ) {
					JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena. Probajte ponovo.");
				}
				
				else {
				
				try {
					LoadDatabase.UcitajCeluBazu();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
				ServisAutomobila s = new ServisAutomobila();
				
				s.setId(FileHandling.servisAutomobilaPath);
				s.setServiser((Serviser) LoginHandling.trenutniKorisnik);
				s.setOpis(textField.getText());
				s.setStatusServisa(true);
				s.setTermin(ServisAutomobila.ConvertStringToDate(formattedTextField.getText()));
				s.setAutomobil((Automobil) cbAuto.getSelectedItem());
				ArrayList<ServisniDeo> delovi = null;
				delovi=(ArrayList<ServisniDeo>) list.getSelectedValuesList();
				s.setDeoZaServis(delovi);
				
				SpecificObjectCreation.CreateServisFromUserInput(s);
				
				
					JOptionPane.showMessageDialog(null, "Uspesan upis ! ");
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Nesto nije uredu.Pokusajte opet.");
				}
				
				
			}
			
			
			}
			}
			
		});
		btnNewButton.setBounds(10, 281, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		
		btnNewButton_1.setBounds(123, 281, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Main Page");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ServiserMain().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(250, 281, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		
		
		cbAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PopuniListuDelova(list,listModel,cbAuto);
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				formattedTextField.setText("");
				textField.setText("");
				cbAuto.setSelectedIndex(-1);
				list.setSelectedIndex(-1);
				}catch (Exception n) {
					/*bacace gresku koja nije greska ako nema ovog 
					 * try catcha
					 */
				}
			}
		});
		
	}
	
	
	public void PopuniComboBoxAuto(JComboBox<Automobil> combo) {
		
		for (HashMap.Entry<Integer,Automobil> entry : LoadDatabase.sviAutomobili.entrySet()) {
			combo.addItem(entry.getValue());
			combo.setSelectedIndex(-1);
		}
	}
	
	public void PopuniListuDelova(JList<ServisniDeo> lista,DefaultListModel<ServisniDeo> listModel,JComboBox<Automobil> cbAuto) {
		for (HashMap.Entry<Integer,ServisniDeo> entry : LoadDatabase.sviDelovi.entrySet()) {
			
			var auto=(Automobil)cbAuto.getSelectedItem();
			if (auto.getMarka().equals(entry.getValue().getMarka())) {
			listModel.addElement(entry.getValue());
			}
		}
		
	}
}
