package de.mxzx.model;

import de.mxzx.converter.ItemStackConverter;
import de.mxzx.converter.LocationConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerData {

    @Id
    private String uuid;
    private String lastIp;
    private String lastVote;
    private String name;
    private String clan;
    private String currentPrefix;
    private String color;
    @Convert(converter = LocationConverter.class)
    @Lob
    private Location logoutLocation;
    private boolean autoLevelUp;
    private boolean isVerified;
    private long nitroDaily;
    private long nitroWeekly;
    private long damagePoints;
    private long bounty;
    private double money;
    private long tokens;
    private long traderTokens;
    private long playTime;
    @ElementCollection
    public List<String> minedBlocks = new ArrayList<>();
    private long minePoints;
    private long minePointsSellTotal;
    private long minePointsGutscheinTotal;
    private long minePointsUsedForUpgradesTotal;
    private long mineRewardsTotal;
    private long lastLogin;
    private long lastMessage;
    private int rankUps;
    private int combatTime;
    private int userId;
    private int maxItemAmount;
    private int votes;
    private int voteStreak;
    private int kills;
    private int deaths;
    private int score;
    private int mineLevel;
    private int prestigeLevel;
    private int killStreak;
    private int jackpotsWon;
    private int cratesOpened;
    private int supplyDrops;
    private int s_positive;
    private int s_chats;
    private int maxHomes;
    private String activeChatColor;
    private int elo;
    private int eloRank;
    private String discordId;
    @ElementCollection
    @Lob
    @Convert(converter = ItemStackConverter.class)
    private List<ItemStack> itemfilter;
    @ElementCollection
    private List<String> activePerks;
    @ElementCollection
    private List<String> chatColors;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Home> homes = new ArrayList<>();
    @ElementCollection
    private List<String> prefixes = new ArrayList<>();
    @ElementCollection
    private List<String> peace;
    @ElementCollection
    private List<String> kitUses;
    @ElementCollection
    private Map<Integer, Integer> sellBuffs;
    @ElementCollection
    private Map<String, Integer> settings;
    @ElementCollection
    private Map<String, Long> rewardUses;
    @ElementCollection
    private Map<String, Integer> rankupUtils;
    //private Inventory[] enderchestInventories;
    //private PlayerMineInventory mineInventory = null;//new PlayerMineInventory(Bukkit.getPlayer(getUuid()));
}
