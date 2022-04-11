package tokenizer
import scala.annotation.tailrec

object Tokenizer {

    class Token(val value: String, val tokenType: String, val tokenLevel: String) {

        def combineToken(inval: Char): Token = {
            new Token(this.value + inval, "Number", "Value")
        }

    }

    def genToken(inp: Char): Token = {
        inp match {
            case '+' => new Token(inp.toString, "Operator", "AddSub")
            case '-' => new Token(inp.toString, "Operator", "AddSub")
            case '*' => new Token(inp.toString, "Operator", "MultDiv")
            case '/' => new Token(inp.toString, "Operator", "MultDiv")
            case '^' => new Token(inp.toString, "Operator", "Pow")
            case ' ' => new Token(inp.toString, "Whitespace", "Space")
            case '(' => new Token(inp.toString, "Block", "Open")
            case ')' => new Token(inp.toString, "Block", "Close")
            case _ => new Token(inp.toString, "Number", "Value")
        }
    }

    @tailrec def genNumToken(inp: String, tok: Token): Token = {
        if (inp.isEmpty || !inp.charAt(0).isDigit) tok
        else genNumToken(inp.substring(1), tok.combineToken(inp.charAt(0)))
    }

    def tokenize(inp: String): List[Token] = {
        if (inp.isEmpty) List[Token]()
        else if (inp.charAt(0).isDigit) List(genNumToken(inp.substring(1), genToken(inp.charAt(0)))) ++
            tokenize(inp.substring(genNumToken(inp.substring(1), genToken(inp.charAt(0))).value.length))
        else List(genToken(inp.charAt(0))) ++ tokenize(inp.substring(1))
    }
}
