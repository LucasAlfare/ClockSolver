package solver_kotlin

import java.util.*
import kotlin.collections.ArrayList

class Clock() {

    var relogios: ArrayList<Int>
    var pinos: ArrayList<Boolean>

    init {
        relogios = arrayListOf(
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,

                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        )
        pinos = arrayListOf(
                false, false,
                false, false
        )
    }

    fun girar(quantidade: Int){
        for (r in indicesAfetados()){
            girarRelogio(r, quantidade * (if (r > 8) -1 else 1))
        }
    }

    fun girarRelogio(indice: Int, movimento: Int){
        for (i in 0..(if (movimento > 0) movimento else (12 - (movimento * -1)))){
            relogios[indice]++
            if (relogios[indice] == 12) relogios[indice] = 0
        }
    }

    fun indicesAfetados(): ArrayList<Int> {
        val a = arrayListOf<Int>()
        for (i in 0..(pinos.size - 1)){
            if (pinos[i]){
                for (j in INDICES_NO_PINO[i]){
                    if (!a.contains(j)){
                        a.add(j)
                    }
                }
            }
        }

        return a
    }

    fun pressionarPino(pino: Int){
        pinos[pino] = !pinos[pino]
    }

    fun y2(){
        val tmp = ArrayList(relogios.subList(0, 9))
        for (i in (0 until 9)){
            relogios[i] = relogios[i+9]
            relogios[i+9] = tmp[i]
        }
    }

    companion object {
        private val INDICES_NO_PINO = arrayListOf(
                arrayListOf(0, 1, 3, 4, 11),
                arrayListOf(1, 2, 4, 5, 9),
                arrayListOf(3, 4, 6, 7, 17),
                arrayListOf(4, 5, 7, 8, 15)
        )

        fun randomClock() : Clock {
            val c = Clock()
            val r = Random()
            for (i in (0 until c.relogios.size)){
                c.relogios[i] = r.nextInt(12)
            }

            return c
        }
    }

    override fun toString(): String {
        var s = ""

        for (i in 1..(relogios.size)){
            val r = relogios[i - 1]
            s += (r).toString() + (if (r < 10) " " else "") + " "

            if (i % 3 == 0) s += "\n"
            if (i == 9) s += "\n"
        }

        return "$s$pinos"
    }
}