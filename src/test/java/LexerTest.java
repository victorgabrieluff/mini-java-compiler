import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.piler.Lexer;
import com.piler.Token;

public class LexerTest {
    Lexer lexer = new Lexer();
    
    @Test
    public void test() {
        String input = "boolean x = true;";
        List<Token> tokens = lexer.lex(input);

        List<Token> expected = List.of(
            new Token("boolean", "boolean"),
            new Token("ID", "x"),
            new Token("ASSIGN", "="),
            new Token("true", "true"),
            new Token("semicolon", ";")
        );

        assertEquals(tokens, expected);
    }
}
