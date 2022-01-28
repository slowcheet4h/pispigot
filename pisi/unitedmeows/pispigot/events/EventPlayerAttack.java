package pisi.unitedmeows.pispigot.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pisi.unitedmeows.eventapi.event.Event;

public class EventPlayerAttack extends PlayerEvent {

	private Entity victim;
	private EntityType victimType;
	private double damage, finalDamage;

	public EventPlayerAttack(Player _attacker, Entity _victim, EntityType _victimType, double _damage, double _finalDamage) {
		super(_attacker);
		victim = _victim;
		victimType = _victimType;
		damage = _damage;
		finalDamage = _finalDamage;
	}

	public double damage() {
		return damage;
	}


	public Entity victim() {
		return victim;
	}

	public EntityType victimType() {
		return victimType;
	}

	public EventPlayerAttack setDamage(double _damage) {
		damage = _damage;
		return this;
	}
}
