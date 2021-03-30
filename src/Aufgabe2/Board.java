package Aufgabe2;

import java.util.*;

/**
 * Klasse Board für 8-Puzzle-Problem
 *
 * @author Önder Tütünci, Matthias Reichenbach
 */
public class Board {

    /**
     * Problmegröße
     */
    public static final int N = 8;

    /**
     * Board als Feld.
     * Gefüllt mit einer Permutation von 0,1,2, ..., 8.
     * 0 bedeutet leeres Feld.
     */
    protected int[] board = new int[N + 1];

    /**
     * Generiert ein zufälliges Board.
     */
    public Board() {
        boolean first = false;
        while (!this.parity() || !first){
            ArrayList<Integer> puzzle = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                puzzle.add(i);
            }
            Random zufall = new Random();
            for (int i = 0; i < board.length; i++) {
                board[i] = puzzle.remove(zufall.nextInt(puzzle.size()));
            }
            first = true;
        }
    }

    /**
     * Generiert ein Board und initialisiert es mit board.
     *
     * @param board Feld gefüllt mit einer Permutation von 0,1,2, ..., 8.
     */
    public Board(int[] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Puzzle{" + "board=" + Arrays.toString(board) + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        return Arrays.equals(this.board, other.board);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.hashCode(this.board);
        return hash;
    }

    /**
     * Paritätsprüfung.
     *
     * @return Parität.
     */
    public boolean parity() {
        Set<String> test = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i] > board[j] && i < j && board[i] != 0 && board[j] != 0) {
                    test.add("" + board[j] + "," + board[i]);
                }
            }
        }
        return test.size() % 2 == 0;
    }

    /**
     * Heurstik h1. (siehe Aufgabenstellung),Monoton weil die Kosten >= sind vonnächsten Schritt
     *
     * @return Heuristikwert.
     */
    public int h1() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            if (i != board[i] && board[i] != 0)
                counter++;
        }
        return counter;
    }

    /**
     * Heurstik h2. (siehe Aufgabenstellung),Monoton weil die Kosten >= sind vonnächsten Schritt
     *
     * @return Heuristikwert.
     */
    public int h2() {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 0) {
                int stein = board[i];
                int destinationSpalte = stein % 2;
                int destinationZeile = stein / 2;
                int sourceSpalte = i % 2;
                int sourceZeile = i / 2;
                sum += Math.abs(sourceSpalte - destinationSpalte) + Math.abs(sourceZeile - destinationZeile);
            }
        }
        return sum;
    }

    /**
     * Liefert eine Liste der möglichen Aktion als Liste von Folge-Boards zurück.
     *
     * @return Folge-Boards.
     */
    public List<Board> possibleActions() {
        List<Board> boardList = new LinkedList<>();
        int idx0 = 0;
        while (board[idx0] != 0)
            idx0++;
        if (idx0 == 0) {
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 0, 1)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 0, 3)));
        } else if (idx0 == 1) {
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 1, 0)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 1, 2)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 1, 4)));
        } else if (idx0 == 2){
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 2, 1)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 2, 5)));
        } else if (idx0 == 3){
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 3, 0)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 3, 6)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 3, 4)));
        } else if (idx0 == 4){
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 4, 1)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 4, 3)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 4, 5)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 4, 7)));
        } else if(idx0 == 5){
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 5, 2)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 5, 4)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 5, 8)));
        } else if (idx0 == 6){
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 6, 3)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 6, 7)));
        } else if (idx0 == 7){
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 7, 4)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 7, 6)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 7, 8)));
        } else if(idx0 == 8){
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 8, 7)));
            boardList.add(new Board(swap(Arrays.copyOf(board, board.length), 8, 5)));
        }

        return boardList;
    }

    private int[] swap(int[] a, int p1, int p2) {
        int temp = a[p1];
        a[p1] = a[p2];
        a[p2] = temp;
        return a;
    }

    /**
     * Prüft, ob das Board ein Zielzustand ist.
     *
     * @return true, falls Board Zieltzustand (d.h. 0,1,2,3,4,5,6,7,8)
     */
    public boolean isSolved() {
        int[] solved = {0,1,2,3,4,5,6,7,8};
        return Arrays.equals(board,solved);
    }


    public static void main(String[] args) {
        Board b = new Board(new int[]{7, 2, 4, 5, 0, 6, 8, 3, 1});        // abc aus Aufgabenblatt
        Board goal = new Board(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});

        for (int i = 0; i < 10; i++) {
            Board test = new Board();
            System.out.println(test);
            System.out.println(test.parity());
        }

        System.out.println(b);
        System.out.println(b.parity());
        System.out.println(b.h1());
        System.out.println(b.h2());

        for (Board child : b.possibleActions())
            System.out.println(child);

        System.out.println(b.isSolved());
        System.out.println(goal.isSolved());
    }
}
	
