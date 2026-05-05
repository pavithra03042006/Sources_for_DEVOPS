import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String expr = request.getParameter("expression");
        double result = 0;

        try {
        	CalculatorService service = new CalculatorService();
        	result = service.eval(expr);
        } catch(Exception e){
            response.getWriter().write("Error");
            return;
        }

        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(result));
    }

    private double eval(String expr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int c) {
                while (ch == ' ') nextChar();
                if (ch == c) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                return parseExpression();
            }

            double parseExpression() {
                double x = parseTerm();
                while(true){
                    if(eat('+')) x += parseTerm();
                    else if(eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                while(true){
                    if(eat('*')) x *= parseFactor();
                    else if(eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if(eat('+')) return parseFactor();
                if(eat('-')) return -parseFactor();

                int start = this.pos;
                while((ch >= '0' && ch <= '9') || ch == '.')
                    nextChar();

                return Double.parseDouble(expr.substring(start, this.pos));
            }
        }.parse();
    }
}