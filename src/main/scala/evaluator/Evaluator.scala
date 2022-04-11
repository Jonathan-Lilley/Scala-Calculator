package evaluator

import syntaxtree.Parser.SyntaxTree

object Evaluator {

    def compute(op: String, left: Double, right: Double): Double = op match {
        case "+" => left + right
        case "-" => left - right
        case "*" => left * right
        case "/" => left / right
        case "^" => scala.math.pow(left,right)
    }

    def evaluate(tree: SyntaxTree): Double = {
        if (tree.value.tokenLevel == "Value") tree.value.value.toDouble
        else compute(tree.value.value, evaluate(tree.leftChild), evaluate(tree.rightChild))
    }

}