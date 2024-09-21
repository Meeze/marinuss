package de.mxzx.controller;


import de.mxzx.Main;
import de.mxzx.model.Clan;
import de.mxzx.model.Punishment;
import de.mxzx.service.ClanService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;


@RequiredArgsConstructor
@Getter
public class ClanController {

    private final Map<String, Clan> cache;
    private final ClanService service;
    public boolean clanChestOpened;


    public Clan getData(String id) {
        Clan data;
        if (!getCache().containsKey(id)) {
            data = getService().load(id);
            getCache().put(data.getOwnerId(), data);
        }
        return getCache().get(id);
    }

    public void saveData(Clan data) {
        getService().save(data);
        getCache().put(data.getName(), data);
    }

    public Comparator<String> comp = (s1, s2) -> {
        Clan c1 = getData(s1);
        Clan c2 = getData(s2);
        if (c1.getWarsWon() > c2.getWarsWon()) {
            return -1;
        }
        if (c1.getWarsWon() < c2.getWarsWon()) {
            return 1;
        }
        return 0;
    };

    public void addBannerSlot(String clan) {
        if (getData(clan).getBannerPatterns().size() == 5)
            return;
        getData(clan).getBannerPatterns().add(new Pattern(DyeColor.BLACK, PatternType.BASE));
        sendCustomClanMessage("§a§lClanBanner", "§6+§e1 §aBannerSlot", clan);
    }
    private String dyeToString(DyeColor color) {
        switch (color) {
            case ORANGE:
                return "§6Orange";
            case MAGENTA:
                return "§dMagenta";
            case LIGHT_BLUE:
                return "§bHellblau";
            case YELLOW:
                return "§eGelb";
            case LIME:
                return "§aGrün";
            case PURPLE:
                return "§5Violett";
            case PINK:
                return "§dPink";
            case RED:
                return "§cRot";
            case BLACK:
                return "§8Schwarz";
            case WHITE:
                return "§fWeiss";
        }
        return null;
    }

    private int dyeToInt(DyeColor color) {
        switch (color) {
            case ORANGE:
                return 14;
            case MAGENTA:
                return 13;
            case LIGHT_BLUE:
                return 12;
            case YELLOW:
                return 11;
            case LIME:
                return 10;
            case PINK:
                return 9;
            case PURPLE:
                return 5;
            case RED:
                return 1;
            case BLACK:
                return 0;
            case WHITE:
                return 15;
        }
        return -1;
    }

    public void sendEmtpyClanWarMessage(String clan) {
        for (String member : getData(clan).getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(member)) != null) {
                Bukkit.getPlayer(UUID.fromString(member)).sendMessage("");
            }
        }
    }

    private int warsWon = 0;

    public void sendClanWarMessage(String message, String clan) {
        for (String member : getData(clan).getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(member)) != null) {
                Bukkit.getPlayer(UUID.fromString(member)).sendMessage("§c§lClanWar §8(§6Info§8) §8» §7" + message);
            }
        }
    }

    public void sendClanAllyMessage(String message, String clan) {
        for (String member : getData(clan).getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(member)) != null) {
                Bukkit.getPlayer(UUID.fromString(member)).sendMessage("§b§lClanAllianz §8(§6Info§8) §8» §7" + message);
            }
        }
    }

    public void sendCustomClanMessage(String prefix, String message, String clan) {
        for (String member : getData(clan).getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(member)) != null) {
                Bukkit.getPlayer(UUID.fromString(member)).sendMessage(prefix + " §8» §7" + message);
            }
        }
    }

    public void sendClanMessage(String message, String clan) {
        for (String member : getData(clan).getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(member)) != null) {
                Bukkit.getPlayer(UUID.fromString(member)).sendMessage("§c§lClan §8(§6Info§8) §8» §7" + message);
            }
        }
    }
    public void sendClanMessageAlly(String player, String clan, String message) {
        for (String member : getData(clan).getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(member)) != null) {
                Bukkit.getPlayer(UUID.fromString(member)).sendMessage("§c§lClan §8(§6" + player + "§8) §8» §7" + message);
            }
        }
    }


    public void setEnergy(int energy, String clan) {
        if (energy > getData(clan).getNextEnergy()) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.playEffect(player.getLocation().clone().add(0, 1, 0), Effect.PORTAL, 1);
                player.playEffect(player.getLocation().clone().add(0, 1, 0), Effect.MOBSPAWNER_FLAMES, 1);
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                player.playSound(player.getLocation(), Sound.AMBIENCE_CAVE, 0.2f, 0.2f);
            });
        }
        getData(clan).setNextEnergy(energy);
    }

    public List<String> getTopList() {
        List<String> ls = new ArrayList<String>();
        for (Clan c : getCache().values()) {
            if (c.getName() == null) {
                continue;
            }
            ls.add(c.getName());
        }
        Collections.sort(ls, comp);
        return ls;
    }

    public boolean isValid(String code) {
        return code
                .matches("[aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789]*");
    }

    public int getClanRank(List<String> topList, String clan) {
        int i = 1;
        for (String s : getTopList()) {
            if (s.equalsIgnoreCase(clan)) {
                return i;
            }
            i++;
        }
        return -1;
    }


    public List<String> getAllClans() {
        List<String> l = new ArrayList<>();
        File f = new File("plugins/" + Main.getInstance().getDescription().getName() + "/Clans");
        File[] ordner = f.listFiles();
        for (File f1 : ordner) {
            l.add(f1.getName().replace(".yml", ""));
        }
        return l;
    }


    public boolean getSettingBool(String name, String clan) {
        return getData(clan).getSettings().getOrDefault(name, 0) == 1;
    }

    public int getSetting(String name, String clan) {
        return getData(clan).getSettings().getOrDefault(name, 0);
    }



    public void showClanInventory(Player player, String clan) {
        player.openInventory(getData(clan).getClanInventory());
    }

    public String getMemberList(String clan) {
        List<String> l = new ArrayList<String>();
        for (String s : getData(clan).getMembers()) {
            if (Bukkit.getPlayer(UUID.fromString(s)) != null) {
                Player p = Bukkit.getPlayer(UUID.fromString(s));
                l.add(getClanRankColor(p.getName(), clan) + p.getName());
            } else {
                String name = Bukkit.getOfflinePlayer(UUID.fromString(s)).getName();
                l.add(getClanRankColor(name, clan) + name);
            }
        }
        return l.toString().replace("[", "").replace("]", "").replace(",", "\n").replace(" ", "");
    }

    public String getTotalName(String clan) {
        return getData(clan).getClanColor() + getData(clan).getName();
    }

    public int getClanBlock(String clan) {
        switch (getData(clan).getLevel()) {
            case 1:
                return 11;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 5;
            case 5:
                return 3;
            case 6:
                return 2;
            case 7:
                return 13;
            case 8:
                return 10;
            case 9:
                return 6;
            case 10:
                return 14;
            default:
                return 0;
        }
    }

    public int getMaxMembers(String clan) {
        return getData(clan).getMaxMembers();
    }

    public int getClanCount() {
        File f = new File("plugins/" + Main.getInstance().getDescription().getName() + "/Clans");
        return f.listFiles().length;
    }

    public String getClanRankColor(String name, String clan) {
        if (name.equalsIgnoreCase(getData(clan).getOwnerName())) {
            return "§8(§4§lOwner§8) §7";
        }
        if (getData(clan).getMods().contains(name)) {
            return "§8(§5§lMod§8) §7";
        }
        if (getData(clan).getTrusted().contains(name)) {
            return "§8(§a§lTrusted§8) §7";
        }
        return "§8(§7Member§8) §7";
    }


    public boolean isMod(String name, String clan) {
        for (String s : getData(clan).getMods()) {
            if (s.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public String getClanRankColorCode(String name, String clan) {
        if (name.equalsIgnoreCase(getData(clan).getOwnerName())) {
            return "§c";
        }
        if (getData(clan).getMods().contains(name)) {
            return "§5";
        }
        if (getData(clan).getTrusted().contains(name)) {
            return "§a";
        }
        return "§7";
    }


    public boolean isTrusted(String name, String clan) {
        for (String s : getData(clan).getTrusted()) {
            if (s.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }



    public boolean hasClanUpgrade(String id, String clan) {
        return getData(clan).getClanUpgrades().contains(id);
    }

    public void addMember(Player p, String clan) {
        Main.getInstance().getPlayerDataController().getData(p).setClan(clan);
        getData(clan).getMembers().add(p.getUniqueId().toString());
        //p.sendMessage("Du bist dem Clan '"+name+"' beigetreten.");
        sendClanMessage(getClanRankColor(p.getName(), clan) + p.getName() + " §7ist dem Clan beigetreten!", clan);
        if (getMaxMembers(clan) == getData(clan).getMembers().size()) {
            sendClanMessage("§c§lWichtig: §7Der Clan hat soeben die max. Anzahl von Mitgliedern erreicht.", clan);
            sendClanMessage("Benutzt /c shop um die Anzahl zu erweitern.", clan);
        }
    }




    private boolean deleteClan() {
        return false;
    }



    public void saveClan() {
       /* cfg.set("name", name);
        cfg.set("owner", owner);
        cfg.set("mods", mods);
        cfg.set("trusted", trusted);
        cfg.set("clanHomes", clanHomes);
        cfg.set("members", members);
        cfg.set("kills", kills);
        cfg.set("deaths", deaths);
        cfg.set("warsWon", warsWon);
        cfg.set("coins", coins);
        cfg.set("exp", exp);
        cfg.set("clanColor", clanColor);
        cfg.set("clanCreated", clanCreated);
        cfg.set("maxMembers", maxMembers);
        cfg.set("maxHomes", maxHomes);
        cfg.set("clanUpgrades", clanUpgrades);
        try {
            cfg.set("bannerPatterns", bannerPatterns);
            cfg.set("bannerBaseColor", baseColor.name());
        } catch (Exception e) {}
        List<String> stringList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : settings.entrySet())
            stringList.add(entry.getKey() + ":" + entry.getValue());
        cfg.set("clanSettings", stringList);
        cfg.save();
        if(clanBanner != null)
            clanBanner.remove();*/
    }


    public int getLevel(String clan) {
        int exp = getData(clan).getExp();
        if (exp <= 100) {
            return 1;
        }
        if (exp <= 500) {
            return 2;
        }
        if (exp <= 1000) {
            return 3;
        }
        if (exp <= 2000) {
            return 4;
        }
        if (exp <= 5000) {
            return 5;
        }
        if (exp <= 10000) {
            return 6;
        }
        if (exp <= 15000) {
            return 7;
        }
        if (exp <= 30000) {
            return 8;
        }
        if (exp <= 45000) {
            return 9;
        }
        if (exp >= 45001) {
            return 10;
        }
        return -1;
    }


    public Player[] getOnlinePlayers(Clan clan) {
        return clan.getMembers().stream().map(Bukkit::getPlayer).filter(Objects::nonNull).toArray(Player[]::new);
    }

    public void addMaxClanMembers(Clan clan, int i) {
        clan.setMaxMembers(clan.getMaxMembers()+i);
        this.getCache().put(clan.getName(), clan);
    }

    public void addClanUpgrade(Clan clan, String itemId) {
        clan.getClanUpgrades().add(itemId);
    }

    public boolean isAlly(Player player1, Player player2) {
        Clan clan1 = getPlayerClan(player1);
        Clan clan2 = getPlayerClan(player2);
        return clan1.getClanAlly().contains(clan2.getName());
    }

    public Clan getPlayerClan(Player player) {
        return Main.getInstance().getClanController().getData(Main.getInstance().getPlayerDataController().getData(player).getClan());
    }

    public void addKills(String clan, int amount) {
        getData(clan).setKills(getData(clan).getKills() + amount);
    }

    public void addExp(String clan, int v) {
        getData(clan).setExp(getData(clan).getExp() + v);
    }

    public void addDeaths(String clan, int amount) {
        getData(clan).setDeaths(getData(clan).getDeaths() + amount);
    }


}
