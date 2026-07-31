package net.fmg793.extmodmenu.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import ext.client.gui.screen.GuiExtensions;
import net.fabricmc.loader.api.FabricLoader;
import net.fmg793.extmodmenu.ExtModMenu;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiExtensions.class)
public class GuiExtensionsMixin extends Screen {
	@Inject(method = "render(IIF)V", at = @At(value = "TAIL"))
	public void loadedModsString(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
		for(int i5 = 0; i5 < ExtModMenu.getModCount(); ++i5) {
			textRenderer.draw("- [Fabric] [" + ExtModMenu.getModBadge(i5) + "] " + ExtModMenu.getModName(i5), 20, 60 + i5 * 16, 0xFFFFFF);
		}
	}
}
