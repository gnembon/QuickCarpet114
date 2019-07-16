package quickcarpet.settings;

import carpet.settings.ParsedRule;
import net.minecraft.Bootstrap;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.dimension.DimensionType;
import quickcarpet.QuickCarpet;
import quickcarpet.annotation.BugFix;
import quickcarpet.feature.PlaceBlockDispenserBehavior;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;

import static carpet.settings.RuleCategory.*;

import carpet.settings.Rule;
import carpet.settings.Validator;
import quickcarpet.utils.Messenger;

public class Settings {
    //public static final CoreSettingsManager MANAGER = new CoreSettingsManager(Settings.class);

    /*
    @Rule(desc = "Enables /tick command to control game speed", category = COMMANDS)
    public static boolean commandTick = true;
    */

    @Rule(desc = "Enables /ping for players to get their ping", category = COMMAND)
    public static boolean commandPing = true;

    @Rule(
            desc = "Enables /carpetfill command",
            extra = "This is a replica of the /fill command for fillUpdates and fillLimits",
            category = COMMAND
    )
    public static boolean commandCarpetFill = true;

    @Rule(
            desc = "Enables /carpetclone command",
            extra = "This is a replica of the /clone command for fillUpdates and fillLimits",
            category = COMMAND
    )
    public static boolean commandCarpetClone = true;

    @Rule(
            desc = "Enables /carpetsetblock command",
            extra = "This is a replica of the /setblock command for fillUpdates and fillLimits",
            category = COMMAND
    )
    public static boolean commandCarpetSetBlock = true;

    /*
    @Rule(desc = "Enables /player command to control/spawn players", category = COMMANDS)
    public static boolean commandPlayer = true;

    @Rule(desc = "Enables /log command to monitor events in the game via chat and overlays", category = COMMANDS)
    public static boolean commandLog = true;

    @Rule(desc = "Enables /spawn command for spawn tracking", category = COMMANDS)
    public static boolean commandSpawn = true;

    @Rule(
            desc = "Enables /c and /s commands to quickly switch between camera and survival modes",
            extra = "/c and /s commands are available to all players regardless of their permission levels",
            category = COMMANDS
    )
    public static boolean commandCameramode = true;

    @CreativeDefault("false")
    @Rule(desc = "fill/clone/setblock and structure blocks cause block updates", category = CREATIVE)
    public static boolean fillUpdates = true;

    @CreativeDefault("500000")
    @Rule(
            desc = "Customizable fill/clone volume limit",
            options = {"32768", "250000", "1000000"},
            validator = Validator.Positive.class,
            category = CREATIVE
    )
    public static int fillLimit = 32768;

    @CreativeDefault
    @SurvivalDefault
    @Rule(
            desc = "Hoppers pointing to wool will count items passing through them",
            extra = {
                    "Enables /counter command, and actions while placing red and green carpets on wool blocks",
                    "Use /counter <color?> reset to reset the counter, and /counter <color?> to query",
                    "In survival, place green carpet on same color wool to query, red to reset the counters",
                    "Counters are global and shared between players, 16 channels available",
                    "Items counted are destroyed, count up to one stack per tick per hopper"
            },
            category = COMMANDS
    )
    public static boolean hopperCounters = false;

    @Rule(desc = "Explosions won't destroy blocks", category = TNT)
    public static boolean explosionNoBlockDamage = false;

    @Rule(desc = "Removes random TNT momentum when primed and set to false", category = TNT)
    public static boolean tntPrimeMomentum = true;

*/
    @Rule(desc = "Sets the horizontal random angle on TNT for debugging of TNT contraptions", category = CREATIVE, options = "-1", validate = TNTAngle.class)
    public static double tntHardcodeAngle = -1;



    public static class TNTAngle extends Validator<Double> {
        @Override
        public Double validate(ServerCommandSource source, ParsedRule<Double> currentRule, Double newValue, String string) {
            if (newValue == -1) return newValue;
            if (newValue >= 0 && newValue < 360) return newValue;
            Messenger.m(source, "r Must be in the range [0,360) or -1 for default");
            return null;
        }
    }


    /*
    @Rule(desc = "Silverfish drop a gravel item when breaking out of a block", category = {FEATURE, EXPERIMENTAL})
    public static boolean silverFishDropGravel = false;

    @Rule(desc = "Shulkers will respawn in end cities", category = {FEATURE, EXPERIMENTAL})
    public static boolean shulkerSpawningInEndCities = false;

    @CreativeDefault
    @Rule(
            desc = "Portals won't let a creative player go through instantly",
            extra = "Holding obsidian in either hand won't let you through at all",
            category = CREATIVE
    )
    public static boolean portalCreativeDelay = false;
    */

    @Rule(desc = "Fire charges from dispensers convert cobblestone to netherrack", category = {FEATURE, EXPERIMENTAL})
    public static boolean fireChargeConvertsToNetherrack = false;

    @Rule(desc = "Automatic crafting table", category = {FEATURE, EXPERIMENTAL})
    public static boolean autoCraftingTable = false;

    /*
    @Rule(desc = "Pistons can push block entities, like hoppers, chests etc.", category = {FEATURE, EXPERIMENTAL})
    public static boolean movableBlockEntities = false;

    @Rule(
            desc = "Empty shulker boxes can stack to 64 when dropped on the ground",
            extra = "To move them around between inventories, use shift click to move entire stacks",
            category = SURVIVAL
    )
    public static boolean stackableShulkerBoxes = false;

    @Rule(desc = "Optimizes spawning", category = {OPTIMIZATIONS, EXPERIMENTAL}, bug = @BugFix(value = "MC-151802", fixVersion = "1.14.3-pre1 (partial)"))
    public static boolean optimizedSpawning = false;
*/
    @Rule(desc = "If a living entity dies on sand with fire on top the sand will convert into soul sand", category = {FEATURE, EXPERIMENTAL})
    public static boolean mobInFireConvertsSandToSoulsand = false;

    @Rule(desc = "Cobblestone crushed by falling anvils makes sand", category = {FEATURE, EXPERIMENTAL})
    public static boolean renewableSand = false;

    @Rule(desc = "Dispensers can place most blocks", category = {EXPERIMENTAL, FEATURE})
    public static PlaceBlockDispenserBehavior.Option dispensersPlaceBlocks = PlaceBlockDispenserBehavior.Option.FALSE;

    /*
    @Rule(desc = "Piston push limit", category = CREATIVE, options = {"10", "12", "14", "100"}, validator = Validator.NonNegative.class)
    public static int pushLimit = 12;

    @Rule(desc = "Rail power limit", category = CREATIVE, options = {"9", "15", "30"}, validator = Validator.Positive.class)
    public static int railPowerLimit = 9;

*/
    @Rule(desc = "1.8 double retraction from pistons.", category = EXPERIMENTAL, extra = {
            "Gives pistons the ability to double retract without side effects."
    })//, bug = @BugFix("MC-88959"))
    public static boolean doubleRetraction = false;

    /*
    @Rule(desc = "Size of spawn chunks", extra = {
            "Like render distance (11 -> 23x23 actively loaded).",
            "Be aware that a border of 11 chunks will stay loaded around that, once those chunks are loaded somehow.",
            "Higher levels need lots of RAM (up to 7569 chunks loaded with level 32)"
    }, category = EXPERIMENTAL, onChange = SpawnChunkLevel.class, validator = SpawnChunkLevel.class)
    public static int spawnChunkLevel = 11;

    public static class SpawnChunkLevel implements ChangeListener<Integer>, Validator<Integer> {
        @Override
        public void onChange(ParsedRule<Integer> rule, Integer previous) {
            int newValue = rule.get();
            if (newValue == previous) return;
            ServerWorld overworld = QuickCarpet.minecraft_server.getWorld(DimensionType.OVERWORLD);
            if (overworld != null) {
                ChunkPos centerChunk = new ChunkPos(overworld.getSpawnPos());
                ServerChunkManager chunkManager = (ServerChunkManager) overworld.getChunkManager();
                chunkManager.removeTicket(ChunkTicketType.START, centerChunk, previous, Unit.INSTANCE);
                chunkManager.addTicket(ChunkTicketType.START, centerChunk, newValue, Unit.INSTANCE);
            }
        }

        @Override
        public Optional<String> validate(Integer value) {
            if (value < 1 || value > 32) return Optional.of("Can only be between 1 and 32");
            return Optional.empty();
        }
    }
    */
    @Rule(desc = "Obsidian surrounded by 6 lava sources has a chance of converting to lava", category = {EXPERIMENTAL, FEATURE})
    public static boolean renewableLava = false;


    /*
    @Rule(desc = "Players can flip and rotate blocks when holding cactus", category = {CREATIVE, SURVIVAL}, extra = {
            "Doesn't cause block updates when rotated/flipped",
            "Applies to pistons, observers, droppers, repeaters, stairs, glazed terracotta etc..."
    })


    @CreativeDefault
    @SurvivalDefault

    */
    public static boolean flippinCactus = false;

    @Rule(desc = "Phantoms don't ignore the mobcap.", category = {SURVIVAL, BUGFIX, EXPERIMENTAL})
    public static boolean phantomsRespectMobcap = false;

    @Rule(desc = "Fixes duping via zombie conversion", category = {BUGFIX, EXPERIMENTAL})// bug = @BugFix(value = "MC-152636", fixVersion = "1.14.4-pre1"))
    @BugFixDefault
    public static boolean conversionDupingFix = false;
    /*
    @Rule(desc = "Coral structures will grow with bonemeal from coral plants", category = FEATURE)
    public static boolean renewableCoral = false;
*/
    @Rule(desc = "Optimizes random ticks for fluids", extra = {
            "Testing showed around 2-3mspt improvement in regular worlds",
            "Needs reloading of chunks to be effective"
    }, category = {OPTIMIZATION, EXPERIMENTAL})
    public static boolean optimizedFluidTicks = false;

    /*
    public static void main(String[] args) throws FileNotFoundException {
        Bootstrap.initialize();
        MANAGER.parse();
        MANAGER.dump(new FileOutputStream(args.length > 0 ? args[0] : "rules.md"));
    }
    */
}
