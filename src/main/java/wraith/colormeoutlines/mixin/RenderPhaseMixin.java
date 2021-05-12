package wraith.colormeoutlines.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import wraith.colormeoutlines.CustomLineWidth;
import wraith.colormeoutlines.ModCompatConfig;
import wraith.colormeoutlines.ModConfig;

@Mixin(RenderPhase.class)
public class RenderPhaseMixin {

    @ModifyVariable(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderPhase;name:Ljava/lang/String;"), ordinal = 0)
    public Runnable beginAction(Runnable runnable) {
        if (((Object)this) instanceof CustomLineWidth) {
            return () -> {
                if (FabricLoader.getInstance().isModLoaded("cloth-config2")) {
                    RenderSystem.lineWidth(ModCompatConfig.getInstance().width * 0.5F);
                } else {
                    RenderSystem.lineWidth(ModConfig.getInstance().getWidth() * 0.5F);
                }
            };
        }
        return runnable;
    }

    @ModifyVariable(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderPhase;name:Ljava/lang/String;"), ordinal = 1)
    public Runnable endAction(Runnable runnable) {
        if (((Object)this) instanceof CustomLineWidth) {
            return () -> RenderSystem.lineWidth(1.0F);
        }
        return runnable;
    }

}
