package de.mxzx.model;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum AMSUpgrade {

    POWER_UP_I(1, "Power-UP I", 2500000L, new ItemStack(Material.GOLD_NUGGET, 1), "test"+"Mit dem Power-UP I Upgrade", "test"+"generiert deine AMS "+ "test"+"5%"+ "test"+" mehr", "test"+"Münzen pro 2 Sek."),
    POWER_UP_II(2, "Power-UP II", 5000000L, new ItemStack(Material.GOLD_NUGGET, 2), "test"+"Mit dem Power-UP II Upgrade", "test"+"generiert deine AMS "+ "test"+"10%"+ "test"+" mehr", "test"+"Münzen pro 2 Sek."),
    POWER_UP_III(3, "Power-UP III", 10000000L, new ItemStack(Material.GOLD_NUGGET, 3), "test"+"Mit dem Power-UP III Upgrade", "test"+"generiert deine AMS "+ "test"+"15%"+ "test"+" mehr", "test"+"Münzen pro 2 Sek."),
    POWER_UP_IV(4, "Power-UP IV", -1L, new ItemStack(Material.GOLD_NUGGET, 4), "test"+"Mit dem Power-UP IV Upgrade", "test"+"generiert deine AMS "+ "test"+"20%"+ "test"+" mehr", "test"+"Münzen pro 2 Sek."),
    POWER_UP_V(5, "Power-UP V", -1L, new ItemStack(Material.GOLD_NUGGET, 5), "test"+"Mit dem Power-UP V Upgrade", "test"+"generiert deine AMS "+ "test"+"30%"+ "test"+" mehr", "test"+"Münzen pro 2 Sek."),
    OVERCLOCKED(6, "Overclocked", -1L, new ItemStack(Material.GOLD_NUGGET, 6), "test"+"Mit dem Overclocked Upgrade", "test"+"generiert deine AMS "+ "test"+"50%"+ "test"+" mehr", "test"+"Münzen pro 2 Sek."),
    OFFLINEGEN_I(7 , "Offline Gen I", 5000000L, new ItemStack(Material.FURNACE, 1), "test"+"Das Offline Gen I Upgrade", "test"+"erlaubt es deiner AMS Münzen zu", "test"+"generieren während du Offline bist.", "test"+"Es werden jedoch nur "+ "test"+"40%"+ "test"+" der Münzen generiert."),
    OFFLINEGEN_II(8 , "Offline Gen II", -1L, new ItemStack(Material.FURNACE, 2), "test"+"Das Offline Gen II Upgrade", "test"+"erlaubt es deiner AMS Münzen zu", "test"+"generieren während du Offline bist.", "test"+"Es werden jedoch nur "+ "test"+"75%"+ "test"+" der Münzen generiert.", "test"+"Stapelt sich nicht mit OG I."),
    OFFLINEGEN_III(9 , "Offline Gen III", -1L, new ItemStack(Material.FURNACE, 3), "test"+"Das Offline Gen II Upgrade", "test"+"erlaubt es deiner AMS Münzen zu", "test"+"generieren während du Offline bist.", "test"+"Es werden "+ "test"+"100%"+ "test"+" der Münzen generiert.", "test"+"Stapelt sich nicht mit OG I & II."),
    FRIEND_MANAGEMENT(10 , "Friend Management", 200000L, new ItemStack(Material.BOOK, 1), "test"+"Mit diesem Upgrade", "test"+"erlaubst du deinen Freunden", "test"+"die Spawner deiner AMS", "test"+"zu verwalten."),
    DIRECT_INFLUENCE(11 , "Direct Influence I", -1L, new ItemStack(Material.PAPER, 1), "test"+"Das Direct Influence I Upgrade", "test"+"ermöglicht es deiner AMS die", "test"+"gesammelten Münzen direkt", "test"+"zu deinem Guthaben hinzuzufügen.", "", "test"+"Direct Influence ist nur", "test"+"aktiv wenn du Online bist."),
    DIRECT_INFLUENCE_II(12 , "Direct Influence II", -1L, new ItemStack(Material.PAPER, 2), "test"+"Das Direct Influence II Upgrade", "test"+"ermöglicht es deiner AMS die", "test"+"generierten Crates direkt", "test"+"in dein Inventar zu legen.", "", "test"+"Direct Influence ist nur", "test"+"aktiv wenn du Online bist."),
    INTERNET(13 , "Internet Upgrade", -1L, new ItemStack(Material.BEACON, 1), "test"+"Mit dem Internet Upgrade", "test"+"kannst du das Spawner senden", "test"+"Feature benutzen um Spawner", "test"+"an andere Personen zu senden"),
    COOLING(14 , "Extra Kühlung", 5000000L, new ItemStack(Material.WATER_BUCKET, 1), "test"+"Das Extra Kühlung Upgrade", "test"+"kühlt deine AMS effektiver und", "test"+"verlangsamt dadurch die", "test"+"Auslastung der AMS."),
    COOLING_II(15, "Tiefkühlung", -1L, new ItemStack(Material.WATER_BUCKET, 2), "test"+"Das Tiefkühlungs Upgrade", "test"+"schaltet die Auslastung aus."),
    VAULT_I(16, "Vault Upgrade I", 1500000L, new ItemStack(Material.CHEST, 1), "test"+"Mit dem Vault Upgrade I", "test"+"wird das Lager deiner", "test"+"AMS um "+ "test"+"10%"+ "test"+" erweitert."),
    VAULT_II(17, "Vault Upgrade II", 3000000L, new ItemStack(Material.CHEST, 2), "test"+"Mit dem Vault Upgrade II", "test"+"wird das Lager deiner", "test"+"AMS um "+ "test"+"20%"+ "test"+" erweitert."),
    VAULT_III(18, "Vault Upgrade III", -1L, new ItemStack(Material.ENDER_CHEST, 1), "test"+"Mit dem Vault Upgrade III", "test"+"wird das Lager deiner", "test"+"AMS um "+ "test"+"50%"+ "test"+" erweitert."),
    VAULT_X(19, "Vault Upgrade X", -1L, new ItemStack(Material.ENDER_CHEST, 2), "test"+"Das Vault Upgrade X entfernt", "test"+"das Limit der Münzen", "test"+"welche die AMS maximal", "test"+"lagern kann."),
    LOG_I(20, "Log Upgrade I", 1000000L, new ItemStack(Material.COMMAND, 1), "test"+"Das Log Upgrade I ermöglicht", "test"+"deiner AMS aufzuzeichnen", "test"+"wenn Münzen oder Crates abgehoben werden."),
    LOG_II(21, "Log Upgrade II", -1L, new ItemStack(Material.COMMAND, 2), "test"+"Das Log Upgrade II ermöglicht", "test"+"deiner AMS aufzuzeichnen", "test"+"wenn Spawner hinzugefügt / entfernt werden."),
    CRATISMUS_I(22, "Cratismus I", -1L, new ItemStack(Material.CHEST, 1), "test"+"Das Cratismus I Upgrade", "test"+"gibt deine AMS während der Generierung", "test"+"eine 1/1000 Chance eine zufällige", "test"+"Crate zu generieren."),
    CRATISMUS_II(23, "Cratismus II", -1L, new ItemStack(Material.CHEST, 2), "test"+"Das Cratismus I Upgrade", "test"+"gibt deine AMS während der Generierung", "test"+"eine 1/750 Chance eine zufällige", "test"+"Crate zu generieren.", "test" + "Stapelt sich nicht mit Cratismus I."),
    CRATISMUS_III(24, "Cratismus III", -1L, new ItemStack(Material.CHEST, 3), "test"+"Das Cratismus I Upgrade", "test"+"gibt deine AMS während der Generierung", "test"+"eine 1/500 Chance eine zufällige", "test"+"Crate zu generieren.", "test" + "Stapelt sich nicht mit Cratismus I und II.");

    public int id;
    private String name;
    private Long price;
    private String[] description;
    private ItemStack itemStack;

    AMSUpgrade(int id, String name, Long price, ItemStack itemStack, String... description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.itemStack = itemStack;
    }



 public static AMSUpgrade forId(int id) {
 for(AMSUpgrade upgrade : values()) {
 if(upgrade.id == id) return upgrade;
 }
 return null;
 }

 public boolean isBuyable() {
 return this.price != -1;
 }

 public Long getPrice() {
 return price;
 }

 public String getName() {
 return name;
 }


 }

