

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.HashMap;

/**
 * Classe de gestion d'un exemplaire d'un ouvrage
 * @author IUT, refactoré par E. Ceret
 * @version 2.0
 *
 */
public class Parution extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _issn;
	private String _numerotation;
	private Periodique _periodique;
	private HashMap<Integer, Article> _articles;


	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************
	/**
	 * Constructeur. Crée un exemplaire d'ouvrage .
	 * 
	 * @param numero		numéro de l'exemplaire dans l'ouvrage.
	 * @param dateReception	date de réception de cet exemplaire.
	 * @param statut  		statut de l'exemplaire (en consultation, empruntable)
	 * @param ouvrage		ouvrage dont cet exemplaire est un représentant.
	 */
	public Parution(String issn, String numerotation, Periodique periodique) {
		this.setIssn(issn);
		this.setNumerotation(numerotation);
		this.setPeriodique(periodique);
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * @param numero le numéro à affecter
	 */
	private void setIssn(String issn) {
		_issn = issn;
	}

	/**
	 * @param numero le numéro à affecter
	 */
	private void setNumerotation(String numerotation) {
		_numerotation = numerotation;
	}
	
	/**
	 * @param ouvrage l'ouvrage lié à l'exemplaire
	 */
	private void setPeriodique(Periodique periodique) {
		_periodique = periodique;
	} // Fin setOuvrage

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * @return l'ouvrage lié à l'exemplaire
	 */
	public String getIssn() {
		return _issn;
	}
	
	/**
	 * @return l'ouvrage lié à l'exemplaire
	 */
	public Periodique getPeriodique() {
		return _periodique;
	}

	/**
	 * @return le numéro de l'exemplaire
	 */
	public String getNumerotation() {
		return _numerotation;
	}

} // Fin Classe Exemplaire