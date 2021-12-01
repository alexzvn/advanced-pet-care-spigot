package fr.nocsy.almpet.commands;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandHandler
        implements CommandExecutor {
    /* 12 */ public static ArrayList<CCommand> commands = new ArrayList<>();

    public static void init(JavaPlugin plugin) {
        /* 16 */ commands.add(new AdvancedPetCommand());
        /* 17 */ for (CCommand c : commands) {
            /* 18 */ plugin.getCommand(c.getName()).setExecutor(new CommandHandler());
        }
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /* 25 */ for (CCommand cmd : commands) {
            /* 26 */ if (cmd.getName().equalsIgnoreCase(command.getName())) {
                /* 27 */ cmd.execute(sender, command, label, args);
            }
        }
        /* 30 */ return true;
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\commands\CommandHandler.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
