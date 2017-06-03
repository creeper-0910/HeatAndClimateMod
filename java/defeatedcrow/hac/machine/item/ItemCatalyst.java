package defeatedcrow.hac.machine.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;

public class ItemCatalyst extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"silver",
			"titanium",
			"bismuth",
			"blaze"
	};

	public ItemCatalyst() {
		super();
		maxMeta = names.length - 1;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/misc/plate_catalyst_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}