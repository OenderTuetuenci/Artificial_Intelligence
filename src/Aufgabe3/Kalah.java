package Aufgabe3;

/**
 * Hauptprogramm für KalahMuster.
 * @since 29.3.2021
 * @author oliverbittel
 */
public class Kalah {
	
	private static final String ANSI_BLUE = "\u001B[34m";

	/**
	 *
	 * @param args wird nicht verwendet.
	 */
	public static void main(String[] args) {
		//testExample();
		//testHHGame();
		//HNGame();
		NNGame();
	}
	
	/**
	 * Beispiel von https://de.wikipedia.org/wiki/Kalaha
	 */
	public static void testExample() { 
		KalahBoard kalahBd = new KalahBoard(new int[]{5,3,2,1,2,0,0,4,3,0,1,2,2,0}, 'B');
		kalahBd.print();

		System.out.println("B spielt Mulde 11");
		kalahBd.move(11);
		kalahBd.print();

		System.out.println("B darf nochmals ziehen und spielt Mulde 7");
		kalahBd.move(7);
		kalahBd.print();
	}
	
	/**
	 * Mensch gegen Mensch
	 */
	public static void testHHGame() {
		KalahBoard kalahBd = new KalahBoard();
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			int action = kalahBd.readAction();
			kalahBd.move(action);
			kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	public static void HNGame(){
		KalahBoard kalahBd = new KalahBoard();
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			System.out.println("AI emphelung");
			MinMax.minMax(kalahBd, 5, 'A').print();
			int action = kalahBd.readAction();
			kalahBd.move(action);
			kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
	public static void NNGame(){
		KalahBoard kalahBd = new KalahBoard();
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			if(kalahBd.getCurPlayer() == 'A')
				kalahBd = MinMax.minMax(kalahBd, 4,'A');
			else
				kalahBd = MinMax.minMax(kalahBd,5,'B');
			kalahBd.print();
		}
		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}
}
