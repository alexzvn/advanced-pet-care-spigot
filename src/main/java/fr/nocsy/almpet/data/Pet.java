/*     */
package fr.nocsy.almpet.data;

import com.ticxo.modelengine.api.ModelEngineAPI;
import com.ticxo.modelengine.api.model.ModeledEntity;
import com.ticxo.modelengine.api.mount.controller.MountController;
import com.ticxo.modelengine.api.mount.handler.IMountHandler;
import fr.nocsy.almpet.AdvancedPet;
import fr.nocsy.almpet.data.inventories.PlayerData;
import fr.nocsy.almpet.utils.Utils;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.Skill;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

/*     */
public class Pet {
    public static final int MOB_SPAWN = 0;
    public static final int DESPAWNED_PREVIOUS = 1;
    public static final int OWNER_NULL = -1;
    public static final int MYTHIC_MOB_NULL = -2;
    public static final int NO_MOB_MATCH = -3;
    public static final int NOT_ALLOWED = -4;

    public static HashMap<UUID, Pet> getActivePets() {
        /* 42 */ return activePets;
        /* 43 */ }

    private static HashMap<UUID, Pet> activePets = new HashMap<>();

    public static ArrayList<Pet> getObjectPets() {
        /* 44 */ return objectPets;
        /* 45 */ }

    private static ArrayList<Pet> objectPets = new ArrayList<>();
    private Pet instance;
    private String id;
    private String mythicMobName;
    private String permission;
    private boolean mountable;
    private int distance;
    private ItemStack icon;
    private String currentName;

    public Pet getInstance() {
        /* 49 */ return this.instance;
    }

    private Skill despawnSkill;
    private boolean autoRide;
    private UUID owner;
    private ActiveMob activeMob;
    private boolean invulnerable;
    private boolean removed;
    private boolean checkPermission;
    private boolean firstSpawn;
    private int task;

    public String getId() {
        /* 52 */ return this.id;
    }

    /* 55 */ public void setMythicMobName(String mythicMobName) {
        this.mythicMobName = mythicMobName;
    }

    public String getMythicMobName() {
        /* 56 */ return this.mythicMobName;
    }

    /* 59 */ public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        /* 60 */ return this.permission;
    }

    /* 63 */ public void setMountable(boolean mountable) {
        this.mountable = mountable;
    }

    public boolean isMountable() {
        /* 64 */ return this.mountable;
    }

    /* 67 */ public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        /* 68 */ this.distance = distance;
    }

    /* 71 */ public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public ItemStack getIcon() {
        /* 72 */ return this.icon;
    }

    /* 75 */ public String getCurrentName() {
        return this.currentName;
    }

    public void setCurrentName(String currentName) {
        /* 76 */ this.currentName = currentName;
    }

    /* 79 */ public Skill getDespawnSkill() {
        return this.despawnSkill;
    }

    public void setDespawnSkill(Skill despawnSkill) {
        /* 80 */ this.despawnSkill = despawnSkill;
    }

    /* 83 */ public boolean isAutoRide() {
        return this.autoRide;
    }

    public void setAutoRide(boolean autoRide) {
        /* 84 */ this.autoRide = autoRide;
    }

    public void setOwner(UUID owner) {
        /* 89 */ this.owner = owner;
    }

    public UUID getOwner() {
        /* 90 */ return this.owner;
    }

    /* 93 */ public void setActiveMob(ActiveMob activeMob) {
        this.activeMob = activeMob;
    }

    public ActiveMob getActiveMob() {
        /* 94 */ return this.activeMob;
    }

    public boolean isInvulnerable() {
        /* 97 */ return this.invulnerable;
    }

    /* 100 */ public boolean isRemoved() {
        return this.removed;
    }

    public void setRemoved(boolean removed) {
        /* 101 */ this.removed = removed;
    }

    /* 104 */ public boolean isCheckPermission() {
        return this.checkPermission;
    }

    public void setCheckPermission(boolean checkPermission) {
        /* 105 */ this.checkPermission = checkPermission;
    }

    /* 108 */ public boolean isFirstSpawn() {
        return this.firstSpawn;
    }

    public void setFirstSpawn(boolean firstSpawn) {
        /* 109 */ this.firstSpawn = firstSpawn;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    protected Pet(String id) {
        /* 118 */ this.id = id;
        /* 119 */ this.instance = this;
        /* 120 */ this.checkPermission = true;
        /* 121 */ this.firstSpawn = true;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public int spawn(Location loc) {
        /* 132 */ if (this.checkPermission && this.owner != null &&
        /* 133 */ Bukkit.getPlayer(this.owner) != null &&
        /* 134 */ !Bukkit.getPlayer(this.owner).hasPermission(this.permission)) {
            /* 135 */ return -4;
        }
        /* 137 */ if (this.mythicMobName == null)
            /* 138 */ return -2;
        /* 139 */ if (this.owner == null)
            /* 140 */ return -1;
        /* 141 */ if (getActivePets().containsKey(this.owner)) {
            /* 143 */ ((Pet) getActivePets().get(this.owner)).despawn();
        }
        /*     */

        try {
            /* 148 */ Entity ent = null;
            /* 149 */ if (this.autoRide) {

                /* 151 */ ent = MythicMobs.inst().getAPIHelper().spawnMythicMob(this.mythicMobName, loc);
            } else {

                /* 155 */ ent = MythicMobs.inst().getAPIHelper().spawnMythicMob(this.mythicMobName,
                        Utils.bruised(loc, getDistance()));
            }
            /* 157 */ if (ent == null) {
                /* 159 */ return -2;
            }
            /* 161 */ Optional<ActiveMob> maybeHere = MythicMobs.inst().getMobManager().getActiveMob(ent.getUniqueId());
            /* 162 */ maybeHere.ifPresent(mob -> this.activeMob = mob);
            /* 163 */ if (this.activeMob == null) {
                /* 165 */ return -2;
            }
            /* 167 */ ent.setMetadata("AlmPet",
                    (MetadataValue) new FixedMetadataValue((Plugin) AdvancedPet.getInstance(), this));
            /* 168 */ if (ent.isInvulnerable() && GlobalConfig.getInstance().isLeftClickToOpen()) {

                /* 170 */ this.invulnerable = true;
                /* 171 */ ent.setInvulnerable(false);
            }
            /* 173 */ this.activeMob.setOwner(this.owner);
            /* 174 */ ia();

            /* 176 */ boolean returnDespawned = false;

            /* 178 */ if (activePets.containsKey(this.owner)) {

                /* 180 */ Pet previous = activePets.get(this.owner);
                /* 181 */ previous.despawn();

                /* 183 */ activePets.remove(this.owner, this);

                /* 185 */ returnDespawned = true;
            }

            /* 188 */ activePets.put(this.owner, this);

            /* 190 */ PlayerData pd = PlayerData.get(this.owner);
            /* 191 */ String name = (String) pd.getMapOfRegisteredNames().get(this.id);
            /* 192 */ if (name != null) {

                /* 194 */ setDisplayName(name, false);
            } else {

                /* 198 */ setDisplayName(Language.TAG_TO_REMOVE_NAME.getMessage(), false);
            }

            /* 201 */ if (this.firstSpawn) {

                /* 203 */ this.firstSpawn = false;
                /* 204 */ (new BukkitRunnable() {
                    public void run() {
                        /* 207 */ Player p = Bukkit.getPlayer(Pet.this.owner);
                        /* 208 */ if (p != null && Pet.this.autoRide) {

                            /* 210 */ boolean mounted = Pet.this.setMount((Entity) p);
                            /* 211 */ if (!mounted)
                                /* 212 */ Language.NOT_MOUNTABLE.sendMessage(p);
                        }
                    }
                    /* 215 */ }).runTaskLater((Plugin) AdvancedPet.getInstance(), 5L);
            }

            /* 218 */ if (returnDespawned)
                /* 219 */ return 1;
            /* 220 */ return 0;
        }
        /* 222 */ catch (InvalidMobTypeException e) {
            /* 223 */ return -3;
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public void spawnWithMessage(Player p, Location loc) {
        /* 235 */ int executed = spawn(p, p.getLocation());

        /* 237 */ switch (executed) {
            case 1:
                /* 238 */ Language.REVOKED_FOR_NEW_ONE.sendMessage(p);
                break;
            /* 239 */ case 0:
                Language.SUMMONED.sendMessage(p);
                break;
            /* 240 */ case -2:
                Language.MYTHICMOB_NULL.sendMessage(p);
                break;
            /* 241 */ case -3:
                Language.NO_MOB_MATCH.sendMessage(p);
                break;
            /* 242 */ case -4:
                Language.NOT_ALLOWED.sendMessage(p);
                break;
            /* 243 */ case -1:
                Language.OWNER_NOT_FOUND.sendMessage(p);
                break;
        }

    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

 public void ia() {
   this.task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)AdvancedPet.getInstance(), new Runnable()
       {
         
         public void run()
         {
           Player p = Bukkit.getPlayer(Pet.this.owner);
           
           if (!Pet.this.getInstance().isStillHere()) {
             
             Bukkit.getScheduler().cancelTask(Pet.this.task);
             
             return;
           } 
           if (p != null) {
             
             Location ownerLoc = p.getLocation();
             Location petLoc = Pet.this.getInstance().getActiveMob().getEntity().getBukkitEntity().getLocation();
             
             if (!ownerLoc.getWorld().getName().equals(petLoc.getWorld().getName())) {
               
               Pet.this.getInstance().despawn();
               Pet.this.getInstance().spawn(p, p.getLocation());
               
               return;
             } 
             double distance = Utils.distance(ownerLoc, petLoc);
             
             if (distance < (Pet.this.getInstance()).distance)
             {
               MythicMobs.inst().getVolatileCodeHandler().getAIHandler().navigateToLocation(Pet.this.getInstance().getActiveMob().getEntity(), Pet.this.getInstance().getActiveMob().getEntity().getLocation(), Double.POSITIVE_INFINITY);
             }
             else if (distance > Pet.this.getInstance().getDistance() && distance < 
               GlobalConfig.getInstance().getDistanceTeleport())
             {


               
               AbstractLocation aloc = new AbstractLocation(Pet.this.getInstance().getActiveMob().getEntity().getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
               MythicMobs.inst().getVolatileCodeHandler().getAIHandler().navigateToLocation(Pet.this.getInstance().getActiveMob().getEntity(), aloc, Double.POSITIVE_INFINITY);
             }
             else if (distance > GlobalConfig.getInstance().getDistanceTeleport() && !p.isFlying())
             {
               Pet.this.getInstance().teleportToPlayer(p);
             }
           
           } else {
             
             Pet.this.getInstance().despawn();
             Bukkit.getScheduler().cancelTask(Pet.this.task);
           } 
         }
       }0L, 10L);
 }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public int spawn(@NotNull Player owner, Location loc) {
        /* 317 */ if (owner == null)
            $$$reportNull$$$0(0);
        this.owner = owner.getUniqueId();
        /* 318 */ return spawn(loc);
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public boolean despawn() {
        /* 327 */ this.removed = true;
        /* 328 */ if (this.activeMob != null) {
            /*     */

            /* 331 */ if (this.despawnSkill != null) {

                /* 333 */ this.despawnSkill.execute(new SkillMetadata(SkillTrigger.CUSTOM, (SkillCaster) this.activeMob,
                        this.activeMob.getEntity()));
            } else {

                /* 337 */ if (this.activeMob.getEntity() != null)
                    /* 338 */ this.activeMob.getEntity().remove();
                /* 339 */ if (this.activeMob.getEntity() != null
                        && this.activeMob.getEntity().getBukkitEntity() != null) {
                    /* 340 */ this.activeMob.getEntity().getBukkitEntity().remove();
                }
            }
            /* 343 */ Player ownerPlayer = Bukkit.getPlayer(this.owner);
            /* 344 */ if (ownerPlayer != null) {
                /* 346 */ dismount((Entity) ownerPlayer);
            }

            /* 349 */ activePets.remove(this.owner);
            /* 350 */ return true;
        }
        /* 352 */ activePets.remove(this.owner);
        /* 353 */ return false;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public void teleport(Location loc) {
        /* 362 */ if (isStillHere()) {

            /* 364 */ despawn();
            /* 365 */ spawn(loc);
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */

    public void teleportToPlayer(Player p) {
        /* 374 */ Location loc = Utils.bruised(p.getLocation(), getDistance());

        /* 376 */ if (isStillHere()) {
            /* 377 */ teleport(loc);
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */

    public boolean isStillHere() {
        /* 386 */ return (this.activeMob != null && this.activeMob.getEntity() != null
                && this.activeMob.getEntity().getBukkitEntity() != null && getActivePets().containsValue(this));
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public void setDisplayName(final String name, boolean save) {
        try {
            /* 396 */ if (name != null && name.length() > GlobalConfig.instance.getMaxNameLenght()) {
                /* 397 */ setDisplayName(name.substring(0, GlobalConfig.instance.getMaxNameLenght()), save);

                return;
            }
            /* 401 */ this.currentName = name;

            /* 403 */ if (isStillHere()) {
                /* 405 */ if (name == null || name.equalsIgnoreCase(Language.TAG_TO_REMOVE_NAME.getMessage())) {
                    /* 406 */ this.activeMob.getEntity().getBukkitEntity().setCustomName(GlobalConfig.getInstance()
                            .getDefaultName().replace("%player%", Bukkit.getOfflinePlayer(this.owner).getName()));

                    /* 408 */ (new BukkitRunnable() {
                        public void run() {
                            /* 412 */ Pet.this.setNameTag(name, false);
                        }
                        /* 414 */ }).runTaskLater((Plugin) AdvancedPet.getInstance(), 20L);

                    /* 416 */ if (save) {

                        /* 418 */ PlayerData pd = PlayerData.get(this.owner);
                        /* 419 */ pd.getMapOfRegisteredNames().remove(getId());
                        /* 420 */ pd.save();
                    }

                    return;
                }

                /* 426 */ this.activeMob.getEntity().getBukkitEntity().setCustomName(name);

                /* 428 */ (new BukkitRunnable() {
                    public void run() {
                        /* 432 */ Pet.this.setNameTag(name, true);
                    }
                    /* 434 */ }).runTaskLater((Plugin) AdvancedPet.getInstance(), 20L);

                /* 436 */ if (save) {
                    /* 438 */ PlayerData pd = PlayerData.get(this.owner);
                    /* 439 */ pd.getMapOfRegisteredNames().put(getId(), name);
                    /* 440 */ pd.save();
                }

            }

            /* 445 */ } catch (Exception ex) {

            /* 447 */ AdvancedPet.getLog()
                    .warning("[AlmPet] : Une exception " + ex.getClass().getSimpleName()
                            + " a été soulevé par setDisplayName(" + Language.TAG_TO_REMOVE_NAME.getMessage()
                            + "), concernant le pet " + this.id);
            /* 448 */ ex.printStackTrace();
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public Pet copy() {
        /* 458 */ Pet pet = new Pet(this.id);
        /* 459 */ pet.setMythicMobName(this.mythicMobName);
        /* 460 */ pet.setPermission(this.permission);
        /* 461 */ pet.setDistance(this.distance);
        /* 462 */ pet.setDespawnSkill(this.despawnSkill);
        /* 463 */ pet.setMountable(this.mountable);
        /* 464 */ pet.setAutoRide(this.autoRide);
        /* 465 */ pet.setIcon(this.icon);
        /* 466 */ pet.setOwner(this.owner);
        /* 467 */ pet.setActiveMob(this.activeMob);
        /* 468 */ return pet;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public boolean setMount(Entity ent) {
        /* 477 */ if (isStillHere()) {

            /* 479 */ UUID petUUID = this.activeMob.getEntity().getUniqueId();
            /* 480 */ ModeledEntity localModeledEntity = ModelEngineAPI.api.getModelManager().getModeledEntity(petUUID);
            /* 481 */ if (localModeledEntity == null) {
                /* 482 */ return false;
            }
            /* 484 */ IMountHandler localIMountHandler = localModeledEntity.getMountHandler();

            /* 486 */ MountController localMountController = ModelEngineAPI.api.getControllerManager()
                    .createController("flying");
            /* 487 */ if (localMountController == null) {
                /* 488 */ localMountController = ModelEngineAPI.api.getControllerManager().createController("walking");
            }
            /* 490 */ localIMountHandler.setDriver(ent, localMountController);
            /* 491 */ localIMountHandler.setCanDamageMount(ent, false);
            /* 492 */ return true;
        }
        /* 494 */ return false;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public boolean hasMount(Entity ent) {
        /* 503 */ if (isStillHere()) {

            /* 505 */ UUID petUUID = this.activeMob.getEntity().getUniqueId();
            /* 506 */ ModeledEntity localModeledEntity = ModelEngineAPI.api.getModelManager().getModeledEntity(petUUID);
            /* 507 */ if (localModeledEntity == null) {
                /* 508 */ return false;
            }
            /* 510 */ IMountHandler localIMountHandler = localModeledEntity.getMountHandler();

            /* 512 */ return (localIMountHandler.hasDriver() || localIMountHandler.hasPassengers());
        }
        /* 514 */ return false;
    }
    /*     */
    /*     */
    /*     */
    /*     */

    public void dismount(Entity ent) {
        /* 522 */ if (isStillHere()) {

            /* 524 */ UUID localUUID = this.activeMob.getEntity().getUniqueId();
            /* 525 */ ModeledEntity localModeledEntity = ModelEngineAPI.api.getModelManager()
                    .getModeledEntity(localUUID);
            /* 526 */ if (localModeledEntity == null) {
                return;
            }
            /* 529 */ IMountHandler localIMountHandler = localModeledEntity.getMountHandler();
            /* 530 */ localIMountHandler.removePassenger(ent);
            /* 531 */ localIMountHandler.setDriver(null);
        }
    }
    /*     */
    /*     */

    public void setNameTag(String name, boolean visible) {
        /* 538 */ if (isStillHere()) {

            /* 540 */ ModeledEntity localModeledEntity = ModelEngineAPI.api.getModelManager()
                    .getModeledEntity(this.activeMob.getEntity().getUniqueId());
            /* 541 */ if (localModeledEntity == null) {
                return;
            }
            /* 544 */ this.activeMob.getEntity().getBukkitEntity().setCustomNameVisible(visible);
            /* 545 */ localModeledEntity.setNametagVisible(visible);
            /* 546 */ localModeledEntity.setNametag(name);
            /* 547 */ localModeledEntity.setInvisible(true);
        }
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    protected void buildIcon(String iconName, List<String> description, String textureBase64) {
        /* 559 */ this.icon = Utils.createHead(iconName, description, textureBase64);
        /* 560 */ ItemMeta meta = this.icon.getItemMeta();
        /* 561 */ meta.setLocalizedName(toString());
        /* 562 */ this.icon.setItemMeta(meta);
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public String toString() {
        /* 571 */ return "AlmPet;" + this.id;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public boolean equals(Pet other) {
        /* 581 */ return this.id.equals(other.getId());
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static Pet fromString(String seria) {
        /* 591 */ if (seria.startsWith("AlmPet;")) {

            /* 593 */ String id = seria.split(";")[1];
            /* 594 */ return getFromId(id);
        }
        /* 596 */ return null;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static Pet getFromId(String id) {
        /* 606 */ for (Pet pet : objectPets) {

            /* 608 */ if (pet.getId().equals(id)) {
                /* 610 */ return pet;
            }
        }
        /* 613 */ return null;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static Pet getFromIcon(ItemStack icon) {
        /* 623 */ if (icon.getItemMeta().hasLocalizedName()) {
            /* 625 */ return fromString(icon.getItemMeta().getLocalizedName());
        }
        /* 627 */ return null;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static Pet getFromEntity(Entity ent) {
        /* 637 */ if (ent != null && ent
                /* 638 */ .hasMetadata("AlmPet")
                && ent
                        /* 639 */ .getMetadata("AlmPet").size() > 0
                && ent
                        /* 640 */ .getMetadata("AlmPet").get(0) != null
                && ((MetadataValue) ent
                        /* 641 */ .getMetadata("AlmPet").get(0)).value() != null) {
            /* 643 */ return (Pet) ((MetadataValue) ent.getMetadata("AlmPet").get(0)).value();
        }
        /* 645 */ return null;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static Pet fromOwner(UUID owner) {
        /* 655 */ return getActivePets().get(owner);
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static Pet getFromLastInteractedWith(Player p) {
        /* 665 */ if (p != null && p
                /* 666 */ .hasMetadata("AlmPetInteracted")
                && p
                        /* 667 */ .getMetadata("AlmPetInteracted").size() > 0
                && p
                        /* 668 */ .getMetadata("AlmPetInteracted").get(0) != null
                && ((MetadataValue) p
                        /* 669 */ .getMetadata("AlmPetInteracted").get(0)).value() != null) {
            /* 671 */ return (Pet) ((MetadataValue) p.getMetadata("AlmPetInteracted").get(0)).value();
        }
        /* 673 */ return null;
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public static List<Pet> getAvailablePets(Player p) {
        /* 683 */ ArrayList<Pet> pets = new ArrayList<>();

        /* 685 */ for (Pet pet : objectPets) {

            /* 687 */ if (pet.isCheckPermission()) {

                /* 689 */ if (p.hasPermission(pet.getPermission())) {
                    /* 690 */ pets.add(pet);
                }
                continue;
            }
            /* 694 */ pets.add(pet);
        }
        /*     */
        /*     */

        /* 699 */ return pets;
    }
    /*     */

    public static void clearPets() {
        /* 704 */ for (Pet pet : getActivePets().values()) {
            /* 706 */ pet.despawn();
        }
    }
}

/*
 * Location: E:\MyGame\pc3\town 1.16\plugins\AdvancedPet
 * r0.2.1.jar!\fr\nocsy\almpet\data\Pet.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version: 1.1.3
 */
