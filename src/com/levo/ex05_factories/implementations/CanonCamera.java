package com.levo.ex05_factories.implementations;

import com.levo.ex05_factories.interfaces.ICamera;

public class CanonCamera implements ICamera {

	@Override
	public void takePhoto() {
		System.out.println("Canon: photo taken");
	}

}
