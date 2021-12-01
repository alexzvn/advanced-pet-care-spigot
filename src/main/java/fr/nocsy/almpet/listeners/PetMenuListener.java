package fr.nocsy.almpet.listeners;

import fr.nocsy.almpet.data.Pet;
import fr.nocsy.almpet.data.inventories.PetMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PetMenuListener
        implements Listener {
    @EventHandler
    public void click(InventoryClickEvent e) {
        /* 19 */ if (e.getView().getTitle().equals(PetMenu.getTitle())) {

            /* 21 */ e.setCancelled(true);
            /* 22 */ Player p = (Player) e.getWhoClicked();
            /* 23 */ ItemStack it = e.getCurrentItem();
            /* 24 */ if (it != null) {

                /* 26 */ if (it.hasItemMeta() && it.getItemMeta().hasLocalizedName()
                        && it.getItemMeta().getLocalizedName().contains("AlmPetPage;")) {

                    /* 28 */ int page = Integer.parseInt(it.getItemMeta().getLocalizedName().split(";")[1]);
                    /* 29 */ p.closeInventory();
                    /* 30 */ if (e.getClick() == ClickType.LEFT) {

                        /* 32 */ PetMenu menu = new PetMenu(p, Math.max(page - 1, 0), true);
                        /* 33 */ menu.open(p);
                    } else {

                        /* 37 */ PetMenu menu = new PetMenu(p, page + 1, true);
                        /* 38 */ menu.open(p);
                    }

                    return;
                }
                /* 43 */ Pet petObject = Pet.getFromIcon(it);
                /* 44 */ if (petObject != null) {

                    /* 46 */ p.closeInventory();
                    /* 47 */ Pet pet = petObject.copy();
                    /* 48 */ pet.spawnWithMessage(p, p.getLocation());
                }
            }
        }
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\listeners\PetMenuListener.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
