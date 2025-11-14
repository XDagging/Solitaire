package resources;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.Stack;
import org.w3c.dom.Text;
import java.util.Queue;
import resources.Card.Suit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.DigestException;


public class GUI extends JFrame implements ActionListener, MouseListener, MouseMotionListener{
	JPanel dealerCards = new JPanel();
	JPanel gameDeck = new JPanel();
	JPanel playerCards = new JPanel();
	JButton gameStart = new JButton("Click here to start the game");
	JButton gameRestart = new JButton("Click here to restart the game");
	JButton hitButton = new JButton("Hit");
	JButton standButton = new JButton("Stand");

	Blackjack game;
    public GUI(Blackjack game){
	   this.game= game;
        //Create and set up the window.
	   int screenWidth = 900;
	   int screenHeight = 575;
       setTitle("Black Jack");
       setSize(screenWidth, screenHeight);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   ImagePanel background = null;
       
       // this supplies the background
       try {
		System.out.println(getClass().toString());

		Image blackImg = ImageIO.read(getClass().getResource("blackjackbackground.jpg"));
		background = new ImagePanel(blackImg);
		setContentPane(background);

       }catch(IOException e) {
    	   e.printStackTrace();
       }
	
		//setting layout to border layout
		background.setLayout(new BorderLayout());
	   
	    //dealer cards
   		Border dealerCardsBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
		dealerCards.setPreferredSize(new Dimension(screenWidth, (int) (screenHeight*0.25)));
		background.add(dealerCards, BorderLayout.NORTH);
	   	dealerCards.setBorder(dealerCardsBorder);
		dealerCards.setOpaque(false);
		dealerCards.add(new JLabel("Dealer's hand"));

		//game deck
		Border deckBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
		gameDeck.setPreferredSize(new Dimension(screenWidth, (int) (screenHeight*0.25)));
		background.add(gameDeck, BorderLayout.WEST);
		gameDeck.setOpaque(false);
		gameDeck.setBorder(deckBorder);

	    //player cards
		Border playerCardBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
		playerCards.setPreferredSize(new Dimension(screenWidth, (int) (screenHeight*0.25)));
		background.add(playerCards, BorderLayout.SOUTH);
		playerCards.add(new JLabel("Your Cards"));
		playerCards.setOpaque(false);
		playerCards.setBorder(playerCardBorder);

		//hit button
		playerCards.add(hitButton);
	   	hitButton.addActionListener(e -> {
			game.hit();
			update();
		});

		//stand button
		playerCards.add(standButton);
		standButton.addActionListener(e -> {
			game.stand();
			update();
		});
	   
		//Game start button
		gameDeck.add(gameStart);
		gameStart.addActionListener(e -> {
			game.gameHasStarted = true;
            game.startGame();
			gameDeck.remove(gameStart);
			this.update();
		
		});

		gameRestart.addActionListener(e -> {
			this.dispose();
			Main.restart();
		
		});


       	this.setVisible(true);
    }

	private void update(){
		//rebuild screen	
		dealerCards.removeAll();
		gameDeck.removeAll();
		playerCards.removeAll();

		for (int i = 0; i < game.dealerHand.size(); i++) {
			Card currentCard = game.dealerHand.get(i);
			if (currentCard != null) {
				dealerCards.add(currentCard);
			}
		}

		for (int i = 0; i < game.playerHand.size(); i++) {
			Card currentCard = game.playerHand.get(i);
			if (currentCard != null) {
				playerCards.add(currentCard);
			}
		}

		Card deckCard = game.deck.poll();
		deckCard.isReversed = true;
		gameDeck.add(deckCard);

		playerCards.add(standButton);
		playerCards.add(hitButton);

		//checks
		game.check();

		if(game.playerWon == true){
			gameDeck.add(new JButton("You Won!"));
			gameDeck.add(gameRestart);
		}

		if(game.playerLost == true){
			gameDeck.add(new JButton("You Lost!"));
			gameDeck.add(gameRestart);
		}

		if(game.playerTied == true){
			gameDeck.add(new JButton("You Tied!"));
			gameDeck.add(gameRestart);
		}

		this.revalidate();
		this.repaint();

	}

	//draws a pile of cards based on a stack
	public JLayeredPane drawPile(Stack<Card> stackIn) {
     	Object cards[];
	 	cards = stackIn.toArray();
		JLayeredPane pile = new JLayeredPane();
		pile.setPreferredSize(new Dimension(150, 200));
		//loops through the cards in the stack
		for (int i = cards.length-1; i > 0; i--) {
			Card currentCard = (Card)cards[i];
			currentCard.setBounds(0,0, currentCard.getPreferredWidth(), currentCard.getPreferredHeight());
			//flips cards around
				currentCard.hide();
			//adds cards to the pile
			pile.add((Card)cards[i], i);
		}
		return pile;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
