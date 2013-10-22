package TotalLog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import TotalLog.Handler.DataHandler;
import TotalLog.Handler.ChatBrother.ChatBrother;
import TotalLog.Listeners.AsynchChat;
import TotalLog.Listeners.BookEdit;
import TotalLog.Listeners.CraftEvent;
import TotalLog.Listeners.MobSpawnEvent;
import TotalLog.Listeners.PlayerCmdPreProcessingEvent;
import TotalLog.Listeners.PlayerDied;
import TotalLog.Listeners.PlayerJoin;
import TotalLog.Listeners.PlayerLeave;

public class TotalLog extends JavaPlugin
{
	public static DataHandler CommandLogger, MobSpawnLogger, PvpLogger, ChatLogger, CraftLogger, BookLogger,
	JoinLeaveLogger, PlayerLogger, ChatFilter;
	private String DateFolder = "";
	public static ChatBrother ChatBrother = new ChatBrother();
	@Override
	public void onEnable()
	{
		CommandLogger = new DataHandler(getDataFolder() + File.separator + "CommandData.txt");
		MobSpawnLogger = new DataHandler(getDataFolder() + File.separator + "MobspawnData.txt");
		PvpLogger = new DataHandler(getDataFolder() + File.separator + "PlayerDeathData.txt");
		ChatLogger = new DataHandler(getDataFolder() + File.separator + "ChatData.txt");
		CraftLogger = new DataHandler(getDataFolder() + File.separator + "CraftData.txt");
		JoinLeaveLogger = new DataHandler(getDataFolder() + File.separator + "JoinLeaveData.txt");
		ChatFilter = new DataHandler(getDataFolder() + File.separator + "ChatFilter.txt");
		
		
		new AsynchChat(this);
		new MobSpawnEvent(this);
		new PlayerDied(this);
		new PlayerCmdPreProcessingEvent(this);
		new CraftEvent(this);
		new BookEdit(this);
		new PlayerJoin(this);
		new PlayerLeave(this);
		Bukkit.getLogger().info("==== Total Logs Has Loaded ====");
		Bukkit.getLogger().info("   -  Big Brother Is Home  -");
	}
	
	@Override
	public void onDisable()
	{
		
	}
	//TODO Write to an individual character log, keeping track of what each player does.
	public static void Write(String Data, Log Type, Player Player)
	{
		String Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		switch (Type)
		{
			case Chat:
				ChatLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Command:
				CommandLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case MobSpawn:
				MobSpawnLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case PVP:
				PvpLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Craft:
				CraftLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Join:
				JoinLeaveLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Leave:
				JoinLeaveLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			default:
				break;
		}
		String DateUser = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		PlayerLogger = new DataHandler("plugins/TotalLog/UserData/" + Player.getName() + "/" + DateUser + ".txt");
		String HourMin = new SimpleDateFormat("HH:mm:ss").format(new Date());
		PlayerLogger.AppendString("[" + HourMin + "] -> " + Data);
	}
	
	public static void Write(String Data, Log Type)
	{
		String Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		switch (Type)
		{
			case Chat:
				ChatLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Command:
				CommandLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case MobSpawn:
				MobSpawnLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case PVP:
				PvpLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Craft:
				CraftLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Join:
				JoinLeaveLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			case Leave:
				JoinLeaveLogger.AppendString("[" + Date + "] -> " + Data);
				break;
			default:
				break;
		}
	}
	
	public static void WriteBook(String Data, String User)
	{
		String Date = new SimpleDateFormat("yyyy-MM-dd HH mm ss").format(new Date());
		BookLogger = new DataHandler("plugins/TotalLog/BookData/" + User + "/" + Date + ".txt");
		BookLogger.AppendString(Data);
	}
	
	public static void Message(String Data)
	{
		for(Player P : Bukkit.getOnlinePlayers())
		{
			P.sendMessage(ChatColor.GREEN + "Big Brother - " + Data);
		}
	}
	
	public static void MessagePlayer(String Data, Player Player)
	{
		Player.sendMessage(ChatColor.GREEN + "Big Brother - " + Data);
	}
	
	public static enum Log
	{
		Command,
		MobSpawn,
		PVP,
		Chat,
		Craft,
		Join,
		Leave
	}
}
