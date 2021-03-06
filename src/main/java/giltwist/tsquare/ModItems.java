package giltwist.tsquare;

import giltwist.tsquare.items.ItemBase;
import giltwist.tsquare.items.ModifierBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	//Utilities
	public static ItemBase tsquareMoveBlock;
	public static ItemBase tsquareEyeDropper;
	public static ItemBase tsquarePaintbrush;
	public static ItemBase tsquareResetAll;
	public static ItemBase tsquareRotateBlock;
	public static ItemBase tsquareBlockInfo;
	public static ItemBase tsquareUndo;
	public static ItemBase tsquareUp;
	public static ItemBase tsquareLoadArea;
	public static ItemBase tsquareColorWheel;
	public static ItemBase tsquareGreenThumb;
	public static ItemBase tsquareDelete;
	public static ItemBase tsquareRuler;
	public static ItemBase tsquareGetBlock;
	
	public static ModifierBase tsquareReplaceMode;
	public static ModifierBase tsquareEraser;
	
	//Basic Shapes
	public static ItemBase tsquareSquareCenter;
	public static ItemBase tsquareCircleCenter;
	public static ItemBase tsquareCubeCenter;
	public static ItemBase tsquareSphereCenter;
	public static ItemBase tsquareOverlay;
	public static ItemBase tsquareFillDown;
	public static ItemBase tsquareHollow;
	
	//Advanced Shapes
	public static ItemBase tsquareCuboid2Corners;
	public static ItemBase tsquareLine;
	public static ItemBase tsquareTriangle3Corners;
	public static ItemBase tsquareCircle3Points;
	
	//Randomized Shapes
	public static ItemBase tsquareBlob;
	public static ItemBase tsquareSplatterSphere;
	public static ItemBase tsquareSplatterCircle;
	public static ItemBase tsquareSplatterOverlay;
	
	//Terraformers
	public static ItemBase tsquareBlendCircle;
	public static ItemBase tsquareBlendSphere;
	public static ItemBase tsquareSmooth;
	public static ItemBase tsquareMelt;
	public static ItemBase tsquareFill;
	public static ItemBase tsquareGrow;
	public static ItemBase tsquareRandomTerraform;
	
	
	public static void init() {
		
		tsquareBlendCircle = register(new ItemBase("tsquareBlendCircle").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareBlendSphere = register(new ItemBase("tsquareBlendSphere").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareBlob = register(new ItemBase("tsquareBlob").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		tsquareBlockInfo = register(new ItemBase("tsquareBlockInfo").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareCircle3Points = register(new ItemBase("tsquareCircle3Points").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareCircleCenter = register(new ItemBase("tsquareCircleCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareColorWheel = register(new ItemBase("tsquareColorWheel").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareCubeCenter = register(new ItemBase("tsquareCubeCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareCuboid2Corners = register(new ItemBase("tsquareCuboid2Corners").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareDelete = register(new ItemBase("tsquareDelete").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareEyeDropper = register(new ItemBase("tsquareEyeDropper").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareFill = register(new ItemBase("tsquareFill").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareFillDown = register(new ItemBase("tsquareFillDown").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareGetBlock = register(new ItemBase("tsquareGetBlock").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareGreenThumb = register(new ItemBase("tsquareGreenThumb").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareGrow = register(new ItemBase("tsquareGrow").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareHollow = register(new ItemBase("tsquareHollow").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareLine = register(new ItemBase("tsquareLine").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareLoadArea = register(new ItemBase("tsquareLoadArea").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareMelt = register(new ItemBase("tsquareMelt").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareMoveBlock = register(new ItemBase("tsquareMoveBlock").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareOverlay = register(new ItemBase("tsquareOverlay").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquarePaintbrush = register(new ItemBase("tsquarePaintbrush").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareRandomTerraform = register(new ItemBase("tsquareRandomTerraform").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareResetAll = register(new ItemBase("tsquareResetAll").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareRotateBlock = register(new ItemBase("tsquareRotateBlock").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareRuler = register(new ItemBase("tsquareRuler").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareSmooth = register(new ItemBase("tsquareSmooth").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareSphereCenter = register(new ItemBase("tsquareSphereCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareSplatterCircle = register(new ItemBase("tsquareSplatterCircle").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		tsquareSplatterOverlay = register(new ItemBase("tsquareSplatterOverlay").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		tsquareSplatterSphere = register(new ItemBase("tsquareSplatterSphere").setMaxStackSize(ModConfig.maxBrushSize).setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		tsquareSquareCenter = register(new ItemBase("tsquareSquareCenter").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		tsquareTriangle3Corners = register(new ItemBase("tsquareTriangle3Corners").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareUndo = register(new ItemBase("tsquareUndo").setMaxStackSize(1).setCreativeTab(TSquare.creativeTab));
		tsquareUp = register(new ItemBase("tsquareUp").setMaxStackSize(ModConfig.maxBrushSize).setCreativeTab(TSquare.creativeTab));
		
		tsquareEraser = new ModifierBase("tsquareEraser");
		tsquareReplaceMode = new ModifierBase("tsquareReplaceMode");
		
		
		tsquareEraser.initModel();
		tsquareReplaceMode.initModel();
		
		
				
		
		
		}
	

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}

		return item;
	}
	
	

}