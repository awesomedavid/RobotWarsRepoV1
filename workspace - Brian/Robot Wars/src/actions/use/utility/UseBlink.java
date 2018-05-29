package actions.use.utility;

import actions.use.UseOnCell;
import animations.AnimBlink;
import animations.AnimCross;
import animations.AnimHeal;
import animations.beam.AnimBeamBurst;
import animations.beam.AnimBeamBurstLaser;
import animations.projectile.AnimProjectileSniper;
import animations.text.AnimFloatText;
import animations.text.AnimFloatTextCombat;
import core.DamageType;
import core.Game;
import core.Utility;
import effects.Damage;
import item.weapon.Weapon;
import objects.GameObject;
import objects.Point;
import objects.Unit;
import sound.Audio;
import world.World;
import world.cells.Cell;

public class UseBlink extends UseOnCell
{
	public UseBlink(Weapon weapon, Point target) {
		super(weapon.getOwner(), World.getCell(target), SECONDARY_BLINK_USE_TIME);
	}

	protected void activate()
	{
		animBlink();
		getActor().setLocation(target.getPoint());
		animBlink();

		//	Audio.playLaser(getActor().getPointPixelSlick());
	}
	
	public void animBlink()
	{
		for(int i = 0; i < 9; i++)
		{
			float x = actor.getXPixel() + Utility.random(-CELL_SIZE/2, CELL_SIZE/2);
			float y = actor.getXPixel() + Utility.random(-CELL_SIZE*2, 0);

			float scale = Utility.random(.5f, 1.5f);
			int duration = (int) (SECONDARY_BLINK_ANIMATION_DURATION * scale);
			int size = (int) (SECONDARY_BLINK_ANIMATION_SIZE * scale);
			animation(new AnimBlink(x, y, size, actor, duration));
		}

	}
}
