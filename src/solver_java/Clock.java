package solver_java;

import java.util.*;

@SuppressWarnings("WeakerAccess")
public class Clock {

    /*
    0 1
    2 3
     */
    public boolean[] pinos;
    public int[] relogios;

    private static final int[][] INDICES_DO_PINO = {
            {0, 1, 3, 4, 11}, //pinos[0]
            {1, 2, 4, 5, 9}, //pinos[1]
            {3, 4, 6, 7, 17}, //pinos[2]
            {4, 5, 7, 8, 15} //pinos[3]
    };

    public Clock() {
        pinos = new boolean[4];
        Arrays.fill(pinos, false);
        relogios = new int[18];
        Arrays.fill(relogios, 0);
    }

    public Clock(int[] relogios, boolean[] pinos) {
        this.relogios = relogios;
        this.pinos = pinos;
    }

    public void girar(int quantidade){
        for (int i : indicesAfetados()) {
            girarRelogio(i, quantidade * (i > 8 ? -1 : 1));
        }
    }

    //TODO: substituir loop por incremento de variável por operador ternário
    public void girarRelogio(int indice, int m){
        for (int i = 0; i < (m > 0 ? m : 12 - (m * -1)); i++) {
            relogios[indice]++;
            if (relogios[indice] == 12) {
                relogios[indice] = 0;
            }
        }
    }

    private ArrayList<Integer> indicesAfetados(){
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < pinos.length; i++) {
            if (pinos[i]){
                for (int r : INDICES_DO_PINO[i]){
                    if (!a.contains(r)){
                        a.add(r);
                    }
                }
            }
        }

        return  a;
    }

    public void pressionarPino(int pino){
        pinos[pino] = !pinos[pino];
    }

    public void y2(){
        int[] tmp = Arrays.copyOfRange(relogios, 0, 9);
        for (int i = 0; i < 9; i++) {
            relogios[i] = relogios[i + 9];
            relogios[i + 9] = tmp[i];
        }
    }

    public static Clock randomClock(){
        Random r = new Random();
        int[] relogios = new int[18];
        for (int i = 0; i < 18; i++) {
            relogios[i] = r.nextInt(12);
        }

        return new Clock(relogios, new boolean[4]);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 1; i < relogios.length + 1; i++) {
            s.append(relogios[i - 1]).append(relogios[i - 1] < 10 ? " " : "").append(" ");
            if (i % 3 == 0) s.append("\n");
            if (i == 9) s.append("\n");
        }

        return s.toString();
    }
}