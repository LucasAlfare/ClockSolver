package solver_kotlin

class Clock {

    var relogios: MutableList<Int>
    var pinos: MutableList<Boolean>

    companion object {
        val INDICES_NO_PINO = mutableListOf(
                mutableListOf(0, 1, 3, 4, 11),
                mutableListOf(1, 2, 4, 5, 9),
                mutableListOf(3, 4, 6, 7, 17),
                mutableListOf(4, 5, 7, 8, 15)
        )
    }

    constructor() {
        relogios = mutableListOf(
                0, 0, 0,
                0, 0, 0,
                0, 0, 0,

                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        )

        pinos = mutableListOf(
                false, false,
                false, false
        )
    }

    constructor(relogios: MutableList<Int>, pinos: MutableList<Boolean>) {
        this.relogios = relogios
        this.pinos = pinos
    }

    fun indicesAfetados(): MutableList<Int> {
        val a = mutableListOf<Int>()
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