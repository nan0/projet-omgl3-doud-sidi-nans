import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueSaisiePeriodique extends Vue {

	private JPanel contentPane;
	private JTextField textFieldIssn;
	private JTextField textFieldNom;
	private JButton btnAnnuler;
	private JButton btnEnregistrer;
	
	/**
	 * Create the frame.
	 */
	public VueSaisiePeriodique(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'un nouveau periodique");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 450, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIssn = new JLabel("Numéro ISSN");
		lblIssn.setBounds(41, 26, 151, 15);
		contentPane.add(lblIssn);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(210, 24, 188, 19);
		contentPane.add(textFieldIssn);
		textFieldIssn.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom du periodique");
		lblNom.setBounds(41, 53, 150, 15);
		contentPane.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(210, 55, 188, 19);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisiePeriodique.this);
			}
		});
		btnAnnuler.setBounds(41, 107, 107, 25);
		contentPane.add(btnAnnuler);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn = textFieldIssn.getText();
				String nom = textFieldNom.getText();
				
				getControleur().nouvPeriodique(issn, nom);
				}
		});
		btnEnregistrer.setBounds(291, 107, 107, 25);
		contentPane.add(btnEnregistrer);
		
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			btnEnregistrer.setEnabled(true);
			btnAnnuler.setEnabled(true);
			break;
			}
		}
	}
}

