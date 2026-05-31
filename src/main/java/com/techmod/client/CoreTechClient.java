package com.techmod.client;

import com.techmod.CoreTechMod;
import com.techmod.client.screen.CoreWorkbenchScreen;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CoreTechMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CoreTechClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(CoreTechMod.CORE_WORKBENCH_MENU.get(), CoreWorkbenchScreen::new);
            ItemBlockRenderTypes.setRenderLayer(CoreTechMod.MECHANICAL_GEAR.get(), RenderType.cutout());
        });
    }
}
