package de.devversion.authenticationannouncer;

import java.util.HashMap;

import de.devversion.bfcon.core.event.EventHandler;
import de.devversion.bfcon.core.event.Listener;
import de.devversion.bfcon.core.event.player.PlayerOnAuthenticatedEvent;
import de.devversion.bfcon.core.event.player.PlayerOnJoinEvent;
import de.devversion.bfcon.core.event.player.PlayerOnLeaveEvent;
import de.devversion.bfcon.core.plugin.Plugin;
import de.devversion.bfcon.protocol.ProtocolEvent;

public class AuthenticationAnnouncer extends Plugin implements Listener {
	
	private final HashMap<String, String> eaguids = new HashMap<String, String>();

	@Override
	public void onDisable() {
		System.out.println("AuthenticationAnnouncer is now disabled.");
	}

	@Override
	public void onEnable() {
		this.getEventManager().registerListener(this, ProtocolEvent.onJoin, ProtocolEvent.onLeave, ProtocolEvent.onAuthenticated);
		
		System.out.println("AuthenticationAnnouncer is now enabled.");
	}
	
	@EventHandler
	public void onAuthenticated(final PlayerOnAuthenticatedEvent e) {
		if (eaguids.containsKey(e.getSoldiername())) {
			this.getCommandInterface().getAdmin().sayGlobal("Welcome " + e.getSoldiername() + " with the GUID " + eaguids.get(e.getSoldiername()) + " to our server.");
		}
	}

	@EventHandler
	public void onJoin(final PlayerOnJoinEvent e) {
		eaguids.put(e.getSoldierName(), e.getGUID());
	}
	
	@EventHandler
	public void onLeave(final PlayerOnLeaveEvent e) {
		eaguids.remove(e.getSoldierName());
	}
}
