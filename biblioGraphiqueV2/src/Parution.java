

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

	private String _id;
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
	 * @param ouvrage							_per.notifierObservateurs();
ouvrage dont cet exemplaire est un représentant.
	 */
	public Parution(Periodique periodique, String id) {
		this.setId(id);
		this.setPeriodique(periodique);
		this.setArticles(new HashMap<Integer, Article>());
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * @param ouvrage l'ouvrage lié à l'exemplaire
	 */
	private void setPeriodique(Periodique periodique) {
		_periodique = periodique;
	} // Fin setOuvrage
	
	private void setId(String id) {
		_id = id;
	}
	
	private void setArticles(HashMap<Integer, Article> articles) {
		_articles = articles;
	}

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * @return l'ouvrage lié à l'exemplaire
	 */
	public Periodique getPeriodique() {
		return _periodique;
	}
	
	public String getId() {
		return _id;
	}
	
	public HashMap<Integer, Article> getArticles() {
		return _articles;
	}
	
	public int genererNumArticle() {
		if (this.getArticles().isEmpty())
			return 0;
		else
			return (this.getArticles().size());
	}
	
	public void ajouterArticle(String page, String titre, HashMap<Integer, Auteur> auteurs) {
		int num = genererNumArticle();
		Article art = new Article(this, page, titre, auteurs);
		_articles.put(num, art);
	}

} // Fin Classe Exemplaire