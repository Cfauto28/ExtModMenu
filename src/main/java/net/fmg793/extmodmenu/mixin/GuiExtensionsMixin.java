package net.fmg793.extmodmenu.mixin;

import ext.client.gui.screen.GuiExtensions;
import ext.client.gui.widget.ButtonSelect;
import net.fmg793.extmodmenu.ExtModMenu;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiExtensions.class)
public class GuiExtensionsMixin extends Screen {
	@Shadow
	private Screen screen;
	
	@Unique
	public int modsStringIndex = 0;
	
	@Overwrite
	public void init() {
		super.init();
		updateButtonStates();
	}

	@Unique
	public void updateButtonStates() {
		this.buttons.clear();
		this.buttons.add(new ButtonSelect(-1, this.width / 2 - 160, this.height / 4 + 148, "<"));
		int i1 = this.buttons.size() - 1;
		if(this.modsStringIndex == 0) {
			((ButtonWidget)this.buttons.get(i1)).active = false;
		}
		this.buttons.add(new ButtonSelect(-2, this.width / 2 + 110, this.height / 4 + 148, ">"));
		int i4 = this.buttons.size() - 1;
		if(this.modsStringIndex >= ExtModMenu.getModCount() - 10) {
			((ButtonWidget)this.buttons.get(i4)).active = false;
		}
		this.buttons.add(new ButtonWidget(0, this.width / 2 - 100, this.height / 4 + 148, "Done"));
	}

	@Inject(method = "buttonClicked(Lnet/minecraft/client/gui/widget/ButtonWidget;)V", at = @At(value = "TAIL"))
	public void extButtonClicked(ButtonWidget button, CallbackInfo ci) {
		if(button.id == -1) {
			if(this.modsStringIndex != 0) {
				this.modsStringIndex -= 10;
				updateButtonStates();
			}
		} else if(button.id == -2) {
			if(!(this.modsStringIndex >= ExtModMenu.getModCount() - 10)) {
				this.modsStringIndex += 10;
				updateButtonStates();
			}
		}
	}

	@Inject(method = "render(IIF)V", at = @At(value = "TAIL"))
	public void loadedModsString(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
        for (int i3 = 0; i3 < (Math.min(this.modsStringIndex + 10, ExtModMenu.getModCount()) - this.modsStringIndex); i3++) {
            int i2 = this.modsStringIndex + i3;
            this.textRenderer.draw("- [Fabric] [" + ExtModMenu.getModBadge(i2) + "] " + ExtModMenu.getModName(i2), 20, 50 + i3 * 16, 0xFFFFFF);
        }
	}
}
