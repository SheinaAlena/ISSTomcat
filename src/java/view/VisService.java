package view;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "visService")
@ApplicationScoped
public class VisService {
    private final static List<String> names = new ArrayList<String>(); 
    private final static List<String> adresses = new ArrayList<String>();  
    private final static List<String> types = new ArrayList<String>(); 
    private final static List<String> ids = new ArrayList<String>(); 
        
    static {
            names.add("VMS");
            names.add("РИТМ");
            names.add("Wialon");

            adresses.add("http://62.133.191.98:47227/vms-ws/services/TransportWS?wsdl");        
            adresses.add("http://10.78.8.72:8080/idp-web/idp/rest/objects/disarm");
            adresses.add("1.xml");
                
            types.add("SOAP/XML");
            types.add("REST/JSON");       
            types.add("Файл");   
            
            ids.add("1");
            ids.add("2");
            ids.add("3");
	}
    
//    public List<Vis> createVis(int size) {
//        List<Vis> list = new ArrayList<Vis>();
//            for(int i = 0 ; i < size; i++) {
//		list.add(new Vis(getId(i), getName(i), getType(i), getAdress(i)));
//            }       
//        return list;
//    }
//    
     private String getName(int i) {
        return names.get(i);
    }
     
    public List<String> getNames() {
        return names;
    }
     
    private String getAdress(int i) {
        return adresses.get(i);
    }
    
    public List<String> getAdresses() {
        return adresses;
    }
    
    private String getType(int i) {
        return types.get(i);
    }
    
    public List<String> getTypes() {
        return types;
    }
        
    private String getRandomId() {
        return "0000"+(int)(Math.random() * 50+1000);
    }
    
    private String getId(int i) {
        return ids.get(i);
    }
}