/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Faritko
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@ManagedBean
public class FileUploadView {
    private final static List<String> atr = new ArrayList<String>();  
    private UploadedFile file;
    private final static Map<String, List<String>> my_map = new HashMap<String, List<String>>();

    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() throws ParserConfigurationException, SAXException, IOException {   
        //маппинг локальных кодов
        my_map.put("Идентификатор", Arrays.asList("ID","Id","id","ид","айди"));
        my_map.put("Дата", Arrays.asList("Дата","дата","date","Date","data"));
        my_map.put("Госномер", Arrays.asList("GosNum","GovNum","GosNumber","GovNumber","Госномер","госномер","gn"));
        my_map.put("Имя", Arrays.asList("name","Name","Names","names"));
        my_map.put("ОКТМО", Arrays.asList("октмо","Октмо","Oktmo","oktmo"));
        my_map.put("Регион", Arrays.asList("district","District","region","Region"));
        my_map.put("Телефон", Arrays.asList("Phone","phone","telephone","telefon"));
        my_map.put("Логин", Arrays.asList("login","Login","логин"));
        my_map.put("Пароль", Arrays.asList("пароль","parol","password","pass","Password","Pass"));
        my_map.put("Комментарий", Arrays.asList("comment","Comment","коммент","комментарий","Коммент"));
        my_map.put("Статус", Arrays.asList("status","статус","state"));
        my_map.put("Организация", Arrays.asList("организация","организ-я","organization","Organization"));
        my_map.put("Фамилия", Arrays.asList("surname","Surname","organization","Organization"));
        my_map.put("Отчество", Arrays.asList("patronymic","Patronymic","organization","Organization"));
        //////////////////////////////////
     
        Path directory = Paths.get("C:\\test");
        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        
        Path f = Files.createTempFile(directory, "ImportXML", ".xml");
        File xmlImport = new File(f.toString());
        try (InputStream input = file.getInputstream()) {
            Files.copy(input, f, StandardCopyOption.REPLACE_EXISTING);
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlImport);
            visit(document, 0); 
            Path ListPath = Files.createTempFile(directory, "ExportList", ".txt");
            Set<String> set = new LinkedHashSet<>(atr);
            try (FileWriter writer = new FileWriter(ListPath.toString())) {
            for(String str: set) {
                writer.write(str);
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        }
        } catch (IOException e) {
                FacesMessage error = new FacesMessage("The files were  not uploaded!");
                FacesContext.getCurrentInstance().addMessage(null, error);
            }
    }
    
    public static void visit(Node node, int level) { 
        NodeList list = node.getChildNodes(); 
        for (int i = 0; i < list.getLength(); i++) { 
            Node childNode = list.item(i); // текущий нод 
            process(childNode, level + 1); // обработка 
            visit(childNode, level + 1); // рекурсия 
        } 
    } 
    
    public static void process(Node node, int level) { 
      if (!node.getNodeName().contains("#"))
      {
          for (Entry entry: my_map.entrySet())
          {
              List<String> tmp = new ArrayList<String>((Collection<? extends String>) entry.getValue());
              if (tmp.contains(node.getNodeName()))
                  atr.add(entry.getKey().toString());
          }
      }    
    }
  /*
    public void addTables() {
        try {
            idRoleT = ConnectToDB.getData("\"ID\"", "role where role.name='" + selectedRoleT + "'").data;
            ConnectToDB.deleteData("role_tables", "\"ID_role\"=" + idRoleT.get(0) + "");
            for (int i = 0; i < selectedTable.size(); i++) {
                for (int j = 2; j < tableall.size(); j = j + 3) {
                    if (selectedTable.get(i).equals(tableall.get(j))) {                    
                        ConnectToDB.insertData("role_tables( \"ID_role\", \"ID_tables\")", idRoleT.get(0) + ", " + tableall.get(j-2));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomizeRole.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
