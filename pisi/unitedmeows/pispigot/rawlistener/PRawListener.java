package pisi.unitedmeows.pispigot.rawlistener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pisi.unitedmeows.pispigot.Pispigot;
import pisi.unitedmeows.pispigot.events.EventPlayerAttack;
import pisi.unitedmeows.pispigot.events.EventPlayerDamagedByEntity;
import pisi.unitedmeows.pispigot.events.EventPlayerMotion;

public class PRawListener implements Listener {


	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Pispigot.eventSystem(event.getPlayer());
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Pispigot.removeSystem(event.getPlayer());
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		final EventPlayerMotion pisiEvent = new EventPlayerMotion(event.getPlayer(), event.getFrom(), event.getTo());
		pisiEvent.setCanceled(event.isCancelled());
		Pispigot.eventSystem(event.getPlayer()).fire(pisiEvent);
		event.setCancelled(pisiEvent.isCanceled());
	}

	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event) {
		final Entity attacker = event.getDamager();
		final Entity damaged = event.getEntity();

		if (attacker instanceof Player) {
			EventPlayerAttack pisiEvent = new EventPlayerAttack((Player) attacker, damaged, event.getEntityType(), event.getDamage(), event.getFinalDamage());
			pisiEvent.setCanceled(event.isCancelled());
			Pispigot.eventSystem((Player) attacker).fire(pisiEvent);
			event.setCancelled(pisiEvent.isCanceled());
			event.setDamage(pisiEvent.damage());
		}


		if (damaged instanceof Player) {
			EventPlayerDamagedByEntity pisiEvent = new EventPlayerDamagedByEntity((Player) damaged, attacker, event.getDamage(), event.getFinalDamage());
			pisiEvent.setCanceled(event.isCancelled());
			Pispigot.eventSystem((Player) damaged).fire(pisiEvent);
			event.setCancelled(pisiEvent.isCanceled());
			event.setDamage(pisiEvent.damage());
		}
	}



}
