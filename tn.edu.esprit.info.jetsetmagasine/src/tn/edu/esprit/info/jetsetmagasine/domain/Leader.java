package tn.edu.esprit.info.jetsetmagasine.domain;

public class Leader extends User {
	private Category category;
	
	public Leader() {
		
	}

	public Leader(int id_auto, String login, String password,
			String nom_prenom, String email, Category category) {
		super(id_auto, login, password, nom_prenom, email);
		this.category = category;
	}

	@Override
	public String toString() {
		return "Leader [ "+super.toString()+" category=" + category + "]";
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
