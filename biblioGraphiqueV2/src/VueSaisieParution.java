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
	private JTextField textFieldIdentification;
	private JTextField textFieldTitre;
	private JTextField textFieldPage;
	private JTextField textFieldNomAut;
	private JButton btnAnnuler1;
	private JButton btnChoisir;
	private JButton btnContinuer;
	private JButton btnAjouterAuteur;
	private JButton btnAjouterArticle;
	private JButton btnAnnuler2;
	private JButton btnTerminer;
	private HashMap<String, Periodique> listToutPeriodiques;
	private HashMap<Integer, Auteur> listToutAuteurs;
	private JScrollPane scrollPane, scrollPane2;
	private List listPer;
	private List listAut;

	
	private Periodique _per;
	private Parution _par;
	private String _identification;
	private HashMap<Integer, String> _noms;
	private boolean existeListe = false;
	private ArrayList<String> tab;
	
	/**
	 * Create the frame.
	 */
	public VueSaisieParution(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'une nouvelle parution");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 600, 650);
		getContentPane().setLayout(null);

		this.setAuteurs(new HashMap<Integer, String>());
		
		btnChoisir = new JButton("Choisir");
		btnChoisir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPer.getSelectedItem() != null) {
					String issn =  getControleur().getVueSaisieParution().getIssnChoisi();
					_per = getControleur().getPeriodique(issn);
					getControleur().getVueSaisieParution().setEtat(finale);
				}
			}
		});
		btnChoisir.setBounds(337, 99, 107, 25);
		getContentPane().add(btnChoisir);
		
		JLabel lblNewLabel = new JLabel("Identification");
		lblNewLabel.setBounds(160, 152, 26, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNomPriodique = new JLabel("Nom périodique");
		lblNomPriodique.setBounds(82, 38, 104, 15);
		getContentPane().add(lblNomPriodique);
		
		textFieldIdentification = new JTextField();
		textFieldIdentification.setBounds(204, 150, 226, 19);
		getContentPane().add(textFieldIdentification);
		textFieldIdentification.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre Article");
		lblNewLabel_1.setBounds(109, 436, 77, 15);
		getContentPane().add(lblNewLabel_1);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(204, 434, 226, 19);
		getContentPane().add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		btnContinuer = new JButton("Continuer");
		btnContinuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPer.getSelectedItem() != null) {
					_identification = textFieldIdentification.getText();
					_par = new Parution(_per, _identification);
					_per.ajouterParution(_identification, _par);
					btnContinuer.setEnabled(false);
					textFieldIdentification.setEnabled(false);
				}
			}
		});
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
				String nom = textFieldNomAut.getText();
				if (listAut.getSelectedItems() != null)
					existeListe = true;
				getControleur().ajouterAuteurArticle(nom, existeListe);
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
		
		btnTerminer = new JButton("Terminer");
		btnTerminer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAuteurs(new HashMap<Integer, String>());
				getControleur().fermerVue(VueSaisieParution.this);
			}
		});
		btnTerminer.setBounds(337, 575, 107, 25);
		getContentPane().add(btnTerminer);
		
		btnAjouterArticle = new JButton("Ajouter Article");
		btnAjouterArticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titre = textFieldTitre.getText();
				String page = textFieldPage.getText();
				if (!_noms.isEmpty() && !titre.isEmpty() && !page.isEmpty() && _par != null) {
					getControleur().nouvArticle(_par, page, titre, _noms);
					getControleur().getVueSaisieParution().setAuteurs(new HashMap<Integer, String>());
					textFieldTitre.setText("");
					textFieldPage.setText("");
				} else {
					Message dialog = new Message("Certaint champs n'ont pas étaient remplis !");
					dialog.setVisible(true);
				} 
			}
		});
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
		
		//List des periodique
		
		listToutPeriodiques = getControleur().getPeriodiques();
		tab = new ArrayList<String>();
		//Iterator it= tab.iterator(); 
		for (int i = listToutPeriodiques.size() - 1; i >= 0 ; i = i - 1) {
			tab.add(((Periodique)listToutPeriodiques.values().toArray()[i]).getIssn());
			tab.add(((Periodique)listToutPeriodiques.values().toArray()[i]).getNom());
		}
		listPer = new List();
		int num = 0;
		for (int i = 0; i < (tab.size() - 1); i = i + 2) {
			listPer.add(tab.get(i+1), num);
			num++;
		}
		scrollPane_2.setViewportView(listPer);
		
		//List des auteurs
		
		listToutAuteurs = getControleur().getAuteurs();
		ArrayList<String> tab2 = new ArrayList<String>();
		//Iterator it= tab.iterator(); 
		for (int i = 0; i < listToutAuteurs.size(); i = i + 1) {
			tab2.add(Integer.toString(i));
			tab2.add(((Auteur)listToutAuteurs.values().toArray()[i]).getNom());
		}
		listAut = new List();
		listAut.setMultipleMode(true);
		listAut.setMultipleSelections(true);
		for (int i = 0; i < (tab2.size() - 1); i = i + 2) {
			listAut.add(tab2.get(i+1), Integer.parseInt(tab2.get(i)));
		}
		scrollPane_1.setViewportView(listAut);
		
	}
	
	public String getIssnChoisi() {
		String issn;
		issn = tab.get(listPer.getSelectedIndex()+listPer.getSelectedIndex());
		return issn;
	}
	
	public void recupAuteurs() {
		int num;
		int i = 0;
		while (i < (listAut.getSelectedItems().length)) {
			num = genererNumAuteurCour();
			/*getControleur().setAuteur(auteur, num);*/
			_noms.put(num, listAut.getSelectedItems()[i]);
			Message dialog = new Message(listAut.getSelectedItems()[i] + " a été ajouté !");
			dialog.setVisible(true);
			i++;
		}
	}
	
	public void setNoms(HashMap<Integer, String> noms) {
		_noms = noms;
	}// Fin setAuteursCreer
	
	int genererNumAuteurCour() {
		if (_noms.isEmpty())
			return 1;
		else
			return (_noms.size() + 1);
	}
	
	public HashMap<Integer, String> getAuteursCour() {
		return _noms;
	}
	
	public void reinitChampAuteur() {
		textFieldNomAut.setText("");
	}
	
	/**
	 * @param periodiques hashtable de periodique à affecter
	 */
	public void setAuteurs(HashMap<Integer, String> noms) {
		_noms = noms;
	}// Fin setPeriodiques
	
	public void setEtat (int etat){
		switch (etat) {
			case initiale: {
				btnChoisir.setEnabled(true);
				btnAnnuler1.setEnabled(true);
				btnContinuer.setEnabled(false);
				btnAjouterAuteur.setEnabled(false);
				btnAjouterArticle.setEnabled(false);
				btnAnnuler2.setEnabled(false);
				btnTerminer.setEnabled(false);
				textFieldIdentification.setEnabled(false);
				textFieldTitre.setEnabled(false);
				textFieldPage.setEnabled(false);
				textFieldNomAut.setEnabled(false);
			break;
			}
			case finale: {
				btnChoisir.setEnabled(false);
				btnAnnuler1.setEnabled(false);
				btnContinuer.setEnabled(true);
				btnAjouterAuteur.setEnabled(true);
				btnAjouterArticle.setEnabled(true);
				btnAnnuler2.setEnabled(true);
				btnTerminer.setEnabled(true);
				textFieldIdentification.setEnabled(true);
				textFieldTitre.setEnabled(true);
				textFieldPage.setEnabled(true);
				textFieldNomAut.setEnabled(true);
			}
		}
	}
}
