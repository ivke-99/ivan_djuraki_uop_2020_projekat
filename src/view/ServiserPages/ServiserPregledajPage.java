package view.ServiserPages;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import classes.Musterija;
import classes.ServisAutomobila;
import classes.ServisniDeo;
import controller.LoginHandling;
import dao.LoadDatabase;

public class ServiserPregledajPage extends JDialog {
	
	private JTable table;
	private JTextField txtId;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField;
	private static String delovi="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiserPregledajPage dialog = new ServiserPregledajPage();
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
	public ServiserPregledajPage() throws ParseException {
		try {
			LoadDatabase.UcitajCeluBazu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBounds(100, 100, 450, 300);
		
		
		MaskFormatter phoneMask = new MaskFormatter("(###)-(###)-(####)");
		phoneMask.setValidCharacters("0123456789");
		phoneMask.setPlaceholderCharacter('_');
		
		setTitle("Prikaz svih servisera page");
		setBounds(100, 100, 871, 580);
		
		try {
			LoadDatabase.LoadAllMusterija();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTable t= new JTable(toTableModel(LoadDatabase.sviServisi));
		getContentPane().add(t, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(t);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(getPreferredSize());
		panel.add(panel_1, BorderLayout.CENTER);
		
		panel_1.setBorder(new EmptyBorder(40,10,10,10));
		panel_1.setLayout(null);
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(341, 67, 62, 20);
		panel_1.add(txtId);
		txtId.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(341, 98, 131, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(341, 127, 131, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(341, 158, 304, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(341, 222, 293, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(341, 284, 293, 20);
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(341, 315, 293, 20);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(240, 68, 46, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setBounds(240, 99, 46, 14);
		panel_1.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setBounds(228, 128, 51, 14);
		panel_1.add(lblPrezime);
		
		JLabel lblJmbg = new JLabel("JMBG");
		lblJmbg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJmbg.setBounds(235, 164, 51, 14);
		panel_1.add(lblJmbg);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(341, 189, 131, 22);
		panel_1.add(comboBox);
		
		JLabel lblPol = new JLabel("Pol");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setBounds(240, 197, 51, 14);
		panel_1.add(lblPol);
		
		JFormattedTextField formattedTextField = new JFormattedTextField(phoneMask);
		formattedTextField.setBounds(341, 253, 168, 20);
		panel_1.add(formattedTextField);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresa.setBounds(235, 225, 51, 14);
		panel_1.add(lblAdresa);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona");
		lblBrojTelefona.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBrojTelefona.setBounds(203, 256, 83, 14);
		panel_1.add(lblBrojTelefona);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
		lblKorisnickoIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKorisnickoIme.setBounds(203, 287, 93, 14);
		panel_1.add(lblKorisnickoIme);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinka.setBounds(228, 316, 93, 14);
		panel_1.add(lblLozinka);
		
		JLabel lblBrojBodova = new JLabel("Plata");
		lblBrojBodova.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBrojBodova.setBounds(214, 351, 93, 14);
		panel_1.add(lblBrojBodova);
		
		textField = new JTextField();
		textField.setBounds(341, 346, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Izmeni");
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Obrisi");
		panel_3.add(btnNewButton_1);
	}
	
	
	
	
	
	public static TableModel toTableModel(HashMap<Integer,ServisAutomobila> map) {
	    DefaultTableModel model = new DefaultTableModel(
	        new Object[] { "Id Servisa", "Automobil", "Termin", "Opis", "Status servisa", "Delovi za servis"}, 0
	    );
	    
	   
	    
	    for (HashMap.Entry<Integer,ServisAutomobila> entry : map.entrySet()) {
	    	
	    	if(entry.getValue().getServiser().getId() == LoginHandling.trenutniKorisnik.getId()) 
	    	{
	    	delovi="";
	    	for(int i=0; i<entry.getValue().getDeoZaServis().size(); i++) {
	    		
	    		if ( i == entry.getValue().getDeoZaServis().size() - 1) {
	    			delovi=delovi+ entry.getValue().getDeoZaServis().get(i).getNazivDela();
	    		}
	    		else {
	    			delovi=delovi+ entry.getValue().getDeoZaServis().get(i).getNazivDela()+",";
	    		}
	    	}
	    		
	    		
	        model.addRow(new Object[] { entry.getKey(), entry.getValue().getAutomobil().getMarka()
	        		,entry.getValue().getTermin(),entry.getValue().getOpis(),entry.getValue().isStatusServisaString(), delovi});
	    	}
	    	
	    }
	   
	    
	    
	    
	    return model;
	}
}
