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
		this.setDocuments(new HashMap<Integer, Document>());
		this.setNom(nom);
	} // Fin Constructeur
	
	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	
	public String toString() {
		return this.getNom();
	}
	
	public void setNom(String nom){
		_nom = nom;
	}
	
	public HashMap<Integer, Document> getDocuments() {
		return _documents;
	}
	
	private void setDocuments(HashMap<Integer, Document> docs) {
		_documents = docs;
	}
	
	public void setDocument(Document doc) {
		int i = genererNumDocument();
		_documents.put(i, doc);
	}
	
	public int genererNumDocument() {
		if (this.getDocuments().isEmpty())
			return 0;
		else
			return (this.getDocuments().size());
	}

	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
		
	public String getNom() {
		return _nom;
	}
	
}
