package com.siri.jdbc;

import java.sql.*;
import java.util.Scanner;

public class Test {
	public static Connection conn = null;
	public static PreparedStatement stmt = null;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook", "root", "root");
		//user inputs
			
			System.out.println("Enter the username: ");
			String uname = scan.next();
			System.out.println("Enter the password: ");
			String pword = scan.next();

			PreparedStatement statement = conn
					.prepareStatement("select * from users  where user_name = ? and password = ?");
			
			//checking user inputs with the data in the database
			
			statement.setString(1, uname);
			statement.setString(2, pword);
			ResultSet result = statement.executeQuery();
		
			//result.first() returns true if given user input and the data in the database is equal
			
			if(result.first()) {
				System.out.println("valid user");
			}else {
				System.out.println("wrong id or password");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}