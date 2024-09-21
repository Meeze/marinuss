package de.mxzx.model.nondb;

public enum RankColor {
    GREEN("§2", "§a", "Grün"),
    YELLOW("§6", "§e", "Gelb"),
    ORANGE("§e", "§6", "Orange"),
    RED("§4", "§c", "Rot"),
    PINK("§5", "§d", "Pink"),
    PURPLE("§d", "§5", "Violett");

    public String scolor;
    public String pcolor;
    public String name;

    RankColor(String scolor, String pcolor, String name) {
        this.scolor = scolor;
        this.pcolor = pcolor;
        this.name = name;
    }

    public static RankColor of(String c) {
        for(RankColor rankColor : values()) {
            if(rankColor.name().equals(c)) return rankColor;
        }
        return null;
    }
    public String formatTitan(String name) {
        int i = 1;
        StringBuilder s = new StringBuilder(scolor + "§l☆ ");
        for(char c : name.toCharArray()) {
            if(i == 1) {
                i = 0;
                s.append(pcolor).append("§L");
            } else {
                i = 1;
                s.append(scolor).append("§L");
            }
            s.append(c);
        }
        return s.toString()+" "+scolor+"§l☆";
    }
    public String format(String name) {
        return this.scolor+"§l§k:"+this.pcolor+"§l"+name+this.scolor+"§l§k:§7";
    }

}
