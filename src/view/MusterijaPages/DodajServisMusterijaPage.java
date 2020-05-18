package view.MusterijaPages;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import classes.Automobil;
import classes.ServisAutomobila;
import controller.FileHandling;
import controller.FillingControl;
import dao.LoadDatabase;
import view.MusterijaMain;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DodajServisMusterijaPage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajServisMusterijaPage dialog = new DodajServisMusterijaPage();
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
	public DodajServisMusterijaPage() {
		setTitle("Dodaj novi servis");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Izaberite automobil:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 26, 120, 22);
		getContentPane().add(lblNewLabel);
		
		JComboBox<Automobil> cbAuto = new JComboBox<Automobil>();
		cbAuto.setBounds(135, 27, 219, 22);
		getContentPane().add(cbAuto);
		cbAuto.setSelectedIndex(-1);
		
		JLabel lblKratakOpisServisa = new JLabel("Kratak opis servisa:");
		lblKratakOpisServisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKratakOpisServisa.setBounds(10, 71, 120, 22);
		getContentPane().add(lblKratakOpisServisa);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(135, 71, 219, 93);
		getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcija=JOptionPane.showConfirmDialog(null, "Da li ste sigurni?","Izaberite opciju",JOptionPane.YES_NO_OPTION);
				if(opcija !=1) {
					if(cbAuto.getSelectedIndex() == -1 || textArea.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Neka polja nisu dobro unesena.");
					}
					
					else {
						ServisAutomobila s = new ServisAutomobila();
						try {
							s.setId(FileHandling.servisAutomobilaPath);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						s.setAutomobil((Automobil)cbAuto.getSelectedItem());
						s.setOpis(textArea.getText());
						s.setStatusServisa(true);
						s.setTermin(null);
						s.setDeoZaServis(null);
						s.setServiser(null);
						s.setCena(0);
						
						LoadDatabase.sviServisi.put(s.getId(), s);
						
						String writing = s.WriteToString();
						try {
						FileHandling.WriteToFile(writing, FileHandling.servisAutomobilaPath);
						JOptionPane.showMessageDialog(null, "Uspesan upis");
						}catch(Exception upis) {
							JOptionPane.showMessageDialog(null, "Neuspesan upis.");
						}
					}
				}
				
			}
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbAuto.setSelectedIndex(-1);
				textArea.setText("");
			}
		});
		btnNewButton_1.setBounds(167, 227, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Izadji");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MusterijaMain().setVisible(true);
			}
		});
		btnNewButton_2.setBounds(335, 227, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		FillingControl.PopuniComboBoxAuto(cbAuto);
		
		
	}
	

}
