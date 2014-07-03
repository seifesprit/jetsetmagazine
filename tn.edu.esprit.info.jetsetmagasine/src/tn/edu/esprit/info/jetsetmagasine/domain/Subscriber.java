package tn.edu.esprit.info.jetsetmagasine.domain;

public class Subscriber {
	
	private int id_auto;
	private String nom_prenom;
	private String email;
	private String id_fb ;
	public Subscriber() {
		// TODO Auto-generated constructor stub
	}

	public Subscriber(int id_auto, String nom_prenom, String email) {
		super();
		this.id_auto = id_auto;
		this.nom_prenom = nom_prenom;
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id_auto;
		result = prime * result
				+ ((nom_prenom == null) ? 0 : nom_prenom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Subscriber))
			return false;
		Subscriber other = (Subscriber) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id_auto != other.id_auto)
			return false;
		if (nom_prenom == null) {
			if (other.nom_prenom != null)
				return false;
		} else if (!nom_prenom.equals(other.nom_prenom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscriber [id_auto=" + id_auto + ", nom_prenom=" + nom_prenom
				+ ", email=" + email + "]";
	}

	public int getId_auto() {
		return id_auto;
	}

	public void setId_auto(int id_auto) {
		this.id_auto = id_auto;
	}

	public String getNom_prenom() {
		return nom_prenom;
	}

	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getId_fb() {
		return id_fb;
	}

	public void setId_fb(String id_fb) {
		this.id_fb = id_fb;
	}
	
	

}
