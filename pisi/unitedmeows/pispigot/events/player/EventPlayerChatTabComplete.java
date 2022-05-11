package pisi.unitedmeows.pispigot.events.player;

import java.util.Collection;

import org.apache.commons.lang3.Validate;
import org.bukkit.entity.Player;

import pisi.unitedmeows.pispigot.events.PlayerEvent;

public class EventPlayerChatTabComplete extends PlayerEvent {
	private final String message;
	private final String lastToken;
	private final Collection<String> completions;

	public EventPlayerChatTabComplete(Player who, String message, Collection<String> completions) {
		super(who);
		Validate.notNull(message, "Message cannot be null");
		Validate.notNull(completions, "Completions cannot be null");
		this.message = message;
		int i = message.lastIndexOf(' ');
		if (i < 0) {
			this.lastToken = message;
		} else {
			this.lastToken = message.substring(i + 1);
		}
		this.completions = completions;
	}

	public String chatMessage() {
		return this.message;
	}

	public String lastToken() {
		return this.lastToken;
	}

	public Collection<String> tabCompletions() {
		return this.completions;
	}
}
