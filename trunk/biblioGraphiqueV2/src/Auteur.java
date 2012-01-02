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
	private HashMap<Integer, Document> _documents;
	
	/**
	 * Constructeur. 
	 * @param nom			nom de l'auteur
	 * @param prenom		prenom de l'auteur
	 */
	public Auteur(String nom) {
		
		this.setNom(nom);
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
		return this.getNom();
	}
	
	public void setNom(String nom){
		_nom = nom;
	}
	
	public HashMap<Integer, Document> getDocuments() {
		return _documents;
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
	
}
