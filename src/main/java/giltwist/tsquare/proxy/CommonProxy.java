package giltwist.tsquare.proxy;

import java.io.File;

import giltwist.tsquare.ModConfig;
import giltwist.tsquare.TSquare;
import giltwist.tsquare.items.ItemBase;
import giltwist.tsquare.items.ModifierBase;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

	// Config instance
	public static Configuration config;

	public void preInit(FMLPreInitializationEvent e) {
		File directory = e.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(), "tsquare.cfg"));
		ModConfig.readConfig();


	}

	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(TSquare.instance, new GuiProxy());
	}

	public void postInit(FMLPostInitializationEvent e) {
		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().register(new ItemBase("tsquareBlendCircle").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareBlendSphere").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareBlob").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareBlockInfo").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareCircle3Points").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareCircleCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareColorWheel").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareCubeCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareCuboid2Corners").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareDelete").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareEyeDropper").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareFill").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareFillDown").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareGetBlock").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareGreenThumb").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareGrow").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareHollow").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareLine").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareLoadArea").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareMelt").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareMoveBlock").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareOverlay").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquarePaintbrush").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareRandomTerraform").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareResetAll").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareRotateBlock").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareRuler").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareSmooth").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareSphereCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareSplatterCircle").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareSplatterOverlay").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareSplatterSphere").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareSquareCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareTriangle3Corners").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareUndo").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		event.getRegistry().register(new ItemBase("tsquareUp").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		
		event.getRegistry().register(new ModifierBase("tsquareEraser"));
		event.getRegistry().register(new ModifierBase("tsquareReplaceMode"));
		

	}

	public void registerItemRenderer(Item item, int meta, String id) {
	}

	public void registerModifierRenderer(Item item, String registryName) {

	}
}