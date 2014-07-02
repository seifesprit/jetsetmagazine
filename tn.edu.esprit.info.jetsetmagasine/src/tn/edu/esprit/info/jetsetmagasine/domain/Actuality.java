package tn.edu.esprit.info.jetsetmagasine.domain;

import java.util.Date;

public class Actuality {
	private int id_auto;
	private String titre;
	private String description;
	private String type;
	private Date date_ajout;
	private Date date_redaction;
	private boolean valide;
	private String image;
	private String source ;
	private Leader leader;
	private Category category;
	
	public Actuality() {
		// TODO Auto-generated constructor stub
	}

	public Actuality(int id_auto, String titre, String description,
			String type, Date date_ajout, Date date_redaction, boolean valide,
			String image, Leader leader, Category category,String source) {
		super();
		this.id_auto = id_auto;
		this.titre = titre;
		this.description = description;
		this.type = type;
		this.date_ajout = date_ajout;
		this.date_redaction = date_redaction;
		this.valide = valide;
		this.image = image;
		this.leader = leader;
		this.category = category;
		this.source = source;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((date_ajout == null) ? 0 : date_ajout.hashCode());
		result = prime * result
				+ ((date_redaction == null) ? 0 : date_redaction.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + id_auto;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((leader == null) ? 0 : leader.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (valide ? 1231 : 1237);
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Actuality))
			return false;
		Actuality other = (Actuality) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (date_ajout == null) {
			if (other.date_ajout != null)
				return false;
		} else if (!date_ajout.equals(other.date_ajout))
			return false;
		if (date_redaction == null) {
			if (other.date_redaction != null)
				return false;
		} else if (!date_redaction.equals(other.date_redaction))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id_auto != other.id_auto)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (leader == null) {
			if (other.leader != null)
				return false;
		} else if (!leader.equals(other.leader))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (valide != other.valide)
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Actuality [id_auto=" + id_auto + ", titre=" + titre
				+ ", description=" + description + ", type=" + type
				+ ", date_ajout=" + date_ajout + ", date_redaction="
				+ date_redaction + ", valide=" + valide + ", image=" + image
				+ ", leader=" + leader + ", category=" + category + "]";
	}




	public int getId_auto() {
		return id_auto;
	}

	public void setId_auto(int id_auto) {
		this.id_auto = id_auto;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate_ajout() {
		return date_ajout;
	}

	public void setDate_ajout(Date date_ajout) {
		this.date_ajout = date_ajout;
	}

	public Date getDate_redaction() {
		return date_redaction;
	}

	public void setDate_redaction(Date date_redaction) {
		this.date_redaction = date_redaction;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	
	
}
