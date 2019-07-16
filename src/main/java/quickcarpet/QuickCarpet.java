package quickcarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quickcarpet.commands.*;
import quickcarpet.helper.TickSpeed;
import quickcarpet.logging.LoggerManager;
import quickcarpet.module.ModuleHost;
import quickcarpet.module.QuickCarpetModule;
import quickcarpet.network.PluginChannelManager;
import quickcarpet.network.channels.RulesChannel;
import quickcarpet.network.channels.StructureChannel;
import quickcarpet.pubsub.PubSubManager;
import quickcarpet.pubsub.PubSubMessenger;
import quickcarpet.settings.Settings;
import quickcarpet.utils.CarpetProfiler;
import quickcarpet.utils.CarpetRegistry;
import quickcarpet.utils.HUDController;

import java.util.Set;
import java.util.TreeSet;

public final class QuickCarpet implements ModInitializer, CarpetExtension
{
    static
    {
        CarpetServer.manageExtension(new QuickCarpet());
    }


    private static final Logger LOG = LogManager.getLogger();
    public static final PubSubManager PUBSUB = new PubSubManager();

    private static QuickCarpet instance = new QuickCarpet();

    public static MinecraftServer minecraft_server;
    public TickSpeed tickSpeed;

    @Environment(EnvType.CLIENT)
    public QuickCarpetClient client;
    public PluginChannelManager pluginChannels;
    //public final Set<QuickCarpetModule> modules = new TreeSet<>();
    private final PubSubMessenger pubSubMessenger = new PubSubMessenger(PUBSUB);
    //private CommandDispatcher<ServerCommandSource> dispatcher;
    //public LoggerManager loggers;

    // Fabric on dedicated server will call getInstance at return of DedicatedServer::<init>(...)
    // new CommandManager(...) is before that so QuickCarpet is created from that
    // Client will call getInstance at head of MinecraftClient::init()
    public QuickCarpet() {
        instance = this;
    }

    public static QuickCarpet getInstance() {
        return instance;
    }

    /*public void init(MinecraftServer server) {

    }*/

    @Override
    public void onServerLoaded(MinecraftServer server) {
        minecraft_server = server;
        //tickSpeed = new TickSpeed(false);
        //loggers = new LoggerManager();
        pluginChannels = new PluginChannelManager(server);
        pluginChannels.register(pubSubMessenger);
        pluginChannels.register(new StructureChannel());
        pluginChannels.register(new RulesChannel());
        //for (QuickCarpetModule m : modules) m.onServerInit(server);
        //Settings.MANAGER.init(server);
        //for (QuickCarpetModule m : modules) m.onServerLoaded(server);
        //registerCarpetCommands();
    }

    @Override
    public void onTick(MinecraftServer server) {
        //tickSpeed.tick(server);
        //HUDController.update(server);
        PUBSUB.update(server.getTicks());
        StructureChannel.instance.tick();
        //for (QuickCarpetModule m : modules) m.tick(server);
    }

    @Override
    public void onGameStarted()
    {
        CarpetServer.settingsManager.parseSettingsClass(Settings.class);
    }


    //not Override - env needed
    public void onGameStarted(EnvType env) {
        CarpetRegistry.init();
        //CarpetProfiler.init();
        //Settings.MANAGER.parse();
        //for (QuickCarpetModule m : modules) {
        //    m.onGameStarted();
        //    LOG.info(Build.NAME + " module " + m.getId() + " version " + m.getVersion() + " initialized");
        //}
        if (env == EnvType.CLIENT) {
            this.client = new QuickCarpetClient();
        }
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        //CarpetCommand.register(dispatcher);
        //TickCommand.register(dispatcher);
        //CarpetFillCommand.register(dispatcher);
        //CarpetCloneCommand.register(dispatcher);
        //CarpetSetBlockCommand.register(dispatcher);
        //CounterCommand.register(dispatcher);
        //PlayerCommand.register(dispatcher);
        //LogCommand.register(dispatcher);
        //SpawnCommand.register(dispatcher);
        PingCommand.register(dispatcher);
        //CameraModeCommand.register(dispatcher);
        //for (QuickCarpetModule m : modules) m.registerCommands(dispatcher);
    }

    /*public void setCommandDispatcher(CommandDispatcher<ServerCommandSource> dispatcher) {
        this.dispatcher = dispatcher;
    }*/

    /*@Override
    public void registerModule(QuickCarpetModule module) {
        LOG.info(Build.NAME + " module " + module.getId() + " version " + module.getVersion() + " registered");
        modules.add(module);
    }*/

    @Override
    public void onInitialize() {

    }


    public static boolean isDevelopment() {
        return Build.VERSION.contains("dev") || FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void onPlayerLoggedIn(PlayerEntity player) {
        ServerPlayerEntity splayer = (ServerPlayerEntity) player;
        //loggers.onPlayerConnect(player);
        if (player instanceof ServerPlayerEntity)
            pluginChannels.onPlayerConnect((ServerPlayerEntity) player);
        //for (QuickCarpetModule m : modules) m.onPlayerConnect(player);
    }

    @Override
    public void onPlayerLoggedOut(PlayerEntity player) {
        //loggers.onPlayerDisconnect(player);
        if (player instanceof ServerPlayerEntity)
            pluginChannels.onPlayerDisconnect((ServerPlayerEntity )player);
        //for (QuickCarpetModule m : modules) m.onPlayerDisconnect(player);
    }
}
