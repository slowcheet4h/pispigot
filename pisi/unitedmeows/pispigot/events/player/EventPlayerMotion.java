package pisi.unitedmeows.pispigot.events.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import pisi.unitedmeows.eventapi.event.Event;

public class EventPlayerMotion extends Event {

	private Player player;
	private Location from, to;

	public EventPlayerMotion(Player _player, Location _from, Location _to) {
		player = _player;
		from = _from;
		to = _to;
	}

	public Player player() {
		return player;
	}

	public Location to() {
		return to;
	}

	public Location from() {
		return from;
	}
}
