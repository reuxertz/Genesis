package com.reuxertz.genesis.command;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class GenesisCommand extends CommandTreeBase {

    public GenesisCommand() {
        this.addSubcommand(new CommandGene());
    }
    @Override
    public String getName() {
        return "genesis";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.genesis.usage";
    }
}
