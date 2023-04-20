package com.bawnorton.bettericon.mixin.client;

import net.minecraft.class_8518;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Mixin(class_8518.class)
public abstract class class_8518Mixin {
    @Inject(method = "method_51419", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void method_51419(ResourcePack resourcePack, String string, CallbackInfoReturnable<InputSupplier<InputStream>> cir, String[] strings, InputSupplier<InputStream> inputSupplier) {
        cir.setReturnValue(() -> MinecraftClient.getInstance().getResourceManager().getResourceOrThrow(new Identifier("bettericon", string)).getInputStream());
    }
}
