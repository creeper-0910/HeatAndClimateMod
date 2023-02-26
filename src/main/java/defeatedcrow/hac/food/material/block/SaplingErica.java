package defeatedcrow.hac.food.material.block;

import java.util.List;
import java.util.Optional;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.ImmutableList;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.crop.CropTier;
import defeatedcrow.hac.api.crop.CropType;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.food.material.FoodInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SaplingErica extends SaplingBaseBlock {

	public SaplingErica(CropTier t) {
		super(t);
	}

	/* ICropData */

	@Override
	public CropType getFamily() {
		return CropType.ERICA;
	}

	@Override
	public Item getCropItem(CropTier t) {
		switch (t) {
		case COMMON:
			return FoodInit.CROP_ER_RHODODENDRON.get();
		case RARE:
			return FoodInit.CROP_ER_BLUEBERRY.get();
		default:
			return FoodInit.CROP_ER_HEATH.get();
		}
	}

	@Override
	public Optional<Block> getMutationTarget(CropTier t) {
		switch (t) {
		case WILD:
			return Optional.of(FoodInit.BLOCK_ER_HEATH.get());
		case COMMON:
			return Optional.of(FoodInit.BLOCK_ER_RHODODENDRON.get());
		case RARE:
			return Optional.of(FoodInit.BLOCK_ER_BLUEBERRY.get());
		default:
			return Optional.empty();
		}
	}

	@Override
	public List<SoilType> getSoilTypes(CropTier t) {
		return ImmutableList.of(SoilType.FARMLAND, SoilType.DIRT);
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(CropTier t) {
		if (t == CropTier.COMMON) {
			return ImmutableList.of(DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM, DCHeatTier.HOT);
		}
		return ImmutableList.of(DCHeatTier.FROSTBITE, DCHeatTier.COLD, DCHeatTier.COOL, DCHeatTier.NORMAL, DCHeatTier.WARM);
	}

	@Override
	public List<DCHumidity> getSuitableHum(CropTier t) {
		return ImmutableList.of(DCHumidity.DRY, DCHumidity.NORMAL, DCHumidity.WET);
	}

	@Override
	public List<DCAirflow> getSuitableAir(CropTier t) {
		return ImmutableList.of(DCAirflow.NORMAL, DCAirflow.FLOW, DCAirflow.WIND);
	}

	@Override
	public List<String> getGeneratedBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("CONIFEROUS", "COLD", "MOUNTAIN");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public List<String> getAvoidBiomeTag(CropTier t) {
		switch (t) {
		case WILD:
			return ImmutableList.of("HOT", "OCEAN");
		default:
			return Lists.newArrayList();
		}
	}

	@Override
	public String getSpeciesName(CropTier tier) {
		if (tier == CropTier.COMMON)
			return "rhododendron";
		if (tier == CropTier.RARE)
			return "blueberry";
		return "heath";
	}

	@Override
	protected void onGrowingTree(Level level, BlockPos pos, BlockState state, CropTier t) {
		level.random.nextInt(2);
		int h = 1;
		int r = 2;
		BlockState log = FoodInit.LOG_BH_COMMON.get().defaultBlockState();
		BlockState leaves = FoodInit.LEAVES_ER_HEATH.get().defaultBlockState();
		if (t == CropTier.COMMON) {
			leaves = FoodInit.LEAVES_ER_RHODODENDRON.get().defaultBlockState().setValue(DCState.FLAG, true);
			h = 2 + level.random.nextInt(3);
		} else if (t == CropTier.RARE) {
			leaves = FoodInit.LEAVES_ER_BLUEBERRY.get().defaultBlockState();
			h = 1 + level.random.nextInt(2);
		}

		int m = ((LeavesCropBlockDC) leaves.getBlock()).getSeasonLeafStage(level, pos);
		leaves = leaves.setValue(DCState.STAGE6, m);

		// 安全性チェック
		if (pos.getY() + h > level.getMaxBuildHeight())
			return;

		if (!level.isClientSide) {
			if (replaceCheck(level, pos, h))
				return;

			if (t == CropTier.COMMON) {
				growTree(level, pos, h, log, leaves);
			} else {
				for (int i = 0; i < h; i++) {
					level.setBlock(pos.above(i), leaves, 2);
				}
			}
		}
	}

}
