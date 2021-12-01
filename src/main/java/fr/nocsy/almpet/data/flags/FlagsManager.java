package fr.nocsy.almpet.data.flags;

import com.sk89q.worldguard.protection.flags.StateFlag;
import fr.nocsy.almpet.AdvancedPet;
import java.util.ArrayList;

public class FlagsManager {
    public static StateFlag ALMPET;
    /* 12 */ private static ArrayList<AbstractFlag> flags = new ArrayList<>();

    public static void init(AdvancedPet instance) {
        /* 16 */ ArrayList<AbstractFlag> flags = new ArrayList<>();

        /* 18 */ if (instance == null) {

            /* 20 */ AdvancedPet.getLog().warning("The main instance is null. The flags could not be registered...");

            return;
        }
        /* 24 */ flags.add(new DismountPetFlag(instance));
        /* 25 */ flags.add(new DespawnPetFlag(instance));

        /* 27 */ for (AbstractFlag flag : flags) {
            /* 29 */ flag.register();
        }
    }

    public static void stopFlags() {
        /* 36 */ for (AbstractFlag flag : flags) {

            /* 38 */ if (flag instanceof StoppableFlag) {
                /* 40 */ ((StoppableFlag) flag).stop();
            }
        }
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\flags\FlagsManager.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
