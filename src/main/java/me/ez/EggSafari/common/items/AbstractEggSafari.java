package me.ez.EggSafari.common.items;

import me.ez.EggSafari.Main;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractEggSafari extends Item {

    public AbstractEggSafari(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag p_41424_) {
        if (Screen.hasShiftDown()) {
            if (stack.hasTag()) {
                assert stack.getTag() != null;
                list.add(new TextComponent("Entity Inside: " + EntityType.byString(stack.getTag().getString("entity")).get().create(level).getName().getString()).withStyle(ChatFormatting.GREEN));
                Float health = stack.getTag().getFloat("entityHealth");
                list.add(new TextComponent("Health: " + Math.round(health)).withStyle(ChatFormatting.RED));
                if (EntityType.byString(stack.getTag().getString("entity")).get().create(level) instanceof Sheep){
                    DyeColor dye = DyeColor.byId(stack.getTag().getInt("sheepColor"));
                    String color = dye.getName().replace("_", " ").toUpperCase();
                    list.add(new TextComponent("Color: " + color).withStyle(ChatFormatting.GOLD));
                }
            } else {
                list.add(new TextComponent("No Entity Inside").withStyle(ChatFormatting.GRAY));
                list.add(new TextComponent("Only Works In Survival").withStyle(ChatFormatting.GRAY));
                super.appendHoverText(stack, level, list, p_41424_);
            }
        }else {
            list.add(new TextComponent("Hold Shift For Information").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.hasTag() && stack.getTag().contains("entity");
    }


    public DyeColor ItIsSheep(ItemStack stack) {
        return DyeColor.byId(stack.getTag().getInt("sheepColor"));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (!stack.hasTag() || !player.getAbilities().instabuild) {
            if (entity instanceof Mob && !(entity instanceof WitherBoss) && !(entity instanceof EnderDragon) && !(entity instanceof IronGolem)) {
                if (hand == InteractionHand.MAIN_HAND) {
                    try {
                        DyeColor color;
                        if (entity instanceof Sheep sheep) {
                            color = sheep.getColor();
                            stack.getOrCreateTag().putInt("sheepColor", color.getId());
                        }
//                        if (entity instanceof Axolotl axolotl) {
//                            int VarientID = axolotl.getVariant().getId();
//                            stack.getOrCreateTag().putInt("axolotlVarient", VarientID);
//
//                        }
                        stack.getOrCreateTag().putString("entity", entity.getType().getRegistryName().toString());
                        stack.getOrCreateTag().putFloat("entityHealth", entity.getHealth());
                        entity.remove(Entity.RemovalReason.DISCARDED);
                    } catch (Exception e) {
                        Main.LOGGER.error("This Mob is UnCatchable! :D");
                    }

                }
            }
        }
        return super.interactLivingEntity(stack, player, entity, hand);
    }

}
