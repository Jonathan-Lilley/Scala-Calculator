package main

import evaluator.Evaluator

object Main {

    def main(args: Array[String]): Unit = {
        println(Evaluator.evaluate(args(0)))
    }

}

