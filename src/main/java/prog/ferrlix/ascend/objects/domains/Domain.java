package prog.ferrlix.ascend.objects.domains;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import prog.ferrlix.ascend.Ascend;
import prog.ferrlix.ascend.util.ConfigUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Domain {
    protected static ArrayList<Domain> instanceList = new ArrayList<>();
    private UUID id;
    private Location location;
    private Integer sizeX;
    private Integer sizeZ;
    private Collection<DomainFlags.Type> flags;
    private DomainMap domainMap;
    private Integer level;
    private UUID owner;
    public Domain(UUID id,
                  Location location,
                  Integer sizeX,
                  Integer sizeZ,
                  Collection<DomainFlags.Type> flags,
                  DomainMap domainMap,
                  Integer level,
                  UUID owner){
        FileConfiguration config = ConfigUtil.get("config.yml").getFileConfig();
        if (id==null){ //new uuid if it didn't have one before
            id = UUID.randomUUID();
        }if (location==null){ //weird if statements are just for readability
            location = null;
        }if (sizeX==null){ //default size
            try {
                sizeX = (Integer) config.get("start.sizex", 0);
                if (sizeX == 0)
                    Ascend.logger.severe("default start sizeX null path in config");
            }catch(Exception e){
                e.printStackTrace();
                Ascend.logger.severe("Default Size X is not an Integer (whole number) in the config");
                return;
            }
        }if (sizeZ==null) { //default size
            try {
                sizeZ = (Integer) config.get("start.sizez", 0);
                if (sizeZ == 0)
                    Ascend.logger.severe("default start sizeZ null path in config");
            }catch(Exception e){
                e.printStackTrace();
                Ascend.logger.severe("Default Size Z is not an Integer (whole number) in the config");
                return;
            }
        }if (flags==null){ //weird if statements are just for readability
            flags = null;
        }if (domainMap==null){ //new default DomainMap
            domainMap = new DomainMap();
        }if (level==null){ //default level
            level = (Integer) config.get("start.island-level");
        }if (owner==null){
            Ascend.logger.severe("Domain owner cannot be null!\n Domain not created");
            return;
        }
        this.id = id;
        this.location = location;
        this.sizeX = sizeX;
        this.sizeZ = sizeZ;
        this.flags = flags;
        this.domainMap = domainMap;
        this.level = level;
        this.owner = owner;
        instanceList.add(this);
        if (Ascend.debug)
            Ascend.logger.info("""
                    Domain created for a player
                    Data:
                    -id: %s
                    -sizeX: %s
                    -sizeZ: %s
                    -domainMap: %s
                    -level: %s
                    -owner: %s
                    """.formatted(id,sizeX,sizeZ,domainMap,level,owner));
    }
    public UUID getId(){return this.id;}
    public Location getLocation(){return this.location;}
    public void setLocation(Location loc){this.location = loc;}
    public Integer[] getSize(){return new Integer[]{this.sizeX, this.sizeZ};}
    public Collection<DomainFlags.Type> getFlags(){return this.flags;}
    public DomainMap getDomainMap(){return this.domainMap;}
    public Integer getLevel(){return this.level;}
    public UUID getOwner(){return this.owner;}
    public static ArrayList<Domain> getInstances(){return instanceList;}
    public static boolean ownerExists(UUID owner){
        for (Domain e : getInstances()){
             if (e.owner == owner)
                 return true;
        }return false;
    }
}
