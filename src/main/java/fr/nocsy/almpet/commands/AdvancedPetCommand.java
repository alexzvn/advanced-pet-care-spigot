/*     */
package fr.nocsy.almpet.commands;

import fr.nocsy.almpet.AdvancedPet;
import fr.nocsy.almpet.data.FormatArg;
import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import fr.nocsy.almpet.data.inventories.PetMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*     */
public class AdvancedPetCommand implements CCommand {
    public String getName() {
        /* 14 */ return "advancedpet";
    }
    /*     */

    public String getPermission() {
        /* 19 */ return "advancedpet.use";
    }

    public String getAdminPermission() {
        /* 23 */ return "advancedpet.admin";
    }
    /*     */

    public void execute(CommandSender sender, Command command, String label, String[] args) {
        /* 28 */ if (label.equalsIgnoreCase(getName()) && sender.hasPermission(getPermission())) {

            /* 30 */ if (args.length == 4) {

                /* 32 */ if (args[0].equalsIgnoreCase("spawn") && sender
                        /* 33 */ .hasPermission(getAdminPermission())) {
                    /*     */

                    /* 36 */ String petId = args[1];
                    /* 37 */ String playerName = args[2];
                    /* 38 */ String booleanValue = args[3];

                    /* 40 */ Player target = Bukkit.getPlayer(playerName);
                    /* 41 */ if (target == null) {

                        /* 43 */ Language.PLAYER_NOT_CONNECTED.sendMessageFormated(sender,
                                new FormatArg[] { new FormatArg("%player%", playerName) });

                        return;
                    }
                    /* 47 */ Pet petObject = Pet.getFromId(petId);
                    /* 48 */ if (petObject == null) {

                        /* 50 */ Language.PET_DOESNT_EXIST.sendMessage(sender);
                        return;
                    }
                    /* 53 */ Pet pet = petObject.copy();

                    /* 55 */ boolean checkPermission = booleanValue.equalsIgnoreCase("true");
                    /* 56 */ if (checkPermission && !target.hasPermission(pet.getPermission())) {

                        /* 58 */ Language.NOT_ALLOWED.sendMessage(target);
                        return;
                    }
                    /* 61 */ pet.setCheckPermission(checkPermission);
                    /* 62 */ pet.spawnWithMessage(target, target.getLocation());

                    return;
                }
                /* 66 */ } else if (args.length == 2) {

                /* 68 */ if (args[0].equalsIgnoreCase("open") && sender
                        /* 69 */ .hasPermission(getAdminPermission()) && sender instanceof Player) {
                    /*     */

                    /* 72 */ String playerName = args[1];
                    /* 73 */ Player playerToOpen = Bukkit.getPlayer(playerName);
                    /* 74 */ if (playerToOpen == null) {

                        /* 76 */ Language.PLAYER_NOT_CONNECTED.sendMessageFormated(sender,
                                new FormatArg[] { new FormatArg("%player%", playerName) });

                        return;
                    }
                    /* 80 */ PetMenu menu = new PetMenu(playerToOpen, 0, false);
                    /* 81 */ menu.open((Player) sender);

                    return;
                }
                /* 85 */ } else if (args.length == 1) {

                /* 87 */ if (args[0].equalsIgnoreCase("reload") && sender
                        /* 88 */ .hasPermission(getAdminPermission())) {

                    /* 90 */ AdvancedPet.loadConfigs();
                    /* 91 */ Language.RELOAD_SUCCESS.sendMessage(sender);
                    /* 92 */ Language.HOW_MANY_PETS_LOADED.sendMessageFormated(sender, new FormatArg[] {
                            new FormatArg("%numberofpets%", Integer.toString(Pet.getObjectPets().size())) });

                    return;
                }
                /* 96 */ } else if (args.length == 0 && sender instanceof Player) {
                /*     */

                /* 99 */ PetMenu menu = new PetMenu((Player) sender, 0, false);
                /* 100 */ menu.open((Player) sender);

                return;
            }
            /* 104 */ if (sender.hasPermission(getAdminPermission())) {
                /* 105 */ Language.USAGE.sendMessage(sender);
            }
        } else {

            /* 109 */ Language.NO_PERM.sendMessage(sender);
        }
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\commands\AdvancedPetCommand.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
