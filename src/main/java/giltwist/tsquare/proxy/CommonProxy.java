package giltwist.tsquare.proxy;


import java.io.File;

import giltwist.tsquare.ModConfig;
import giltwist.tsquare.ModItems;
import giltwist.tsquare.TSquare;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "tsquare.cfg"));
       ModConfig.readConfig();

      
        ModItems.init();


    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TSquare.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }
    
    public void registerItemRenderer(Item item, int meta, String id) {
    }
    
    public void registerModifierRenderer(Item item, String registryName){
    	
    }
}