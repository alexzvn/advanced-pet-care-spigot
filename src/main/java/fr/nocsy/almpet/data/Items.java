/*     */
package fr.nocsy.almpet.data;

/*     */
import fr.nocsy.almpet.utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
public enum Items {
    /* 17 */ MOUNT("mount"),
    /* 18 */ RENAME("rename"),
    /* 19 */ PETMENU("petmenu");

    public ItemStack getItem() {
        /* 21 */ return this.item;
    }

    private ItemStack item;

    Items(String name) {
        /* 26 */ switch (name) {

            case "mount":
                /* 29 */ this.item = mount();
                break;
            case "rename":
                /* 32 */ this.item = rename();
                break;
            case "petmenu":
                /* 35 */ this.item = backToPets();
                break;
        }
    }
    /*     */

    private static ItemStack mount() {
        /* 42 */ ItemStack it = new ItemStack(Material.SADDLE);
        /* 43 */ ItemMeta meta = it.getItemMeta();
        /* 44 */ meta.setDisplayName(Language.MOUNT_ITEM_NAME.getMessage());

        /* 46 */ ArrayList<String> lore = new ArrayList<>(
                Arrays.asList(Language.MOUNT_ITEM_DESCRIPTION.getMessage().split("\n")));
        /* 47 */ meta.setLore(lore);

        /* 49 */ it.setItemMeta(meta);
        /* 50 */ return it;
    }
    /*     */

    private static ItemStack rename() {
        /* 55 */ ItemStack it = new ItemStack(Material.NAME_TAG);
        /* 56 */ ItemMeta meta = it.getItemMeta();
        /* 57 */ meta.setDisplayName(Language.RENAME_ITEM_NAME.getMessage());

        /* 59 */ ArrayList<String> lore = new ArrayList<>(
                Arrays.asList(Language.RENAME_ITEM_DESCRIPTION.getMessage().split("\n")));
        /* 60 */ meta.setLore(lore);

        /* 62 */ it.setItemMeta(meta);
        /* 63 */ return it;
    }
    /*     */

    private static ItemStack backToPets() {
        /* 68 */ ArrayList<String> lore = new ArrayList<>(
                Arrays.asList(Language.BACK_TO_PETMENU_ITEM_DESCRIPTION.getMessage().split("\n")));

        /* 70 */ ItemStack it = Utils.createHead(Language.BACK_TO_PETMENU_ITEM_NAME.getMessage(), lore,
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI5M2E2MDcwNTAzMTcyMDcxZjM1ZjU4YzgyMjA0ZTgxOGNkMDY1MTg2OTAxY2ExOWY3ZGFkYmRhYzE2NWU0NCJ9fX0=");
        /* 71 */ ItemMeta meta = it.getItemMeta();
        /* 72 */ meta.setLocalizedName("AlmPet;BackToPetMenu");

        /* 74 */ it.setItemMeta(meta);

        /* 76 */ return it;
    }
    /*     */

    public static ItemStack page(int index) {
        /* 81 */ ItemStack it = new ItemStack(Material.PAPER);
        /* 82 */ ItemMeta meta = it.getItemMeta();
        /* 83 */ meta.setDisplayName(Language.TURNPAGE_ITEM_NAME.getMessage());

        /* 85 */ meta.setLocalizedName("AlmPetPage;" + index);

        /* 87 */ ArrayList<String> lore = new ArrayList<>(
                Arrays.asList(Language.TURNPAGE_ITEM_DESCRIPTION.getMessage().split("\n")));
        /* 88 */ meta.setLore(lore);

        /* 90 */ it.setItemMeta(meta);
        /* 91 */ return it;
    }
    /*     */

    public static ItemStack petInfo(Pet pet) {
        /* 96 */ Pet objectPet = Pet.getFromId(pet.getId());

        /* 98 */ ItemStack it = objectPet.getIcon().clone();
        /* 99 */ ItemMeta meta = it.getItemMeta();

        /* 101 */ ArrayList<String> lore = new ArrayList<>(meta.getLore());

        /* 103 */ if (pet.getCurrentName() != null) {

            /* 105 */ lore.add(" ");
            /* 106 */ lore.add(Language.NICKNAME
                    .getMessageFormatted(new FormatArg[] { new FormatArg("%nickname%", pet.getCurrentName()) }));
            /* 107 */ lore.add(" ");
        }

        /* 110 */ lore.addAll(Arrays.asList(Language.NICKNAME_ITEM_LORE.getMessage().split("\n")));

        /* 112 */ meta.setLore(lore);

        /* 114 */ it.setItemMeta(meta);
        /* 115 */ return it;
    }
    /*     */

    public static ItemStack deco(Material mat) {
        /* 120 */ ItemStack it = new ItemStack(mat);
        /* 121 */ ItemMeta meta = it.getItemMeta();
        /* 122 */ meta.setDisplayName("§0");

        /* 124 */ ArrayList<String> lore = new ArrayList<>();
        /* 125 */ meta.setLore(lore);

        /* 127 */ it.setItemMeta(meta);

        /* 129 */ return it;
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\Items.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
