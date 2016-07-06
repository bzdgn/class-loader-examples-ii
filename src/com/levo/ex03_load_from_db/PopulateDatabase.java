package com.levo.ex03_load_from_db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PopulateDatabase {

	public static void main(String[] args) throws SQLException, IOException {
		String dbUrl = "jdbc:derby://localhost:1527/memory:levoDB/class-loader-demo;create=true";
		Connection conn;
		conn = DriverManager.getConnection(dbUrl);
		
		
		createTable(conn);
		createEntry(conn);
		
		conn.close();
	}
	
	private static void createEntry(Connection conn) throws SQLException, IOException {		
		String insertTable =	"INSERT INTO CLASSES (CLASSNAME, CLASS) " +
								"VALUES(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(insertTable);
		
		String workingDirectory = System.getProperty("user.dir");
		String pathString = workingDirectory + "\\lib2\\Quote.class";
		System.out.println(pathString);
		
		Path path = Paths.get(pathString);
		byte[] fileBytes = Files.readAllBytes(path);
		Blob fileBlob = conn.createBlob();
		fileBlob.setBytes(1, fileBytes);
		
		pstmt.setString(1, "com.levo.ex02.implementations.Quote");
		pstmt.setBlob(2, fileBlob);
		
		pstmt.executeUpdate();
	}
	
	private static void createTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		
		String createTable = "CREATE TABLE CLASSES(CLASSNAME VARCHAR(255), CLASS BLOB)";
		
		stmt.executeUpdate(createTable);
		
		stmt.close();
	}

}
