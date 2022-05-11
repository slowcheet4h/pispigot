package pisi.unitedmeows.pispigot.events.player;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import pisi.unitedmeows.pispigot.events.PlayerEvent;

public class EventPlayerCommandPreprocess extends PlayerEvent {
	private static final HandlerList handlers = new HandlerList();
	private String message;
	private String format = "<%1$s> %2$s";
	private final Set<Player> recipients;

	public EventPlayerCommandPreprocess(Player player, String message) {
		super(player);
		this.recipients = new HashSet<>(player.getServer().getOnlinePlayers());
		this.message = message;
	}

	public EventPlayerCommandPreprocess(Player player, String message, Set<Player> recipients) {
		super(player);
		this.recipients = recipients;
		this.message = message;
	}

	public String message() {
		return this.message;
	}

	public void message(String command) throws IllegalArgumentException {
		Validate.notNull(command, "Command cannot be null");
		Validate.notEmpty(command, "Command cannot be empty");
		this.message = command;
	}

	@Deprecated
	public String format() {
		return this.format;
	}

	@Deprecated
	public void format(String format) {
		try {
			String.format(format, this.player(), this.message);
		}
		catch (RuntimeException ex) {
			ex.fillInStackTrace();
			throw ex;
		}
		this.format = format;
	}

	@Deprecated
	/**
	 * @deprecated Because we cant set player in PlayerEvents currently and I dont see any reason for setting the player.
	 */
	public void setPlayer() {
		throw new UnsupportedOperationException("setPlayer is unavailable.");
	}

	@Deprecated
	public Set<Player> recipients() {
		return this.recipients;
	}
}
