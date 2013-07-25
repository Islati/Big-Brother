package TotalLog.Listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import TotalLog.TotalLog;
import TotalLog.Handler.Cooldown.Cooldown;

public class AsynchChat implements Listener
{
	private Cooldown ChatBrotherCooldown = new Cooldown(600);
	public AsynchChat(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void ChatEvent(AsyncPlayerChatEvent Event)
	{
		if (!Event.getMessage().startsWith("/"))
		{
			TotalLog.Write(Event.getPlayer().getName() + " - " + Event.getMessage(), TotalLog.Log.Chat,Event.getPlayer());
			if (Event.getMessage().contains("#"))
			{
				if (!Event.getMessage().contains(" ") && TotalLog.ChatBrother.hashtagExists(Event.getMessage()) == false)
				{
					TotalLog.ChatBrother.addHashtag(Event.getMessage());
					//TotalLog.Message("Hashtags are amazing! I n)
				}
			}
			if (Event.getMessage().toLowerCase().contains("budder"))
			{
				String Message = Event.getMessage().toLowerCase().replace("budder", "hampster");
				Event.setMessage(Message);
			}
			
			if (Event.getMessage().toLowerCase().contains("butter"))
			{
				String Message = Event.getMessage().toLowerCase().replace("butter", "hampster");
				Event.setMessage(Message);
			}
			if (TotalLog.ChatBrother.PercentCheck(3) == true && !ChatBrotherCooldown.IsOnCooldown("CB"))
			{
				switch (new Random().nextInt(2))
				{
					case 0:
						TotalLog.ChatBrother.addPlayerQuote(Event.getMessage(), Event.getPlayer());
						Bukkit.getLogger().info(ChatColor.GREEN + "[Big Brother] Just saved a quote from " + Event.getPlayer().getName() + " that says '" + Event.getMessage() + "'");
						break;
					case 1:
						String BBMsg = TotalLog.ChatBrother.getRandomQuote();
						if (BBMsg.contains(TotalLog.ChatBrother.RandomNumberTag))
						{
							BBMsg = TotalLog.ChatBrother.FormatMessage(BBMsg, TotalLog.ChatBrother.RandomNumberTag, "" + (1 + new Random().nextInt(15) + new Random().nextInt(19) + new Random().nextInt(11)) + "");
						}
						TotalLog.Message(BBMsg);
						break;
					default:
						break;
				}
				ChatBrotherCooldown.SetOnCooldown("CB");
			}
		}
	}
}
