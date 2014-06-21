package tn.edu.esprit.info.jetsetmagasine.domain;

public class User {

	private int id_auto;
	private String login;
	private String password;
	private String nom_prenom;
	private String email;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int id_auto, String login, String password,
			String nom_prenom, String email) {
		super();
		this.id_auto = id_auto;
		this.login = login;
		this.password = password;
		this.nom_prenom = nom_prenom;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Utilisateur [id_auto=" + id_auto + ", login=" + login
				+ ", password=" + password + ", nom_prenom=" + nom_prenom
				+ ", email=" + email + "]";
	}

	public int getId_auto() {
		return id_auto;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		
}
