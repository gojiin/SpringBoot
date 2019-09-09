package com.jiin.memo;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

 @RequestMapping(value = "/", method = RequestMethod.GET)
 public String home(Locale locale, Model model)  {

    return "login";
 }
 
 @RequestMapping(value = "/create", method = RequestMethod.GET)
 public String create(Locale locale, Model model) throws URISyntaxException, SQLException {

	URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
	
    Connection connection = DriverManager.getConnection(dbUrl, username, password);
    
    String query = "CREATE TABLE memoNote(no serial PRIMARY KEY, memo TEXT);";
    Statement statement = connection.createStatement();
    int result = statement.executeUpdate(query);
    
    statement.close();
    connection.close();
    return "login";
 }
 
 @RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
	    
	    return "join";
	}
 
	@RequestMapping(value = "/input_password", method = RequestMethod.POST)
	public String inputPassword(@RequestParam("password") String password, 
			HttpServletRequest request) {
		if(password.equals("jiin")) {
			HttpSession session = request.getSession();
			session.setAttribute("is_login", true);
			return "redirect:/memo_list";
			
		} else
			
		return "redirect:/";
	}
	
	@RequestMapping(value = "/memo_list", method = RequestMethod.GET)
	public String memoList(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Boolean isLogin = (Boolean) session.getAttribute("is_login");
		if (isLogin == null || !isLogin) {
			return "redirect:/";
		}
		
		return "memoList";
	}
 
}