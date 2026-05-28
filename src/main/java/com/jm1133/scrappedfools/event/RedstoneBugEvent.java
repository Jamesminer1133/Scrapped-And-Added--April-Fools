package com.jm1133.scrappedfools.event;

import com.jm1133.scrappedfools.entity.ModEntities;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.HashSet;
import java.util.Set;

public class RedstoneBugEvent {
    private static final Long2IntOpenHashMap LAST_SIGNAL = new Long2IntOpenHashMap();
    private static final Set<BlockPos> WATCHED_POSITIONS = new HashSet<>();
    @SubscribeEvent
    public static void onBlockUpdate(BlockEvent.NeighborNotifyEvent event) {
        Level level = (Level) event.getLevel();
        if (level.isClientSide()) return;

        BlockPos pos = event.getPos();
        WATCHED_POSITIONS.add(pos.immutable());
    }
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (WATCHED_POSITIONS.isEmpty()) return;
        RandomSource random = level.random;
        Set<BlockPos> toRemove = new HashSet<>();
        for (BlockPos pos : WATCHED_POSITIONS) {
            int newSignal = level.getBestNeighborSignal(pos);
            long key = pos.asLong();
            int oldSignal = LAST_SIGNAL.getOrDefault(key, -1);
            if (newSignal != oldSignal) {

                LAST_SIGNAL.put(key, newSignal);
                if (newSignal == 0) {
                    toRemove.add(pos);
                    continue;
                }
                if (random.nextFloat() < 0.01f) {
                    ModEntities.REDSTONEBUG.get().spawn(
                            level,
                            pos,
                            net.minecraft.world.entity.MobSpawnType.EVENT
                    );
                }
            }
        }
        WATCHED_POSITIONS.removeAll(toRemove);
    }
}