package net.themodcraft.tmcserverstartcloudv2;

import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.concurrent.TimeUnit;

public class ServerStatusListener implements Listener {

    private final TMCServerStartCloudV2 plugin;

    public ServerStatusListener(TMCServerStartCloudV2 plugin) {
        this.plugin = plugin;
    }

    public void registerListener() {
        plugin.getProxy().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onServerConnect(ServerConnectEvent event) {
        String serverName = event.getTarget().getName();
        String serverIP = event.getTarget().getAddress().getAddress().getHostAddress();

        // Server is starting
        System.out.println("Server " + serverName + " with IP " + serverIP + " is starting.");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onServerDisconnect(ServerDisconnectEvent event) {
        String serverName = event.getTarget().getName();
        String serverIP = event.getTarget().getAddress().getAddress().getHostAddress();

        // Server has stopped
        System.out.println("Server " + serverName + " with IP " + serverIP + " has stopped.");

        // Close terminal window associated with the server after 5 minutes
        String terminalId = ""; // Retrieve terminal ID here
        TerminalWindowManager.setServerOfflineTime(serverName);
        plugin.getProxy().getScheduler().schedule(plugin, () -> {
            TerminalWindowManager.closeTerminalIfServerOffline(serverName, terminalId);
        }, 5 * 60, TimeUnit.SECONDS);
    }
}
