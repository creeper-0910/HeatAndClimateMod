package defeatedcrow.hac.food.event;

import defeatedcrow.hac.api.material.IRapidCollectables;
import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.core.material.item.tool.ItemScythe;
import defeatedcrow.hac.core.tag.TagDC;
import defeatedcrow.hac.core.tag.TagUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.block.ClimateCropBaseBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClickEventDC {

	@SubscribeEvent
	public static void onClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getLevel() != null) {
			Level level = event.getLevel();
			Player player = event.getEntity();
			ItemStack item = event.getItemStack();
			BlockState target = event.getLevel().getBlockState(event.getPos());

			// fertilizer
			if (!item.isEmpty() && TagUtil.matchTag("fertilizer", item.getItem()).isPresent()) {
				int m = DCState.getInt(target, BlockStateProperties.MOISTURE);
				int f = DCState.getInt(target, DCState.FERTILE);
				if (target.is(TagDC.BlockTag.FARMLAND) && f < 3) {
					if (!level.isClientSide) {
						if (player instanceof ServerPlayer)
							CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, event.getPos(), item);
						// 肥料アイテムを耕地にまく
						int f2 = f + 1;
						if (TagUtil.matchTag("fertilizer_avd", item.getItem()).isPresent()) {
							f2 = 3;
						}
						BlockState next = FoodInit.FERTILE.get().defaultBlockState().setValue(DCState.FERTILE, f + 1).setValue(BlockStateProperties.MOISTURE, m);
						level.setBlockAndUpdate(event.getPos(), next);
						item.shrink(1);
						level.levelEvent(1505, event.getPos().above(), 0);

					}
					event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));
				}
			}
			// green manure
			else if (!item.isEmpty() && TagUtil.matchTag("hoes", item.getItem()).isPresent()) {
				int m = DCState.getInt(target, DCState.STAGE5);
				if (target.is(TagDC.BlockTag.CROP_GREEN_MANURES) && (m == -1 || m > 1)) {
					// 下のブロック
					BlockState below = level.getBlockState(event.getPos().below());
					if (below.is(BlockTags.DIRT) || below.is(TagDC.BlockTag.FARMLAND)) {
						int f = DCState.getInt(below, DCState.FERTILE);
						if (!level.isClientSide && f < 3) {
							// 緑肥をすき込む
							if (target.getBlock() instanceof ClimateCropBaseBlock) {
								((ClimateCropBaseBlock) target.getBlock()).breakAndDropSeed(level, event.getPos(), target);
							} else {
								level.setBlockAndUpdate(event.getPos(), Blocks.AIR.defaultBlockState());
							}
							BlockState next = FoodInit.FERTILE.get().defaultBlockState().setValue(DCState.FERTILE, f + 1).setValue(BlockStateProperties.MOISTURE, 7);
							level.setBlockAndUpdate(event.getPos().below(), next);
							if (player != null) {
								item.hurtAndBreak(1, player, (c) -> {
									c.broadcastBreakEvent(event.getHand());
								});
							}
							level.playSound(player, event.getPos(), SoundEvents.HOE_TILL, SoundSource.BLOCKS, 0.5F, 1.0F);
							level.levelEvent(1505, event.getPos(), 0);
						}
						event.setCancellationResult(InteractionResult.SUCCESS);
						event.setUseItem(Result.ALLOW);
					}
				} else if (target.getBlock() instanceof ClimateCropBaseBlock) {
					if (!level.isClientSide) {
						// 確実に種を取れる
						if (target.getBlock() instanceof ClimateCropBaseBlock) {
							((ClimateCropBaseBlock) target.getBlock()).breakAndDropSeed(level, event.getPos(), target);
						} else {
							level.setBlockAndUpdate(event.getPos(), Blocks.AIR.defaultBlockState());
						}
						if (player != null) {
							item.hurtAndBreak(1, player, (c) -> {
								c.broadcastBreakEvent(event.getHand());
							});
						}
						level.playSound(player, event.getPos(), SoundEvents.HOE_TILL, SoundSource.BLOCKS, 0.5F, 1.0F);
						level.levelEvent(1505, event.getPos(), 0);
						event.setUseItem(Result.ALLOW);
					}
				}
			} else if (target.getBlock() instanceof IRapidCollectables) {
				// 範囲収穫
				BlockPos.MutableBlockPos mpos = new BlockPos.MutableBlockPos();
				int r = 2;
				if (item.getItem() instanceof TieredItem tiered) {
					r = tiered.getTier().getLevel();
				} else if (item.getItem() instanceof ItemScythe scythe) {
					r = scythe.tier.getLevel();
				}
				boolean consume = false;
				for (int x = -r; x < r + 1; x++) {
					for (int z = -r; z < r + 1; z++) {
						for (int y = r; y >= -r; y--) {
							mpos.set(event.getPos().getX() + x, event.getPos().getY() + y, event.getPos().getZ() + z);
							BlockState s2 = level.getBlockState(mpos);
							Block b2 = s2.getBlock();
							if (b2 instanceof IRapidCollectables rap) {
								if (rap.isCollectable(item)) {
									rap.doCollect(level, mpos, s2, player, item);
									consume = true;
								}
							}
						}
					}
				}

				if (consume) {
					level.playSound(player, event.getPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1.0F);
					if (player != null) {
						item.hurtAndBreak(1, player, (p) -> {
							p.broadcastBreakEvent(event.getHand());
						});
					}
					event.setUseItem(Result.ALLOW);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onClickEntity(PlayerInteractEvent.EntityInteract event) {
		Player player = event.getEntity();
		Entity target = event.getTarget();
		if (player != null && target != null) {
			ItemStack hand = player.getItemInHand(event.getHand());
			if (!DCUtil.isEmpty(hand) && hand.getItem() == FoodInit.FOOD_EMPTY_PACK.get()) {
				if (target instanceof Cow || target instanceof Llama || target instanceof Goat) {
					player.playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
					ItemStack ret = ItemUtils.createFilledResult(hand, player, new ItemStack(FoodInit.FOOD_MILK.get()), false);
					event.setCancellationResult(InteractionResult.SUCCESS);
				}
			}
		}
	}

}
