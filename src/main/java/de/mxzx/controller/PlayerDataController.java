package de.mxzx.controller;

import de.mxzx.model.PlayerData;
import de.mxzx.service.PlayerDataService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class PlayerDataController {

    private final Map<String, PlayerData> playerCache;
    private final PlayerDataService service;

    public List<PlayerData> getAllOfflineData() {
        return new ArrayList<>(getPlayerCache().values());
    }
    public PlayerData getData(String id) {
        PlayerData data;
        if (!getPlayerCache().containsKey(id)) {
            data = getService().load(id);
            getPlayerCache().put(data.getUuid(), data);
        }
        return getPlayerCache().get(id);
    }
    public PlayerData getData(Player player) {
        return getData(player.getUniqueId().toString());
    }

    public void setData(PlayerData data) {
        getPlayerCache().put(data.getUuid(), data);
    }

    public void saveData(PlayerData data) {
        getPlayerCache().put(data.getUuid(), data);
        getService().save(data);
    }
}
