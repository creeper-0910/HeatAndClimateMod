/**
 * Copyright (c) defeatedcrow, 2016 URL:http://defeatedcrow.jp/modwiki/Mainpage Please check the License.txt included in the package file of
 * this Mod.
 */

package defeatedcrow.hac.main;

import java.nio.file.Paths;
import java.util.Calendar;

import defeatedcrow.hac.config.ClimateConfig;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.CreativeTabClimate;
import defeatedcrow.hac.core.recipe.RecipeJsonMaker;
import defeatedcrow.hac.food.item.brewing.MicrobeRegister;
import defeatedcrow.hac.main.api.MainAPIManager;
import defeatedcrow.hac.main.config.MainConfig;
import defeatedcrow.hac.main.config.MainCoreConfig;
import defeatedcrow.hac.main.config.ModuleConfig;
import defeatedcrow.hac.main.config.WorldGenConfig;
import defeatedcrow.hac.main.recipes.DCFluidFuelRegister;
import defeatedcrow.hac.main.recipes.DCHeatTreatmentRegister;
import defeatedcrow.hac.main.recipes.DCInfoDataRegister;
import defeatedcrow.hac.main.recipes.FoodBrewingRecipeRegister;
import defeatedcrow.hac.main.recipes.OreDicRegister;
import defeatedcrow.hac.main.tabs.CreativeTabClimateBuild;
import defeatedcrow.hac.main.tabs.CreativeTabClimateContainer;
import defeatedcrow.hac.main.tabs.CreativeTabClimateEquips;
import defeatedcrow.hac.main.tabs.CreativeTabClimateFood;
import defeatedcrow.hac.main.tabs.CreativeTabClimateFoodAdv;
import defeatedcrow.hac.main.tabs.CreativeTabClimateMachine;
import defeatedcrow.hac.main.tabs.CreativeTabClimateMagic;
import defeatedcrow.hac.main.tabs.CreativeTabClimateTool;
import defeatedcrow.hac.main.util.DCChunkloadContoroller;
import defeatedcrow.hac.main.worldgen.vein.VeinTableRegister;
import defeatedcrow.hac.plugin.DCIntegrationCore;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ClimateMain.MOD_ID, name = ClimateMain.MOD_NAME,
		version = ClimateMain.MOD_MAJOR + "." + ClimateMain.MOD_MINOR + "." + ClimateMain.MOD_BUILD,
		dependencies = ClimateMain.MOD_DEPENDENCIES, acceptedMinecraftVersions = ClimateCore.MOD_ACCEPTED_MC_VERSIONS,
		updateJSON = ClimateMain.UPDATE_JSON, certificateFingerprint = ClimateMain.KEY, useMetadata = true)
public class ClimateMain {
	public static final String MOD_ID = "dcs_climate";
	public static final String MOD_NAME = "HeatAndClimateMod";
	public static final int MOD_MAJOR = 3;
	public static final int MOD_MINOR = 9;
	public static final int MOD_BUILD = 7;
	public static final String MOD_DEPENDENCIES = "required-after:dcs_lib@[3.9.0,)";
	public static final String UPDATE_JSON = "https://defeatedcrow.jp/version/heatandclimate.json";
	public static final String KEY = "4cd12b92959105443b7b694fffe0cea9ed004886";

	@SidedProxy(clientSide = "defeatedcrow.hac.main.client.ClientMainProxy",
			serverSide = "defeatedcrow.hac.main.CommonMainProxy")
	public static CommonMainProxy proxy;

	@Instance("dcs_climate")
	public static ClimateMain instance;

	public static final CreativeTabClimate tool = new CreativeTabClimateTool(MOD_ID);
	public static final CreativeTabClimate machine = new CreativeTabClimateMachine(MOD_ID + "_machine");
	public static final CreativeTabClimate food = new CreativeTabClimateFood(MOD_ID + "_food");
	public static final CreativeTabClimate food_adv = new CreativeTabClimateFoodAdv(MOD_ID + "_food_adv");
	public static final CreativeTabClimate build = new CreativeTabClimateBuild(MOD_ID + "_build");
	public static final CreativeTabClimate cont = new CreativeTabClimateContainer(MOD_ID + "_container");
	public static final CreativeTabClimate cloth = new CreativeTabClimateEquips(MOD_ID + "_clothing");
	public static final CreativeTabClimate magic = new CreativeTabClimateMagic(MOD_ID + "_magic");

	public static final Calendar CAL = Calendar.getInstance();
	public static int month = 0;
	public static int day = 0;

	public static final String AUTHOR = "defeatedcrow";

	@EventHandler
	public void construction(FMLConstructionEvent event) {
		// TextureStitch
		proxy.loadConst();
		FluidRegistry.enableUniversalBucket();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// config
		MainConfig.INSTANCE.load(ClimateConfig.configDir);
		// api
		MainAPIManager.fuelRegister = new DCFluidFuelRegister();
		MainAPIManager.heatTreatmentRegister = new DCHeatTreatmentRegister();
		MainAPIManager.veinRegister = VeinTableRegister.INSTANCE;
		MainAPIManager.infoRegister = new DCInfoDataRegister();
		MainAPIManager.microbeRegister = new MicrobeRegister();
		MainAPIManager.brewingRegister = new FoodBrewingRecipeRegister();
		MainAPIManager.isLoaded = true;

		// integration
		DCIntegrationCore.loadedCheck();

		// Material
		proxy.loadMaterial();
		// TileEntity
		proxy.loadTE();
		// Entity
		proxy.loadEntity();
		// potion
		proxy.loadPotion();
		// enchant
		proxy.loadEnchantment();
		// villager
		if (ModuleConfig.village) {
			proxy.loadVillager();
		}
		// achievement
		// AchievementClimate.load();
		OreDicRegister.load();
		// loader
		DCChunkloadContoroller.getInstance().preInit();

		DCIntegrationCore.INSTANCE.loadPre();

		if (ClimateCore.isDebug) {
			RecipeJsonMaker.canUse = false;
			RecipeJsonMaker.canDeprecate = false;
			RecipeJsonMaker.dir = Paths
					.get("F:\\modding\\1.12.1\\hac_main\\src\\main\\resources\\assets\\dcs_climate\\recipes\\");
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// config
		MainCoreConfig.INSTANCE.loadCurrencyItem();
		MainCoreConfig.INSTANCE.loadBlockNames();
		WorldGenConfig.INSTANCE.loadBlockNames();
		WorldGenConfig.INSTANCE.loadGeyserGas();

		// WorldGen
		proxy.loadWorldGen();
		// event
		proxy.loadInit();
		// Recipes
		proxy.loadRecipes();
		// other things
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);

		DCIntegrationCore.INSTANCE.loadInit();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		DCIntegrationCore.INSTANCE.loadPost();

		// WorldGen
		proxy.loadWorldGenPost();

		// date
		month = CAL.get(CAL.MONTH);
		day = CAL.get(CAL.DATE);

		if (!ForgeModContainer.fullBoundingBoxLadders) {
			ForgeModContainer.fullBoundingBoxLadders = true;
		}
	}

}
