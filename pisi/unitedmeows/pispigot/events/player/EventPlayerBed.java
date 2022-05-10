package pisi.unitedmeows.pispigot.events.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import pisi.unitedmeows.pispigot.events.PlayerEvent;

public class EventPlayerBed extends PlayerEvent {
	private Block bed;
	private BedAction action;

	public EventPlayerBed(Player _player, Block _bed, BedAction _action) {
		super(_player);
		bed = _bed;
		action = _action;
	}

	public Block bed() {
		return bed;
	}

	public enum BedAction {
		SLEEP,
		LEAVE
	}
}
