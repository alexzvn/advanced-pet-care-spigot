package fr.nocsy.almpet.data.inventories;

import fr.nocsy.almpet.data.GlobalConfig;
import fr.nocsy.almpet.data.Items;
import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class PetInteractionMenu {
    public static String getTitle() {
        /* 18 */ return title;
        /* 19 */ }

    private static String title = Language.INVENTORY_PETS_MENU_INTERACTIONS.getMessage();
    private Inventory inventory;

    public Inventory getInventory() {
        /* 21 */ return this.inventory;
    }

    public PetInteractionMenu(Pet pet) {
        /* 26 */ this.inventory = Bukkit.createInventory(null, InventoryType.HOPPER, title);

        /* 28 */ this.inventory.setItem(0, Items.PETMENU.getItem());

        /* 30 */ if (GlobalConfig.getInstance().isNameable()) {
            /* 31 */ this.inventory.setItem(1, Items.RENAME.getItem());
        } else {
            /* 33 */ this.inventory.setItem(1, Items.deco(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
            /* 34 */ }
        this.inventory.setItem(2, Items.petInfo(pet));

        /* 36 */ if (GlobalConfig.getInstance().isMountable() && pet.isMountable()) {
            /* 37 */ this.inventory.setItem(3, Items.MOUNT.getItem());
        } else {

            /* 40 */ this.inventory.setItem(3, Items.deco(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        }
        /* 42 */ this.inventory.setItem(4, Items.deco(Material.BLUE_STAINED_GLASS_PANE));
    }

    public void open(Player p) {
        /* 47 */ p.openInventory(this.inventory);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\inventories\PetInteractionMenu.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
