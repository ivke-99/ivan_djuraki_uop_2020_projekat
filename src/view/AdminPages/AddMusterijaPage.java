package view.AdminPages;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import classes.Identifiable;
import classes.Musterija;
import classes.Osoba;
import classes.Osoba.TipoviKorisnika;
import controller.FileHandling;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddMusterijaPage extends JDialog {
	protected JTextField textField;
	protected JTextField textField_1;
	protected JTextField textField_2;
	protected JTextField textField_3;
	protected JTextField textField_5;
	protected JTextField textField_6;
	protected JTextField textField_7;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMusterijaPage dialog = new AddMusterijaPage();
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
	
	
	public AddMusterijaPage() throws ParseException {
		setTitle("Dodaj Musteriju Page");
		setBounds(100, 100, 450, 395);
		getContentPane().setLayout(null);
		
		
		/* Korisnik ne moze nista drugo da unese sem brojeva, tako da
		 * je ovim setter i validacije settera prakticno nepotrebno.
		 */
		
		MaskFormatter phoneMask = new MaskFormatter("(###)-(###)-(####)");
		phoneMask.setValidCharacters("0123456789");
		phoneMask.setPlaceholderCharacter('_');
		
		JLabel lblNewLabel = new JLabel("Ime:");
		lblNewLabel.setBounds(10, 24, 46, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(83, 21, 124, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime:");
		lblNewLabel_1.setBounds(10, 55, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(83, 52, 124, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("JMBG:");
		lblNewLabel_2.setBounds(10, 83, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(83, 80, 124, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Pol:");
		lblNewLabel_3.setBounds(10, 110, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"musko", "zensko"}));
		comboBox.setBounds(83, 106, 124, 22);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("Adresa:");
		lblNewLabel_4.setBounds(10, 142, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(83, 139, 124, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Broj telefona:");
		lblNewLabel_5.setBounds(10, 172, 77, 14);
		getContentPane().add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(83, 201, 124, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Korisnicko ime:");
		lblNewLabel_6.setBounds(10, 204, 104, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Lozinka:");
		lblNewLabel_7.setBounds(10, 235, 46, 14);
		getContentPane().add(lblNewLabel_7);
		
		textField_6 = new JTextField();
		textField_6.setBounds(83, 232, 124, 20);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(83, 263, 124, 20);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Broj bodova:");
		lblNewLabel_8.setBounds(10, 266, 90, 14);
		getContentPane().add(lblNewLabel_8);
		
		JButton btnNewButton = new JButton("Dodaj");
		
		btnNewButton.setBounds(43, 310, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setBounds(154, 310, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JFormattedTextField txtPhone = new JFormattedTextField(phoneMask);
		txtPhone.setBounds(83, 169, 124, 20);
		getContentPane().add(txtPhone);
		
		JButton btnNewButton_2 = new JButton("Pocetna Stranica");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(286, 322, 138, 23);
		getContentPane().add(btnNewButton_2);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty()
						|| textField_2.getText().isEmpty() || textField_3.getText().isEmpty()
						|| !(txtPhone.getText().length() == 18) || textField_5.getText().isEmpty()
						|| textField_6.getText().isEmpty() || textField_7.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Neka polja nisu dobro unesena. Pokusajte ponovo.");
				}
				
				else {
					String pol = (String)comboBox.getSelectedItem();
					try {
						KreirajMusteriju(pol,txtPhone);
						JOptionPane.showMessageDialog(null, "Uspesan upis.");
						dispose();
						new AdminMain().setVisible(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Neuspesan upis.");
						e1.printStackTrace();
					}
					
				}
			}
		});
		
		
	}
	
	public void KreirajMusteriju(String pol,JFormattedTextField txtPhone) throws Exception {
		LoadDatabase.LoadAllMusterija();
		String wrt="";
		Musterija mus = new Musterija();
		mus.setId(FileHandling.musterijaPath);
		mus.setIme(textField.getText());
		mus.setPrezime(textField_1.getText());
		mus.setJmbg(textField_2.getText());
		/* ne znam ni sto sam pravio boolean kad svejedno mogu samo 2 opcije da dam, volim 
		 * komplikovati sebi zivot
		 */
		if (pol.equals("musko")) {
			mus.setPol(true);
		}
		
		else {
			mus.setPol(false);
		}
		mus.setAdresa(textField_3.getText());
		mus.setBrtel(txtPhone.getText());
		mus.setKorisnickoIme(textField_5.getText());
		mus.setLozinka(textField_6.getText());
		mus.setBrojBodova(Integer.parseInt(textField_7.getText()));
		mus.setTipkorisnika(TipoviKorisnika.musterija);
		wrt = "\n"+mus.WriteToString();
		FileHandling.WriteToFile(wrt,FileHandling.musterijaPath);
		LoadDatabase.sveMusterije.put(mus.getId(), mus);
		
		
	}
}
