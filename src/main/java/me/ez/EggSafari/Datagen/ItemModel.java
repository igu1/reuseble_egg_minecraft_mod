package me.ez.EggSafari.Datagen;

import me.ez.EggSafari.Init;
import me.ez.EggSafari.Main;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModel extends ItemModelProvider {

    public ItemModel(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(Init.EGG_SAFARI_NET.get());
        simpleItem(Init.REUSABLE_EGG_SAFARI_NET.get());

    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Main.MOD_ID,"item/" + item.getRegistryName().getPath()));
    }
}
