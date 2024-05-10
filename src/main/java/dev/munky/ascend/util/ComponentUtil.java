package dev.munky.ascend.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ComponentUtil {
    public static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static String toString(Component component){
        String result = "NULL";
        if (component==null) Logging.warning("Null Component attempted serialization");
        else result = miniMessage.serialize(component);
        return result;
    }
    public static Component toComponent(String string){
        Component result = Component.text("NULL").color(NamedTextColor.RED);
        if (string==null) Logging.warning("Null string attempted deserialization");
        else result = miniMessage.deserialize(string);
        return result;
    }

}
