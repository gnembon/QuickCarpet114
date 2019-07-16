package quickcarpet.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.InfestedBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import quickcarpet.annotation.Feature;

//import static quickcarpet.settings.Settings.silverFishDropGravel;

@Feature("silverFishDropGravel")
@Mixin(InfestedBlock.class)
public abstract class InfestedBlockMixin extends Block {
    public InfestedBlockMixin(Settings block$Settings_1) {
        super(block$Settings_1);
    }
    /*

    @Inject(method = "onStacksDropped", at = @At(value = "INVOKE", shift = At.Shift.AFTER,
            target = "Lnet/minecraft/entity/mob/SilverfishEntity;playSpawnEffects()V"))
    private void onOnStacksDropped(BlockState blockState_1, World world_1, BlockPos blockPos_1,
                                   ItemStack itemStack_1, CallbackInfo ci) {
        if (silverFishDropGravel) {
            dropStack(world_1, blockPos_1, new ItemStack(Blocks.GRAVEL));
        }
    }*/
}
