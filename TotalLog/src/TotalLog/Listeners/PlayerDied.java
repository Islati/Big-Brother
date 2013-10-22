package TotalLog.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import TotalLog.TotalLog;
import TotalLog.TotalLog.Log;

public class PlayerDied implements Listener
{
	public PlayerDied(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerDiedEvent(PlayerDeathEvent Event)
	{
		try
		{
			String Name = Event.getEntity().getName();
			TotalLog.Write(Name + " died; [DeathMessage] ->" + Event.getDeathMessage(), TotalLog.Log.PVP,Event.getEntity());
		}
		catch (Exception Ex)
		{
			//slop
		}
	}
	
	
}
