package pisi.unitedmeows.pispigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import pisi.unitedmeows.eventapi.system.BasicEventSystem;
import pisi.unitedmeows.pispigot.rawlistener.PRawListener;

public class Pispigot extends JavaPlugin {
	private static Pispigot INSTANCE;
	private BasicEventSystem eventSystem;

	@Override
	public void onEnable() {
		INSTANCE = this;
		Bukkit.getServer().getPluginManager().registerEvents(new PRawListener(), INSTANCE);
	}

	public BasicEventSystem eventSystem() {
		return eventSystem;
	}

	public static Pispigot get() {
		return INSTANCE;
	}
}
