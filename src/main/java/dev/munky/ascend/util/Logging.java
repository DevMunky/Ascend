package dev.munky.ascend.util;

import dev.munky.ascend.Ascend;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class Logging {
    private static final Plugin PLUGIN = Ascend.plugin;
    public static void debug(String message){
        if (Ascend.config.debug)
            Logger.getLogger(AnsiColors.FG_RED + "Debug - " + PLUGIN.getName() + AnsiColors.RESET).info( AnsiColors.FG_CYAN + message + AnsiColors.RESET);
    }
    public static void severe(String message){
        Logger.getLogger(AnsiColors.FG_RED + AnsiColors.RAPID_BLINK + "Error - " + PLUGIN.getName() + AnsiColors.RESET).severe("\n" + AnsiColors.BG_RED + AnsiColors.FG_CYAN + message + AnsiColors.RESET);
    }
    public static void warning(String message){
        Logger.getLogger( AnsiColors.FG_YELLOW + "Warning - " + PLUGIN.getName() + AnsiColors.RESET).warning(AnsiColors.FG_YELLOW + message + AnsiColors.RESET);
    }
    public static void info(String message){
        Logger.getLogger(AnsiColors.FG_BLUE + PLUGIN.getName() + AnsiColors.RESET).info(AnsiColors.FG_GREEN + message + AnsiColors.RESET);
    }
}
