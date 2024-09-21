package de.mxzx.controller;


import de.mxzx.model.AMS;
import de.mxzx.service.AmsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class AmsController {

    private final Map<String, AMS> cache;
    private final AmsService service;

    public AMS getData(String id) {
        AMS data;
        if (!getCache().containsKey(id)) {
            data = getService().load(id);
            getCache().put(data.getOwnerId(), data);
        }
        return getCache().get(id);
    }

    public void setData(AMS data) {
        getCache().put(data.getOwnerId(), data);
    }

    public void saveData(AMS data) {
        getService().save(data);
        getCache().put(data.getOwnerId(), data);
    }

    public AMS getAms(Player player) {
        return getData(player.getUniqueId().toString());
    }


}
