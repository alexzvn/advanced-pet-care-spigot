package fr.nocsy.almpet.data;

public class GlobalConfig
        extends AbstractConfig {
    public static GlobalConfig instance;
    /* 17 */ private String prefix = "§8[§6AdvancedPet§8] »";

    public String getPrefix() {
        return this.prefix;
    }

    /* 19 */ private boolean nameable;
    private boolean mountable;
    private String defaultName = "§9Pet of %player%";
    private boolean rightClickToOpen;
    private boolean leftClickToOpen;

    public String getDefaultName() {
        return this.defaultName;
    }

    public boolean isNameable() {
        /* 21 */ return this.nameable;
    }

    public boolean isMountable() {
        /* 23 */ return this.mountable;
    }

    public boolean isRightClickToOpen() {
        /* 25 */ return this.rightClickToOpen;
    }

    public boolean isLeftClickToOpen() {
        /* 27 */ return this.leftClickToOpen;
    }

    /* 29 */ private int distanceTeleport = 30;

    public int getDistanceTeleport() {
        return this.distanceTeleport;
    }

    /* 31 */ private int maxNameLenght = 16;

    public int getMaxNameLenght() {
        return this.maxNameLenght;
    }

    public static GlobalConfig getInstance() {
        /* 37 */ if (instance == null) {
            /* 38 */ instance = new GlobalConfig();
        }
        /* 40 */ return instance;
    }

    public void init() {
        /* 45 */ init("", "config.yml");

        /* 47 */ if (getConfig().get("Prefix") == null)
            /* 48 */ getConfig().set("Prefix", this.prefix);
        /* 49 */ if (getConfig().get("DefaultName") == null)
            /* 50 */ getConfig().set("DefaultName", this.defaultName);
        /* 51 */ if (getConfig().get("RightClickToOpenMenu") == null)
            /* 52 */ getConfig().set("RightClickToOpenMenu", Boolean.valueOf(true));
        /* 53 */ if (getConfig().get("LeftClickToOpenMenu") == null)
            /* 54 */ getConfig().set("LeftClickToOpenMenu", Boolean.valueOf(true));
        /* 55 */ if (getConfig().get("Nameable") == null)
            /* 56 */ getConfig().set("Nameable", Boolean.valueOf(true));
        /* 57 */ if (getConfig().get("Mountable") == null)
            /* 58 */ getConfig().set("Mountable", Boolean.valueOf(true));
        /* 59 */ if (getConfig().get("DistanceTeleport") == null)
            /* 60 */ getConfig().set("DistanceTeleport", Integer.valueOf(30));
        /* 61 */ if (getConfig().get("MaxNameLenght") == null) {
            /* 62 */ getConfig().set("MaxNameLenght", Integer.valueOf(this.maxNameLenght));
        }
        /* 64 */ save();
        /* 65 */ reload();
    }

    public void save() {
        /* 70 */ super.save();
    }

    public void reload() {
        /* 76 */ loadConfig();

        /* 78 */ this.prefix = getConfig().getString("Prefix");
        /* 79 */ this.defaultName = getConfig().getString("DefaultName");
        /* 80 */ this.rightClickToOpen = getConfig().getBoolean("RightClickToOpenMenu");
        /* 81 */ this.leftClickToOpen = getConfig().getBoolean("LeftClickToOpenMenu");
        /* 82 */ this.nameable = getConfig().getBoolean("Nameable");
        /* 83 */ this.mountable = getConfig().getBoolean("Mountable");
        /* 84 */ this.distanceTeleport = getConfig().getInt("DistanceTeleport");
        /* 85 */ this.maxNameLenght = getConfig().getInt("MaxNameLenght");
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\GlobalConfig.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
