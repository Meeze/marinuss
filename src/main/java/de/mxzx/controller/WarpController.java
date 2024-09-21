package de.mxzx.controller;

import de.mxzx.model.Warp;
import de.mxzx.service.WarpService;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Map;

@Data
public class WarpController {


    // string name, warp cached entity
    private final Map<String, Warp> warps;
    private final WarpService service;

    public void setUp() {
      service.loadAll().forEach(warp -> warps.put(warp.getName(), warp));
    }

    public void saveWarp(String name, Location loc) {
        Warp warp = getWarpFromLocation(name, loc);
        save(warp);
    }

    private void save(Warp warp) {
        service.save(warp);
        getWarps().put(warp.getName(), warp);
    }

    public void delete(String warp) {
        service.delete(warp);
        getWarps().remove(warp);
    }

    public void teleportToSpawn(Player player) {
        warp(player, "spawn");
    }

    public void warp(Player player, Warp warp) {
        player.teleport(getLocFromWarp(warp));
    }

    public void warp(Player player, String warpName) {
        Warp warp = warps.get(warpName);
        warp(player, warp);
    }

    private Warp getWarpFromLocation(String name, Location loc) {
        return Warp.builder().location(loc).name(name).x(loc.getBlockX()).y(loc.getBlockY()).z(loc.getBlockZ()).world(loc.getWorld().toString()).build();
    }

    private Location getLocFromWarp(Warp warp) {
        Location loc = new Location(Bukkit.getWorld(warp.getWorld()), warp.getX(), warp.getY(), warp.getZ());
        return loc;
    }

}
