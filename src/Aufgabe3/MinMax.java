package Aufgabe3;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MinMax {
    public static int knoten = 0;

    public static KalahBoard minMax(KalahBoard board,int limit,char spieler) {
        knoten = 0;
        KalahBoard ausgabe = board.possibleActions().stream().max((b1,b2) -> Integer.compare(min(b1,limit,spieler),min(b2,limit,spieler))).get();
        System.out.println("erstellte Knoten (Ohne Alpha-Beta und Ohne Sortierung): "+knoten);
        return ausgabe;
    }
    private static int min(KalahBoard board,int limit,char spieler){
        if((board.isFinished() || limit <= 0) && spieler == 'A')
            return board.evaluationA();
        else if ((board.isFinished() || limit <= 0) && spieler == 'B')
            return board.evaluationB();
        int v = Integer.MAX_VALUE;
        for (var b: board.possibleActions()) {
            knoten=knoten+1;
            v = Math.min(v, max(b,limit--,spieler));
        }
        return v;
    }
    private static int max(KalahBoard board,int limit,char spieler){
        if((board.isFinished() || limit <= 0) && spieler == 'A')
            return board.evaluationA();
        else if ((board.isFinished() || limit <= 0) && spieler == 'B')
            return board.evaluationB();
        int v = Integer.MIN_VALUE;
        for (var b:board.possibleActions()) {
            knoten = knoten+1;
            v = Math.max(v,min(b,limit--,spieler));
        }
        return v;
    }
    public static KalahBoard minMaxAlphaBeta(KalahBoard board, int limit, char spieler) {
        knoten = 0;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        KalahBoard ausgabe = board.possibleActions().stream().max((b1, b2) -> Integer.compare(minAlphaBeta(b1, limit, spieler, alpha, beta), minAlphaBeta(b2, limit, spieler, alpha, beta))).get();
        System.out.println("erstellte Knoten (mit Alpha-Beta und mit Sortierung): " + knoten);
        return ausgabe;
    }

    private static int minAlphaBeta(KalahBoard board, int limit, char spieler, int alpha, int beta) {
        if ((board.isFinished() || limit <= 0) && spieler == 'A')
            return board.evaluationA();
        else if ((board.isFinished() || limit <= 0) && spieler == 'B')
            return board.evaluationB();
        int v = Integer.MAX_VALUE;
        List<KalahBoard> sortedActions;
        if(spieler == 'A')
            sortedActions = board.possibleActions().stream().sorted(Comparator.comparingInt(KalahBoard::evaluationA)).collect(Collectors.toList());
        else
            sortedActions = board.possibleActions().stream().sorted(Comparator.comparingInt(KalahBoard::evaluationB)).collect(Collectors.toList());
        for (var b : sortedActions) {
            knoten = knoten + 1;
            v = Math.min(v, maxAplhaBeta(b, limit--, spieler, alpha, beta));
            if (v >= alpha) return v;
            beta = Math.min(beta, v);
        }
        return v;
    }

    private static int maxAplhaBeta(KalahBoard board, int limit, char spieler, int alpha, int beta) {
        if ((board.isFinished() || limit <= 0) && spieler == 'A')
            return board.evaluationA();
        else if ((board.isFinished() || limit <= 0) && spieler == 'B')
            return board.evaluationB();
        int v = Integer.MIN_VALUE;
        List<KalahBoard> sortedActions;
        if(spieler == 'A')
            sortedActions = board.possibleActions().stream().sorted(Comparator.comparingInt(KalahBoard::evaluationA).reversed()).collect(Collectors.toList());
        else
            sortedActions = board.possibleActions().stream().sorted(Comparator.comparingInt(KalahBoard::evaluationB).reversed()).collect(Collectors.toList());
        for (var b : sortedActions) {
            knoten = knoten + 1;
            v = Math.max(v, minAlphaBeta(b, limit--, spieler, alpha, beta));
            if (v >= beta)
                return v;
            alpha = Math.max(alpha, v);
        }
        return v;
    }
    public static KalahBoard minMaxAlphaBetaWOSorted(KalahBoard board, int limit, char spieler) {
        knoten = 0;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        KalahBoard ausgabe = board.possibleActions().stream().max((b1, b2) -> Integer.compare(minAlphaBetaWOSorted(b1, limit, spieler, alpha, beta), minAlphaBetaWOSorted(b2, limit, spieler, alpha, beta))).get();
        System.out.println("erstellte Knoten (mit Alpha und ohne Sortierung): " + knoten);
        return ausgabe;
    }

    private static int minAlphaBetaWOSorted(KalahBoard board, int limit, char spieler, int alpha, int beta) {
        if ((board.isFinished() || limit <= 0) && spieler == 'A')
            return board.evaluationA();
        else if ((board.isFinished() || limit <= 0) && spieler == 'B')
            return board.evaluationB();
        int v = Integer.MAX_VALUE;
        for (var b : board.possibleActions()) {
            knoten = knoten + 1;
            v = Math.min(v, maxAplhaBetaWOSorted(b, limit--, spieler, alpha, beta));
            if (v >= alpha) return v;
            beta = Math.min(beta, v);
        }
        return v;
    }

    private static int maxAplhaBetaWOSorted(KalahBoard board, int limit, char spieler, int alpha, int beta) {
        if ((board.isFinished() || limit <= 0) && spieler == 'A')
            return board.evaluationA();
        else if ((board.isFinished() || limit <= 0) && spieler == 'B')
            return board.evaluationB();
        int v = Integer.MIN_VALUE;
        for (var b : board.possibleActions()) {
            knoten = knoten + 1;
            v = Math.max(v, minAlphaBetaWOSorted(b, limit--, spieler, alpha, beta));
            if (v >= beta)
                return v;
            alpha = Math.max(alpha, v);
        }
        return v;
    }
}
