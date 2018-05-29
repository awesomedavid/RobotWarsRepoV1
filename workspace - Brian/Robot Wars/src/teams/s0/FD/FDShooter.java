package teams.s0.FD;

import org.newdawn.slick.SlickException;

import actions.Reload;
import files.Images;
import item.StoneBlock;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.melee.SecondaryDrill;
import item.weapon.ranged.PrimaryShotgun;
import item.weapon.ranged.PrimarySniper;
import item.weapon.utility.SecondaryRepairKit;
import objects.Frame;
import objects.Robot;
import objects.Unit;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class FDShooter extends FDRobot {
	final int TURN_TIME = 50;
	int turnTimer = 0;
	int timer = 0;
	
	public FDShooter(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.LIGHT);
		setName("Shooter");
		buy(new PrimaryShotgun(this));
		buy(new SecondaryRepairKit(this));
		buy(new UpgradeShield(this));
	}
	public void action() {
		Unit target = getNearestEnemy();
		shoot(target);
		if(getMaxDurability()-getCurDurability()>=100) {
			if(this.getSecondary()!=null && this.getSecondary().canUse()) {
			useSecondary();
			}
		}
		if(getMaxShield()<MAXIMUM_SHIELD_CAP) {
		buy(new UpgradeShield(this));
		}
		reloadIfNeeded();
		pickupIfAble();
		spinToWin();
		wander();
	}

}
