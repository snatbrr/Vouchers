package com.snat.vouchers;

import com.snat.vouchers.commands.VoucherCommand;
import com.snat.vouchers.listener.VoucherListener;
import com.snat.vouchers.managers.ConfigManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Voucher extends JavaPlugin {

    public static Plugin getPlugin() {
        return plugin;
    }

    private static Voucher plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("voucher").setExecutor(new VoucherCommand());
        getServer().getPluginManager().registerEvents(new VoucherListener(), this);
        ConfigManager.setUpConfig(this);

    }

}
