package net.fmg793.extmodmenu.mixin;

import net.fmg793.extmodmenu.ExtModMenu;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
	@Inject(method = "render(IIF)V", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/gui/GuiElement;drawString(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V"))
	public void drawLoadedModsString(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
		String modsString = "Mods loaded: " + ExtModMenu.getModCount();
		drawString(textRenderer, modsString, 2, 12, 0x808080);
	}
}
