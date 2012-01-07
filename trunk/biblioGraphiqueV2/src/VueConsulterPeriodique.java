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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JScrollPane;
/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueConsulterPeriodique extends Vue {

	private JPanel contentPane;
	
	private JButton btnRechercher;
	private JButton btnAnnuler;
	private JButton btnFermer;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	volatile private List listParution;
	private List listPer;
	
	private HashMap<String, Periodique> listToutPeriodiques;
	private HashMap<String, Parution> listToutParutions;
	private ArrayList<String> tab;
	private Periodique _per;
	
	/**
	 * Create the frame.
	 */
	public VueConsulterPeriodique(Controleur controleur) {
		super(controleur);
		setTitle("Consultation d'un periodique");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Periodiques");
		lblNewLabel.setBounds(105, 26, 26, 15);
		contentPane.add(lblNewLabel);	
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 26, 125, 72);
		scrollPane.setHorizontalScrollBar(null);
		contentPane.add(scrollPane);
		
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
		scrollPane.setViewportView(listPer);
		
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listPer.getSelectedItem() != null) {
					String issn =  getControleur().getVueConsulterPeriodique().getIssnChoisi();
					_per = getControleur().getPeriodique(issn);
					_per.addObserver(VueConsulterPeriodique.this);
					VueConsulterPeriodique.this.alimente(_per);
					getControleur().getVueConsulterPeriodique().setEtat(finale);
				}
			}
		});
		btnRechercher.setBounds(275, 21, 107, 25);
		contentPane.add(btnRechercher);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueConsulterPeriodique.this);
			}
		});
		btnAnnuler.setBounds(275, 58, 107, 25);
		contentPane.add(btnAnnuler);		
		
		JLabel lblArticles = new JLabel("Parutions");
		lblArticles.setBounds(72, 164, 59, 15);
		contentPane.add(lblArticles);
		
		btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueConsulterPeriodique.this);
			}
		});
		btnFermer.setBounds(275, 248, 107, 25);
		contentPane.add(btnFermer);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(149, 164, 233, 72);
		contentPane.add(scrollPane_1);
		

		listParution = new List();
		scrollPane_1.setViewportView(listParution);
		
	}
	
	public void alimente(Periodique per) {
		if (per != null) {
			listToutParutions = per.getParutions();
			tab = new ArrayList<String>();
			//Iterator it= tab.iterator(); 
			for (int i = listToutParutions.size() - 1; i >= 0 ; i = i - 1) {
				tab.add(((Parution)listToutParutions.values().toArray()[i]).getId());
			}
			int num2 = 0;
			for (int i = 0; i < tab.size(); i = i + 1) {
				listParution.add(tab.get(i), num2);
				num2++;
			}
		} else {
		Message dialog = new Message("per null");
		dialog.setVisible(true);
		}
	}

	public String getIssnChoisi() {
		String issn;
		issn = tab.get(listPer.getSelectedIndex()+listPer.getSelectedIndex());
		return issn;
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			btnRechercher.setEnabled(true);
			btnAnnuler.setEnabled(true);
			btnFermer.setEnabled(false);
			break;
			}
		case finale: {
			btnRechercher.setEnabled(false);
			btnAnnuler.setEnabled(false);
			btnFermer.setEnabled(true);
			break;
			}
		}
	}
}
