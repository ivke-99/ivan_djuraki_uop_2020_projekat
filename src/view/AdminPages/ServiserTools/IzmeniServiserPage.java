package view.AdminPages.ServiserTools;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import classes.Identifiable;
import classes.Serviser;
import classes.Serviser.Specijalizacija;
import classes.Osoba.TipoviKorisnika;
import controller.FileHandling;
import controller.FillingControl;
import controller.Validator;
import dao.LoadDatabase;
import view.AdminMain;

@SuppressWarnings("serial")
public class IzmeniServiserPage extends JDialog {
	protected JTextField txtIme;
	protected JTextField txtPrezime;
	protected JTextField txtJMBG;
	protected JTextField txtAdresa;
	protected JTextField txtkorIme;
	protected JTextField txtLozinka;
	protected JTextField txtPlata;
	protected Serviser currentServiser;
	protected JComboBox<Serviser> cbServiser;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmeniServiserPage dialog = new IzmeniServiserPage();
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
	public IzmeniServiserPage() throws ParseException {
		setBounds(100, 100, 380, 500);
		setTitle("Izmeni ili Obrisi Servisera");
		setBounds(100, 100, 380, 455);
		getContentPane().setLayout(null);
		
		MaskFormatter phoneMask = new MaskFormatter("(###)-(###)-(####)");
		phoneMask.setValidCharacters("0123456789");
		phoneMask.setPlaceholderCharacter('_');
		
		JComboBox<Serviser> cbServiser = new JComboBox<Serviser>();
		FillingControl.PopuniComboBoxSviServiseri(cbServiser);
		cbServiser.setBounds(134, 39, 174, 22);
		getContentPane().add(cbServiser);
		cbServiser.setSelectedIndex(-1);
		
		JLabel lblNewLabel = new JLabel("Izaberi servisera:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 41, 114, 20);
		getContentPane().add(lblNewLabel);
		
		txtIme = new JTextField();
		txtIme.setBounds(134, 72, 164, 20);
		getContentPane().add(txtIme);
		txtIme.setColumns(10);
		
		txtPrezime = new JTextField();
		txtPrezime.setBounds(134, 103, 164, 20);
		getContentPane().add(txtPrezime);
		txtPrezime.setColumns(10);
		
		txtJMBG = new JTextField();
		txtJMBG.setBounds(134, 134, 164, 20);
		getContentPane().add(txtJMBG);
		txtJMBG.setColumns(10);
		
		txtAdresa = new JTextField();
		txtAdresa.setBounds(134, 198, 164, 20);
		getContentPane().add(txtAdresa);
		txtAdresa.setColumns(10);
		
		JComboBox<String> cbPol = new JComboBox<String>();
		cbPol.setBounds(134, 165, 96, 22);
		getContentPane().add(cbPol);
		cbPol.setModel(new DefaultComboBoxModel<String>(new String[] {"musko", "zensko"}));
		cbPol.setSelectedIndex(-1);
		
		txtkorIme = new JTextField();
		txtkorIme.setBounds(134, 229, 164, 20);
		getContentPane().add(txtkorIme);
		txtkorIme.setColumns(10);
		
		txtLozinka = new JTextField();
		txtLozinka.setBounds(134, 260, 164, 20);
		getContentPane().add(txtLozinka);
		txtLozinka.setColumns(10);
		
		txtPlata = new JTextField();
		txtPlata.setBounds(133, 324, 86, 20);
		getContentPane().add(txtPlata);
		txtPlata.setColumns(10);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(10, 72, 114, 20);
		getContentPane().add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(10, 106, 114, 20);
		getContentPane().add(lblPrezime);
		
		JLabel lblJmbg = new JLabel("JMBG:");
		lblJmbg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJmbg.setBounds(10, 137, 114, 20);
		getContentPane().add(lblJmbg);
		
		JLabel lblPol = new JLabel("Pol:");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setBounds(10, 169, 114, 20);
		getContentPane().add(lblPol);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresa.setBounds(10, 201, 114, 20);
		getContentPane().add(lblAdresa);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime:");
		lblKorisnickoIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKorisnickoIme.setBounds(10, 232, 114, 20);
		getContentPane().add(lblKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinka.setBounds(10, 263, 114, 20);
		getContentPane().add(lblLozinka);
		
		JLabel lblBrojBodova = new JLabel("Plata:");
		lblBrojBodova.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBrojBodova.setBounds(10, 322, 114, 20);
		getContentPane().add(lblBrojBodova);
		
		JButton btnIzmeni = new JButton("Izmeni");
		btnIzmeni.setBounds(10, 382, 89, 23);
		getContentPane().add(btnIzmeni);
		
		JButton btnObrisi = new JButton("Obrisi");
		btnObrisi.setBounds(130, 382, 89, 23);
		getContentPane().add(btnObrisi);
		
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
		txtBrojTelefona.setBounds(134, 293, 114, 20);
		getContentPane().add(txtBrojTelefona);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBrojTelefona.setBounds(10, 294, 114, 20);
		getContentPane().add(lblBrojTelefona);
		
		JLabel lblSpecijalizacija = new JLabel("Specijalizacija:");
		lblSpecijalizacija.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpecijalizacija.setBounds(10, 351, 114, 20);
		getContentPane().add(lblSpecijalizacija);
		
		JComboBox<Specijalizacija> cbSpecijalizacija = new JComboBox<Specijalizacija>();
		cbSpecijalizacija.setBounds(134, 349, 114, 22);
		getContentPane().add(cbSpecijalizacija);
		FillingControl.PopuniSpecijalizaciju(cbSpecijalizacija);
		cbSpecijalizacija.setSelectedIndex(-1);
		
		
		cbServiser.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				currentServiser= null;
				currentServiser= (Serviser)cbServiser.getSelectedItem();
				try {
				txtIme.setText(currentServiser.getIme());
				txtPrezime.setText(currentServiser.getPrezime());
				txtJMBG.setText(currentServiser.getJmbg());
				txtAdresa.setText(currentServiser.getAdresa());
				cbPol.setSelectedItem(currentServiser.isPol());
				txtkorIme.setText(currentServiser.getKorisnickoIme());
				txtLozinka.setText(currentServiser.getLozinka());
				txtBrojTelefona.setText(currentServiser.getBrtel());
				txtPlata.setText(currentServiser.getPlata()+"");
				cbSpecijalizacija.setSelectedItem(currentServiser.getSpec());
				}catch(Exception deleted) {
					ResetFields(txtBrojTelefona,cbPol, cbSpecijalizacija);
				}
			}
		});
		
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
				if (opcija != 1 ) {
					
					
					
					if(txtIme.getText().isEmpty() || txtPrezime.getText().isEmpty()
							|| txtJMBG.getText().isEmpty() || txtAdresa.getText().isEmpty()
							|| !(txtBrojTelefona.getText().length() == 18) || txtkorIme.getText().isEmpty()  
							|| txtLozinka.getText().isEmpty() || txtPlata.getText().isEmpty() || cbServiser.getSelectedIndex() == -1
							|| cbSpecijalizacija.getSelectedItem().equals(Specijalizacija.none))
						{
						JOptionPane.showMessageDialog(null,"Neka polja nisu dobro unesena. Pokusajte ponovo.");
						} else
						try {
							
							if (!(currentServiser.getKorisnickoIme().equals(txtkorIme.getText())))
							{
							if (Validator.CheckForIme(txtkorIme.getText()) == false) {
								JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji.");
							}
							}
							else {
								try {
									IzmeniServisera(currentServiser,txtBrojTelefona,cbPol,cbSpecijalizacija);
									JOptionPane.showMessageDialog(null, "Uspesna izmena!");
									int opcija2=JOptionPane.showConfirmDialog(null, "Da li zelite jos neku operaciju?","Izaberite Opciju",JOptionPane.YES_NO_OPTION);
									if (opcija2 == 1) {
										dispose();
										new AdminMain().setVisible(true);
									}
									
									if(opcija2 == 0) {
										dispose();
										new IzmeniServiserPage().setVisible(true);
									}
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, "Greska pri izmeni.Proverite da li ste uneli sva polja.");
									e1.printStackTrace();
								}
								
								
							}
						} catch (HeadlessException e1) {}
						  catch (Exception e1) {}
						
					
					
				}
			}
		});
		
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
				if (opcija != 1) {
					if (cbServiser.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Morate izabrati musteriju.");
					}
					
					try {
						var brisanje = cbServiser.getSelectedItem();
						ResetFields(txtBrojTelefona,cbPol, cbSpecijalizacija);
						LoadDatabase.sviServiseri.remove(((Identifiable) brisanje).getId());
						cbServiser.setSelectedIndex(-1);
						cbServiser.removeItem(brisanje);
						cbServiser.setSelectedIndex(-1);
						JOptionPane.showMessageDialog(null, "Uspesno brisanje!");
							}catch(Exception unknown) {
								JOptionPane.showMessageDialog(null, "Neuspelo brisanje.");
								unknown.printStackTrace();
							}
				}
				
				
			}
		});

	}
	
public void IzmeniServisera(Serviser old,JFormattedTextField txtBrojTelefona,JComboBox<String> cbPol,JComboBox<Specijalizacija> cbSpecijalizacija) throws Exception {
		
		String oldLine = old.WriteToString();
		
		Serviser novi = new Serviser();
		novi.setIdFromFile(old.getId());
		novi.setIme(txtIme.getText());
		novi.setPrezime(txtPrezime.getText());
		novi.setJmbg(txtJMBG.getText());
		novi.setAdresa(txtAdresa.getText());
		novi.setBrtel(txtBrojTelefona.getText());
		novi.setKorisnickoImeUnique(txtkorIme.getText());
		novi.setLozinka(txtLozinka.getText());
		novi.setTipkorisnika(TipoviKorisnika.serviser);
		if(cbPol.getSelectedItem().equals("musko")) 
			novi.setPol(true);
		else 
			novi.setPol(false);
		novi.setPlata(Integer.parseInt(txtPlata.getText()));
		novi.setSpec( (Specijalizacija) cbSpecijalizacija.getSelectedItem());
		LoadDatabase.sviServiseri.replace(currentServiser.getId(), novi);
		
		String newLine = novi.WriteToString();
		FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.musterijaPath);
	}
	
	public void ResetFields(JFormattedTextField txtBrojTelefona,JComboBox<String> cbPol,JComboBox<Specijalizacija> cbSpecijalizacija) {
		txtIme.setText("");
		txtPrezime.setText("");
		txtJMBG.setText("");
		txtAdresa.setText("");
		cbPol.setSelectedIndex(-1);
		txtkorIme.setText("");
		txtLozinka.setText("");
		txtBrojTelefona.setText("");
		txtPlata.setText("");
		cbSpecijalizacija.setSelectedIndex(-1);
		
	}

}
