package resources;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.Stack;
import org.w3c.dom.Text;

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

	Solitaire game;
    public GUI(Solitaire game){
	   this.game= game;
        //Create and set up the window.
	   int screenWidth = 900;
	   int screenHeight = 700;
       setTitle("Black Jack");
       setSize(screenWidth, screenHeight);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   ImagePanel background = null;
       
       // this supplies the background
       try {
		System.out.println(getClass().toString());

		Image blackImg = ImageIO.read(getClass().getResource("background.jpg"));
		background = new ImagePanel(blackImg);
		setContentPane(background);

       }catch(IOException e) {
    	   e.printStackTrace();
       }
	
		//setting layout to border layout
		background.setLayout(new BorderLayout());
	   
	    //dealer cards
   		Border dealerCardsBorder = BorderFactory.createLineBorder(Color.BLUE, 3);
		JPanel dealerCards = new JPanel();
		dealerCards.setPreferredSize(new Dimension(screenWidth, (int) (screenHeight*0.25)));
		background.add(dealerCards, BorderLayout.NORTH);
		dealerCards.add(new Card(4, Suit.Clubs));
	   	dealerCards.setBorder(dealerCardsBorder);
		dealerCards.add(new JLabel("Dealer's hand"));

		//stack test example
	   	Stack<Card> testDeck = new Stack<Card>();
		testDeck.add(new Card(1, Suit.Spades));
		testDeck.add(new Card(2, Suit.Spades));
		testDeck.add(new Card(3, Suit.Spades));
		testDeck.add(new Card(4, Suit.Spades));
		testDeck.add(new Card(5, Suit.Spades));

		//game deck
		Border deckBorder = BorderFactory.createLineBorder(Color.GREEN, 3);
		JPanel gameDeck = new JPanel();
		gameDeck.setPreferredSize(new Dimension(screenWidth, (int) (screenHeight*0.25)));
		background.add(gameDeck, BorderLayout.WEST);
		gameDeck.add(drawPile(testDeck)); //placeholder card
		gameDeck.setBorder(deckBorder);

	    //player cards
		Border playerCardBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
		JPanel playerCards = new JPanel();
		playerCards.setPreferredSize(new Dimension(screenWidth, (int) (screenHeight*0.25)));
		background.add(playerCards, BorderLayout.SOUTH);
		playerCards.add(new Card(4, Suit.Hearts));
		playerCards.add(new JLabel("Your Cards"));

		//hit and stand buttons
		playerCards.add(new JButton("Hit"));
	   	playerCards.add(new JButton("Stand"));
		playerCards.setBorder(playerCardBorder);

		
	
// 	   JButton x = new JButton("click here to start game");

// 		x.addActionListener(e -> {
// // The "if" check is technically redundant since this listener
//           // is only attached to 'x', but it doesn't hurt.
// 		  	System.out.println("this button was");
//             game.startGame();
// 			this.remove(x);
// 		});

// 		this.add(x);
       this.setVisible(true);
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
