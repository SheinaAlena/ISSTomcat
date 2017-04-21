package view;
 
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
 
@ManagedBean
public class DataExporterView implements Serializable {
     
    private List<Vis> vis;
         
    @ManagedProperty("#{visService}")
    private VisService service;
     
    @PostConstruct
    public void init() {
        //vis = service.createVis(3);
    }
 
    public List<Vis> getVis() {
        return vis;
    }
 
    public void setService(VisService service) {
        this.service = service;
    }
}