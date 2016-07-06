package com.levo.ex03;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.levo.ex02.interfaces.IQuote;

public class MainViaDB {

	public static void main(String[] args) {
		try {
			DerbyServerClassLoader cl = new DerbyServerClassLoader("jdbc:derby://localhost:1527/memory:levoDB/class-loader-demo;create=true");
			
			Class clazz = cl.findClass("com.levo.ex02.implementations.Quote");		// Load class
			IQuote o = (IQuote) clazz.newInstance();								// Instantiate the class object
			System.out.println(o.getQuote());										// Print toString() of the class
		} catch(ClassNotFoundException e) {		// Class Not Found Exception
			e.printStackTrace();
		} catch (InstantiationException e) {	// Instantiation Exception
			e.printStackTrace();
		} catch (IllegalAccessException e) {	// Access Exception
			e.printStackTrace();
		}
	}

}
