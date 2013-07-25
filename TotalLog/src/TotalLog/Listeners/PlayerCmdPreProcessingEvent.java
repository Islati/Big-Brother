package TotalLog.Listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import TotalLog.TotalLog;

public class PlayerCmdPreProcessingEvent implements Listener
{
	
	public PlayerCmdPreProcessingEvent(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void CommandPreProcess(PlayerCommandPreprocessEvent Event)
	{
		
		if (Event.getMessage().contains("market") && Event.getPlayer().getGameMode() == GameMode.CREATIVE && !Event.getPlayer().isOp())
		{
			Event.setCancelled(true);
			return;
		}
		TotalLog.Write("User " + Event.getPlayer().getName() + " Used command '" + Event.getMessage() + "'" ,TotalLog.Log.Command,Event.getPlayer());
		if (!Event.getPlayer().isOp())
		{
			TotalLog.ChatBrother.HandleCommandReminder(Event.getPlayer(), Event.getMessage());
		}
	}
}
