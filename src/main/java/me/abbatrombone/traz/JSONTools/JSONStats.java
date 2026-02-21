package me.abbatrombone.traz.JSONTools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.abbatrombone.traz.Managers.OSManager;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONStats {

    public enum filePaths{
        randomStats("/randomStats.json"),
        semiRandomStat("/semirandomStats.json");
        private final String path;

        filePaths(String path){this.path = path;}

        public String getPath(){return path;}
    }

    private final ObjectMapper mapper = new ObjectMapper();
    private Map<String, Map<String, WinLose>> data;
    String fileName;
    private final File jsonfile;

    public JSONStats(filePaths fileName){
        this.fileName = fileName.getPath();
        String filepath = OSManager.getCacheDirectory() + fileName.getPath();
        jsonfile = new File(filepath);
        load(jsonfile);
    }
    private void load(File jsonfile) {
        try {
            data = mapper.readValue(
                    jsonfile,
                    new TypeReference<Map<String, Map<String, WinLose>>>() {}
            );
        } catch (IOException e) {
            data = new HashMap<>();
        }
    }
    public WinLose get(String category, String item) {
        return data
                .computeIfAbsent(category, k -> new HashMap<>())
                .computeIfAbsent(item, k -> new WinLose(0, 0));
    }
    public Map.Entry<String, WinLose> getHighestWin(String category) {
        return data.getOrDefault(category, Map.of())
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(e -> e.getValue().win))
                .orElse(null);
    }
    public List<Map.Entry<String, WinLose>> getTopWins(String category) {
        return data.getOrDefault(category, Map.of())
                .entrySet()
                .stream()
                .sorted((a,b) -> Integer.compare(b.getValue().win, a.getValue().win))
                .toList();
    }

    public void addWin(String category, String item) {
        get(category, item).win++;
        save();
    }

    public void addLose(String category, String item) {
        get(category, item).lose++;
        save();
    }

    private void save() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(jsonfile, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
