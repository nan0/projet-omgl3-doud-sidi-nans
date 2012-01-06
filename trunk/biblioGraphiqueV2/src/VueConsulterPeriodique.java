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
import java.awt.List;
import javax.swing.JScrollPane;
/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueConsulterPeriodique extends Vue {

	private JPanel contentPane;
	private JTextField textFieldIssn;
	private JTextField textFieldTitrePer;
	
	private JButton btnRechercher;
	private JButton btnAnnuler;
	private JButton btnFermer;
	private JScrollPane scrollPane_1;
	private List listParution;
	
	/**
	 * Create the frame.
	 */
	public VueConsulterPeriodique(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'un nouveau periodique");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Issn");
		lblNewLabel.setBounds(105, 26, 26, 15);
		contentPane.add(lblNewLabel);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(149, 24, 114, 19);
		contentPane.add(textFieldIssn);
		textFieldIssn.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(275, 21, 107, 25);
		contentPane.add(btnRechercher);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(275, 58, 107, 25);
		contentPane.add(btnAnnuler);
		
		JLabel lblTitrePriodique = new JLabel("Titre périodique");
		lblTitrePriodique.setBounds(31, 120, 100, 15);
		contentPane.add(lblTitrePriodique);
		
		textFieldTitrePer = new JTextField();
		textFieldTitrePer.setBounds(149, 118, 233, 19);
		contentPane.add(textFieldTitrePer);
		textFieldTitrePer.setColumns(10);
		
		JLabel lblArticles = new JLabel("Parutions");
		lblArticles.setBounds(72, 164, 59, 15);
		contentPane.add(lblArticles);
		
		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(275, 248, 107, 25);
		contentPane.add(btnFermer);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(149, 164, 233, 72);
		contentPane.add(scrollPane_1);
		
		listParution = new List();
		scrollPane_1.setViewportView(listParution);
		
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			btnRechercher.setEnabled(true);
			btnAnnuler.setEnabled(false);
			btnFermer.setEnabled(false);
			break;
			}
		}
	}
}
