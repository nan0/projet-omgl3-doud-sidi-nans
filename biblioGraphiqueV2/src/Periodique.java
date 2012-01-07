import java.io.Serializable;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Observable;

/**
 * Classe de gestion de periodique.
 * @author IUT, refactoré par E. Ceret
 * @version 2.0
 */
public class Periodique extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _issn;
	private String _nom;
	private int _derNumParution;

	// Attributs d'Association
	private HashMap<String, Parution> _parutions;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 * @param issn			numero ISBN de l'ouvrage
	 * @param nom			titre de l'ouvrage
	 */
	public Periodique(String issn, String nom) {
		
		this.setIssn(issn);
		this.setNom(nom);
		this.setParutions(new HashMap<String, Parution>());
		this.setDerNumParution(0);
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * setter de l'attribut nom
	 * @param nom valeur à affecter à l'auteur du periodique
	 */
	public void setNom(String nom){
		_nom = nom;
	}
	
	/**
	 * setter de l'attrribut parutions (ensemble des parutions du periodique)
	 * @param parutions hashmap de parutions
	 */
	public void setParutions(HashMap<String, Parution> parutions) {
		_parutions = parutions;
	}
	
	/**
	 * setter de l'attribut num d'IS>N.
	 * @param issn valeur à affecter à l'ISSN de l'ouvrage
	 */
	public void setIssn(String issn) {
		this._issn = issn;
	}

	/**
	 * setter du num de la derniere parution.
	 * @param derNumParution nombre à affecter au dernier numéro de parution
	 */
	private void setDerNumParution(int derNumParution) {
		_derNumParution  = derNumParution ;
	}
	
	/**
	 * getter du dernier numéro d'exemplaire
	 * @return un entier représentant le dernier numéro d'exemplaire de l'ouvrage
	 */
	private int getDerNumParution() {
		return _derNumParution;
	}
	
	
	public void notifierObservateurs() {
		this.setChanged();
		this.notifyObservers();	
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Lie un exemplaire à l'ouvrage. Insère un exemplaire dans l'ensemble des exemplaires de l'ouvrage, avec son numero.
	 * @param numero numero de l'exemplaire à insérer
	 * @param exemplaire exemplaire à insérer
	 */
	public void setParution(String numerotation, Parution parution) {
		_parutions.put(numerotation, parution);	
	} // Fin setExemplaire
	
	


	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * Getter de l'ISSN.
	 * @return ISSN du periodique
	 */
	public String getIssn() {
		return _issn;
	}

	/**
	 * Getter du nom.
	 * @return nom du periodique
	 */
	public String getNom() {
		return _nom;
	}

	/**
	 * getter d'un exemplaire de l'ouvrage.
	 * @param numero numero de l'exemplaire retourné
	 * @return l'exemplaire dont le numéro a été passé en paramètre, s'il existe
	 */
	public Parution getParution(int numero) {
		return (Parution) _parutions.get(numero);
	} // Fin uneParution

	/**
	 * getter de l'ensemble des parutions.
	 * @return une collection de parutions
	 */
	public HashMap<String, Parution> getParutions() {
		return _parutions;
	}

	/**
	 * getter du nombre d'exemplaires de l'ouvrage.
	 * @return un entier représentant le nombre d'exemplaires
	 */
	public int getNbParutions() {
		return _parutions.size();
	}

	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Ajout d'un exemplaire à l'ouvrage. Le numéro de cet exemplaire est généré automatiquement pour l'ouvrage.
	 * @param dateReception date à laquelle l'exemplaire a été reçu.
	 * @return l'exemplaire ou null si la date de reception est antérieure à la date d'édition de l'ouvrage 
	 */
	public void ajouterParution(String numerotation, Parution par) {
			this.setParution(numerotation, par);
	} 

	/**
	 * @param dateRecep
	 * @return vrai si date réception de l'exemplaire est postérieure à la date édition de l'ouvrage
	 */
	
} // Fin Classe Ouvrage
