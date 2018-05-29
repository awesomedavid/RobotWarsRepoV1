package teams.s0.FD;

import org.newdawn.slick.SlickException;

import actions.Reload;
import files.Images;
import item.StoneBlock;
import item.equipment.UpgradePlating;
import item.equipment.UpgradeShield;
import item.weapon.melee.SecondaryDrill;
import item.weapon.ranged.PrimarySniper;
import objects.Frame;
import objects.Robot;
import objects.Unit;
import world.cells.feature.Mountain;
import world.cells.feature.Tree;
import world.cells.feature.Wall;

public class FDOof extends FDRobot {
	final int TURN_TIME = 50;
	int turnTimer = 0;
	int timer = 0;

	public FDOof(int x, int y, int id, int myID) throws SlickException {
		super(x, y, id, myID);
		this.setFrame(Frame.LIGHT);
		setName("Sniper");
		//buy(new PrimarySniper(this));
		buy(new SecondaryDrill(this));
		buy(new UpgradePlating(this));
		buy(new UpgradeShield(this));
		buy(new UpgradeShield(this));
	}

	@Override
	public void action() {
		timer++;
		nice;
		if (timer % 500 == 499) {
			buy(new UpgradePlating(this));
			buy(new UpgradeShield(this));
		}

		Unit target = getNearestEnemy();

		shoot(target);
		reloadIfNeeded();
		pickupIfAble();
		spinToWin();
		wander();

	}

}
