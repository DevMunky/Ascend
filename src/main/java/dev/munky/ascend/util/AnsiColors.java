package dev.munky.ascend.util;

public class AnsiColors {
    /*
    https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit
     */
    public static final String RESET = "\u001B[0m";
    public static final String FG_BLACK = "\u001B[30m";
    public static final String BOLD = "\u001B[1m";
    public static final String RAPID_BLINK = "\u001B[6m";
    public static final String FG_RED = "\u001B[38;5;9m";
    public static final String FG_GREEN = "\u001B[32m";
    public static final String FG_YELLOW = "\u001B[33m";
    public static final String FG_BLUE = "\u001B[38;5;12m";
    public static final String FG_PURPLE = "\u001B[38;5;207m";
    public static final String FG_CYAN = "\u001B[36m";
    public static final String FG_WHITE = "\u001B[37m";
    public static final String BG_RED = "\u001B[41m";
    public static final String FG_DARK_RED = "\u001B[38;5;88m";
}
