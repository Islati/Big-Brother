package TotalLog.Listeners;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import TotalLog.TotalLog;

public class PlayerJoin implements Listener
{
	public PlayerJoin(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void JoinEvent(PlayerJoinEvent Event)
	{
		if (TotalLog.ChatBrother.PercentCheck(10))
		{
			TotalLog.ChatBrother.HandleJoin(Event.getPlayer());
		}
		TotalLog.Write(Event.getPlayer().getName() + " Joined the game from " + Event.getPlayer().getAddress().getAddress().toString(), TotalLog.Log.Join,Event.getPlayer());
	}
}
