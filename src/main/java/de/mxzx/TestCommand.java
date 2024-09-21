package de.mxzx;

import de.mxzx.model.*;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        switch (args[0]) {
            case "milestone":
                if (args[1].equalsIgnoreCase("loadtodb")) {
                    Milestones.getMilestones().forEach(milestone -> {
                        Main.getInstance().getMilestoneController().saveData(milestone);
                    });

                }
                break;
            case "season":
                if (args[1].equalsIgnoreCase("create")) {
                    Season season = new Season();
                    season.setUuidPlayer("1234");
                    List<Milestones> milestones1 = new ArrayList<Milestones>() {{add(Milestones.KILLS_50);}};
                    List<Milestones> milestones2 = new ArrayList<Milestones>() {{add(Milestones.KILLS_100);}};
                    List<Milestones> milestones3 = new ArrayList<Milestones>() {{add(Milestones.KILLS_50);add(Milestones.KILLS_100);}};
                    season.setMilestones(milestones1);
                    season.setActiveMilestones(milestones2);
                    season.setCompletedMilestones(milestones3);
                    Main.getInstance().getSeasonController().saveData(season);

                }
                break;
            case "ams":
                if (args[1].equalsIgnoreCase("create")) {
                    AMS ams = new AMS();
                    ams.setOwnerId("1234");
                    ams.setId(UUID.randomUUID().toString());
                    ams.setSpawner(2333);
                    ams.setFriends(new ArrayList<String>() {{
                        add("friend1");
                    }});
                    ams.setUpgrades(new ArrayList<AMSUpgrade>() {{
                        add(AMSUpgrade.DIRECT_INFLUENCE);
                        add(AMSUpgrade.VAULT_X);
                    }});
                    Main.getInstance().getAmsController().saveData(ams);
                }
                break;
            case "clan":
                if (args[1].equalsIgnoreCase("create")) {
                    Map<String, Integer> settings = new HashMap<>();
                    settings.put("test", 1);
                    settings.put("test3", 0);
                    List<ItemStack> chestItems = new ArrayList<ItemStack>() {{
                        add(new ItemStack(Material.LEATHER_BOOTS, 2));
                    }};
                    Inventory inv = Bukkit.createInventory(null, 9, "clanec");
                    Clan clan = Clan.builder().name("clan").ownerId("1234").ownerName("notch").mods(new ArrayList<String>() {{
                                add("11");
                            }}).trusted(new ArrayList<String>() {{
                                add("22");
                            }}).members(new ArrayList<String>() {{
                                add("11");
                                add("22");
                            }})
                            .clanHomes(new ArrayList<String>() {{
                                add("home1");
                            }}).baseColor(DyeColor.BLUE).bannerPatterns(new ArrayList<Pattern>(){{add(new Pattern(DyeColor.BLUE, PatternType.BORDER));}}).clanAlly(new ArrayList<String>() {{add("otherclan");}}
                            ).settings(settings).chestItems(chestItems).clanInventory(inv).exp(123).build();
                    Main.getInstance().getClanController().saveData(clan);
                }
                break;
            case "mine":
                if (args[1].equalsIgnoreCase("create")) {
                    Mine mine = new Mine();
                    String name = "mine";
                    Location loc1 = new Location(Bukkit.getWorld("world"), 0, 150, 0);
                    Location loc2 = new Location(Bukkit.getWorld("world"), 100, 150, 0);
                    Location loc3 = new Location(Bukkit.getWorld("world"), 100, 150, 100);
                    List<Location> locations = new ArrayList<>();
                    locations.add(loc2);
                    locations.add(loc1);
                    List<Location> mineLocations = new ArrayList<>();
                    mineLocations.add(loc3);
                    Map<Material, Integer> blocks = new HashMap<>();
                    blocks.put(Material.ANVIL, 100);
                    blocks.put(Material.OBSIDIAN, 10);
                    mine.setName(name);
                    mine.setBlocks(blocks);
                    mine.setLocations(locations);
                    mine.setMineLocations(mineLocations);
                    mine.setNpcLocation(loc1);
                    mine.setHologram(loc2);
                    mine.setSpawn(loc3);
                    mine.setMinLevel(0);
                    mine.setMaxLevel(11);
                    Main.getInstance().getMineController().saveData(mine);
                } else if (args[1].equalsIgnoreCase("showdata")) {
                    Bukkit.broadcastMessage(Main.getInstance().getMineController().getData("mine").toString());
                    System.out.println(Main.getInstance().getMineController().getData("mine").toString());
                } else if (args[1].equalsIgnoreCase("edit")) {
                    Mine mine = Main.getInstance().getMineController().getData("mine");
                    String name = "mine";
                    Location loc1 = new Location(Bukkit.getWorld("world"), 0, 150, 77);
                    Location loc2 = new Location(Bukkit.getWorld("world"), -100, 150, 0);
                    Location loc3 = new Location(Bukkit.getWorld("world"), -100, 150, 100);
                    List<Location> locations = new ArrayList<>();
                    locations.add(loc2);
                    locations.add(loc1);
                    List<Location> mineLocations = new ArrayList<>();
                    mineLocations.add(loc3);
                    Map<Material, Integer> blocks = new HashMap<>();
                    blocks.put(Material.ANVIL, 44);
                    blocks.put(Material.OBSIDIAN, 10);
                    blocks.put(Material.DARK_OAK_DOOR, 2);
                    mine.setBlocks(blocks);
                    mine.setMinLevel(0);
                    mine.setMaxLevel(7);
                } else if (args[1].equalsIgnoreCase("save")) {
                    Mine mine = Main.getInstance().getMineController().getData("mine");
                    Main.getInstance().getMineController().saveData(mine);
                }
                break;
            case "player":
                if (args[1].equalsIgnoreCase("showdata")) {
                    Player player = (Player) commandSender;
                    PlayerData data = Main.getInstance().getPlayerDataController().getData("1234");
                    player.sendMessage(data.toString());
                    System.out.println(data.toString());
                } else if (args[1].equalsIgnoreCase("showalldata")) {
                    Player player = (Player) commandSender;
                    Map<String, PlayerData> data = Main.getInstance().getPlayerDataController().getPlayerCache();
                    data.forEach((s1, playerData) -> {
                        System.out.println(data.toString());
                        player.sendMessage(data.toString());
                    });
                } else if (args[1].equalsIgnoreCase("edit1")) {
                    Player player = (Player) commandSender;
                    PlayerData data = Main.getInstance().getPlayerDataController().getData("1234");
                    data.setClan("newclan");
                    data.setBounty(12323213123L);
                } else if (args[1].equalsIgnoreCase("home")) {
                    Player player = (Player) commandSender;
                    PlayerData data = Main.getInstance().getPlayerDataController().getData("1234");
                    Home home = data.getHomes().get(0);
                    player.sendMessage(home.getName());
                    player.teleport(home.getLoc());
                } else if (args[1].equalsIgnoreCase("testsettings")) {
                    Player player = (Player) commandSender;
                    PlayerData data = Main.getInstance().getPlayerDataController().getData("1234");
                    Map<String, Integer> settings = new HashMap<>();
                    settings.put("test", 0);
                    settings.put("newsetting", 1);
                    data.setSettings(settings);
                    Main.getInstance().getPlayerDataController().setData(data);
                } else if (args[1].equalsIgnoreCase("save")) {
                    PlayerData data = Main.getInstance().getPlayerDataController().getData("1234");
                    Main.getPlugin(Main.class).getPlayerDataController().saveData(data);
                } else if (args[1].equalsIgnoreCase("create")) {
                    Map<Integer, Integer> sellBuffs = new HashMap<>();
                    sellBuffs.put(-1, -1);
                    Map<String, Integer> settings = new HashMap<>();
                    settings.put("test", 1);
                    settings.put("test3", 0);
                    Map<String, Long> rewardUses = new HashMap<>();
                    rewardUses.put("reward", 1L);
                    Map<String, Integer> rankupUtils = new HashMap<String, Integer>();
                    rankupUtils.put("rankutil", 1);
                    Location dummyLoc = new Location(Bukkit.getWorld("world"), 0, 0, 0);
                    PlayerData d = PlayerData.builder().uuid("1234").lastIp("testip").lastVote("testvote").name("testname").clan("testclan").currentPrefix("testprefix").color("testcolor").logoutLocation(new Location(Bukkit.getWorld("world"), 0, 0, 0))
                            .autoLevelUp(false).isVerified(false).nitroDaily(-1).nitroWeekly(-1).damagePoints(-1).bounty(-1).money(-1).tokens(-1).traderTokens(-1).playTime(-1).minedBlocks(new ArrayList<String>() {{
                                add("ss");
                            }}).minePoints(-1).minePointsSellTotal(-1).minePointsGutscheinTotal(-1).minePointsUsedForUpgradesTotal(-1).mineRewardsTotal(-1).lastIp("").lastMessage(-1).rankUps(-1).combatTime(-1).rankUps(-1).combatTime(-1).userId(-1)
                            .maxItemAmount(-1).votes(-1).voteStreak(-1).kills(-1).deaths(-1).score(-1).mineLevel(-1).prestigeLevel(-1).killStreak(-1).jackpotsWon(-1).cratesOpened(-1).supplyDrops(-1).s_positive(-1).s_chats(-1)
                            .maxHomes(-1).activeChatColor("testcolor").elo(-1).eloRank(-1).discordId("testdcid").itemfilter(new ArrayList<ItemStack>() {{
                                add(new ItemStack(Material.LEATHER_BOOTS));
                            }}).activePerks(new ArrayList<String>() {{
                                add("perk");
                            }}).chatColors(new ArrayList<String>() {{
                                add("chatcolor");
                            }}).prefixes(new ArrayList<String>() {{
                                add("prefix");
                            }}).peace(new ArrayList<String>() {{
                                add("peace");
                            }}).kitUses(new ArrayList<String>() {{
                                add("kituses");
                            }}).homes(new ArrayList<Home>() {{
                                add(new Home("test", UUID.randomUUID().toString(), dummyLoc));
                            }}).sellBuffs(sellBuffs).settings(settings).rewardUses(rewardUses).rankupUtils(rankupUtils).build();
                    System.out.println(d.toString());
                    Main.getPlugin(Main.class).getPlayerDataController().saveData(d);

                    PlayerData d2 = Main.getPlugin(Main.class).getPlayerDataController().getData("1234");
                    System.out.println(d2);
                }
        }
        return true;
    }
}
