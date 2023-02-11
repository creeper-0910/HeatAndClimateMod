package defeatedcrow.hac.core.material.effects;

import defeatedcrow.hac.core.ClimateCore;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MobEffectDC extends MobEffect {

	int iconX = 0;
	int iconY = 0;
	final String name;

	public MobEffectDC(String n, MobEffectCategory cat, int color) {
		super(cat, color);
		name = n;
	}

	public String getEffectName() {
		return ClimateCore.MOD_ID + ":" + name;
	}

	public MobEffectDC setIconIndex(int x, int y) {
		iconX = x;
		iconY = y;
		return this;
	}

	public int getIconX() {
		return iconX * 18;
	}

	public int getIconY() {
		return iconY * 18 + 198;
	}

	private static final defeatedcrow.hac.core.client.effect.DCEffectRenderer renderer = new defeatedcrow.hac.core.client.effect.DCEffectRenderer();

	@Override
	public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientMobEffectExtensions> consumer) {
		consumer.accept(renderer);
	}

}
