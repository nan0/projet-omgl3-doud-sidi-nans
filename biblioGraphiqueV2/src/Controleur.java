
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Observable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Classe controleur et application (système)
 * @author IUT,   A. Culet
 * @version 1.0 
 */

public class Controleur extends Observable implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * La classe Controleur est unique pour tous les cas d'utilisation
	 * Elle est également la classe "application" qui gère l'ensemble des objets de l'appli
	 */	
	// ************************************************************************************************************
		// Attributs
		// ************************************************************************************************************
	
		// Attributs d'Association
		// Ensemble des ouvrages de la bibliothèque
		private HashMap<String, Ouvrage> _ouvrages; 
		// Ensemble des periodiques de la bibliothèque
		private HashMap<String, Periodique> _periodiques; 
		// Ensemble des auteurs de la bibliothèque
		private HashMap<Integer, Auteur> _auteurs; 
		private HashMap<Integer, Auteur> _auteursCreer;
		
		// les différentes fenêtres pour chaque fonctionnalité
		private VueMenuBiblio _vueMenuBiblio = null;
		
		// une seule fenêtre est active à la fois; les autres sont à null.
		// permet de connaître l'état des fenêtres de l'interface
		private VueSaisieOuvrage _vueSaisieOuvrage = null;
		private VueSaisiePeriodique _vueSaisiePeriodique = null;
		private VueSaisieParution _vueSaisieParution = null;
		private VueSaisieExemplaire _vueSaisieExemplaire = null;
		private VueConsultOuvrage _vueConsultOuvrage = null;
		private VueConsulterPeriodique _vueConsulterPeriodique = null;
		private VueRechercheParAuteur _vueRechercheParAuteur = null;
		// ************************************************************************************************************
		// Constructeur
		// ************************************************************************************************************

		public Controleur() {
			this.setOuvrages(new HashMap<String, Ouvrage>());
			this.setPeriodiques(new HashMap<String, Periodique>());
			this.setAuteurs(new HashMap<Integer, Auteur>());
			this.setAuteursCreer(new HashMap<Integer, Auteur>());
		} // Fin Controleur

		// ************************************************************************************************************
		// Méthodes privées
		// ************************************************************************************************************

		// ------------------------------------------------------------------------------------------------------------
		// Affecteurs
		
		/**
		 * Ajoute un ouvrage à l'ensemble des ouvrages de la bibliothèque.
		 * @param ouvrage 	Ouvrage à ajouter
		 * @param isbn 	code ISBN de cet ouvrage
		 */
		private void setOuvrage(Ouvrage ouvrage, String isbn) {
			this.getOuvrages().put(isbn, ouvrage);
		} // Fin setOuvrage

		/**
		 * @param ouvrages hashtable d'ouvrages à affecter
		 */
		private void setOuvrages(HashMap<String, Ouvrage> ouvrages) {
			_ouvrages = ouvrages;
		}// Fin setOuvrages
		
		/**
		 * Ajoute un Periodique à l'ensemble des periodiques de la bibliothèque.
		 * @param periodique periodique à ajouter
		 * @param issn 	code ISSN de cet ouvrage
		 */
		private void setPeriodique(Periodique periodique, String issn) {
			this.getPeriodiques().put(issn, periodique);
		} // Fin setPeriodique

		/**
		 * @param periodiques hashtable de periodique à affecter
		 */
		private void setPeriodiques(HashMap<String, Periodique> periodiques) {
			_periodiques = periodiques;
		}// Fin setPeriodiques
		
		/**
		 * Ajoute un Auteur à l'ensemble des auteurs de la bibliothèque.
		 * @param auteur auteur à ajouter
		 * @param numero
		 */
		public void setAuteur(Auteur auteur, int numero) {
			this.getAuteurs().put(numero, auteur);
		} // Fin setAuteur

		/**
		 * @param auteurs hashtable d'auteur à affecter
		 */
		public void setAuteurs(HashMap<Integer, Auteur> auteurs) {
			_auteurs = auteurs;
		}// Fin setAuteurs
		
		public void setAuteursCreer(HashMap<Integer, Auteur> auteursCreer) {
			_auteursCreer = auteursCreer;
		}// Fin setAuteursCreer
		
		/**
		 * @param vue  la vue à affecter
		 */
		private void setVueMenuBiblio(VueMenuBiblio vue) {
			_vueMenuBiblio = vue;
		}// Fin getVueVueMenuBiblio
		
		private void setVueSaisieOuvrage(VueSaisieOuvrage vue) {
			_vueSaisieOuvrage = vue;
		}// Fin setVueVueSaisieOuvrage
		
		private void setVueSaisiePeriodique(VueSaisiePeriodique vue) {
			_vueSaisiePeriodique = vue;
		}// Fin setVueVueSaisiePeriodique
		
		private void setVueSaisieExemplaire(VueSaisieExemplaire vue) {
			_vueSaisieExemplaire = vue;
		}// Fin setVueVueSaisieExemplaire
		
		private void setVueSaisieParution(VueSaisieParution vue) {
			_vueSaisieParution = vue;
		}// Fin setVueVueSaisieParution
		
		private void setVueConsultOuvrage(VueConsultOuvrage vue) {
			_vueConsultOuvrage = vue;
		}// Fin setVueVueConsultOuvrage
		
		private void setVueConsulterPeriodique(VueConsulterPeriodique vue) {
			_vueConsulterPeriodique = vue;
		}// Fin setVueVueConsultPeriodique
		
		private void setVueRechercheParAuteur(VueRechercheParAuteur vue) {
			_vueRechercheParAuteur = vue;
		}// Fin setVueRechercheParAuteur
		// ------------------------------------------------------------------------------------------------------------
		// Accesseurs
		
		/**
		 * @return ensemble des ouvrages de la bibliothèque
		 */
		private HashMap<String, Ouvrage> getOuvrages() {
			return _ouvrages;
		}// Fin getOuvrages

		/**
		 * Accès à un ouvrage par son numéro ISBN
		 * @param isbn 	le code ISBN de l'ouvrage cherché
		 * @return l'ouvrage qui a l'ISBN indiqué
		 */
		private Ouvrage getOuvrage(String isbn) {
			return this.getOuvrages().get(isbn);
		} // Fin getOuvrage

		public HashMap<String, Periodique> getPeriodiques() {
			return _periodiques;
		}// Fin getPeriodiques
		
		/**
		 * Accès à un Periodique par son numéro ISSN
		 * @param issn 	le code ISSN du periodique cherché
		 * @return le periodique qui a l'ISSN indiqué
		 */
		public Periodique getPeriodique(String issn) {
			return this.getPeriodiques().get(issn);
		} // Fin getPeriodique
		
		public HashMap<Integer, Auteur> getAuteurs() {
			return _auteurs;
		}// Fin getAuteurs
		
		public Auteur getAuteur(int key) {
			return _auteurs.get(key);
		}// Fin getAuteur
		
		public HashMap<Integer, Auteur> getAuteursCreer() {
			return _auteursCreer;
		}// Fin getAuteursCreer
		
		public int existeAuteur(Auteur a) {
			if (!(this.getAuteurs().isEmpty())) {
				for (int i = 0; i < this.getAuteurs().size(); i++) {
					if (a.getNom().compareTo(this.getAuteurs().get(i).getNom()) == 0)
						return i;
				}
			} 
			return -1;
		} // Fin existeAuteur
		
		/**
		 * @return la vue  
		 */
		private VueMenuBiblio getVueMenuBiblio() {
			return _vueMenuBiblio ;
		}// Fin getVueVueMenuBiblio
		
		private VueSaisieOuvrage getVueSaisieOuvrage() {
			return _vueSaisieOuvrage ;
		}// Fin getVueVueSaisieOuvrage
		
		private VueSaisiePeriodique getVueSaisiePeriodique() {
			return _vueSaisiePeriodique ;
		}// Fin getVueVueSaisiePeriodique
		
		private VueSaisieExemplaire getVueSaisieExemplaire() {
			return _vueSaisieExemplaire ;
		}// Fin getVueVueSaisieExemplaire
		
		public VueSaisieParution getVueSaisieParution() {
			return _vueSaisieParution ;
		}// Fin getVueVueSaisieParution
		
		private VueConsultOuvrage getVueConsultOuvrage() {
			return _vueConsultOuvrage ;
		}// Fin getVueVueConsultOuvrage
		
		public VueConsulterPeriodique getVueConsulterPeriodique() {
			return _vueConsulterPeriodique ;
		}// Fin getVueVueConsulterPeriodique
		
		private VueRechercheParAuteur getVueRechercheParAuteur() {
			return _vueRechercheParAuteur ;
		}// Fin  getVueRechercheParAuteur
		
		public int genererNumAuteur() {
			if (this.getAuteurs().isEmpty())
				return 0;
			else
				return (this.getAuteurs().size());
		}
		
		public int genererNumAuteurCreer() {
			if (this.getAuteursCreer().isEmpty())
				return 0;
			else
				return (this.getAuteursCreer().size());
		}
		
		// ************************************************************************************************************
		// Méthodes publiques de création et affichage des fenêtres de l'application et fermeture
		// ************************************************************************************************************
		/**
		 * Création et affichage de la fenêtre principale de l'application. 
		 * Elle reste affichée
		 */
		
		public void menuBiblio() {

			try {this.setVueMenuBiblio(new VueMenuBiblio(this));	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * Création et affichage de la fenêtre de consultation d'un ouvrage
		 */
		
		public void consulterOuvrage() {
			try {this.setVueConsultOuvrage (new VueConsultOuvrage(this));
			// le Menu est caché
			this.getVueMenuBiblio().getFrame().setVisible(false); 	
			// la vue courante est VueConsultOuvrage
				this.getVueConsultOuvrage().setEtat(Vue.initiale);
				this.getVueConsultOuvrage().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void consulterPeriodique() {
			try {this.setVueConsulterPeriodique (new VueConsulterPeriodique(this));
			// le Menu est caché
			this.getVueMenuBiblio().getFrame().setVisible(false); 	
			// la vue courante est VueConsultOuvrage
				this.getVueConsulterPeriodique().setEtat(Vue.initiale);
				this.getVueConsulterPeriodique().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Création et affichage de la fenêtre de saisie d'un exemplaire d'ouvrage
		 */
		public void saisirExemplaire() {
			try {this.setVueSaisieExemplaire(new VueSaisieExemplaire(this));
			// le Menu est caché
			this.getVueMenuBiblio().getFrame().setVisible(false); 
			// la vue courante est VueSaisieExemplaire
				this.getVueSaisieExemplaire().setEtat(Vue.initiale);
				this.getVueSaisieExemplaire().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * Création et affichage de la fenêtre de saisie d'un ouvrage
		 */
		public void saisirOuvrage() {
			try {this.setVueSaisieOuvrage(new VueSaisieOuvrage(this));
			// le Menu est caché
			this.getVueMenuBiblio().getFrame().setVisible(false); 
			// la vue courante est VueSaisieOuvrage
				this.getVueSaisieOuvrage().setEtat(Vue.initiale);
				this.getVueSaisieOuvrage().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		/**
		 * Création et affichage de la fenêtre de saisie d'un periodique
		 */
		public void saisirPeriodique() {
			try {this.setVueSaisiePeriodique(new VueSaisiePeriodique(this));
			// le Menu est caché
			this.getVueMenuBiblio().getFrame().setVisible(false); 
			// la vue courante est VueSaisiePeridique
				this.getVueSaisiePeriodique().setEtat(Vue.initiale);
				this.getVueSaisiePeriodique().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		/**
		 * Création et affichage de la fenêtre de saisie d'une parution
		 */
		public void saisirParution() {
			try {this.setVueSaisieParution(new VueSaisieParution(this));
			// le Menu est caché
			this.getVueMenuBiblio().getFrame().setVisible(false); 
			// la vue courante est VueSaisiePeridique
				this.getVueSaisieParution().setEtat(Vue.initiale);
				this.getVueSaisieParution().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		
		public void rechercheParAuteur() {
			try {this.setVueRechercheParAuteur(new VueRechercheParAuteur(this));
			// le Menu est caché
			this.getVueMenuBiblio().getFrame().setVisible(false); 	
			// la vue courante est VueConsultOuvrage
				this.getVueRechercheParAuteur().setEtat(Vue.initiale);
				this.getVueRechercheParAuteur().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			
			}
		}
		/**
		 * fermeture de la fenêtre vue
		 * lors de la fermeture de la fenêtre principale de l'application sauvegarde des objets sérialisés 
		 */
		public void fermerVue (Vue vue) {
			if (vue instanceof VueMenuBiblio ) {	
			// Quitte l'aplication. Sauvegarde les objets du modèle
				this.sauve();
				System.exit(0);
				}
			vue.dispose();
			// le Menu est rendu de nouveau visible
			this.getVueMenuBiblio().getFrame().setVisible(true);
			this.resetVues();
		}
		
		// Restaure l'état de l'interface avec seule la fenêtre du Menu principal active
		private void resetVues() {
			this.setVueSaisieOuvrage (null);
			this.setVueConsultOuvrage(null);
			this.setVueSaisieExemplaire(null);
		}
		
		// ************************************************************************************************************
		// Opérations liées à la sérialisation des objets de l'application
		// ************************************************************************************************************
		/**
		 *  restauration des objets de l'application
		 */
		public Controleur restaure() {
			try {
				FileInputStream fichier = new FileInputStream("Fsauv.ser");
				ObjectInputStream in = new ObjectInputStream(fichier);
				return((Controleur) in.readObject());
			} catch (Exception e) {
				Message dialog = new Message("Pbs de Restauration ou fichier non encore créé");
				dialog.setVisible(true);
				return this;
			} 
		}
		/**
		 *  sauvegarde des objets de l'application
		 */
		private void sauve() {
			try {
				FileOutputStream f = new FileOutputStream("Fsauv.ser");
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(this);
			} catch (Exception e) {
				Message dialog = new Message("Pb de Sauvegarde dans le fichier");
				dialog.setVisible(true);
			}
		}
		// ************************************************************************************************************
		// Opérations liées à l'application en réponse à une action de l'utilisateur dans une vue
		// ************************************************************************************************************

		/**
		 * Accès à un ouvrage par son numéro ISBN
		 * Invoqué dans VueConsultOuvrage et VueSaisieExemplaire
		 * @param isbn 	le code ISBN de l'ouvrage cherché
		 * @return l'ouvrage qui a l'ISBN indiqué ou null
		 * affiche un message d'erreur si l'ouvrage n'est pas trouvé
		 */
		public Ouvrage rechOuvrage(String isbn) {
			Ouvrage ouv = this.getOuvrage(isbn);
			if (ouv == null) {
				Message dialog = new Message("Ouvrage inconnu");
				dialog.setVisible(true);
			}
			else {
				// la vue courante est VueSaisieExemplaire
				if (this.getVueSaisieExemplaire() != null) {
				// la vue est inscrite comme observatrice de l'ouvrage	
					ouv.addObserver(this.getVueSaisieExemplaire());
				// le contrôleur modifie l'état de la vue
					this.getVueSaisieExemplaire().setEtat(Vue.inter1);
					this.getVueSaisieExemplaire().alimente(ouv);
					}
				// la vue courante est VueConsultOuvrage
				if (this.getVueConsultOuvrage() != null) {
					// le contrôleur modifie l'état de la vue
					this.getVueConsultOuvrage().setEtat(Vue.finale);
					this.getVueConsultOuvrage().alimente(ouv);
					}
			}
			return ouv;
		} // Fin rechOuvrage
	
		public void ajouterAuteur(String nom, boolean existeListe) {
			/* listAuteur.getSelectedValue(); */
			if (nom.length() != 0) {
				int num = this.getVueSaisieOuvrage().genererNumAuteurCour();
				this.getVueSaisieOuvrage().getAuteursCour().put(num, nom);
				String mess = nom + " à été ajouté";
				Message dialog = new Message(mess);
				dialog.setVisible(true);
				this.getVueSaisieOuvrage().reinitChampAuteur();
			} else if (existeListe) {
				this.getVueSaisieOuvrage().recupAuteurs();
			}
		}
		
		public void ajouterAuteurArticle(String nom, boolean existeListe) {
			/* listAuteur.getSelectedValue(); */
			if (nom.length() != 0) {
				int num = this.getVueSaisieParution().genererNumAuteurCour();
				this.getVueSaisieParution().getAuteursCour().put(num, nom);
				String mess = nom + " à été ajouté";
				Message dialog = new Message(mess);
				dialog.setVisible(true);
				this.getVueSaisieParution().reinitChampAuteur();
			} else if (existeListe) {
				this.getVueSaisieParution().recupAuteurs();
			}
		}
		
		public void nouvArticle(Parution par, String page, String titre, HashMap<Integer, String> noms) {
			int numC, num;
			this.setAuteursCreer(new HashMap<Integer, Auteur>());
			Auteur auteur;
			for (int i = 0; i < noms.size(); i++) {
				numC = this.genererNumAuteurCreer();
				auteur = new Auteur((noms.values().toArray())[i].toString());
				numC = this.existeAuteur(auteur);
				if (numC == -1) {
					//this.getVueSaisieOuvrage().getAuteursCour().put(num, ((noms.values().toArray())[i].toString()));
					num = this.genererNumAuteur();
					_auteursCreer.put(num, auteur);
					_auteurs.put(num, auteur);
				} else {
					_auteursCreer.put(numC, auteur);
				}
			}
		// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
			int no = par.genererNumArticle();
			Article art = new Article(par, page, titre, _auteursCreer);
			for (int mapKey : _auteursCreer.keySet()) {
				int no2 = existeAuteur(_auteursCreer.get(mapKey));
				_auteurs.get(no2).setDocument(art);
			}
			par.setArticle(no, art);
			Message dialog = new Message("Article enregistré");
			dialog.setVisible(true);
			this.getVueSaisieParution().setNoms(new HashMap<Integer, String>());
		}
		
		/**
		 * Création d'un exemplaire d'ouvrage 
		 * Invoqué dans VueSaisieExemplaire
		 * @param ouv l'ouvrage  dateRecep la date de réception de l'exemplaire	
		 * affiche un message de confirmation après l'enregistrement ou un message d'erreur 
		 */
		public void nouvExemplaire(Ouvrage ouv, String dateReception, String statut) {
			// vérification de la présence de la date et de son format
			if (dateReception.length() == 0 ){
					Message dialog = new Message("La date de réception est obligatoire");
					dialog.setVisible(true);
					}
			else {
				GregorianCalendar date = ESDate.lireDate (dateReception);
				if (date == null) {
					Message dialog = new Message("Le format de la date est incorrect");
					dialog.setVisible(true);
					}
				else {
					int statutEx;
					if (statut == "empruntable") {
						statutEx = Exemplaire.EMPRUNTABLE ; }
					else {
						statutEx = Exemplaire.EN_CONSULTATION ; }
			// demande d'ajout de l'exemplaire
					Exemplaire exemplaire = ouv.ajouterExemplaire(date, statutEx);
			// l'opération s'est bien passée
					if (exemplaire != null) {
			// le contrôleur modifie l'état de la vue
						this.getVueSaisieExemplaire().setEtat(Vue.finale);
			// affichage d'un message de confirmation
						Message dialog = new Message("Exemplaire enregistré");
						dialog.setVisible(true);
					}
					else {
						Message dialog = new Message("Date de Reception incorrecte / à la date d'Edition.");
						dialog.setVisible(true);
					}
				}
			}
		} // Fin nouvExemplaire
		
		/**
		 * Création d'un ouvrage 
		 * Invoqué dans VueSaisieOuvrage
		 * @param  dateEdition la date d'édition de l'ouvrage
		 * affiche un message de confirmation après l'enregistrement ou un message d'erreur 
		 */
		public void nouvOuvrage(String isbn, String titre, String editeur, String dateEdition, HashMap<Integer, String> noms) {
			// vérification de la présence des infos obligatoires et du format de la date
			if ((isbn.length() == 0) || (titre.length() == 0) || (noms.isEmpty()) 
					|| (editeur.length() == 0 )|| (dateEdition.length() == 0 )){
					Message dialog = new Message("Tous les champs sont obligatoires");
					dialog.setVisible(true);
					}
			else {
				GregorianCalendar date = ESDate.lireDate (dateEdition);
				if (date == null) {
					Message dialog = new Message("Le format de la date est incorrect");
					dialog.setVisible(true);
					}
				else if (this.getOuvrage(isbn)== null) {
				// Instanciation de l'ouvrage
					int numC, num;
					this.setAuteursCreer(new HashMap<Integer, Auteur>());
					Auteur auteur;
					for (int i = 0; i < noms.size(); i++) {
						auteur = new Auteur((noms.values().toArray())[i].toString());
						numC = this.existeAuteur(auteur);
						if (numC == -1) {
							//this.getVueSaisieOuvrage().getAuteursCour().put(num, ((noms.values().toArray())[i].toString()));
							num = this.genererNumAuteur();
							_auteursCreer.put(num, auteur);
							_auteurs.put(num, auteur);
						} else {
							_auteursCreer.put(numC, auteur);
						}
					}
					Ouvrage ouvrage = new Ouvrage(isbn, titre, editeur, date, _auteursCreer);
				// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
					for (int mapKey : _auteursCreer.keySet()) {
						int no = existeAuteur(_auteursCreer.get(mapKey));
						_auteurs.get(no).setDocument(ouvrage);
					}
					this.setOuvrage(ouvrage, isbn);
					Message dialog = new Message("Ouvrage enregistré");
					dialog.setVisible(true);
					this.getVueSaisieOuvrage().setNoms(new HashMap<Integer, String>());
					this.fermerVue (this.getVueSaisieOuvrage());
					} 
				else {
						Message dialog = new Message("Ouvrage déjà présent");
						dialog.setVisible(true);
				}
			}
		}// Fin nouvOuvrage
		
		/**
		 * Création d'un Periodique
		 * Invoqué dans VueSaisiePeriodique
		 * affiche un message de confirmation après l'enregistrement ou un message d'erreur 
		 */
		public void nouvPeriodique(String issn, String nom) {
			// vérification de la présence des infos obligatoires
			if ((issn.length() == 0) || (nom.length() == 0)){
					Message dialog = new Message("Tous les champs sont obligatoires");
					dialog.setVisible(true);
					}
			else {
				if (this.getPeriodique(issn)== null) {
				// Instanciation de l'ouvrage
					Periodique periodique = new Periodique(issn,nom);
				// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
					this.setPeriodique(periodique, issn);
					
					Message dialog = new Message("Periodique enregistré");
					dialog.setVisible(true);
					this.fermerVue (this.getVueSaisiePeriodique());
					} 
				else {
						Message dialog = new Message("Periodique déjà présent");
						dialog.setVisible(true);
				}
			}
		}// Fin nouvPeriodique
}
		

