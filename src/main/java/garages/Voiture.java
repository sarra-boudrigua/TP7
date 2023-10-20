package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();
	private final Set<Garage> myGarages = new HashSet<>();
	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {

		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (this.estDansUnGarage() == true) {
			throw new UnsupportedOperationException("Voiture déjà dans un garage !");
		} else {
			Stationnement s = new Stationnement(this, g);
			myStationnements.add(s);
			myGarages.add(g);
		}
	}

	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		// TODO: Implémenter cette méthode
		if (this.estDansUnGarage() == false) {
			throw new UnsupportedOperationException("Voiture n'est pas dans un garage !");
		} else {
			int der = myStationnements.size() - 1;
			if (der >= 0) {
				if (myStationnements.get(der).estEnCours()) {
					myStationnements.get(der).terminer();
				}
			}
		}
		// Trouver le dernier stationnement de la voiture
		// Terminer ce stationnement
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		// TODO: Implémenter cette méthode
		return myGarages;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// TODO: Implémenter cette méthode

		// Vrai si le dernier stationnement est en cours
		int der = myStationnements.size() - 1;
		if (der >= 0) {
			if (myStationnements.get(der).estEnCours()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		// TODO: Implémenter cette méthode

		String impression = "";
		for(Garage g : this.garagesVisites()) {
			impression += "\n" + g.toString();
			for(Stationnement s : this.myStationnements) {
				if (s.getGarage() == g) {
					impression += "\n    " + s.toString();
					System.out.print(impression);
				}
			}
		}

		out.println(impression);
	}
}
