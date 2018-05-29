package teams.s0.FD;

import org.newdawn.slick.SlickException;

import actions.Reload;
import files.Images;
import item.StoneBlock;
import item.equipment.UpgradeAgility;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.melee.SecondaryDrill;
import item.weapon.ranged.PrimaryLaser;
import item.weapon.ranged.PrimarySniper;
import objects.Frame;
import objects.Robot;
import objects.Unit;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class FDRunner extends FDRobot {
	final int TURN_TIME = 50;
	int turnTimer = 0;
	int timer = 0;

	public FDRunner(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.LIGHT);
		setName("Runner");
		buy(new PrimaryLaser(this));
		buy(new SecondaryDrill(this));
	}

	@Override
	public void action() {
		Unit target = getNearestEnemy();
		shoot(target);
		reloadIfNeeded();
		pickupIfAble();
		spinToWin();
		wander();

	}

}
