package giltwist.tsquare;

import org.apache.logging.log4j.Logger;

import giltwist.tsquare.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = TSquare.MODID, name = TSquare.MODNAME, version = TSquare.MODVERSION, dependencies = "required-after:Forge@[11.16.0.1865,)", useMetadata = true)
public class TSquare {

    public static final String MODID = "tsquare";
    public static final String MODNAME = "T-Square Builder Tools";
    public static final String MODVERSION = "0.7.2";

    public static final TSquareTab creativeTab = new TSquareTab();
    
    @SidedProxy(clientSide = "giltwist.tsquare.proxy.ClientProxy", serverSide = "giltwist.tsquare.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static TSquare instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	System.out.println(MODNAME + " is loading!");
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
        TSquarePacketHandler.registerMessages("tsquare");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	MinecraftForge.EVENT_BUS.register(new TSquareEventHandler());
        proxy.postInit(e);
    }
}