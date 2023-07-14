import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Некорректное значение метода getLocalNumber()", 14, getLocalNumber());
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Метод getClassNumber() возвращает число меньше 45 (" + getClassNumber() + ")",
                getClassNumber() > 45);
    }
}
