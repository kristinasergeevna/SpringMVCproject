package ioc;

import org.springframework.stereotype.Component;

/**
 * Created by SDS on 11.03.2016.
 */
@Component("csvReporter")
public class CSVReporter implements Reporter {
    @Override
    public void generate() {
        System.out.println("CSVReporter");
    }
}
