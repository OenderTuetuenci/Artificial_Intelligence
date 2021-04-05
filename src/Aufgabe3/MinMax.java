package Aufgabe3;

public class MinMax {
    public static int minMax(KalahBoard board) {
        return min(board);
    }
    private static int min(KalahBoard board){
        if(board.isFinished())
            return board.evaluation();
        int v = Integer.MAX_VALUE;
        for (var b: board.possibleActions()) {
            v = Math.min(v, max(b));
        }
        return v;
    }
    private static int max(KalahBoard board){
        if (board.isFinished())
            return board.evaluation();
        int v = Integer.MIN_VALUE;
        for (var b:board.possibleActions()) {
            v = Math.max(v,min(b));
        }
        return v;
    }
}
