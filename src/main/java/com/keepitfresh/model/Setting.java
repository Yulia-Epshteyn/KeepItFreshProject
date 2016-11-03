package com.keepitfresh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_SETTING")
public class Setting {

   @Id @GeneratedValue
   @Column(name = "id")
	private int id;
	@Column(name = "user")
    private String user;
	@Column(name = "email_address")
    private String emailAddress;
   @Column(name = "days_before_exp")
    private int daysBeforeExp;

    public Setting() {
        super();
    }
    
    public Setting(String user, String email, int daysBeforeExp)
    {
        super();
        this.user = user;
        this.emailAddress = email;
        this.daysBeforeExp = daysBeforeExp;
    }

    
	public int getId() {
		return id;
	 }

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getDaysBeforeExp() {
		return daysBeforeExp;
	}

	public void setDaysBeforeExp(int daysBeforeExp) {
		this.daysBeforeExp = daysBeforeExp;
	}
	
    @Override
    public String toString() {
        return String.format(
                "Setting [id=%s, user=%s, emailAddress=%s, daysBeforeExp=%s]", id,
                user, emailAddress, daysBeforeExp);
    }
 	
   @Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Setting other = (Setting) obj;
        if (id != other.id)
            return false;
        return true;
	}
    
}
