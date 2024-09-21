package de.mxzx.converter;

import org.bukkit.block.banner.Pattern;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

public class PatternConverter implements
        AttributeConverter<Pattern, String> {
    @Override
    public String convertToDatabaseColumn(Pattern pattern) {
        if(pattern == null)
            return null;
        return toString(pattern.serialize());
    }

    @Override
    public Pattern convertToEntityAttribute(String s) {
        return fromString(s);
    }

    public Pattern fromString(String pattern) {
        Map<String, Object> map = new HashMap<>();
        String[] parts1 = pattern.split("~");
        for (String part : parts1) {
            String[] parts2 = pattern.split(":");
            map.put(parts2[0], parts2[1]);
        }
        return new Pattern(map);
    }

    public String toString(Map<String, Object> pattern) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : pattern.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("~");
        }
        return sb.toString();
    }


}
