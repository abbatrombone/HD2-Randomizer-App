package me.abbatrombone.traz.JSONTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.abbatrombone.traz.Managers.OSManager;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONReader {
    private final ObjectMapper mapper = new ObjectMapper();
    private Map<String, Boolean> values;
    private final String filePath = OSManager.getConfigDirectory() + "/checkbox.json";
    private final File jsonFile = new File(filePath);

    public JSONReader(){
        load();
    }

    private void load() {
        try {
            values = mapper.readValue(
                    jsonFile,
                    new TypeReference<Map<String, Boolean>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to load checkbox.json", e);
        }
    }
    public boolean readValue(String key) {
        return values.getOrDefault(key, false);
    }
}
