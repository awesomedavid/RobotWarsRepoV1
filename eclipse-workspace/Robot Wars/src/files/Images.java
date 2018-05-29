package files;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import values.CoreValues;

public class Images implements CoreValues
{

	public static SpriteSheet raider;
	
	public static SpriteSheet terrainSoil;
	public static SpriteSheet terrainSand;
	public static SpriteSheet terrainMud;
	public static SpriteSheet terrainGrassLight;
	public static SpriteSheet terrainGrassMedium;
	public static SpriteSheet terrainGrassHeavy;

	public static SpriteSheet terrainFloor;

	public static SpriteSheet terrainStoneRough;
	public static SpriteSheet terrainWaterShallow;
	public static SpriteSheet terrainWaterDeep;

	public static SpriteSheet alpha;
	public static SpriteSheet alphaCorners;
	
	public static SpriteSheet wallBrick;
	public static SpriteSheet mountain;
	public static SpriteSheet mountainDamaged;

	public static Image stoneBlock;
	public static Image woodLog;
	public static Image beacon;

	public static Image[] treeOak; 
	public static Image[] metal; 
	public static Image[] metalGrey; 


	
	public static void loadWorld() throws SlickException 
	{
		raider = new SpriteSheet("res/raider.png", CELL_SIZE, CELL_SIZE, 0);
		terrainSoil = new SpriteSheet("res/soil.png", CELL_SIZE, CELL_SIZE, 0);
		terrainSand = new SpriteSheet("res/sand.png", CELL_SIZE, CELL_SIZE, 0);
		terrainMud = new SpriteSheet("res/cell/terrain/terrainMud.png", CELL_SIZE, CELL_SIZE, 0);
		terrainGrassLight = new SpriteSheet("res/cell/terrain/terrainGrassLight.png", CELL_SIZE, CELL_SIZE, 0);
		terrainGrassMedium = new SpriteSheet("res/cell/terrain/terrainGrassMedium.png", CELL_SIZE, CELL_SIZE, 0);
		terrainGrassHeavy = new SpriteSheet("res/cell/terrain/terrainGrassHeavy.png", CELL_SIZE, CELL_SIZE, 0);

		terrainStoneRough = new SpriteSheet("res/cell/terrain/terrainStoneRough.png", CELL_SIZE, CELL_SIZE, 0);
		terrainWaterShallow = new SpriteSheet("res/cell/terrain/terrainWaterShallow.png", CELL_SIZE, CELL_SIZE, 0);
		terrainWaterDeep = new SpriteSheet("res/cell/terrain/terrainWaterDeep.png", CELL_SIZE, CELL_SIZE, 0);
		terrainFloor = new SpriteSheet("res/cell/terrain/terrainFloor.png", CELL_SIZE, CELL_SIZE, 0);

		alpha = new SpriteSheet("res/misc/alpha.png", CELL_SIZE, CELL_SIZE, 0);
		alphaCorners = new SpriteSheet("res/misc/alpha_corners.png", CELL_SIZE, CELL_SIZE, 0);
		
		mountain = new SpriteSheet("res/cell/feature/mountain.png", CELL_SIZE, CELL_SIZE, 0);
		mountainDamaged = new SpriteSheet("res/cell/feature/mountainDamaged.png", CELL_SIZE, CELL_SIZE, 0);

		wallBrick = new SpriteSheet("res/cell/feature/wallBrick.png", CELL_SIZE, CELL_SIZE, 0);
		
		stoneBlock = new Image("res/item/resource/stoneBlock.png");
		woodLog = new Image("res/item/resource/woodLog.png");
				
		beacon = new Image("res/cell/feature/beacon.png");
		
		treeOak= new Image[2];
		treeOak[0] = new Image("res/cell/feature/treeOakA.png");
		treeOak[1] = new Image("res/cell/feature/treeOakB.png");
		
		metal = new Image[3];
		metal[0] = new Image("res/item/resource/metal0.png");
		metal[1] = new Image("res/item/resource/metal1.png");
		metal[2] = new Image("res/item/resource/metal2.png");
		
		metalGrey = new Image[3];
		metalGrey[0] = new Image("res/item/resource/metal0d.png");
		metalGrey[1] = new Image("res/item/resource/metal1d.png");
		metalGrey[2] = new Image("res/item/resource/metal2d.png");
		//sand.colo(255, 0,  0);
		


	}
	

	public static Image primaryDrill;
	public static Image primaryChainsaw;
	public static Image primaryMachineGun;
	public static Image primaryFlamethrower;
	public static Image primaryLaser;
	public static Image primaryPowerFist;
	public static Image primaryRepairBeam;
	public static Image primaryRocketLauncher;
	public static Image primarySniperRifle;
	public static Image primaryShotgun;

	public static Image secondaryRepairKit;
	public static Image secondarySmokeGrenade;
	public static Image secondaryBlink;
	public static Image secondaryLandmine;

	public static void loadWeapons() throws SlickException
	{
		primaryDrill = new Image("res/item/weapon/primary/primaryDrill.png");
		primaryChainsaw = new Image("res/item/weapon/primary/primaryChainsaw.png");
		primaryMachineGun = new Image("res/item/weapon/primary/primaryMachineGun.png");
		primaryFlamethrower = new Image("res/item/weapon/primary/primaryFlamethrower.png");
		primaryPowerFist = new Image("res/item/weapon/primary/primaryPowerFist.png");
		primaryLaser = new Image("res/item/weapon/primary/primaryLaser.png");
		primaryFlamethrower = new Image("res/item/weapon/primary/primaryFlamethrower.png");
		primaryRepairBeam = new Image("res/item/weapon/primary/primaryRepairBeam.png");
		primaryRocketLauncher = new Image("res/item/weapon/primary/primaryRocketLauncher.png");
		primarySniperRifle = new Image("res/item/weapon/primary/primarySniperRifle.png");
		primaryShotgun = new Image("res/item/weapon/primary/primaryShotgun.png");

		secondaryRepairKit = new Image("res/item/weapon/secondary/secondaryRepairKit.png");
		secondarySmokeGrenade = new Image("res/item/weapon/secondary/secondarySmokeGrenade.png");
		secondaryBlink = new Image("res/item/weapon/secondary/secondaryBlink.png");
		secondaryLandmine = new Image("res/item/weapon/secondary/secondaryLandmine.png");		
		
	}
	
	public static Image boom;

	public static void loadEffects() throws SlickException 
	{
		boom = new Image("res/boom.png");		

	}
	
	
	//public static Image robotDefault;

	public static SpriteSheet robotDefault;
	public static SpriteSheet robotHit;
	public static SpriteSheet robotSoldier;

//	public static Image robotHappy;
//	public static Image robotLarva;
//	public static Image robotOptic;
//	public static Image robotPincer;
//	public static Image robotRollout;
//	public static Image robotScreen;
//	public static Image robotTank;
//	public static Image robotTesla;
	
	public static void loadRobots() throws SlickException
	{
		robotDefault = new SpriteSheet("res/robot/robotTest.png", 32, 64, 0);
		robotHit = new SpriteSheet("res/robot/robotHit.png", 32, 64, 0);
		robotSoldier = new SpriteSheet("res/robot/robotSoldier.png", 32, 64, 0);

		
//		robotHappy = new Image("res/robot/robotHappy.png");
//		robotLarva = new Image("res/robot/robotLarva.png");
//		robotOptic = new Image("res/robot/robotOptic.png");
//		robotPincer = new Image("res/robot/robotPincer.png");
//		robotRollout = new Image("res/robot/robotRollout.png");
//		robotScreen = new Image("res/robot/robotScreen.png");
//		robotTank = new Image("res/robot/robotTank.png");
//		robotTesla = new Image("res/robot/robotTesla.png");	
//		
		
	}

	

}
