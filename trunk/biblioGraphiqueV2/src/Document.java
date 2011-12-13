import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

public class Document extends Observable implements Serializable {


	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	
	private String _titre;
	private HashMap<Integer, Auteur> _auteurs;
	
	/**
	 * Constructeur. 
	 * @param nom			nom de l'auteur
	 * @param prenom		prenom de l'auteur
	 */
	public Document(String titre) {
		
		this.setTitre(titre);
	} // Fin Constructeur
	
	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * setter de l'attribut nom
	 * @param nom nom de l'auteur
	 */
	public void setTitre(String titre){
		_titre = titre;
	}
	
	public void ajouterAuteurs(HashMap<Integer, Auteur> auteurs) {
		_auteurs = auteurs;
	}
	
	/**
	 * Lie un exemplaire à l'ouvrage. Insère un exemplaire dans l'ensemble des exemplaires de l'ouvrage, avec son numero.
	 * @param numero numero de l'exemplaire à insérer
	 * @param exemplaire exemplaire à insérer
	 */
	private void ajouterAuteur(int numero, Auteur auteur) {
		_auteurs.put(numero, auteur);	
	} // Fin setExemplaire
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
		
	/**
	 * Getter du nom
	 * @return nom de l'auteur
	 */
	public String getTitre() {
		return _titre;
	}	
	
	/**
	 * getter d'un exemplaire de l'ouvrage.
	 * @param numero numero de l'exemplaire retourné
	 * @return l'exemplaire dont le numéro a été passé en paramètre, s'il existe
	 */
	public Auteur getAuteur(int numero) {
		return (Auteur) _auteurs.get(numero);
	} // Fin unExemplaire

	/**
	 * getter de l'ensemble des exemplaires.
	 * @return une collection d'exemplaires
	 */
	public Collection<Auteur> getAuteurs() {
		return _auteurs.values();
	}
}
