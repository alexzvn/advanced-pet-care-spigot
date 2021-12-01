package fr.nocsy.almpet.data;

import fr.nocsy.almpet.utils.Utils;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;






















































































































































































































































class null
 implements Runnable
{
 public void run() {
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
}


/* Location:              E:\MyGame\pc3\town 1.16\plugins\AdvancedPet r0.2.1.jar!\fr\nocsy\almpet\data\Pet$2.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */
