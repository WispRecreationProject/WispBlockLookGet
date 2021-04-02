package dev.yhdiamond.wispblocklookget;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LookListener implements Listener {
    public static Map<UUID, Block> playerToBlock = new HashMap<>();
    @EventHandler
    public void onLook(PlayerMoveEvent e) {
        if (WispBlockLookGet.isStarted && e.getPlayer().getTargetBlockExact(5) != null && !e.getPlayer().getTargetBlockExact(5).getType().equals(Material.AIR)) {
            if (!playerToBlock.containsKey(e.getPlayer().getUniqueId())) {
                playerToBlock.put(e.getPlayer().getUniqueId(), e.getPlayer().getTargetBlockExact(5));
            }
            if (e.getPlayer().getTargetBlockExact(5).getType().isItem() && !playerToBlock.get(e.getPlayer().getUniqueId()).equals(e.getPlayer().getTargetBlockExact(5))) {
                for (Map.Entry<Integer, ItemStack> leftover : e.getPlayer().getInventory().addItem(new ItemStack(e.getPlayer().getTargetBlockExact(5).getType())).entrySet()) {
                    e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), leftover.getValue());
                }
            }
            playerToBlock.put(e.getPlayer().getUniqueId(), e.getPlayer().getTargetBlockExact(5));

        }

    }
}
