package com.levo.ex05_factories.implementations.X;

import com.levo.ex05_factories.interfaces.ICamera;
import com.levo.ex05_factories.interfaces.ICameraFactory;

public class NikonCameraFactory implements ICameraFactory {

	@Override
	public ICamera createCamera() {
		return new NikonCamera();
	}

}
