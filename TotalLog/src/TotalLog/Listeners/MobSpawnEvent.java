package TotalLog.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import TotalLog.TotalLog;

public class MobSpawnEvent implements Listener
{
	
	public MobSpawnEvent(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void MobSpaweed(CreatureSpawnEvent Event)
	{
		if (Event.getSpawnReason() != SpawnReason.NATURAL)
		{
			TotalLog.Write(Event.getEntityType().getName() + " was spawned at Co-ords [" + Event.getLocation().getX() + "," + Event.getLocation().getY() + "," + Event.getLocation().getZ() + "]  via SpawnType : " + Event.getSpawnReason().toString(), TotalLog.Log.MobSpawn);
		}
	}
	
}
