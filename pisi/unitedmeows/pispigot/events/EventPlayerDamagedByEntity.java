package pisi.unitedmeows.pispigot.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pisi.unitedmeows.eventapi.event.Event;

public class EventPlayerDamagedByEntity extends PlayerEvent {

	private Entity attacker;
	private double damage, finalDamage;

	public EventPlayerDamagedByEntity(Player _player, Entity _attacker, double _damage, double _finalDamage) {
		super(_player);
		attacker = _attacker;
		damage = _damage;
		finalDamage = _finalDamage;
	}

	public EventPlayerDamagedByEntity setDamage(double _damage) {
		damage = _damage;
		return this;
	}

	public double damage() {
		return damage;
	}

	public Entity attacker() {
		return attacker;
	}

	public double finalDamage() {
		return finalDamage;
	}
}
