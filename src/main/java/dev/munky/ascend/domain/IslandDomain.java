package dev.munky.ascend.domain;

import dev.munky.ascend.util.RelativeLocation;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class IslandDomain extends Domain{
    public final Player owner;
    public final HashSet<DomainPlayer> players = new HashSet<>();
    public IslandDomain(RelativeLocation location, Player owner) {
        super(location);
        this.owner = owner;
    }
}
