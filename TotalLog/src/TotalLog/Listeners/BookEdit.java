package TotalLog.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import TotalLog.TotalLog;

public class BookEdit implements Listener
{
	public BookEdit(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void BookEdited(PlayerEditBookEvent Event)
	{
		String BookData = "";
		for(String S : Event.getNewBookMeta().getPages())
		{
			BookData += S + "\n\n================================================\n\n";
		}
		TotalLog.WriteBook(BookData, Event.getPlayer().getName());
		
	}
}
