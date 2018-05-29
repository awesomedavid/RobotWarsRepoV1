package values;

import core.DamageType;

public interface UpgradeValues 
{
	// Plating Upgrade
	final static int UPGRADE_PLATING_COST = 5;	
	final static int UPGRADE_PLATING_AMOUNT = 20;
	final static int UPGRADE_PLATING_WEIGHT = 1;

	// Shield Upgrade
	final static int UPGRADE_SHIELD_COST = 5;	
	final static int UPGRADE_SHIELD_AMOUNT = 20;
	final static int UPGRADE_SHIELD_REGEN = 1;
	final static int UPGRADE_SHIELD_WEIGHT = 1;

	// Accuracy Upgrade
	final static int UPGRADE_ACCURACY_COST = 5;	
	final static int UPGRADE_ACCURACY_WEIGHT = 1;
	final static float UPGRADE_ACCURACY_AMOUNT = .1f;
	
	// Agility Upgrade
	final static int UPGRADE_AGILITY_COST = 5;	
	final static int UPGRADE_AGILITY_WEIGHT = 1;
	final static float UPGRADE_AGILITY_SPEED = .05f;
	final static float UPGRADE_AGILITY_DODGE = .05f;
	
	// Pierce Upgrade
	final static int UPGRADE_PIERCE_COST = 5;	
	final static int UPGRADE_PIERCE_WEIGHT = 1;
	
	// Energy Upgrade
	final static int UPGRADE_ENERGY_COST = 5;	
	final static int UPGRADE_ENERGY_WEIGHT = 1;
	
	// Heat Sink Upgrade
	
	/* 
	 * Causes a robot to reduce it's heat level more quickly
	 */
	final static int UPGRADE_HEAT_SINK_COST = 5;	
	final static float UPGRADE_HEAT_SINK_AMOUNT = .1f;
	final static int UPGRADE_HEAT_SINK_WEIGHT = 1;
	
	/* Processor Upgrade
	 *
	 * A robot's processor determines at what point it becomes overclocked
	 * Each robot starts at processor level 1, and it can be upgraded twice
	 * 
	 * Each upgraded level has two effects:
	 *   - Increases the maximum processing level before triggering overclock by X * level
	 *   - Reduces the heat generated when overclocking by Y / level 
	 */
	
	final static int UPGRADE_PROCESSOR_COST = 5;	
	final static int UPGRADE_PROCESSOR_AMOUNT = 1;		
	final static int UPGRADE_PROCESSOR_WEIGHT = 1;

}
