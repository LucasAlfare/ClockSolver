/*
 * Copyright (c) 2018. Criado por @Lucas Sousa
 */

package kotlinn

fun main(args: Array<String>) {
    val x = SolverBasico
    x.resolver(Clock.randomClock())
    println(x.randomState())
}