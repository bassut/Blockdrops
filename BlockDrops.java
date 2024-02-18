package Bassut.BlockDrops;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomBlockDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        // Ensure config.yml exists, else create it with default values
        saveDefaultConfig();

        // Register the event listener
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);

        // Register command to reload configuration
        // Check if the command exists in plugin.yml before setting an executor
        if (this.getCommand("reloadconfig") != null) {
            this.getCommand("reloadconfig").setExecutor(new CommandExecutor() {
                @Override
                public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                    if (sender.hasPermission("customblockdrops.reload")) {
                        reloadConfig();
                        sender.sendMessage("Configuration reloaded.");
                        return true;
                    }
                    sender.sendMessage("You do not have permission to execute this command.");
                    return false;
                }
            });
        } else {
            getLogger().warning("The 'reloadconfig' command has not been defined in plugin.yml.");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
