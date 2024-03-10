package prog.ferrlix.ascend.objects;

import org.bukkit.entity.Player;
import prog.ferrlix.ascend.permissions.PermissionLevel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DomainMap {
    private Map<Player,Collection<PermissionLevel>> map = new HashMap<>();
    public DomainMap(Player p, Collection<PermissionLevel> permissionLevels){
        map.put(p, permissionLevels);
    }
    public DomainMap(){
    }
    public boolean hasPerm(Player p,PermissionLevel perm){
        return map.get(p).contains(perm);
    }
    public void addPerm(Player p, PermissionLevel perm){
        Collection<PermissionLevel> list = map.get(p);
        list.add(perm);
        map.put(p, list);
    }
    public void removePerm(Player p, PermissionLevel perm){
        Collection<PermissionLevel> list = map.get(p);
        list.remove(perm);
        map.put(p, list);
    }
    public void addPlayer(Player p, Collection<PermissionLevel> permissionLevels){
        map.put(p,permissionLevels);
    }
    public void removePlayer(Player p){
        map.remove(p);
    }
    @Override
    public String toString(){
        return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "#" + map;
    }
}
