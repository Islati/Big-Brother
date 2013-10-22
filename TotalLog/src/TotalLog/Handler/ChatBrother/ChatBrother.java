package TotalLog.Handler.ChatBrother;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import TotalLog.TotalLog;
import TotalLog.Handler.DataHandler;

public class ChatBrother
{
	public String RandomNumberTag = "<RandomNumber>";
	
	public ChatBrother()
	{
		
	}
	
	public void HandleCommandReminder(Player P, String Command)
	{
		
		if (StartsWith(Command, "/w "))
		{
			String PlayerName = StringUtils.substringBetween(Command, "/w ", " ");
			Player Player = Bukkit.getPlayer(PlayerName);
			if (Player != null)
			{
				Player.playSound(Player.getLocation(), Sound.CAT_MEOW, 1.0f, 1.0f);
			}
		}
		else if (StartsWith(Command, "/msg"))
		{
			
		}
		
		if (StartsWith(Command,"/spawnmob") || StartsWith(Command,"/spawner"))
		{
			String Addition = "";
			while (true)
			{
				if (PercentCheck(11) == true)
				{
					Addition += " " + getRandomHashTag();
				}
				else
				{
					break;
				}
			}
			TotalLog.MessagePlayer((FormatMessage(getMessage("SpawnText") + Addition,"<Player>",P.getName())),P);
			return;
		}
		
		if (StartsWith(Command,"/give") || StartsWith(Command,"/i") || StartsWith(Command,"/itemgen") || StartsWith(Command,"/more"))
		{
			String Addition = "";
			while (true)
			{
				if (PercentCheck(11) == true)
				{
					Addition += " " + getRandomHashTag();
				}
				else
				{
					break;
				}
			}
			TotalLog.Message(FormatMessage(getMessage("ItemText") + Addition,"<Player>",P.getName()));
			return;
		}
		
		if (StartsWith(Command,"/kill") || StartsWith(Command,"/smite") || StartsWith(Command,"/fireball") || StartsWith(Command,"/explode"))
		{
			String[] Commands;
			String WhatDo = "";
			if (Command.contains(" "))
			{
				Commands = StringUtils.split(Command, " ");
				WhatDo = Commands[0];
			}
			else
			{
				WhatDo = Command;
			}
			String Addition = "";
			while (true)
			{
				if (PercentCheck(11) == true)
				{
					Addition += " " + getRandomHashTag();
				}
				else
				{
					break;
				}
			}
			String Message = FormatMessage(getMessage("KillText") + Addition,"<Command>",WhatDo);
			String Say = FormatMessage(Message,"<Player>",P.getName());
			TotalLog.Message(Say);
			return;
		}
		
		if (StartsWith(Command,"repair"))
		{
			String Addition = "";
			while (true)
			{
				if (PercentCheck(11) == true)
				{
					Addition += " " + getRandomHashTag();
				}
				else
				{
					break;
				}
			}
			TotalLog.MessagePlayer(FormatMessage(getMessage("RepairText") + Addition,"<Player>",P.getName()),P);
			return;
		}
		
		if (Command.equalsIgnoreCase("/join"))
		{
			TotalLog.MessagePlayer("Hey " + ChatColor.YELLOW + P.getName() + ChatColor.GREEN + ", you were close! To join a faction type " + ChatColor.GOLD + "/join <faction>" + ChatColor.GREEN + " - Where " + ChatColor.GOLD + "<faction>" + ChatColor.GOLD + " is the group you want to join :)",P);
			return;
		}
		
		if (StartsWith(Command,"f join"))
		{
			TotalLog.MessagePlayer("Hey " + ChatColor.YELLOW + P.getName() + ChatColor.GREEN + ", you were close! We don't use the factions plugin though, so /f isn't a command. To join a faction type " + ChatColor.GOLD + "/join <faction>" + ChatColor.GREEN +" - Where " + ChatColor.GOLD + "<faction>" + ChatColor.GOLD + " is the group you want to join :)",P);
			return;
			
		}
	}
	
	/**
	 * Handles join messages of a player
	 * @param P The player to handle
	 */
	public void HandleJoin(Player P)
	{
		String Welcome = FormatMessage(getMessage("JoinText"),"<Player>",P.getName());
		Welcome = FormatMessage(Welcome,this.RandomNumberTag, "" + (1 + new Random().nextInt(15) + new Random().nextInt(16) + new Random().nextInt(9)));
		TotalLog.Message(Welcome);
		Bukkit.getLogger().info(ChatColor.GREEN + "Big Brother - " + Welcome + generateHashTags());
	}
	
	/**
	 * Handling the leave messages of a player
	 * @param P The player to handle
	 */
	public void HandleQuit(Player P)
	{
		String Welcome = FormatMessage(getMessage("QuitText"),"<Player>",P.getName());
		TotalLog.Message(Welcome);
		Bukkit.getLogger().info(ChatColor.GREEN + "Big Brother - " + Welcome + generateHashTags());
	}
	
	public String getRandomMessage(List<String> List)
	{
		return List.get(new Random().nextInt(List.size()));
	}
	
	/**
	 * Adds "potential" hashtags to a list for later use
	 * @param Message
	 */
	public void HandleHashtag(String Message)
	{
		new DataHandler("plugins/TotalLog/ChatBrother/PotentialHashTags.txt").AppendString(Message);
	}
	
	/**
	 * An easier way to use String.Startswith
	 * @param What String to search
	 * @param Check Check for at the start
	 * @return True if .ToLowerCase() contains Check.Lowercase at ther start
	 */
	public boolean StartsWith(String What,String Check)
	{
		return What.toLowerCase().startsWith(Check.toLowerCase());
	}
	
	/**
	 * Replaces text within a string, IE: Formatting a message to replace <Player> with the players name
	 * @param Text The string to search
	 * @param Find The string to find within the search
	 * @param Replace The string to replace the found string with
	 * @return
	 */
	public String FormatMessage(String Text, String Find, String Replace)
	{
		String Return = StringUtils.replace(Text, Find, Replace);
		return Return;
	}
	
	
	/**
	 * @return Returns the Hashtags from the HashTag file @ /plugins/totallog/chatbrother/HashTags.txt
	 */
	public List<String> getHashtags()
	{
		try
		{
			return FileUtils.readLines(new File("plugins/TotalLog/ChatBrother/HashTags.txt"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @return Gets a random hashtag from the hashtags file
	 */
	public String getRandomHashTag()
	{
		return getHashtags().get(new Random().nextInt(getHashtags().size()));
	}
	
	public void addHashtag(String Tag)
	{
		new DataHandler("plugins/TotalLog/ChatBrother/HashTags.txt").AppendString(Tag);
	}
	
	public boolean hashtagExists(String Tag)
	{
		DataHandler HashHandler = new DataHandler("plugins/TotalLog/ChatBrother/HashTags.txt");
		return HashHandler.Contains(Tag);
	}
	
	public void addPlayerQuote(String Text,Player Player)
	{
		DataHandler Quotes = new DataHandler("plugins/TotalLog/ChatBrother/PlayerQuotes.txt");
		Quotes.AppendString("<Quote>");
		Quotes.AppendString("\t<WhoSaidIt>" + Player.getName() + "</WhoSaidIt>");
		Quotes.AppendString("\t<WhatWasSaid>" + Text + "</WhatWasSaid>");
		Quotes.AppendString("</Quote>");
	}
	
	public List<String> getQuoteBlocks()
	{
		return new DataHandler("plugins/TotalLog/ChatBrother/PlayerQuotes.txt").getAllBetween("<Quote>", "</Quote>");
	}
	
	public String getRandomQuote()
	{
		String Block = this.getRandomMessage(getQuoteBlocks());
		String WhoSaid = new DataHandler().getStringBetween(Block, "<WhoSaidIt>", "</WhoSaidIt>");
		String WhatWasSaid = new DataHandler().getStringBetween(Block, "<WhatWasSaid>", "</WhatWasSaid>");
		String RandomFormat = getMessage("QuoteFormats");
		String Message = FormatMessage(RandomFormat,"<Player>", ChatColor.YELLOW + WhoSaid + ChatColor.GREEN);
		return FormatMessage(Message,"<Quote>",ChatColor.RED + WhatWasSaid + ChatColor.GREEN + generateHashTags());
	}
	
	public String generateHashTags()
	{
		String Welcome = "";
		while (true)
		{
			if (PercentCheck(11) == true)
			{
				Welcome += " " + getRandomHashTag();
			}
			else
			{
				break;
			}
		}
		return Welcome;
	}
	
	public boolean PercentCheck(int Chances)
	{
		return new Random().nextInt(100) <= Chances;
	}
	
	public String getMessage(String FileName)
	{
		try
		{
			List<String> Messages = FileUtils.readLines(new File("plugins/TotalLog/ChatBrother/" + FileName + ".txt"));
			return getRandomMessage(Messages);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "...";
	}
}
