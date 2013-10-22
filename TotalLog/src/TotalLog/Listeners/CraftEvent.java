package TotalLog.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import TotalLog.TotalLog;

public class CraftEvent implements Listener
{
	public CraftEvent(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerCrafted(CraftItemEvent Event)
	{
		try
		{
			TotalLog.Write(Event.getWhoClicked().getName() + " crafted a(n) " + Event.getInventory().getResult().getType().name() + " at location [" + Event.getWhoClicked().getLocation().getX() + "," + Event.getWhoClicked().getLocation().getY() + "," + Event.getWhoClicked().getLocation().getZ() + "]", TotalLog.Log.Craft,(Player)Event.getWhoClicked());
	
		}
		catch (NullPointerException Ex)
		{
			// Slop
		}
	}
}
