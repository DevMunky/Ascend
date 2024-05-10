package dev.munky.ascend.domain;

import dev.munky.ascend.util.RelativeLocation;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

/**
 * An area managed by Ascend
 */
public abstract class Domain {
    public final RelativeLocation location;
    public Domain(RelativeLocation location){
        this.location = location;
    }
}
