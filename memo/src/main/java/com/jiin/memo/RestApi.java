package com.jiin.memo;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApi {
	@RequestMapping(value = "/select_memo_list", method = RequestMethod.GET)
	public ArrayList<HashMap> selectMemoList(Locale locale, Model model) throws SQLException, URISyntaxException {
		
		ArrayList<HashMap> data = new ArrayList<HashMap>();
		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
		
	    Connection connection = DriverManager.getConnection(dbUrl, username, password);
	    
	    String query = "SELECT * FROM memoNote";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int no = resultSet.getInt("no");
			String memo = resultSet.getString("memo");
			HashMap<String, String> row = new HashMap<String, String>();
			row.put("no", "" + no);
			row.put("memo", memo);
			data.add(row);
		}
		preparedStatement.close();
	    connection.close();
	    
		return data;
	}
	@RequestMapping(value = "/memo_insert", method = RequestMethod.GET)
	public HashMap<String, String> memoInsert(Locale locale, Model model
			, @RequestParam String memo) throws URISyntaxException, SQLException {
		
//		Connection connection = null;
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
		
	    Connection connection = DriverManager.getConnection(dbUrl, username, password);
	    
	    String query = "INSERT INTO memoNote (memo) VALUES ('" + memo + "')";
	    
	    Statement statement = connection.createStatement();
	    int q = statement.executeUpdate(query);
	    statement.close();
	    connection.close();

		result.put("result", "success");
		return result;
	}
	
	@RequestMapping(value = "/memo_mod", method = RequestMethod.GET)
	public HashMap<String, String> userMod(Locale locale, Model model
			, @RequestParam String no, @RequestParam String memo) throws URISyntaxException, SQLException {
		
		HashMap<String, String> result = new HashMap<String, String>();
		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
		
	    Connection connection = DriverManager.getConnection(dbUrl, username, password);
	    
	    String query = "UPDATE memoNote SET memo='" + memo +"'WHERE no=" + no;
	    Statement statement = connection.createStatement();
		int q = statement.executeUpdate(query);
		statement.close();
		connection.close();
	    
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping(value = "/memo_del", method = RequestMethod.GET)
	public HashMap<String, String> memoDel(Locale locale, Model model, @RequestParam String no) throws URISyntaxException, SQLException {
		HashMap<String, String> result = new HashMap<String, String>();
		
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		
		String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
		
	    Connection connection = DriverManager.getConnection(dbUrl, username, password);
	    
	    String query = "DELETE FROM memoNote WHERE no=" + no;
	    
	    Statement statement = connection.createStatement();
		int q = statement.executeUpdate(query);
		statement.close();
		connection.close();
	    
	    result.put("result", "success");
	    
		return result;
	}

}
