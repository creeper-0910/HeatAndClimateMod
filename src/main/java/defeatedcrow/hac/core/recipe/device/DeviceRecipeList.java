package defeatedcrow.hac.core.recipe.device;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.recipe.RecipeTypeDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.recipe.CookingRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

public class DeviceRecipeList {

	public static void init() {
		CookingRecipes.init();
	}

	public static void addSimpleRecipe(RecipeTypeDC group, Item output, DCHeatTier heat, List<Ingredient> input) {
		addSimpleRecipe(group, new ItemStack(output), heat, null, null, input);
	}

	public static void addSimpleRecipe(RecipeTypeDC group, Item output, DCHeatTier heat, DCHumidity hum, DCAirflow air, List<Ingredient> input) {
		addSimpleRecipe(group, new ItemStack(output), heat, hum, air, input);
	}

	public static void addSimpleRecipe(RecipeTypeDC group, ItemStack output, DCHeatTier heat, DCHumidity hum, DCAirflow air, List<Ingredient> input) {
		List<DCHeatTier> heats = Lists.newArrayList();
		List<DCHumidity> hums = Lists.newArrayList();
		List<DCAirflow> airs = Lists.newArrayList();
		if (heat == null) {
			heats.addAll(DCHeatTier.elements());
		} else {
			heats.add(heat);
			if (heat == DCHeatTier.NORMAL) {
				heats.add(DCHeatTier.COOL);
				heats.add(DCHeatTier.WARM);
			} else if (heat.getTier() < 0 && heat != DCHeatTier.ABSOLUTE) {
				heats.add(heat.addTier(-1));
			} else if (heat.getTier() > 0 && heat != DCHeatTier.INFERNO) {
				heats.add(heat.addTier(1));
			}
		}
		if (hum == null) {
			hums.addAll(DCHumidity.elements());
		} else {
			hums.add(hum);
		}
		if (air == null) {
			airs.addAll(DCAirflow.elements());
		} else {
			airs.add(air);
		}
		addMillRecipe(group, output, ItemStack.EMPTY, 0, heats, hums, airs, input);
	}

	// mill
	public static void addMillRecipe(RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, List<Ingredient> in) {
		addRecipe(group, o, sec, secRate, ItemStack.EMPTY, 0, FluidStack.EMPTY, t, h, a, new ArrayList<String>(), in);
	}

	// squeeze, distill, tea
	public static void addFluidRecipe(RecipeTypeDC group, ItemStack o, FluidStack oF, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, List<String> inF, List<Ingredient> in) {
		addRecipe(group, o, ItemStack.EMPTY, 0, ItemStack.EMPTY, 0, oF, t, h, a, inF, in);
	}

	// cooking
	public static void addCookingRecipe(RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, FluidStack oF, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a, List<String> inF, List<Ingredient> in) {
		addRecipe(group, o, sec, secRate, ItemStack.EMPTY, 0, oF, t, h, a, inF, in);
	}

	// cooking2
	public static void addCookingRecipe(RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, FluidStack oF, List<DCHeatTier> t, List<String> inF, List<Ingredient> in) {
		addRecipe(group, o, sec, secRate, ItemStack.EMPTY, 0, oF, t, Lists.newArrayList(), Lists.newArrayList(), inF, in);
	}

	// pulverize
	public static void addPulverizeRecipe(RecipeTypeDC group, ItemStack o, ItemStack sec, int secRate, ItemStack ter, int terRate, FluidStack oF, List<DCHeatTier> t, List<DCHumidity> h, List<DCAirflow> a,
			List<Ingredient> in) {
		addRecipe(group, o, sec, secRate, ter, terRate, oF, t, h, a, new ArrayList<String>(), in);
	}

	// all
	public static void addRecipe(RecipeTypeDC type, ItemStack output, ItemStack sec, int secRate, ItemStack ter, int terRate, FluidStack outF, List<DCHeatTier> heat, List<DCHumidity> hum, List<DCAirflow> air,
			List<String> inF, List<Ingredient> input) {
		ResourceLocation resF = DCUtil.getRes(outF.getFluid()).orElse(new ResourceLocation(ClimateCore.MOD_ID, "main/null_item"));
		ResourceLocation resO = DCUtil.getRes(output.getItem()).orElse(resF);
		String fName = resO.getPath().replace('/', '_');
		DeviceRecipe ret = new DeviceRecipe(type, output, sec, secRate, ter, terRate, outF, heat, hum, air, inF, input);
		DeviceRecipeConfig.addRecipe(fName, ret);
	}

}
