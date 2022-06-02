package me.ez.EggSafari.Datagen;

import me.ez.EggSafari.Init;
import net.minecraft.data.DataGenerator;

public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {

    public LanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
    }

    @Override
    protected void addTranslations() {
        add(Init.EGG_SAFARI_NET.get(), "Safari net");
        add(Init.REUSABLE_EGG_SAFARI_NET.get(), "Reusable Safari net");
    }
}
