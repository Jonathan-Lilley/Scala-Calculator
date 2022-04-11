package syntaxtree

import tokenizer.Tokenizer.Token

object Parser {

    class SyntaxTree(val value: Token, val leftChild: SyntaxTree, val rightChild: SyntaxTree) {

        def printTree(): Unit = {
            println(this.value.value)
            if (this.leftChild != null) this.leftChild.printTree()
            if (this.rightChild != null) this.rightChild.printTree()
        }

    }

    val operators = List("+","-","*","/","^")

    def skipBlock(tokens: List[Token], counter: Int): List[Token] = {
        if (counter == 0 || tokens.isEmpty) tokens
        else if (tokens(0).tokenLevel == "Open") skipBlock(tokens.drop(1), counter + 1)
        else if (tokens(0).tokenLevel == "Close") skipBlock(tokens.drop(1), counter - 1)
        else skipBlock(tokens.drop(1), counter)
    }

    def checkSkip(tokens: List[Token]): List[Token] = {
        if (tokens(0).tokenLevel == "Open") skipBlock(tokens.drop(1),1)
        else tokens.drop(1)
    }

    def splitOnOp(op: String, tokens: List[Token]): List[Token] = {
        if (tokens.length < 2 || tokens(0).value == op) tokens
        else splitOnOp(op, checkSkip(tokens))
    }

    def splitOnOps(ops: List[String], tokens: List[Token]): List[Token] = {
        if (ops.isEmpty) tokens
        else if (splitOnOp(ops(0),tokens).length < 2) splitOnOps(ops.drop(1),tokens)
        else splitOnOp(ops(0),tokens)
    }

    def firstSplit(operators: List[String], tokens: List[Token]): List[Token] = {
        tokens.dropRight(splitOnOps(operators,tokens).length)
    }

    def parse(tokens: List[Token]): SyntaxTree = {

        /*println("recursed")
        tokens.foreach(v => print(v.value+" "))
        //println("operator")
        //println(splitOnOps(operators,tokens)(0).value)
        println()*/

        if (tokens.length == 1) new SyntaxTree(tokens(0),null,null)
        else if (tokens.length > 2 && checkSkip(tokens).isEmpty) parse(tokens.drop(1).dropRight(1))
        else new SyntaxTree(
            splitOnOps(operators,tokens)(0),
            parse(firstSplit(operators, tokens)),
            parse(splitOnOps(operators,tokens).drop(1))
        )
    }

}