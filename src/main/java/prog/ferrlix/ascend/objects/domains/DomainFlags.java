package prog.ferrlix.ascend.objects.domains;

public class DomainFlags {
    public enum Type{
        GLOBAL_GUEST, //essentially just public
        GLOBAL_SWITCHER, //anyone can switch blocks states
        GLOBAL_TAKER, //anyone can manipulate items
        ADMIN, //admin domain for any reason
    }
}
