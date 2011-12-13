import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

public class Auteur extends Observable implements Serializable {


	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	private String _nom;
	private String _prenom;
	private HashMap<Integer, Document> _documents;
	
	/**
	 * Constructeur. 
	 * @param nom			nom de l'auteur
	 * @param prenom		prenom de l'auteur
	 */
	public Auteur(String nom, String prenom) {
		
		this.setNom(nom);
		this.setPrenom(prenom);
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
	public String toString() {
		return this.getNom() + "  " + this.getPrenom();
	}
	
	public void setNom(String nom){
		_nom = nom;
	}
	
	/**
	 * setter de l'attribut prenom
	 * @param prenom prenom de l'auteur
	 */
	public void setPrenom(String prenom){
		_prenom = prenom;
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
		
	/**
	 * Getter du nom
	 * @return nom de l'auteur
	 */
	public String getNom() {
		return _nom;
	}

	/**
	 * Getter du prenom
	 * @return prenom de l'auteur
	 */
	public String getPrenom() {
		return _prenom;
	}
	
}
