/* Copyright 2020 lantisog
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mxzx.model.nondb;

import org.bukkit.Material;

public enum QuestItem {

    AIR(Material.AIR, "§cUnbekanntes Item"),
    BONE(Material.BONE, "§fKnochen"),
    ROTTEN_FLESH(Material.ROTTEN_FLESH, "§5Verrottetes Fleisch"),
    SPIDER_EYE(Material.SPIDER_EYE, "§5Spinnenauge"),
    ENDER_PEARL(Material.ENDER_PEARL, "§3Enderperle"),
    SLIME_BALL(Material.SLIME_BALL, "§aSchleimball"),
    MAGMA_CREAM(Material.MAGMA_CREAM, "§cMagmacreme"),
    SULPHUR(Material.SULPHUR, "§7Schwarzpulver"),
    GLOWSTONE_DUST(Material.GLOWSTONE_DUST, "§4Z§ca§6u§eb§ae§2r§3s§1t§ba§du§5b"),
    BLAZE_ROD(Material.BLAZE_ROD, "§eLohenrute"),
    GHAST_TEAR(Material.GHAST_TEAR, "§fGhastträne"),

    COAL(Material.COAL, "§8Kohle"),
    IRON_INGOT(Material.IRON_INGOT, "§7Eisenbarren"),
    GOLD_INGOT(Material.GOLD_INGOT, "§eGoldbarren"),
    DIAMOND(Material.DIAMOND, "§bDiamant"),
    EMERALD(Material.EMERALD, "§aSmaragd"),

    PLAYER_HEAD(Material.SKULL_ITEM, "§3Spielerkopf");

    //Gold Miner

    Material material;
    String name;

    QuestItem(Material material, String name) {
        this.material = material;
        this.name = name;
    }

    public static QuestItem forDisplayName(String display) {
        for (QuestItem questItem : values()) {
            if (questItem.getName().equalsIgnoreCase(display))
                return questItem;
        }
        return AIR;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

}
