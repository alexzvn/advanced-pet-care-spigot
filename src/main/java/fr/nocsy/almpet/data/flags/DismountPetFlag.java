package fr.nocsy.almpet.data.flags;

import fr.nocsy.almpet.AdvancedPet;
import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DismountPetFlag extends AbstractFlag implements StoppableFlag {
    public DismountPetFlag(AdvancedPet instance) {
        /* 14 */ super("dismountPet", false, instance);
    }

    private int task;

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

                            /* 45 */ if (!pet.isMountable()) {
                                continue;
                            }
                            /* 48 */ Player p = Bukkit.getPlayer(owner);

                            /* 50 */ if (p != null) {

                                /* 52 */ if (!pet.hasMount((Entity) p)) {
                                    continue;
                                }
                                /* 55 */ boolean hasToBeEjected = DismountPetFlag.this.testState(p);

                                /* 57 */ if (hasToBeEjected) {

                                    /* 59 */ pet.dismount((Entity) p);
                                    /* 60 */ Language.NOT_MOUNTABLE_HERE.sendMessage(p);
                                }
                            }
                        }
                    }
                }, 0L, 20L);
    }

    public void stop() {
        /* 73 */ Bukkit.getServer().getScheduler().cancelTask(this.task);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\flags\DismountPetFlag.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
