package fr.nocsy.almpet;

import fr.nocsy.almpet.commands.CommandHandler;
import fr.nocsy.almpet.data.GlobalConfig;
import fr.nocsy.almpet.data.LanguageConfig;
import fr.nocsy.almpet.data.Pet;
import fr.nocsy.almpet.data.PetConfig;
import fr.nocsy.almpet.data.flags.FlagsManager;
import fr.nocsy.almpet.listeners.EventListener;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvancedPet extends JavaPlugin {
    public static AdvancedPet getInstance() {
        /* 16 */ return instance;
    }

    private static AdvancedPet instance;

    public static Logger getLog() {
        /* 18 */ return log;
        /* 19 */ }

    private static Logger log = Bukkit.getLogger();

    public static String getPrefix() {
        /* 21 */ return prefix;
        /* 22 */ }

    private static String prefix = "§8[§»";

    public static String getLogName() {
        /* 24 */ return logName;
        /* 25 */ }

    private static String logName = "[AdvancedPet] : ";

    public void onEnable() {
        /* 30 */ instance = this;
        /* 31 */ CommandHandler.init(this);
        /* 32 */ EventListener.init(this);

        /* 34 */ loadConfigs();
        /* 35 */ getLog().info("-=-=-=-= AdvancedPet loaded =-=-=-=-");
        /* 36 */ getLog().info("        Plugin made by Nocsy");
        /* 37 */ getLog().info("-=-=-=-= -=-=-=-=-=-=- =-=-=-=-");

        try {
            /* 41 */ FlagsManager.init(this);
            /* 42 */ } catch (IllegalPluginAccessException ex) {

            /* 44 */ getLog().warning(getLogName() + "Flag manager encountered an exception " + getLogName());
        }
    }

    public void onDisable() {
        /* 51 */ getLog().info("-=-=-=-= AdvancedPet disable =-=-=-=-");
        /* 52 */ getLog().info("            See you soon");
        /* 53 */ getLog().info("-=-=-=-= -=-=-=-=-=-=- =-=-=-=-");

        /* 55 */ Pet.clearPets();
        /* 56 */ FlagsManager.stopFlags();
    }

    public static void loadConfigs() {
        /* 61 */ GlobalConfig.getInstance().init();
        /* 62 */ LanguageConfig.getInstance().init();
        /* 63 */ PetConfig.loadPets(AbstractConfig.getPath() + "Pets/", true);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\AdvancedPet.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
