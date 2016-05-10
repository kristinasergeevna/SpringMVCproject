import ioc.ReporterApp;
import jdbc.CreateTables;
import jdbc.spring.AppSpringData;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * Unit test for simple App.
 */

@Component
public class ReporterAppTest extends TestCase {
    @Test
    public void testReporter() {
        ReporterApp.main(null);
    }

    @Test
    public void testCreateTables() {
        CreateTables.main(null);
    }
/*
        @Test
        public void testDerbyTest() {
            DerbyTest.main(null);
        }*/
    @Test
    public void testSpringJdbc() {
        AppSpringData.main(null);
    }
}
