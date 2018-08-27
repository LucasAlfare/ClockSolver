package solver_java;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> q = new ArrayList<>();
        for (int k = 0; k < 5; k++) {
            long i = System.currentTimeMillis();
            for (int j = 0; j < 100000; j++) {
                //gera e resolve um clock aleatorio
                SolverBasico.solve(Clock.randomClock());
                //gera o embaralhamento para o clock gerado (e resolvido)
                SolverBasico.randomState();
                if ((System.currentTimeMillis() - i) >= (1000L)){
                    q.add(j);
                    break;
                }
            }
        }

        System.out.println(q);

        int s = 0;
        for (int v : q){
            s += v;
        }
        System.out.println("Média: " + s / q.size());

        q.remove(Collections.max(q));
        q.remove(Collections.min(q));
        s = 0;
        for (int v : q){
            s += v;
        }
        System.out.println("Avg: " + s / q.size());
    }
}