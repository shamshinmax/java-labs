import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionEvaluatorTest {

    @Test
    void evaluatesSimpleExpression() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(7.0, evaluator.evaluate("3+4"), 1e-9);
    }

    @Test
    void respectsOperatorPrecedence() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(14.0, evaluator.evaluate("2+3*4"), 1e-9);
    }

    @Test
    void evaluatesParenthesesCorrectly() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(20.0, evaluator.evaluate("(2+3)*4"), 1e-9);
    }

    @Test
    void supportsUnaryMinus() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertEquals(-3.0, evaluator.evaluate("-5+2"), 1e-9);
        assertEquals(-10.0, evaluator.evaluate("-(2+3)*2"), 1e-9);
    }

    @Test
    void throwsOnDivisionByZero() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("10/0"));
    }

    @Test
    void throwsOnInvalidExpression() {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("2+*3"));
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("(2+3"));
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(""));
    }
}
