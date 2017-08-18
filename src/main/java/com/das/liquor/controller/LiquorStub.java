package com.das.liquor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.das.liquor.model.Liquor;

public class LiquorStub {
	private static Map<Long, Liquor> typeOfLiquor = new HashMap<Long, Liquor>();
	private static Long idIndex = 3L;

	//populate initial typeOfLiquor
	
	static {
		Liquor a = new Liquor(1L, "100 Pipers", 
				"100 Pipers 12 Year", "Whisky", 48, 12, 1, 1994);
		typeOfLiquor.put(1L, a);
		
		Liquor b = new Liquor(2L, "Black Dog", 
				"Black Dog 12 Year", "Whisky", 48, 12, 1, 1990);
		typeOfLiquor.put(2L, b);

		Liquor c = new Liquor(2L, "Old Monk", 
				"Old Monk Rum", "Rum", 50, 1, 0, 1970);
		typeOfLiquor.put(3L, c);
		
	}

	public static List<Liquor> list() {
		return new ArrayList<Liquor>(typeOfLiquor.values());
	}

	public static Liquor create(Liquor wreck) {
		idIndex += idIndex;
		wreck.setId(idIndex);
		typeOfLiquor.put(idIndex, wreck);
		return wreck;
	}

	public static Liquor get(Long id) {
		return typeOfLiquor.get(id);
	}

	public static Liquor update(Long id, Liquor wreck) {
		typeOfLiquor.put(id, wreck);
		return wreck;
	}

	public static Liquor delete(Long id) {
		return typeOfLiquor.remove(id);
	}
}
