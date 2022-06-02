package me.ez.EggSafari.Datagen;

import me.ez.EggSafari.Main;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagenerator {
    @SubscribeEvent
    public static void onGatheringData(final GatherDataEvent e){
        DataGenerator datagenerator = e.getGenerator();
        ExistingFileHelper helper = e.getExistingFileHelper();

        datagenerator.addProvider(new ItemModel(datagenerator, Main.MOD_ID, helper));
        datagenerator.addProvider(new RecipeProvider(datagenerator));
        datagenerator.addProvider(new LanguageProvider(datagenerator, Main.MOD_ID, "en_us"));
    }
}
