package com.techmod;

import com.techmod.block.CoreWorkbenchBlock;
import com.techmod.block.ore.AluminumOreBlock;
import com.techmod.block.ore.LeadOreBlock;
import com.techmod.block.ore.SilverOreBlock;
import com.techmod.block.ore.TinOreBlock;
import com.techmod.block.ore.UraniumOreBlock;
import com.techmod.blockentity.CoreWorkbenchBlockEntity;
import com.techmod.menu.CoreWorkbenchMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(CoreTechMod.MOD_ID)
public class CoreTechMod {
    public static final String MOD_ID = "techmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    // Deferred Registers
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // ====== Blocks ======
    public static final RegistryObject<Block> CORE_WORKBENCH = BLOCKS.register("core_workbench",
            CoreWorkbenchBlock::new);
    public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore", TinOreBlock::new);
    public static final RegistryObject<Block> ALUMINUM_ORE = BLOCKS.register("aluminum_ore", AluminumOreBlock::new);
    public static final RegistryObject<Block> LEAD_ORE = BLOCKS.register("lead_ore", LeadOreBlock::new);
    public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore", SilverOreBlock::new);
    public static final RegistryObject<Block> URANIUM_ORE = BLOCKS.register("uranium_ore", UraniumOreBlock::new);

    // ====== Block Items ======
    public static final RegistryObject<Item> CORE_WORKBENCH_ITEM = ITEMS.register("core_workbench",
            () -> new BlockItem(CORE_WORKBENCH.get(), new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TIN_ORE_ITEM = ITEMS.register("tin_ore",
            () -> new BlockItem(TIN_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_ORE_ITEM = ITEMS.register("aluminum_ore",
            () -> new BlockItem(ALUMINUM_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LEAD_ORE_ITEM = ITEMS.register("lead_ore",
            () -> new BlockItem(LEAD_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> SILVER_ORE_ITEM = ITEMS.register("silver_ore",
            () -> new BlockItem(SILVER_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_ORE_ITEM = ITEMS.register("uranium_ore",
            () -> new BlockItem(URANIUM_ORE.get(), new Item.Properties()));

    // ====== Materials ======
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_LEAD = ITEMS.register("raw_lead", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MACHINE_GEAR = ITEMS.register("machine_gear", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BASIC_CIRCUIT = ITEMS.register("basic_circuit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILICON_WAFER = ITEMS.register("silicon_wafer", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BATTERY_CELL = ITEMS.register("battery_cell", () -> new Item(new Item.Properties()));

    // ====== Block Entities ======
    public static final RegistryObject<BlockEntityType<CoreWorkbenchBlockEntity>> CORE_WORKBENCH_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("core_workbench",
                    () -> BlockEntityType.Builder.of(CoreWorkbenchBlockEntity::new, CORE_WORKBENCH.get()).build(null));

    // ====== Menus ======
    public static final RegistryObject<MenuType<CoreWorkbenchMenu>> CORE_WORKBENCH_MENU =
            MENUS.register("core_workbench",
                    () -> IForgeMenuType.create((windowId, inventory, data) ->
                            new CoreWorkbenchMenu(windowId, inventory, data.readBlockPos())));

    // ====== Creative Tab ======
    public static final RegistryObject<CreativeModeTab> TECH_TAB = TABS.register("techmod",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.techmod"))
                    .icon(() -> CORE_WORKBENCH_ITEM.get().getDefaultInstance())
                    .displayItems((params, output) -> {
                        ITEMS.getEntries().forEach(reg -> output.accept(reg.get()));
                    })
                    .build());

    public CoreTechMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        MENUS.register(modEventBus);
        TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("CoreTechMod loaded!");
    }
}
