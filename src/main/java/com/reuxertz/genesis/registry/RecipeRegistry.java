package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.items.tools.BaseHammer;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {

    protected List<IRecipe> recipies = new ArrayList<IRecipe>();

    public static void RegisterMetalTools(Item nugget, Item ingot, ItemPickaxe pickaxe, ItemHoe hoe, ItemSpade shovel, ItemAxe axe, BaseHammer hammer, Item chain)
    {
        GameRegistry.addShapedRecipe(new ResourceLocation("SugarBeets"), new ResourceLocation("recipes"), new ItemStack(ingot, 1), "AAA", "AAA", "AAA", 'A', nugget);
        GameRegistry.addShapelessRecipe(new ResourceLocation("SugarBeets"), new ResourceLocation("recipes"), new ItemStack(ingot, 1), Ingredient.fromStacks(new ItemStack(nugget, 9)));
        //GameRegistry.addShapelessRecipe(new ResourceLocation("SugarBeets"), new ResourceLocation("recipes"), new ItemStack(nugget, 9), ingot);


        //GameRegistry.addShapedRecipe(new ResourceLocation("SugarBeets"), new ResourceLocation("recipes"),new ItemStack(Items.SUGAR, 2), "BBB", 'B', Items.BEETROOT);
    }



}
