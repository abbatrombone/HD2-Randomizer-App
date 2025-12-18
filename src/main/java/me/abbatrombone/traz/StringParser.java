package me.abbatrombone.traz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
    StringParser(){}

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

}
