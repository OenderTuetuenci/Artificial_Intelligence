package Aufgabe2;

import java.util.*;

/**
 * @author Önder Tütünci, Matthias Reichenbach
 */
public class A_Star {
    // cost ordnet jedem Board die Aktuellen Pfadkosten (g-Wert) zu.
    // pred ordnet jedem Board den Elternknoten zu. (siehe Skript S. 2-25).
    // In cost und pred sind genau alle Knoten der closedList und openList enthalten!
    // Nachdem der Zielknoten erreicht wurde, lässt sich aus cost und pred der Ergebnispfad ermitteln.
    private static HashMap<Board, Integer> cost = new HashMap<>();
    private static HashMap<Board, Board> pred = new HashMap<>();

    // openList als Prioritätsliste.
    // Die Prioritätswerte sind die geschätzen Kosten f = g + h (s. Skript S. 2-66)
    private static IndexMinPQ<Board, Integer> openList = new IndexMinPQ<>();

    public static Deque<Board> aStar(Board startBoard) {
        if (startBoard.isSolved())
            return new LinkedList<>();
        openList.add(startBoard, startBoard.h2());
        cost.put(startBoard, 0);
        pred.put(startBoard, null);
        Set<Board> closedList = new HashSet<>();

        while (!openList.isEmpty()) {
            Board n = openList.removeMin();
            //System.out.println(n);
            if (n.isSolved())
                return result(n);
            closedList.add(n);
            for (Board b : n.possibleActions()) {
                if (openList.get(b) == null && !closedList.contains(b)) {
                    openList.add(b, cost.get(n) + b.h2() + 1);
                    cost.put(b, cost.get(n) + 1);
                    pred.put(b, n);
                } else if (openList.get(b) != null) {
                    if (cost.get(b) > cost.get(n) + 1) {
                        openList.change(b, cost.get(n) + 1);
                        cost.put(b, cost.get(n) + 1);
                        pred.put(b, n);
                    }
                }
            }
        }
        return null; // Keine Lösung
    }

    private static Deque<Board> result(Board ziel) {
        LinkedList<Board> path = new LinkedList<>();
        for (Board s = ziel; s != null; s = pred.get(s)) {
            if (pred.get(s) == null)
                break;
            path.addFirst(s);
        }
        return path;
    }
}
