package pisi.unitedmeows.pispigot.events.player;

import org.bukkit.World;
import org.bukkit.entity.Player;

import pisi.unitedmeows.pispigot.events.PlayerEvent;

public class EventPlayerChangedWorld extends PlayerEvent {
	private final World from;

	public EventPlayerChangedWorld(Player player, World from) {
		super(player);
		this.from = from;
	}

	public World from() {
		return this.from;
	}
}
