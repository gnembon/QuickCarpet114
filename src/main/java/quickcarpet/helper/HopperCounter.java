package quickcarpet.helper;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2LongLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import quickcarpet.QuickCarpet;
import quickcarpet.logging.Logger;
import quickcarpet.pubsub.PubSubInfoProvider;
import quickcarpet.utils.Messenger;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class HopperCounter
{
    /*
    public static final Map<DyeColor, HopperCounter> COUNTERS;

    static {
        EnumMap<DyeColor, HopperCounter> counterMap = new EnumMap<>(DyeColor.class);
        for (DyeColor color : DyeColor.values()) {
            counterMap.put(color, new HopperCounter(color));
        }
        COUNTERS = Maps.immutableEnumMap(counterMap);
    }

    public final DyeColor color;
    private final Object2LongMap<Item> counter = new Object2LongLinkedOpenHashMap<>();
    private long startTick;
    private long startMillis;
    private PubSubInfoProvider<Long> pubSubProvider;

    private HopperCounter(DyeColor color) {
        this.color = color;
        pubSubProvider = new PubSubInfoProvider<>(QuickCarpet.PUBSUB, "carpet.counter." + color.getName(), 0, this::getTotalItems);
    }

    public void add(MinecraftServer server, ItemStack stack) {
        if (startTick == 0) {
            startTick = server.getTicks();
            startMillis = System.currentTimeMillis();
        }
        Item item = stack.getItem();
        counter.put(item, counter.getLong(item) + stack.getCount());
        pubSubProvider.publish();
    }

    public void reset(MinecraftServer server) {
        counter.clear();
        startTick = server.getTicks();
        startMillis = System.currentTimeMillis();
        pubSubProvider.publish();
    }

    public static void resetAll(MinecraftServer server) {
        for (HopperCounter counter : COUNTERS.values()) {
            counter.reset(server);
        }
    }

    public static List<Text> formatAll(MinecraftServer server, boolean realtime)
    {
        List<Text> text = new ArrayList<>();

        for (HopperCounter counter : COUNTERS.values()) {
            List<Text> temp = counter.format(server, realtime, false);
            if (temp.size() > 1) {
                if (!text.isEmpty()) text.add(Messenger.s(""));
                text.addAll(temp);
            }
        }
        if (text.isEmpty()) {
            text.add(Messenger.s("No items have been counted yet."));
        }
        return text;
    }

    public List<Text> format(MinecraftServer server, boolean realTime, boolean brief) {
        if (counter.isEmpty()) {
            if (brief) {
                return Collections.singletonList(Messenger.c("g "+color+": -, -/h, - min "));
            }
            return Collections.singletonList(Messenger.s(String.format("No items for %s yet", color.getName())));
        }
        long total = getTotalItems();
        long ticks = Math.max(realTime ? (System.currentTimeMillis() - startMillis) / 50 : server.getTicks() - startTick, 1);
        if (total == 0) {
            if (brief) {
                return Collections.singletonList(Messenger.c(
                        String.format("c %s: 0, 0/h, %.1f min ", color, ticks / (20.0 * 60.0))));
            }
            return Collections.singletonList(Messenger.c(String.format("w No items for %s yet (%.2f min.%s)",
                            color.getName(), ticks / (20.0 * 60.0), (realTime ? " - real time" : "")),
                    "nb  [X]", "^g reset", "!/counter " + color.getName() +" reset"));
        }
        if (brief) {
            return Collections.singletonList(Messenger.c(String.format("c %s: %d, %d/h, %.1f min ",
                            color.getName(), total, total * (20 * 60 * 60) / ticks, ticks / (20.0 * 60.0))));
        }
        return counter.object2LongEntrySet().stream().map(e -> {
            Text itemName = new TranslatableText(e.getKey().getTranslationKey());
            long count = e.getLongValue();
            return Messenger.c("w - ", itemName, String.format("w : %d, %.1f/h",
                    count,
                    count * (20.0 * 60.0 * 60.0) / ticks));
        }).collect(Collectors.toList());
    }

    @Nullable
    public static HopperCounter getCounter(String color) {
        try {
            DyeColor colorEnum = DyeColor.valueOf(color.toUpperCase(Locale.ROOT));
            return COUNTERS.get(colorEnum);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public long getTotalItems() {
        return counter.values().stream().mapToLong(Long::longValue).sum();
    }

    public static class LogCommandParameters extends AbstractMap<String, Long> implements Logger.CommandParameters<Long> {
        public static final LogCommandParameters INSTANCE = new LogCommandParameters();
        private LogCommandParameters() {}
        @Override
        public Set<Entry<String, Long>> entrySet() {
            Map<String, Long> counts = new LinkedHashMap<>();
            for (Entry<DyeColor, HopperCounter> counterEntry : COUNTERS.entrySet()) {
                counts.put(counterEntry.getKey().name(), counterEntry.getValue().getTotalItems());
            }
            return counts.entrySet();
        }
    }*/
}
