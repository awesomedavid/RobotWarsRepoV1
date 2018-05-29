package teams.starter.basic.robots;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Utility;
import files.Images;
import item.StoneBlock;
import item.equipment.UpgradeAgility;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.ranged.PrimaryLaser;
import item.weapon.ranged.PrimaryMachineGun;
import item.weapon.ranged.PrimarySniper;
import item.weapon.utility.SecondaryRepairKit;
import objects.Direction;
import objects.Frame;
import objects.GameObject;
import objects.Robot;
import objects.Unit;
import world.World;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class BasicRunner extends BasicRobot 
{


	public BasicRunner(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.LIGHT);						
		setName("Runner");						

		// 5 / 5 Weight on Upgrades
		buy(new UpgradeAgility(this));			
		buy(new UpgradeAgility(this));			
		buy(new UpgradeShield(this));		
		buy(new UpgradeShield(this));		
		buy(new UpgradeShield(this));	
		

	}
	
	public void action()
	{
		pickupIfAble();
		spinToWin();
		wander();
	}

	
}
