package defeatedcrow.hac.plugin;

import defeatedcrow.hac.core.DCLogger;
import net.minecraftforge.fml.common.Loader;

public class DCIntegrationCore {

	public static final DCIntegrationCore INSTANCE = new DCIntegrationCore();

	private DCIntegrationCore() {}

	public static void load() {

		DCPluginFluid.load();

		if (Loader.isModLoaded("BiomesOPlenty")) {
			try {
				DCPluginBoP.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: BiomesOPlenty");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: BiomesOPlenty");
			}
		}

		if (Loader.isModLoaded("forestry")) {
			try {
				DCPluginForestry.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: forestry");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: forestry");
			}
		}

		if (Loader.isModLoaded("IC2")) {
			try {
				DCPluginIC2.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: IC2");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: IC2");
			}
		}

		if (Loader.isModLoaded("schr0tanpopo")) {
			try {
				DCPluginTanpopo.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: schr0tanpopo");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: schr0tanpopo");
			}
		}

		if (Loader.isModLoaded("Mekanism")) {
			try {
				DCPluginMekanism.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: Mekanism");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: Mekanism");
			}
		}

		if (Loader.isModLoaded("JEI")) {
			try {
				DCsJEIPlugin2.load();
				DCLogger.infoLog("dcs_climate", "Successfully loaded mod plugin: JEI");
			} catch (Exception e) {
				DCLogger.infoLog("dcs_climate", "Failed to load mod plugin: JEI");
			}
		}

	}

}
