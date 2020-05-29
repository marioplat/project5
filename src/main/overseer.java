package main;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import image.imag;
import view.window;

public class overseer {

	//instance variables
	private window _w;
	
	int RPS_max = 3;
	int RPS_min = 1;
	int range = RPS_max - RPS_min + 1;
	private int computerPlay;
	
	private int player1Score;
	private int player2Score;
	private int computerScore;
	
	//OPTIONS
	public static final ImageIcon[] RPS_IMAGE_OPTIONS = {imag.getRock(), imag.getPaper(), imag.getScissors(), imag.getItem(), imag.getExit()};
	public static final ImageIcon[] RPS_IMAGE_OPTIONS_PVP = {imag.getRock(), imag.getPaper(), imag.getScissors(), imag.getExit()};
	//public static final String[] RPS_OPTIONS = { "Rock", "Paper", "Scissors", "Items", "Menu" };
	public static final String[] MENU_OPTIONS = { "Player vs Player", "Player vs AI", "Shop", "Statistics", "Exit" };
	public static final String[] COMPETITIVE_OPTIONS = {"FIGHT TO THE DEATH!", "MENU"};
	//public static final ImageIcon[] SHOP_IMAGE_OPTIONS = {imag.getBrittleCrown(), imag.getEffigyOfGrief(), imag.getTougherTimes(), imag.getCrowbar(), imag.getWoodsprite(), imag.getGlowingMeteorite(), imag.getDialogue(), imag.getExit()};
	public static final String[] SHOP_OPTIONS = {"Brittle Crown: 5 Coins", "Effigy of Grief: 15 Coins", "Tougher Times: 20 Coins", "Crowbar: 40 Coins", "Woodsprite: 50 Coins", "Meteorite: 100 Coins", "Who are you?", "MENU"};
	public static final String[] STATS_OPTIONS = {"MENU", ">"};
	public static final String[] DIALOGUE_OPTIONS = {"Competition?", "Items?", "Threaten", "Barter", "Bye"};
	public static final String[] INVENTORY_OPTIONS = {"Brittle Crown", "Effigy of Grief", "Tougher Times", "Crowbar", "Woodsprite", "Meteorite", "Back"};
	
	//STATISTICS
	private int rockClicked;
	private int paperClicked;
	private int scissorClicked;
	private int roundsPlayed;
	private int gamesPlayed;
	
	private int gamesPlayed_PVE;
	private int roundsPlayed_PVE;
	private int playerWin_PVE;
	private int playerLoss_PVE;
	private int ties_PVE;
	
	private int gamesPlayed_PVP;
	private int roundsPlayed_PVP;
	private int player1Win_PVP;
	private int player1Loss_PVP;
	private int player2Win_PVP;
	private int player2Loss_PVP;
	private int ties_PVP;
	
	private int usedBrittleCrown;
	private int usedEffigyOfGrief;
	private int usedTougherTimes;
	private int usedCrowbar;
	private int usedWoodsprite;
	private int usedMeteorite;
	
	//SHOP
	private int coins = 100;
	private boolean hasBrittleCrown = false;
	private boolean hasEffigyOfGrief = false;
	private boolean hasTougherTimes = false;
	private boolean hasCrowbar = false;
	private boolean hasWoodsprite = false;
	private boolean hasMeteorite = false;



	//constructor
	public overseer() {
		_w = new window();
		menu();
	}

	/*
	 * This method updates the CPU's number for every turn. The numbers, 1 through 3, represent
	 * rock, paper, and scissors respectively
	 */
	public void updateComputerPlay() {
		computerPlay = (int) (Math.random() * range) + RPS_min;
	}


	/*
	 * This method sets up the main menu. The player can select different play styles, the shop, or look through statistics.
	 * The menu also has the amount of coins a player has.
	 */
	public void menu() {
		int x = JOptionPane.showOptionDialog(null, ""
				+ "\r\nDefeat the opposition using a variety of monsters! \r\n \r\nCOINS: " + coins + "\r\n", "Ultimate RPS", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, imag.getMenuScreen(), MENU_OPTIONS, null);
		if (x == 0) {
			_w.msg("Decide who is Player 1 and Player 2. \r\n \r\n ITEMS ARE DISABLED!");
			playerVersusPlayer();
		} else if (x == 1) {
			playerVersusComputer();
		} else if (x == 2) {
			menuShop();
		} else if (x == 3) {
			menuStatistics();
		} else {
			System.exit(0);
		}
	}

	
	/*
	 * This method sets up the shop menu. The player can buy a variety of items to use during 
	 * battle using coins. The method also updates the players inventory if they buy an item,
	 * while also deducting their coins. Though, if the player does not have enough coins to 
	 * buy the item, they do not receive the item. The player can also converse with the 
	 * shopkeeper.
	 */
	public void menuShop() {
		int shop = JOptionPane.showOptionDialog(null, "RICO THE SHOPKEEPER: Aye, welcome to me shop. How may I serve yeh. " + "\r\n --------- \r\n ITEMS"
				+ "\r\n --------- \r\n \r\n Brittle Crown: For 3 turns, gain 2 extra coins."
				+ "\r\n \r\n Effigy of Grief: You and the enemy lose one point. "
				+ "\r\n \r\n Tougher Times: Resets both the enemy score and yours. "
				+ "\r\n \r\n Crowbar: You and the enemy lose 2 points. "
				+ "\r\n \r\n Woodsprite: You gain +1 point. "
				+ "\r\n \r\n Meteorite: A barrage of meteors rains upon the battlefield and everyone dies... Except you! "
				+ "\r\n \r\n You have " + coins + " coins \r\n \r\n", 
				"Ultimate RPS", 
				JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.PLAIN_MESSAGE, 
				imag.getShopkeeper(), 
				SHOP_OPTIONS, 
				null);
		if (shop == 0) {
			if(coins >= 5) {
				hasBrittleCrown = true;
				coins -= 5;
				JOptionPane.showOptionDialog(null, "You bought the Brittle Crown!"
						+ "\r\n \r\n The Brittle Crown has magical powers and was once worn by a king. "
						+ "\r\n Legends say that the king was leading a wealthy kingdom, "
						+ "\r\n but he was wasting most of his wealth on very large dinners.", 
						"Ultimate RPS", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.PLAIN_MESSAGE, 
						imag.getBrittleCrown(), 
						null, 
						null); 
				menuShop();
			}
			else {
				_w.msg("You do not have enough coins for this item. Go play some more!");
				menuShop();
			}
	   } else if (shop == 1) {
			if(coins >= 15) {
				hasEffigyOfGrief = true;
				coins -= 15;
				JOptionPane.showOptionDialog(null, "You bought the Effigy of Grief! "
						+ "\r\n \r\n Who could this effigy represent?"
						+ "\r\n Some say it was to immortalize an ancient god long ago due to its peculiar, yet recognizable body.", 
						"Ultimate RPS", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.PLAIN_MESSAGE, 
						imag.getEffigyOfGrief(), 
						null, 
						null);
				menuShop();
			}
			else {
				_w.msg("You do not have enough coins for this item. Go play some more!");
				menuShop();
			}
		} else if (shop == 2) {
			if(coins >= 20) {
				hasTougherTimes = true;
				coins -= 20;
				JOptionPane.showOptionDialog(null, "You bought the Tougher Times!"
						+ "\r\n \r\n Who knew this fluffy, innocent looking teddy bear could tank incoming damage?"
						+ "\r\n ..."
						+ "\r\n No one, really.", 
						"Ultimate RPS", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.PLAIN_MESSAGE, 
						imag.getTougherTimes(), 
						null, 
						null);
				menuShop();
			}
			else {
				_w.msg("You do not have enough coins for this item. Go play some more!");
				menuShop();
			}
		} else if (shop == 3) {
			if(coins >= 40) {
				hasCrowbar = true;
				coins -= 40;
				JOptionPane.showOptionDialog(null, " You bought the Crowbar! \r\n"
						+ "\r\n Just a plain old crowbar. "
						+ "\r\n Used to beat dudes."
						+ "\r\n ..."
						+ "\r\n Can't say much, really.", 
						"Ultimate RPS", 
						JOptionPane.PLAIN_MESSAGE,
						JOptionPane.PLAIN_MESSAGE, 
						imag.getCrowbar(), 
						null, 
						null);
				menuShop();
			}
			else {
				_w.msg("You do not have enough coins for this item. Go play some more!");
				menuShop();
			}
		} else if (shop == 4) {
			if(coins >= 50) {
				hasWoodsprite = true;
				coins -= 50;
				JOptionPane.showOptionDialog(null, "You bought the Woodsprite! \r\n"
						+ "\r\n This tiny sprite is one passive little guy. "
						+ "\r\n His purpose is to help others in need!", 
						"Ultimate RPS", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.PLAIN_MESSAGE, 
						imag.getWoodsprite(), 
						null, 
						null);
				menuShop();
			}
			else {
				_w.msg("You do not have enough coins for this item. Go play some more!");
				menuShop();
			}
		} else if (shop == 5) {
			if(coins >= 20) {
				hasMeteorite = true;
				coins -= 100;
				JOptionPane.showOptionDialog(null, "You bought the Meteorite!"
						+ "\r\n \r\n Welcome... "
						+ "\r\n To the APOCALYPSE", 
						"Ultimate RPS", 
						JOptionPane.PLAIN_MESSAGE, 
						JOptionPane.PLAIN_MESSAGE, 
						imag.getGlowingMeteorite(), 
						null,
						null);
				menuShop();
			}
			else {
				_w.msg("You do not have enough coins for this item. Go play some more!");
				menuShop();
			}
		} else if (shop == 6) {
			menuDialogue();
		} else {
			menu();
		}

	}
    /*
     * This method sets up the dialogue menu for the shop menu. The player is given different
     * options that reveals more dialogue.      	
     */
	public void menuDialogue() {
		int dialogue = _w.option(DIALOGUE_OPTIONS, " Aye? I don't really get visitors askin' meh those kinds of questions... "
				+ "\r\n Customers just come in 'ere, grab a nick nack or two, and bolt they're way out. "
				+ "\r\n These people just want to win that stupid little competition. "
				+ "\r\n \r\n Right, yeh asked for me. What do ye wanna know?");
		if(dialogue == 0) { // comp
			_w.msg(" Yeh kinda silly, eh? Sorry if I offend yeh, but that crap has no purpose in existin'."
					+ "\r\n It's just a bunch of lil' kids trying to play the lottery, yeh just ain't winnin'!"
					+ "\r\n The most powerful will always cheat their way into victory, and these kids don't know it...");
			menuDialogue();
		}
		else if(dialogue == 1) { // items
			_w.msg(" Only the finest, mate. Some may call this junk, but me I call em' money bags."
					+ "\r\n Stupid lil' kids always got the money for it, so it's how I make a livin'."
					+ "\r\n And as for where I got em... Well, let's just say I have some business relations with the owners of that competition."
					+ "\r\n Not bad, eh? The big bucks are so cake to get at all times. ");
			menuDialogue();
		}
		else if(dialogue == 2) { // threaten
			_w.msg("Well if it's a fight yeh want its a fight yeh'll get! "
					+ "\r\n \r\n \r\n YOU DIED!");
			System.exit(0);
		}
		else if(dialogue == 3) { // barter
			_w.msg(" 'Scuse me new guy, but I don't do lower prices. "
					+ "\r\n Unless yeh're blind or a bloody loon, ye can clearly see the prices on the board 'ere. "
					+ "\r\n Now shed up, and buy somethin'. Or better yet, leave the shop. I don't need your kind 'ere.");
			menuDialogue();
		}
		else {
			menuShop();
		}
	}
	
	/*
	 * This method sets up the statistics menu. The player can go through each categorized statistics page:
	 * general, PVE, PVP, and item. As the player plays the game, every action they do will update the
	 * statistics page using counters.
	 */
	public void menuStatistics() {
		int general = JOptionPane.showOptionDialog(null, "GENERAL STATISTICS "
				+ "\r\n   Games Played: " + gamesPlayed 
				+ "\r\n \r\n   Rounds Played: " + roundsPlayed 
				+ "\r\n \r\n   Rocks Clicked: " + rockClicked
				+ "\r\n \r\n   Papers Clicked: " + paperClicked
				+ "\r\n \r\n   Scissors Clicked: " + scissorClicked
				+ "\r\n \r\n   Coins: " + coins, "Ultimate RPS", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, imag.getStats(), STATS_OPTIONS, null); 
		if(general == 0) {
			menu();
		}
		else {
			int pvp = JOptionPane.showOptionDialog(null, "PRACTICE VERSUS AI STATISTICS "
					+ "\r\n   PVE Games Played: " + gamesPlayed_PVE
					+ "\r\n \r\n   PVE Rounds Played: " + roundsPlayed_PVE
					+ "\r\n \r\n   PVE Games Won: " + playerLoss_PVE
					+ "\r\n \r\n   PVE Games Lost: " + playerWin_PVE
					+ "\r\n \r\n   PVE Ties: " + ties_PVE, "Ultimate RPS", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, imag.getStats(), STATS_OPTIONS, null);
			if(pvp == 0) {
				menu();
			}
			else {
				int pve = JOptionPane.showOptionDialog(null, "PLAYER VERSUS PLAYER STATISTICS "
						+ "\r\n   PVP Games Played: " + gamesPlayed_PVP
						+ "\r\n \r\n   PVP Rounds Played: " + roundsPlayed_PVP
						+ "\r\n \r\n   Player 1 Games Won: " + player1Win_PVP
						+ "\r\n \r\n   Player 1 Games Lost: " + player1Loss_PVP
						+ "\r\n \r\n   Player 2 Games Won: " + player2Win_PVP
						+ "\r\n \r\n   Player 2 Games Lost: " + player2Loss_PVP
						+ "\r\n \r\n   PVP Ties: " + ties_PVP, "Ultimate RPS", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, imag.getStats(), STATS_OPTIONS, null);
				if(pve == 0) {
					menu();
				}
				else {
					int item = JOptionPane.showOptionDialog(null, "ITEM STATISTICS "
							+ "\r\n   Brittle Crown Used: " + usedBrittleCrown
							+ "\r\n \r\n   Effigy of Grief Used: " + usedEffigyOfGrief
							+ "\r\n \r\n   Tougher Times Used: " + usedTougherTimes
							+ "\r\n \r\n   Crowbar Used: " + usedCrowbar
							+ "\r\n \r\n   Woodsprite Used: " + usedWoodsprite
							+ "\r\n \r\n   Meteorite Used: " + usedMeteorite,
							"Ultimate RPS", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, imag.getStats(), STATS_OPTIONS, null);
					if(pve == 0) {
						menu();
					}
					else {
						menu();
					}
				}
			}
		}
	}
	/*
	 * This method sets up the PVP inventory, a menu accessible during the battle. This will show the different items
	 * that the player bought and they can choose what item they would like to activate. If the player does not
	 * own the item, they will simply be notified that cannot activate the item's power.
	 */
	public void menuInventory_PVP() {
		int inv = JOptionPane.showOptionDialog(null, 
				" Select an item! \r\n Player 1 score is: " + player1Score
				+ "\r\n Player 2 score is: " + player2Score, 
				"Ultimate RPS", 
				JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.PLAIN_MESSAGE, 
				imag.getItem(), 
				INVENTORY_OPTIONS, 
				null);
		if(inv == 0) {
			if(hasBrittleCrown == true) {
				for(int i = 0; i < 3; i++) {
					coins += 2;
				}
				usedBrittleCrown++;
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 1) {
			if(hasEffigyOfGrief == true) {
				player1Score -= 1;
				player2Score -= 1;
				usedEffigyOfGrief++;
				checkWinLoss_PVP();
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 2) {
			if(hasTougherTimes == true) {
				player1Score = 0;
				player2Score = 0;
				usedTougherTimes++;
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 3) {
			if(hasCrowbar == true) {
				player1Score -= 2;
				player2Score -= 2;
				usedCrowbar++;
				checkWinLoss_PVP();
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 4) {
			if(hasWoodsprite == true) {
				player1Score += 1;
				usedWoodsprite++;
				checkWinLoss_PVP();
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 5) {
			if(hasMeteorite == true) {
				player1Score = 3;
				usedMeteorite++;
				checkWinLoss_PVP();
			}
			else {
				inventoryErrorMessage();
			}
		}
		else {
			playerVersusPlayer();
		}
	}
	/*
	 * This method sets up the PVE inventory, a menu accessible during the battle. This will show the different items
	 * that the player bought and they can choose what item they would like to activate. If the player does not
	 * own the item, they will simply be notified that cannot activate the item's power.
	 */
	public void menuInventory_PVE() {
		int inv = JOptionPane.showOptionDialog(null, 
				" Select an item! \r\n Player 1 score is: " + player1Score
				+ "\r\n The enemy score is: " + computerScore, 
				"Ultimate RPS", 
				JOptionPane.PLAIN_MESSAGE, 
				JOptionPane.PLAIN_MESSAGE, 
				imag.getItem(), 
				INVENTORY_OPTIONS, 
				null);
		if(inv == 0) {
			if(hasBrittleCrown == true) {
				for(int i = 0; i < 3; i++) {
					coins++;
				}
				usedBrittleCrown++;
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 1) {
			if(hasEffigyOfGrief == true) {
				player1Score -= 1;
				computerScore -= 1;
				usedEffigyOfGrief++;
				checkWinLoss_PVE();
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 2) {
			if(hasTougherTimes == true) {
				player1Score = 0;
				computerScore = 0;
				usedTougherTimes++;
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 3) {
			if(hasCrowbar == true) {
				player1Score -= 2;
				computerScore -= 2;
				usedCrowbar++;
				checkWinLoss_PVE();
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 4) {
			if(hasWoodsprite == true) {
				player1Score += 1;
				usedWoodsprite++;
				checkWinLoss_PVE();
			}
			else {
				inventoryErrorMessage();			}
		}
		else if(inv == 5) {
			if(hasMeteorite == true) {
				player1Score = 3;
				usedMeteorite++;
				checkWinLoss_PVE();
			}
			else {
				inventoryErrorMessage();
			}
		}
		else {
			playerVersusComputer();
		}
	}
	
	/*
	 * This method sets up the PVE battle game mode. Numbers 1 through 3 represent rock, paper, scissors respectively.
	 * If the player selects an action, the method will compare the action's number and the CPU's randomly generated
	 * number. If the player wins the turn, they will earn a point. If the CPU wins, it will earn the point.
	 * Once either opponent reaches a certain amount of points, they will win the battle. This method also contains
	 * counters for the statistics menu and also coins.
	 */
	public void playerVersusComputer() {
		while (true) {
			int x = _w.option(RPS_IMAGE_OPTIONS, " Rock, Paper, or Scissors? \r\n \r\n Player score is: " + player1Score
					+ "\r\n Enemy score is: " + +computerScore);
			if (x == 0) { // PLAYER ROCK
				if (computerPlay == 1) {
					roundsPlayed++;
					roundsPlayed_PVE++;
					rockClicked++;
					ties_PVE++;
					JOptionPane.showOptionDialog(null, 
							" You chose rock \r\n ENEMY CHOSE ROCK! \r\n \r\n IT'S A TIE! \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore, 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getRock(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				} else if (computerPlay == 2) {
					roundsPlayed++;
					roundsPlayed_PVE++;
					rockClicked++;
					computerScore++;
					JOptionPane.showOptionDialog(null, 
							" You chose rock \r\n ENEMY CHOSE PAPER! \r\n \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore, 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getPaper(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				} else {
					roundsPlayed++;
					roundsPlayed_PVE++;
					rockClicked++;
					player1Score++;
					coins++;
					JOptionPane.showOptionDialog(null, 
							" You chose rock \r\n ENEMY CHOSE SCISSORS! \r\n \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore + "\r\n \r\n You've earned 1 coin!", 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getScissors(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				}

			} else if (x == 1) { // PLAYER PAPER
				if (computerPlay == 1) {
					roundsPlayed++;
					roundsPlayed_PVE++;
					paperClicked++;
					player1Score++;
					coins++;
					JOptionPane.showOptionDialog(null, 
							" You chose paper \r\n ENEMY CHOSE ROCK! \r\n \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore + "\r\n \r\n You've earned 1 coin!", 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getRock(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				} else if (computerPlay == 2) {
					roundsPlayed++;
					roundsPlayed_PVE++;
					paperClicked++;
					ties_PVE++;
					JOptionPane.showOptionDialog(null, 
							" You chose paper \r\n ENEMY CHOSE PAPER! \r\n \r\n IT'S A TIE! \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore, 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getPaper(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				} else {
					roundsPlayed++;
					roundsPlayed_PVE++;
					paperClicked++;
					computerScore++;
					JOptionPane.showOptionDialog(null, 
							" You chose paper \r\n ENEMY CHOSE SCISSORS! \r\n \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore + "\r\n \r\n You've earned 1 coin!", 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getScissors(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				}

			} else if (x == 2) { // PLAYER SCISSORS
				if (computerPlay == 1) {
					roundsPlayed++;
					roundsPlayed_PVE++;
					scissorClicked++;
					computerScore++;
					JOptionPane.showOptionDialog(null, 
							" You chose scissors \r\n ENEMY CHOSE ROCK! \r\n \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore + "\r\n \r\n You've earned 1 coin!", 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getRock(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				} else if (computerPlay == 2) {
					roundsPlayed++;
					roundsPlayed_PVE++;
					scissorClicked++;
					player1Score++;
					coins++;
					JOptionPane.showOptionDialog(null, 
							" You chose scissors \r\n ENEMY CHOSE PAPER! \r\n \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore, 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getPaper(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				} else {
					roundsPlayed++;
					roundsPlayed_PVE++;
					scissorClicked++;
					ties_PVE++;
					JOptionPane.showOptionDialog(null, 
							" You chose scissors \r\n ENEMY CHOSE SCISSORS! \r\n \r\n IT'S A TIE! \r\n Player score is: " + player1Score
							+ "\r\n Enemy score is: " + computerScore + "\r\n \r\n You've earned 1 coin!", 
							"Ultimate RPS", 
							JOptionPane.PLAIN_MESSAGE, 
							JOptionPane.PLAIN_MESSAGE, 
							imag.getScissors(), 
							null, 
							null);
					updateComputerPlay();
					checkWinLoss_PVE();

				}
			} else if(x == 3) {
				menuInventory_PVE();
			}
			else {
				menu();
			}

		}

	}


	/*
	 * This method sets up the PVP battle game mode. Numbers 1 through 3 represent rock, paper, scissors respectively.
	 * If the player selects an action, the method will compare the action's number and the CPU's randomly generated
	 * number. If player 1 wins the turn, they will earn a point. If player 2 wins, it will earn the point.
	 * Once either opponent reaches a certain amount of points, they will win the battle. This method also contains
	 * counters for the statistics menu and also coins.
	 */
	public void playerVersusPlayer() {
		while (true) {
			int p1 = _w.option(RPS_IMAGE_OPTIONS_PVP, " Player 1!  Rock, Paper, or Scissors? \r\n \r\n Player 1 score is: "
					+ player1Score + "\r\n Player 2 score is: " + player2Score);
			int p2 = _w.option(RPS_IMAGE_OPTIONS_PVP, " Player 2!  Rock, Paper, or Scissors? \r\n \r\n Player 1 score is: "
					+ player1Score + "\r\n Player 2 score is: " + player2Score);
			if (p1 == 0) { // PLAYER 1 ROCK
				rockClicked++;
				if (p2 == 0) { // PLAYER 2 ROCK
					rockClicked++;
					roundsPlayed++;
					roundsPlayed_PVP++;
					ties_PVP++;
					_w.msg(" Player 1 chose rock \r\n Player 2 chose rock \r\n \r\n Player 1 score is: " + player1Score
							+ "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				} else if (p2 == 1) { // PLAYER 2 PAPER
					roundsPlayed++;
					roundsPlayed_PVP++;
					paperClicked++;
					player2Score++;
					_w.msg(" Player 1 chose rock \r\n Player 2 chose paper \r\n \r\n Player 1 score is: " + player1Score
							+ "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				} else { // PLAYER 2 SCISSORS
					roundsPlayed++;
					roundsPlayed_PVP++;
					scissorClicked++;
					player1Score++;
					_w.msg(" Player 1 chose rock \r\n Player 2 chose scissors \r\n \r\n Player 1 score is: "
							+ player1Score + "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				}

			} else if (p1 == 1) { // PLAYER 1 PAPER
				paperClicked++;
				if (p2 == 0) { // PLAYER 2 ROCK
					roundsPlayed++;
					roundsPlayed_PVP++;
					rockClicked++;
					player1Score++;
					_w.msg(" Player 1 chose paper \r\n Player 2 chose rock \r\n \r\n Player 1 score is: " + player1Score
							+ "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				} else if (p2 == 1) { // PLAYER 2 PAPER
					roundsPlayed++;
					roundsPlayed_PVP++;
					paperClicked++;
					ties_PVP++;
					_w.msg(" Player 1 chose paper \r\n Player 2 chose paper \r\n \r\n Player 1 score is: "
							+ player1Score + "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				} else { // PLAYER 2 SCISSORS
					roundsPlayed++;
					roundsPlayed_PVP++;
					scissorClicked++;
					player2Score++;
					_w.msg(" Player 1 chose paper \r\n Player 2 chose scissors \r\n \r\n Player 1 score is: "
							+ player1Score + "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				}

			} else if (p1 == 2) { // PLAYER 1 SCISSORS
				scissorClicked++;
				if (p2 == 0) { // PLAYER 2 ROCK
					roundsPlayed++;
					roundsPlayed_PVP++;
					rockClicked++;
					player2Score++;
					_w.msg(" Player 1 chose scissors \r\n Player 2 chose rock \r\n \r\n Player 1 score is: "
							+ player1Score + "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				} else if (p2 == 1) { // PLAYER 2 PAPER
					roundsPlayed++;
					roundsPlayed_PVP++;
					paperClicked++;
					player1Score++;
					_w.msg(" Player 1 chose scissors \r\n Player 2 chose paper \r\n \r\n Player 1 score is: "
							+ player1Score + "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				} else { // PLAYER 2 SCISSORS
					roundsPlayed++;
					roundsPlayed_PVP++;
					scissorClicked++;
					ties_PVP++;
					_w.msg(" Player 1 chose scissors \r\n Player 2 chose scissors \r\n \r\n Player 1 score is: "
							+ player1Score + "\r\n Player 2 score is: " + player2Score);
					checkWinLoss_PVP();
				}
			} else {
				player1Score = 0;
				player2Score = 0;
				menu();
			}
		}

	}
	


	/*
	 * This method checks if either opponent reaches the maximum amount of points to win the match. This will open
	 * a window announcing the winner and loser. If the player wins, they will earn coins. If they lose, they will
	 * lose a coin. This method also contains counters for the statistics menu. (PVE game mode)
	 */
	public void checkWinLoss_PVE() {
		if (player1Score == 3) {
			_w.msg("You are victorious!! \r\n \r\n FINAL SCORES \r\n Player: " + player1Score + "\r\n Computer: "
					+ computerScore  + "\r\n \r\n You've earned 5 coins for winning!");
			gamesPlayed++;
			gamesPlayed_PVE++;
			playerWin_PVE++;
			coins += 5;
			player1Score = 0;
			computerScore = 0;
			menu();
		}
		if (computerScore == 3) {
			_w.msg("You lose... \r\n \r\n FINAL SCORES \r\n Player: " + player1Score + "\r\n Computer: "
					+ computerScore  + "\r\n \r\n You lost 1 coin for losing");
			gamesPlayed++;
			gamesPlayed_PVE++;
			playerLoss_PVE++;
			coins -= 1;
			player1Score = 0;
			computerScore = 0;
			menu();
		}
	}

	/*
	 * This method checks if either opponent reaches the maximum amount of points to win the match. This will open
	 * a window announcing the winner and loser. If the player wins, they will earn coins. If they lose, they will
	 * lose a coin. This method also contains counters for the statistics menu. (PVP game mode)
	 */
	public void checkWinLoss_PVP() {
		if (player1Score == 3) {
			_w.msg("Player 1 is victorious!! \r\n FINAL SCORES \r\n Player1: " + player1Score + "\r\n Player 2: "
					+ player2Score);
			gamesPlayed++;
			gamesPlayed_PVP++;
			player1Win_PVP++;
			player2Loss_PVP++;
			coins += 5;
			player1Score = 0;
			player2Score = 0;
			menu();
		}
		if (player2Score == 3) {
			_w.msg("Player 2 is victorious!! \r\n FINAL SCORES \r\n Player1: " + player1Score + "\r\n Player 2: "
					+ player2Score);
			gamesPlayed++;
			gamesPlayed_PVP++;
			player2Win_PVP++;
			player1Loss_PVP++;
			coins += 5;
			player1Score = 0;
			player2Score = 0;
			menu();
		}
	}
	
	/*
	 * This method sets up the window menu that tells the player that they do not own the item they are selecting.
	 */
	public void inventoryErrorMessage() {
		_w.msg("YOU DO NOT OWN THIS ITEM! \r\n Buy the item first with your earned coins, then you can use it.");
	}
}