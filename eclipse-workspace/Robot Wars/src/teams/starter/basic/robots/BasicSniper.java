package teams.starter.basic.robots;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import files.Images;
import item.Item;
import item.StoneBlock;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.ranged.PrimaryLaser;
import item.weapon.ranged.PrimaryMachineGun;
import item.weapon.ranged.PrimarySniper;
import item.weapon.utility.SecondaryRepairKit;
import objects.Frame;
import objects.Robot;
import objects.Unit;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class BasicSniper extends BasicRobot 
{
	final int TURN_TIME = 50;
	int turnTimer = 0;

	public BasicSniper(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.HEAVY);
		setName("Sniper");
		
		// 2 / 9 Weight on Weapons
		buy(new PrimarySniper(this));		

		// 7 / 9 Weight on Upgrades
		buy(new UpgradePlating(this));			
		buy(new UpgradePlating(this));			
		buy(new UpgradePlating(this));			

		buy(new UpgradeShield(this));
		buy(new UpgradeShield(this));
		buy(new UpgradeShield(this));
		buy(new UpgradeShield(this));
	}
	
	public void action()
	{
		Unit target = getNearestEnemy();

		shoot(target);
		reloadIfNeeded();
		pickupIfAble();
		spinToWin();
		wander();
	}

	
}
