package objects;

import org.newdawn.slick.SlickException;

import actions.Move;
import actions.Scrap;
import actions.Upgrade;
import core.Utility;
import files.Images;
import item.Metal;
import item.equipment.Equipment;
import values.RobotValues;
import world.World;
import world.cells.Cell;


public abstract class Robot extends Unit implements RobotValues
{		
	private String name;
	private int myID;
	private Frame frame;
	private int respawnTimer;
	private int respawnDuration;
	private int spawnX;
	private int spawnY;
	
	public Robot(int x, int y, int teamId, int myID) throws SlickException
	{
		super(x, y, teamId);
		this.myID = myID;
		spawnX = x;
		spawnY = y;
		frame = Frame.NONE;
		name = "Robot" + myID;
		respawnDuration = RESPAWN_TIME_BASE;
		turnRandom();
	}
	

	// This method is used to upgrade your robot.  Instant speed at start of program.
	public void buy(Equipment e)
	{
		if(getTeam().hasMetal(e.getCost()) && getTimer() == 0)
		//if(getTeam().hasMetal(e.getCost()) && getInventory().canCarry(e) && getTimer() == 0)
		{
			getInventory().add(e);
			getTeam().loseMetal(e.getCost());
		}
		else if(readyToAct() && getTeam().hasMetal(e.getCost()))
	//	else if(readyToAct() && getTeam().hasMetal(e.getCost()) && getInventory().canCarry(e))
		{
			setAction(new Upgrade(this, e));		
		}
	}
	
	// This method is used to scrap a piece of gear - as an action
	// Refunds 50% the cost, unless it's a consumable item
	public void scrap(Equipment e)
	{		
		if(readyToAct())
		{
			setAction(new Scrap(this, e));		
		}
	}
	
	
	
	public int getWeightCapacity()
	{
		return getInventory().getWeightCapacity();
	}
	
	public int getWeightCapacityFullyEncumbered()
	{
		return getInventory().getWeightCapacityFullyEncumbered();
	}

	public int getWeightCarried()
	{
		return getInventory().getWeightCarried();
	}
	
	public int getWeightCapacityLeft()
	{
		return getInventory().getWeightCapacityLeft();
	}
	
	public int getWeightCapacityLeftFullyEncumbered()
	{
		return getInventory().getWeightCapacityLeftFullyEncumbered();
	}
	
	public boolean readyToAct()
	{
		return super.readyToAct() && frame != Frame.NONE;
	}
	
	
	public String getName()
	{
		return name;
	}
		
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Frame getFrame()
	{
		return frame;
	}
	
	public void setFrame(Frame type)
	{
		if(frame != Frame.NONE)
		{
			return;
		}

		frame = type;
		
		if(frame == Frame.LIGHT)
		{
			getInventory().increaseMaxWeight(FRAME_LIGHT_WEIGHT_MAX);
			setBaseSpeed(FRAME_LIGHT_SPEED_BASE);
			increaseMaxHealth(FRAME_LIGHT_HEALTH_BASE);
		}
		else if(frame == Frame.MEDIUM)
		{
			getInventory().increaseMaxWeight(FRAME_MEDIUM_WEIGHT_MAX);
			setBaseSpeed(FRAME_MEDIUM_SPEED_BASE);
			increaseMaxHealth(FRAME_MEDIUM_HEALTH_BASE);
		}
		else if(frame == Frame.HEAVY)
		{
			getInventory().increaseMaxWeight(FRAME_HEAVY_WEIGHT_MAX);
			setBaseSpeed(FRAME_HEAVY_SPEED_BASE);
			increaseMaxHealth(FRAME_HEAVY_HEALTH_BASE);
		}
		 
	}

	public int getID()
	{
		return myID;
	}
	
	final public void update()
	{
		super.update();
		
		respawn();
	}
	
	final public void die()
	{
		super.die();
		onDeath();
		
		if(!isAlive() && respawnTimer == 0)
		{
			getCell().addItem(new Metal(getTeam().getColor()));
		}
	}
	
	public float getMoveSpeed()
	{
		return Math.max(moveSpeed - getInventory().getWeightSpeedPenalty(), 0);
	}
	
	// This method is called when a robot dies
	abstract public void onDeath();
		
	public void respawn()
	{
		if(!isAlive())
		{
			respawnTimer++;
		}
		
		if(respawnTimer == respawnDuration)
		{
			if(hasPrimary() && getPrimary().canReload())
			{
				getPrimary().reloadActionComplete();
			}
			if(hasSecondary() && getSecondary().canReload())
			{
				getSecondary().reloadActionComplete();
			}
			curHealth = maxHealth;
			curShield = maxShield;
			curPlating = maxPlating;
			isAlive = true;
			this.x = spawnX;
			this.y = spawnY;
			World.getCell(x, y).addUnit(this);
			respawnTimer = 0;
			respawnDuration *= RESPAWN_TIME_PENALTY_PER_DEATH;
		}
	}
	
	public int respawnTimeLeft()
	{
		return respawnDuration - respawnTimer;
	}
	
	public int respawnTime()
	{
		return respawnDuration;
	}
	
	/******** Direction ************/
	
	public void turnLeft()
	{
		if(facingEast())
		{
			turnNorth();
		}
		else if(facingNorth())
		{
			turnWest();
		}
		else if(facingWest())
		{
			turnSouth();
		}
		else if(facingSouth())
		{
			turnEast();
		}
	}
	
	public void turnRight()
	{
		if(facingWest())
		{
			turnNorth();
		}
		else if(facingSouth())
		{
			turnWest();
		}
		else if(facingEast())
		{
			turnSouth();
		}
		else if(facingNorth())
		{
			turnEast();
		}
	}
	
	public void turnAround()
	{
		if(facingWest())
		{
			turnEast();
		}
		else if(facingSouth())
		{
			turnNorth();
		}
		else if(facingEast())
		{
			turnWest();
		}
		else if(facingNorth())
		{
			turnSouth();
		}
	}
	
	
	public void turnRandom()
	{	
		int r = Utility.random(4);
		
		switch(r)
		{
		case 0:
			turnNorth();
			break;
		case 1:
			turnWest();
			break;
		case 2:
			turnSouth();
			break;
		case 3:
			turnEast();
			break;
		}
	}
		
	
	public boolean frontIsClear()
	{
		if(getTileFront() != null)
		{
			return canEnter(getTileFront().getX(), getTileFront().getY());
		}
		else
		{
			return false;
		}
	}
	
	public boolean hasTileFront()
	{
		if(getDirection() == Direction.WEST)
		{
			return getTileWest() != null;
		}
		else if(getDirection()  == Direction.NORTH)
		{
			return getTileNorth() != null;
		}
		else if(getDirection() == Direction.EAST)
		{
			return getTileEast() != null;
		}
		else if(getDirection()  == Direction.SOUTH)
		{
			return getTileSouth() != null;		}
		else
		{
			return false;
		}
	}
	
	public Cell getTileFront()
	{
		if(getDirection() == Direction.WEST)
		{
			return getTileWest();
		}
		else if(getDirection()  == Direction.NORTH)
		{
			return getTileNorth();
		}
		else if(getDirection() == Direction.EAST)
		{
			return getTileEast();
		}
		else if(getDirection()  == Direction.SOUTH)
		{
			return getTileSouth();
		}
		else
		{
			return null;
		}
	}
	
	public Cell getTileWest()
	{
		return World.getCell(getX()-1, getY());
	}
	
	public Cell getTileNorth()
	{
		return World.getCell(getX(), getY()-1);
	}
	
	public Cell getTileEast()
	{
		return World.getCell(getX()+1, getY());
	}
	
	public Cell getTileSouth()
	{
		return World.getCell(getX(), getY()+1);
	}

	public boolean facingWest()
	{
		return facing(Direction.WEST);
	}
	
	public boolean facingNorth()
	{
		return facing(Direction.NORTH);
	}
	
	public boolean facingEast()
	{
		return facing(Direction.EAST);
	}
	
	public boolean facingSouth()
	{
		return facing(Direction.SOUTH);
	}
	
	public void turnWest()
	{
		turn(Direction.WEST);
	}
	
	public void turnNorth()
	{
		turn(Direction.NORTH);
	}
	
	public void turnEast()
	{
		turn(Direction.EAST);
	}
	
	public void turnSouth()
	{
		turn(Direction.SOUTH);
	}
	
	public String toString()
	{
		return name;
	}
	
	
	
}
