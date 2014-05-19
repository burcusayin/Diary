package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PROPERTYRIGHT database table.
 * 
 */
@Entity
@NamedQuery(name="Propertyright.findAll", query="SELECT p FROM Propertyright p")
public class Propertyright implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PROPERTYRIGHT_ID")
	private long propertyrightId;

	@Temporal(TemporalType.DATE)
	@Column(name="PROPERTYRIGHT_DATE")
	private Date propertyrightDate;

	@Column(name="\"TYPE\"")
	private String type;

	//bi-directional many-to-one association to Diary
	@ManyToOne
	@JoinColumn(name="DIARY_ID")
	private Diary diary;

	public Propertyright() {
	}

	public long getPropertyrightId() {
		return this.propertyrightId;
	}

	public void setPropertyrightId(long propertyrightId) {
		this.propertyrightId = propertyrightId;
	}

	public Date getPropertyrightDate() {
		return this.propertyrightDate;
	}

	public void setPropertyrightDate(Date propertyrightDate) {
		this.propertyrightDate = propertyrightDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Diary getDiary() {
		return this.diary;
	}

	public void setDiary(Diary diary) {
		this.diary = diary;
	}

}