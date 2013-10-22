package TotalLog.Listeners;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import TotalLog.TotalLog;
import TotalLog.Handler.ItemHandler;
import TotalLog.Handler.Cooldown.Cooldown;

public class AsynchChat implements Listener
{
	private Cooldown ChatBrotherCooldown = new Cooldown(600);
	private Cooldown AwesomeCookieCooldown = new Cooldown(86400);
	public AsynchChat(TotalLog Plugin)
	{
		Plugin.getServer().getPluginManager().registerEvents(this, Plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void ChatEvent(AsyncPlayerChatEvent Event)
	{
		if (!Event.getMessage().startsWith("/"))
		{
			String LowercaseChat = Event.getMessage();
			TotalLog.Write(Event.getPlayer().getName() + " - " + Event.getMessage(), TotalLog.Log.Chat,Event.getPlayer());
			
			for(String BlockedWord : TotalLog.ChatFilter.getContentsAsList())
			{
				String[] Filtered = StringUtils.split(BlockedWord, "|");
				if (LowercaseChat.toLowerCase().contains(Filtered[0].toLowerCase()))
				{
					LowercaseChat = WordUtils.capitalize(StringUtils.replace(LowercaseChat.toLowerCase(), Filtered[0].toLowerCase(), Filtered[1].toLowerCase()));
				}
			}
			
			Event.setMessage(LowercaseChat);
			/*if (Event.getMessage().contains("#"))
			{
				if (!Event.getMessage().contains(" ") && TotalLog.ChatBrother.hashtagExists(Event.getMessage()) == false)
				{
					TotalLog.ChatBrother.addHashtag(Event.getMessage());
					//TotalLog.Message("Hashtags are amazing! I n)
				}
			}
			/*
			if (Event.getMessage().toLowerCase().contains("budder") || Event.getMessage().toLowerCase().contains("butter"))
			{
				
				String Message = Event.getMessage().toLowerCase().replace("budder", "hampster").replace("butter", "hampster");
				Event.setMessage(Message);
			}
			*/
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
							BBMsg = TotalLog.ChatBrother.FormatMessage(BBMsg, TotalLog.ChatBrother.RandomNumberTag, "" + (1 + new Random().nextInt(15) + new Random().nextInt(50) + new Random().nextInt(69)) + "");
						}
						TotalLog.Message(BBMsg);
						break;
					default:
						break;
				}
				ChatBrotherCooldown.SetOnCooldown("CB");
			}
			
			if (Event.getMessage().startsWith("@"))
			{
				String AtSplit = StringUtils.substringBetween(Event.getMessage(), "@", " ");
				if (AtSplit != null)
				{
					Player Receiver = Bukkit.getPlayer(AtSplit);
					if (Receiver != null && Receiver.isOnline())
					{
						String SendMessage = StringUtils.substringAfter(Event.getMessage(), "@" + AtSplit + " ");
						Event.getPlayer().chat("/w " + Receiver.getName() + " " + SendMessage);
						Receiver.playSound(Receiver.getLocation(), Sound.CAT_MEOW, 1.0f, 1.0f);
						Event.setCancelled(true);
						return;
					}
				}
			}
			
			LowercaseChat = LowercaseChat.toLowerCase();
			
			if (LowercaseChat.contains("brandon is awesome") || LowercaseChat.contains("jodie is awesome") || LowercaseChat.contains("this server is awesome") || LowercaseChat.contains("totalwar is awesome") || LowercaseChat.contains("dan is awesome"))
			{
				if (!this.AwesomeCookieCooldown.IsOnCooldown(Event.getPlayer().getName()))
				{
					ItemStack AwesomeCookie = new ItemHandler().makeItemStack(Material.COOKIE, ChatColor.YELLOW + "Cookie of Awesomeness", Arrays.asList(new String[] {ChatColor.WHITE + "A token of appreciation for",ChatColor.WHITE + "being to damn awesome!" }));
					if (Event.getPlayer().getInventory().firstEmpty() != -1)
					{
						Event.getPlayer().getInventory().addItem(AwesomeCookie);
						TotalLog.MessagePlayer("Here, have a cookie ♥",Event.getPlayer());
						AwesomeCookieCooldown.SetOnCooldown(Event.getPlayer().getName());
					}
				}
			}
			
			
			if (Event.getMessage().startsWith(">"))
			{
				Event.setMessage(ChatColor.GREEN + Event.getMessage());
			}
		}
	}
}
