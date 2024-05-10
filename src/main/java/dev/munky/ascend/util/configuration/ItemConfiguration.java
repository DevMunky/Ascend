package dev.munky.ascend.util.configuration;

import dev.munky.ascend.util.ComponentUtil;
import dev.munky.ascend.util.Logging;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;

import static net.kyori.adventure.text.Component.text;

public class ItemConfiguration extends AscendedConfiguration{

    public ItemConfiguration() {
        super("item.yml");
    }
    public ItemStack getDefinedItem(String path){
        ItemStack item = new ItemStack(Material.STONE);
        path = path.toLowerCase();
        try{
            ConfigurationSection itemSection = yaml.getConfigurationSection(path);
            if (itemSection == null) {
                Logging.warning("Item not found for path -> " + path);
                return item;
            }
            Material material = Material.getMaterial(itemSection.getString("material", "STONE").toUpperCase().trim().replaceAll(" ", "_"));
            Component name = itemSection.getComponent("name", MiniMessage.miniMessage(), text("Name of item not found"));
            name.decorationIfAbsent(TextDecoration.ITALIC, TextDecoration.State.FALSE);
            ArrayList<Component> lore = new ArrayList<>();
            for (String miniString : itemSection.getStringList("lore")) {
                lore.add(ComponentUtil.toComponent(miniString));
            }
            if (material!=null) item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.displayName(name);
            meta.lore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }
}
