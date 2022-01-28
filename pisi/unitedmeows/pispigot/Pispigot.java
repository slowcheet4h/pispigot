package pisi.unitedmeows.pispigot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pisi.unitedmeows.eventapi.system.BasicEventSystem;
import pisi.unitedmeows.pispigot.rawlistener.PRawListener;

import java.util.HashMap;

public class Pispigot extends JavaPlugin {


	private static HashMap<Player, BasicEventSystem> _EVENT_SYSTEMS;
	private static Pispigot INSTANCE;

	static {
		_EVENT_SYSTEMS = new HashMap<>();
	}

	@Override
	public void onEnable() {
		INSTANCE = this;
		_EVENT_SYSTEMS.entrySet().removeIf(k -> !k.getKey().isOnline());
		Bukkit.getServer().getPluginManager().registerEvents(new PRawListener(), INSTANCE);
		for (Player player : Bukkit.getOnlinePlayers()) {
			_EVENT_SYSTEMS.computeIfAbsent(player, k -> new BasicEventSystem());
		}
	}


	public static BasicEventSystem removeSystem(Player player) {
		return _EVENT_SYSTEMS.remove(player);
	}

	public static BasicEventSystem eventSystem(Player player) {
		return _EVENT_SYSTEMS.computeIfAbsent(player, k -> new BasicEventSystem());
	}


}
