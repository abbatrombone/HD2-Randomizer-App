package me.abbatrombone.traz.JSONTools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import me.abbatrombone.traz.Managers.OSManager;
import me.abbatrombone.traz.Panels.SelectBondsPanel;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONCheckboxes {
    private static final Logger logger = Logger.getLogger(JSONCheckboxes.class.getName());

    public JSONCheckboxes(SelectBondsPanel selectBondsPanel){
        String filePath = OSManager.getConfigDirectory() + "/checkbox.json";

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        for(JCheckBox box : selectBondsPanel.getCheckBoxes()){
            rootNode.put(box.getName(), box.isSelected());
        }
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            try (FileWriter writer = new FileWriter(filePath, false)) {
                writer.write(jsonString);
            }
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Json Processing failed. Getting message:\n" + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception. Getting message:\n" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
