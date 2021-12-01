package fr.nocsy.almpet.data.flags;

import fr.nocsy.almpet.data.Language;
import fr.nocsy.almpet.data.Pet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;




























class null
  implements Runnable
{
  public void run() {
   for (UUID owner : Pet.getActivePets().keySet()) {
      
     Pet pet = (Pet)Pet.getActivePets().get(owner);
      
     if (!pet.isMountable()) {
        continue;
      }
     Player p = Bukkit.getPlayer(owner);
      
     if (p != null) {
        
       if (!pet.hasMount((Entity)p)) {
          continue;
        }
       boolean hasToBeEjected = DismountPetFlag.this.testState(p);
        
       if (hasToBeEjected) {
          
         pet.dismount((Entity)p);
         Language.NOT_MOUNTABLE_HERE.sendMessage(p);
        } 
      } 
    } 
  }
}


/* Location:              E:\MyGame\pc3\town 1.16\plugins\AdvancedPet r0.2.1.jar!\fr\nocsy\almpet\data\flags\DismountPetFlag$1.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */
