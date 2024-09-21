package de.mxzx.controller;

import de.mxzx.model.Season;
import de.mxzx.service.SeasonService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class SeasonController {
    private final Map<String, Season> cache;
    private final SeasonService service;

    public Season getData(String id) {
        Season data;
        if (!getCache().containsKey(id)) {
            data = getService().load(id);
            setData(data);
        }
        return getCache().get(id);
    }

    public void setData(Season data) {
        getCache().put(data.getUuidPlayer(), data);
    }

    public void saveData(Season data) {
        getService().save(data);
        setData(data);
    }
}
