package de.mxzx.model;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) charon, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by charon
 **/
@Getter
public enum Milestones {


    // FIRST SEASON INVENTORY
    KILLS_50(new Milestone("Kills_50", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills§8: §c50", " "), 50, 10)),
    KILLS_100(new Milestone("Kills_100", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills§8: §c100", " "), 100, 11)),
    KILLS_150(new Milestone("Kills_150", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills§8: §c150", " "), 150, 12)),
    KILLS_200(new Milestone("Kills_200", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills§8: §c200", " "), 200, 13)),
    KILLS_250(new Milestone("Kills_250", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills§8: §c250", " "), 250, 14)),
    KILLS_300(new Milestone("Kills_300", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills§8: §c300", " "), 300, 15)),
    KILLS_450(new Milestone("Kills_450", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills§8: §c450", " "), 450, 16)),
    KILLSTREAK_10(new Milestone("Killstreak_10", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills in Folge§8: §c10", " "), 10, 19)),
    KILLSTREAK_20(new Milestone("Killstreak_20", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills in Folge§8: §c20", " "), 20, 20)),
    KILLSTREAK_30(new Milestone("Killstreak_30", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills in Folge§8: §c30", " "), 30, 21)),
    KILLSTREAK_40(new Milestone("Killstreak_40", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills in Folge§8: §c40", " "), 40, 22)),
    KILLSTREAK_50(new Milestone("Killstreak_50", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills in Folge§8: §c50", " "), 50, 23)),
    KILLSTREAK_60(new Milestone("Killstreak_60", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills in Folge§8: §c60", " "), 60, 24)),
    KILLSTREAK_70(new Milestone("Killstreak_70", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigte Kills in Folge§8: §c70", " "), 70, 25)),
    PVP_NOOB(new Milestone("PvP_350", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigter PvP-Rank§8: §9Noob", " "), 350, 28)),
    PVP_AMATEUR(new Milestone("PvP_750", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigter PvP-Rank§8: §dAmateur", " "), 750, 29)),
    PVP_PREMIER(new Milestone("PvP_2350", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigter PvP-Rank§8: §2Premier", " "), 2350, 30)),
    PVP_PRO(new Milestone("PvP_3150", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigter PvP-Rank§8: §bPro", " "), 3150, 31)),
    PVP_MAJOR(new Milestone("PvP_3800", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigter PvP-Rank§8: §eMajor", " "), 3800, 32)),
    PVP_MASTER(new Milestone("PvP_4600", Arrays.asList(" ",
            "§6Informationen",
            "§6§l│ §fBenötigter PvP-Rank§8: §cMaster", " "), 4600, 33));

    private final Milestone milestone;

    Milestones(Milestone milestone) {
        this.milestone = milestone;
    }

    public static Milestone getMilestone(String name) {
        for (Milestones milestones : values()) {
            if (milestones.getMilestone().getName().equalsIgnoreCase(name)) {
                return milestones.getMilestone();
            }
        }
        return null;
    }

    public static List<Milestone> getMilestones() {
        List<Milestone> milestones = Lists.newArrayList();
        for (Milestones milestoneValue : values()) {
            milestones.add(milestoneValue.getMilestone());
        }
        return milestones;
    }
}
