package defeatedcrow.hac.core;

import defeatedcrow.hac.core.config.ConfigLoadEventDC;
import defeatedcrow.hac.core.config.CoreConfigDC;
import defeatedcrow.hac.core.event.BiomeBaseTempEventDC;
import defeatedcrow.hac.core.event.BlockUpdateEventDC;
import defeatedcrow.hac.core.event.LivingDropEventDC;
import defeatedcrow.hac.core.event.LivingTickEventDC;
import defeatedcrow.hac.core.event.ServerTickEventDC;
import defeatedcrow.hac.core.json.JsonInit;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmeltingConfig;
import defeatedcrow.hac.core.recipe.smelting.ClimateSmeltingList;
import defeatedcrow.hac.food.FoodProxy;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxyDC {

	public void registerEvent() {
		MinecraftForge.EVENT_BUS.addListener(BiomeBaseTempEventDC::onTemp);
		MinecraftForge.EVENT_BUS.addListener(ServerTickEventDC::onTickEvent);
		MinecraftForge.EVENT_BUS.addListener(LivingTickEventDC::onLivingTick);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onBlockUpdate);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onCropUpdate);
		MinecraftForge.EVENT_BUS.addListener(BlockUpdateEventDC::onBlockPlacement);
		MinecraftForge.EVENT_BUS.addListener(ConfigLoadEventDC::onLoad);
		MinecraftForge.EVENT_BUS.addListener(ConfigLoadEventDC::onFileChange);
		MinecraftForge.EVENT_BUS.addListener(LivingDropEventDC::onDrop);

		FoodProxy.registerEvent();
	}

	public void commonInit() {
		JsonInit.init();
		CoreConfigDC.loadConfig();

		FoodProxy.commonInit();
	}

	public void updatePlayerClimate() {}

	public void registerRecipes() {
		ClimateSmeltingList.init();

		ClimateSmeltingConfig.loadFiles();
		ClimateSmeltingConfig.initFile();
	};

	public boolean keyShiftPushed() {
		return false;
	}

	public boolean keyHUDPushed() {
		return false;
	}

	public boolean keyCharmPushed() {
		return false;
	}

	public void triggerAdvancement(LivingEntity player, String res) {
		if (player instanceof ServerPlayer) {
			ServerPlayer serverplayer = (ServerPlayer) player;
			Advancement adv = serverplayer.server.getAdvancements().getAdvancement(new ResourceLocation(ClimateCore.MOD_ID + ":" + res));
			if (adv != null)
				((ServerPlayer) player).getAdvancements().award(adv, "impossible");
		}
	}

}
