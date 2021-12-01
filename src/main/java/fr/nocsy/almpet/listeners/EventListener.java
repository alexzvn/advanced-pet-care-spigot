package fr.nocsy.almpet.listeners;

import java.util.ArrayList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener
        implements Listener {
    /* 10 */ private static ArrayList<Listener> listeners = new ArrayList<>();

    public static void init(JavaPlugin plugin) {
        /* 14 */ listeners.add(new PetMenuListener());
        /* 15 */ listeners.add(new PetInteractionMenuListener());
        /* 16 */ listeners.add(new PetListener());

        /* 18 */ for (Listener l : listeners)
            /* 19 */ plugin.getServer().getPluginManager().registerEvents(l, (Plugin) plugin);
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\listeners\EventListener.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
