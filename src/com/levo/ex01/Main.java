package com.levo.ex01;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

	public static void main(String[] args) {
		String workingDirectory = System.getProperty("user.dir");
		System.out.println("Working Directory = " + workingDirectory);
		
		URL url;
		
		try {
			url = new URL("file:///" + workingDirectory + "\\lib\\quoter.jar");
			URLClassLoader ucl = new URLClassLoader(new URL[]{url});	//can obtain multiple URL's inside a list
			Class clazz = ucl.loadClass("com.levo.Quote");				// Load class
			Object o = clazz.newInstance();								// Instantiate the class object
			System.out.println(o.toString());							// Print toString() of the class
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
