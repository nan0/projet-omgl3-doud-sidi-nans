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
	private JButton btnAjouter;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JLabel lblNom;
	private HashMap<Integer, String> _noms;
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

		this.setAuteurs(new HashMap<Integer, String>());
		
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
						_noms);
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
		//Iterator it= tab.iterator(); 
		for (int i = 0; i < listToutAuteurs.size(); i = i + 1) {
			tab.add(Integer.toString(i));
			tab.add(((Auteur)listToutAuteurs.values().toArray()[i]).getNom());
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
		for (int i = 0; i < (tab.size() - 1); i = i + 2) {
			list.add(tab.get(i+1), Integer.parseInt(tab.get(i)));
		}
		list.setBounds(170, 165, 150, 80);
		scrollPane.setViewportView(list);
			
		textFieldNom = new JTextField();
		textFieldNom.setBounds(170, 296, 114, 19);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = textFieldNom.getText();
				if (list.getSelectedItems() != null)
					existe = true;
				getControleur().ajouterAuteur(nom, existe);
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

		lblNom = new JLabel("Nom et prénom");
		lblNom.setBounds(99, 298, 61, 15);
		contentPane.add(lblNom);	

	}
	
	public void recupAuteurs() {
		int num;
		int i = 0;
		while (i < (list.getSelectedItems().length)) {
			num = genererNumAuteurCour();
			/*getControleur().setAuteur(auteur, num);*/
			_noms.put(num, list.getSelectedItems()[i]);
			Message dialog = new Message(list.getSelectedItems()[i]);
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
		textFieldNom.setText("");
	}
	
	/**
	 * @param periodiques hashtable de periodique à affecter
	 */
	public void setAuteurs(HashMap<Integer, String> noms) {
		_noms = noms;
	}// Fin setPeriodiques

	public void setEtat(int etat) {
		switch (etat) {
		case initiale: {
			buttonEnreg.setEnabled(true);
			btnAjouter.setEnabled(true);
			buttonAnnuler.setEnabled(true);
			break;
		}
		}
	}
}
