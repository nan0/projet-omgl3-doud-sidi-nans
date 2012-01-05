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
import javax.swing.JSeparator;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.List;

/**
 * Fenêtre de saisie d'un exemplaire d'un ouvrage avec la vue des exemplaires de l'ouvrage
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueSaisieParution extends Vue {

	private JPanel contentPane;
	private JTextField textFieldIssn;
	private JTextField textFieldTitre;
	private JTextField textFieldIdentification;
	private JTextField textFieldNbExemplaires;
	private JTextArea textAreaInfosExemplaires;
	private List list;
	private HashMap<String, Periodique> listToutPeriodiques;
	private JScrollPane scrollPane;
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonEnreg;
	private JButton buttonAnnulerEnreg;
	private JButton buttonFermer;
	
	// à ajouter car la vue est observatrice d'un ouvrage
	private Ouvrage _ouvrage ;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton RadioButtonConsultable;
	private JButton buttonAnnulerRech;
	
	/**
	 * Create the frame.
	 */
	public VueSaisieParution(Controleur controleur) {
		super (controleur);
		setTitle("Enregistrement d'une nouvelle parution de periodique");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 540, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listToutPeriodiques = getControleur().getPeriodiques();
		ArrayList<String> tab = new ArrayList<String>();
		//Iterator it= tab.iterator(); 
		for (int i = 0; i < listToutPeriodiques.size(); i = i + 1) {
			tab.add(Integer.toString(i));
			tab.add(((Periodique)listToutPeriodiques.values().toArray()[i]).getNom());
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
		list.setBounds(20, 165, 150, 80);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("Issn");
		lblNewLabel.setBounds(145, 31, 43, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Identifiant parution");
		lblNewLabel_1.setBounds(12, 115, 168, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(190, 30, 159, 19);
		contentPane.add(textFieldIssn);
		textFieldIssn.setColumns(10);
		
		textFieldIdentification = new JTextField();
		textFieldIdentification.setText("");
		textFieldIdentification.setBounds(189, 114, 114, 19);
		contentPane.add(textFieldIdentification);
		textFieldIdentification.setColumns(10);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = textFieldIsbn.getText();
				// liaison de la vue avec l'objet observé
				setOuvrage (getControleur().rechOuvrage(isbn));
		}});
		buttonRech.setBounds(357, 12, 107, 25);
		contentPane.add(buttonRech);
		
		buttonAnnulerEnreg = new JButton("Annuler");
		buttonAnnulerEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieParution.this);}
		});
		buttonAnnulerEnreg.setBounds(357, 167, 107, 25);
		contentPane.add(buttonAnnulerEnreg);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateReception = textFieldDateReception.getText();
				String statut;
				if (RadioButtonConsultable.isSelected()) {
					statut = "consultable";}
				else {
					statut = "empruntable";}
				getControleur().nouvExemplaire(getOuvrage(), dateReception, statut);
				}
		});
		buttonEnreg.setBounds(357, 130, 107, 25);
		contentPane.add(buttonEnreg);
		
		JLabel lblNewLabel_2 = new JLabel("Titre Parution");
		lblNewLabel_2.setBounds(91, 85, 97, 15);
		contentPane.add(lblNewLabel_2);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(189, 83, 247, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		JLabel lblExemplaires = new JLabel("Exemplaires");
		lblExemplaires.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblExemplaires.setBounds(206, 237, 97, 15);
		contentPane.add(lblExemplaires);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(74, 223, 300, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre d'exemplaires");
		lblNewLabel_3.setBounds(21, 266, 150, 15);
		contentPane.add(lblNewLabel_3);
		
		textFieldNbExemplaires = new JTextField();
		textFieldNbExemplaires.setEditable(false);
		textFieldNbExemplaires.setBounds(179, 264, 49, 19);
		contentPane.add(textFieldNbExemplaires);
		textFieldNbExemplaires.setColumns(10);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueSaisieParution.this);
			}
		});
		buttonFermer.setBounds(169, 399, 107, 25);
		contentPane.add(buttonFermer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 291, 399, 96);
		contentPane.add(scrollPane);
		
		textAreaInfosExemplaires = new JTextArea();
		textAreaInfosExemplaires.setEditable(false);
		scrollPane.setColumnHeaderView(textAreaInfosExemplaires);
		
		JLabel lblNewLabel_4 = new JLabel("Statut");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(117, 172, 61, 15);
		contentPane.add(lblNewLabel_4);
		
		RadioButtonConsultable = new JRadioButton("Consultable");
		buttonGroup.add(RadioButtonConsultable);
		RadioButtonConsultable.setBounds(190, 164, 134, 23);
		contentPane.add(RadioButtonConsultable);
		
		JRadioButton RadioButtonEmpruntable = new JRadioButton("Empruntable");
		buttonGroup.add(RadioButtonEmpruntable);
		RadioButtonEmpruntable.setSelected(true);
		RadioButtonEmpruntable.setBounds(190, 191, 134, 23);
		contentPane.add(RadioButtonEmpruntable);
		
		buttonAnnulerRech = new JButton("Annuler");
		buttonAnnulerRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieParution.this);}
		});
		buttonAnnulerRech.setBounds(357, 46, 107, 25);
		contentPane.add(buttonAnnulerRech);
	}
	
		private Ouvrage getOuvrage() {
			return _ouvrage;
		}
		private void setOuvrage(Ouvrage ouvrage) {
			 _ouvrage = ouvrage;
		}
		
		/**
		 * alimentation des composants d'affichage en consultant directement l'ouvrage
		 */
		public void alimente(Ouvrage ouv) {
			textFieldTitre.setText(ouv.getTitre());
			textFieldNbExemplaires.setText (String.valueOf(ouv.getNbExemplaires()));
			textAreaInfosExemplaires.setText("");
			for (Exemplaire exemplaire : ouv.getExemplaires()) {
				  textAreaInfosExemplaires.append( "numéro " + String.valueOf(exemplaire.getNumero()) 
				+ " reçu le " + ESDate.ecrireDate(exemplaire.getDateReception()) + " : " + exemplaire.libStatut() + "\n");
			}
		}
		/**
		 * définition des états de la fenêtre
		 */
		public void setEtat (int etat){
			switch (etat) {
			case initiale: {
				buttonRech.setEnabled(true);
				buttonEnreg.setEnabled(false);
				buttonAnnulerEnreg.setEnabled(false);
				buttonFermer.setEnabled(false);
				break;
				}
			case inter1: {
				buttonRech.setEnabled(false);
				buttonAnnulerRech.setEnabled(false);
				buttonEnreg.setEnabled(true);
				buttonAnnulerEnreg.setEnabled(true);
				buttonFermer.setEnabled(false);
				textFieldIssn.setEditable(false);
				break;
				}
			case finale: {
				buttonRech.setEnabled(false);
				buttonEnreg.setEnabled(false);
				buttonAnnulerEnreg.setEnabled(false);
				buttonFermer.setEnabled(true);
				break;
				}
			}
		}
		/**
		 * méthode exécutée automatiquement lorsque l'ouvrage observé notifie un changement
		 */
		public void update(Observable observable, Object objet) {
			// maj de la vue lorque l'ouvrage a été modifié
			this.alimente(this.getOuvrage());
			super.update(observable,  objet);
		} // Fin upDate	
}