package fr.nocsy.almpet.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {
    public static ItemStack createHead(String name, List<String> lore, String base64) {
        /* 20 */ ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        /* 21 */ item.setDurability((short) 3);
        /* 22 */ SkullMeta headMeta = (SkullMeta) item.getItemMeta();

        /* 24 */ headMeta.setDisplayName(name);
        /* 25 */ headMeta.setLore(lore);

        /* 27 */ item.setItemMeta((ItemMeta) headMeta);
        /* 28 */ GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        /* 29 */ profile.getProperties().put("textures", new Property("textures", base64));
        /* 30 */ Field profileField = null;

        /* 33 */ try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            /* 34 */ profileField.setAccessible(true);
            /* 35 */ profileField.set(headMeta, profile);
        }

        /* 37 */ catch (NoSuchFieldException noSuchFieldException) {
        } catch (IllegalArgumentException illegalArgumentException) {
        } catch (IllegalAccessException illegalAccessException) {
        }
        /* 38 */ item.setItemMeta((ItemMeta) headMeta);
        /* 39 */ return item;
    }

    public static double distance(Location loc1, Location loc2) {
        /* 44 */ double x1 = loc1.getX();
        /* 45 */ double y1 = loc1.getY();
        /* 46 */ double z1 = loc1.getZ();

        /* 48 */ double x2 = loc2.getX();
        /* 49 */ double y2 = loc2.getY();
        /* 50 */ double z2 = loc2.getZ();

        /* 52 */ double square = Math.pow(x1 - x2, 2.0D) + Math.pow(y1 - y2, 2.0D) + Math.pow(z1 - z2, 2.0D);

        /* 54 */ return Math.sqrt(square);
    }

    public static Location bruised(Location loc, double distance) {
        /* 65 */ Location origin = loc.clone();

        /* 67 */ Random random = new Random();
        /* 68 */ double r = Math.min(1.0D, distance)
                + (Math.max(distance - 0.1D, 1.0D) - Math.min(1.0D, distance)) * random.nextDouble();
        /* 69 */ double theta = 6.283185307179586D * random.nextDouble();

        /* 71 */ double x = r * Math.cos(theta) + loc.getX();
        /* 72 */ double z = r * Math.sin(theta) + loc.getZ();
        /* 73 */ double y = loc.getY();

        /* 75 */ loc = new Location(loc.getWorld(), x, y, z);

        /* 77 */ int threshHoldY = 5;
        /* 78 */ int maxY = 0;
        /* 79 */ while (!loc.getBlock().isPassable() && maxY < threshHoldY) {

            /* 81 */ loc.add(0.0D, 1.0D, 0.0D);
            /* 82 */ maxY++;
        }
        /* 84 */ if (maxY == threshHoldY) {
            /* 86 */ return origin;
        }
        /* 88 */ return loc;
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpe\\utils\Utils.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
