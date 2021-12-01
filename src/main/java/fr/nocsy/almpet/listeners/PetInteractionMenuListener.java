/*     */
package fr.nocsy.almpet.listeners;

/*     */
import fr.nocsy.almpet.data.FormatArg;
import fr.nocsy.almpet.data.Items;
import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import fr.nocsy.almpet.data.inventories.PetInteractionMenu;
import fr.nocsy.almpet.data.inventories.PetMenu;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

/*     */
public class PetInteractionMenuListener
        implements Listener {
    @EventHandler
    public void click(InventoryClickEvent e) {
        /* 26 */ if (e.getView().getTitle().equalsIgnoreCase(PetInteractionMenu.getTitle())) {

            /* 28 */ e.setCancelled(true);

            /* 30 */ Player p = (Player) e.getWhoClicked();

            /* 32 */ if (e.getClickedInventory() == null) {

                /* 34 */ openBackPetMenu(p);

                return;
            }
            /* 38 */ ItemStack it = e.getCurrentItem();
            /* 39 */ if (it != null && it.hasItemMeta() && it.getItemMeta().hasDisplayName()) {

                /* 41 */ if (it.getItemMeta().hasLocalizedName() && it.getItemMeta().getLocalizedName()
                        .equals(Items.PETMENU.getItem().getItemMeta().getLocalizedName())) {

                    /* 43 */ openBackPetMenu(p);

                    return;
                }
                /* 47 */ Pet pet = Pet.getFromLastInteractedWith(p);
                /* 48 */ if (pet == null) {

                    /* 50 */ p.closeInventory();

                    return;
                }
                /* 54 */ if (!pet.isStillHere()) {

                    /* 56 */ Language.REVOKED_BEFORE_CHANGES.sendMessage(p);
                    /* 57 */ p.closeInventory();

                    return;
                }
                /* 61 */ if (it.isSimilar(Items.MOUNT.getItem())) {

                    /* 63 */ if (p.isInsideVehicle()) {
                        /* 65 */ Language.ALREADY_INSIDE_VEHICULE.sendMessage(p);
                    }
                    /* 67 */ else if (!pet.setMount((Entity) p)) {
                        /* 69 */ Language.NOT_MOUNTABLE.sendMessage(p);
                    }

                    /* 72 */ } else if (it.isSimilar(Items.RENAME.getItem())) {

                    /* 74 */ if (!this.waitingForAnswer.contains(p.getUniqueId())) {
                        /* 75 */ this.waitingForAnswer.add(p.getUniqueId());
                    }
                    /* 77 */ Language.TYPE_NAME_IN_CHAT.sendMessage(p);
                    /* 78 */ Language.IF_WISH_TO_REMOVE_NAME.sendMessageFormated((CommandSender) p,
                            new FormatArg[] { new FormatArg("%tag%", Language.TAG_TO_REMOVE_NAME.getMessage()) });
                }
                /* 80 */ else if (e.getSlot() == 2) {

                    /* 82 */ pet.despawn();
                    /* 83 */ Language.REVOKED.sendMessage(p);
                }
                /* 85 */ p.closeInventory();
            }
        }
    }
    /*     */
    /*     */

    /* 92 */ private ArrayList<UUID> waitingForAnswer = new ArrayList<>();

    public ArrayList<UUID> getWaitingForAnswer() {
        return this.waitingForAnswer;
    }
    /*     */
    /*     */

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        /* 98 */ Player p = e.getPlayer();

        /* 100 */ if (this.waitingForAnswer.contains(p.getUniqueId())) {

            /* 102 */ this.waitingForAnswer.remove(p.getUniqueId());
            /* 103 */ e.setCancelled(true);

            /* 105 */ String name = e.getMessage();
            /* 106 */ name = ChatColor.translateAlternateColorCodes('&', name);

            /* 108 */ Pet pet = Pet.getFromLastInteractedWith(p);

            /* 110 */ if (pet != null && pet.isStillHere()) {

                /* 112 */ pet.setDisplayName(name, true);

                /* 114 */ Language.NICKNAME_CHANGED_SUCCESSFULY.sendMessage(p);
            } else {

                /* 118 */ Language.REVOKED_BEFORE_CHANGES.sendMessage(p);
            }
        }
    }
    /*     */
    /*     */

    private void openBackPetMenu(Player p) {
        /* 126 */ UUID uuid = p.getUniqueId();

        /* 128 */ PetMenu menu = new PetMenu(p, 0, false);
        /* 129 */ menu.open(p);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\listeners\PetInteractionMenuListener.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
