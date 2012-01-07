import java.io.Serializable;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Observable;

public class Article extends Document implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _page;
	private Parution _par;
	
	public Article(Parution par, String page, String titre, HashMap<Integer, Auteur> auteurs) {
		super(titre);
		super.setAuteurs(auteurs);
		this.setParution(par);
		this.setPage(page);
	}
	
	private void setParution(Parution par) {
		_par = par;
	}
	
	private void setPage(String page) {
		_page = page;
	}
}

