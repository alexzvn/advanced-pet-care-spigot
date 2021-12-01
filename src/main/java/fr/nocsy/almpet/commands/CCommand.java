package fr.nocsy.almpet.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface CCommand {
  String getName();
  
  String getPermission();
  
  void execute(CommandSender paramCommandSender, Command paramCommand, String paramString, String[] paramArrayOfString);
}


/* Location:              E:\MyGame\pc3\town 1.16\plugins\AdvancedPet r0.2.1.jar!\fr\nocsy\almpet\commands\CCommand.class
 * Java compiler version: 15 (59.0)
 * JD-Core Version:       1.1.3
 */