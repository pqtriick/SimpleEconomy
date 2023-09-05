package de.pqtriick.economy.util.Skull;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author pqtriick_
 * @created 00:33, 05.09.2023
 */

public class SkullBuilder {

    private ItemStack item;
    private SkullMeta Meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKELETON_SKULL);

    public SkullBuilder(ItemStack skull) {
        item = skull;
    }
    public SkullBuilder setOwner (UUID uuid){
        Meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
        return this;
    }
    public SkullBuilder setDisplayName (String DisplayName){
        Meta.setDisplayName(DisplayName);
        return this;
    }
    public SkullBuilder setLore (String... string){
        Meta.setLore(Arrays.asList(string));
        return this;
    }
    public ItemStack build(){
        item.setItemMeta(Meta);
        return item;
    }
}
