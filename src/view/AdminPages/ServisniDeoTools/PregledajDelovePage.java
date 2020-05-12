package view.AdminPages.ServisniDeoTools;

import java.awt.EventQueue;

import javax.swing.JDialog;

public class PregledajDelovePage extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledajDelovePage dialog = new PregledajDelovePage();
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
	public PregledajDelovePage() {
		setBounds(100, 100, 450, 300);

	}

}
