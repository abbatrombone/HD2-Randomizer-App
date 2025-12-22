package me.abbatrombone.traz.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
    public StringParser(){}

    public String parseArmorLevel(String armor){
        String level = "";
        Pattern pattern = Pattern.compile(
                "level=([^,]+)"
        );

        Matcher matcher = pattern.matcher(armor);

        if (matcher.find()) {
            level = matcher.group(1);
        }
        return level;
    }
    public String parseArmorPassive(String armor){
        String passive = "";

        Pattern pattern = Pattern.compile(
                "\\s*passive='([^']+)'"
        );

        Matcher matcher = pattern.matcher(armor);

        if (matcher.find()) {
            passive = matcher.group(1);
        }
        return passive;
    }
    public String parseStrategem(String stratagem){
        String name = "";

        Pattern pattern = Pattern.compile(
                "\\s*name='([^']+)'"
        );

        Matcher matcher = pattern.matcher(stratagem);

        if (matcher.find()) {
            name = matcher.group(1);
        }
        return name;
    }
    public String parseThrowable(String throwable){
        String name = "";

        Pattern pattern = Pattern.compile(
                "\\s*throwableName='([^']+)'"
        );

        Matcher matcher = pattern.matcher(throwable);

        if (matcher.find()) {
            name = matcher.group(1);
        }
        return name;
    }

}
