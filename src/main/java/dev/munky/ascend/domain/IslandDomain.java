package dev.munky.ascend.domain;

import dev.munky.ascend.domain.permission.DomainPermission;
import dev.munky.ascend.util.RelativeLocation;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IslandDomain extends Domain {
    public final UUID owner;
    public final Map<DomainPermission, UUID> islandPermissions;
    public IslandDomain(UUID domainUUID, UUID owner, RelativeLocation location, Map<DomainPermission,UUID> islandPermissions){
        super(domainUUID, location);
        this.owner = owner;
        this.islandPermissions = islandPermissions;
    }
    public IslandDomain(RelativeLocation location, Player owner) {
        this(UUID.randomUUID(),owner.getUniqueId(), location, new HashMap<>());
    }
}
