import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorServiceTest {

    CalculatorService service = new CalculatorService();

    @Test
    public void testAddition() {
        assertEquals(5.0, service.eval("2+3"), 0.001);
    }

    @Test
    public void testSubtraction() {
        assertEquals(1.0, service.eval("3-2"), 0.001);
    }

    @Test
    public void testMultiplication() {
        assertEquals(6.0, service.eval("2*3"), 0.001);
    }

    @Test
    public void testDivision() {
        assertEquals(2.0, service.eval("6/3"), 0.001);
    }

    @Test
    public void testComplexExpression() {
        assertEquals(14.0, service.eval("2+3*4"), 0.001);
    }
}