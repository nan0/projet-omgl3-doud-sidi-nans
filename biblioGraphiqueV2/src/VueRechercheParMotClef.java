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
import javax.swing.JScrollPane;
import java.awt.List;
import java.util.ArrayList;
/**
 * Fenêtre de consultation d'un ouvrage
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */

public class VueRechercheParMotClef extends Vue {

	private JPanel contentPane;
	private JTextField textFieldAuteur;
	private JTextField textFieldOuvrages;
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonAnnuler;
	private JButton buttonFermer;
	private JScrollPane scrollPane;
	private List list;
	
	
	/**
	 * Create the frame.
	 */
	public VueRechercheParMotClef(Controleur controleur) {
		super(controleur);
		setTitle("Recherche Par Mot clé");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mot clé");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(33, 66, 61, 15);
		contentPane.add(lblNewLabel);
		
		textFieldAuteur = new JTextField();
		textFieldAuteur.setBounds(112, 64, 114, 19);
		contentPane.add(textFieldAuteur);
		textFieldAuteur.setColumns(10);
		
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueRechercheParMotClef.this);}
		});
		buttonAnnuler.setBounds(255, 98, 107, 25);
		contentPane.add(buttonAnnuler);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().rechOuvrage(textFieldAuteur.getText());
				}
		});
		buttonRech.setBounds(255, 61, 107, 25);
		contentPane.add(buttonRech);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueRechercheParMotClef.this);}
		});
		buttonFermer.setBounds(255, 415, 107, 25);
		contentPane.add(buttonFermer);
		
		JLabel lblOuvrages = new JLabel("Documents");
		lblOuvrages.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOuvrages.setBounds(17, 178, 77, 15);
		contentPane.add(lblOuvrages);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 178, 220, 102);
		contentPane.add(scrollPane);
		
		list = new List();
		scrollPane.setViewportView(list);
	
	
	}

	 /* alimentation des composants d'affichage  */
	public void alimente(Auteur aut) {

		ArrayList<String> tab = new ArrayList<String>();
		for (int i = 0; i < aut.getDocuments().size(); i = i + 1) {
			tab.add(Integer.toString(i));
			tab.add((aut.getDocuments().values().toArray()[i].toString()));
		}
		for (int i = 0; i < (tab.size() - 1); i = i + 2) {
			list.add(tab.get(i+1), Integer.parseInt(tab.get(i)));
		}
	}
}