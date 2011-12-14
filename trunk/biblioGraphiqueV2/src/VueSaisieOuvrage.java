import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JScrollBar;
import java.awt.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JScrollPane;

/**
 * Fenêtre de saisie d'un ouvrage Code du JFrame généré par Window Builder/Swing
 * Designer.
 * 
 * @author IUT, A.Culet
 * @version 1.0
 */
public class VueSaisieOuvrage extends Vue {

	private JPanel contentPane;
	private JTextField textFieldIsbn;
	private JTextField textFieldTitre;
	private JTextField textFieldDate;
	private JTextField textFieldEditeur;
	private JButton buttonEnreg;
	private JButton buttonAnnuler;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private HashMap<Integer, Auteur> _auteurs;
	private List list;
	private HashMap<Integer, Auteur> listToutAuteurs;
	private JScrollPane scrollPane;
	private List list_1;
	private boolean existe = false;

	/**
	 * Create the frame.
	 */
	public VueSaisieOuvrage(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'un nouvel ouvrage");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setAuteurs(new HashMap<Integer, Auteur>());
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(99, 32, 61, 15);
		contentPane.add(lblNewLabel);

		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(170, 32, 141, 19);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(99, 66, 61, 15);
		contentPane.add(lblNewLabel_1);

		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(170, 65, 141, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);

		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = textFieldIsbn.getText();
				String titre = textFieldTitre.getText();
				String editeur = textFieldEditeur.getText();
				String dateEdition = textFieldDate.getText();

				getControleur().nouvOuvrage(isbn, titre, editeur, dateEdition,
						_auteurs);
				_auteurs = null;
			}
		});
		buttonEnreg.setBounds(302, 396, 107, 25);
		contentPane.add(buttonEnreg);

		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieOuvrage.this);
			}
		});
		buttonAnnuler.setBounds(38, 396, 107, 25);
		contentPane.add(buttonAnnuler);

		JLabel lblNewLabel_2 = new JLabel("Date édition (MM/AAAA)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(12, 120, 154, 33);
		contentPane.add(lblNewLabel_2);

		textFieldDate = new JTextField();
		textFieldDate.setBounds(170, 127, 141, 19);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);

		JLabel lblEditeur = new JLabel("Editeur");
		lblEditeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditeur.setBounds(99, 93, 61, 15);
		contentPane.add(lblEditeur);

		textFieldEditeur = new JTextField();
		textFieldEditeur.setColumns(10);
		textFieldEditeur.setBounds(170, 96, 141, 19);
		contentPane.add(textFieldEditeur);

		
		listToutAuteurs = getControleur().getAuteurs();
		ArrayList<String> tab = new ArrayList<String>();
		Iterator it= tab.iterator(); 
		for (int i = 1; i <= listToutAuteurs.size(); i = i + 3) {
			tab.add(Integer.toString(i));
			tab.add(listToutAuteurs.get(i).getNom());
			tab.add(listToutAuteurs.get(i).getPrenom());
		}
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 165, 200, 108);
		contentPane.add(scrollPane);
		
		list = new List();
		list.setMultipleMode(true);
		list.setFont(new Font("Dialog", Font.BOLD, 12));
		list.setForeground(Color.BLACK);
		list.setBackground(Color.WHITE);
		list.setMultipleSelections(true);
		for (int i = 0; i < (tab.size() - 2); i = i + 3) {
			list.add(tab.get(i+1) + " " + tab.get(i+2), Integer.parseInt(tab.get(i)));
		}
		list.setBounds(170, 165, 150, 80);
		scrollPane.setViewportView(list);
			
		textFieldNom = new JTextField();
		textFieldNom.setBounds(170, 296, 114, 19);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		JButton btnAjouter = new JButton("Ajouter");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = textFieldNom.getText();
				String prenom = textFieldPrenom.getText();
				if (list.getSelectedItems() != null)
					existe = true;
				getControleur().ajouterAuteur(nom, prenom, existe);
			}
		});
		btnAjouter.setBounds(302, 327, 107, 25);
		contentPane.add(btnAjouter);

		JLabel lblAuteurs = new JLabel("Auteur(s)");
		lblAuteurs.setBounds(99, 165, 61, 15);
		contentPane.add(lblAuteurs);

		JLabel lblSelectionnerUnAuteur = new JLabel("Selectionner un auteur existant ou ajouter en un à la main");
		lblSelectionnerUnAuteur.setBounds(38, 275, 371, 15);
		contentPane.add(lblSelectionnerUnAuteur);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(170, 330, 114, 19);
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		lblNom = new JLabel("Nom");
		lblNom.setBounds(99, 298, 61, 15);
		contentPane.add(lblNom);

		lblPrenom = new JLabel("Prénom");
		lblPrenom.setBounds(99, 332, 61, 15);
		contentPane.add(lblPrenom);
	

	}
	
	public void recupAuteurs() {
		int[] auteursVect = list.getSelectedIndexes();
		int num;
		int i = 0;
		while (i < (list.getSelectedItems().length)) {
			Auteur auteur = new Auteur(listToutAuteurs.get(auteursVect[i]).getNom(),
					listToutAuteurs.get(auteursVect[i]).getPrenom());
			num = getControleur().genererNumAuteur();
			/*getControleur().setAuteur(auteur, num);*/
			_auteurs.put(num, auteur);
		}
	}
	
	public HashMap<Integer, Auteur> getAuteursCour() {
		return _auteurs;
	}
	
	public void reinitChampAuteur() {
		textFieldNom.setText("");
		textFieldPrenom.setText("");
	}
	
	/**
	 * @param periodiques hashtable de periodique à affecter
	 */
	public void setAuteurs(HashMap<Integer, Auteur> auteurs) {
		_auteurs = auteurs;
	}// Fin setPeriodiques

	public void setEtat(int etat) {
		switch (etat) {
		case initiale: {
			buttonEnreg.setEnabled(true);
			buttonAnnuler.setEnabled(true);
			break;
		}
		}
	}
}
