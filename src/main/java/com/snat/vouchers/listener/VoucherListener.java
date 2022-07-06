package com.snat.vouchers.listener;

import com.snat.vouchers.Voucher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.List;

import static com.snat.vouchers.Utils.color;


public class VoucherListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player player = e.getPlayer();
        FileConfiguration config = Voucher.getPlugin().getConfig();

        for(String voucher : config.getKeys(false)){
            if (player.getInventory().getItemInMainHand().getType().equals(Material.PAPER)) {
                List<String> lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
                String loreName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().toString();

                if(loreName.equals(color((String) config.get(voucher + ".name")))) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        List<String> cmds = player.getInventory().getItemInMainHand().getItemMeta().getLore();
                        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                        System.out.println(cmds.get(1));
                        String commands = Arrays.toString(cmds.get(1).split(ChatColor.RED + "Commands: : ")).replace("[", "").replace("[", "");
                        String[] cmdsArray = commands.split(", ");

                        for (String cmd : cmdsArray) {
                            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                            Bukkit.dispatchCommand(console, cmd.replace("%player%", player.getName()));
                        }
                    }
                }
            }
        }



    }
}
