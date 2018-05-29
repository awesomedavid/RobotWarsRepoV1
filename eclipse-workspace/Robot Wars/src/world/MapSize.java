package world;

public enum MapSize {
	SMALL, MEDIUM, LARGE;
		
	public int getSize()
	{
		if(equals(SMALL))
		{
			return 80;
		}
		if(equals(MEDIUM))
		{
			return 120;
		}
		else if(equals(LARGE))
		{
			return 160;
		}
		else
		{
			return 0;
		}
	}
	
	public float getLatencyModifier()
	{
		if(equals(SMALL))
		{
			return .5f;
		}
		if(equals(MEDIUM))
		{
			return 1f;
		}
		else if(equals(LARGE))
		{
			return 2f;
		}
		else
		{
			return 0;
		}
	}
}
