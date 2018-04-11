package com.hexa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Priya
 * @author Bijitha
 * @version 1.0
 * class Book
 */
@Entity
@Table(name="book")
public class Book {
    
	
	@Id
	private int bid;
	
	private String bimg;
	@NotNull(message="Book name is mandatory")
	@NotEmpty(message="Book name is mandatory")
	@Size(min=3,max=20, message="name can contain 3-20 alphabets")
	@Pattern(regexp="[a-zA-z]+", message="name must contain only alphabets")
	@Column(name="bname")
	private String bname;
	
	@NotNull(message="Author name is mandatory")
	@NotEmpty(message="Author name is mandatory")
	@Size(min=3,max=20, message="Author name can contain 3-20 alphabets")
	//@Pattern(regexp="[a-zA-z]+", message="Author name must contain only alphabets")
	@Column(name="author")
	private String author;
	
	@NotNull(message="Publisher is mandatory")
	@NotEmpty(message="Publisher is mandatory")
	@Size(min=3,max=20, message="Publisher can contain 3-20 alphabets")
	@Pattern(regexp="[a-zA-z]+", message="Publisher must contain only alphabets")
	@Column(name="publisher")
	private String publisher;
	
	@NotNull(message="Price is mandatory")
	@Min(value=100, message="minimum value must be 100")
	@Column(name="price")
	private double price;
	
	@ManyToOne
	@JoinColumn(name="categoryid",referencedColumnName="cid")
	private Category cat;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return bid+" "+bname+" "+author+" "+publisher+" "+price+" "+bimg;
	}
	
	/**
	 * @return String
	 * getter for bimg   
	 */
	public String getBimg() {
		return bimg;
	}
	
	/**
	 * @param bimg
	 * setter for bimg   
	 */
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	
	/**
	 * @return integer
	 * getter for bid   
	 */
	public int getBid() {
		return bid;
	}
	
	/**
	 * @param bid
	 * setter for bid   
	 */
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	/**
	 * @return String
	 * getter for bname   
	 */
	public String getBname() {
		return bname;
	}
	
	/**
	 * @param bname
	 * setter for bname   
	 */
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	/**
	 * @return String
	 * getter for author   
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * @param author
	 * setter for author  
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * @return String
	 * getter for publisher  
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * @param publisher
	 * setter for publisher   
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * @return Double
	 * getter for price   
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * @param price
	 * setter for price   
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return Category
	 * getter for cat   
	 */
	public Category getCat() {
		return cat;
	}
	
	/**
	 * @param cat
	 * setter for cat  
	 */
	public void setCat(Category cat) {
		this.cat = cat;
	}
	
}
