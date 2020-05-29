package image;

import javax.swing.ImageIcon;

import images.pic;

public class imag {
	//Sets up the image icon that represents each item.
	private static ImageIcon rock = new ImageIcon(pic.class.getResource("rock.jpg"));
	private static ImageIcon paper = new ImageIcon(pic.class.getResource("paper.jpg"));
	private static ImageIcon scissors = new ImageIcon(pic.class.getResource("scissors.jpg"));
	private static ImageIcon exit = new ImageIcon(pic.class.getResource("exit.png"));
	private static ImageIcon item = new ImageIcon(pic.class.getResource("item.png"));
	private static ImageIcon BrittleCrown = new ImageIcon(pic.class.getResource("BrittleCrown.png"));
	private static ImageIcon Crowbar = new ImageIcon(pic.class.getResource("Crowbar.png"));
	private static ImageIcon EffigyOfGrief = new ImageIcon(pic.class.getResource("EffigyOfGrief.png"));
	private static ImageIcon GlowingMeteorite = new ImageIcon(pic.class.getResource("GlowingMeteorite.png"));
	private static ImageIcon TougherTimes = new ImageIcon(pic.class.getResource("TougherTimes.png"));
	private static ImageIcon Woodsprite = new ImageIcon(pic.class.getResource("Woodsprite.png"));
	private static ImageIcon Dialogue = new ImageIcon(pic.class.getResource("dialogue.png"));
	private static ImageIcon Stats = new ImageIcon(pic.class.getResource("stats.png"));
	private static ImageIcon MenuScreen = new ImageIcon(pic.class.getResource("menu screen.PNG"));
	private static ImageIcon shopkeeper = new ImageIcon(pic.class.getResource("shopkeeper.jpg"));
	
	//getters for each item image icon
	public static ImageIcon getRock() {
		return rock;
	}
	
	public static ImageIcon getPaper() {
		return paper;
	}
	
	public static ImageIcon getScissors() {
		return scissors;
	}

	public static ImageIcon getExit() {
		return exit;
	}

	public static ImageIcon getItem() {
		return item;
	}
	
	public static ImageIcon getBrittleCrown() {
		return BrittleCrown;
	}
	
	public static ImageIcon getCrowbar() {
		return Crowbar;
	}
	
	public static ImageIcon getTougherTimes() {
		return TougherTimes;
	}
	
	public static ImageIcon getWoodsprite() {
		return Woodsprite;
	}
	
	public static ImageIcon getGlowingMeteorite() {
		return GlowingMeteorite;
	}
	
	public static ImageIcon getEffigyOfGrief() {
		return EffigyOfGrief;
	}
	
	public static ImageIcon getDialogue() {
		return Dialogue;
	}
	
	public static ImageIcon getStats() {
		return Stats;
	}
	
	public static ImageIcon getMenuScreen() {
		return MenuScreen;
	}
	
	public static ImageIcon getShopkeeper() {
		return shopkeeper;
	}
	
}
