package solver_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        inverter();

        /*
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
         */
    }

    static ArrayList<Integer> lista = new ArrayList<>(Arrays.asList(1, 2, 30, 40));
    public static void inverter(){
        System.out.println(lista);
        int metade = lista.size() / 2;
        List<Integer> tmp = lista.subList(0, metade);
        for (int i = 0; i < metade; i++) {
            lista.set(i, lista.get(i + metade));
            lista.set(i + metade, tmp.get(i));
            System.out.println("tmp:" + tmp);
        }
        System.out.println(lista);
    }
}