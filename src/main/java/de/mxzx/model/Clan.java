package de.mxzx.model;


import de.mxzx.converter.InventoryConverter;
import de.mxzx.converter.ItemListConverter;
import de.mxzx.converter.PatternConverter;
import de.mxzx.model.nondb.ClanQuest;
import lombok.*;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clan {

	@Id
    private String name;
    private String ownerId;
    private String ownerName;
	@ElementCollection
    private List<String> mods;
	@ElementCollection
    private List<String> trusted;
	@ElementCollection
	private List<String> clanHomes;
	//@Transient
	//private Map<String, List<ClanQuest>> clanQuests = new HashMap<>();
	@Enumerated(EnumType.STRING)
	private DyeColor baseColor;
	@ElementCollection
	@Convert(converter = PatternConverter.class)
	private List<Pattern> bannerPatterns;
	@ElementCollection
	private List<String> members;
	@ElementCollection
	private List<String> clanAlly;
	@ElementCollection
	private List<String> pendingClanAlly;
	@ElementCollection
	private Map<String, Integer> settings;
	private int kills;
	private int deaths;
	private float EXPMultiplier;
	private int warsWon;
	private int coins;
	private int exp;
	private String clanColor;
	private String clanCreated;
	private int maxMembers;
	private int chestSlots;
	private int maxAlly;
	private int maxHomes;
	@Convert(converter = InventoryConverter.class)
	private Inventory clanInventory;
	@Convert(converter = ItemListConverter.class)
	private List<ItemStack> inv;
	@ElementCollection
	private List<String> clanUpgrades;
	private int multiplier;
	private int energy;
	private int nextEnergy;
	private int level;
	@Convert(converter = ItemListConverter.class)
	private List<ItemStack> chestItems;
	@Transient
	private Map<String, List<ClanQuest>> clanQuests = new HashMap<>();



}
