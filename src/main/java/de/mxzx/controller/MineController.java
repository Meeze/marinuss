package de.mxzx.controller;

import de.mxzx.model.Mine;
import de.mxzx.model.PlayerData;
import de.mxzx.service.MineService;
import de.mxzx.service.PlayerDataService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class MineController {
    private final Map<String, Mine> cache;
    private final MineService service;

    public Mine getData(String id) {
        Mine data;
        if (!getCache().containsKey(id)) {
            data = getService().load(id);
            getCache().put(data.getName(), data);
        }
        return getCache().get(id);
    }

    public void setData(Mine data) {
        getCache().put(data.getName(), data);
    }

    public void saveData(Mine data) {
        getCache().put(data.getName(), data);
        getService().save(data);
    }
}
