package ioc;

import org.springframework.stereotype.Component;

/**
 * Created by SDS on 11.03.2016.
 */
@Component
public class HTMLReporter implements Reporter {
    @Override
    public void generate() {
        System.out.println("HTMLReporter");
    }
}
