package pisi.unitedmeows.pispigot.rawlistener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import pisi.unitedmeows.pispigot.Pispigot;
import pisi.unitedmeows.pispigot.events.player.EventPlayerAttack;
import pisi.unitedmeows.pispigot.events.player.EventPlayerBed;
import pisi.unitedmeows.pispigot.events.player.EventPlayerDamagedByEntity;
import pisi.unitedmeows.pispigot.events.player.EventPlayerMotion;

public class PRawListener implements Listener {
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event) {
		final EventPlayerBed pisiEvent = new EventPlayerBed(event.getPlayer(), event.getBed(), EventPlayerBed.BedAction.SLEEP);
		pisiEvent.setCanceled(event.isCancelled());
		Pispigot.get().eventSystem().fire(pisiEvent);
		event.setCancelled(pisiEvent.isCanceled());
	}

	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent event) {
		Pispigot.get().eventSystem().fire(new EventPlayerBed(event.getPlayer(), event.getBed(), EventPlayerBed.BedAction.LEAVE));
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		final EventPlayerMotion pisiEvent = new EventPlayerMotion(event.getPlayer(), event.getFrom(), event.getTo());
		pisiEvent.setCanceled(event.isCancelled());
		Pispigot.get().eventSystem().fire(pisiEvent);
		event.setCancelled(pisiEvent.isCanceled());
	}

	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event) {
		final Entity attacker = event.getDamager();
		final Entity damaged = event.getEntity();
		if (attacker instanceof Player) {
			EventPlayerAttack pisiEvent = new EventPlayerAttack((Player) attacker, damaged, event.getEntityType(), event.getDamage(), event.getFinalDamage());
			pisiEvent.setCanceled(event.isCancelled());
			Pispigot.get().eventSystem().fire(pisiEvent);
			event.setCancelled(pisiEvent.isCanceled());
			event.setDamage(pisiEvent.damage());
		}
		if (damaged instanceof Player) {
			EventPlayerDamagedByEntity pisiEvent = new EventPlayerDamagedByEntity((Player) damaged, attacker, event.getDamage(), event.getFinalDamage());
			pisiEvent.setCanceled(event.isCancelled());
			Pispigot.get().eventSystem().fire(pisiEvent);
			event.setCancelled(pisiEvent.isCanceled());
			event.setDamage(pisiEvent.damage());
		}
	}
}
