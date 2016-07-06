package com.levo.ex04_class_for_name;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.levo.ex02.interfaces.IQuote;

public class MainTwoVersions {

	public static void main(String[] args) {
		String workingDirectory = System.getProperty("user.dir");
//		System.out.println("Working Directory = " + workingDirectory);
		
		try {
			URL url1 = new URL("file:///" + workingDirectory + "\\lib2\\Implementations.jar");
			URLClassLoader ucl1 = new URLClassLoader(new URL[]{url1});
			Class clazz1 = Class.forName("com.levo.ex02.implementations.Quote", true, ucl1);
			IQuote quote1 = (IQuote) clazz1.newInstance();
			
			URL url2 = new URL("file:///" + workingDirectory + "\\lib2\\ImplementationsV2.jar");
			URLClassLoader ucl2 = new URLClassLoader(new URL[]{url2});
			Class clazz2 = Class.forName("com.levo.ex02.implementations.Quote", true, ucl2);
			IQuote quote2 = (IQuote) clazz2.newInstance();
			
			System.out.printf("clazz1 == clazz2 ? \t\t%b\n", clazz1 == clazz2);
			System.out.printf("quote1.class == quote2.class ? \t%b\n", quote1.getClass() == quote2.getClass());
			System.out.printf("clazz1 : %d , clazz2 : %d\n", clazz1.hashCode(), clazz2.hashCode());
			
			System.out.println("Quote #1 : " + quote1.getQuote());
			System.out.println("Quote #2 : " + quote2.getQuote());
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
