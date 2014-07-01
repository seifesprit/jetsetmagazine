package tn.edu.esprit.info.jetsetmagasine.domain;

public class Category {

	private int id_auto;
	private String libelle;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int id_auto, String libelle) {
		super();
		this.id_auto = id_auto;
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Category [libelle=" + libelle + "]";
	}

	public int getId_auto() {
		return id_auto;
	}

	public void setId_auto(int id_auto) {
		this.id_auto = id_auto;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_auto;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Category))
			return false;
		Category other = (Category) obj;
		if (id_auto != other.id_auto)
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		return true;
	}
	
}
