package com.nukeologist.circuitos.proxy;

import com.nukeologist.circuitos.init.ModBlocks;
import com.nukeologist.circuitos.init.ModItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        ModBlocks.registerModels();
        ModItems.initModels();
    }
}
