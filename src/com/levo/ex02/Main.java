package com.levo.ex02;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.levo.ex02.interfaces.IQuote;

public class Main {

	public static void main(String[] args) {
		String workingDirectory = System.getProperty("user.dir");
		System.out.println("Working Directory = " + workingDirectory);
		
		URL url;
		
		try {
			url = new URL("file:///" + workingDirectory + "\\lib2\\Implementations.jar");
			URLClassLoader ucl = new URLClassLoader(new URL[]{url});				//can obtain multiple URL's inside a list
			Class clazz = ucl.loadClass("com.levo.ex02.implementations.Quote");		// Load class
			IQuote o = (IQuote) clazz.newInstance();								// Instantiate the class object
			System.out.println(o.getQuote());										// Print toString() of the class
		} catch(MalformedURLException e) {		// URL Exception
			e.printStackTrace();
		} catch(ClassNotFoundException e) {		// Class Not Found Exception
			e.printStackTrace();
		} catch (InstantiationException e) {	// Instantiation Exception
			e.printStackTrace();
		} catch (IllegalAccessException e) {	// Access Exception
			e.printStackTrace();
		}
	}

}
