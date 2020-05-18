package view.AdminPages.AdminTools;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import classes.Admin;
import classes.Osoba.TipoviKorisnika;
import controller.FileHandling;
import dao.LoadDatabase;
import view.AdminMain;

@SuppressWarnings("serial")
public class AddAdminPage extends JDialog {
	protected JTextField txtIme;
	protected JTextField txtPrezime;
	protected JTextField txtJMBG;
	protected JTextField txtAdresa;
	protected JTextField txtkorIme;
	protected JTextField txtLozinka;
	protected JTextField txtPlata;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAdminPage dialog = new AddAdminPage();
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
	public AddAdminPage() throws ParseException {
		setTitle("Dodaj Admina");
		setBounds(100, 100, 475, 473);
		getContentPane().setLayout(null);
		setBounds(100, 100, 380, 455);
		getContentPane().setLayout(null);
		
		MaskFormatter phoneMask = new MaskFormatter("(###)-(###)-(####)");
		phoneMask.setValidCharacters("0123456789");
		phoneMask.setPlaceholderCharacter('_');
		
		txtIme = new JTextField();
		txtIme.setBounds(134, 30, 164, 20);
		getContentPane().add(txtIme);
		txtIme.setColumns(10);
		
		txtPrezime = new JTextField();
		txtPrezime.setBounds(134, 61, 164, 20);
		getContentPane().add(txtPrezime);
		txtPrezime.setColumns(10);
		
		txtJMBG = new JTextField();
		txtJMBG.setBounds(134, 92, 164, 20);
		getContentPane().add(txtJMBG);
		txtJMBG.setColumns(10);
		
		txtAdresa = new JTextField();
		txtAdresa.setBounds(134, 156, 164, 20);
		getContentPane().add(txtAdresa);
		txtAdresa.setColumns(10);
		
		JComboBox<String> cbPol = new JComboBox<String>();
		cbPol.setBounds(134, 123, 96, 22);
		getContentPane().add(cbPol);
		cbPol.setModel(new DefaultComboBoxModel<String>(new String[] {"musko", "zensko"}));
		cbPol.setSelectedIndex(-1);
		
		txtkorIme = new JTextField();
		txtkorIme.setBounds(134, 187, 164, 20);
		getContentPane().add(txtkorIme);
		txtkorIme.setColumns(10);
		
		txtLozinka = new JTextField();
		txtLozinka.setBounds(134, 218, 164, 20);
		getContentPane().add(txtLozinka);
		txtLozinka.setColumns(10);
		
		txtPlata = new JTextField();
		txtPlata.setBounds(133, 282, 86, 20);
		getContentPane().add(txtPlata);
		txtPlata.setColumns(10);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(10, 30, 114, 20);
		getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(10, 64, 114, 20);
		getContentPane().add(lblPrezime);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		lblJmbg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJmbg.setBounds(10, 95, 114, 20);
		getContentPane().add(lblJmbg);
		
		JLabel lblPol = new JLabel("Pol:");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setBounds(10, 127, 114, 20);
		getContentPane().add(lblPol);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresa.setBounds(10, 159, 114, 20);
		getContentPane().add(lblAdresa);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime:");
		lblKorisnickoIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKorisnickoIme.setBounds(10, 190, 114, 20);
		getContentPane().add(lblKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinka.setBounds(10, 221, 114, 20);
		getContentPane().add(lblLozinka);
		
		JLabel lblPlata = new JLabel("Plata:");
		lblPlata.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlata.setBounds(10, 280, 114, 20);
		getContentPane().add(lblPlata);
		
		JButton btnIzadji = new JButton("Izadji");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminMain().setVisible(true);
			}
		});
		btnIzadji.setBounds(265, 382, 89, 23);
		getContentPane().add(btnIzadji);
		
		JFormattedTextField txtBrojTelefona = new JFormattedTextField(phoneMask);
		txtBrojTelefona.setBounds(134, 251, 114, 20);
		getContentPane().add(txtBrojTelefona);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBrojTelefona.setBounds(10, 252, 114, 20);
		getContentPane().add(lblBrojTelefona);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBounds(10, 382, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIme.setText("");
				txtPrezime.setText("");
				txtJMBG.setText("");
				txtAdresa.setText("");
				txtkorIme.setText("");
				txtLozinka.setText("");
				txtPlata.setText("");
				txtBrojTelefona.setText("");
				cbPol.setSelectedIndex(-1);
			}
		});
		btnNewButton_1.setBounds(134, 382, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
				
				if (opcija != 1) {
					
					if(txtIme.getText().isEmpty() || txtPrezime.getText().isEmpty()
							|| txtJMBG.getText().isEmpty() || txtAdresa.getText().isEmpty()
							|| !(txtBrojTelefona.getText().length() == 18) || txtkorIme.getText().isEmpty()  
							|| txtLozinka.getText().isEmpty() || txtPlata.getText().isEmpty() || cbPol.getSelectedIndex() == -1)
						{
							JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena.Pokusajte ponovo.");
						}
					
					else {
						String pol = (String) cbPol.getSelectedItem();
						
						try {
							KreirajAdmina(pol,txtBrojTelefona);
							JOptionPane.showMessageDialog(null, "Uspesan upis.");
							
							txtIme.setText("");
							txtPrezime.setText("");
							txtJMBG.setText("");
							txtAdresa.setText("");
							txtkorIme.setText("");
							txtLozinka.setText("");
							txtPlata.setText("");
							txtBrojTelefona.setText("");
							cbPol.setSelectedIndex(-1);
						}catch(Exception u) {
							JOptionPane.showMessageDialog(null, "Neuspesan upis.Korisnicko ime vec postoji, ili format nekog polja"
									+ "nije dobro unesen.");
						}
					}
				
				}
				
			}
		});
	}
	
	public void KreirajAdmina(String pol,JFormattedTextField txtBrojTelefona) throws Exception {
		String wrt="";
		Admin a = new Admin();
		a.setId(FileHandling.adminPath);
		a.setIme(txtIme.getText());
		a.setPrezime(txtPrezime.getText());
		a.setJmbg(txtJMBG.getText());
		if (pol.equals("musko")) {
			a.setPol(true);
		}
		
		else {
			a.setPol(false);
		}
		a.setAdresa(txtAdresa.getText());
		a.setBrtel(txtBrojTelefona.getText());
		a.setKorisnickoIme(txtkorIme.getText());
		a.setLozinka(txtLozinka.getText());
		a.setPlata(Integer.parseInt(txtPlata.getText()));
		a.setTipkorisnika(TipoviKorisnika.admin);
		wrt = a.WriteToString();
		FileHandling.WriteToFile(wrt,FileHandling.adminPath);
		LoadDatabase.sviAdmini.put(a.getId(), a);
		
		
	}
}
