package teams.s0.name;

import org.newdawn.slick.SlickException;

import files.Images;
import item.StoneBlock;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.ranged.PrimaryLaser;
import item.weapon.ranged.PrimarySniper;
import objects.Frame;
import objects.Robot;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class NameRobotLaser extends NameRobot 
{
	public NameRobotLaser(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.MEDIUM);
		setName("Laser");
		buy(new PrimaryLaser(this));
	}
	
}
