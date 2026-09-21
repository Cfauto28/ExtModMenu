package net.fmg793.extmodmenu;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import java.util.ArrayList;
import java.util.List;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;

public class ExtModMenu implements ClientModInitializer {
	public static String modName = "";
	public static List<String> modNameList = new ArrayList<String>();

	public static int getModCount() {
		return modNameList.size();
	}

	public static String getModName(int i) {
		return modNameList.get(i);
	}

	public static String getModBadge(int i) {
		String mod = modNameList.get(i);
		if (mod.startsWith("OSL") || mod.startsWith("Fabric") || mod.startsWith("Mixin") || mod.startsWith("json") || mod.startsWith("Ornithe")) {
			return "LIBRARY";
		} else if (mod.startsWith("Minecraft")) {
			return "CLIENT";
		} else if (mod.startsWith("Java") || mod.startsWith("OpenJDK") || mod.startsWith("Eclipse")) {
			return "RUNTIME";
		}
		return "MOD";
	}

	@Override
	public void initClient() {
		for (ModContainer modContainer : FabricLoader.getInstance().getAllMods()) {
			ModMetadata metadata = modContainer.getMetadata();
			modName = metadata.getName() + " " + metadata.getVersion();
			modNameList.add(modName);
		}
	}
}
