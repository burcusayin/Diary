package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "OWN" database table.
 * 
 */
@Entity
@Table(name="\"OWN\"")
@NamedQuery(name="Own.findAll", query="SELECT o FROM Own o")
public class Own implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OwnPK id;

	public Own() {
	}

	public OwnPK getId() {
		return this.id;
	}

	public void setId(OwnPK id) {
		this.id = id;
	}

}