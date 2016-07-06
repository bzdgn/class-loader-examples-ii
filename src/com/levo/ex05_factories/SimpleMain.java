package com.levo.ex05_factories;

import com.levo.ex05_factories.implementations.NikonCameraFactory;
import com.levo.ex05_factories.interfaces.ICamera;
import com.levo.ex05_factories.interfaces.ICameraFactory;

public class SimpleMain {

	public static void main(String[] args) {
		ICameraFactory cameraFactory = new NikonCameraFactory();
		ICamera camera = cameraFactory.createCamera();
		
		camera.takePhoto();
	}

}
