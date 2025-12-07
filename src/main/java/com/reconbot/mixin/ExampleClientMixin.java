package com.reconbot.mixin.client;

import com.mojang.blaze3d.platform.Input;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.BlockPos;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickCallback;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class ExampleClientMixin {
	@Inject(at = @At("HEAD"), method = "run")
	private void init(CallbackInfo info) {
	public class ExampleClientMixin implements ClientModInitializer {

    // Later: Add more blocks (perferably player made)
    private static final Set<Block> BASE_BLOCKS = Set.of(
        Blocks.CRAFTING_TABLE,
        Blocks.FURNACE,
        Blocks.CHEST,
        Blocks.TORCH,
        Blocks.LANTERN,
        Blocks.BED,
        Blocks.OAK_PLANKS,
        Blocks.SPRUCE_PLANKS,
        Blocks.SMOOTH_STONE,
        Blocks.GLASS,
        Blocks.BRICKS
		Blocks.CRAFTING_TABLE,
		Blocks.FURNACE,
		Blocks.BLAST_FURNACE,
		Blocks.SMOKER,
		Blocks.STONECUTTER,
		Blocks.ANVIL,
		Blocks.GRINDSTONE,
		Blocks.SMITHING_TABLE,
		Blocks.COMPOSTER,
		Blocks.BARREL,
		Blocks.LECTERN,
		Blocks.CARTOGRAPHY_TABLE,
		Blocks.LOOM,
		Blocks.GRINDSTONE,
		Blocks.SMITHING_TABLE,
		Blocks.LEVER,
		Blocks.BUTTON,
		Blocks.PRESSURE_PLATE,
		Blocks.REDSTONE_TORCH,
		Blocks.REDSTONE_BLOCK,
		Blocks.DISPENSER,
		Blocks.DROPPER,
		Blocks.PISTON,
		Blocks.STICKY_PISTON,
		Blocks.OBSERVER,
		Blocks.HOPPER,
		Blocks.TNT,
		Blocks.MINECART_WITH_HOPPER,
		Blocks.REDSTONE_LAMP,
		Blocks.DAYLIGHT_SENSOR,
		Blocks.COMMAND_BLOCK,
		Blocks.JUKEBOX,
		Blocks.FARMLAND,
		Blocks.CAULDRON,
		Blocks.BREWING_STAND,
		Blocks.CRAFTING_TABLE,
		Blocks.COMPOSTER,
		Blocks.FLOWER_POT,
		Blocks.BED,
		Blocks.DOOR,
		Blocks.TRAPDOOR,
		Blocks.SIGN,
		Blocks.NOTE_BLOCK,
		Blocks.BEACON,
		Blocks.CHORUS_FLOWER,
		Blocks.CHEST,
		Blocks.SHULKER_BOX,
		Blocks.ENDER_CHEST,
		Blocks.BARREL,
		Blocks.HOPPER,
		Blocks.SPAWNER,
		Blocks.BEE_NEST,
		Blocks.BEEHIVE,
		Blocks.TURTLE_EGG,
		Blocks.FURNACE,
		Blocks.BREWING_STAND,
		Blocks.GRINDSTONE,
		Blocks.LECTERN,
		Blocks.BARREL,
		Blocks.PAINTING,
		Blocks.FLOWER_POT,
		Blocks.END_PORTAL_FRAME,
		Blocks.DRAGON_EGG,
		Blocks.GLASS_PANE,
		Blocks.LADDER,
		Blocks.SCAFFOLDING,
		Blocks.CONDUIT,
		Blocks.RAIL,
		Blocks.POWERED_RAIL,
		Blocks.ACTIVATOR_RAIL,
		Blocks.DETECTOR_RAIL,
		Blocks.MINECART

    );

    @Override
    public void onInitializeClient() {
		//It uses client ticks
        ClientTickEvents.END_CLIENT_TICK.register(client -> onClientTick(client));
    }

    private void onClientTick(MinecraftClient client) {
        if (client.player == null || client.world == null) {
            return;
        }

        scanAreaForBases(client.player, client.world);
    }

    private void scanAreaForBases(net.minecraft.client.network.ClientPlayerEntity player, ClientWorld world) {
        int radius = 32; // Radius around the bot to scan for bases and players
        BlockPos playerPos = player.getBlockPos();

        for (int x = playerPos.getX() - radius; x < playerPos.getX() + radius; x++) {
            for (int y = playerPos.getY() - radius; y < playerPos.getY() + radius; y++) {
                for (int z = playerPos.getZ() - radius; z < playerPos.getZ() + radius; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    BlockState state = world.getBlockState(currentPos);
                    Block block = state.getBlock();

                    if (BASE_BLOCKS.contains(block)) {
                        logBaseCoordinates(currentPos, block);
                        return; // If a base block is found, exit the method and save the cords to a txt and log it to the console
                    }
                }
            }
        }
    }

    private void logBaseCoordinates(BlockPos pos, Block blockFound) {
        String filename = "found_bases.txt";
        String logEntry = String.format("Found potential base at: X:%d, Y:%d, Z:%d (Found: %s)%n",
                pos.getX(), pos.getY(), pos.getZ(), blockFound.getName().getString());

        try (FileWriter writer = new FileWriter(filename, true)) { 
            writer.write(logEntry);
            System.out.println("Logged: " + logEntry); 
        } catch (IOException e) {
            System.err.println("Could not write to log file: " + e.getMessage());
        }
    }
}
}
}