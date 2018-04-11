package com.hexa.web;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hexa.dao.ObjectNotFoundException;
import com.hexa.dao.OlxDao;
import com.hexa.entity.Book;
import com.hexa.entity.Category;
import com.hexa.entity.User;

import javassist.NotFoundException;

/**
 * @author Priya
 * @author Bijitha
 * @version 1.0
 * Olx controller
 */
@Controller
public class OlxController {

	private Logger logger=LoggerFactory.getLogger("myapp");
	@Autowired
	private OlxDao dao;
	
	/**
	 * @param arg1 model
	 * @return Main.jsp
	 *<p>list all categories</p>   
	 */
	@RequestMapping(value= {"/","/main"})
	public String MainPage(Model model) {
		model.addAttribute("clist",dao.getCategories());
		return "Main";
	}
	
	/**
	 * @param arg1 cname
	 * @param arg2 model
	 * @return BookView.jsp
	 * <p>Search by category name</p>  
	 */
	@RequestMapping("/searchbycat")
	public String ViewByCat(@RequestParam("ctg") String cname,Model model) throws NotFoundException{
		model.addAttribute("blist",dao.getBooks(cname));
		return "BookView";
	}
	
	/**
	 * @param arg1 author
	 * @param arg2 model
	 * @return AutForm.jsp
	 * <p>Search by author name</p>   
	 */
	@RequestMapping("/byauthor")
	public String ViewAut(@RequestParam("auth") String author, Model model) throws ObjectNotFoundException{
		model.addAttribute("alist",dao.byAuthor(author));
		return "AutForm";
	}
	
	/**
	 * @param arg1 publisher
	 * @param arg2 model
	 * @return AutForm.jsp
	 * <p>Search by publisher name</p>   
	 */
	@RequestMapping("/bypublisher")
	public String ViewPublisher(@RequestParam("publ") String publisher, Model model) throws ObjectNotFoundException{
		model.addAttribute("alist",dao.byPublisher(publisher));
		return "AutForm";
	}
	
	/**
	 * @param arg1 bname
	 * @param arg2 model
	 * @return AutForm.jsp
	 * <p>Search by book name</p>
	 */
	@RequestMapping("/bybook")
	public String ViewBookname(@RequestParam("book") String bname, Model model) throws ObjectNotFoundException{
		model.addAttribute("alist",dao.byBookname(bname));
		return "AutForm";
	}
	
	/**
	 * @param arg1 bid
	 * @param arg2 model
	 * @return BookDetails.jsp
	 * <p>dispalys the book details</p>  
	 */
	@RequestMapping("/bookdetails")
	public String ViewBook(@RequestParam("bookid") Integer bid,Model model) throws NotFoundException{
		model.addAttribute("nlist",dao.getBook(bid));
		return "BookDetails";
	}
	
	/**
	 * @param arg1 model
	 * @return Login.jsp
	 * <p>redirects to login page</p>
	 */
	@RequestMapping("/loginpage")
	public String LoginPage(Model model){
		return "Login";
	}
	
	/**
	 * @param arg1 uname
	 * @param arg2 pwd
	 * @param arg3 model
	 * @return Main.jsp
	 * <p>login page</p>    
	 */
	@RequestMapping(value = "/loginval", method=RequestMethod.POST)
	public String Login(@RequestParam("username") String uname,@RequestParam("password") String pwd, Model model,HttpServletRequest req) throws NotFoundException{
		User user = new User();
		user=dao.login(uname, pwd);
		//model.addAttribute("ulist",dao.login(uname, pwd));
		HttpSession sess = req.getSession();
		if(user != null) {
			sess.setAttribute("user", user);
		}
		model.addAttribute("clist",dao.getCategories());
		return "Main";
	}
	
	/**
	 * @param arg1 img
	 * @param arg2 resp
	 * @return String 
	 * <p>to display the book image</p> 
	 */
	@RequestMapping("viewimg")
	public String viewImage(@RequestParam("imgname") String img, HttpServletResponse resp) throws IOException {
		resp.setContentType("image/jpeg");
		FileInputStream fis = new FileInputStream("D:\\bookpics\\"+ img+".jpg");
		byte[] arr = new byte[fis.available()];
		fis.read(arr);
		ServletOutputStream out = resp.getOutputStream();
		out.write(arr);
		fis.close();
		return null;
	}
	
	/**
	 * @param arg1 bid
	 * @param arg2 resp
	 * @return String  
	 * <p>Downloads the book</p>
	 */
	@RequestMapping("bookdownload")
	public String bookDownload(@RequestParam("bookid") Integer bid, HttpServletResponse resp) throws IOException, NotFoundException {
		resp.setContentType("application/pdf");
		resp.setHeader("Content-Disposition", "attachment");
		Book book = new Book();
		book = dao.getBook(bid);
		FileInputStream fis = new FileInputStream("D:\\down\\"+book.getBname()+".pdf");
		byte[] arr = new byte[fis.available()];
		fis.read(arr);
		ServletOutputStream out = resp.getOutputStream();
		out.write(arr);
		fis.close();
		return null;
	}
	
	/**
	 * @param arg1 model
	 * @return AddBook.jsp
	 * <p>redirects to Add Book page</p>
	 */
	@RequestMapping("/addfrm")
	public String displayAddFrm(Model model) {
		Book bk = new Book();
		Category ct = new Category();
		bk.setCat(ct);
		
		model.addAttribute("obean", bk);
		model.addAttribute("cmap", getCattAsMap());
		return "AddBook";

	}
	
	/**
	 * @param arg1 book
	 * @param arg2 br
	 * @param arg3 file
	 * @param arg4 file1
	 * @param arg5 model
	 * @return AddBook.jsp or Main.jsp
	 * <p>redirects to Add Book page or main page</p>
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addBook(@Valid @ModelAttribute("obean") Book book, BindingResult br,
			@RequestParam("fname") MultipartFile file,@RequestParam("bookpdf") MultipartFile file1, Model model) throws IOException {
		if(br.hasErrors()) {
			logger.debug("validation failure");  
		model.addAttribute("cmap", getCattAsMap());
		return "AddBook";
		}
		logger.debug("validation successfull");
           book.setBimg(book.getBname());
		   FileOutputStream fos=new FileOutputStream("d:\\bookpics\\"+book.getBimg()+".jpg");
		   fos.write(file.getBytes());
		   fos.close();
		   logger.info("image saved");
		   FileOutputStream fpdf=new FileOutputStream("d:\\down\\"+book.getBname()+".pdf");
		   fpdf.write(file1.getBytes());
		   fpdf.close();
		   logger.info("File saved");
		   dao.addBook(book);
		   logger.info("book added");
		   model.addAttribute("msg","book added successfully");
		   model.addAttribute("clist",dao.getCategories());
		return "Main";
		
	}
	
	/**
	 * @param arg1 model
	 * @return AddUser.jsp
	 * <p>redirects to Add User page</p>
	 */
	@RequestMapping("/adduser")
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("ubean", user);
		
		return "AddUser";

	}
	
	/**
	 * @param arg1 user
	 * @param arg2 br
	 * @param arg3 model
	 * @return AddUser.jsp or Main.jsp
	 * <p>Add the user or return to main page</p>
	 */
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public String newUser(@Valid @ModelAttribute("ubean") User user, BindingResult br, Model model) throws IOException {
		if(br.hasErrors()) {
			logger.debug("validation failure");  
		return "AddUser";
		}
		logger.debug("validation successfull");
		   dao.addUser(user);
		   logger.info("User added");
		   model.addAttribute("msg","User added successfully");
		   model.addAttribute("clist",dao.getCategories());
		return "Main";
		
	}
	

	/**
	 * @param arg1 req
	 * @param arg2 model
	 * @return Main.jsp
	 * <p>Logs out to main page</p>
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		HttpSession sess=req.getSession(false);
		sess.invalidate();
		 model.addAttribute("clist",dao.getCategories());
		 return "Main";


	}
	/**
	 * <p> maps category name to category id</p>
	 * @return map
	 */
	public Map<Integer, String> getCattAsMap() {
		List<Category> lst = dao.getCategories();
		Map<Integer, String> map = new HashMap<>();
		for (Category  c : lst) {
			map.put(c.getCid(), c.getCname());
		}
		return map;

	}
	
	/**
	 * <p>handles exception</p>
	 * @return error page
	 */
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleException(NotFoundException ex) {
		return new ModelAndView("MyErr","err",ex.getMessage());
	}
	
}
