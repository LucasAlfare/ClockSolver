/*
 * Copyright (c) 2018. Criado por @Lucas Sousa
 */

package solverKotlin

import kotlin.collections.ArrayList

object SolverBasico {

    @JvmStatic
    val ORDEM_ALVOS = arrayListOf(
            //cruz frente
            1, 3, 7, 5,

            //all
            0,

            /*y2*/

            //cruz traseira
            1, 3, 7, 5,

            //cantos traseira
            0, 2, 6, 8,

            //all
            0
    )

    @JvmStatic
    val ORDEM_PINOS = arrayListOf(
            arrayListOf(false, false, false, true),
            arrayListOf(false, true, false, false),
            arrayListOf(true, false, false, false),
            arrayListOf(true, false, true, false),

            arrayListOf(true, true, true, true),

            arrayListOf(false, false, false, true),
            arrayListOf(false, true, false, false),
            arrayListOf(true, false, false, false),
            arrayListOf(true, false, true, false),

            arrayListOf(false, true, true, true),
            arrayListOf(true, false, true, true),
            arrayListOf(true, true, false, true),
            arrayListOf(true, true, true, false),

            arrayListOf(true, true, true, true)
    )
    
    @JvmStatic
    val MOVIMENTOS = arrayListOf(
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
    )
    
    @JvmStatic
    val MOVIMENTOS_SOLVE = arrayListOf<Int>()
    
    @JvmStatic
    fun resolver(c: Clock){
        for (i in 0 until 14){
            c.pinos = ORDEM_PINOS[i]
            val d = dif(
                    if (i != 4 && i != 13) c.relogios[ORDEM_ALVOS[i]] else ORDEM_ALVOS[i],
                    c.relogios[4])
            c.girar(d)
            if (i == 4) c.y2()
            MOVIMENTOS_SOLVE.add(if (d == 6) -6 else d)
        }
    }

    @JvmStatic
    fun randomState(): String {
        val solveReversa = ArrayList(MOVIMENTOS_SOLVE.reversed())
        val mReversos = ArrayList(MOVIMENTOS.reversed())

        var s = ""
        for (i in 0 until solveReversa.size){
            val atual = solveReversa[i] * -1
            s += mReversos[i] + Math.abs(atual) + (if (atual < 0) "- " else "+ ")
            if (i == 8) s += "y2 "
        }

        return s
    }

    private fun dif(relogioAlvo: Int, relogioCentro: Int) : Int {
        val d = relogioAlvo - relogioCentro
        return if (d > 6) d - 12 else if (d < -6) 12 + d else d
    }
}