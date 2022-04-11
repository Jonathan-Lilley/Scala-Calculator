package evaluator

import tokenizer.Tokenizer.tokenize
import syntaxtree.Parser.parse
import syntaxtree.Parser.SyntaxTree

object Evaluator {

    def compute(op: String, left: Double, right: Double): Double = op match {
        case "+" => left + right
        case "-" => left - right
        case "*" => left * right
        case "/" => left / right
        case "^" => scala.math.pow(left,right)
    }

    def evaluateTree(tree: SyntaxTree): Double = {
        if (tree.value.tokenLevel == "Value") tree.value.value.toDouble
        else compute(tree.value.value, evaluateTree(tree.leftChild), evaluateTree(tree.rightChild))
    }

    def evaluate(eqn: String): Double = {
        evaluateTree(parse(tokenize(eqn)))
    }

}