package fr.nocsy.almpet.data.flags;

import fr.nocsy.almpet.AdvancedPet;
import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DespawnPetFlag
        extends AbstractFlag implements StoppableFlag {
    public DespawnPetFlag(AdvancedPet instance) {
        /* 14 */ super("despawnPet", false, instance);
    }

    int task;

    public void register() {
        /* 20 */ super.register();
        /* 21 */ launch();
    }

    private void launch() {
        /* 27 */ if (getFlag() == null) {

            /* 29 */ AdvancedPet.getLog().warning(AdvancedPet.getLogName() + "Flag " + AdvancedPet.getLogName()
                    + " couldn't not be launched as it's null. Please contact Nocsy.");

            return;
        }

        /* 34 */ AdvancedPet.getLog()
                .info(AdvancedPet.getLogName() + "Starting flag " + AdvancedPet.getLogName() + ".");

        /* 37 */ this.task = Bukkit.getServer().getScheduler()
                .scheduleSyncRepeatingTask((Plugin) getAdvancedPetInstance(), new Runnable() {
                    public void run() {
                        /* 41 */ for (UUID owner : Pet.getActivePets().keySet()) {

                            /* 43 */ Pet pet = (Pet) Pet.getActivePets().get(owner);
                            /* 44 */ Player p = Bukkit.getPlayer(owner);

                            /* 46 */ if (p != null) {

                                /* 48 */ boolean hasToBeRemoved = DespawnPetFlag.this.testState(p);

                                /* 50 */ if (hasToBeRemoved) {

                                    /* 52 */ pet.despawn();
                                    /* 53 */ Language.CANT_FOLLOW_HERE.sendMessage(p);
                                }
                            }
                        }
                    }
                }, 0L, 20L);
    }

    public void stop() {
        /* 66 */ Bukkit.getServer().getScheduler().cancelTask(this.task);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\flags\DespawnPetFlag.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
