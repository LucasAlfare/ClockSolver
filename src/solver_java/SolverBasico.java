package solver_java;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Esta classe funciona como um solucionador básico e gerador de embaralhementos
 * para o quebra-cabeças Rubik's Clock.
 *
 * O método de resolução aqui é básico e é o que utilizo de forma pessoal.
 */
@SuppressWarnings({"WeakerAccess", "SpellCheckingInspection"})
public class SolverBasico {

    /**
     * Este array guarda todos os índices dos relógios que irão ser
     * acessados durante a resolução
     */
    public static final int[] ORDEM_ALVOS = {
            //cruz frente
            1,
            3,
            7,
            5,

            //all
            0,

            /*y2*/

            //cruz traseira
            1,
            3,
            7,
            5,

            //cantos traseira
            0,
            2,
            6,
            8,

            //all
            0
    };

    /**
     * Este array guarda as configurações dos pinos, que serão usados
     * durante a resolução.
     */
    private static final boolean[][] ORDEM_PINOS = {
            {false, false, false, true},
            {false, true, false, false},
            {true, false, false, false},
            {true, false, true, false},

            {true, true, true, true},

            {false, false, false, true},
            {false, true, false, false},
            {true, false, false, false},
            {true, false, true, false},

            {false, true, true, true},
            {true, false, true, true},
            {true, true, false, true},
            {true, true, true, false},

            {true, true, true, true}
    };

    /**
     * Este array guarda os nomes dos movimentos de cada etapa da solve,
     * para posterior geração de embaralhamento.
     *
     * Estes movimentos são respectivos à meu método (sequência) pessoal
     * de resolução de um Rubik's Clock. Dessa forma, em todos os movimentos
     * os giros das engrenagens são realizados, obrigatoriamente, em uma en-
     * grenagem correspondente a algum pino que esteja levantado (seu estado
     * deve ser {@code true}).
     */
    private static final String[] MOVIMENTOS = {
            "DR",
            "UR",
            "UL",
            "L",
            "ALL",

            "DR",
            "UR",
            "UL",
            "L",
            "ul",
            "ur",
            "dl",
            "dr",
            "ALL"
    };

    /**
     * Este array auxiliar guarda os valores das quantidades de giros
     * a serem realizados em cada etapa da resolução encontrada.
     */
    private static final Integer[] MOVIMENTOS_SOLVE = new Integer[14];

    /**
     * Este método pega um Clock e procura por todos as quantidades de
     * giros que levam os ponteiros à posição 12/0.
     *
     * Este método deve ser chamado sempre que for  for obter-se um
     * embaralhamento.
     *
     * @param c algum clock.
     */
    public static void solve(Clock c){
        for (int i = 0; i < 14; i++) {
            c.pinos = ORDEM_PINOS[i];
            //calcula a diferença
            int d = dif(
                    (i != 4 && i != 13 ? c.relogios[ORDEM_ALVOS[i]] : ORDEM_ALVOS[i]),
                    c.relogios[4]);
            //movimenta a diferença calculada no Clock do parametro
            c.girar(d);
            if (i == 4) c.y2();

            //este operador força a salvar -6 para prevenir
            //que o método gerar() adicionar um xx6- à solução
            MOVIMENTOS_SOLVE[i] = d == 6 ? -6 : d;
        }
    }

    /**
     * Este método inverte a sequência de resolução de algum clock
     * que tenha sido repassado e adiciona os nomes de movimentos,
     * gerando, então, um embaralhamento na categoria random state.
     *
     * @return um embaralhamento random state a partir dos valores
     *          gerados pelo método {@code solve(Clock)}.
     */
    @SuppressWarnings("StringConcatenationInLoop")
    public static String randomState(){
        ArrayList<Integer> solveReversa = new ArrayList<>(Arrays.asList(MOVIMENTOS_SOLVE));
        ArrayList<String> movimentosSolveReversos = new ArrayList<>(Arrays.asList(MOVIMENTOS));

        //inverte ambas as listas
        Collections.reverse(solveReversa);
        Collections.reverse(movimentosSolveReversos);

        String s = "";
        for (int i = 0; i < solveReversa.size(); i++) {
            int atual = solveReversa.get(i) * -1;
            s +=
                    movimentosSolveReversos.get(i) +
                            Math.abs(atual) + (atual < 0 ? "-" : "+") + " ";
            if (i == 8) s += "y2 ";
        }

        return s;
    }

    /**
     * Sempre retorna um valor entre -6 e 6.
     *
     * @param relogioAlvo
     * @param relogioCentro
     * @return diferença de valores entre os relógios, ou seja, movimento necessário para
     *          deixar o centro igual ao alvo.
     */
    private static int dif(int relogioAlvo, int relogioCentro){
        int d = relogioAlvo - relogioCentro;
        return d > 6 ? d - 12 : (d < (-6) ? 12 + d : d);
    }
}