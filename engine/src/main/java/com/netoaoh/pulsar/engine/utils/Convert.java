/**
 * Project: PulsarGameEngine
 * Filename: Convert.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.utils;

import com.netoaoh.pulsar.engine.math.Vector2f;
import org.jbox2d.common.Vec2;

public class Convert {

	public static Vec2 convertToVec2(Vector2f vector){
		return new Vec2(vector.getX(), vector.getY());
	}
}
