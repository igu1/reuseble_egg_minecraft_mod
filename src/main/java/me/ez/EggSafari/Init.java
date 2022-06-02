package me.ez.EggSafari;

import me.ez.EggSafari.common.items.EggSafari;
import me.ez.EggSafari.common.items.ReusableEggSafariNet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Init {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<EggSafari> EGG_SAFARI_NET = ITEMS.register("egg_safari",
            () -> new EggSafari(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<ReusableEggSafariNet> REUSABLE_EGG_SAFARI_NET = ITEMS.register("reusable_egg_safari",
            () -> new ReusableEggSafariNet(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).rarity(Rarity.EPIC)));

}
