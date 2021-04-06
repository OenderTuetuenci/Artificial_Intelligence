package Aufgabe3;

public class MinMax {
    public static int knoten = 0;

    public static KalahBoard minMax(KalahBoard board,int limit,char spieler) {
        knoten = 0;
        KalahBoard ausgabe = board.possibleActions().stream().max((b1,b2) -> Integer.compare(min(b1,limit,spieler),min(b2,limit,spieler))).get();
        System.out.println("erstellte Knoten: "+knoten);
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
}
