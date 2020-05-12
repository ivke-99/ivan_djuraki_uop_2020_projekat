package view.AdminPages.ServisniDeoTools;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import classes.ServisniDeo;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.AdminMain;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodajSimetricniDeoPage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajSimetricniDeoPage dialog = new DodajSimetricniDeoPage();
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
	public DodajSimetricniDeoPage() {
		setTitle("Kreiraj Simetricni Deo");
		setBounds(100, 100, 297, 180);
		getContentPane().setLayout(null);
		
		JComboBox<ServisniDeo> cbDeo = new JComboBox<ServisniDeo>();
		cbDeo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbDeo.setBounds(98, 27, 148, 33);
		FillingControl.PopuniDeloveSimetrija(cbDeo);
		getContentPane().add(cbDeo);
		cbDeo.setSelectedIndex(-1);
		
		JLabel lblNewLabel = new JLabel("Servisni Deo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 26, 91, 33);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("DODAJ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				boolean opcija = FillingControl.PrintOpcija();
				boolean existence = false;
				if (opcija != false) {
				var original = (ServisniDeo)cbDeo.getSelectedItem();
				
				/*check*/
					
				try {
					ServisniDeo kopija = new ServisniDeo(original);
					for(ServisniDeo s : LoadDatabase.sviDelovi.values()) {
						if(s.getNazivDela().equalsIgnoreCase(kopija.getNazivDela())) {
							JOptionPane.showMessageDialog(null, "Simetricni Deo vec postoji.");
							existence = true;
						}
					}
					if (existence != true) {
					kopija.setId(FileHandling.servisniDeoPath);
					LoadDatabase.sviDelovi.put(kopija.getId(), kopija);
					String write = kopija.WriteToString();
					FileHandling.WriteToFile(write, FileHandling.servisniDeoPath);
					JOptionPane.showMessageDialog(null, "Uspesno dodat simetricni deo!");
					cbDeo.setSelectedIndex(-1);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati deo.");
				}
					
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(10, 99, 91, 33);
		getContentPane().add(btnNewButton);
		
		JButton btnIzadji = new JButton("IZADJI");
		btnIzadji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminMain().setVisible(true);
			}
		});
		btnIzadji.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIzadji.setBounds(180, 99, 91, 33);
		getContentPane().add(btnIzadji);

	}
}
