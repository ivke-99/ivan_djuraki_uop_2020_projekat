package view.AdminPages.ServisniDeoTools;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import classes.Identifiable;
import classes.ServisniDeo;
import classes.Automobil.MarkaiModel;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.AdminMain;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class IzmeniServisniDeoPage extends JDialog {
	private JTextField txtNaziv;
	private JTextField txtCena;
	private ServisniDeo currentDeo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmeniServisniDeoPage dialog = new IzmeniServisniDeoPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public IzmeniServisniDeoPage() {
		setTitle("Dodaj Servisni Deo");
		setBounds(100, 100, 342, 336);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naziv dela:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 66, 84, 35);
		getContentPane().add(lblNewLabel);
		
		JComboBox<MarkaiModel> cbMarkaiModel = new JComboBox<MarkaiModel>();
		cbMarkaiModel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbMarkaiModel.setBounds(154, 112, 137, 31);
		FillingControl.PopuniComboBoxMarkaiModel(cbMarkaiModel);
		getContentPane().add(cbMarkaiModel);
		cbMarkaiModel.setSelectedIndex(-1);
		
		JLabel lblMarkaIModel = new JLabel("Marka i Model:");
		lblMarkaIModel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMarkaIModel.setBounds(10, 112, 108, 31);
		getContentPane().add(lblMarkaIModel);
		
		JLabel lblCena = new JLabel("Cena dela:");
		lblCena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCena.setBounds(10, 154, 108, 31);
		getContentPane().add(lblCena);
		
		JComboBox<ServisniDeo> cbDeo = new JComboBox<ServisniDeo>();
		cbDeo.setSelectedIndex(-1);
		cbDeo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbDeo.setBounds(154, 20, 137, 31);
		getContentPane().add(cbDeo);
		FillingControl.PopuniComboDelovi(cbDeo);
		cbDeo.setSelectedIndex(-1);
		
		txtNaziv = new JTextField();
		txtNaziv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNaziv.setBounds(154, 66, 137, 31);
		getContentPane().add(txtNaziv);
		txtNaziv.setColumns(10);
		
		JButton btnIzmeni = new JButton("IZMENI");
		btnIzmeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean opcija = FillingControl.PrintOpcija();
				if(opcija != false) {
					try {
						String stari = currentDeo.WriteToString();
						ServisniDeo novi = new ServisniDeo();
						novi.setIdFromFile(currentDeo.getId());
						novi.setCena(Double.parseDouble(txtCena.getText()));
						novi.setMarka((MarkaiModel) cbMarkaiModel.getSelectedItem());
						novi.setNazivDela(txtNaziv.getText());
						LoadDatabase.sviDelovi.replace(novi.getId(), novi);
						String write = novi.WriteToString();
						FileHandling.ReplaceLineInFile(stari, write, FileHandling.servisniDeoPath);
						JOptionPane.showMessageDialog(null, "Uspesna izmena!");
						
						int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite da izvrsite jos neku operaciju?", "Izaberi Opciju", JOptionPane.YES_NO_OPTION);
						if(opcija2 == 0) {
							dispose();
							new IzmeniServisniDeoPage().setVisible(true);
						}
						else {
							dispose();
							new AdminMain().setVisible(true);
						}
						
						
					}catch(Exception cena) {
						JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena.");
					}
				}
			}
		});
		btnIzmeni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIzmeni.setBounds(10, 255, 97, 31);
		getContentPane().add(btnIzmeni);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.setBounds(117, 255, 97, 31);
		getContentPane().add(btnReset);
		
		JButton btnIzadji = new JButton("IZADJI");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminMain().setVisible(true);
				dispose();
			}
		});
		btnIzadji.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIzadji.setBounds(219, 255, 97, 31);
		getContentPane().add(btnIzadji);
		
		txtCena = new JTextField();
		txtCena.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCena.setColumns(10);
		txtCena.setBounds(154, 154, 139, 31);
		getContentPane().add(txtCena);
		
		JLabel lblDeo = new JLabel("Deo:");
		lblDeo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDeo.setBounds(10, 20, 84, 35);
		getContentPane().add(lblDeo);
		
	
		JButton btnObrisi = new JButton("OBRISI");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean opcija = FillingControl.PrintOpcija();
				if (opcija != false) {
					try {
					String oldLine = currentDeo.WriteToString();
					currentDeo.setDeleted(true);
					String newLine = currentDeo.WriteToString();
					LoadDatabase.sviDelovi.remove(((Identifiable) currentDeo).getId());
					FileHandling.ReplaceLineInFile(oldLine, newLine, FileHandling.servisniDeoPath);
					JOptionPane.showMessageDialog(null, "Uspesno obrisan deo.");
					int opcija2 = JOptionPane.showConfirmDialog(null, "Zelite da izvrsite jos neku operaciju?", "Izaberi Opciju", JOptionPane.YES_NO_OPTION);
					if(opcija2 == 0) {
						dispose();
						new IzmeniServisniDeoPage().setVisible(true);
					}
					else {
						dispose();
						new AdminMain().setVisible(true);
					}
					}catch(Exception none) {
						JOptionPane.showMessageDialog(null, "Izaberite deo.");
					}
				}
			
			}
		});
		btnObrisi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnObrisi.setBounds(117, 213, 97, 31);
		getContentPane().add(btnObrisi);
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbDeo.setSelectedIndex(-1);
				cbMarkaiModel.setSelectedIndex(-1);
				txtCena.setText("");
				txtNaziv.setText("");
			}
		});
		
		cbDeo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				currentDeo = null;
				currentDeo = (ServisniDeo) cbDeo.getSelectedItem();
				cbMarkaiModel.setSelectedItem(currentDeo.getMarka());
				txtCena.setText(currentDeo.getCena()+"");
				txtNaziv.setText(currentDeo.getNazivDela());
			}
		});
		
	}
}
