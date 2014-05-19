package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PROPERTYRIGHTHOLDER database table.
 * 
 */
@Entity
@NamedQuery(name="Propertyrightholder.findAll", query="SELECT p FROM Propertyrightholder p")
public class Propertyrightholder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="HOLDER_ID")
	private long holderId;

	@Column(name="HOLDER_E_MAIL")
	private String holderEMail;

	@Column(name="HOLDER_NAME")
	private String holderName;

	@Column(name="HOLDER_PHONE")
	private String holderPhone;

	@Column(name="HOLDER_SURNAME")
	private String holderSurname;

	public Propertyrightholder() {
	}

	public long getHolderId() {
		return this.holderId;
	}

	public void setHolderId(long holderId) {
		this.holderId = holderId;
	}

	public String getHolderEMail() {
		return this.holderEMail;
	}

	public void setHolderEMail(String holderEMail) {
		this.holderEMail = holderEMail;
	}

	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderPhone() {
		return this.holderPhone;
	}

	public void setHolderPhone(String holderPhone) {
		this.holderPhone = holderPhone;
	}

	public String getHolderSurname() {
		return this.holderSurname;
	}

	public void setHolderSurname(String holderSurname) {
		this.holderSurname = holderSurname;
	}

}