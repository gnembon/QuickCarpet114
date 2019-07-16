package quickcarpet.logging;

import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import quickcarpet.QuickCarpet;
import quickcarpet.utils.HUDController;

import java.util.Arrays;
import java.util.function.Supplier;

public interface LogHandler
{
    /*

    LogHandler CHAT = (logger, player, message, commandParams) -> Arrays.stream(message)
            .forEach(m -> player.sendChatMessage(new TranslatableText("chat.type.announcement", logger.getDisplayName(), m), MessageType.CHAT));
    LogHandler HUD = new LogHandler() {
        @Override
        public void handle(Logger logger, ServerPlayerEntity player, Text[] message, Supplier<Logger.CommandParameters> commandParams) {
            for (Text m : message)
                HUDController.addMessage(player, m);
        }

        @Override
        public void onRemovePlayer(String playerName) {
            ServerPlayerEntity player = QuickCarpet.minecraft_server.getPlayerManager().getPlayer(playerName);
            if (player != null)
                HUDController.clearPlayerHUD(player);
        }
    };

    @FunctionalInterface
    interface LogHandlerCreator
    {
        LogHandler create(String... extraArgs);

        default boolean usesExtraArgs() {
            return false;
        }
    }

    void handle(Logger logger, ServerPlayerEntity player, Text[] message, Supplier<Logger.CommandParameters> commandParams);

    default void onAddPlayer(String playerName) {}

    default void onRemovePlayer(String playerName) {}
*/
}
