package com.levo.ex05_factories;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import com.levo.ex05_factories.interfaces.ICamera;
import com.levo.ex05_factories.interfaces.ICameraFactory;

public class ConfigurationMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		// you can parametrize the loadConfiguration parameter
		Configuration conf = Configuration.loadConfiguration(".//ex05conf//config.json");
		
		String location		= conf.getLocation();
		String factoryType	= conf.getFactoryType();
		
		URL url = new URL(location);
		URLClassLoader ucl = new URLClassLoader(new URL[]{url});
		
		// We will get sub-classes of ICameraFactory
		Class<ICameraFactory> cls = (Class<ICameraFactory>) Class.forName(factoryType, true, ucl);
		ICameraFactory cameraFactory = cls.newInstance();
		
		ICamera camera = cameraFactory.createCamera();
		camera.takePhoto();
	}

}
