package TotalLog.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import TotalLog.TotalLog;
import TotalLog.TotalLog.Log;

public class PlayerLeave implements Listener
{
	public PlayerLeave(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void LeaveEvent(PlayerQuitEvent Event)
	{
		TotalLog.ChatBrother.HandleQuit(Event.getPlayer());
		TotalLog.Write(Event.getPlayer().getName() + " Quit the game from " + Event.getPlayer().getAddress().getAddress().toString(), TotalLog.Log.Leave,Event.getPlayer());
	}
}
