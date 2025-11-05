package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;

import resources.Card.Suit;

public class Solitaire {
	// ArrayList<Stack <Card>> columns;
	Queue<Card> deck;
	ArrayList<Card> dealerHand;
	ArrayList<Card> playerHand;
	private boolean playerWon; 
	public boolean gameHasStarted = false;

	public Solitaire() {
		// initialize queues as linked lists
		this.deck = new LinkedList<>();
		this.dealerHand = new ArrayList<>();
		this.playerHand = new ArrayList<>();
	}

	public int checkTotalValue(ArrayList<Card> x) {
		int totalValue = 0;
		for (int i=0; i<x.size(); i++) {

			if ((x.get(i).value == 1) && (x.size()-1==i)) {
				if (totalValue+11==21) {
					playerWon = true;
					break;
				}

			}

			totalValue += x.get(i).value;
		}


		
		
		return totalValue;


	}
	// if true, they hit on the player hand, if not dealer.
	public void hit(boolean isPlayerHand) {
		Card newCard = deck.poll();	

		if (isPlayerHand) {
			this.playerHand.add(newCard);

			int value = checkTotalValue(this.playerHand);

			if (value>21) {
				playerWon = false;
			} else if (value==21) {
				playerWon = true;
			}

		} else {
			this.dealerHand.add(newCard);

			int value = checkTotalValue(this.dealerHand);

			if (value>21) {
				playerWon = true;
			} else if (value==21) {
				playerWon = false;
			}


		}
	}

	public void dealCards() {


		hit(true);
		hit(true);

		
		hit(false);
		hit(false);

	}

	public boolean getPlayerWon() {
		return playerWon;
	}

	// cues the dealer to start dealing themself
	public void stand() {
		while (checkTotalValue(this.dealerHand) <= 17) {
			hit(false);
		}

		if (playerWon) {
			return;
		} else if (checkTotalValue(playerHand) > checkTotalValue(dealerHand)) {
			playerWon = true;
		} else if (checkTotalValue(playerHand) < checkTotalValue(dealerHand)) {
			playerWon = false;
		} else if (checkTotalValue(dealerHand) == checkTotalValue(playerHand)) {
			// house always wins
			playerWon = false;
		}


	}





	private void shuffleCards() {
		// build a fresh list of 52 cards (ranks 1..13 for each suit)
		System.out.println("shuffle cards was called");
		ArrayList<Card> allDeck = new ArrayList<>(52);
		for (int i = 1; i <= 13; i++) {
			allDeck.add(new Card(i, Suit.Spades));
			allDeck.add(new Card(i, Suit.Clubs));
			allDeck.add(new Card(i, Suit.Diamonds));
			allDeck.add(new Card(i, Suit.Hearts));
		}

		// shuffle and place into the deck queue
		Collections.shuffle(allDeck);
		// System.out.println("")
		deck.clear();
		deck.addAll(allDeck);
		

	}


	public void startGame() {
		shuffleCards();
		dealCards();


	}

	





	
	//the part of your program that's in charge of game rules goes here.
}
