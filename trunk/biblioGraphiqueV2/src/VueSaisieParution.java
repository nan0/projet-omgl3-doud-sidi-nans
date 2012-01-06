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
import java.awt.Label;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueSaisieParution extends Vue {
	private JTextField textFieldIssn;
	private JTextField textFieldTitre;
	private JTextField textFieldPage;
	private JTextField textFieldNomAut;
	private JButton btnAnnuler1;
	private JButton btnChoisir;
	private JButton btnContinuer;
	private JButton btnAjouterAuteur;
	private JButton btnAjouterArticle;
	private JButton btnAnnuler2;
	private JButton btnEnregistrer;
	private HashMap<String, Periodique> listToutPeriodiques;
	private HashMap<Integer, Auteur> listToutAuteurs;
	private JScrollPane scrollPane, scrollPane2;
	private List listPer;

	
	private Periodique _per;
	
	/**
	 * Create the frame.
	 */
	public VueSaisieParution(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'une nouvelle parution");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 600, 650);
		getContentPane().setLayout(null);

		btnChoisir = new JButton("Choisir");
		btnChoisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPer.getSelectedItem() != null) {
					_per = getControleur().getPeriodique(listPer.getSelectedItem());
					getControleur().getVueSaisieParution().setEtat(inter1);
				}
			}
		});
		btnChoisir.setBounds(337, 99, 107, 25);
		getContentPane().add(btnChoisir);
		
		JLabel lblNewLabel = new JLabel("Issn");
		lblNewLabel.setBounds(160, 152, 26, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNomPriodique = new JLabel("Nom périodique");
		lblNomPriodique.setBounds(82, 38, 104, 15);
		getContentPane().add(lblNomPriodique);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(204, 150, 226, 19);
		getContentPane().add(textFieldIssn);
		textFieldIssn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre Article");
		lblNewLabel_1.setBounds(109, 436, 77, 15);
		getContentPane().add(lblNewLabel_1);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(204, 434, 226, 19);
		getContentPane().add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		btnContinuer = new JButton("Continuer");
		btnContinuer.setBounds(233, 193, 144, 25);
		getContentPane().add(btnContinuer);
		
		JLabel lblNewLabel_2 = new JLabel("N° page");
		lblNewLabel_2.setBounds(138, 477, 48, 15);
		getContentPane().add(lblNewLabel_2);
		
		textFieldPage = new JTextField();
		textFieldPage.setBounds(204, 475, 226, 19);
		getContentPane().add(textFieldPage);
		textFieldPage.setColumns(10);
		
		JLabel label = new JLabel("Auteur(s)");
		label.setBounds(125, 315, 61, 15);
		getContentPane().add(label);
		
		btnAjouterAuteur = new JButton("Ajouter Auteur");
		btnAjouterAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAjouterAuteur.setBounds(439, 387, 141, 25);
		getContentPane().add(btnAjouterAuteur);
		
		textFieldNomAut = new JTextField();
		textFieldNomAut.setBounds(204, 390, 226, 19);
		getContentPane().add(textFieldNomAut);
		textFieldNomAut.setColumns(10);
		
		JLabel lblNomEtPrenom = new JLabel("Nom et prenom");
		lblNomEtPrenom.setBounds(89, 392, 97, 15);
		getContentPane().add(lblNomEtPrenom);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(337, 575, 107, 25);
		getContentPane().add(btnEnregistrer);
		
		btnAjouterArticle = new JButton("Ajouter Article");
		btnAjouterArticle.setBounds(236, 517, 141, 25);
		getContentPane().add(btnAjouterArticle);
		
		btnAnnuler2 = new JButton("Annuler");
		btnAnnuler2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieParution.this);
			}
		});
		btnAnnuler2.setBounds(139, 575, 107, 25);
		getContentPane().add(btnAnnuler2);
		
		btnAnnuler1 = new JButton("Annuler");
		btnAnnuler1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieParution.this);
			}
		});
		btnAnnuler1.setBounds(138, 99, 108, 25);
		getContentPane().add(btnAnnuler1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(216, 262, 207, 94);
		getContentPane().add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(201, 12, 229, 71);
		getContentPane().add(scrollPane_2);
		
		
		listToutPeriodiques = getControleur().getPeriodiques();
		ArrayList<String> tab = new ArrayList<String>();
		//Iterator it= tab.iterator(); 
		for (int i = 0; i < listToutPeriodiques.size(); i = i + 1) {
			tab.add(Integer.toString(i));
			tab.add(((Periodique)listToutPeriodiques.values().toArray()[i]).getNom());
		}
		listPer = new List();
		for (int i = 0; i < (tab.size() - 1); i = i + 2) {
			listPer.add(tab.get(i+1), Integer.parseInt(tab.get(i)));
		}
		scrollPane_2.setViewportView(listPer);
		
		listToutAuteurs = getControleur().getAuteurs();
		ArrayList<String> tab2 = new ArrayList<String>();
		//Iterator it= tab.iterator(); 
		for (int i = 0; i < listToutAuteurs.size(); i = i + 1) {
			tab2.add(Integer.toString(i));
			tab2.add(((Auteur)listToutAuteurs.values().toArray()[i]).getNom());
		}
		List listAut = new List();
		listAut.setMultipleMode(true);
		listAut.setMultipleSelections(true);
		for (int i = 0; i < (tab2.size() - 1); i = i + 2) {
			listAut.add(tab2.get(i+1), Integer.parseInt(tab2.get(i)));
		}
		scrollPane_1.setViewportView(listAut);
		
	}
	
	public void setEtat (int etat){
		switch (etat) {
			case initiale: {
				btnChoisir.setEnabled(true);
				btnAnnuler1.setEnabled(true);
				btnContinuer.setEnabled(false);
				btnAjouterAuteur.setEnabled(false);
				btnAjouterArticle.setEnabled(false);
				btnAnnuler2.setEnabled(false);
				btnEnregistrer.setEnabled(false);
				textFieldIssn.setEnabled(false);
				textFieldTitre.setEnabled(false);
				textFieldPage.setEnabled(false);
				textFieldNomAut.setEnabled(false);
			break;
			}
			case inter1: {
				btnChoisir.setEnabled(false);
				btnAnnuler1.setEnabled(true);
				btnContinuer.setEnabled(true);
				btnAjouterAuteur.setEnabled(false);
				btnAjouterArticle.setEnabled(false);
				btnAnnuler2.setEnabled(false);
				btnEnregistrer.setEnabled(false);
				textFieldIssn.setEnabled(true);
				textFieldTitre.setEnabled(false);
				textFieldPage.setEnabled(false);
				textFieldNomAut.setEnabled(false);
			}
			case finale: {
				btnChoisir.setEnabled(false);
				btnAnnuler1.setEnabled(false);
				btnContinuer.setEnabled(false);
				btnAjouterAuteur.setEnabled(true);
				btnAjouterArticle.setEnabled(true);
				btnAnnuler2.setEnabled(true);
				btnEnregistrer.setEnabled(true);
				textFieldIssn.setEnabled(true);
				textFieldTitre.setEnabled(true);
				textFieldPage.setEnabled(true);
				textFieldNomAut.setEnabled(true);
			}
		}
	}
}
