package com.hexa.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Priya
 * @author Bijitha
 * @version 1.0
 * class Category
 */
@Entity
@Table(name="category")
public class Category {

	@Id
	private int cid;
	private String cname;
	@OneToMany(mappedBy="cat",fetch=FetchType.LAZY)
	private Set<Book> blist;
	
	
	/**
	 * @return int
	 * getter for cid   
	 */
	public int getCid() {
		return cid;
	}
	
	/**
	 * @param arg1 cid
	 * setter for cid  
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	/**
	 * @return String
	 * getter for cname   
	 */
	public String getCname() {
		return cname;
	}
	
	/**
	 * @param arg1 cname
	 * setter for cname 
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	/**
	 * @return Set of book
	 * getter for Blist   
	 */
	public Set<Book> getBlist() {
		return blist;
	}
	
	/**
	 * @param arg1 blist
	 * setter for blist 
	 */
	public void setBlist(Set<Book> blist) {
		this.blist = blist;
	}
	@Override
	public String toString() {
		return cid+" "+cname;
	}
}
