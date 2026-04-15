package lab2;

/**
 * вычисляет выражения со скобками и операциями
 */
public class ExpressionEvaluator {
    private String expression;
    private int pos;

    /**
     * вычисляет значение выражения
     *
     * @param expression строка выражения
     * @return вычисленный результат
     * @throws IllegalArgumentException если выражение некорректно
     */
    public double evaluate(String expression) {
        if (expression == null || expression.isBlank()) {
            throw new IllegalArgumentException("Пустое выражение");
        }

        this.expression = expression;
        this.pos = 0;

        double result = parseExpression();
        skipSpaces();

        if (pos != this.expression.length()) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        return result;
    }

    /**
     * разбирает выражение уровня сложения и вычитания
     *
     * @return значение выражения на текущем уровне приоритета
     */
    private double parseExpression() {
        double value = parseTerm();
        while (true) {
            skipSpaces();
            if (match('+')) {
                value += parseTerm();
            } else if (match('-')) {
                value -= parseTerm();
            } else {
                return value;
            }
        }
    }

    /**
     * разбирает выражение уровня умножения и деления
     *
     * @return значение терма на текущем уровне приоритета
     */
    private double parseTerm() {
        double value = parseFactor();
        while (true) {
            skipSpaces();
            if (match('*')) {
                value *= parseFactor();
            } else if (match('/')) {
                double divisor = parseFactor();
                if (divisor == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                value /= divisor;
            } else {
                return value;
            }
        }
    }

    /**
     * разбирает унарный знак, выражение в скобках или число
     *
     * @return значение фактора
     */
    private double parseFactor() {
        skipSpaces();
        if (match('+')) {
            return parseFactor();
        }
        if (match('-')) {
            return -parseFactor();
        }
        if (match('(')) {
            double value = parseExpression();
            skipSpaces();
            if (!match(')')) {
                throw new IllegalArgumentException("Некорректные скобки");
            }
            return value;
        }
        return parseNumber();
    }

    /**
     * считывает число с текущей позиции 
     *
     * @return считанное число
     * @throws IllegalArgumentException если число записано некорректно
     */
    private double parseNumber() {
        skipSpaces();
        int start = pos;
        boolean hasDot = false;

        while (pos < expression.length()) {
            char c = expression.charAt(pos);
            if (Character.isDigit(c)) {
                pos++;
            } else if (c == '.') {
                if (hasDot) {
                    throw new IllegalArgumentException("Некорректное число");
                }
                hasDot = true;
                pos++;
            } else {
                break;
            }
        }

        if (start == pos) {
            throw new IllegalArgumentException("Ожидалось число");
        }

        return Double.parseDouble(expression.substring(start, pos));
    }

    /**
     * проверяет совпадает ли текущий символ с ожидаемым и сдвигает позицию
     *
     * @param expected ожидаемый символ
     * @return true, если символ совпал
     */
    private boolean match(char expected) {
        if (pos < expression.length() && expression.charAt(pos) == expected) {
            pos++;
            return true;
        }
        return false;
    }

    /**
     * пропускает все пробельные символы начиная с текущей позиции
     */
    private void skipSpaces() {
        while (pos < expression.length() && Character.isWhitespace(expression.charAt(pos))) {
            pos++;
        }
    }
}
