package wraith.colormeoutlines.mixin;


import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.chunk.BlockBufferBuilderStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wraith.colormeoutlines.CustomRenderLayer;

import java.util.Map;

@Mixin(BlockBufferBuilderStorage.class)
public abstract class BlockBufferBuilderStorageMixin {

    @Shadow
    @Final
    private Map<RenderLayer, BufferBuilder> builders;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(CallbackInfo callbackInfo) {
        this.builders.put(CustomRenderLayer.OUTLINE, new BufferBuilder(CustomRenderLayer.OUTLINE.getExpectedBufferSize()));
    }

}