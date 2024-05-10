package dev.munky.ascend.domain;

import dev.munky.ascend.domain.permission.DomainPermission;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DomainPlayer {
    public final Player player;
    public final Map<DomainPermission, Boolean> permissions = new HashMap<>(Map.of(
            DomainPermission.NONE,false,
            DomainPermission.VISIT,false,
            DomainPermission.BLOCK_BREAK,false,
            DomainPermission.BLOCK_PLACE,false,
            DomainPermission.BLOCK_FLIP_FLOP,false,
            DomainPermission.BLOCK_OPEN,false,
            DomainPermission.DOMAIN_MODIFY,false,
            DomainPermission.DOMAIN_QUANTUM_MODIFY,false
    ));
    public DomainPlayer(Player player){
        this.player = player;
    }
}
