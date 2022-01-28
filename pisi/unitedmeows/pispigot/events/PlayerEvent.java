package pisi.unitedmeows.pispigot.events;

import org.bukkit.entity.Player;
import pisi.unitedmeows.eventapi.event.Event;

public class PlayerEvent extends Event {
	private Player player;

	public PlayerEvent(Player _player) {
		player = _player;
	}

	public Player player() {
		return player;
	}
}
