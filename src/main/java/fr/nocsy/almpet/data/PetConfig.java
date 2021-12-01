/*     */
package fr.nocsy.almpet.data;

/*     */
import fr.nocsy.almpet.AdvancedPet;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.skills.Skill;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/*     */
public class PetConfig
        extends AbstractConfig {
    /* 16 */ private Pet pet = null;

    public Pet getPet() {
        return this.pet;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public PetConfig(String folderName, String fileName) {
        /* 26 */ init(folderName, fileName);
        /* 27 */ reload();
    }
    /*     */
    /*     */
    /*     */
    /*     */

    public void save() {
        /* 35 */ super.save();
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public void reload() {
        /* 44 */ loadConfig();
        /*     */

        /* 47 */ String id = getConfig().getString("Id");
        /* 48 */ String mobType = getConfig().getString("MythicMob");
        /* 49 */ String permission = getConfig().getString("Permission");
        /* 50 */ int distance = getConfig().getInt("Distance");
        /* 51 */ final String despawnSkillName = getConfig().getString("DespawnSkill");
        /* 52 */ String iconName = getConfig().getString("Icon.Name");
        /* 53 */ String textureBase64 = getConfig().getString("Icon.TextureBase64");
        /* 54 */ boolean autoRide = getConfig().getBoolean("AutoRide");
        /* 55 */ List<String> description = getConfig().getStringList("Icon.Description");

        /* 57 */ if (id == null || mobType == null || permission == null || iconName == null || textureBase64 == null
                || description == null) {
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */
            /*     */

            /* 65 */ AdvancedPet.getLog().warning(AdvancedPet.getLogName()
                    + "This pet could not be registered. Please check the configuration file to make sure you didn't miss anything.");
            /* 66 */ AdvancedPet.getLog().warning(AdvancedPet.getLogName() + "Information about the registered pet : ");
            /* 67 */ AdvancedPet.getLog().warning("id : " + id);
            /* 68 */ AdvancedPet.getLog().warning("mobType : " + mobType);
            /* 69 */ AdvancedPet.getLog().warning("permission : " + permission);

            return;
        }
        /* 73 */ final Pet pet = new Pet(id);
        /* 74 */ pet.setMythicMobName(mobType);
        /* 75 */ pet.setPermission(permission);
        /* 76 */ if (getConfig().get("Mountable") == null) {
            /* 77 */ pet.setMountable(GlobalConfig.getInstance().isMountable());
        } else {
            /* 79 */ pet.setMountable(getConfig().getBoolean("Mountable"));
        }
        /* 81 */ pet.setAutoRide(autoRide);
        /* 82 */ pet.setDistance(distance);

        /* 84 */ if (despawnSkillName != null) {
            /* 86 */ (new BukkitRunnable() {
                public void run() {
                    /* 89 */ Optional<Skill> optionalSkill = MythicMobs.inst().getSkillManager()
                            .getSkill(despawnSkillName);
                    /* 90 */ Objects.requireNonNull(pet);
                    optionalSkill.ifPresent(pet::setDespawnSkill);
                    /* 91 */ if (pet.getDespawnSkill() == null) {
                        /* 93 */ AdvancedPet.getLog()
                                .warning(AdvancedPet.getLogName() + "Impossible to link the despawn skill \""
                                        + AdvancedPet.getLogName() + "\" to the pet \"" + despawnSkillName
                                        + "\", because this skill doesn't exist.");
                    }
                }
                /* 96 */ }).runTaskLater((Plugin) AdvancedPet.getInstance(), 5L);
        }

        /* 99 */ pet.buildIcon(iconName, description, textureBase64);

        /* 101 */ this.pet = pet;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static void loadPets(String folderPath, boolean clearPets) {
        /* 111 */ if (clearPets) {
            /* 113 */ Pet.getObjectPets().clear();
        }

        /* 116 */ File folder = new File(folderPath);
        /* 117 */ if (!folder.exists()) {
            /* 118 */ folder.mkdirs();
        }
        /* 120 */ for (File file : folder.listFiles()) {

            /* 122 */ if (file.isDirectory()) {

                /* 124 */ loadPets(file.getPath().replace("\\", "/"), false);
            } else {

                /* 128 */ PetConfig petConfig = new PetConfig(
                        folder.getPath().replace("\\", "/").replace(AbstractConfig.getPath(), ""), file.getName());

                /* 130 */ if (petConfig.getPet() != null) {
                    /* 131 */ Pet.getObjectPets().add(petConfig.getPet());
                }
            }
        }
        /* 135 */ if (clearPets)
            /* 136 */ AdvancedPet.getLog()
                    .info(AdvancedPet.getLogName() + AdvancedPet.getLogName() + " pets registered successfully !");
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\PetConfig.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
