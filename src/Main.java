import java.util.Scanner;

public class Main {
    char token;
    private String s;
    private int index = 0;

    public Main() {
        start();
    }

    public static void main() {
        new Main();
    }

    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Please enter your query (end to end): ");
            s = scanner.nextLine();
            if (s.equalsIgnoreCase("end")) {
                break;
            }
            s += "\n";
            getToken();
            command();
        }
    }

    void error(String err) {
        System.err.printf("Error: %s\n", err);
    }

    void getToken() {
        token = s.toCharArray()[index++];
    }

    void match(char c, String msg) {
        if (token == c)
            getToken();
        else
            error(msg);
    }

    void command() {
        double result = expr();
        if (token == '\n')
            System.out.printf("The result is %f\n", result);
        else
            System.err.printf("Token after end of expression");
    }

    double expr() {
        double result = term();
        while (token == '+') {
            match('+', "+ expected");
            result += term();
        }
        while (token == '-') {
            match('-', "- expected");
            result -= term();
        }

        return result;
    }

    double term() {
        double result = factor();
        while (token == '*') {
            match('*', "* expected");
            result *= factor();
        }
        while (token == '/') {
            match('/', "/ expected");
            result /= factor();
        }
        while (token == '^') {
            match('^', "^ expected");
            result = Math.pow(result, term());
        }
        while (token == '%') {
            match('%', "% expected");
            result %= term();
        }
        return result;
    }

    double factor() {
        double result;
        if (token == '(') {
            match('(', "( expected");
            result = expr();
            match(')', ") expected");
        } else {
            result = decimalNumber();
        }
        return result;
    }

    double decimalNumber() {
        double result = number();

        if (token == '.') {
            getToken();
            double r = number();
            while (r >= 1) {
                r /= 10;
            }
            result += r;
        }
        return result;
    }

    double number() {
        double result = digit();
        while (Character.isDigit(token)) {
            result = 10 * result + digit();
        }
        return result;
    }

    double digit() {
        double result = 0;
        if (Character.isDigit(token)) {

            result = token - '0';
            match(token, "( expected");
        } else {
            error("digit expected");
        }
        return result;
    }
}
