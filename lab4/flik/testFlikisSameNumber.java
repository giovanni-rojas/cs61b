package flik;
import static org.junit.Assert.*;
import org.junit.Test;

public class testFlikisSameNumber {
    @Test
    public void testSameNumberAt128() {
        assertTrue("these are not the same", Flik.isSameNumber(128, 128));
    }
}
