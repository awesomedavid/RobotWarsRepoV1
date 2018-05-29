package files;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

import core.Splash;

public class Fonts {
	
	
	
	public static TrueTypeFont calibri12Bold;
	public static TrueTypeFont calibri14Bold;
	public static TrueTypeFont calibri16Bold;
	public static TrueTypeFont calibri18Bold;
	public static TrueTypeFont calibri20Bold;
	public static TrueTypeFont calibri22Bold;
	public static TrueTypeFont calibri24Bold;
	
	public static TrueTypeFont arial12Bold;
	public static TrueTypeFont arial14Bold;
	public static TrueTypeFont arial16Bold;
	public static TrueTypeFont arial20Bold;

//	public static TrueTypeFont calibri14;
//
//	public static TrueTypeFont calibri16;
//	public static TrueTypeFont calibri20;

	public static TrueTypeFont impact28;
	public static TrueTypeFont arialblack12;

	public static TrueTypeFont arialblack16;

	public static TrueTypeFont arialblack36;
	public static TrueTypeFont arialblack28;
	public static TrueTypeFont arialblack48;



	public static void loadFonts() 
	{	
		// Used
		calibri12Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 12), false);
		calibri14Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 14), false);
		calibri16Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 16), false);
		calibri18Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 18), false);
		calibri20Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 20), false);
		calibri22Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 22), false);
		calibri24Bold = new TrueTypeFont(new Font("Calibri", Font.BOLD, 24), false);
		
		arial12Bold = new TrueTypeFont(new Font("Arial", Font.BOLD, 12), false);
		arial14Bold = new TrueTypeFont(new Font("Arial", Font.BOLD, 14), false);
		arial16Bold = new TrueTypeFont(new Font("Arial", Font.BOLD, 16), false);
		arial20Bold = new TrueTypeFont(new Font("Arial", Font.BOLD, 20), false);

		arialblack12 = new TrueTypeFont(new Font("Arial Black", Font.PLAIN, 12), false);
		arialblack16 = new TrueTypeFont(new Font("Arial Black", Font.PLAIN, 16), false);
		arialblack28 = new TrueTypeFont(new Font("Arial Black", Font.PLAIN, 28), false);
		arialblack36 = new TrueTypeFont(new Font("Arial Black", Font.PLAIN, 36), false);

		arialblack48 = new TrueTypeFont(new Font("Arial", Font.BOLD, 48), false);

	}
}
