package prog.ferrlix.ascend.events;

import org.bukkit.event.Cancellable;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import prog.ferrlix.ascend.domains.Domain;

import java.util.UUID;

public class DomainCreateEvent extends Event implements Cancellable{
    @Override
    public boolean isCancelled() {return isCancelled;}
    @Override
    public void setCancelled(boolean cancel) {
        //do things to cancel
        isCancelled = true;
    }
    private UUID owner;
    private boolean isCancelled;
    private Domain domain;
    public DomainCreateEvent(UUID owner){
        this.owner = owner;
        this.domain = new Domain(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                owner
        );
        this.isCancelled = false;
    }
    public UUID getOwner(){return owner;}
    public Domain getDomain(){return domain;}

    /**
     * dumb handlers
     */
    private static final HandlerList handlers = new HandlerList();
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
