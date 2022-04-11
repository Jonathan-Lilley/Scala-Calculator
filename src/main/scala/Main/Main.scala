package main

import tokenizer.Tokenizer
import syntaxtree.Parser
import evaluator.Evaluator

object Main {

    def main(args: Array[String]): Unit = {
        //val example = "1+2*((3-4)/6)-5^2"
        val example = "1+2*((3-4)/6)-5^2"
        val tokens = Tokenizer.tokenize(example)
        //println(example)
        /*for (tok <- tokens) System.out.printf("Value: %s\tToken type: %s\tToken level: %s\n",
            tok.value, tok.tokenType, tok.tokenLevel)
        val splittoks = Parser.splitOnOps(Parser.operators,tokens)
        println(tokens.dropRight(splittoks.length)(0).value)*/
        /*for (tok <- splittoks) System.out.printf("Value: %s\tToken type: %s\tToken level: %s\n",
            tok.value, tok.tokenType, tok.tokenLevel)*/
        /*val skipped = Parser.checkSkip(tokens)
        for (tok <- skipped) System.out.printf("Value: %s\tToken type: %s\tToken level: %s\n",
            tok.value, tok.tokenType, tok.tokenLevel)*/
        //val split = Parser.splitOnOp("-",tokens)
        //for (tok <- split) System.out.printf("Value: %s\tToken type: %s\tToken level: %s\n",
            //tok.value, tok.tokenType, tok.tokenLevel)

        val tree = Parser.parse(tokens)
        //println()
        //tree.printTree()

        println(Evaluator.evaluate(tree))
}

}