package dev.munky.ascend.domain;

import dev.munky.ascend.util.RelativeLocation;

import java.util.UUID;

/**
 * An area managed by Ascend
 */
public abstract class Domain {
    public final UUID uuid;
    public final RelativeLocation location;
    public Domain(UUID domainUUID, RelativeLocation location){
        this.location = location;
        this.uuid = domainUUID;
    }
}
