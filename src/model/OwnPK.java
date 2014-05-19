package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the "OWN" database table.
 * 
 */
@Embeddable
public class OwnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="PROPERTYRIGHT_ID")
	private long propertyrightId;

	@Column(name="HOLDER_ID")
	private long holderId;

	public OwnPK() {
	}
	public long getPropertyrightId() {
		return this.propertyrightId;
	}
	public void setPropertyrightId(long propertyrightId) {
		this.propertyrightId = propertyrightId;
	}
	public long getHolderId() {
		return this.holderId;
	}
	public void setHolderId(long holderId) {
		this.holderId = holderId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OwnPK)) {
			return false;
		}
		OwnPK castOther = (OwnPK)other;
		return 
			(this.propertyrightId == castOther.propertyrightId)
			&& (this.holderId == castOther.holderId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.propertyrightId ^ (this.propertyrightId >>> 32)));
		hash = hash * prime + ((int) (this.holderId ^ (this.holderId >>> 32)));
		
		return hash;
	}
}