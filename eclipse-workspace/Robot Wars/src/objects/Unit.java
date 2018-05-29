package objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

import actions.Action;
import actions.Chop;
import actions.Mine;
import actions.Move;
import actions.Pickup;
import actions.Reload;
import actions.Stop;
import actions.build.Build;
import actions.build.BuildFloor;
import actions.build.BuildFloorStone;
import actions.build.BuildFloorWood;
import actions.build.BuildWall;
import actions.build.BuildWallStone;
import actions.build.BuildWallWood;
import actions.use.ranged.UseLaser;
import core.Game;
import core.Utility;
import effects.Effect;
import effects.Stun;
import files.Fonts;
import files.Images;
import item.Item;
import item.weapon.Weapon;
import values.ActionValues;
import values.CoreValues;
import values.DisplayValues;
import values.RobotValues;
import values.Settings;
import world.World;
import world.cells.Cell;
import world.cells.feature.Feature;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;
import world.cells.feature.WallStone;
import world.cells.terrain.FloorStone;
import world.cells.terrain.Terrain;
import world.cells.terrain.WaterDeep;
import world.cells.terrain.WaterShallow;

public abstract class Unit extends GameObject implements ActionValues
{
	private Inventory items;
	private Action myAction;
	private float curHeat;
	private float maxHeat;
	private float coolRate;
	private int processerLevel;
	private boolean canMine;
	private boolean canChop;
	private float baseAccuracy;
	private float mineSkill = 0;
	private float chopSkill = 0;
	protected float moveSpeed;
	private Latency latency;
	
	public Unit(int x, int y, int teamID) throws SlickException {
		super(x, y, teamID);
		sheet = Images.robotDefault;
		setImage(sheet.getSprite(0, 0));
		latency = new Latency();
		getCell().addUnit(this);
		items = new Inventory(this);
		maxHeat = RobotValues.ROBOT_HEAT_BASE;
		curHeat = 0;
		coolRate = RobotValues.ROBOT_COOL_RATE_BASE;
		processerLevel = 1;

	}
	
	public void setImage(Image i)
	{
		image = i.copy();
		image.setImageColor(getTeam().getColor().getRed()/255.0f, 
							getTeam().getColor().getGreen()/255.0f, 
							getTeam().getColor().getBlue()/255.0f);
	}
	
	public Cell look()
	{
		return look(getDirection());
	}
	
	public Cell look(Direction d)
	{
		int x = getX();
		int y = getY();
		
		if(d == Direction.WEST)
		{
			x = getX() - 1;
		}
		else if(d == Direction.NORTH)
		{
			y = getY() - 1;
		}
		else if(d == Direction.EAST)
		{
			x = getX() + 1;
		}		
		else if(d == Direction.SOUTH)
		{
			y = getY() + 1;
		}
		
		if(World.inBounds(x, y))
		{
			return World.getCell(x, y);
		}
		else
		{
			return null;
		}
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(Point p)
	{
		setLocation(p.getX(), p.getY());
	}
	
	
	public float getCurHeat()
	{
		return curHeat;
	}
	
	public float getMaxHeat()
	{
		return maxHeat;
	}
	
	public float getCoolRate()
	{
		return coolRate;
	}
	
	public boolean isOverheated()
	{
		return curHeat > maxHeat;
	}
	
	public void addHeat(float amount)
	{
		curHeat += amount;
	}
	
	public Inventory getInventory()
	{
		return items;
	}
	
	public int getProcessers()
	{
		return processerLevel;
	}

	public void reduceHeat(float amount)
	{
		curHeat = Math.max(0, curHeat - amount);
	}
	
	public boolean hasAction()
	{
		return myAction != null;
	}
	
	public Action getAction()
	{
		return myAction;
	}
	
	public boolean readyToAct()
	{
//		System.out.println("    " + isAlive());
//		System.out.println("    " +  !isOverheated());
//		System.out.println( "    " +  !hasAction());
//		System.out.println("    " +   !hasEffect(Stun.class));

		return isAlive() && !isOverheated() && !hasAction() && !hasEffect(Stun.class);
	}
		
	public void clearAction()
	{
		if(myAction != null)
		{
			myAction.cancel();
		}
		myAction = null;
	}
	
	public float getAccuracy()
	{
		return baseAccuracy;
	}
	
	public boolean hasItem()
	{
		return !items.isEmpty();
	}
	
	public boolean hasPrimary()
	{
		return items.hasPrimary();
	}
	
	public boolean hasSecondary()
	{
		return items.hasSecondary();
	}
	
	public Weapon getPrimary()
	{
		return items.getPrimary();
	}
	
	public Weapon getSecondary()
	{
		return items.getSecondary();
	}	
	
	public void usePrimary()
	{
		getPrimary().use();
	}
	
	public void usePrimary(GameObject o)
	{
		getPrimary().use(o);
	}
	
	public void usePrimary(Point p)
	{
		getPrimary().use(p);
	}
	
	public void useSecondary()
	{
		getSecondary().use();
	}
	
	public void useSecondary(GameObject o)
	{
		getSecondary().use(o);
	}
	
	public void useSecondary(Point p)
	{
		getSecondary().use(p);
	}
	
	public boolean canEnter(int x, int y)
	{
		return World.canEnter(x,  y, this);
	}
	
	public boolean canEnter(Cell t)
	{
		return World.canEnter(t.getX(), t.getY(), this);
	}

	public void increaseDodge(float amount)
	{
		dodge = Math.min(dodge + amount, RobotValues.MAXIMUM_DODGE_CAP);	
	}
	
	public void decreaseDodge(float amount)
	{
		dodge = Math.min(dodge - amount, 0);	
	}	
	
	public void increaseBaseAccuracy(float amount)
	{
		baseAccuracy = Math.min(baseAccuracy + amount, RobotValues.MAXIMUM_ACCURACY_CAP);	
	}
	
	public void decreaseBaseAccuracy(float amount)
	{
		baseAccuracy = Math.max(baseAccuracy - amount, 0);	
	}
	
	public void setBaseSpeed(float amount)
	{
		moveSpeed = amount;
	}
	
	public void increaseBaseSpeed(float amount)
	{	
		moveSpeed += amount;
	}
	public void decreaseBaseSpeed(float amount)
	{
		moveSpeed -= amount;
	}
	
	public void increaseProcessorLevel(int amount)
	{
		processerLevel = Math.min(processerLevel + amount, RobotValues.MAXIMUM_PROCESSERS);	
	}
	
	public void decreaseProcessorLevel(int amount)
	{
		processerLevel = Math.max(processerLevel - amount, 0);	
	}
	

	public void increaseMaxHealth(int amount)
	{
		maxHealth = Math.min(maxHealth + amount, RobotValues.MAXIMUM_SHIELD_CAP);	
		curHealth = Math.min(curHealth + amount, RobotValues.MAXIMUM_SHIELD_CAP);	
	}
	public void decreaseMaxHealth(int amount)
	{
		maxHealth = Math.max(maxHealth - amount, 0);	
		curHealth = Math.max(curHealth - amount, 0);	
	}
	
	public void increaseMaxPlating(int amount)
	{
		maxPlating = Math.min(maxPlating + amount, RobotValues.MAXIMUM_PLATING_CAP);	
		curPlating = Math.min(curPlating + amount, RobotValues.MAXIMUM_PLATING_CAP);	
	}
	
	public void decreaseMaxPlating(int amount)
	{
		maxPlating = Math.max(maxPlating - amount, 0);	
		curPlating = Math.max(curPlating - amount, 0);	
	}
	
	public void increaseMaxShield(int amount)
	{
		maxShield = Math.min(maxShield + amount, RobotValues.MAXIMUM_SHIELD_CAP);	
		curShield = Math.min(curShield + amount, RobotValues.MAXIMUM_SHIELD_CAP);	
	}
	
	public void decreaseMaxShield(int amount)
	{
		maxShield = Math.max(maxShield - amount, 0);	
		curShield = Math.max(curShield - amount, 0);	
	}
	
	
	public void increaseShieldRegen(int amount)
	{
		shieldRegenAmount += amount;	
		
	}
	
	public void decreaseShieldRegen(int amount)
	{
		shieldRegenAmount -= amount;	
		
	}
	
	public void increaseCoolRate(float amount)
	{
		coolRate += amount;	
	}
	
	public void decreaseCoolRate(float amount)
	{
		coolRate -= amount;	
	}

	public boolean inRange(GameObject target, Weapon w)
	{
		return inRange(target.getPoint(), w);
	}
	
	public boolean inRange(Cell target, Weapon w)
	{
		return inRange(target.getPoint(), w);
	}
	
	public boolean inRange(Point target, Weapon w)
	{
		return Utility.distance(x, y, target.getX(), target.getY()) <= w.getMaxRange() && 
			   Utility.distance(x, y, target.getX(), target.getY()) >= w.getMinRange();
	}

	public boolean inZone()
	{
		return getCell().hasZone();
	}
	
	public boolean inZoneFriendly()
	{
		return inZone() && getCell().getZone().getOwner() == this.getTeam();
	}
	
	public boolean inZoneNeutral()
	{
		return inZone() && getCell().getZone().isNeutral();
	}
	
	public boolean inZoneHostile()
	{
		return inZone() && !inZoneNeutral() && getCell().getZone().getOwner() != this.getTeam();
	}
	
	/******************************** UNIT DETECTION *********************************/
	
	
	public int getDistance(GameObject o)
	{
		return Utility.distance(this, o);
	}
	
	public int getDistance(Point p)
	{
		return Utility.distance(this.getPoint(), p);
	}
	
	public int getDistance(Cell c)
	{
		return Utility.distance(this.getPoint(), c.getPoint());
	}
	
	
	/******************************** ENEMIES *****************************************/
	
	// Gets all enemies
	final public ArrayList<Unit> getEnemies() {
		return getEnemies(Unit.class);
	}
	
	// Gets all enemies of a given class
	final public ArrayList<Unit> getEnemies(Class<? extends Unit> clazz) {

		ArrayList<Unit> allEnemies = new ArrayList<Unit>();
		for (Unit a : Game.getUnits()) {
			if (clazz.isInstance(a) && a.getTeam() != getTeam() && a.isAlive()) {
				allEnemies.add(a);
			}
		}
		return allEnemies;
	}
	
	// Gets the nearest enemy
	final public Unit getNearestEnemy() {
		return getNearestEnemy(Unit.class);
	}

	// Gets the nearest enemy of a given class
	final public Unit getNearestEnemy(Class<? extends Unit> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getEnemies(clazz)) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}
	
	// Gets the nearest enemy with a given weapon
	final public Unit getNearestEnemyWithWeapon(Class<? extends Weapon> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getEnemies()) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance)
			{
				if(a.hasPrimary() && clazz.isInstance(a.getPrimary()) ||
				   a.hasSecondary() && clazz.isInstance(a.getSecondary()))
				{	
					nearestDistance = d;
					nearestTarget = a;
				}
			}
		}
		return nearestTarget;
	}


	// Gets the nearest enemy unless it belongs to a given class
	final public Unit nearestEnemyExclude(Class<? extends Unit> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getEnemies(clazz)) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}

		return nearestTarget;
	}

	// Count the enemies within a given radius
	final public int countEnemiesInRadius(float radius) {
		return getEnemiesInRadius(radius, Unit.class).size();
	}

	// Gets the enemies in a given radius
	final public ArrayList<Unit> getEnemiesInRadius(float radius) {
		return getEnemiesInRadius(radius, Unit.class);
	}

	// Gets the enemies in a given radius of a given type
	final public ArrayList<Unit> getEnemiesInRadius(float radius, Class<? extends Unit> clazz) {
		ArrayList<Unit> radiusEnemies = new ArrayList<Unit>();
		
		for (Unit e : getEnemies(clazz)) {
			if (Utility.distance(this, e) <= radius) {
				radiusEnemies.add(e);
			}
		}
		return radiusEnemies;
	}
	
	/******************************** ALLIES *****************************************/
	
	// Gets all allies
	final public ArrayList<Unit> getAllies() {
		return getAllies(Unit.class);
	}
	
	// Gets all allies of a given class
	final public ArrayList<Unit> getAllies(Class<? extends Unit> clazz) {

		ArrayList<Unit> allAllies = new ArrayList<Unit>();
		for (Unit a : Game.getUnits()) {
			if (a != this && clazz.isInstance(a) && a.getTeam() == getTeam() && a.isAlive()) {
				allAllies.add(a);
			}
		}
		return allAllies;
	}
	
	// Gets the nearest ally
	final public Unit getNearestAlly() {
		return getNearestAlly(Unit.class);
	}

	// Gets the nearest ally of a given class
	final public Unit getNearestAlly(Class<? extends Unit> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getAllies(clazz)) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}
		return nearestTarget;
	}
	
	// Gets the nearest ally with a given weapon
	final public Unit getNearestAllyWithWeapon(Class<? extends Weapon> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getAllies()) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance)
			{
				if(a.hasPrimary() && clazz.isInstance(a.getPrimary()) ||
				   a.hasSecondary() && clazz.isInstance(a.getSecondary()))
				{	
					nearestDistance = d;
					nearestTarget = a;
				}
			}
		}
		return nearestTarget;
	}


	// Gets the nearest ally unless it belongs to a given class
	final public Unit nearestAllyExclude(Class<? extends Unit> clazz) {

		float nearestDistance = Float.MAX_VALUE;
		Unit nearestTarget = null;

		for (Unit a : getAllies(clazz)) {
			float d = Utility.distance(this, a);

			if (d < nearestDistance) {
				nearestDistance = d;
				nearestTarget = a;
			}
		}

		return nearestTarget;
	}

	// Count the allies within a given radius
	final public int countAlliesInRadius(float radius) {
		return getAlliesInRadius(radius, Unit.class).size();
	}

	// Gets the allies in a given radius
	final public ArrayList<Unit> getAlliesInRadius(float radius) {
		return getEnemiesInRadius(radius, Unit.class);
	}

	// Gets the allies in a given radius of a given type
	final public ArrayList<Unit> getAlliesInRadius(float radius, Class<? extends Unit> clazz) {
		ArrayList<Unit> radiusAllies = new ArrayList<Unit>();
		
		for (Unit e : getAllies(clazz)) {
			if (Utility.distance(this, e) <= radius) {
				radiusAllies.add(e);
			}
		}
		return radiusAllies;
	}
	
	
	
	/******************************** SKILLS ******************************************/
	
	public void enableMine()
	{
		canMine = true;
		mineSkill = BASE_MINE_SKILL;
	}
	
	public void enableChop()
	{
		canChop = true;
		chopSkill = BASE_CHOP_SKILL;
	}
	
	public void disableMine()
	{
		canMine = false;
		mineSkill = 0;
	}
	
	public void disableChop()
	{
		canChop = false;
		chopSkill = 0;
	}
	
	
	public boolean canMine()
	{
		return canMine;
	}
	
	public boolean canChop()
	{
		return canChop;
	}
	
	public float getMiningSkill()
	{
		return mineSkill;
	}
	
	public float getChopSkill()
	{
		return chopSkill;
	}
	
	public float getMoveSpeed()
	{
		return moveSpeed;
	}
	
	/******************************** ACTIONS ******************************************/
	
	public void setAction(Action a)
	{
		if(readyToAct())
		{
			myAction = a;
		}
	}
	
	
	public void stop()
	{		
		if(readyToAct())
		{
			setAction(new Stop(this));		
		}
	}
	
	final public void move()
	{		
		if(readyToAct() && canEnter(look()))
		{
			setAction(new Move(this));		
		}
	}
	
	final public void mine()
	{
		Cell next = look();
		
		if(readyToAct() && canMine() && next != null && next.hasFeature() && next.getFeature() instanceof Mountain)
		{
			setAction(new Mine(this, (Mountain) next.getFeature()));		
		}
	}
	
	final public void chop()
	{
		Cell next = look();
		
		if(readyToAct() && canChop() && next != null && next.hasFeature() && next.getFeature() instanceof Tree)
		{
			setAction(new Chop(this, (Tree) next.getFeature()));		
		}
	}
	
	final public void pickup()
	{
		if(readyToAct() && getCell().hasItem())
		{
			setAction(new Pickup(this));
		}
	}
	
	final public void build(Class<?> clazz)
	{
		if(WallStone.class.isAssignableFrom(clazz))
		{
			buildWallStone();
		}
		else if(FloorStone.class.isAssignableFrom(clazz))
		{
			buildFloorStone();
		}			
	}
	
	final public void reload(Weapon w)
	{
		if(readyToAct() && w.usesAmmo())
		{
			setAction(new Reload(this, w));
		}
	}	
	
	final public void buildWallStone()
	{
		if(readyToAct() && getTeam().hasStone(BUILD_WALL_STONE_COST) && !getCell().hasFeature() && !getCell().hasItem())
		{
			setAction(new BuildWallStone(this));	
			getTeam().loseStone(BUILD_WALL_STONE_COST);
		}
	}
	
	final public void buildWallWood()
	{
		if(readyToAct() && getTeam().hasWood(BUILD_WALL_WOOD_COST) && !getCell().hasFeature() && !getCell().hasItem())
		{
			setAction(new BuildWallWood(this));	
			getTeam().loseWood(BUILD_WALL_WOOD_COST);
		}
	}
	
	final public void buildFloorStone()
	{
		if(readyToAct() && getTeam().hasStone(BUILD_FLOOR_STONE_COST) && !getCell().hasFeature())
		{
			setAction(new BuildFloorStone(this));
			getTeam().loseStone(BUILD_FLOOR_STONE_COST);
		}
	}
	
	final public void buildFloorWood()
	{
		if(readyToAct() && getTeam().hasWood(BUILD_FLOOR_WOOD_COST) && !getCell().hasFeature())
		{
			setAction(new BuildFloorWood(this));
			getTeam().loseWood(BUILD_FLOOR_WOOD_COST);
		}
	}
	
	abstract public void action();
	
	final public long getRecentLatency()
	{
		return latency.getRecentLatency();
	}
	final public void addLatency(long amount)
	{
		latency.addLatency(amount);
	}
	
	final public int getOverclockLevel()
	{
		return RobotValues.OVERCLOCK_LEVEL_PER_PROCESSOR * processerLevel;
	}
	
	public void update()
	{
		super.update();		
		
		
		if(isAlive())
		{
			if(getCell().getTerrain() instanceof WaterShallow || getCell().getTerrain() instanceof WaterDeep)
			{
				reduceHeat(coolRate * 2);
			}
			else
			{
				reduceHeat(coolRate);
			}
		}
		if(getRecentLatency() > RobotValues.OVERCLOCK_LEVEL_PER_PROCESSOR * processerLevel)
		{
			addHeat(RobotValues.ROBOT_OVERCLOCK_RATE / processerLevel);
		}

		
		items.update();
		
		// If I have no action queued and I am alive, subclass defines behavior
		if(readyToAct())
		{
			
			action();
		}
		
		// Do nothing if stunned
		else if(hasEffect(Stun.class))
		{
		
		}
		
		// Set stunned if overheated
		else if(isOverheated())
		{
			float excessHeatDissipateTime = (getCurHeat() - getMaxHeat()) / getCoolRate();
			float fullHeatDissipateTime = (getMaxHeat() / getCoolRate());
			int stunTime = (int) (excessHeatDissipateTime + fullHeatDissipateTime);
			//System.out.println(stunTime);
			addEffect(new Stun(this, this, 0, stunTime));
		}
		
		else if(myAction == null)
		{
			// No action, but stunned or something - do nothing
		}
		
		// If I'm interrupted or finished, clear out my action
		else if(myAction.isCancelled() || !isAlive())
		{
			clearAction();
		}
		
		// Otherwise, continue current action
		else
		{
			
			myAction.update();
			
			// this fixes movement, but it's causing units to sometimes fire after death.... 
			if(myAction.isComplete())
			{
				clearAction();
			}
		}
			
	}
	
	public void die()
	{
		super.die();
		clearAction();
		curHeat = 0;
	}
	

	
	final public int getXPixel()
	{
		return x * CoreValues.CELL_SIZE + getMovingOffset().getX();
	}

	final public int getYPixel()
	{
		return y * CoreValues.CELL_SIZE + getMovingOffset().getY();
	}
	
	
	final public Point getMovingOffset()
	{
		int xOff = 0;
		int yOff = 0;
		
		if(myAction instanceof Move)
		{
			if(facing(Direction.WEST))
			{
				xOff = (int) (-CoreValues.CELL_SIZE * myAction.getPercentComplete());
			}
			else if(facing(Direction.NORTH))
			{
				yOff = (int) (-CoreValues.CELL_SIZE  * myAction.getPercentComplete());
			}
			else if(facing(Direction.EAST))
			{
				xOff = (int) (CoreValues.CELL_SIZE  * myAction.getPercentComplete());
			}
			else if(facing(Direction.SOUTH))
			{
				yOff = (int) (CoreValues.CELL_SIZE * myAction.getPercentComplete());
			}

		}

		return new Point(xOff, yOff);
		
	}
	
	abstract public void display(Graphics g);

	final public void render(Graphics g)
	{		
		if(Settings.showPlayerDisplay)
		{
			display(g);
		}

		if (isAlive() && image != null) 
		{
			if(theta > 360)
			{
				theta %= 360;
			}
			
			
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
			
			int	step = 0;
			
			if(Math.abs(xOff) > 0)
			{
				step = (int) (Math.abs(xOff) / (float) CoreValues.CELL_SIZE * 3.0);
			}
			else if(Math.abs(yOff) > 0)
			{
				step = (int) (Math.abs(yOff) / (float) CoreValues.CELL_SIZE * 3.0);
			}
			
			
			setImage(sheet.getSprite((int) (theta / 90), (int) step));

			
			image.draw(x * CoreValues.CELL_SIZE + xOff, y * CoreValues.CELL_SIZE + yOff - CoreValues.CELL_SIZE);
				
			renderHealthbars(g);
			renderAction(g);
			renderHealthValues(g);			
			renderOverheat(g);
			
			if(hasAction())
			{
				getAction().render(g);
			}
		}	
		

	}
	
	final public void renderOverheat(Graphics g)
	{
		if(hasEffect(Stun.class))
		{
			float xOff = getMovingOffset().getX() + CELL_SIZE / 2;
			float yOff = getMovingOffset().getY() + CELL_SIZE / 2;
			
			g.setFont(Fonts.arialblack28);
			g.setColor(Color.black);
			String message = "!";
			
			int w = Fonts.arialblack28.getWidth(message);
			int h = Fonts.arialblack28.getHeight(message);

			g.drawString(message, x*CELL_SIZE+xOff+2-w/2, y*CELL_SIZE-CELL_SIZE*2+yOff+2-h/2);
			g.setColor(new Color(255, 100, 0));
			g.drawString(message, x*CELL_SIZE+xOff-w/2, y*CELL_SIZE-CELL_SIZE*2+yOff-h/2);
		}
	}
	
	final public void renderHealthValues(Graphics g)
	{ 
		if(Settings.showHealthValues)
		{
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
			g.setFont(Fonts.calibri12Bold);
			g.setColor(DisplayValues.COLOR_SHIELD);
			g.drawString(""+(int)getCurShield(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff);
			g.setColor(DisplayValues.COLOR_PLATING);
			g.drawString(""+(int)getCurPlating(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff+12);
			g.setColor(DisplayValues.COLOR_HEALTH);
			g.drawString(""+(int)getCurHealth(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff+24);
		}
	}
	
	final public void renderAction(Graphics g)
	{
		if(Settings.showAction)
		{
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
			g.setFont(Fonts.calibri12Bold);
			g.setColor(Color.white);
			g.drawString(""+getAction(), x*CELL_SIZE+xOff, y*CELL_SIZE+32+yOff);
		}
	}
	
	final public void renderHealthbars(Graphics g)
	{	
		if(Settings.showHealthbars)
		{
			float xOff = getMovingOffset().getX();
			float yOff = getMovingOffset().getY();
			
		final int WIDTH = CELL_SIZE - 2;
			
		
	
		int hWidthMax = Math.round((getMaxHealth() / getMaxDurability() * WIDTH));
		int hWidthCur = Math.round((getCurHealth() / getMaxHealth() * hWidthMax));
		
		int pWidthMax = Math.round((getMaxPlating() / getMaxDurability() * WIDTH));
		int pWidthCur = Math.round((getCurPlating() / getMaxPlating() * pWidthMax));
		
		int sWidthMax = Math.round((getMaxShield() / getMaxDurability() * WIDTH));
		int sWidthCur = Math.round((getCurShield() / getMaxShield() * sWidthMax));

		
		renderBar(g, 0, WIDTH, new Color(40, 40, 40));
		
		
			if(curHealth > 0)
			{
				//renderBar(g, hWidth, DisplayValues.COLOR_HEALTH.darker(.40f));
				renderBar(g, 0, hWidthCur,  DisplayValues.COLOR_HEALTH);
			}
			
			// Plating 
			if(curPlating > 0)
			{
				//renderBar(g, CELL_SIZE-2,  DisplayValues.COLOR_PLATING.darker(.40f));
				renderBar(g, hWidthCur, pWidthCur,  DisplayValues.COLOR_PLATING);
			}
//			
//			// Shield 
			if(curShield > 0)
			{
				//renderBar(g, CELL_SIZE-2,  DisplayValues.COLOR_SHIELD.darker(.40f));
				renderBar(g, hWidthCur + pWidthCur, sWidthCur,  DisplayValues.COLOR_SHIELD);
			}
		}
	}
	
	final private void renderBar(Graphics g, int indent, int width, Color c)
	{
		g.setColor(c);
		g.fillRect(getXPixel()+1 + indent, getYPixel() - 5 - CoreValues.CELL_SIZE, width, 5);
		g.setColor(Color.black);
		g.drawRect(getXPixel()+1 + indent, getYPixel() - 5 - CoreValues.CELL_SIZE, width, 5);
	}

}