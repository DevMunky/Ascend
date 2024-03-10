package prog.ferrlix.ascend.domains;

public class Flags {
    public enum Type{
        GLOBAL_GUEST, //essentially just public
        GLOBAL_SWITCHER, //anyone can switch blocks states
        GLOBAL_TAKER, //anyone can manipulate items
        ADMIN, //admin domain for any reason
    }
}
