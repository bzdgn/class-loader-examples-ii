package com.levo.ex05_factories.implementations;

import com.levo.ex05_factories.interfaces.ICamera;
import com.levo.ex05_factories.interfaces.ICameraFactory;

public class CanonCameraFactory implements ICameraFactory {

	@Override
	public ICamera createCamera() {
		return new CanonCamera();
	}

}