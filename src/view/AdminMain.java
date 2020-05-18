package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import controller.LoginHandling;
import view.AdminPages.AdminTools.AddAdminPage;
import view.AdminPages.AdminTools.IzmeniAdminePage;
import view.AdminPages.AdminTools.PregledajAdminePage;
import view.AdminPages.AutomobilTools.AddAutomobilPage;
import view.AdminPages.AutomobilTools.IzmeniAutomobilPage;
import view.AdminPages.AutomobilTools.PregledajAllAutomobilPage;
import view.AdminPages.MusterijaTools.AddMusterijaPage;
import view.AdminPages.MusterijaTools.IzmeniMusterijuPage;
import view.AdminPages.ServisTools.DodajServisPage;
import view.AdminPages.ServisTools.IzmeniObrisiServisPage;
import view.AdminPages.ServisTools.PregledajSveServise;
import view.AdminPages.ServisTools.ZakaziServisMusterijiPage;
import view.AdminPages.ServiserTools.AddServiserPage;
import view.AdminPages.ServiserTools.IzmeniServiserPage;
import view.AdminPages.ServiserTools.PregledajServiserPage;
import view.AdminPages.ServisnaKnjizicaTools.PregledajServisneKnjizicePage;
import view.AdminPages.ServisniDeoTools.DodajServisniDeoPage;
import view.AdminPages.ServisniDeoTools.DodajSimetricniDeoPage;
import view.AdminPages.ServisniDeoTools.IzmeniServisniDeoPage;
import view.AdminPages.ServisniDeoTools.PregledajDelovePage;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class AdminMain extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain dialog = new AdminMain();
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
	public AdminMain() {
		setTitle("Admin Main Page");
		setBounds(100, 100, 631, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Musterija");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Kreiraj");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AddMusterijaPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Izmeni");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniMusterijuPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Obrisi");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new IzmeniMusterijuPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Pregledaj");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new view.AdminPages.MusterijaTools.PregledajMusterijePage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Serviser");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Kreiraj");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AddServiserPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Izmeni");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniServiserPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Obrisi");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniServiserPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Pregledaj");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PregledajServiserPage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_2 = new JMenu("Admin");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Kreiraj");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AddAdminPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Izmeni");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniAdminePage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Obrisi");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniAdminePage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Pregledaj");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PregledajAdminePage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_11);
		
		JMenu mnNewMenu_3 = new JMenu("Automobil");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Kreiraj");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AddAutomobilPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_12);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Izmeni");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniAutomobilPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_13);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Obrisi");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniAutomobilPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_14);
		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Pregledaj");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PregledajAllAutomobilPage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_15);
		
		JMenu mnNewMenu_4 = new JMenu("Servis Automobila");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_16 = new JMenuItem("Dodaj");
		mntmNewMenuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new DodajServisPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_16);
		
		JMenuItem mntmNewMenuItem_17 = new JMenuItem("Zakazi Musteriji");
		mntmNewMenuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ZakaziServisMusterijiPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_17);
		
		JMenuItem mntmNewMenuItem_18 = new JMenuItem("Izmeni/Obrisi/Zavrsi");
		mntmNewMenuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new IzmeniObrisiServisPage().setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_18);
		
		JMenuItem mntmNewMenuItem_19 = new JMenuItem("Pregledaj");
		mntmNewMenuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PregledajSveServise().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_19);
		
		JMenu mnNewMenu_6 = new JMenu("Servisna Knjizica");
		menuBar.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_26 = new JMenuItem("Obrisi");
		mnNewMenu_6.add(mntmNewMenuItem_26);
		
		JMenuItem mntmNewMenuItem_27 = new JMenuItem("Pregledaj");
		mntmNewMenuItem_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PregledajServisneKnjizicePage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_27);
		
		JMenu mnNewMenu_7 = new JMenu("Servisni Deo");
		mnNewMenu_7.setHorizontalAlignment(SwingConstants.TRAILING);
		menuBar.add(mnNewMenu_7);
		
		JMenuItem mntmNewMenuItem_28 = new JMenuItem("Dodaj");
		mntmNewMenuItem_28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DodajServisniDeoPage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_28);
		
		JMenuItem mntmNewMenuItem_29 = new JMenuItem("Dodaj Simetricni Deo");
		mntmNewMenuItem_29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DodajSimetricniDeoPage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_29);
		
		JMenuItem mntmNewMenuItem_30 = new JMenuItem("Obrisi");
		mntmNewMenuItem_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzmeniServisniDeoPage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_30);
		
		JMenuItem mntmNewMenuItem_20 = new JMenuItem("Izmeni");
		mntmNewMenuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IzmeniServisniDeoPage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_20);
		
		JMenuItem mntmNewMenuItem_31 = new JMenuItem("Pregledaj");
		mntmNewMenuItem_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PregledajDelovePage().setVisible(true);
				dispose();
			}
		});
		mnNewMenu_7.add(mntmNewMenuItem_31);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Izadji");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(516, 205, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Dobrodosli" + " "+LoginHandling.trenutniKorisnik.getIme()+" "+
				LoginHandling.trenutniKorisnik.getPrezime()+"!");
				lblNewLabel.setBounds(10, 11, 434, 25);
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
				getContentPane().add(lblNewLabel);

	}
}
