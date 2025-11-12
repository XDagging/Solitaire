package resources;

public class Main {

	public static void main(String[] args) {
		restart();
	}

	public static void restart() {
		Blackjack game = new Blackjack();
		GUI gui = new GUI(game);
	}
}