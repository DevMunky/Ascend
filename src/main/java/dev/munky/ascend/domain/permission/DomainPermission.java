package dev.munky.ascend.domain.permission;

import java.util.Arrays;
import java.util.List;

public enum DomainPermission {
    NONE,
    VISIT,
    BLOCK_BREAK,
    BLOCK_PLACE,
    BLOCK_FLIP_FLOP,
    BLOCK_OPEN,
    DOMAIN_MODIFY,
    DOMAIN_QUANTUM_MODIFY;
    DomainPermission(){}
    public static List<String> getPermissionNames(){
        return Arrays.stream(DomainPermission.values()).map(DomainPermission::name).toList();
    }
}
