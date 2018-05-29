package teams.starter.basic.robots;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import files.Images;
import item.StoneBlock;
import item.equipment.UpgradeHeatSink;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.arc.SecondarySmokeGrenade;
import item.weapon.ranged.PrimaryLaser;
import item.weapon.ranged.PrimaryMachineGun;
import item.weapon.ranged.PrimaryShotgun;
import item.weapon.ranged.PrimarySniper;
import item.weapon.utility.SecondaryRepairKit;
import objects.Direction;
import objects.Frame;
import objects.GameObject;
import objects.Point;
import objects.Robot;
import objects.Unit;
import world.cells.Cell;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class BasicHunter extends BasicRobot 
{
	public BasicHunter(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.MEDIUM);						
		setName("Hunter");						
			
		// 3 / 7 Weight on Weapons
		buy(new PrimaryShotgun(this));		
		buy(new SecondarySmokeGrenade(this));		

		// 4 / 7 Weight on Upgrades
		buy(new UpgradeHeatSink(this));			
		buy(new UpgradeHeatSink(this));			
		buy(new UpgradePlating(this));		
		buy(new UpgradePlating(this));		

	}
	
	public void action()
	{
		Unit target = getNearestEnemy();
		
		smoke(target.getPoint());
		
		shoot(target);
		reloadIfNeeded();
		pickupIfAble();
		spinToWin();
		seek(target);
	}
	
	public void smoke(Point p)
	{
		if(p != null && hasSecondary() && getSecondary().canUse(p))
		{
			getSecondary().use(p);
		}
	}
	

	public void repair()
	{
		if(getCurHealth() < getMaxHealth() * .5 && hasSecondary() && getSecondary().canUse())
		{
			useSecondary();
		}
	}




	
	
}
