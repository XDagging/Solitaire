package resources;

public class Main {

	public static void main(String[] args) {
		restart();
	}

	public static void restart() {
		Solitaire game = new Solitaire();
		GUI gui = new GUI(game);
	}
}