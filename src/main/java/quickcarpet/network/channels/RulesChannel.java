package quickcarpet.network.channels;

import carpet.CarpetServer;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.packet.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import quickcarpet.network.PacketSplitter;
import quickcarpet.network.PluginChannelHandler;
//import quickcarpet.settings.ParsedRule;
import carpet.settings.ParsedRule;

import quickcarpet.settings.Settings;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class RulesChannel implements PluginChannelHandler {
    public static final Identifier CHANNEL = new Identifier("carpet:rules");
    public static final int VERSION = 1;

    public static final int PACKET_S2C_DATA = 0;

    public static RulesChannel instance;

    private final Set<ServerPlayerEntity> players = new LinkedHashSet<>();

    public RulesChannel() {
        instance = this;
    }

    @Override
    public Identifier[] getChannels() {
        return new Identifier[] {CHANNEL};
    }

    @Override
    public boolean register(Identifier channel, ServerPlayerEntity player) {
        players.add(player);
        sendRuleUpdate(player, CarpetServer.settingsManager.getRules());
        return true;
    }

    @Override
    public void unregister(Identifier channel, ServerPlayerEntity player) {
        players.remove(player);
    }

    @Override
    public void onCustomPayload(CustomPayloadC2SPacket packet, ServerPlayerEntity player) {

    }

    public void sendRuleUpdate(Set<ParsedRule<?>> rules) {
        if (players.isEmpty()) return;
        CompoundTag data = serializeRuleUpdate(rules);
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeVarInt(PACKET_S2C_DATA);
        buf.writeCompoundTag(data);
        for (ServerPlayerEntity player : players) PacketSplitter.send(player.networkHandler, CHANNEL, buf);
    }

    public void sendRuleUpdate(ServerPlayerEntity player, Collection<ParsedRule<?>> rules) {
        CompoundTag data = serializeRuleUpdate(rules);
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeVarInt(PACKET_S2C_DATA);
        buf.writeCompoundTag(data);
        PacketSplitter.send(player.networkHandler, CHANNEL, buf);
    }

    public static CompoundTag serializeRuleUpdate(Collection<ParsedRule<?>> rules) {
        CompoundTag data = new CompoundTag();
        data.putInt("Version", VERSION);
        ListTag rulesList = new ListTag();
        for (ParsedRule<?> rule : rules) {
            CompoundTag ruleTag = new CompoundTag();
            ruleTag.putString("Id", rule.name);
            ruleTag.putString("Type", rule.type.getName());
            ruleTag.putString("DefaultValue", rule.defaultAsString);
            ruleTag.putString("Value", rule.getAsString());
            ruleTag.putString("Description", rule.description);
            ListTag extraList = new ListTag();
            for (String extra : rule.extraInfo) extraList.add(new StringTag(extra));
            ruleTag.put("ExtraInfo", extraList);
            rulesList.add(ruleTag);
        }
        data.put("Rules", rulesList);
        return data;
    }
}
