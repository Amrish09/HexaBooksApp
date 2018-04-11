package com.hexa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import javassist.NotFoundException;

/**
 * @author Priya
 * @author Bijitha
 * @version 1.0
 * class User
 */
@Entity()
@Table(name="user")
public class User {
@Id
@Column(name="uid")
private int uid;

@NotNull(message="User name is mandatory")
@NotEmpty(message="User name is mandatory")
@Size(min=3,max=20, message="name can contain 3-20 alphabets")
@Pattern(regexp="[a-zA-z]+", message="name must contain only alphabets")
@Column(name="uname")
private String uname;

@NotNull(message="Password is mandatory")
@NotEmpty(message="Password is mandatory")
@Size(min=3,max=20, message="Password can contain 3-20 alphabets")
@Column(name="pwd")
private String pwd;

/**
 * @return integer
 * getter for uid   
 */
public int getUid() {
	return uid;
}

/**
 * @param arg1 uid
 * setter for uid   
 */
public void setUid(int uid) {
	this.uid = uid;
}

/**
 * @return String
 * getter for uname   
 */
public String getUname() {
	return uname;
}

/**
 * @param arg1 uname
 * setter for uname  
 */
public void setUname(String uname) {
	this.uname = uname;
}

/**
 * @return String
 * getter for pwd  
 */
public String getPwd() {
	return pwd;
}

/**
 * @param arg1 pwd
 * setter for pwd  
 */
public void setPwd(String pwd) {
	this.pwd = pwd;
}
	
}
