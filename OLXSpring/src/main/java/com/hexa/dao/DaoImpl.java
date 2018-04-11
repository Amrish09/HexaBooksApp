package com.hexa.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.hexa.entity.Book;
import com.hexa.entity.Category;
import com.hexa.entity.User;

import javassist.NotFoundException;

/** 
 * @author Priya
 * @author Bijitha
 * @version 1.0
 * <p>contains all method implementation</p>
 */

@Repository("mydao")
public class  DaoImpl   implements OlxDao{
   
	
    private Logger logger=LoggerFactory.getLogger("myapp");
	private static SessionFactory sfac;
	
    @Autowired
    public void setSfac(SessionFactory sfac) {
    	
    	this.sfac = sfac;
    }

	/**
	 * @param cat category object
	 * @return list of book
	 * @throws NotFoundException
	 * <p>search the books for a category</p>    
	 */
	@Override
	public List<Book> getBooks(String cat) throws NotFoundException{
		Session sess=sfac.openSession();
		String hql="from Book b inner join fetch b.cat c where c.cname=?";
		Query qry=sess.createQuery(hql);
		qry.setString(0, cat);
		List<Book> lst=qry.list();
		sess.close();
		
		
		return lst;
	}
    
	/**
	 * @param uname username
	 * @param pwd password
	 * @return User
	 * @throws NotFoundException
	 * <p>method for login</p>   
	 */
	@Override
	public User login(String uname, String pwd) throws NotFoundException{
		Session sess = sfac.openSession();
		String hql = "from User where uname=? and pwd=?";
		Query qry = sess.createQuery(hql);
		qry.setString(0, uname);
		qry.setString(1, pwd);
		User user = (User) qry.uniqueResult();
		sess.close();
		if(user == null) {
			logger.error("No such user. Please try again");
			throw new NotFoundException("No such user. Please try again");
		}
		return user;
	}
    
	/**
	 * @param bid bookid
	 * @return list of book
	 * @throws NotFoundException
	 * <p>search the book based on bid</p>    
	 */
	@Override
	public Book getBook(int bid) throws NotFoundException{
		Session sess=sfac.openSession();
		Book book=(Book)sess.get(Book.class, bid);
		sess.close();
		
		return book;
	}
    
	/**
	 * @param book Book object
	 * @return integer
	 * <p>method for adding new book</p>   
	 */
	@Override
	public int addBook(Book book) {
		Session sess = sfac.openSession();
		Transaction tx=sess.beginTransaction();
		try {
			sess.save(book);
			tx.commit();
			return 1;
		} catch (HibernateException e) {
			tx.rollback();
			
		}finally {
			sess.close();
		}
		return 0;
	}
    
	
	/**
	 * @return list of categories
	 * <p>method for getting categories</p>  
	 */
	@Override
	public List<Category> getCategories() {
		Session sess = sfac.openSession();
		Query qry=sess.createQuery("from Category c ");
		List<Category> lst=qry.list();
		sess.close();
		return lst;
	}
	
	/**
	 * @param author author name
	 * @return list of book
	 * <p>method for searching book for a particular author</p>  
	 */
	@Override
	public List<Book> byAuthor(String author) throws ObjectNotFoundException{
		if(author==null)
			return null;
		Session sess=sfac.openSession();
		String hql="from Book  where author like :a";
		Query qry=sess.createQuery(hql);
		qry.setString("a", "%"+author+"%");
		List<Book> lst=qry.list();
		sess.close();	
		if(lst.equals(null)) {
			logger.error("Book not found for your search"+ author);
			throw new ObjectNotFoundException("Book not found for your search"+ author);
		}
		//logger.info("Exception throwed");
		return lst;
	}
    
	/**
	 * @param publisher publisher name
	 * @return list of book
	 * @throws ObjectNotFoundException
	 * <p>method for searching book for a particular publisher</p>  
	 */
	@Override
	public List<Book> byPublisher(String publisher) throws ObjectNotFoundException{
		Session sess=sfac.openSession();
		String hql="from Book where publisher like :p";
		Query qry=sess.createQuery(hql);
		qry.setString("p", "%"+publisher+"%");
		List<Book> lst=qry.list();
		sess.close();	
		if(lst==null) {
			logger.error("Book not found for your search"+ publisher);
			throw new ObjectNotFoundException("Book not found for your search"+ publisher);
		}
		//logger.info("Exception throwed");
		return lst;
	}
    
	 
	/**
     * @param bname book name
	 * @return list of book
	 * @throws ObjectNotFoundException
	 * <p>method for searching book for a particular name </p> 
	*/
	@Override
	public List<Book> byBookname(String bname) throws ObjectNotFoundException{
		Session sess=sfac.openSession();
		String hql="from Book where bname like :b";
		Query qry=sess.createQuery(hql);
		qry.setString("b", "%"+bname+"%");
		List<Book> lst=qry.list();
		sess.close();	
		if(lst==null) {
			logger.error("Book not found for your search"+ bname);
			throw new ObjectNotFoundException("Book not found for your search"+ bname);
		}
		//logger.info("Exception throwed");
		return lst;
	}
	
	/**
     * @param user User object
	 * @return int
	 * <p>method for adding new user</p> 
	*/
	@Override
	public int addUser(User user) {
		Session sess = sfac.openSession();
		Transaction tx=sess.beginTransaction();
		try {
			sess.save(user);
			tx.commit();
			return 1;
		} catch (HibernateException e) {
			tx.rollback();
			
		}finally {
			sess.close();
		}
		return 0;
	}



}
