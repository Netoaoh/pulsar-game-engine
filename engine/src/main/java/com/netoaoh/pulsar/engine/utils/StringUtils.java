/**
 * Project: PulsarGameEngine
 * Filename: StringUtils.java
 * Author: Paulo Maria Neto
 * Created: 13/07/16
 * --------------------------------------------------------------
 * Copyright (c) 2016 - Design Coding, All Rights Reserved.
 */

package com.netoaoh.pulsar.engine.utils;

import java.util.ArrayList;

public class StringUtils {

	public static String[] removeEmptyStrings(String[] data){
		ArrayList<String> result = new ArrayList<String>();

		for(int i = 0; i < data.length; i++)
			if(!data[i].equals(""))
				result.add(data[i]);

		String[] res = new String[result.size()];
		result.toArray(res);

		return res;
	}
}
