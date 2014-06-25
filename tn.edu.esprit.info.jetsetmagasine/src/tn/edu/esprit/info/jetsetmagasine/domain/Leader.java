package tn.edu.esprit.info.jetsetmagasine.domain;

public class Leader extends User {
	
	public Leader() {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public int getId_auto() {
		// TODO Auto-generated method stub
		return super.getId_auto();
	}

	public Leader(int id_auto, String login, String password,
			String nom_prenom, String email) {
		super(id_auto, login, password, nom_prenom, email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getLogin() {
		// TODO Auto-generated method stub
		return super.getLogin();
	}

	@Override
	public void setLogin(String login) {
		// TODO Auto-generated method stub
		super.setLogin(login);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		super.setPassword(password);
	}

	@Override
	public String getNom_prenom() {
		// TODO Auto-generated method stub
		return super.getNom_prenom();
	}

	@Override
	public void setNom_prenom(String nom_prenom) {
		// TODO Auto-generated method stub
		super.setNom_prenom(nom_prenom);
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		super.setEmail(email);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	


}
