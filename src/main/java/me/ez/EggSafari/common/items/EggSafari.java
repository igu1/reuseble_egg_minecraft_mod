package me.ez.EggSafari.common.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class EggSafari extends AbstractEggSafari{

    public EggSafari(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext use) {
        if (use.getPlayer().getMainHandItem().hasTag()){
            if (use.getPlayer().getMainHandItem().getTag().contains("entity")) {
                ItemStack stack = use.getItemInHand();
                BlockPos clickedPos = use.getClickedPos();
                Level level = use.getLevel();
                assert stack.getTag() != null;
                String EntityTypeString = stack.getTag().getString("entity");
                EntityType<?> CaughtEntityType = EntityType.byString(EntityTypeString).get();
                Entity entity = CaughtEntityType.create(level);
                Mob mob = (Mob) entity;

                if (mob != null) {
                    mob.setHealth(stack.getTag().getFloat("entityHealth"));
                }

                if (mob instanceof Sheep sheep) sheep.setColor(DyeColor.byId(stack.getTag().getInt("varient")));
                if (mob instanceof Llama llama) llama.setVariant(stack.getTag().getInt("varient"));
                if (mob instanceof Parrot parrot) parrot.setVariant(stack.getTag().getInt("varient"));
                if (mob instanceof TropicalFish fish) fish.setVariant(stack.getTag().getInt("varient"));
                if (mob instanceof Cat cat) cat.setCatType(stack.getTag().getInt("varient"));

                if (mob != null) {
                    level.addFreshEntity(mob);
                }
                if (mob != null) {
                    mob.setPos(clickedPos.getX(), clickedPos.getY() + 1, clickedPos.getZ());
                }

                if (stack.getTag().contains("varient")) {
                    stack.removeTagKey("varient");
                }
                stack.removeTagKey("entityHealth");
                stack.removeTagKey("entity");
                stack.shrink(1);
            }
        }
        return super.useOn(use);
    }

}
