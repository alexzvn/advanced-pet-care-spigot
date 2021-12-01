package fr.nocsy.almpet.data;

import fr.nocsy.almpet.AdvancedPet;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.skills.Skill;
import java.util.Objects;
import java.util.Optional;
import org.bukkit.scheduler.BukkitRunnable;












































































class null
  extends BukkitRunnable
{
  public void run() {
   Optional<Skill> optionalSkill = MythicMobs.inst().getSkillManager().getSkill(despawnSkillName);
   Objects.requireNonNull(pet); optionalSkill.ifPresent(pet::setDespawnSkill);
   if (pet.getDespawnSkill() == null)
    {
     AdvancedPet.getLog().warning(AdvancedPet.getLogName() + "Impossible to link the despawn skill \"" + AdvancedPet.getLogName() + "\" to the pet \"" + despawnSkillName + "\", because this skill doesn't exist.");
    }
  }
}


/* Location:              E:\MyGame\pc3\town 1.16\plugins\AdvancedPet r0.2.1.jar!\fr\nocsy\almpet\data\PetConfig$1.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */
