package fr.nocsy.almpet.data.inventories;

import fr.nocsy.almpet.data.Items;
import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PetMenu {
    public static String getTitle() {
        /* 16 */ return title;
        /* 17 */ }

    private static String title = Language.INVENTORY_PETS_MENU.getMessage();
    private Inventory inventory;

    public Inventory getInventory() {
        /* 19 */ return this.inventory;
    }

    public PetMenu(Player p, int page, boolean addPager) {
        /* 24 */ List<Pet> availablePets = Pet.getAvailablePets(p);

        /* 26 */ while (availablePets.size() - 53 * page < 0 && page > 0) {

            /* 29 */ page--;
        }

        /* 32 */ int invSize = Math.max(Math.min(availablePets.size() - 53 * page, 53), 0);
        /* 33 */ while (invSize % 9 != 0 || invSize == 0) {
            /* 35 */ invSize++;
        }

        /* 38 */ this.inventory = Bukkit.createInventory(null, invSize, title);

        /* 40 */ for (int i = page * 53; i < invSize + page * 53; i++) {

            /* 42 */ if (i < availablePets.size()) {

                /* 46 */ Pet pet = availablePets.get(i);

                /* 48 */ if (i % 53 == 0 && i > page * 53) {

                    /* 50 */ this.inventory.setItem(invSize - 1, Items.page(page));
                    break;
                }
                /* 53 */ this.inventory.addItem(new ItemStack[] { pet.getIcon() });
            }
        }

        /* 57 */ if (addPager) {
            /* 59 */ this.inventory.setItem(invSize - 1, Items.page(page));
        }
    }

    public void open(Player p) {
        /* 66 */ p.openInventory(this.inventory);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\inventories\PetMenu.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
