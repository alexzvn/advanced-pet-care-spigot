package fr.nocsy.almpet.data;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Language {
    /* 9 */ INVENTORY_PETS_MENU("§0☀ §4Pets §0☀"),
    /* 10 */ INVENTORY_PETS_MENU_INTERACTIONS("§0☀ §4Pet §0☀"),

    /* 12 */ MOUNT_ITEM_NAME("§6Mount"),
    /* 13 */ MOUNT_ITEM_DESCRIPTION("§7Click to mount your pet"),

    /* 15 */ RENAME_ITEM_NAME("§6Rename"),
    /* 16 */ RENAME_ITEM_DESCRIPTION("§7Click to rename your pet"),

    /* 18 */ BACK_TO_PETMENU_ITEM_NAME("§cBack to menu"),
    /* 19 */ BACK_TO_PETMENU_ITEM_DESCRIPTION("§7Click to get back to the menu"),

    /* 21 */ TURNPAGE_ITEM_NAME("§6Next page"),
    /* 22 */ TURNPAGE_ITEM_DESCRIPTION("§eRight click§7 to go forward \n§aLeft click§7 to go backward"),

    /* 24 */ NICKNAME("§9Nickname : §7%nickname%"),
    /* 25 */ NICKNAME_ITEM_LORE("§cClick here to revoke your pet"),

    /* 27 */ SUMMONED("§7A pet has been summoned !"),
    /* 28 */ REVOKED("§7Your pet was revoked."),
    /* 29 */ REVOKED_FOR_NEW_ONE("§7Your previous pet was revoked to summon the new one."),
    /* 30 */ MYTHICMOB_NULL("§cThis pet could not be summoned. The associated mythicMob is null."),
    /* 31 */ NO_MOB_MATCH("§cThis pet could not be summoned. The associated mythicmob isn't registered in MythicMobs."),
    /* 32 */ NOT_ALLOWED("§cYou're not allowed to summon this pet."),
    /* 33 */ OWNER_NOT_FOUND("§cThis pet could not be summoned. The summoner couldn't be found."),
    /* 34 */ REVOKED_BEFORE_CHANGES("§cYour pet was revoked before the modifications could take place."),
    /* 35 */ NOT_MOUNTABLE("§cThis pet has no mounting point."),
    /* 36 */ NOT_MOUNTABLE_HERE("§cYou can't ride a pet in this area."),
    /* 37 */ CANT_FOLLOW_HERE("§cYour pet can't follow you in this area."),
    /* 38 */ TYPE_NAME_IN_CHAT("§aRight down in the chat the name of your pet."),
    /* 39 */ IF_WISH_TO_REMOVE_NAME("§aSIf you wish to remove it, right §c%tag%§a in the chat."),
    /* 40 */ NICKNAME_CHANGED_SUCCESSFULY("§aNickname successfully changed !"),
    /* 41 */ TAG_TO_REMOVE_NAME("None"),
    /* 42 */ ALREADY_INSIDE_VEHICULE(
            "§7You're already mounting something. Please dismount your current mount to use this feature."),
    /* 43 */ PET_DOESNT_EXIST("§cThis pet doesn't exist. Please check the id."),
    /* 44 */ PLAYER_NOT_CONNECTED("§cThe player §6%player%§c isn't connected."),

    /* 46 */ RELOAD_SUCCESS("§aReloaded successfully."),
    /* 47 */ HOW_MANY_PETS_LOADED("§a%numberofpets% were registered successfully"),

    /* 49 */ USAGE(
            "§7Usage : §6/almpet §8...\n§8   ... §areload \n§8   ... §7(nothing here to open the GUI) \n§8   ... §aopen §8<§7player§8>\n§8   ... §aspawn §8<§7id§8> <§7player§8> §atrue§8/§cfalse §7(check if the player have the permission to spawn the pet or not)"),

    /* 54 */ NO_PERM("§cYou're not allowed to use this command.");

    public String getMessage() {
        /* 56 */ return this.message;
    }

    private String message;

    Language(String message) {
        /* 61 */ this.message = message;
    }

    public void reload() {
        /* 66 */ if (LanguageConfig.getInstance().getMap().containsKey(name().toLowerCase())) {
            /* 68 */ this.message = LanguageConfig.getInstance().getMap().get(name().toLowerCase());
        }
    }

    public void sendMessage(Player p) {
        /* 74 */ p.sendMessage(GlobalConfig.getInstance().getPrefix() + " " + GlobalConfig.getInstance().getPrefix());
    }

    public void sendMessage(CommandSender sender) {
        /* 78 */ sender
                .sendMessage(GlobalConfig.getInstance().getPrefix() + " " + GlobalConfig.getInstance().getPrefix());
    }

    public void sendMessageFormated(CommandSender sender, FormatArg... args) {
        /* 83 */ String toSend = new String(this.message);
        /* 84 */ for (FormatArg arg : args) {
            /* 86 */ toSend = arg.applyToString(toSend);
        }
        /* 88 */ sender
                .sendMessage(GlobalConfig.getInstance().getPrefix() + " " + GlobalConfig.getInstance().getPrefix());
    }

    public String getMessageFormatted(FormatArg... args) {
        /* 93 */ String toSend = new String(this.message);
        /* 94 */ for (FormatArg arg : args) {
            /* 96 */ toSend = arg.applyToString(toSend);
        }
        /* 98 */ return toSend;
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\Language.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
