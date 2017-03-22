package giltwist.tsquare;

import giltwist.tsquare.items.ItemBase;
import giltwist.tsquare.items.ItemEraser;
import giltwist.tsquare.items.ItemReplaceMode;
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
	
	public static ItemReplaceMode tsquareReplaceMode;
	public static ItemEraser tsquareEraser;
	
	//Basic Shapes
	public static ItemBase tsquareSquareCenter;
	public static ItemBase tsquareCircleCenter;
	public static ItemBase tsquareCubeCenter;
	public static ItemBase tsquareSphereCenter;
	
	//Advanced Shapes
	public static ItemBase tsquareCuboid2Corners;
	public static ItemBase tsquareLine;
	
	//Randomized Shapes
	public static ItemBase tsquareBlob;
	
	//Terraformers
	public static ItemBase tsquareBlendSphere;
	public static ItemBase tsquareSmooth;
	public static ItemBase tsquareMelt;
	public static ItemBase tsquareFill;
	public static ItemBase tsquareGrow;
	
	public static void init() {
		
		tsquareBlendSphere = register(new ItemBase("tsquareBlendSphere").setCreativeTab(TSquare.creativeTab));
		tsquareBlob = register(new ItemBase("tsquareBlob").setMaxDamage(20).setCreativeTab(TSquare.creativeTab));
		tsquareBlockInfo = register(new ItemBase("tsquareBlockInfo").setCreativeTab(TSquare.creativeTab));
		tsquareCircleCenter = register(new ItemBase("tsquareCircleCenter").setCreativeTab(TSquare.creativeTab));
		tsquareCubeCenter = register(new ItemBase("tsquareCubeCenter").setCreativeTab(TSquare.creativeTab));
		tsquareCuboid2Corners = register(new ItemBase("tsquareCuboid2Corners").setCreativeTab(TSquare.creativeTab));
		tsquareEyeDropper = register(new ItemBase("tsquareEyeDropper").setCreativeTab(TSquare.creativeTab));
		tsquareFill = register(new ItemBase("tsquareFill").setCreativeTab(TSquare.creativeTab));
		tsquareGrow = register(new ItemBase("tsquareGrow").setCreativeTab(TSquare.creativeTab));
		tsquareLine = register(new ItemBase("tsquareLine").setCreativeTab(TSquare.creativeTab));
		tsquareMelt = register(new ItemBase("tsquareMelt").setCreativeTab(TSquare.creativeTab));
		tsquareMoveBlock = register(new ItemBase("tsquareMoveBlock").setCreativeTab(TSquare.creativeTab));
		tsquarePaintbrush = register(new ItemBase("tsquarePaintbrush").setCreativeTab(TSquare.creativeTab));
		tsquareResetAll = register(new ItemBase("tsquareResetAll").setCreativeTab(TSquare.creativeTab));
		tsquareRotateBlock = register(new ItemBase("tsquareRotateBlock").setCreativeTab(TSquare.creativeTab));
		tsquareSmooth = register(new ItemBase("tsquareSmooth").setCreativeTab(TSquare.creativeTab));
		tsquareSphereCenter = register(new ItemBase("tsquareSphereCenter").setCreativeTab(TSquare.creativeTab));
		tsquareSquareCenter = register(new ItemBase("tsquareSquareCenter").setCreativeTab(TSquare.creativeTab));
		tsquareUndo = register(new ItemBase("tsquareUndo").setCreativeTab(TSquare.creativeTab));
		
		tsquareEraser = new ItemEraser();
		tsquareEraser.initModel();
		
		tsquareReplaceMode = new ItemReplaceMode();
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