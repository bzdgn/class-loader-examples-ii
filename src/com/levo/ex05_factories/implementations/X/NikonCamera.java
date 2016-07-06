package com.levo.ex05_factories.implementations.X;

import com.levo.ex05_factories.interfaces.ICamera;

public class NikonCamera implements ICamera {

	@Override
	public void takePhoto() {
		System.out.println("Nikon: photo taken");
	}

}
