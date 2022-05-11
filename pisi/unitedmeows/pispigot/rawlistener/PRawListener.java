package pisi.unitedmeows.pispigot.rawlistener;

import static pisi.unitedmeows.pispigot.events.player.EventPlayerBed.BedAction.*;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

import pisi.unitedmeows.pispigot.Pispigot;
import pisi.unitedmeows.pispigot.events.player.*;

public class PRawListener implements Listener {
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent event) {
		final EventPlayerBed pisiEvent = new EventPlayerBed(event.getPlayer(), event.getBed(), EventPlayerBed.BedAction.SLEEP);
		pisiEvent.setCanceled(event.isCancelled());
		Pispigot.get().eventSystem().fire(pisiEvent);
		event.setCancelled(pisiEvent.isCanceled());
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		EventAsyncPlayerChat asyncPlayerChat = new EventAsyncPlayerChat(event.getPlayer(), event.getMessage(), event.getRecipients());
		asyncPlayerChat.setCanceled(event.isCancelled());
		Pispigot.get().eventSystem().fire(asyncPlayerChat);
		event.setCancelled(asyncPlayerChat.isCanceled());
		event.setMessage(asyncPlayerChat.message());
		event.setFormat(asyncPlayerChat.format());
	}

	@EventHandler
	public void onChatTabComplete(PlayerChatTabCompleteEvent event) {
		Pispigot.get().eventSystem().fire(new EventPlayerChatTabComplete(event.getPlayer(), event.getChatMessage(), event.getTabCompletions()));
	}

	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		EventPlayerCommandPreprocess eventPlayerChatTabComplete = new EventPlayerCommandPreprocess(event.getPlayer(), event.getMessage(), event.getRecipients());
		eventPlayerChatTabComplete.setCanceled(event.isCancelled());
		Pispigot.get().eventSystem().fire(eventPlayerChatTabComplete);
		event.setCancelled(eventPlayerChatTabComplete.isCanceled());
		event.setFormat(eventPlayerChatTabComplete.format());
		event.setMessage(eventPlayerChatTabComplete.message());
	}

	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
		Pispigot.get().eventSystem().fire(new EventPlayerChangedWorld(event.getPlayer(), event.getFrom()));
	}

	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent event) {
		Pispigot.get().eventSystem().fire(new EventPlayerBed(event.getPlayer(), event.getBed(), LEAVE));
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
