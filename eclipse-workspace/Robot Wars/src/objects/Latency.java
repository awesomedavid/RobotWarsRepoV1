package objects;

import values.CoreValues;

public class Latency {

	private long latencyAverage;
	private int latencyCount;
	private long latencyLast;
	private long latencyRecent;
	
	public void addLatency(long amount)
	{
		if(latencyCount == 0)
		{
			latencyAverage = amount;
			latencyLast = amount;
			latencyRecent = amount;
			latencyCount++;
		}
		else
		{
			latencyAverage = ((latencyAverage * latencyCount) + amount) / (latencyCount + 1);
			latencyRecent = ((latencyRecent * (CoreValues.FRAMES_PER_SECOND-1)) + amount) /  CoreValues.FRAMES_PER_SECOND;
			latencyLast = amount;
			latencyCount++;
		}
	}
	
	public long getAverageLatency()
	{
		return latencyAverage;
	}
	
	public long getRecentLatency()
	{
		if(latencyCount >1)
		{
			return latencyRecent;	
		}
		else
		{
			return 0;
		}
	}
	
	public long getLatency()
	{
		return latencyLast;	
	}
	
}
