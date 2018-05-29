package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import display.selector.Button;
import display.selector.DropDown;
import display.selector.UIManager;
import objects.Team;
import teams.starter.basic.BasicBalancedTeam;
import values.CoreValues;
import values.Settings;


public class Select extends BasicGameState
{
	private StateBasedGame sbg;
	private int id;

	private static String[] blklst = {  };

	private static List<Team> teams;
	private static List<Group> groups;
	private static Map<Integer, Class<? extends Team>> tempTeams;

	private static final String teamsDir = "bin/teams";

	private static List<DropDown<Class <? extends Team>>> dropDowns;

	Button<Integer> run;
	DropDown<Integer> test;

	Select(int id) {
		this.id = id;
		teams = new ArrayList<Team>();
		tempTeams = new HashMap<Integer, Class<? extends Team>>();
	}
	
	
	public static void setTeams()
	{
		addTeam(BasicBalancedTeam.class);
		addTeam(BasicBalancedTeam.class);
	}
	

	public int getID() {
		return id;
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.sbg = sbg;
		UIManager.gc = gc;

		run = new Button<Integer>(Game.getScreenWidth()-256, Game.getScreenHeight()-64, 256, 64, 1);
		run.addText(0, "Run Game");

		groups = new ArrayList<Group>();
		InitGroups();

		dropDowns = new ArrayList<DropDown<Class <? extends Team>>>();

		for(int i = 0; i < 16; i++) {
			dropDowns.add(new DropDown<Class <? extends Team>>(200, 50 * i + 128, 512, 40));
			dropDowns.get(i).addText(0, "Team " + (i+1));
			for(Group g : groups) {
				for(Class<? extends Team> e : g.getTeams()) {
					dropDowns.get(i).addElement(e, e.getSimpleName());
				}
			}
		}

		File f = new File("res/Select.txt");

		if(!f.exists())
			return;

		try {
			Scanner s = new Scanner(f);

			String t;

			int i = 0;

			while(s.hasNextLine()) {
				t = s.nextLine();
				for(Group g : groups) {
					for(Class<?extends Team> c : g.getTeams()) {
						if(c.getName().equals(t)) {
							dropDowns.get(i).setReturnOBJ(c);
							dropDowns.get(i).addText(1, c.getSimpleName());
							tempTeams.put(i, c);
							try {
								Team a = (Team)(c.getConstructor(Integer.TYPE)).newInstance(i);
								dropDowns.get(i).addColor(0, a.getColor());
								dropDowns.get(i).addColor(2, a.getColor().darker(.5f));

							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
									| NoSuchMethodException | SecurityException e) {
								e.printStackTrace();
							}
							i++;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<Team> getTeams() {
		return teams;
	}

	private class Group {
		String name;
		String path;
		List<Class<? extends Team>> teams;

		Group(File file) {
			name = file.getName();
			path = file.getAbsolutePath();
			teams = new ArrayList<Class<?extends Team>>();

			for(File f : file.listFiles()) {
				if(!blklstd(f.getName())) {
					List<Class<?extends Team>> c = getTeam(f);

					if(c != null)
						teams.addAll(c);
				}
			}
		}

		List<Class<?extends Team>> getTeam(File f) {
			ArrayList<Class<? extends Team>> temp = new ArrayList<Class<? extends Team>>();
			for(File a : f.listFiles()) {

				try {
					URL url = Paths.get(new File("bin").getAbsolutePath()).toUri().toURL();
					URL[] urls = {url};
					ClassLoader cl = new URLClassLoader(urls);
					Class<?> cs = cl.getSystemClassLoader().loadClass(a.getPath().substring(4, a.getPath().length() - 6).replace("\\", "."));
					if(Team.class.isAssignableFrom(cs) && !Modifier.isAbstract(((Class<? extends Team>) cs).getModifiers())) {
						temp.add((Class<? extends Team>) cs);
					}
				}	catch(Exception e) {
				}
			}
			return temp;
		}

		List<Class<?extends Team>> getTeams() {
			return teams;
		}

		String getName() {
			return this.name;
		}

		String getPath() {
			return this.path;
		}
	}

	private void InitGroups() {
		File dir = new File(teamsDir);

		for (File f : dir.listFiles()) {
			if(f.isDirectory() && !blklstd(f.getName()))
				groups.add(new Group(f));
		}
	}


	public static void addTeam(Class <? extends Team> clazz) {	
		try {
			Team t = (Team)(clazz.getConstructor(Integer.TYPE)).newInstance(teams.size());
			teams.add(t);			

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	private boolean blklstd(String s) {
		for(String a : blklst)
			if(a.equalsIgnoreCase(s))
				return true;

		return false;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{		
		if(Settings.teamSelection)
		{
			int c = 0;

			for(DropDown d : dropDowns) {
				Class<? extends Team> t = (Class<? extends Team>) d.getReturnOBJ();

				if(t != null) {
					if(tempTeams.get(c) != t) {
						tempTeams.put(c, t);
						try {
							Team a = (Team)(t.getConstructor(Integer.TYPE)).newInstance(c);
							d.addColor(0, a.getColor());
							d.addColor(2, a.getColor().darker(.5f));

						} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| NoSuchMethodException | SecurityException e) {
							e.printStackTrace();
						}
					}
				}
				else {
					d.addColor(0, new Color(64, 64, 64));
					d.addColor(2, new Color(200, 200, 200));
					tempTeams.put(c, null);
				}
				c++;
			}

			if(run.isPressed() || gc.getInput().isKeyDown(Input.KEY_SPACE) || gc.getInput().isKeyDown(Input.KEY_ENTER)) {

				for(int i = 0; i < 16; i++) {
					if(dropDowns.get(i).getReturnOBJ() != null) {
						Select.addTeam((dropDowns.get(i).getReturnOBJ()));
					}
				}

				File f = new File("res/Select.txt");

				if(!f.exists()) {
					try {
						f.createNewFile();
					} catch (IOException e) {	
						e.printStackTrace();
					}
				}

				try {
					FileOutputStream p = new FileOutputStream(f, false);

					for(int i = 0; i < 16; i++) {
						if(dropDowns.get(i).getReturnOBJ() != null) {
							p.write((dropDowns.get(i).getReturnOBJ().getName() + "\r\n").getBytes());
						}
					}
				} catch (IOException e) {

					e.printStackTrace();
				}

				sbg.enterState(Engine.GAME_ID, 
						new FadeOutTransition(Color.black, CoreValues.TRANSITION_FADE_TIME), 
						new FadeInTransition(Color.black, CoreValues.TRANSITION_FADE_TIME));
			}

			UIManager.reset();
		}
		
		// Hardcoded mode
		else
		{
			setTeams();
			sbg.enterState(Engine.GAME_ID, 
					new FadeOutTransition(Color.black, 0), 
					new FadeInTransition(Color.black, 0));
		}
	}

	public void enter(GameContainer gc, StateBasedGame sbg) {
		gc.setMouseGrabbed(false);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(Settings.teamSelection)
		{
			UIManager.render(gc, g);
		}
	}
}
