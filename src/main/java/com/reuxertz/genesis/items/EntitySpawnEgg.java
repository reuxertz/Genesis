package com.reuxertz.genesis.items;

import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.api.items.ItemBase;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.entities.EntityOrganism;
import com.reuxertz.genesis.registry.RegistryObject;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntitySpawnEgg extends ItemBase implements IItemColor {

    public EntitySpawnEgg() {
        super("entitySpawnEgg", CreativeTabs.MISC);
        this.setHasSubtypes(true);
    }

    public int colorMultiplier(ItemStack stack, int tintIndex)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        int color = -1;

        if (tintIndex == 0)
            color = nbt.getInteger("primaryColor");
        if (tintIndex == 1)
            color = nbt.getInteger("secondaryColor");

        int primaryColor = nbt.getInteger("primaryColor");
        int secondaryColor = nbt.getInteger("secondaryColor");

        return color;
    }


    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        //int mode = this.changeMode(stack);
//        if (world.isRemote) {
//            String modeString = "";
//            if (mode == 0) {
//                modeString = "random";
//            } else if (mode == 1) {
//                modeString = "male";
//            } else if (mode == 2) {
//                modeString = "female";
//            }
            //player.sendMessage(new TextComponentString(new LangHelper("spawnegg.genderchange.name").withProperty("mode", I18n.format("gender." + modeString + ".name")).build()));
//        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

//    @Override
//    public String getItemStackDisplayName(ItemStack stack) {
//        //Dinosaur dinosaur = this.getDinosaur(stack);
//
//        //return new LangHelper("item.dino_spawn_egg.name").withProperty("dino", "entity.jurassicraft." + dinosaur.getName().replace(" ", "_").toLowerCase(Locale.ENGLISH) + ".name").build();
//    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subtypes) {

        Genesis.registry.iterate(registryObject -> {
            if (registryObject.entityEntry != null) {

                List<SpeciesRegistry.BreedRegistryObject> breeds = SpeciesRegistry.getBreeds(registryObject.name);
                Map<String, SpeciesRegistry.StateRegistryObject> stateMap = SpeciesRegistry.getSpeciesStates(registryObject.name);

                if (stateMap == null)
                    stateMap = new HashMap<>();

                if (stateMap.size() == 0)
                    stateMap.put("", new SpeciesRegistry.StateRegistryObject("", (entity) -> { }));

                List<SpeciesRegistry.StateRegistryObject> states = new ArrayList<>(stateMap.values());
                for (int i = 0; i < breeds.size(); i++)
                {
                    for (int j = 0; j < states.size(); j++)
                    {
                        NBTTagCompound nbt = new NBTTagCompound();
                        ItemStack itemStack = new ItemStack(this, 1);
                        itemStack.setStackDisplayName("item.entityspawnegg." + registryObject.name);
                        nbt.setString("name", registryObject.name);

                        if (breeds.get(i).breedName == null)
                            continue;

                        nbt.setString("breed", breeds.get(i).breedName);
                        nbt.setString("state", states.get(j).stateName);

                        nbt.setInteger("primaryColor", registryObject.primaryEggColor);
                        nbt.setInteger("secondaryColor", registryObject.secondaryEggColor);

                        itemStack.setTagCompound(nbt);
                        subtypes.add(itemStack);
                    }
                }
            }
        });

//        List<Dinosaur> dinosaurs = new LinkedList<>(EntityHandler.getDinosaurs().values());
//
//        Collections.sort(dinosaurs);
//
//        for (Dinosaur dinosaur : dinosaurs) {
//            if (dinosaur.shouldRegister()) {
//                subtypes.add(new ItemStack(item, 1, EntityHandler.getDinosaurId(dinosaur)));
//            }
//        }
        return;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float x, float y, float z) {
        ItemStack stack = player.getHeldItem(hand);
        if (world.isRemote) {
            return EnumActionResult.SUCCESS;
        } else if (!player.canPlayerEdit(pos.offset(side), side, stack)) {
            return EnumActionResult.PASS;
        } else {
            IBlockState state = world.getBlockState(pos);

            if (state.getBlock() == Blocks.MOB_SPAWNER) {
                TileEntity tile = world.getTileEntity(pos);

                if (tile instanceof TileEntityMobSpawner) {
                    MobSpawnerBaseLogic spawnerLogic = ((TileEntityMobSpawner) tile).getSpawnerBaseLogic();
                    //spawnerLogic.setEntityId(EntityList.getKey(this.getDinosaur(stack).getDinosaurClass()));
                    tile.markDirty();

                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }

                    return EnumActionResult.SUCCESS;
                }
            }

            pos = pos.offset(side);
            double yOffset = 0.0D;

            if (side == EnumFacing.UP && state.getBlock() instanceof BlockFence) {
                yOffset = 0.5D;
            }

            boolean b = this.bFull3D;

            RegistryObject registryObject = Genesis.registry.getRegistryObject(stack.getTagCompound().getString("name"));
            String subspecies = stack.getTagCompound().getString("breed");

            Class<? extends EntityOrganism> entityClass = (Class<? extends EntityOrganism>)(registryObject.entityEntry.getEntityClass());
            double adultMass = SpeciesRegistry.getSpeciesFeature(registryObject.name, SpeciesFeature.FeatureTypes.AdultMass).values.get(0);
            Entity entity = null;
            try {

                entity = entityClass.getConstructor(World.class, RegistryObject.class, String.class, Double.class, Double.class)
                        .newInstance(player.world, registryObject, subspecies, ((Double)1.0d), ((Double)adultMass));

            }
            catch (Exception ex)
            {
                return EnumActionResult.FAIL;
            }

//            entity.setDNAQuality(100);
//
//            int mode = this.getMode(stack);
//            if (mode > 0) {
//                entity.setMale(mode == 1);
//            }
//
//            if (player.isSneaking()) {
//                entity.setAge(0);
//            }

            entity.setPosition(pos.getX(), pos.getY() + 1.0, pos.getZ());
            entity.setLocationAndAngles(pos.getX(), pos.getY() + 1.0, pos.getZ(), MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
            //entity.rotationYawHead = entity.rotationYaw;
            //entity.renderYawOffset = entity.rotationYaw;

            if (entity != null) {
                world.spawnEntity(entity);
            }

            return EnumActionResult.SUCCESS;
        }
    }

    public NBTTagCompound getNBT(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
        }
        stack.setTagCompound(nbt);
        return nbt;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        NBTTagCompound nbt = stack.getTagCompound();

        String line = nbt.getString("breed") + "." + nbt.getString("name");

        if (nbt.getString("state") != "")
            line = nbt.getString("state") + "." + line;

        tooltip.add(line);
    }
}
