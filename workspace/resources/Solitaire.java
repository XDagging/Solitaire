package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;

import resources.Card.Suit;

public class Solitaire {
	// ArrayList<Stack <Card>> columns;
	public Queue<Card> deck;
	public ArrayList<Card> dealerHand;
	public ArrayList<Card> playerHand;
	private int playerHandValue;
	private int dealerHandValue;
	public boolean playerLost = false;
	public boolean playerWon = false; 
	public boolean gameHasStarted = false;

	public Solitaire() {
		// initialize queues as linked lists
		this.deck = new LinkedList<>();
		this.dealerHand = new ArrayList<>();
		this.playerHand = new ArrayList<>();
	}

	public int checkTotalValue(ArrayList<Card> x) {
		int totalValue = 0;
		int aces = 0;
		for (int i = 0; i < x.size(); i++) {
			int currValue = x.get(i).value;
			if(currValue > 10){
				currValue = 10;
			}
			if(currValue == 1){
				currValue = 11;
			}
			totalValue += currValue;
		}
		return totalValue;
	}

	// hit function
	public void hit() {
		Card newCard = deck.poll();	
		this.playerHand.add(newCard);
		System.out.println("player curr value:" + (playerHandValue + newCard.value));
	}

	// checks if player has won or lost
	public void check() {
		int playerValue = checkTotalValue(this.playerHand);
		int dealerValue = checkTotalValue(this.dealerHand);
			if (playerValue > 21) {
				playerLost = true;
			} else if (dealerValue > 21) {
				playerWon = true;
			} else if (playerValue == 21) {
				playerWon = true;
			} else if (dealerValue == 21) {
				playerLost = true;
			}
		}

	public void dealCards() {
		for(int i = 0; i < 2; i++){
			Card newCard = deck.poll();
			this.playerHand.add(newCard);
			playerHandValue += newCard.value;
			Card newCard2 = deck.poll();
			this.dealerHand.add(newCard2);
			dealerHandValue += newCard2.value;
		}
		System.out.println("dealer first value:" + (dealerHandValue));
		System.out.println("player first value:" + (playerHandValue));
		if(checkTotalValue(playerHand) == 21){
			playerWon = true;
		}
		if(checkTotalValue(dealerHand) == 21){
			playerLost = true;
		}
	}

	public boolean getPlayerWon() {
		return playerWon;
	}

	// cues the dealer to start dealing themself
	public void stand() {
		while (checkTotalValue(this.dealerHand) <= 17) {
			Card newCard = deck.poll();	
			this.dealerHand.add(newCard);
		}
	}

	void shuffleCards() {
		// build a fresh list of 52 cards (ranks 1..13 for each suit)
		LinkedList<Card> allDeck = new LinkedList();
		for (int i = 1; i <= 13; i++) {
			allDeck.add(new Card(i, Suit.Spades));
			allDeck.add(new Card(i, Suit.Clubs));
			allDeck.add(new Card(i, Suit.Diamonds));
			allDeck.add(new Card(i, Suit.Hearts));
		}
		// shuffle and place into the deck queue
		Collections.shuffle(allDeck);
		deck = allDeck;
	}

	public void startGame() {
		shuffleCards();
		dealCards();
	}
}
