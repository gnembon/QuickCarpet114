package quickcarpet;

import net.minecraft.client.MinecraftClient;
import quickcarpet.client.ClientPluginChannelManager;
import quickcarpet.client.ClientPubSubListener;
import quickcarpet.client.ClientRulesChannel;
import quickcarpet.helper.TickSpeed;
import quickcarpet.settings.ParsedRule;
import quickcarpet.settings.Settings;

public class QuickCarpetClient {
    private final MinecraftClient minecraftClient;
    private final ClientRulesChannel rulesChannel;
    private final ClientPubSubListener pubSubListener;
    //public TickSpeed tickSpeed = new TickSpeed(true);

    public QuickCarpetClient() {
        QuickCarpet.getInstance().client = this;
        minecraftClient = MinecraftClient.getInstance();
        ClientPluginChannelManager.INSTANCE.register(rulesChannel = new ClientRulesChannel());
        ClientPluginChannelManager.INSTANCE.register(pubSubListener = new ClientPubSubListener());
    }

    public boolean isSingleplayer() {
        return minecraftClient.isInSingleplayer();
    }

    public void onJoinServer() {
        //tickSpeed = new TickSpeed(true);
        ClientPluginChannelManager.INSTANCE.sendRegisterPacket(minecraftClient.getNetworkHandler());
        pubSubListener.subscribe(
            "minecraft.performance.tps",
            "carpet.tick-rate.tps-goal",
            "carpet.tick-rate.paused",
            "carpet.tick-rate.step"
        );
        /*if (!isSingleplayer()) {
            for (ParsedRule<?> rule : Settings.MANAGER.getRules()) rule.resetToDefault(false);
        }*/
    }

    public void onLeaveServer() {
        //tickSpeed = new TickSpeed(true);
    }

    public void tick() {
        //tickSpeed.tick(MinecraftClient.getInstance());
    }
}
