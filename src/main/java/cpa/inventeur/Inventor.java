package cpa.inventeur;

/**
 * @author HUANG Shenyuan
 * @date 2018-3-29
 */
public class Inventor {
	private String name;
	private boolean statut;
	private static final boolean FREE = true;
	private static final boolean BUSY = false;

	Inventor(String name) {
		this.name = name;
		statut = FREE;
	}

	void setFree() {
		this.statut = FREE;
	}

	void setBusy() {
		this.statut = BUSY;
	}
	
	boolean getStatut() {
		return statut;
	}
	
	@Override 
	public String toString() {
		return this.name;
	}
	
}
