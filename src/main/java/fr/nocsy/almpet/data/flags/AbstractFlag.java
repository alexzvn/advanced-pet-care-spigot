package fr.nocsy.almpet.data.flags;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.flags.registry.SimpleFlagRegistry;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import fr.nocsy.almpet.AdvancedPet;
import org.bukkit.entity.Player;

public abstract class AbstractFlag {
    private StateFlag flag;
    private final AdvancedPet advancedPetInstance;

    public StateFlag getFlag() {
        /* 19 */ return this.flag;
    }

    private final String flagName;
    private final boolean defaultValue;

    public AdvancedPet getAdvancedPetInstance() {
        /* 22 */ return this.advancedPetInstance;
    }

    public String getFlagName() {
        /* 24 */ return this.flagName;
    }

    public boolean isDefaultValue() {
        /* 26 */ return this.defaultValue;
    }

    public AbstractFlag(String flagName, boolean defaultValue, AdvancedPet instance) {
        /* 31 */ this.flagName = flagName;
        /* 32 */ this.defaultValue = defaultValue;
        /* 33 */ this.advancedPetInstance = instance;
    }

    public void register() {
        /* 41 */ FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        /* 42 */ ((SimpleFlagRegistry) WorldGuard.getInstance().getFlagRegistry()).setInitialized(false);

        try {
            /* 47 */ StateFlag flag = new StateFlag(this.flagName, this.defaultValue);
            /* 48 */ registry.register((Flag) flag);
            /* 49 */ this.flag = flag;

            /* 51 */ AdvancedPet.getLog()
                    .info(AdvancedPet.getLogName() + AdvancedPet.getLogName() + " flag registered successfully !");
        }
        /* 53 */ catch (Exception e) {
            /* 54 */ AdvancedPet.getLog()
                    .warning(AdvancedPet.getLogName() + "Exception raised " + AdvancedPet.getLogName());
            /* 55 */ AdvancedPet.getLog().warning(AdvancedPet.getLogName() + AdvancedPet.getLogName()
                    + " seems to be conflicting with a previously existing instance of the plugin. Trying to attach the flag to the previous version...");

            /* 58 */ Flag<?> existing = registry.get(this.flagName);
            /* 59 */ AdvancedPet.getLog().warning(AdvancedPet.getLogName() + AdvancedPet.getLogName()
                    + " has been considered as " + getFlagName() + " by Worldguard");
            /* 60 */ if (existing instanceof StateFlag) {
                /* 61 */ this.flag = (StateFlag) existing;
                /* 62 */ AdvancedPet.getLog()
                        .info(AdvancedPet.getLogName() + AdvancedPet.getLogName() + " flag attached successfully !");
            } else {

                /* 66 */ AdvancedPet.getLog().warning(AdvancedPet.getLogName() + AdvancedPet.getLogName()
                        + " Flag couldn't be attached... Server restart will be necessary to fix the issue.");
            }
        }
    }

    public boolean testState(Player p) {
        /* 79 */ LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
        /* 80 */ Location loc = localPlayer.getLocation();
        /* 81 */ RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        /* 82 */ RegionQuery query = container.createQuery();

        /* 84 */ return query.testState(loc, localPlayer, new StateFlag[] { getFlag() });
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\flags\AbstractFlag.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
