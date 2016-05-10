package ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by SDS on 11.03.2016.
 */
@Component
public class ReporterApp {

    private Reporter reporter;

    ReporterApp(final Reporter reporter) {
    this.reporter=reporter;
    }

    public void run() {
        reporter.generate();
    }

    public void setReporter(final Reporter reporter) {
        this.reporter = reporter;
    }

    public static void main(String[] args) {
       /* Reporter reporter=new CSVReporter();
        reporter.generate();
        Reporter reporter1=new HTMLReporter();
        reporter1.generate();
        Reporter reporter2=new PDFReporter();
        reporter2.generate();
        Reporter reporter3= (Reporter) Container.getComponent(args[0]);
        if(reporter3!=null){
            reporter3.generate();
        }*/
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        ReporterApp reporter = (ReporterApp) context.getBean("reporterApp");
        reporter.run();
    }
}
