package com.levo.ex03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DerbyServerClassLoader extends ClassLoader {
	
	private ClassLoader parent;
	private String connectionString;
	
	public DerbyServerClassLoader(String connectionString) {
		this(ClassLoader.getSystemClassLoader(), connectionString);
		this.connectionString = connectionString;
	}
	
	public DerbyServerClassLoader(ClassLoader parent, String connectionString) {
		super(parent);
		this.parent = parent;
		this.connectionString = connectionString;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class cls = null;
		
		try {
			cls = parent.loadClass(name);
		} catch(ClassNotFoundException cnfe) {
			byte[] bytes;
			try {
				bytes = loadClassFromDatabase(name);
			} catch (SQLException sqle) {
				throw new ClassNotFoundException("Unable to load class from Database", sqle);
			}
			
			if(bytes != null)
				return defineClass(name, bytes, 0, bytes.length);
		}
		
		return super.findClass(name);
	}

	private byte[] loadClassFromDatabase(String name) throws SQLException {
		PreparedStatement pstmt = null;
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(connectionString);
			
			String sql = "SELECT CLASS FROM CLASSES WHERE CLASSNAME = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Blob blob = rs.getBlob(1);
				byte[] data = blob.getBytes(1, (int)blob.length());
				return data;
			}
		} catch(SQLException sqlex) {
			System.out.println("Unexpected exception: " + sqlex.toString());
		} catch(Exception ex) {
			System.out.println("Unexpected exception: " + ex.toString());
		} finally {
			if(pstmt != null)
				pstmt.close();
			
			if(connection != null)
				connection.close();
		}
		
		return null;
	}
	
}
