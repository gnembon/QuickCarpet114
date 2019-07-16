package quickcarpet.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.entity.EntityCategory;
import net.minecraft.server.world.ChunkTicketManager;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import quickcarpet.annotation.Feature;
import quickcarpet.utils.SpawnTracker;

@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManagerMixin {

    @Shadow @Final private ServerWorld world;

    @Shadow @Final private ChunkTicketManager ticketManager;

    /*
    @Feature("spawnTracker")
    @Redirect(
        method = {"tickChunks", "method_20801"},
        at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2IntMap;getInt(Ljava/lang/Object;)I"),
        require = 1, allow = 1
    )
    private int onMobcapCheck(Object2IntMap mobcaps, Object key) {
        EntityCategory category = (EntityCategory) key;
        int levelCount = this.ticketManager.getLevelCount();
        int cap = category.getSpawnCap() * levelCount / (17 * 17);
        int mobsPresent = mobcaps.getInt(key);
        SpawnTracker.registerMobcapStatus(this.world.getDimension().getType(), category, mobsPresent > cap);
        return mobsPresent;
    }*/
}
