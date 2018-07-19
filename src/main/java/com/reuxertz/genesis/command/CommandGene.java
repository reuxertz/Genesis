package com.reuxertz.genesis.command;

import com.reuxertz.genesisAPI.organics.GeneHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandGene extends CommandTreeBase {

    public CommandGene() {
        this.addSubcommand(new SetGene());
        this.addSubcommand(new ShowGene());
    }

    @Override
    public String getName() {
        return "gene";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "gene usuange";//TODO: localize this
    }

    public static class ShowGene extends CommandBase {
        @Override
        public String getName() {
            return "show";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "commands.gene.show.usuage";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            ItemStack stack = ((EntityLivingBase)sender.getCommandSenderEntity()).getHeldItemMainhand();
            if(stack.getTagCompound() != null) {
                if(args.length == 0) {
                    NBTTagCompound nbt = stack.getTagCompound().getTagList("genomeStack", 10).getCompoundTagAt(0);//TODO: remove tag list (not me)
                    nbt.getKeySet().stream()
                            .filter(string -> string.matches("sequence\\d+"))
                            .filter(string -> !nbt.getString(string).isEmpty())
                            .forEach(string -> sender.sendMessage(new TextComponentString(nbt.getString(string))));
                } else {
                    String sequence = args[0];
                    NBTTagCompound nbt = stack.getTagCompound().getTagList("genomeStack", 10).getCompoundTagAt(0);//TODO: remove tag list (not me)
                    String current = nbt.getString("sequence" + sequence);
                    if(current.isEmpty()) {
                        throw new CommandException("commands.gene.set.error.emptysequence", sequence);
                    }
                    sender.sendMessage(new TextComponentString(current));
                }
            } else {
                throw new CommandException("commands.gene.show.error.nosequence");
            }
        }
    }

    public static class SetGene extends CommandBase {
        @Override
        public String getName() {
            return "set";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "commands.gene.set.usuage";//TODO lang
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            if(args.length < 3) {
                throw new WrongUsageException(this.getUsage(sender));
            }
            ItemStack stack = ((EntityLivingBase)sender.getCommandSenderEntity()).getHeldItemMainhand();
            if(stack.getTagCompound() != null) {
                String sequence = args[0];
                NBTTagCompound nbt = stack.getTagCompound().getTagList("genomeStack", 10).getCompoundTagAt(0);//TODO: remove tag list (not me)
                String current = nbt.getString("sequence" + sequence);
                if(current.isEmpty()) {
                    throw new CommandException("commands.gene.set.error.emptysequence", sequence);
                }
                int setpoint = CommandGene.parseInt(args[1], 0, current.length() - 1);
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < current.length(); i++) {
                    if(i == setpoint) {
                        String gene = args[2];
                        searchLoop: {
                            for(char c : GeneHelper.letters.toCharArray()) {
                                if(String.valueOf(c).equals(gene)) {
                                    break searchLoop;
                                }
                            }
                            throw new CommandException("commands.gene.set.error.unknowngene", gene);
                        }
                        stringBuilder.append(gene);
                    } else {
                        stringBuilder.append(current.toCharArray()[i]);
                    }
                }
                String out = stringBuilder.toString();
                nbt.setString("sequence" + sequence, out);
                CommandGene.notifyCommandListener(sender, this, "commands.gene.set.succeed", sequence, setpoint, current.toCharArray()[setpoint], out.toCharArray()[setpoint]);
            } else {
                throw new CommandException("commands.gene.set.error.genomenotfound");
            }
        }
    }
}
