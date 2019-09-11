package com.jiin.colorchoice;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//Controller 클래스라는 것을 어노테이션을 통해 기능 지정
@Controller
public class ViewController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	 public String home(Locale locale, Model model)  {

	    return "index";
	 }
	
	@RequestMapping(value = "/histories", method = RequestMethod.GET)
	 public String histories(Locale locale, Model model) throws URISyntaxException, SQLException  {
		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
		
	    Connection connection = DriverManager.getConnection(dbUrl, username, password);
	    
	    String query = "SELECT * FROM f_colors";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		StringBuffer colors = new StringBuffer(); //histories.jsp에 있는 colors에 저장되게 되는거야
		while (resultSet.next()) {
			int idx = resultSet.getInt("idx");
			String code = resultSet.getString("color_code");
			String timestamp = resultSet.getString("timestamp");
			String rowString = "<li style='background-color: " + code + ";'>" + code + "<br />" + timestamp + "</li>";
			colors.append(rowString);
			
		}
		preparedStatement.close();
	    connection.close();
	    
		model.addAttribute("colors", colors);


	    return "histories";
	 }

}
