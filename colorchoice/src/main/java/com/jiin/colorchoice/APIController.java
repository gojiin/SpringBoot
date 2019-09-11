package com.jiin.colorchoice;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
	
	@RequestMapping(value = "/create_table", method = RequestMethod.GET)
	public HashMap<String, String> createTable() throws SQLException, URISyntaxException {
		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
		
	    Connection connection = DriverManager.getConnection(dbUrl, username, password);
	    
	    String query = "CREATE TABLE f_colors(idx serial PRIMARY KEY, color_code VARCHAR(10), timestamp VARCHAR(20));";
	    Statement statement = connection.createStatement();
	    int result = statement.executeUpdate(query);
	    
	    statement.close();
	    connection.close();
	    
	    HashMap<String, String> resultJson = new HashMap<String, String>();
	    resultJson.put("message", "success");
	    
		return resultJson;
	}
	
	@RequestMapping("/select_color")
	public HashMap<String, String> selectColor(@RequestParam String code) throws URISyntaxException, SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String timestamp = format.format(time);

		HashMap<String, String> result = new HashMap<String, String>();
		
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

	    Connection connection = DriverManager.getConnection(dbUrl, username, password);

		String query = "INSERT INTO f_colors (color_code, timestamp) VALUES ('" + code + "', '"
				+ timestamp + "')";
		Statement statement = connection.createStatement();
		int q = statement.executeUpdate(query);
		statement.close();
		connection.close();
		
		return result;
	}

}
