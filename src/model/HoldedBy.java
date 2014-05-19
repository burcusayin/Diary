package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HOLDED_BY database table.
 * 
 */
@Entity
@Table(name="HOLDED_BY")
@NamedQuery(name="HoldedBy.findAll", query="SELECT h FROM HoldedBy h")
public class HoldedBy implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HoldedByPK id;

	public HoldedBy() {
	}

	public HoldedByPK getId() {
		return this.id;
	}

	public void setId(HoldedByPK id) {
		this.id = id;
	}

}