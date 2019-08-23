package net.silentchaos512.tutorial.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.silentchaos512.tutorial.TutorialMod;
import net.silentchaos512.tutorial.item.BackpackItem;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Registers items and stores references to them, if needed. It works more or less like {@link
 * ModBlocks}, so I won't repeat myself here. Check ModBlocks' Javadoc for more details.
 */
public final class ModItems {
    public static final BackpackItem backpack = new BackpackItem();

    /**
     * Stores block items which need to be registered. I am using a {@link LinkedHashMap} so the
     * items will be registered in the order they were created.
     * <p>
     * You could probably clear this map after registering everything. It would free up a little
     * memory, but you would only be removing pointers.
     */
    static final Map<String, BlockItem> BLOCKS_TO_REGISTER = new LinkedHashMap<>();

    private ModItems() {}

    /**
     * Creates and registers items. Also registers block items, which were created in {@link
     * ModBlocks#registerAll(RegistryEvent.Register)}.
     *
     * @param event The event
     */
    public static void registerAll(RegistryEvent.Register<Item> event) {
        // Blocks
        BLOCKS_TO_REGISTER.forEach(ModItems::register);

        // Items
        register("backpack", backpack);

        // See the comment in ModBlocks, this is the same concept
        for (Gem gem : Gem.values()) {
            // ruby, sapphire
            register(gem.getName(), gem.getGemItem());
        }
    }

    /**
     * Register the item. Also sets the registry name.
     *
     * @param name The path of the item ID
     * @param item The item
     * @param <T>  The item class
     * @return The item
     */
    private static <T extends Item> T register(String name, T item) {
        ResourceLocation id = TutorialMod.getId(name);
        item.setRegistryName(id);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
