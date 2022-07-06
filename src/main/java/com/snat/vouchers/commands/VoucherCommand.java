package com.snat.vouchers.commands;

import com.snat.vouchers.Voucher;
import com.snat.vouchers.managers.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.snat.vouchers.Utils.color;

public class VoucherCommand implements CommandExecutor  {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration config = Voucher.getPlugin().getConfig();



            if (args.length == 2) {

                String voucherInputed = args[1];
                ItemStack voucherItem = null;
                for(String voucher : config.getKeys(false)){
                    if(voucher.toLowerCase().equals(voucherInputed.toLowerCase())){
                        String DisplayName = "notset";
                        List<String> commands = new ArrayList<String>();
                        if(config.getString(voucher + ".name") != null){
                            DisplayName = config.getString(color(voucher + ".name"));
                        }
                        if(config.get(voucher + ".commands") != null){
                            for(String cmd : config.getStringList(voucher + ".commands")) {
                                commands.add(cmd);
                            }
                        }


                        voucherItem = new ItemStack(Material.PAPER);
                        ItemMeta voucherMeta = voucherItem.getItemMeta();
                        voucherMeta.setDisplayName(color(DisplayName));
                        List<String> voucherLore = new ArrayList<String>();
                        voucherLore.add(ChatColor.RED + "Voucher: : " + color(DisplayName));
                        voucherLore.add(ChatColor.RED + "Commands: : " + commands);
                        voucherMeta.setLore(voucherLore);
                        voucherItem.setItemMeta(voucherMeta);

                    }
                }

                switch(args[0]) {
                    case "give":
                        if (player.hasPermission("voucher.give")) {
                            player.getInventory().addItem(voucherItem);
                        } else {
                            player.sendMessage(color("&cYou do not have permission!"));
                        }
                        break;
                    default:

                }

            } else {
                player.sendMessage(color("&cIncorrect Usage! Use /voucher give <voucher>!"));
            }

        }

        return false;
    }

}
