package defeatedcrow.hac.food.material.entity;

import defeatedcrow.hac.api.material.EntityRenderData;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.item.ItemEntityFood;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class StickFishItem extends ItemEntityFood {

	public StickFishItem(String s, int nut, float sat, TagKey<Item> pair) {
		super(s, nut, sat, pair);
	}

	@Override
	public EntityType<?> getType() {
		return FoodInit.STICK_FISH.get();
	}

	@Override
	public EntityRenderData getRenderData(Item item) {
		if (item == FoodInit.STICK_FISH_RAW.get())
			return FISH_RAW;
		if (item == FoodInit.STICK_FISH_COOKED.get())
			return FISH_COOKED;
		return FISH_RAW;
	}

	public static final EntityRenderData FISH_RAW = new EntityRenderData("food/stick_fish_raw", 1F, 0F);
	public static final EntityRenderData FISH_COOKED = new EntityRenderData("food/stick_fish_cooked", 1F, 0F);

}
