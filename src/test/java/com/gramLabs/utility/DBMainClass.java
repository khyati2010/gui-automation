package com.gramLabs.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBMainClass extends MainClass {
	private static Statement statement;
	private static Connection connection;

	
	public static void createConnection()
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(property.getProperty("database_url"), property.getProperty("usr_name"),
				property.getProperty("password"));
		statement = connection.createStatement();
	}

	
//	A generic method to fetch a value from a table using column name
	public static ArrayList<String> executeAQuery(String query, String column) throws SQLException, ClassNotFoundException {
		createConnection();
		ResultSet rs = statement.executeQuery(query);
		ArrayList<String> data = new ArrayList<String>();
		while (rs.next()) {
			data.add(rs.getString(column));
		}
		return data;
	}
}
