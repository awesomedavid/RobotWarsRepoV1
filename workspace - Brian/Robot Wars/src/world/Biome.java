package world;

import core.Utility;
import objects.Direction;

public enum Biome {
	
	LIGHT_FOREST, DENSE_FOREST, MOUNTAINS, HILLS, PLAINS, ARID_SHRUBLAND, DESERT, LAKES;
	
	public static Biome getRandom() {
			return Biome.values()[(Utility.random(Biome.values().length))];
		}
	
	
	public float getFertility()
	{
		if(equals(DENSE_FOREST))
		{
			return .4f;
		}
		else if(equals(LIGHT_FOREST))
		{
			return .2f;
		}
		else if(equals(ARID_SHRUBLAND))
		{
			return -.2f;
		}
		else if(equals(DESERT))
		{
			return -.4f;
		}
		else
		{
			return 0;
		}
		
	}
	
	public float getWaterLevel()
	{
		if(equals(LAKES))
		{
			return .4f;
		}
		else if(equals(ARID_SHRUBLAND))
		{
			return -.2f;
		}
		else if(equals(DESERT))
		{
			return -.4f;
		}
		else
		{
			return 0;
		}

	}
	
	public float getMountain()
	{
		if(equals(MOUNTAINS))
		{
			return .2f;
		}
		if(equals(HILLS))
		{
			return .1f;
		}
		else if(equals(PLAINS))
		{
			return -.1f;
		}
		else
		{
			return 0;
		}
	}
	
	public String toString() {
		String word = super.toString().toLowerCase();
		String first = word.toUpperCase().substring(0, 1);
		word = first + word.substring(1, word.length());

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '_') {
				word = word.substring(0, i) + " " + word.substring(i + 1, i + 2).toUpperCase()
						+ word.substring(i + 2, word.length());
			}
		}

		return word;
	}

	
}


