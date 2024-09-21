package de.mxzx;

import de.mxzx.controller.*;
import de.mxzx.service.*;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


@Getter
public class Main extends JavaPlugin {

    private HibernateService hibernateService = new HibernateService();
    private PlayerDataService playerDataService;
    private AmsService amsService;
    private WarpService warpService;
    private PunishmentService punishmentService;
    private MineService mineService;
    private ClanService clanService;
    private SeasonService seasonService;
    private MilestoneService milestoneService;

    private PlayerDataController playerDataController;
    private AmsController amsController;
    private WarpController warpController;
    private PunishmentController punishmentController;
    private ClanController clanController;
    private MineController mineController;
    private SeasonController seasonController;
    private MilestoneController milestoneController;

    public void onEnable() {
        this.getCommand("test").setExecutor(new TestCommand());
        this.playerDataService = new PlayerDataService(getHibernateService());
        this.amsService = new AmsService(getHibernateService());
        this.warpService = new WarpService(getHibernateService());
        this.punishmentService = new PunishmentService(getHibernateService());
        this.mineService = new MineService(getHibernateService());
        this.clanService = new ClanService(getHibernateService());
        this.seasonService = new SeasonService(getHibernateService());
        this.milestoneService = new MilestoneService(getHibernateService());

        this.playerDataController = new PlayerDataController(new HashMap<>(), getPlayerDataService());
        this.amsController = new AmsController(new HashMap<>(), getAmsService());
        this.warpController = new WarpController(new HashMap<>(), getWarpService());
        this.punishmentController = new PunishmentController(new HashMap<>(), getPunishmentService());
        this.clanController = new ClanController(new HashMap<>(), getClanService());
        this.mineController = new MineController(new HashMap<>(), getMineService());
        this.seasonController = new SeasonController(new HashMap<>(), getSeasonService());
        this.milestoneController = new MilestoneController(new HashMap<>(), getMilestoneService());
    }

    public static Main getInstance() {
        return Main.getPlugin(Main.class);
    }
}
