package com.keepitfresh.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item
{
   @Id @GeneratedValue
   @Column(name = "id")
	private int id;
   @Column(name = "user")
    private String user;
   @Column(name = "name")
    private String name;
   @Column(name = "category")
    private String category;
   @Column(name = "quantity")
    private int quantity;
   @Column(name = "exp_date")
    private Date expDate;
    
    public Item() {
        super();
    }
    
    public Item(String user, String name, String category, int quantity, Date expDate)
    {
        super();
        this.user = user;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.expDate = expDate;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}


    @Override
    public String toString() {
        return String.format(
                "Item [id=%s, user=%s, name=%s, category=%s, quantity=%s, expDate=%s]", id,
                user, name, category, quantity, expDate);
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
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        return true;
	}
}

