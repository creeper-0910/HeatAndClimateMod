package defeatedcrow.hac.core.client;

import defeatedcrow.hac.core.client.entity.EntityModelLoader;
import defeatedcrow.hac.core.client.entity.model.BlockCabinetModel;
import defeatedcrow.hac.core.client.entity.model.BlockChandelier2Model;
import defeatedcrow.hac.core.client.entity.model.BlockChandelierModel;
import defeatedcrow.hac.core.client.entity.model.BlockLockerModel;
import defeatedcrow.hac.core.client.entity.model.BlockLuggageModel;
import defeatedcrow.hac.core.client.entity.model.ChairBindModel;
import defeatedcrow.hac.core.client.entity.model.ModelMagicFin;
import defeatedcrow.hac.core.client.entity.model.ModelMagicWing;
import defeatedcrow.hac.core.client.entity.model.ModelThinArmor;
import defeatedcrow.hac.core.client.entity.renderer.RenderHarpoon;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererCabinet;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererChandelier;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererLocker;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererLuggage;
import defeatedcrow.hac.core.client.entity.renderer.TileRendererToolHook;
import defeatedcrow.hac.core.client.particle.LeakageParticleDC;
import defeatedcrow.hac.core.client.particle.LightOrbDC;
import defeatedcrow.hac.core.client.particle.SmokeParticleDC;
import defeatedcrow.hac.core.material.BuildInit;
import defeatedcrow.hac.core.material.CoreInit;
import defeatedcrow.hac.core.material.block.building.CabinetTile;
import defeatedcrow.hac.core.material.block.building.ChandelierTile;
import defeatedcrow.hac.core.material.block.building.LockerTile;
import defeatedcrow.hac.core.material.block.building.LuggageTile;
import defeatedcrow.hac.core.material.item.tool.HarpoonItem;
import defeatedcrow.hac.food.client.entity.RenderBottleBeer;
import defeatedcrow.hac.food.client.entity.RenderBottleWine;
import defeatedcrow.hac.food.client.entity.RenderBreadCream;
import defeatedcrow.hac.food.client.entity.RenderBreadSausage;
import defeatedcrow.hac.food.client.entity.RenderBreadSquare;
import defeatedcrow.hac.food.client.entity.RenderCake;
import defeatedcrow.hac.food.client.entity.RenderCasserole;
import defeatedcrow.hac.food.client.entity.RenderDrinkCold;
import defeatedcrow.hac.food.client.entity.RenderDrinkCup;
import defeatedcrow.hac.food.client.entity.RenderFoodBase;
import defeatedcrow.hac.food.client.entity.RenderPlateChicken;
import defeatedcrow.hac.food.client.entity.RenderPlateFish;
import defeatedcrow.hac.food.client.entity.RenderPlateGarlic;
import defeatedcrow.hac.food.client.entity.RenderPlateLegs;
import defeatedcrow.hac.food.client.entity.RenderPlateMeat;
import defeatedcrow.hac.food.client.entity.RenderPlateSteak;
import defeatedcrow.hac.food.client.entity.RenderRicebowl;
import defeatedcrow.hac.food.client.entity.RenderSalad;
import defeatedcrow.hac.food.client.entity.RenderSandwich;
import defeatedcrow.hac.food.client.entity.RenderSoup;
import defeatedcrow.hac.food.client.entity.RenderStew;
import defeatedcrow.hac.food.client.entity.RenderStickBeef;
import defeatedcrow.hac.food.client.entity.RenderStickFish;
import defeatedcrow.hac.food.client.entity.RenderStickMeat;
import defeatedcrow.hac.food.client.entity.RenderSweetpotato;
import defeatedcrow.hac.food.client.entity.RenderTart;
import defeatedcrow.hac.food.client.model.BottleModel_Beer;
import defeatedcrow.hac.food.client.model.BottleModel_Wine;
import defeatedcrow.hac.food.client.model.BowlSoupModel;
import defeatedcrow.hac.food.client.model.BowlStewModel;
import defeatedcrow.hac.food.client.model.BreadCreamModel;
import defeatedcrow.hac.food.client.model.BreadRoundModel;
import defeatedcrow.hac.food.client.model.BreadSausageModel;
import defeatedcrow.hac.food.client.model.BreadSquareModel;
import defeatedcrow.hac.food.client.model.CakeModel;
import defeatedcrow.hac.food.client.model.CasseroleModel;
import defeatedcrow.hac.food.client.model.DrinkCupModel;
import defeatedcrow.hac.food.client.model.DrinkGlassModel;
import defeatedcrow.hac.food.client.model.PlateChickenModel;
import defeatedcrow.hac.food.client.model.PlateFishModel;
import defeatedcrow.hac.food.client.model.PlateGarlicModel;
import defeatedcrow.hac.food.client.model.PlateLegsModel;
import defeatedcrow.hac.food.client.model.PlateMeatModel;
import defeatedcrow.hac.food.client.model.PlateSteakModel;
import defeatedcrow.hac.food.client.model.RiceModel;
import defeatedcrow.hac.food.client.model.SaladModel;
import defeatedcrow.hac.food.client.model.SandwichModel;
import defeatedcrow.hac.food.client.model.StickBeefModel;
import defeatedcrow.hac.food.client.model.StickChickenModel;
import defeatedcrow.hac.food.client.model.StickFishModel;
import defeatedcrow.hac.food.client.model.SweetPotatoModel;
import defeatedcrow.hac.food.client.model.TartModel;
import defeatedcrow.hac.food.material.FoodInit;
import defeatedcrow.hac.food.material.entity.BottleBeerItem;
import defeatedcrow.hac.food.material.entity.BottleWineItem;
import defeatedcrow.hac.food.material.entity.BreadCreamItem;
import defeatedcrow.hac.food.material.entity.BreadRoundItem;
import defeatedcrow.hac.food.material.entity.BreadSausageItem;
import defeatedcrow.hac.food.material.entity.BreadSquareItem;
import defeatedcrow.hac.food.material.entity.CakeItem;
import defeatedcrow.hac.food.material.entity.CasseroleItem;
import defeatedcrow.hac.food.material.entity.CookedSweetpotatoItem;
import defeatedcrow.hac.food.material.entity.DrinkColdItem;
import defeatedcrow.hac.food.material.entity.DrinkCupItem;
import defeatedcrow.hac.food.material.entity.PlateBeefItem;
import defeatedcrow.hac.food.material.entity.PlateChickenItem;
import defeatedcrow.hac.food.material.entity.PlateFishItem;
import defeatedcrow.hac.food.material.entity.PlateGarlicItem;
import defeatedcrow.hac.food.material.entity.PlateLegsItem;
import defeatedcrow.hac.food.material.entity.PlateMeatItem;
import defeatedcrow.hac.food.material.entity.SaladItem;
import defeatedcrow.hac.food.material.entity.SandwichItem;
import defeatedcrow.hac.food.material.entity.StickBeefItem;
import defeatedcrow.hac.food.material.entity.StickFishItem;
import defeatedcrow.hac.food.material.entity.StickMeatItem;
import defeatedcrow.hac.food.material.entity.TartItem;
import defeatedcrow.hac.food.material.entity.potfoods.PorridgeItem;
import defeatedcrow.hac.food.material.entity.potfoods.RiceBowlItem;
import defeatedcrow.hac.food.material.entity.potfoods.SoupItem;
import defeatedcrow.hac.machine.client.entity.BlockChamberFuelModel;
import defeatedcrow.hac.machine.client.entity.BlockChamberIronModel;
import defeatedcrow.hac.machine.client.entity.CableModel;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_A;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_B;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_C;
import defeatedcrow.hac.machine.client.entity.CookingPotModel_Layer;
import defeatedcrow.hac.machine.client.entity.FermentationJarModel_A;
import defeatedcrow.hac.machine.client.entity.FermentationJarModel_B;
import defeatedcrow.hac.machine.client.entity.IBCModel;
import defeatedcrow.hac.machine.client.entity.PortableCanModel;
import defeatedcrow.hac.machine.client.entity.StoneMillModel;
import defeatedcrow.hac.machine.client.entity.TeaPotModel_A;
import defeatedcrow.hac.machine.client.entity.TeaPotModel_B;
import defeatedcrow.hac.machine.client.entity.TeaPotModel_C;
import defeatedcrow.hac.machine.client.entity.TileRendererChamberFuel;
import defeatedcrow.hac.machine.client.entity.TileRendererChamberIron;
import defeatedcrow.hac.machine.client.entity.TileRendererCookingPot;
import defeatedcrow.hac.machine.client.entity.TileRendererCopperCable;
import defeatedcrow.hac.machine.client.entity.TileRendererFermentationJar;
import defeatedcrow.hac.machine.client.entity.TileRendererIBC;
import defeatedcrow.hac.machine.client.entity.TileRendererPipeAlloy;
import defeatedcrow.hac.machine.client.entity.TileRendererPortableCan;
import defeatedcrow.hac.machine.client.entity.TileRendererStoneMill;
import defeatedcrow.hac.machine.client.entity.TileRendererTeaPot;
import defeatedcrow.hac.machine.client.entity.TileRendererWaterPump;
import defeatedcrow.hac.machine.client.entity.WaterPumpModel;
import defeatedcrow.hac.machine.material.MachineInit;
import defeatedcrow.hac.machine.material.block.machine.CookingPotTile;
import defeatedcrow.hac.machine.material.block.machine.FermentationJarTile;
import defeatedcrow.hac.machine.material.block.machine.StoneMillTile;
import defeatedcrow.hac.machine.material.block.machine.TeaPotTile;
import defeatedcrow.hac.machine.material.block.transport.IBCTile;
import defeatedcrow.hac.machine.material.block.transport.PortableCanTile;
import defeatedcrow.hac.magic.client.entity.RenderBindPlant;
import defeatedcrow.hac.magic.client.entity.RenderColorArrow;
import defeatedcrow.hac.magic.client.entity.RenderCrowTurret;
import defeatedcrow.hac.magic.client.entity.RenderEmpty;
import defeatedcrow.hac.magic.client.entity.RenderLightCauldron;
import defeatedcrow.hac.magic.client.model.CrowTurretModel;
import defeatedcrow.hac.magic.client.model.LightCauldronModel;
import defeatedcrow.hac.magic.material.MagicInit;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

public class EntityClientRegister {

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		// TESR
		event.registerLayerDefinition(ChandelierTile.CHALCEDONY.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.FLUORITE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.JET.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.DESERTROSE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.SERPENTINE.getLayerLocation(), BlockChandelierModel::createBodyLayer);
		event.registerLayerDefinition(ChandelierTile.IRON.getLayerLocation(), BlockChandelier2Model::createBodyLayer);

		event.registerLayerDefinition(LuggageTile.NORMAL.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.BLUE.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.BLACK.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.RED.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.GREEN.getLayerLocation(), BlockLuggageModel::createBodyLayer);
		event.registerLayerDefinition(LuggageTile.WHITE.getLayerLocation(), BlockLuggageModel::createBodyLayer);

		event.registerLayerDefinition(CabinetTile.NORMAL.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.BLUE.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.BLACK.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.RED.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.GREEN.getLayerLocation(), BlockCabinetModel::createBodyLayer);
		event.registerLayerDefinition(CabinetTile.WHITE.getLayerLocation(), BlockCabinetModel::createBodyLayer);

		event.registerLayerDefinition(LockerTile.NORMAL.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.BLUE.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.BLACK.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.RED.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.GREEN.getLayerLocation(), BlockLockerModel::createBodyLayer);
		event.registerLayerDefinition(LockerTile.WHITE.getLayerLocation(), BlockLockerModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererChamberIron.DATA.getLayerLocation(), BlockChamberIronModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererChamberFuel.DATA.getLayerLocation(), BlockChamberFuelModel::createBodyLayer);

		event.registerLayerDefinition(PortableCanTile.NORMAL.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.WHITE.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.BLUE.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.BLACK.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.RED.getLayerLocation(), PortableCanModel::createBodyLayer);
		event.registerLayerDefinition(PortableCanTile.GREEN.getLayerLocation(), PortableCanModel::createBodyLayer);

		event.registerLayerDefinition(IBCTile.NORMAL.getLayerLocation(), IBCModel::createBodyLayer);
		event.registerLayerDefinition(IBCTile.INNER.getLayerLocation(), IBCModel::createBodyLayer);

		event.registerLayerDefinition(CookingPotTile.NORMAL.getLayerLocation(), CookingPotModel_A::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.WHITE.getLayerLocation(), CookingPotModel_A::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.BLUE.getLayerLocation(), CookingPotModel_B::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.BLACK.getLayerLocation(), CookingPotModel_C::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.RED.getLayerLocation(), CookingPotModel_A::createBodyLayer);
		event.registerLayerDefinition(CookingPotTile.GREEN.getLayerLocation(), CookingPotModel_B::createBodyLayer);

		event.registerLayerDefinition(TeaPotTile.NORMAL.getLayerLocation(), TeaPotModel_A::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.WHITE.getLayerLocation(), TeaPotModel_C::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.BLUE.getLayerLocation(), TeaPotModel_B::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.BLACK.getLayerLocation(), TeaPotModel_A::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.RED.getLayerLocation(), TeaPotModel_B::createBodyLayer);
		event.registerLayerDefinition(TeaPotTile.GREEN.getLayerLocation(), TeaPotModel_B::createBodyLayer);

		event.registerLayerDefinition(FermentationJarTile.NORMAL.getLayerLocation(), FermentationJarModel_B::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.WHITE.getLayerLocation(), FermentationJarModel_B::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.BLUE.getLayerLocation(), FermentationJarModel_A::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.BLACK.getLayerLocation(), FermentationJarModel_B::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.RED.getLayerLocation(), FermentationJarModel_A::createBodyLayer);
		event.registerLayerDefinition(FermentationJarTile.GREEN.getLayerLocation(), FermentationJarModel_A::createBodyLayer);

		event.registerLayerDefinition(StoneMillTile.NORMAL.getLayerLocation(), StoneMillModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererCopperCable.COPPER.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_OUTLET.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_POWERED.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED_OUTLET.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererCopperCable.COPPER_COATED_POWERED.getLayerLocation(), CableModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererPipeAlloy.BRASS.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererPipeAlloy.BRASS_INPUT.getLayerLocation(), CableModel::createBodyLayer);
		event.registerLayerDefinition(TileRendererPipeAlloy.BRASS_OUTLET.getLayerLocation(), CableModel::createBodyLayer);

		event.registerLayerDefinition(TileRendererWaterPump.TEX.getLayerLocation(), WaterPumpModel::createBodyLayer);

		// Entity
		event.registerLayerDefinition(HarpoonItem.FLINT.getLayerLocation(), TridentModel::createLayer);
		event.registerLayerDefinition(HarpoonItem.STEEL.getLayerLocation(), TridentModel::createLayer);

		event.registerLayerDefinition(EntityModelLoader.INSTANCE.BOOTS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.45F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.LEGGINS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.35F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.SHIRT.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.45F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.JACKET.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.5F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.SUITS.getLayerLocation(), () -> ModelThinArmor.createArmorMesh(new CubeDeformation(0.4F)));
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.HAT.getLayerLocation(), () -> ModelThinArmor.createHatMesh());
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.LONG.getLayerLocation(), () -> ModelThinArmor.createLongMesh());
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.WING.getLayerLocation(), () -> ModelMagicWing.createWingMesh());
		event.registerLayerDefinition(EntityModelLoader.INSTANCE.FIN.getLayerLocation(), () -> ModelMagicFin.createFinMesh());

		event.registerLayerDefinition(RenderBindPlant.PLANT.getLayerLocation(), ChairBindModel::createBodyLayer);
		event.registerLayerDefinition(RenderLightCauldron.TEX.getLayerLocation(), LightCauldronModel::createBodyLayer);
		event.registerLayerDefinition(RenderCrowTurret.TEX.getLayerLocation(), CrowTurretModel::createBodyLayer);

		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ROUND_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_NUTS_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_NUTS_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_CINNAMON_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_CINNAMON_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ANKO_RAW.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadRoundItem.BREAD_ANKO_BAKED.getLayerLocation(), BreadRoundModel::createBodyLayer);
		event.registerLayerDefinition(BreadSquareItem.BREAD_SQUARE_RAW.getLayerLocation(), BreadSquareModel::createBodyLayer);
		event.registerLayerDefinition(BreadSquareItem.BREAD_SQUARE_BAKED.getLayerLocation(), BreadSquareModel::createBodyLayer);
		event.registerLayerDefinition(BreadCreamItem.BREAD_CREAM_RAW.getLayerLocation(), BreadCreamModel::createBodyLayer);
		event.registerLayerDefinition(BreadCreamItem.BREAD_CREAM_BAKED.getLayerLocation(), BreadCreamModel::createBodyLayer);
		event.registerLayerDefinition(BreadSausageItem.BREAD_SAUSAGE_RAW.getLayerLocation(), BreadSausageModel::createBodyLayer);
		event.registerLayerDefinition(BreadSausageItem.BREAD_SAUSAGE_BAKED.getLayerLocation(), BreadSausageModel::createBodyLayer);

		event.registerLayerDefinition(SandwichItem.FRUIT.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.MARMALADE.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.EGG.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.SALAD.getLayerLocation(), SandwichModel::createBodyLayer);
		event.registerLayerDefinition(SandwichItem.SALMON.getLayerLocation(), SandwichModel::createBodyLayer);

		event.registerLayerDefinition(TartItem.TART_APPLE_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_APPLE_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PEACH_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PEACH_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_BERRY_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_BERRY_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_LEMON_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_LEMON_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_COCOA_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_COCOA_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_BAKED.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_RAW.getLayerLocation(), TartModel::createBodyLayer);
		event.registerLayerDefinition(TartItem.TART_PISTACHIO_BAKED.getLayerLocation(), TartModel::createBodyLayer);

		event.registerLayerDefinition(StickBeefItem.BEEF_RAW.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.BEEF_COOKED.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.PORK_RAW.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickBeefItem.PORK_COOKED.getLayerLocation(), StickBeefModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.MUTTON_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.MUTTON_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.CHICKEN_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.CHICKEN_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.OFFAL_RAW.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickMeatItem.OFFAL_COOKED.getLayerLocation(), StickChickenModel::createBodyLayer);
		event.registerLayerDefinition(StickFishItem.FISH_RAW.getLayerLocation(), StickFishModel::createBodyLayer);
		event.registerLayerDefinition(StickFishItem.FISH_COOKED.getLayerLocation(), StickFishModel::createBodyLayer);

		event.registerLayerDefinition(CookedSweetpotatoItem.RAW.getLayerLocation(), SweetPotatoModel::createBodyLayer);
		event.registerLayerDefinition(CookedSweetpotatoItem.COOKED.getLayerLocation(), SweetPotatoModel::createBodyLayer);

		event.registerLayerDefinition(PlateBeefItem.BEEF_RAW.getLayerLocation(), PlateSteakModel::createBodyLayer);
		event.registerLayerDefinition(PlateBeefItem.BEEF_COOKED.getLayerLocation(), PlateSteakModel::createBodyLayer);
		event.registerLayerDefinition(PlateMeatItem.MEAT_RAW.getLayerLocation(), PlateMeatModel::createBodyLayer);
		event.registerLayerDefinition(PlateMeatItem.MEAT_COOKED.getLayerLocation(), PlateMeatModel::createBodyLayer);
		event.registerLayerDefinition(PlateLegsItem.LEGS_RAW.getLayerLocation(), PlateLegsModel::createBodyLayer);
		event.registerLayerDefinition(PlateLegsItem.LEGS_COOKED.getLayerLocation(), PlateLegsModel::createBodyLayer);
		event.registerLayerDefinition(PlateGarlicItem.GARLIC_RAW.getLayerLocation(), PlateGarlicModel::createBodyLayer);
		event.registerLayerDefinition(PlateGarlicItem.GARLIC_COOKED.getLayerLocation(), PlateGarlicModel::createBodyLayer);
		event.registerLayerDefinition(PlateChickenItem.CHICKEN_BIG_RAW.getLayerLocation(), PlateChickenModel::createBodyLayer);
		event.registerLayerDefinition(PlateChickenItem.CHICKEN_BIG_COOKED.getLayerLocation(), PlateChickenModel::createBodyLayer);
		event.registerLayerDefinition(PlateFishItem.FISH_RAW.getLayerLocation(), PlateFishModel::createBodyLayer);
		event.registerLayerDefinition(PlateFishItem.FISH_COOKED.getLayerLocation(), PlateFishModel::createBodyLayer);

		event.registerLayerDefinition(SaladItem.GREEN.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.POTATO.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.NUTS.getLayerLocation(), SaladModel::createBodyLayer);
		event.registerLayerDefinition(SaladItem.MELON.getLayerLocation(), SaladModel::createBodyLayer);

		event.registerLayerDefinition(CasseroleItem.CASSEROLE_GRATIN_SHRIMP_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_GRATIN_SHRIMP_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_SHEPHERDS_PIE_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_SHEPHERDS_PIE_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_DORIA_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_DORIA_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_JANSSONS_FRESTELESE_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_JANSSONS_FRESTELESE_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_MOUSSAKA_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_MOUSSAKA_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_PARMIGIANA_RAW.getLayerLocation(), CasseroleModel::createBodyLayer);
		event.registerLayerDefinition(CasseroleItem.CASSEROLE_PARMIGIANA_BAKED.getLayerLocation(), CasseroleModel::createBodyLayer);

		event.registerLayerDefinition(PorridgeItem.PORRIDGE.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_MILK.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.MUESLI.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SAFFRON.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SQUID.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_BORSCH.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CONSOMME_VEGETABLE.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_MUSHROOM.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SALMON.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SHRIMP.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CULLEN_SKINK.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_KHARCHO.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_TOMYUMGOONG.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_BAKKUTTEH.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_MISO_TOFU.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_MISO_MUSHROOM.getLayerLocation(), BowlStewModel::createBodyLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_MISO_PORK.getLayerLocation(), BowlStewModel::createBodyLayer);

		event.registerLayerDefinition(SoupItem.SOUP_CREAM_POTATO.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PUMPKIN.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_CORN.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SPINACH.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PARSNIP.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SHRIMP.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_EGG.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_CRAB.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_JUTE.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_GASPACHO.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_TARATOR.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_MINESTRONE.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHILIBEANS.getLayerLocation(), BowlSoupModel::createBodyLayer);
		event.registerLayerDefinition(SoupItem.SOUP_SORREL.getLayerLocation(), BowlSoupModel::createBodyLayer);

		event.registerLayerDefinition(CakeItem.BUTTER.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.BERRY.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.CHOCOLATE.getLayerLocation(), CakeModel::createBodyLayer);
		event.registerLayerDefinition(CakeItem.GREENTEA.getLayerLocation(), CakeModel::createBodyLayer);

		event.registerLayerDefinition(RiceBowlItem.NORMAL.getLayerLocation(), RiceModel::createBodyLayer);

		event.registerLayerDefinition(BottleBeerItem.BEER.getLayerLocation(), BottleModel_Beer::createBodyLayer);
		event.registerLayerDefinition(BottleBeerItem.SAKE.getLayerLocation(), BottleModel_Beer::createBodyLayer);
		event.registerLayerDefinition(BottleWineItem.WINE.getLayerLocation(), BottleModel_Wine::createBodyLayer);
		event.registerLayerDefinition(BottleWineItem.WINE_WHITE.getLayerLocation(), BottleModel_Wine::createBodyLayer);

		event.registerLayerDefinition(DrinkColdItem.DRINK_APPLE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_APPLE_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_BERRY.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_BERRY_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE_WHITE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GRAPE_WHITE_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GUAVA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_GUAVA_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_LEMON.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MANDARIN.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_POMELO.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_CITRUS_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MANGO.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MANGO_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MELON.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MELON_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_VEGETABLE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_MILK_SHAKE.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PEACH.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PEACH_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PLUM.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_PLUM_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_TEA_BARLEY.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_TEA_SODA.getLayerLocation(), DrinkGlassModel::createBodyLayer);
		event.registerLayerDefinition(DrinkColdItem.DRINK_TONIC.getLayerLocation(), DrinkGlassModel::createBodyLayer);

		event.registerLayerDefinition(DrinkCupItem.TEA_APPLE.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_APPLE_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BERRY.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BERRY_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLACK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLACK_LEMON.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLACK_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLUE.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_BLUE_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_CHAI.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_COCOA.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_COCOA_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_GREEN.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_GREEN_MILK.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_HIBISCUS.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_MALLOW.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_MALLOW_LEMON.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_MINT.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_ROSEHIP.getLayerLocation(), DrinkCupModel::createBodyLayer);
		event.registerLayerDefinition(DrinkCupItem.TEA_SAFFRON.getLayerLocation(), DrinkCupModel::createBodyLayer);

		// pot layer
		event.registerLayerDefinition(RiceBowlItem.NORMAL_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_MILK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SAFFRON_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.PORRIDGE_SQUID_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.MUESLI_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_BORSCH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CONSOMME_VEGETABLE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_MUSHROOM_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SALMON_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CREAM_SHRIMP_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_CULLEN_SKINK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_KHARCHO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_TOMYUMGOONG_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_BAKKUTTEH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_MISO_TOFU_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_MISO_EGGPLANT_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_MISO_MUSHROOM_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(PorridgeItem.STEW_MISO_PORK_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_POTATO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PUMPKIN_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_CORN_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SPINACH_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_PARSNIP_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CREAM_SHRIMP_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_EGG_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHINESE_CRAB_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_JUTE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_GASPACHO_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_TARATOR_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_MINESTRONE_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_CHILIBEANS_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
		event.registerLayerDefinition(SoupItem.SOUP_SORREL_LAYER.getLayerLocation(), CookingPotModel_Layer::createOvarlayLayer);
	}

	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		// TESR
		event.registerBlockEntityRenderer(BuildInit.CHANDELIER_TILE.get(), TileRendererChandelier::new);
		event.registerBlockEntityRenderer(BuildInit.LUGGAGE_TILE.get(), TileRendererLuggage::new);
		event.registerBlockEntityRenderer(BuildInit.CABINET_TILE.get(), TileRendererCabinet::new);
		event.registerBlockEntityRenderer(BuildInit.LOCKER_TILE.get(), TileRendererLocker::new);
		event.registerBlockEntityRenderer(BuildInit.TOOLHOOK_TILE.get(), TileRendererToolHook::new);
		event.registerBlockEntityRenderer(MachineInit.CHAMBER_BRICK_TILE.get(), TileRendererChamberFuel::new);
		event.registerBlockEntityRenderer(MachineInit.CHAMBER_IRON_TILE.get(), TileRendererChamberIron::new);
		event.registerBlockEntityRenderer(MachineInit.PORTABLE_CAN_TILE.get(), TileRendererPortableCan::new);
		event.registerBlockEntityRenderer(MachineInit.IBC_TILE.get(), TileRendererIBC::new);
		event.registerBlockEntityRenderer(MachineInit.COOKING_POT_TILE.get(), TileRendererCookingPot::new);
		event.registerBlockEntityRenderer(MachineInit.TEA_POT_TILE.get(), TileRendererTeaPot::new);
		event.registerBlockEntityRenderer(MachineInit.FERMANTATION_JAR_TILE.get(), TileRendererFermentationJar::new);
		event.registerBlockEntityRenderer(MachineInit.MILL_TILE.get(), TileRendererStoneMill::new);
		event.registerBlockEntityRenderer(MachineInit.CABLE_COPPER_TILE.get(), TileRendererCopperCable::new);
		event.registerBlockEntityRenderer(MachineInit.PIPE_BRASS_TILE.get(), TileRendererPipeAlloy::new);
		event.registerBlockEntityRenderer(MachineInit.WATER_PUMP_TILE.get(), TileRendererWaterPump::new);

		// Entity
		event.registerEntityRenderer(CoreInit.HARPOON.get(), RenderHarpoon::new);

		event.registerEntityRenderer(FoodInit.BREAD_ROUND.get(), RenderFoodBase::new);
		event.registerEntityRenderer(FoodInit.BREAD_SQUARE.get(), RenderBreadSquare::new);
		event.registerEntityRenderer(FoodInit.BREAD_CREAM.get(), RenderBreadCream::new);
		event.registerEntityRenderer(FoodInit.BREAD_SAUSAGE.get(), RenderBreadSausage::new);
		event.registerEntityRenderer(FoodInit.SANDWICH.get(), RenderSandwich::new);
		event.registerEntityRenderer(FoodInit.SWEETPOTATO.get(), RenderSweetpotato::new);
		event.registerEntityRenderer(FoodInit.STICK_BEEF.get(), RenderStickBeef::new);
		event.registerEntityRenderer(FoodInit.STICK_MEAT.get(), RenderStickMeat::new);
		event.registerEntityRenderer(FoodInit.STICK_FISH.get(), RenderStickFish::new);
		event.registerEntityRenderer(FoodInit.PLATE_STEAK.get(), RenderPlateSteak::new);
		event.registerEntityRenderer(FoodInit.PLATE_MEAT.get(), RenderPlateMeat::new);
		event.registerEntityRenderer(FoodInit.PLATE_LEGS.get(), RenderPlateLegs::new);
		event.registerEntityRenderer(FoodInit.PLATE_BIG_STEAK.get(), RenderPlateGarlic::new);
		event.registerEntityRenderer(FoodInit.PLATE_CHICKEN.get(), RenderPlateChicken::new);
		event.registerEntityRenderer(FoodInit.PLATE_FISH.get(), RenderPlateFish::new);
		event.registerEntityRenderer(FoodInit.SALAD.get(), RenderSalad::new);
		event.registerEntityRenderer(FoodInit.STEW.get(), RenderStew::new);
		event.registerEntityRenderer(FoodInit.SOUP.get(), RenderSoup::new);
		event.registerEntityRenderer(FoodInit.CAKE.get(), RenderCake::new);
		event.registerEntityRenderer(FoodInit.RICE.get(), RenderRicebowl::new);
		event.registerEntityRenderer(FoodInit.BOTTLE_BEERTYPE.get(), RenderBottleBeer::new);
		event.registerEntityRenderer(FoodInit.BOTTLE_WINETYPE.get(), RenderBottleWine::new);
		event.registerEntityRenderer(FoodInit.CUP.get(), RenderDrinkCup::new);
		event.registerEntityRenderer(FoodInit.GLASS.get(), RenderDrinkCold::new);
		event.registerEntityRenderer(FoodInit.CASSEROLE.get(), RenderCasserole::new);
		event.registerEntityRenderer(FoodInit.TART.get(), RenderTart::new);

		event.registerEntityRenderer(MagicInit.ARROW_WHITE_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BLUE_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BLACK_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_RED_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_GREEN_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_ROBBER_ENTITY.get(), RenderColorArrow::new);
		event.registerEntityRenderer(MagicInit.ARROW_BIND_ENTITY.get(), RenderColorArrow::new);

		event.registerEntityRenderer(MagicInit.BIND_PLANT_ENTITY.get(), RenderBindPlant::new);
		event.registerEntityRenderer(MagicInit.BIND_ELECTRIC_ENTITY.get(), RenderBindPlant::new);

		event.registerEntityRenderer(MagicInit.PHOENIX_LIGHT_ENTITY.get(), RenderLightCauldron::new);
		event.registerEntityRenderer(MagicInit.SILKY_FAIRY_ENTITY.get(), RenderEmpty::new);
		event.registerEntityRenderer(MagicInit.CROW_TURRET.get(), RenderCrowTurret::new);
	}

	public static void registerLayers(EntityRenderersEvent.AddLayers event) {

	}

	public static void registerParticle(RegisterParticleProvidersEvent event) {
		event.register(CoreInit.SMOKE.get(), SmokeParticleDC.Provider::new);
		event.register(CoreInit.LIGHT_ORB_WHITE.get(), LightOrbDC.Provider::new);
		event.register(CoreInit.LIGHT_ORB_RED.get(), LightOrbDC.Provider::new);
		event.register(CoreInit.LIGHT_ORB_BLUE.get(), LightOrbDC.Provider::new);
		event.register(CoreInit.LEAKAGE.get(), LeakageParticleDC.Provider::new);
	}

	public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(EntityModelLoader.INSTANCE);
	}

	public static void registerRenderTypes() {
		ItemBlockRenderTypes.setRenderLayer(CoreInit.BRINE.getStillFluid().get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(CoreInit.BRINE.getFlowingFluid().get(), RenderType.translucent());

		ItemBlockRenderTypes.setRenderLayer(CoreInit.HOTSPRING.getStillFluid().get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(CoreInit.HOTSPRING.getFlowingFluid().get(), RenderType.translucent());

		ItemBlockRenderTypes.setRenderLayer(CoreInit.SPARKLING.getStillFluid().get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(CoreInit.SPARKLING.getFlowingFluid().get(), RenderType.translucent());
	}
}
