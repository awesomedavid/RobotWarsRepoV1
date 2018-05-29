package sound;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import core.Game;
import core.Utility;
import values.CoreValues;
import values.Settings;

public class Audio 
{



	
	
	public static void playSniper(Point location)
	{
		if(Settings.sfxOn)
		{
			Audio.sniper.play(location, Utility.random(.9f, 1.1f), 1);
		}
	}
	
	public static void playShotgun(Point location)
	{
		if(Settings.sfxOn)
		{
			Audio.shotgun.play(location, Utility.random(.9f, 1.1f), 1);
		}
	}
		
	public static void playReload(Point location)
	{
		if(Settings.sfxOn)
		{
			Audio.reload.play(location, Utility.random(.9f, 1.1f), 1);
		}
	}
	
	public static void playBlast(Point location)
	{
		if(Settings.sfxOn)
		{
			Audio.blast.play(location, Utility.random(.9f, 1.1f), 1);
		}
	}
	
	
	public static void playGrenade(Point location)
	{
		if(Settings.sfxOn)
		{
			Audio.grenade.play(location, Utility.random(.9f, 1.1f), 1);
		}
	}
	
	public static void playHeal(Point location)
	{
		if(Settings.sfxOn)
		{
			Audio.heal.play(location);	
		}
	}
	
	public static void playBoom(Point location, float pitch, float volume)
	{
		if(Settings.sfxOn)
		{
			Audio.boom[Utility.random(0, 2)].play(location, pitch, volume);
		}
	}
	
	public static void playAegis(Point location)
	{
		if(Settings.sfxOn)
		{
			Audio.aegis.play(location);	
		}
	}
	
	public static void playEMP(Point Location)
	{
		if(Settings.sfxOn)
		{
			emp.play(Location);
		}
	}
	
	public static void playMG(Point location)
	{
		if(Settings.sfxOn)
		{
			final float PITCH = 1.6f;
			final float VOLUME = 1.3f;
			
			if(!Audio.mg[0].playing())
			{
				Audio.mg[0].play(location, PITCH, VOLUME);
			}
			else if(!Audio.mg[1].playing())
			{
				Audio.mg[1].play(location, PITCH, VOLUME);
			}
			else if(!Audio.mg[2].playing())
			{
				Audio.mg[2].play(location, PITCH, VOLUME);
			}
			else if(!Audio.mg[3].playing())
			{
				Audio.mg[3].play(location, PITCH, VOLUME);
			}
			else if(!Audio.mg[4].playing())
			{
				Audio.mg[4].play(location, PITCH, VOLUME);
			}
		}
		
	}
	
	public static void playLaser(Point location)
	{
		if(Settings.sfxOn)
		{
			laser[Utility.random(0, 2)].play(location, 1.5f, .6f);	
		}
	}
	
	
	private static ArrayList<Song> songs;
	private static ArrayList<Song> special;
	private static ArrayList<Song> hidden;
	
	private static Song gameMusic;
	
	public static void loadSongList() throws SlickException
	{
		String path = "res/music/core/";
		
		songs = new ArrayList<Song>();
//		songs.add(new Song("Nobuo Uematsu", "Clash on the Big Bridge (FF5)", path + "ff5_clash.ogg"));
//		songs.add(new Song("Nobuo Uematsu", "Battle Theme (FF6)", path + "ff6_battle.ogg"));
//		songs.add(new Song("Nobuo Uematsu", "Those Who Fight Further (FF7)", path + "ff7_boss.ogg"));
//		songs.add(new Song("Nobuo Uematsu", "The Man With The Machine Gun (FF8)", path + "ff8_laguna.ogg"));
		songs.add(new Song("Ramin Djawadi", "Pacific Rim - Main Theme", path + "pacific_rim_theme.ogg"));

		
		special = new ArrayList<Song>();
		
		path = "res/music/special/";

		special.add(new Song("Sabaton", "Winged Hussars", path + "winged_hussars.ogg", true));
		// To Add:
		// Immigrant Song
		
		
		
		hidden = new ArrayList<Song>();
		path = "res/music/hidden/";
		
		hidden.add(new Song("Darude", "Sandstorm (Potato Cover)", path + "potato.ogg", true));
		
		
		// Title Track - Pacific Rim Theme
		// End Credits - Pacific Rim - Cancelling the Apocolypse
		
		setRandomGameMusic();
	}

	public static void setRandomGameMusic()
	{
		try
		{
			if(Math.random() < CoreValues.MUSIC_HIDDEN_CHANCE)
			{
				gameMusic = Audio.hidden.get(Utility.random(0, Audio.hidden.size()-1));
			}
			else if(Math.random() < CoreValues.MUSIC_SPECIAL_CHANCE)
			{
				gameMusic = Audio.special.get(Utility.random(0, Audio.special.size()-1));
			}
			else
			{
				gameMusic = Audio.songs.get(Utility.random(0, Audio.songs.size()-1));
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Song getGameMusic()
	{
		return gameMusic;
	}
	
	public static void loadGameMusicFile() throws SlickException
	{
		gameMusic.loadMusic();
	}
	
	public static void playGameMusic()
	{
		try
		{
			if(getGameMusic() == null)
			{
				setRandomGameMusic();
			}
			
			if(getGameMusic().getMusic() == null)
			{
				loadGameMusicFile();	
			}
			
			if(Settings.musicOn)
			{
				getGameMusic().getMusic().loop();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	// Attacks
	private static SFX[] laser;
	private static SFX[] mg;
	private static SFX blast;
	private static SFX sniper;
	private static SFX shotgun;
	private static SFX grenade;

	private static SFX heal;
	private static SFX[] boom;
	
	// Abilities
	private static SFX aegis;
	private static SFX emp;

	// Actions
	private static SFX reload;
	
	public static void loadSFX() 
	{
		try
		{
			// Music

			loadGameMusicFile();
	
			// Attacks
			laser = new SFX[3];
			laser[0] = new SFX("res/sfx/laser1.ogg");
			laser[1] = new SFX("res/sfx/laser2.ogg");
			laser[2] = new SFX("res/sfx/laser3.ogg");
			
			mg = new SFX[5];
			mg[0] = new SFX("res/sfx/mg.ogg");
			mg[1] = new SFX("res/sfx/mg.ogg");
			mg[2] = new SFX("res/sfx/mg.ogg");
			mg[3] = new SFX("res/sfx/mg.ogg");
			mg[4] = new SFX("res/sfx/mg.ogg");
	
			blast = new SFX("res/sfx/blast.ogg");
			heal = new SFX("res/sfx/heal.ogg");
	
			// Abilities
			aegis = new SFX("res/sfx/aegis.ogg");
			emp = new SFX("res/sfx/emp.ogg");
			
			// Other
			boom = new SFX[3];
			boom[0] = new SFX("res/sfx/boom1.ogg");
			boom[1] = new SFX("res/sfx/boom2.ogg");
			boom[2] = new SFX("res/sfx/boom3.ogg");
			
			reload = new SFX("res/sfx/reload.ogg");
			sniper = new SFX("res/sfx/sniper.ogg");
			shotgun = new SFX("res/sfx/shotgun.ogg");
			grenade = new SFX("res/sfx/grenade.ogg");


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
