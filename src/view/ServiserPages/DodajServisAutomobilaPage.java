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
import classes.ServisniDeo;
import controller.FileHandling;
import controller.LoginHandling;
import dao.LoadDatabase;
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
		try {
			LoadDatabase.UcitajCeluBazu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MaskFormatter dateMask = new MaskFormatter("##/##/#### ##:##");
		dateMask.setValidCharacters("0123456789");
		dateMask.setPlaceholderCharacter('_');
		
		setBounds(100, 100, 568, 428);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Automobil : ");
		lblNewLabel.setBounds(10, 24, 84, 14);
		getContentPane().add(lblNewLabel);
		
		JComboBox<Automobil> cbAuto = new JComboBox<Automobil>();
		
		
		cbAuto.setBounds(86, 20, 250, 22);
		getContentPane().add(cbAuto);
		
		JLabel lblNewLabel_1 = new JLabel("Izaberi delove:");
		lblNewLabel_1.setBounds(10, 167, 72, 14);
		getContentPane().add(lblNewLabel_1);
		
		DefaultListModel<ServisniDeo> listModel=new DefaultListModel<ServisniDeo>();
		JList<ServisniDeo> list = new JList<ServisniDeo>(listModel);
		list.setBounds(92, 151, 138, 67);
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
		lblNewLabel_3.setBounds(10, 103, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					LoadDatabase.UcitajCeluBazu();
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				try {
				ServisAutomobila s = new ServisAutomobila();
				try {
					s.setId(FileHandling.servisAutomobilaPath);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				s.setServiser((Serviser) LoginHandling.trenutniKorisnik);
				s.setOpis(textField.getText());
				s.setStatusServisa(true);
				try {
					s.setTermin(ServisAutomobila.ConvertStringToDate(formattedTextField.getText()));
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Date nije dobro unesen.");
				}
				s.setAutomobil((Automobil) cbAuto.getSelectedItem());
				
				
				ArrayList<ServisniDeo> delovi = null;
				delovi=(ArrayList<ServisniDeo>) list.getSelectedValuesList();
				s.setDeoZaServis(delovi);
				
				var line=new StringJoiner("|");
				line.add(s.getId()+"");
				line.add(s.getAutomobil().getId()+"");
				line.add(s.getServiser().getId()+"");
				line.add(ServisAutomobila.ConvertDateToString(s.getTermin()));
				line.add(s.getOpis());
				line.add(s.isStatusServisa()+"");
				for(ServisniDeo sa : s.getDeoZaServis()) {
					line.add(sa.getId()+"");
				}
				
				FileHandling.WriteToFile("\n"+line.toString(), FileHandling.servisAutomobilaPath);
				
				}catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Nesto nije uredu.Pokusajte opet.");
				}
				
			}
			
			
			
			
		});
		btnNewButton.setBounds(46, 294, 89, 23);
		getContentPane().add(btnNewButton);
		
		
		
		cbAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeAllElements();
				PopuniListuDelova(list,listModel,cbAuto);
			}
		});
		
		
	}
	
	public void ResetFields() {
		
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
