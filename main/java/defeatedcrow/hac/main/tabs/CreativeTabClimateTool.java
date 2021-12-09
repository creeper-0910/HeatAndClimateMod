package defeatedcrow.hac.main.tabs;

import defeatedcrow.hac.core.CreativeTabClimate;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabClimateTool extends CreativeTabClimate {

	// クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabClimateTool(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "HeatAndClimate:Tools";
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(MainInit.crowDrill);
	}

}