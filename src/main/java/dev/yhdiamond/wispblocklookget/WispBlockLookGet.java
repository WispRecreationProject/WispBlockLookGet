package dev.yhdiamond.wispblocklookget;

import dev.yhdiamond.wispblocklookget.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class WispBlockLookGet extends JavaPlugin {

    public static WispBlockLookGet plugin;
    public static boolean isStarted = false;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new LookListener(), this);
        plugin = this;
        getCommand("blocklookget").setExecutor(new StartCommand());
        getCommand("blocklookget").setTabCompleter(new CommandComplete());
        new Metrics(this, 10888);
    }
}
