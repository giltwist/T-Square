package giltwist.tsquare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;

import giltwist.tsquare.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = TSquare.MODID, name = TSquare.MODNAME, version = TSquare.MODVERSION, dependencies = "required-after:Forge@[11.16.0.1865,)", useMetadata = true)
public class TSquare {

    public static final String MODID = "tsquare";
    public static final String MODNAME = "T-Square Builder Tools";
    public static final String MODVERSION = "0.8.1";
    public static Set<String> USERWHITELIST;

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
    
    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event){
	
			Set<String> tempWhitelist = new HashSet<String>();

			File rootDirectory = event.getServer().getDataDirectory();
			String configPath = rootDirectory.getAbsolutePath() + File.separator + "config" + File.separator;
			String tsquarePath = configPath + "T-Square" + File.separator;
			String userPath = tsquarePath + "userWhitelist.txt";

			File configFile = new File(configPath);
			if (!configFile.exists() || !configFile.isDirectory()) {
				configFile.mkdir();
			}
			File tsquareFile = new File(tsquarePath);
			if (!tsquareFile.exists() || !tsquareFile.isDirectory()) {
				tsquareFile.mkdir();
			}
			File userFile = new File(userPath);
			if (userFile.exists() && userFile.isFile()) {

				try {
					List<String> usernames = Files.readAllLines(Paths.get(userPath));
					
					for (int i=0;i<usernames.size();i++){
						tempWhitelist.add(usernames.get(i));					
					}

				} catch (IOException ioe) {

				}

			} else {

				try {
					userFile.createNewFile();

				} catch (IOException ioe) {

				}

			}

		USERWHITELIST = tempWhitelist;
		
	}
    
    
}