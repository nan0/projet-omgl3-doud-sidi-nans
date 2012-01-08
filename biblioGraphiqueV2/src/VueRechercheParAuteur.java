import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Fenêtre de consultation d'un ouvrage
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */

public class VueRechercheParAuteur extends Vue {

	private JPanel contentPane;
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonAnnuler;
	private JButton buttonFermer;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private List listOuv;
	private List listArt;
	private List listAut;
	private Auteur _aut;
	
	private HashMap<Integer, Auteur> listToutAuteurs;
	private HashMap<Integer, Document> listToutDocuments;

	
	/**
	 * Create the frame.
	 */
	public VueRechercheParAuteur(Controleur controleur) {
		super(controleur);
		setTitle("Recherche Par Auteur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Auteurs");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(17, 66, 61, 15);
		contentPane.add(lblNewLabel);
		
		listToutAuteurs = getControleur().getAuteurs();
		ArrayList<String> tab = new ArrayList<String>();
		//Iterator it= tab.iterator(); 
		for (int i = 0; i < listToutAuteurs.size(); i = i + 1) {
			tab.add(Integer.toString(i));
			tab.add(((Auteur)listToutAuteurs.values().toArray()[i]).getNom());
		}
	
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(110, 66, 140, 100);
		contentPane.add(scrollPane1);
		
		listAut = new List();
		listAut.setFont(new Font("Dialog", Font.BOLD, 12));
		listAut.setForeground(Color.BLACK);
		listAut.setBackground(Color.WHITE);
		for (int i = 0; i < (tab.size() - 1); i = i + 2) {
			listAut.add(tab.get(i+1), Integer.parseInt(tab.get(i)));
		}
		listAut.setBounds(110, 66, 130, 90);
		scrollPane1.setViewportView(listAut);
		
		
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueRechercheParAuteur.this);}
		});
		buttonAnnuler.setBounds(255, 98, 107, 25);
		contentPane.add(buttonAnnuler);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listAut.getSelectedItem() != null) {
					String nom = listAut.getSelectedItem();
					Auteur a = new Auteur(nom);
					int num = getControleur().existeAuteur(a);
					if (num != -1) {
						_aut = getControleur().getAuteur(num);
						_aut.addObserver(VueRechercheParAuteur.this);
						VueRechercheParAuteur.this.alimente(_aut);
					}
				}
			}
		});
		buttonRech.setBounds(255, 61, 107, 25);
		contentPane.add(buttonRech);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueRechercheParAuteur.this);}
		});
		buttonFermer.setBounds(255, 415, 107, 25);
		contentPane.add(buttonFermer);
		
		JLabel lblOuvrages = new JLabel("Ouvrages");
		lblOuvrages.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOuvrages.setBounds(17, 178, 61, 15);
		contentPane.add(lblOuvrages);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 178, 220, 102);
		contentPane.add(scrollPane);
		
		listOuv = new List();
		scrollPane.setViewportView(listOuv);
		
		JLabel lblArticles = new JLabel("Articles");
		lblArticles.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArticles.setBounds(17, 290, 61, 15);
		contentPane.add(lblArticles);
	
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(112, 290, 220, 102);
		contentPane.add(scrollPane2);
		
		listArt = new List();
		scrollPane2.setViewportView(listArt);
	
	
	}

	 /* alimentation des composants d'affichage  */
	public void alimente(Auteur aut) {
		if (aut != null) {
			listOuv.removeAll();
			listArt.removeAll();
			listToutDocuments = aut.getDocuments();
			ArrayList<String> tab2 = new ArrayList<String>();
			ArrayList<String> tab3 = new ArrayList<String>();
			//Iterator it= tab.iterator(); 
			for (int i = listToutDocuments.size() - 1; i >= 0 ; i = i - 1) {
				String s = ((Document)listToutDocuments.values().toArray()[i]).getTitre();
				if (listToutDocuments.values().toArray()[i] instanceof Ouvrage)
					tab2.add(s);
				else
					tab3.add(s);
			}
			int num2 = 0, num3 = 0;
			for (int i = 0; i < tab2.size(); i = i + 1) {
				listOuv.add(tab2.get(i), num2);
				num2++;
			}
			for (int i = 0; i < tab3.size(); i = i + 1) {
				listArt.add(tab3.get(i), num3);
				num3++;
			}
		} else {
		Message dialog = new Message("aut null");
		dialog.setVisible(true);
		}
	}
}