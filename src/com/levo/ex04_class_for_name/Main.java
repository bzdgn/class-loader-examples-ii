package com.levo.ex04_class_for_name;

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
			// Note: you can rename Quote.java to make sure that program retrieves
			//       binary from the jar file located on lib dir
			url = new URL("file:///" + workingDirectory + "\\lib2\\Implementations.jar");
			URLClassLoader ucl = new URLClassLoader(new URL[]{url});				//can obtain multiple URL's inside a list
			
			// instead of using ucl.loadClass() method, using Class.forName() and pass classloader as a param
			Class clazz = Class.forName("com.levo.ex02.implementations.Quote", true, ucl);		// Load class
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
