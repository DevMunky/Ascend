package prog.ferrlix.ascend.util;

import net.kyori.adventure.text.format.TextColor;

public class StringUtil {
    public static TextColor hexToTextColor(String hexCode) {
        if (hexCode.matches("^#?[A-Fa-f0-9]{6}$")) {
            if (!hexCode.startsWith("#"))
                hexCode = "#" + hexCode;
            return TextColor.fromHexString(hexCode);
        }
        return TextColor.color(255, 255, 255); //fallback color
    }
}
