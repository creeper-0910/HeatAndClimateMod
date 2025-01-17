package defeatedcrow.hac.machine.material.block.machine;

import java.util.Optional;

import javax.annotation.Nullable;

import com.google.common.base.Suppliers;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.api.material.IDisplayTile;
import defeatedcrow.hac.api.recipe.IDeviceRecipe;
import defeatedcrow.hac.core.network.packet.message.MsgTileDisplayItemToC;
import defeatedcrow.hac.core.recipe.DCRecipes;
import defeatedcrow.hac.core.util.DCItemUtil;
import defeatedcrow.hac.machine.client.gui.TeaPotMenu;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.fluid.DCTank;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class TeaPotTile extends FermentationJarTile implements IDisplayTile {

	public TeaPotTile(BlockPos pos, BlockState state) {
		super(MachineInit.TEA_POT_TILE.get(), pos, state);
		totalProgress = maxProgressTime();
	}

	/* inventory */

	public final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int id) {
			switch (id) {
			case 0:
				return TeaPotTile.this.currentProgress;
			case 1:
				return TeaPotTile.this.totalProgress;
			default:
				return 0;
			}
		}

		@Override
		public void set(int id, int data) {
			switch (id) {
			case 0:
				TeaPotTile.this.currentProgress = data;
				break;
			case 1:
				TeaPotTile.this.totalProgress = data;
				break;
			}

		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	@Override
	public int getContainerSize() {
		return 7;
	}

	@Override
	protected int[] getTopSlots() {
		return new int[] { 0, 1, 2, 5 };
	}

	@Override
	protected int[] getBottomSlots() {
		return new int[] { 3, 4, 6 };
	}

	@Override
	protected int[] getSideSlots() {
		return new int[] { 0, 1, 2, 3, 4, 5, 6 };
	}

	@Override
	protected int maxInSlot() {
		return 2;
	}

	@Override
	protected int maxProgressTime() {
		return 60;
	}

	/* DeviceRecipe */
	@Override
	public boolean continueProcess(Level level, BlockPos pos, BlockState state) {
		// priority check
		if (recipe != null) {
			NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
			Optional<IDeviceRecipe> check = DCRecipes.getTeaRecipe(Suppliers.ofInstance(currentClimate), inputs, inputTank.getFluid());

			if (check.isPresent() && check.get().getPriority() == recipe.getPriority()) {
				boolean result = inventory.canInsertResult(recipe.getOutput(), maxInSlot() + 1, maxInSlot() + 2) > 0;
				if (recipe.getSecondaryRate() > 0 && inventory.canInsertResult(recipe.getSecondaryOutput(), maxInSlot() + 1, maxInSlot() + 2) == 0) {
					result = false;
				}
				return result;
			}
		}
		return false;
	}

	@Override
	public boolean startProcess(Level level, BlockPos pos, BlockState state) {
		NonNullList<ItemStack> inputs = this.inventory.getSizedList(0, maxInSlot());
		Optional<IDeviceRecipe> check = DCRecipes.getTeaRecipe(Suppliers.ofInstance(currentClimate), inputs, inputTank.getFluid());
		if (check.isPresent()) {
			recipe = check.get();
			this.totalProgress = maxProgressTime();
			return true;
		}
		return false;
	}

	int count = 4;
	private ItemStack display = ItemStack.EMPTY;

	@Override
	public ItemStack getDisplay() {
		return display;
	}

	@Override
	public void setDisplay(ItemStack item) {
		display = item;
	}

	@Override
	public boolean onTickProcess(Level level, BlockPos pos, BlockState state) {
		super.onTickProcess(level, pos, state);
		if (count > 0) {
			count--;
			return false;
		} else {
			count = 4;

			if (!DCItemUtil.isSameItem(display, inventory.getItem(maxInSlot() + 1), false)) {
				display = inventory.getItem(maxInSlot() + 1).copy();
				MsgTileDisplayItemToC.sendToClient((ServerLevel) level, pos, display);
			}
			return false;
		}

	}

	/* outputTankは使用しない */

	// fluid

	@Override
	public int getTanks() {
		return 1;
	}

	@Override
	public DCTank getTank(int id) {
		return inputTank;
	}

	@Override
	public DCTank getTank(Direction dir) {
		return inputTank;
	}

	// cap

	LazyOptional<? extends IFluidHandler> fluidhandler = LazyOptional.of(() -> inputTank);

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && capability == ForgeCapabilities.FLUID_HANDLER) {
			return fluidhandler.cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		fluidhandler.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.fluidhandler = LazyOptional.of(() -> inputTank);
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inv) {
		return new TeaPotMenu(MachineInit.TEA_POT_MENU.get(), i, inv, this, this.dataAccess);
	}

	@Override
	public EntityRenderData getRenderData(Block block) {
		if (block == MachineInit.TEA_POT_BLUE.get())
			return BLUE;
		if (block == MachineInit.TEA_POT_BLACK.get())
			return BLACK;
		if (block == MachineInit.TEA_POT_RED.get())
			return RED;
		if (block == MachineInit.TEA_POT_GREEN.get())
			return GREEN;
		if (block == MachineInit.TEA_POT_WHITE.get())
			return WHITE;
		if (block == MachineInit.TEA_POT_NORMAL.get())
			return NORMAL;
		return NORMAL;
	}

	public static final EntityRenderData WHITE = new EntityRenderData("tile/tea_pot_white", 1F, -0.5F);
	public static final EntityRenderData BLUE = new EntityRenderData("tile/tea_pot_blue", 1F, -0.5F);
	public static final EntityRenderData BLACK = new EntityRenderData("tile/tea_pot_black", 1F, -0.5F);
	public static final EntityRenderData RED = new EntityRenderData("tile/tea_pot_red", 1F, -0.5F);
	public static final EntityRenderData GREEN = new EntityRenderData("tile/tea_pot_green", 1F, -0.5F);
	public static final EntityRenderData NORMAL = new EntityRenderData("tile/tea_pot_normal", 1F, -0.5F);

}
