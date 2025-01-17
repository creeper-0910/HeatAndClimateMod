package defeatedcrow.hac.machine.material.fluid;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.util.TagKeyDC;
import defeatedcrow.hac.core.material.block.BlockItemDC;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;

public class FluidBlockItemDC extends BlockItemDC {

	private int cap = 1000;

	public FluidBlockItemDC(String regName, Block block, Properties prop, TagKey<Item> pair) {
		super(regName, block, prop, pair);
	}

	public FluidBlockItemDC setCap(int c) {
		cap = c;
		return this;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
		if (!DCUtil.isEmpty(stack) && stack.hasTag()) {
			if (stack.getTag().contains(TagKeyDC.getTankKey(1), 10)) {
				CompoundTag tankTag = stack.getTag().getCompound(TagKeyDC.getTankKey(1));
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(tankTag);
				if (fluid.isEmpty()) {
					MutableComponent com = Component.literal("Tank1 EMPTY");
					list.add(com);
				} else {
					MutableComponent com = Component.literal("Tank1 FLUID: ");
					com.append(fluid.getDisplayName()).append(" " + fluid.getAmount() + "mB").withStyle(ChatFormatting.AQUA);
					list.add(com);
				}
			}
			if (stack.getTag().contains(TagKeyDC.getTankKey(2), 10)) {
				CompoundTag tankTag = stack.getTag().getCompound(TagKeyDC.getTankKey(1));
				FluidStack fluid = FluidStack.loadFluidStackFromNBT(tankTag);
				if (fluid.isEmpty()) {
					MutableComponent com = Component.literal("Tank2 EMPTY");
					list.add(com);
				} else {
					MutableComponent com = Component.literal("Tank2 FLUID: ");
					com.append(fluid.getDisplayName()).append(" " + fluid.getAmount() + "mB").withStyle(ChatFormatting.AQUA);
					list.add(com);
				}
			}
		}
		super.appendHoverText(stack, level, list, flag);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag tag) {
		if (!DCUtil.isEmpty(stack) && stack.hasTag() && stack.getTag().contains(TagKeyDC.getTankKey(2), 10)) {
			return new FluidItemWrapperSided(stack, cap).sidedTank();
		}
		return new FluidItemWrapperSided(stack, cap);
	}

}
