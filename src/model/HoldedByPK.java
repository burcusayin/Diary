package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the HOLDED_BY database table.
 * 
 */
@Embeddable
public class HoldedByPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DIARY_ID")
	private long diaryId;

	@Column(name="HOLDER_ID")
	private long holderId;

	public HoldedByPK() {
	}
	public long getDiaryId() {
		return this.diaryId;
	}
	public void setDiaryId(long diaryId) {
		this.diaryId = diaryId;
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
		if (!(other instanceof HoldedByPK)) {
			return false;
		}
		HoldedByPK castOther = (HoldedByPK)other;
		return 
			(this.diaryId == castOther.diaryId)
			&& (this.holderId == castOther.holderId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.diaryId ^ (this.diaryId >>> 32)));
		hash = hash * prime + ((int) (this.holderId ^ (this.holderId >>> 32)));
		
		return hash;
	}
}