package zale.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with Eclipse.
 * User: 朱永林.
 * Date: 2017年4月18日.
 * Time: 下午5:57:50.
 * Explain:
 */
public class StudentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private Date birthday;
	private int avgscore;

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", name=" + name + ", description=" + description + ", birthday=" + birthday
				+ ", avgscore=" + avgscore + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAvgscore() {
		return avgscore;
	}

	public void setAvgscore(int avgscore) {
		this.avgscore = avgscore;
	}

}
