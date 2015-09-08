package com.pavithra.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/books")
public class BookController {
	@RequestMapping(value="/insert/barcode/{barcode}/title/{title}/author/{author}/pages/{pages}/flag/{flag}", method = RequestMethod.GET)
	public @ResponseBody String insertBook_old(@PathVariable int barcode,@PathVariable String title,@PathVariable String author,@PathVariable int pages,@PathVariable int flag, ModelMap model) {
		int successFlag= insert("insert",barcode,title,author,pages,flag);
		//model.addAttribute("books", i);
		if (successFlag==1) model.addAttribute("book", "Book Inserted");
		else
			return "The book with barcode "+barcode+" already exists!";
		Book book=new Book(barcode,title,author,pages,flag);
		return book.toString();
	}

	@RequestMapping(value="/delete/barcode/{barcode}", method = RequestMethod.GET)
	public @ResponseBody String deleteBook(@PathVariable int barcode, ModelMap model) {
		int successFlag= delete(barcode);
		Book book=new Book(barcode);
		if (successFlag==1)
			return "Book with barcode "+book.barcode+ " has been deleted successfully!";
		else
			return "No book exists with barcode "+book.barcode;
	}

	@RequestMapping(value="/retrieve/barcode/{barcode}", method = RequestMethod.GET)
	public @ResponseBody String retrievebook(@PathVariable int barcode, ModelMap model) {
		Book book= retrieve(barcode);
		if (book.barcode==0)
			return "No book exists with barcode "+barcode;
		else
			return book.toString();
	}

	public int insert(String operation,int barcode,String title,String author,int pages,int flag) {
		String query1 =null;
		String query2 =null;
		String dbUrl = "jdbc:mysql://localhost/mydatabase";
		String dbClass = "com.mysql.jdbc.Driver";
		query1 = "SELECT *FROM BOOKS WHERE BARCODE="+barcode+";";
		query2 = "INSERT INTO BOOKS VALUES ("+barcode+",'"+title+"','"+author+"','"+pages+"','"+flag+"');";
		String username = "root";
		String password = "";
		int i=0;
		try {
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl,username, password);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query1);
			if (rs.next()==true)
				return 0;
			statement.executeUpdate(query2);
			connection.close();
			i=1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return i;
	}
	public int delete(int barcode) {
		String query =null;
		String dbUrl = "jdbc:mysql://localhost/mydatabase";
		String dbClass = "com.mysql.jdbc.Driver";
		query = "DELETE FROM BOOKS WHERE BARCODE="+barcode+";";
		String username = "root";
		String password = "";
		try {
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl,username, password);
			Statement statement = connection.createStatement();
			Book book=new Book();
			statement.execute(query);
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	public Book retrieve(int barcode) {
		String query =null;
		String dbUrl = "jdbc:mysql://localhost/mydatabase";
		String dbClass = "com.mysql.jdbc.Driver";
		query = "SELECT *FROM BOOKS WHERE BARCODE="+barcode+";";
		String username = "root";
		String password = "";
		Book book=new Book();
		int i=0;
		try {
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl,username, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				book.barcode = resultSet.getInt("barcode");
				book.title = resultSet.getString("author");
				book.author = resultSet.getString("title");
				book.pages = resultSet.getInt("pages");
				book.read = resultSet.getInt("flag");
			}
			connection.close();
			i=1;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
			i=0;
		}
		return book;
	}
}