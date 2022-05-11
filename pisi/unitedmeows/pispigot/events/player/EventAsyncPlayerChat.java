package pisi.unitedmeows.pispigot.events.player;

import java.util.IllegalFormatException;
import java.util.Set;

import org.bukkit.entity.Player;

import pisi.unitedmeows.pispigot.events.PlayerEvent;

public class EventAsyncPlayerChat extends PlayerEvent {
	private String message;
	private String format = "<%1$s> %2$s";
	private final Set<Player> recipients;

	public EventAsyncPlayerChat(Player who, String message, Set<Player> players) {
		super(who);
		this.message = message;
		this.recipients = players;
	}

	public String message() {
		return this.message;
	}

	public void message(String message) {
		this.message = message;
	}

	public String format() {
		return this.format;
	}

	public void format(String format) throws IllegalFormatException,NullPointerException {
		try {
			String.format(format, this.player(), this.message);
		}
		catch (RuntimeException ex) {
			ex.fillInStackTrace();
			throw ex;
		}
		this.format = format;
	}

	public Set<Player> recipients() {
		return this.recipients;
	}
}
