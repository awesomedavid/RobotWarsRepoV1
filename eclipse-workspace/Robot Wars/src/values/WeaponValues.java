package values;

import core.DamageType;

public interface WeaponValues 
{	
	// General
	final static int PRIMARY_COST = 10;
	final static int PRIMARY_WEIGHT = 2;
	final static int SECONDARY_COST = 10;
	final static int SECONDARY_WEIGHT = 1;
	
	
	/****************************************************************
	 *                                                              *
	 *                      PRIMARY WEAPONS							*
	 *                                                              *
	 ****************************************************************/
	
	// Artillery
	final static int PRIMARY_ARTILLERY_MAX_RANGE = 60;
	final static int PRIMARY_ARTILLERY_MIN_RANGE = 20;
	final static int PRIMARY_ARTILLERY_RANGE_MIN = 20;
	final static int PRIMARY_ARTILLERY_RADIUS = 5;
	final static int PRIMARY_ARTILLERY_USE_TIME = 300;		
	final static int PRIMARY_ARTILLERY_DAMAGE = 100;
	final static int PRIMARY_ARTILLERY_PROJECTILE_SPEED = 4;	
	final static float PRIMARY_ARTILLERY_ACCURACY = 1f;
	final static int PRIMARY_ARTILLERY_SCATTER = 20;
	final static int PRIMARY_ARTILLERY_RELOAD = 800;
	final static int PRIMARY_ARTILLERY_COOLDOWN = PRIMARY_ARTILLERY_RELOAD;
	final static int PRIMARY_ARTILLERY_AMMO = 1;
	final static int PRIMARY_ARTILLERY_HEAT = 50;
	final static DamageType PRIMARY_ARTILLERY_TYPE = DamageType.EXPLOSIVE;
	
	// Repair Beam
	final static float PRIMARY_REPAIR_BEAM_AMOUNT = 3;
	final static float PRIMARY_REPAIR_BEAM_ACCURACY = 2;	
	final static float PRIMARY_REPAIR_BEAM_ACCURACY_DROP = 0;	
	final static int PRIMARY_REPAIR_BEAM_COOLDOWN = 20;
	final static int PRIMARY_REPAIR_BEAM_HEAT = 10;
	final static int PRIMARY_REPAIR_BEAM_RANGE = 12;	
	final static int PRIMARY_REPAIR_BEAM_USE_TIME = PRIMARY_REPAIR_BEAM_COOLDOWN;
	final static int PRIMARY_REPAIR_BEAM_ANIMATION_DURATION = PRIMARY_REPAIR_BEAM_COOLDOWN+1;
	final static int PRIMARY_REPAIR_BEAM_ANIMATION_WIDTH = 13;
	final static int PRIMARY_REPAIR_BEAM_ANIMATION_CROSS_SIZE = 10;
	final static int PRIMARY_REPAIR_BEAM_ANIMATION_CROSS_DURATION = 20;

	final static DamageType PRIMARY_REPAIR_BEAM_TYPE = DamageType.ENERGY;
	
	// Laser
	final static float PRIMARY_LASER_DAMAGE = 20;
	final static float PRIMARY_LASER_ACCURACY = .95f;	
	final static float PRIMARY_LASER_ACCURACY_DROP = .01f;	
	final static int PRIMARY_LASER_COOLDOWN = 120;
	final static int PRIMARY_LASER_HEAT = 65;
	final static int PRIMARY_LASER_RANGE = 15;	
	final static int PRIMARY_LASER_USE_TIME = 20;
	final static int PRIMARY_LASER_ANIMATION_DURATION = 30;
	final static int PRIMARY_LASER_ANIMATION_WIDTH = 7;
	final static DamageType PRIMARY_LASER_TYPE = DamageType.ENERGY;
	
	// Machine Gun
	final static int PRIMARY_MG_RANGE = 10;	
	final static float PRIMARY_MG_ACCURACY = .95f;	
	final static float PRIMARY_MG_ACCURACY_DROP = .03f;	
	final static int PRIMARY_MG_USE_TIME = 5;
	final static float PRIMARY_MG_DAMAGE = 3;
	final static int PRIMARY_MG_COOLDOWN = 7;
	final static int PRIMARY_MG_RELOAD = 500;
	final static int PRIMARY_MG_AMMO = 50;
	final static int PRIMARY_MG_ANIMATION_SPEED = 12;
	final static int PRIMARY_MG_HEAT = 2;
	final static DamageType PRIMARY_MG_TYPE = DamageType.NORMAL;	

	// Shotgun
	final static int PRIMARY_SHOTGUN_RANGE = 6;	
	final static float PRIMARY_SHOTGUN_ACCURACY = 1.20f;	
	final static float PRIMARY_SHOTGUN_ACCURACY_DROP = .15f;	
	final static int PRIMARY_SHOTGUN_USE_TIME = 5;
	final static float PRIMARY_SHOTGUN_DAMAGE = 20;
	final static int PRIMARY_SHOTGUN_RELOAD = 60;
	final static int PRIMARY_SHOTGUN_COOLDOWN = 60;
	final static int PRIMARY_SHOTGUN_AMMO = 2;
	final static int PRIMARY_SHOTGUN_ANIMATION_COUNT = 5;
	final static int PRIMARY_SHOTGUN_ANIMATION_SPEED = 6;
	final static int PRIMARY_SHOTGUN_HEAT = 15;
	final static DamageType PRIMARY_SHOTGUN_TYPE = DamageType.NORMAL;
	
	// Sniper
	final static int PRIMARY_SNIPER_RANGE = 20;	
	final static float PRIMARY_SNIPER_ACCURACY = .40f;	
	final static float PRIMARY_SNIPER_ACCURACY_DROP = -.03f;	
	final static int PRIMARY_SNIPER_USE_TIME = 120;
	final static float PRIMARY_SNIPER_DAMAGE = 30;
	final static int PRIMARY_SNIPER_RELOAD = 250;
	final static int PRIMARY_SNIPER_COOLDOWN = PRIMARY_SNIPER_RELOAD;
	final static int PRIMARY_SNIPER_ANIMATION_SPEED = 30;
	final static int PRIMARY_SNIPER_AMMO = 1;
	final static int PRIMARY_SNIPER_HEAT = 30;
	final static DamageType PRIMARY_SNIPER_TYPE = DamageType.NORMAL;
	
	
	/****************************************************************
	 *                                                              *
	 *                     SECONDARY WEAPONS						*
	 *                                                              *
	 ****************************************************************/
	
	// Blink
	final static int SECONDARY_BLINK_RANGE = 10;
	final static int SECONDARY_BLINK_USE_TIME = 1;
	final static int SECONDARY_BLINK_ANIMATION_DURATION = 80;
	final static int SECONDARY_BLINK_ANIMATION_SIZE = 20;

	final static int SECONDARY_BLINK_COOLDOWN = 1000;
	final static int SECONDARY_BLINK_HEAT = 0;
	
	// Chainsaw	
	final static int SECONDARY_CHAINSAW_RANGE = 1;	
	final static int SECONDARY_CHAINSAW_USE_TIME = 1;
	final static float SECONDARY_CHAINSAW_DAMAGE = 2;
	final static int SECONDARY_CHAINSAW_COOLDOWN = 8;	
	final static int SECONDARY_CHAINSAW_ANIMATION_DURATION = 5;
	final static DamageType SECONDARY_CHAINSAW_TYPE = DamageType.NORMAL;
	final static int SECONDARY_CHAINSAW_HEAT = 2;	

	// Drill
	final static int SECONDARY_DRILL_RANGE = 1;	
	final static int SECONDARY_DRILL_USE_TIME = 1;
	final static float SECONDARY_DRILL_DAMAGE = 1;
	final static int SECONDARY_DRILL_COOLDOWN = 5;	
	final static int SECONDARY_DRILL_ANIMATION_DURATION = 5;
	final static int SECONDARY_DRILL_HEAT = 2;
	final static DamageType SECONDARY_DRILL_TYPE = DamageType.PIERCE;

	// Repair Kit
	final static int SECONDARY_REPAIR_KIT_RANGE = 0;
	final static int SECONDARY_REPAIR_KIT_USE_TIME = 120;
	final static float SECONDARY_REPAIR_KIT_HEAL_AMOUNT = 80;		
	final static int SECONDARY_REPAIR_KIT_ANIMATION_DURATION = 80;
	final static int SECONDARY_REPAIR_KIT_ANIMATION_SIZE = 20;

	final static int SECONDARY_REPAIR_KIT_COOLDOWN = 15;
	final static int SECONDARY_REPAIR_KIT_RELOAD = -1;
	final static int SECONDARY_REPAIR_KIT_AMMO = 3;
	final static int SECONDARY_REPAIR_KIT_HEAT = 0;

	// Smoke
	final static int SECONDARY_SMOKE_GRENADE_RANGE = 30;
	final static int SECONDARY_SMOKE_GRENADE_RADIUS = 7;
	final static int SECONDARY_SMOKE_GRENADE_USE_TIME = 50;
	final static int SECONDARY_SMOKE_GRENADE_DURATION_MIN = 1000;	
	final static int SECONDARY_SMOKE_GRENADE_DURATION_MAX = 1200;		
	final static int SECONDARY_SMOKE_GRENADE_ANIMATION_SPEED = 2;
	final static int SECONDARY_SMOKE_GRENADE_SCATTER = 5;
	final static int SECONDARY_SMOKE_GRENADE_ACCURACY = 1;
	final static int SECONDARY_SMOKE_GRENADE_COOLDOWN = 1000;
	final static int SECONDARY_SMOKE_GRENADE_RELOAD = -1;
	final static int SECONDARY_SMOKE_GRENADE_AMMO = 10;
	final static int SECONDARY_SMOKE_GRENADE_HEAT = 0;
	
	
	

}
