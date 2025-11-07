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
	public boolean playerWon; 
	public boolean gameHasStarted = false;

	public Solitaire() {
		// initialize queues as linked lists
		this.deck = new LinkedList<>();
		this.dealerHand = new ArrayList<>();
		this.playerHand = new ArrayList<>();
	}

	public int checkTotalValue(ArrayList<Card> x) {
		int totalValue = 0;
		for (int i = 0; i < x.size(); i++) {
			totalValue += x.get(i).value;
		}
		return totalValue;
	}

	// hit function
	public void hit() {
		Card newCard = deck.poll();	
		System.out.println("we just added: " + newCard);
		this.playerHand.add(newCard);
	}

	// checks if player has won or lost
	public void check() {
		int playerValue = checkTotalValue(this.playerHand);
			if (playerValue > 21) {
				playerWon = false;
			} else if (playerValue == 21) {
				playerWon = true;
			}
		}

	public void dealCards() {
		for(int i = 0; i < 2; i++){
			System.out.println("this was called dealCards");
			Card newCard = deck.poll();
			// System.out.println("deck is here", deck.isEmpty());
			this.playerHand.add(newCard);
			Card newCard2 = deck.poll();
			this.dealerHand.add(newCard2);
		}
		if(checkTotalValue(playerHand) == 21){
			playerWon = true;
		}
		if(checkTotalValue(dealerHand) == 21){
			playerWon = false;
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
		// deck.addAll(allDeck);
		System.out.println("this is the size of the deck: " + deck.size());
	}

	public void startGame() {
		shuffleCards();
		dealCards();
		// if(true){
		// 	hit();
		// 	check();
		// }
		// stand();
	}
}
