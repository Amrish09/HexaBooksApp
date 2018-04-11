 package com.hexa.dao;

import java.util.List;

import com.hexa.entity.Book;
import com.hexa.entity.Category;
import com.hexa.entity.User;

import javassist.NotFoundException;

/**
 * 
 *<p>Methods for CRUD operation</p>
 *
 */
public interface OlxDao {
	public List<Book> getBooks(String cat) throws NotFoundException;
	public User login(String uname, String pwd) throws NotFoundException;
	public Book getBook(int bid) throws NotFoundException; 
	public int addBook(Book book );
    public List<Category> getCategories();
    public List<Book> byAuthor(String author) throws ObjectNotFoundException;
	public List<Book> byPublisher(String publisher) throws ObjectNotFoundException;
	public List<Book> byBookname(String bname) throws ObjectNotFoundException;
	public int addUser(User user);
}
