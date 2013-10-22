package TotalLog.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemHandler
{
	/**
	 * Get the lore of an item if it has lore
	 * 
	 * @param Item
	 *            Item to get lore of
	 * @return ArrayList<String> of the lore if the item has lore, otherwise
	 *         null
	 */
	public ArrayList<String> getItemLore(ItemStack Item)
	{
		ArrayList<String> ReturnLore = new ArrayList<String>();
		if (Item.hasItemMeta() && Item.getItemMeta().hasLore())
		{
			for (String S : Item.getItemMeta().getLore())
			{
				ReturnLore.add(S);
			}
			return ReturnLore;
		}
		return null;
	}

	/**
	 * Get lore of item at specific line
	 * 
	 * @param Item
	 *            Item to get lore of
	 * @param Line
	 *            Index of lore to get
	 * @return String of lore if it exists, otherwise null
	 */
	public String getItemLore(ItemStack Item, int Line)
	{
		if (Item.hasItemMeta() && Item.getItemMeta().hasLore())
		{
			try
			{
				return Item.getItemMeta().getLore().get(Line);
			}
			catch (IndexOutOfBoundsException Exception)
			{
				Exception.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * Checks if an items lore contains specific text
	 * 
	 * @param Item
	 *            Item to check
	 * @param Text
	 *            Text to check the item for
	 * @return true if the item has the text in its lore, otherwise false.
	 */
	public boolean itemLoreContains(ItemStack Item, String Text)
	{
		if (Item.hasItemMeta() && Item.getItemMeta().hasLore())
		{
			ArrayList<String> Lore = getItemLore(Item);
			for (String s : Lore)
			{
				if (s.toLowerCase().contains(Text.toLowerCase()))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Set the name of an Item
	 * 
	 * @param Item
	 *            Item to set the name of
	 * @param Text
	 *            The name to give the item
	 */
	public void setItemName(ItemStack Item, String Text)
	{
		ItemMeta iMeta = Item.getItemMeta();
		iMeta.setDisplayName(Text);
		Item.setItemMeta(iMeta);
	}

	/**
	 * Get the name of an item
	 * 
	 * @param Item
	 * @return Name of the item if it has a name, otherwise its material type in
	 *         lowercase
	 */
	public String getItemName(ItemStack Item)
	{
		if (Item.hasItemMeta() && Item.getItemMeta().hasDisplayName())
		{
			return Item.getItemMeta().getDisplayName();
		}
		return Item.getType().toString().toLowerCase();
	}

	/**
	 * Removes an amount of items from a stack
	 * 
	 * @param Item
	 * @param Amount
	 * @return The itemstack if there are more than 0 items left in the stack,
	 *         otherwise null
	 */
	public ItemStack RemoveFromStack(ItemStack Item, int Amount)
	{
		if (Item.getAmount() > Amount)
		{
			ItemMeta StackMeta = Item.getItemMeta();
			ItemStack Return = new ItemStack(Item.getType(), Item.getAmount() - Amount);
			Return.setItemMeta(StackMeta);
			Return.setDurability(Item.getDurability());
			return Return;
		}
		return null;
	}

	/**
	 * Set the color to a piece of leather armor
	 * 
	 * @param Item
	 * @param Color
	 * @return true if the color was set, otherwise false
	 */
	public boolean setColor(ItemStack Item, Color Color)
	{
		List<Material> Leathers = Arrays.asList(new Material[] { Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS });
		if (Leathers.contains(Item.getType()))
		{
			try
			{
				LeatherArmorMeta ItemMeta = (LeatherArmorMeta) Item.getItemMeta();
				ItemMeta.setColor(Color);
				Item.setItemMeta(ItemMeta);
			}
			catch (Exception Ex)
			{
				Ex.printStackTrace();
				return false;
			}
		}
		return false;
	}

	/**
	 * Add an enchantment to an item ignoring the restrictions
	 * 
	 * @param Item
	 * @param Enchant
	 * @param Level
	 * @param IgnoreRestrictions
	 * @return true if the enchantment was added, false otherwise
	 */
	public boolean addEnchantment(ItemStack Item, Enchantment Enchant, int Level, boolean IgnoreRestrictions)
	{
		ItemMeta iMeta = Item.getItemMeta();
		boolean Status = iMeta.addEnchant(Enchant, Level, IgnoreRestrictions);
		Item.setItemMeta(iMeta);
		return Status;
	}

	/**
	 * Calls addEnchantment but follows restrictions
	 * 
	 * @param Item
	 * @param Enchant
	 * @param Level
	 * @return
	 */
	public boolean addEnchantment(ItemStack Item, Enchantment Enchant, int Level)
	{
		return this.addEnchantment(Item, Enchant, Level, false);
	}

	public boolean setItemLore(ItemStack Item, List<String> Lore)
	{
		try
		{
			ItemMeta iMeta = Item.getItemMeta();
			iMeta.setLore(Lore);
			Item.setItemMeta(iMeta);
			return true;
		}
		catch (Exception Ex)
		{
			Ex.printStackTrace();
			return false;
		}
	}

	public ItemStack makeItemStack(Material Material)
	{
		return new ItemStack(Material);
	}

	public ItemStack makeItemStack(Material Material, String Name)
	{
		ItemStack Item = new ItemStack(Material);
		this.setItemName(Item, Name);
		return Item;
	}

	public ItemStack makeItemStack(Material Material, String Name, List<String> Lore)
	{
		ItemStack Item = new ItemStack(Material);
		this.setItemName(Item, Name);
		this.setItemLore(Item, Lore);
		return Item;
	}

	public ItemStack makeLeatherItemStack(Material Material, String Name, List<String> Lore, HashMap<Enchantment, Integer> Enchantments, Color Color)
	{
		ItemStack Item = new ItemStack(Material);
		this.setItemName(Item, Name);
		this.setItemLore(Item, Lore);
		for (Entry<Enchantment, Integer> Enchant : Enchantments.entrySet())
		{
			this.addEnchantment(Item, Enchant.getKey(), Enchant.getValue(), true);
		}
		this.setColor(Item, Color);
		return Item;
	}
}