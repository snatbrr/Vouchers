package com.snat.vouchers.managers;

import com.snat.vouchers.Voucher;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;


    public static void setUpConfig(Voucher voucher) {
        ConfigManager.config = voucher.getConfig();
        voucher.saveDefaultConfig();
    }



}
