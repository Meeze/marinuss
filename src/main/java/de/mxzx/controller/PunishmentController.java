package de.mxzx.controller;


import de.mxzx.model.PlayerData;
import de.mxzx.model.Punishment;
import de.mxzx.model.PunishmentType;
import de.mxzx.service.PunishmentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;

@RequiredArgsConstructor
@Data
public class PunishmentController {

    private final Map<String, Punishment> cache;
    private final PunishmentService service;

    private Punishment getPunishment(String id) {
        Punishment data;
        if (!getCache().containsKey(id)) {
            data = getService().load(id);
            getCache().put(data.getPunishedId(), data);
        }
        return getCache().get(id);
    }

    public void punish(Player player, PunishmentType type, String reason, long until) {
        switch (type.name().toLowerCase()) {
            case "ban":
            case "mute":
            case "kick":
            case "warn":
        }
    }

    private void ban(Player player, String reason, String issuer, long untilMillis){

    }

    private void mute(Player player, String reason, String issuer, long untilMillis){

    }

    private void kick(Player player, String reason, String issuer){

    }

    private void warn(Player player, String reason, String issuer){

    }

    public void unban(Player player) {

    }

    public Punishment getPunishment(Player player, PunishmentType type) {
        return null;
    }

    public boolean checkIfBanned(Player player) {
        return false;
    }
    public boolean checkIfMuted(Player player) {
        return false;
    }

    public void save() {
    }
}
