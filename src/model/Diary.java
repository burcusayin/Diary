package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the DIARY database table.
 * 
 */
@Entity
@NamedQuery(name="Diary.findAll", query="SELECT d FROM Diary d")
public class Diary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DIARY_ID")
	private long diaryId;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="DIARY_DATE")
	private Date diaryDate;

	@Column(name="DIARY_TIME")
	private Timestamp diaryTime;

	private String title;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="USERNAME")
	private Person person;

	//bi-directional many-to-one association to Propertyright
	@OneToMany(mappedBy="diary")
	private Set<Propertyright> propertyrights;

	public Diary() {
	}

	public long getDiaryId() {
		return this.diaryId;
	}

	public void setDiaryId(long diaryId) {
		this.diaryId = diaryId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDiaryDate() {
		return this.diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public Timestamp getDiaryTime() {
		return this.diaryTime;
	}

	public void setDiaryTime(Timestamp diaryTime) {
		this.diaryTime = diaryTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Set<Propertyright> getPropertyrights() {
		return this.propertyrights;
	}

	public void setPropertyrights(Set<Propertyright> propertyrights) {
		this.propertyrights = propertyrights;
	}

	public Propertyright addPropertyright(Propertyright propertyright) {
		getPropertyrights().add(propertyright);
		propertyright.setDiary(this);

		return propertyright;
	}

	public Propertyright removePropertyright(Propertyright propertyright) {
		getPropertyrights().remove(propertyright);
		propertyright.setDiary(null);

		return propertyright;
	}

}