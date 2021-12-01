package fr.nocsy.almpet.data.inventories;

import fr.nocsy.almpet.data.AbstractConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerData
        extends AbstractConfig {
    public static HashMap<UUID, PlayerData> getRegisteredData() {
        /* 13 */ return registeredData;
        /* 14 */ }

    public static HashMap<UUID, PlayerData> registeredData = new HashMap<>();
    private final UUID uuid;
    /* 15 */ public HashMap<String, String> mapOfRegisteredNames = new HashMap<>();

    public HashMap<String, String> getMapOfRegisteredNames() {
        return this.mapOfRegisteredNames;
    }

    public UUID getUuid() {
        /* 18 */ return this.uuid;
    }

    private PlayerData(UUID uuid) {
        /* 23 */ this.uuid = uuid;
        /* 24 */ init();
        /* 25 */ save();
    }

    public static PlayerData get(UUID owner) {
        /* 30 */ if (registeredData.containsKey(owner)) {
            /* 32 */ return registeredData.get(owner);
        }

        /* 36 */ PlayerData data = new PlayerData(owner);
        /* 37 */ registeredData.put(owner, data);
        /* 38 */ return data;
    }

    public void init() {
        /* 44 */ init("PlayerData", this.uuid.toString() + ".yml");

        /* 46 */ if (getConfig().get("Names") == null) {
            /* 47 */ getConfig().set("Names", new ArrayList());
        }
        /* 49 */ reload();
    }

    public void save() {
        /* 55 */ ArrayList<String> serializedMap = new ArrayList<>();

        /* 57 */ for (String id : this.mapOfRegisteredNames.keySet()) {

            /* 59 */ String name = this.mapOfRegisteredNames.get(id);
            /* 60 */ String seria = id + ";" + id;
            /* 61 */ serializedMap.add(seria);
        }

        /* 64 */ getConfig().set("Names", serializedMap);

        /* 66 */ super.save();
    }

    public void reload() {
        /* 72 */ this.mapOfRegisteredNames.clear();

        /* 74 */ for (String seria : getConfig().getStringList("Names")) {

            /* 76 */ String[] table = seria.split(";");
            /* 77 */ String id = table[0];
            /* 78 */ String name = table[1];

            /* 80 */ this.mapOfRegisteredNames.put(id, name);
        }
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\inventories\PlayerData.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
