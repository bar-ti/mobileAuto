import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Некорректное значение метода getLocalNumber()", 14, getLocalNumber());
    }
}
