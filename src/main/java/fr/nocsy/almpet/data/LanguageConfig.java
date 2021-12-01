package fr.nocsy.almpet.data;

import fr.nocsy.almpet.AdvancedPet;
import java.util.HashMap;

public class LanguageConfig
        extends AbstractConfig {
    public static LanguageConfig instance;
    /* 12 */ private HashMap<String, String> map = new HashMap<>();

    public HashMap<String, String> getMap() {
        return this.map;
    }

    public static LanguageConfig getInstance() {
        /* 18 */ if (instance == null) {
            /* 19 */ instance = new LanguageConfig();
        }
        /* 21 */ return instance;
    }

    public void init() {
        /* 26 */ init("", "language.yml");

        /* 28 */ for (Language lang : Language.values()) {

            /* 30 */ if (getConfig().get(lang.name().toLowerCase()) == null) {
                /* 31 */ getConfig().set(lang.name().toLowerCase(), lang.getMessage());
            }
        }
        /* 34 */ save();
        /* 35 */ reload();
    }

    public void save() {
        /* 40 */ super.save();
    }

    public void reload() {
        /* 46 */ loadConfig();

        /* 48 */ this.map.clear();

        /* 50 */ for (Language lang : Language.values()) {

            /* 52 */ if (getConfig().get(lang.name().toLowerCase()) != null) {
                /* 53 */ this.map.put(lang.name().toLowerCase(), getConfig().getString(lang.name().toLowerCase()));
            }
            /* 55 */ lang.reload();
        }

        /* 58 */ AdvancedPet.getLog().info(AdvancedPet.getLogName() + "Language file reloaded.");
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\LanguageConfig.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
