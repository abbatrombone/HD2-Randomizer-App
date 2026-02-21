package me.abbatrombone.traz.JSONTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.abbatrombone.traz.Managers.OSManager;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CheckboxJSONReader {
    private final ObjectMapper mapper = new ObjectMapper();
    private Map<String, Boolean> checkBoxvalues;

    public CheckboxJSONReader(){
        String filepath = OSManager.getConfigDirectory() + "/checkbox.json";
        File jsonfile = new File(filepath);

        loadCheckboxes(jsonfile);
    }

    private void loadCheckboxes(File jsonFile){
        try {
            checkBoxvalues = mapper.readValue( jsonFile, new TypeReference<Map<String, Boolean>>() {} );
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load checkbox.json", e);
        }
    }
    public boolean readcheckBoxValues(String key) {
        return checkBoxvalues.getOrDefault(key, false);
    }
}
