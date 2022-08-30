package defeatedcrow.hac.machine.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCTileBlockFaced;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.util.DCName;
import defeatedcrow.hac.main.util.MainUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGasBurner extends DCTileBlockFaced implements IHeatTile {
	public BlockGasBurner(Material m, String s, int max) {
		super(Material.ROCK, s, 3);
		this.setHardness(1.5F);
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN;
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (player != null && !player.world.isRemote) {
			ItemStack heldItem = player.getHeldItem(hand);
			TileEntity tile = world.getTileEntity(pos);
			if (!player.isSneaking() && tile instanceof TileGasBurner && hand == EnumHand.MAIN_HAND) {
				boolean flag = false;
				if (!DCUtil.isEmpty(heldItem) && heldItem.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, side)) {
					IFluidHandlerItem cont = heldItem.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, side);
					if (cont != null && cont.drain(1000, false) != null) {
						FluidStack f = cont.drain(1000, false);
						if (MainAPIManager.fuelRegister.isRegistered(f.getFluid()) && f.getFluid().isGaseous()) {
							if (DCFluidUtil.onActivateDCTank(tile, heldItem, world, state, side, player)) {
								world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
								flag = true;
							}
						}
					}
				}
				if (!flag) {
					player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
				}
				return true;
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileGasBurner();
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// block側の気候処理は無し
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos from, BlockPos to) {
		IBlockState state = world.getBlockState(to);
		int m = DCState.getInt(state, DCState.TYPE4);
		if (m >= 0) {
			if ((m & 3) == 1 && (to.equals(from.down()) || to.equals(from.down(2))))
				return DCHeatTier.UHT;
		}
		return DCHeatTier.NORMAL;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = this.getMetaFromState(state) & 3;
		return meta == 1 ? 15 : 0;
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		int m = DCState.getInt(state, DCState.TYPE4);
		if (m >= 0) {
			int power = m & 2;
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1 + power), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, power), 3);
			}
		}
	}

	public static void changePowerState(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int m = DCState.getInt(state, DCState.TYPE4);
		if (m >= 0) {
			int lit = m & 1;
			boolean power = (m & 2) == 0;
			if (power) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, lit + 2), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, lit), 3);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int meta = DCState.getInt(state, DCState.TYPE4);
		return meta == 1 || meta == 3;
	}

	public static boolean isPower(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int meta = DCState.getInt(state, DCState.TYPE4);
		return meta == 0 || meta == 1;
	}
	// redstone

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		if (!world.isRemote) {
			boolean flag = world.isBlockPowered(pos);
			if (flag || block.getDefaultState().canProvidePower()) {
				int m = DCState.getInt(state, DCState.TYPE4);
				int lit = m & 1;
				boolean flag2 = m == 1;
				boolean power = (m & 2) != 0;
				if (flag && !power) {
					world.setBlockState(pos, state.withProperty(DCState.TYPE4, lit + 2), 3);
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.6F);
				} else if (!flag && power) {
					world.setBlockState(pos, state.withProperty(DCState.TYPE4, lit), 3);
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, 0.5F);
				}
			}
		}
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.FUEL_GAS.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.RED.toString() + "UHT");
			tooltip.add(DCName.RANGE.getLocalizedName() + ": " + I18n.format("dcs.tip.upper_only"));
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(DCName.DRAIN_SIDED_TANK.getLocalizedName());
			tooltip.add(DCName.RS.getLocalizedName() + ": " + DCName.TURN_OFF.getLocalizedName());
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}
	// redstone

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return MainUtil.calcTankRedstone(worldIn.getTileEntity(pos));
	}
}
