package view.AdminPages.MusterijaTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import classes.Musterija;
import classes.Osoba.TipoviKorisnika;
import controller.FileHandling;
import controller.Validator;
import dao.LoadDatabase;
import view.AdminMain;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
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
	 * 
	 * @throws ParseException
	 */

	public AddMusterijaPage() throws ParseException {
		setTitle("Dodaj Musteriju Page");
		setBounds(100, 100, 450, 395);
		getContentPane().setLayout(null);

		/*
		 * Korisnik ne moze nista drugo da unese sem brojeva, tako da je ovim setter i
		 * validacije settera prakticno nepotrebno.
		 */

		MaskFormatter phoneMask = new MaskFormatter("(###)-(###)-(####)");
		phoneMask.setValidCharacters("0123456789");
		phoneMask.setPlaceholderCharacter('_');

		JLabel lblNewLabel = new JLabel("Ime:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 29, 46, 14);
		getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(134, 23, 124, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Prezime:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 58, 77, 14);
		getContentPane().add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(134, 52, 124, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("JMBG:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 86, 46, 14);
		getContentPane().add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(134, 80, 124, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Pol:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 114, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "musko", "zensko" }));
		comboBox.setBounds(134, 106, 104, 22);
		getContentPane().add(comboBox);
		comboBox.setSelectedIndex(-1);

		JLabel lblNewLabel_4 = new JLabel("Adresa:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 145, 66, 14);
		getContentPane().add(lblNewLabel_4);

		textField_3 = new JTextField();
		textField_3.setBounds(134, 139, 124, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Broj telefona:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 172, 104, 21);
		getContentPane().add(lblNewLabel_5);

		textField_5 = new JTextField();
		textField_5.setBounds(134, 201, 124, 20);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Korisnicko ime:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(10, 204, 104, 14);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Lozinka:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(10, 235, 104, 14);
		getContentPane().add(lblNewLabel_7);

		textField_6 = new JTextField();
		textField_6.setBounds(134, 232, 124, 20);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setBounds(134, 263, 46, 20);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Broj bodova:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(10, 266, 90, 20);
		getContentPane().add(lblNewLabel_8);

		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

		btnNewButton.setBounds(43, 310, 89, 35);
		getContentPane().add(btnNewButton);

		JFormattedTextField txtPhone = new JFormattedTextField(phoneMask);
		txtPhone.setBounds(134, 170, 104, 20);
		getContentPane().add(txtPhone);

		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				comboBox.setSelectedIndex(-1);
				txtPhone.setText("");
			}
		});
		btnNewButton_1.setBounds(154, 310, 89, 35);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Izadji");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(334, 310, 90, 35);
		getContentPane().add(btnNewButton_2);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcija = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Izaberite opciju",
						JOptionPane.YES_NO_OPTION);
				if (opcija != 1) {
					if (textField.getText().isEmpty() || textField_1.getText().isEmpty()
							|| textField_2.getText().isEmpty() || textField_3.getText().isEmpty()
							|| !(txtPhone.getText().length() == 18) || textField_5.getText().isEmpty()
							|| textField_6.getText().isEmpty() || textField_7.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Neka polja su prazna ili nisu dobrog formata. Pokusajte ponovo.");
					}

					else {
						String pol = (String) comboBox.getSelectedItem();
						try {

							if (Validator.CheckForIme(textField_5.getText()) == false)
								JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji.");
							else {
								KreirajMusteriju(pol, txtPhone);
								JOptionPane.showMessageDialog(null, "Uspesan upis.");
								dispose();
								new AdminMain().setVisible(true);
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());

						}

					}
				}
			}
		});

	}

	public void KreirajMusteriju(String pol, JFormattedTextField txtPhone) throws Exception {
		String wrt = "";
		Musterija mus = new Musterija();
		mus.setId(FileHandling.musterijaPath);
		mus.setIme(textField.getText());
		mus.setPrezime(textField_1.getText());
		mus.setJmbg(textField_2.getText());
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
		wrt = mus.WriteToString();
		FileHandling.WriteToFile(wrt, FileHandling.musterijaPath);
		LoadDatabase.sveMusterije.put(mus.getId(), mus);

	}
}
