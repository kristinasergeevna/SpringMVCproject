package ioc;


import java.util.HashMap;
import java.util.Map;


public class Container {
    private static Map<String, Object> container;
    public synchronized static Object getComponent(final String componentName){
        if (container==null){
            container= new HashMap<String, Object>();
        }
        Object result=container.get(componentName);
        if(result==null){
            if("pdfreporter".equals(componentName.toLowerCase())){
                result=new PDFReporter();
            }else if("htmlreporter".equals(componentName.toLowerCase())){
                result =new HTMLReporter();
            }else  if("csvreporter".equals(componentName.toLowerCase())){
                result=new CSVReporter();
            }
            if(result!=null){
                container.put(componentName,result);
            }
        }
        return result;
    }
}
