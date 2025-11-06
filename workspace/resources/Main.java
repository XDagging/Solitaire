package resources;

public class Main {

	public static void main(String[] args) {
		Solitaire game = new Solitaire();
		GUI gui = new GUI(game);
		game.startGame();
		game.shuffleCards();
		System.out.println(game.deck);
		System.out.println(game.dealerHand);
		System.out.println(game.playerHand);
		game.hit();
		System.out.println(game.playerHand);
		System.out.println(game.playerWon);
	}
}