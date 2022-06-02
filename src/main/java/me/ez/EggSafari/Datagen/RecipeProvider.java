package me.ez.EggSafari.Datagen;

import me.ez.EggSafari.Init;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> craftingRecipes) {
        ShapedRecipeBuilder.shaped(Init.EGG_SAFARI_NET.get())
                .define('D', Items.DIAMOND)
                .define('G', Items.GOLD_INGOT)
                .define('I', Items.IRON_INGOT)
                .define('B', Items.IRON_BARS)
                .pattern("BDB")
                .pattern("IGI")
                .pattern("BIB")
                .unlockedBy("by_getting_diamond", has(Items.DIAMOND))
                .save(craftingRecipes);

        ShapedRecipeBuilder.shaped(Init.REUSABLE_EGG_SAFARI_NET.get())
                .define('D', Items.DIAMOND_BLOCK)
                .define('G', Items.GOLD_BLOCK)
                .define('I', Items.IRON_BLOCK)
                .define('B', Items.IRON_BARS)
                .pattern("BDB")
                .pattern("IGI")
                .pattern("BIB")
                .unlockedBy("by_getting_diamondBlock", has(Items.DIAMOND_BLOCK))
                .save(craftingRecipes);
    }


}
