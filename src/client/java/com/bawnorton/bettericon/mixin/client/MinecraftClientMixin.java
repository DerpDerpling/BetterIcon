package com.bawnorton.bettericon.mixin.client;

import net.minecraft.SharedConstants;
import net.minecraft.class_8518;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.util.Window;
import net.minecraft.resource.DefaultResourcePack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourcePack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow @Final private Window window;
    @Shadow @Final private DefaultResourcePack defaultResourcePack;
    @Shadow @Final private static Logger LOGGER;

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setIcon(Lnet/minecraft/resource/ResourcePack;Lnet/minecraft/class_8518;)V"))
    private void init(Window instance, ResourcePack resourcePack, class_8518 arg) {
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void afterResourceManagerInit(RunArgs args, CallbackInfo ci) {
        try {
            window.setIcon(defaultResourcePack, SharedConstants.getGameVersion().isStable() ? class_8518.RELEASE : class_8518.SNAPSHOT);
        } catch (IOException var10) {
            LOGGER.error("Couldn't set icon", var10);
        }
    }
}
