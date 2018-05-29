package actions;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import item.Item;
import item.Metal;
import item.StoneBlock;
import item.WoodLog;
import item.equipment.Equipment;
import item.weapon.Weapon;
import objects.Direction;
import objects.Robot;
import objects.Unit;
import sound.Audio;
import world.World;
import world.cells.Cell;
import world.cells.feature.Mountain;

public class Reload extends Action 
{
	Weapon weapon;
	
	public Reload(Unit actor, Weapon w)
	{
		super(actor, w.getReloadTime());
		weapon = w;
		
		if(!w.usesAmmo() || !w.canReload())
		{
			cancel();
		}
	}
		
	public void render(Graphics g)
	{
		super.render(g);
		
		renderActionProgress(g);

	}
	
	protected void complete()
	{
		weapon.reloadActionComplete();
		Audio.playReload(getActor().getPointPixelSlick());

	}
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	
}
