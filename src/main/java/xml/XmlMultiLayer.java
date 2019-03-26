package xml;

import org.apache.log4j.Logger;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class XmlMultiLayer {
    private static final Logger LOGGER = Logger.getLogger(XmlMultiLayer.class);

    public void initMultiLayer() {
        List<Map<List<Set<Integer>>, String>> multiLayer = new ArrayList<>();
        Integer number = 100;
        Set<Integer> set = new HashSet<>(number);
        List<Set<Integer>> list = new ArrayList<>();
        list.add(set);
        Map<List<Set<Integer>>, String> map = new HashMap<>();
        map.put(list, "TEST");
        multiLayer.add(map);

    }
    public void convertToXml(List list) {
        try (FileOutputStream xmlFile = new FileOutputStream("listStructure.xml")) {
            XMLEncoder xmlEncoder = new XMLEncoder(xmlFile);
            xmlEncoder.writeObject(list);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
