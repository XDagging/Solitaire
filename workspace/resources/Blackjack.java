package resources;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;

import resources.Card.Suit;

public class Blackjack {
	// ArrayList<Stack <Card>> columns;
	public Queue<Card> deck;
	public ArrayList<Card> dealerHand;
	public ArrayList<Card> playerHand;
	public boolean playerLost = false;
	public boolean playerWon = false; 
	public boolean playerTied = false;
	public boolean gameHasStarted = false;
	public boolean standCalled = false;

	public Blackjack() {
		// initialize queues as linked lists
		this.deck = new LinkedList<>();
		this.dealerHand = new ArrayList<>();
		this.playerHand = new ArrayList<>();
	}

	public int checkTotalValue(ArrayList<Card> x) {
		int totalValue = 0;
		int aces = 0;
		if(x.size() > 0 && x != null){
		for (int i = 0; i < x.size(); i++) {
			int currValue = x.get(i).value;
			if(currValue > 10){
				currValue = 10;
			}
			if(currValue == 1){
				aces += 1;
				currValue = 11;
			}
			totalValue += currValue;
		}
		while(totalValue > 21 && aces > 0){
			totalValue -= 10;
			aces -= 1;
		}

		return totalValue;
	}
	return 0;
	}

	// hit function
	public void hit() {
		if(dealerHand.size() == 2){
			Card newCard = deck.poll();	
			this.playerHand.add(newCard);
		}
	}

	// checks if player has won or lost
	public void check() {
		int playerValue = checkTotalValue(this.playerHand);
		int dealerValue = checkTotalValue(this.dealerHand);
		System.out.println("Player Value: " + playerValue);
		System.out.println("Dealer Value: " + dealerValue);
			if (playerValue > 21) {
				playerLost = true;
			} else if (dealerValue > 21) {
				playerWon = true;
			} else if (playerValue == 21) {
				playerWon = true;
			} else if (dealerValue == 21) {
				playerLost = true;
			} else if(standCalled && dealerValue > playerValue && dealerValue < 21){
				playerLost = true;
			} else if(standCalled && dealerValue < playerValue && playerValue < 21){
				playerWon = true;
			} else if(standCalled && dealerValue == playerValue){
				playerTied = true;
			}
		}

	public void dealCards() {
		Card playerCard1 = deck.poll();
		this.playerHand.add(playerCard1);
		Card playerCard2 = deck.poll();
		this.playerHand.add(playerCard2);
		Card dealerCard1 = deck.poll();
		this.dealerHand.add(dealerCard1);
		Card dealerCard2 = deck.poll();
		this.dealerHand.add(dealerCard2);
		dealerCard1.hide();
		System.out.println("Dealer card 1: " + dealerCard1 + "Dealer card 2: " + dealerCard2 + "Player card 1: " + playerCard1 + "Player card 2: " + playerCard2);
		System.out.println("PlayerHand: " + playerHand);
		System.out.println("DealerHand: " + dealerHand);
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
		long timeStart = System.currentTimeMillis();
		double secondsToAnimate = 0.5;
		while((System.currentTimeMillis()-timeStart)/1000 < secondsToAnimate){
			dealerHand.get(0).show();
		}
		while (checkTotalValue(this.dealerHand) <= 17) {
			dealerHit();
		}
		standCalled = true;
		check();
	}

	public void dealerHit(){
		// long timeStart = System.currentTimeMillis();
		// double secondsToAnimate = 0.5;
		// while((System.currentTimeMillis() - timeStart)/1000 < secondsToAnimate){
			Card newCard = deck.poll();	
			this.dealerHand.add(newCard);
		//}
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
