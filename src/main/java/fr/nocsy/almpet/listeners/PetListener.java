/*     */
package fr.nocsy.almpet.listeners;

/*     */
import fr.nocsy.almpet.AdvancedPet;
import fr.nocsy.almpet.data.GlobalConfig;
import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import fr.nocsy.almpet.data.inventories.PetInteractionMenu;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDespawnEvent;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/*     */
public class PetListener
        implements Listener {
    @EventHandler
    public void interact(PlayerInteractEntityEvent e) {
        /* 32 */ if (!GlobalConfig.getInstance().isRightClickToOpen()) {
            return;
        }
        /* 35 */ Player p = e.getPlayer();
        /* 36 */ Entity ent = e.getRightClicked();

        /* 38 */ Pet pet = Pet.getFromEntity(ent);

        /* 40 */ if (pet != null && (pet
                /* 41 */ .getOwner().equals(p.getUniqueId()) || p.isOp())) {

            /* 43 */ PetInteractionMenu menu = new PetInteractionMenu(pet);
            /* 44 */ p.setMetadata("AlmPetInteracted",
                    (MetadataValue) new FixedMetadataValue((Plugin) AdvancedPet.getInstance(), pet));
            /* 45 */ menu.open(p);
        }
    }
    /*     */

    @EventHandler
    public void interact(EntityDamageByEntityEvent e) {
        /* 52 */ if (!GlobalConfig.getInstance().isLeftClickToOpen()) {
            return;
        }
        /* 55 */ if (!(e.getDamager() instanceof Player)) {
            return;
        }
        /* 58 */ Player p = (Player) e.getDamager();
        /* 59 */ Entity ent = e.getEntity();

        /* 61 */ Pet pet = Pet.getFromEntity(ent);

        /* 63 */ if (pet != null && (pet
                /* 64 */ .getOwner().equals(p.getUniqueId()) || p.isOp())) {

            /* 66 */ PetInteractionMenu menu = new PetInteractionMenu(pet);
            /* 67 */ p.setMetadata("AlmPetInteracted",
                    (MetadataValue) new FixedMetadataValue((Plugin) AdvancedPet.getInstance(), pet));
            /* 68 */ menu.open(p);
            /* 69 */ e.setCancelled(true);
            /* 70 */ e.setDamage(0.0D);
        }
    }
    /*     */

    @EventHandler
    public void teleport(PlayerChangedWorldEvent e) {
        /* 77 */ Player p = e.getPlayer();
        /* 78 */ if (Pet.getActivePets().containsKey(p.getUniqueId())) {

            /* 80 */ Pet pet = (Pet) Pet.getActivePets().get(p.getUniqueId());
            /* 81 */ pet.despawn();
            /* 82 */ pet.spawn(p, p.getLocation());
        }
    }
    /*     */
    /*     */

    @EventHandler
    public void teleport(PlayerTeleportEvent e) {
        /* 90 */ Player p = e.getPlayer();
        /* 91 */ if (Pet.getActivePets().containsKey(p.getUniqueId())) {

            /* 93 */ Pet pet = (Pet) Pet.getActivePets().get(p.getUniqueId());
            /* 94 */ pet.dismount((Entity) p);
        }
    }
    /*     */
    /*     */

    @EventHandler
    public void riding(EntityDamageEvent e) {
        /* 102 */ if (e.getEntity() instanceof Player) {

            /* 104 */ Player p = (Player) e.getEntity();
            /* 105 */ if (p.isInsideVehicle() && Pet.fromOwner(p.getUniqueId()) != null) {

                /* 107 */ Pet pet = Pet.fromOwner(p.getUniqueId());
                /* 108 */ pet.dismount((Entity) p);
            }
            return;
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    @EventHandler
    public void damaged(EntityDamageEvent e) {
        /* 122 */ if (e.getEntity() instanceof Player) {

            /* 124 */ Pet pet = Pet.getFromEntity(e.getEntity());
            /* 125 */ if (pet != null && pet.isInvulnerable()) {

                /* 127 */ e.setDamage(0.0D);
                /* 128 */ e.setCancelled(true);
            }
            return;
        }
    }
    /*     */

    @EventHandler
    public void gamemode(PlayerGameModeChangeEvent e) {
        /* 137 */ UUID uuid = e.getPlayer().getUniqueId();
        /* 138 */ if (Pet.getActivePets().containsKey(uuid) && e.getNewGameMode() == GameMode.SPECTATOR) {

            /* 140 */ Pet pet = (Pet) Pet.getActivePets().get(uuid);
            /* 141 */ pet.despawn();
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    @EventHandler
    public void despawn(MythicMobDespawnEvent e) {
        /* 152 */ if (e.getEntity() != null) {

            /* 154 */ Pet pet = Pet.getFromEntity(e.getEntity());
            /* 155 */ if (pet != null) {
                /* 157 */ if (!pet.isRemoved()) {

                    /* 159 */ pet.despawn();
                    /* 160 */ Player owner = Bukkit.getPlayer(pet.getOwner());
                    /* 161 */ if (owner != null) {
                        /* 163 */ Language.REVOKED.sendMessage(owner);
                    }
                }
            }
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    @EventHandler
    public void death(MythicMobDeathEvent e) {
        /* 177 */ if (e.getEntity() != null) {

            /* 179 */ Pet pet = Pet.getFromEntity(e.getEntity());
            /* 180 */ if (pet != null) {
                /* 182 */ if (!pet.isRemoved()) {

                    /* 184 */ pet.despawn();
                    /* 185 */ Player owner = Bukkit.getPlayer(pet.getOwner());
                    /* 186 */ if (owner != null) {
                        /* 188 */ Language.REVOKED.sendMessage(owner);
                    }
                }
            }
        }
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\listeners\PetListener.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
