package tn.edu.esprit.info.jetsetmagasine.domain;

public class Leader extends User {
	private String category;
	
	public Leader() {
		
	}

	public Leader(int id_auto, String login, String password,
			String nom_prenom, String email) {
		super(id_auto, login, password, nom_prenom, email);
	}
}
