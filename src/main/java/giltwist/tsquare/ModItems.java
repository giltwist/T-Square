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
	
		
	public static void init() {
		
		tsquareMoveBlock = register(new ItemBase("tsquareMoveBlock").setCreativeTab(TSquare.creativeTab));
		tsquareEyeDropper = register(new ItemBase("tsquareEyeDropper").setCreativeTab(TSquare.creativeTab));
		tsquarePaintbrush = register(new ItemBase("tsquarePaintbrush").setCreativeTab(TSquare.creativeTab));
		tsquareResetAll = register(new ItemBase("tsquareResetAll").setCreativeTab(TSquare.creativeTab));
		tsquareRotateBlock = register(new ItemBase("tsquareRotateBlock").setCreativeTab(TSquare.creativeTab));
		tsquareBlockInfo = register(new ItemBase("tsquareBlockInfo").setCreativeTab(TSquare.creativeTab));
		tsquareSquareCenter = register(new ItemBase("tsquareSquareCenter").setCreativeTab(TSquare.creativeTab));
		tsquareUndo = register(new ItemBase("tsquareUndo").setCreativeTab(TSquare.creativeTab));
		
		tsquareReplaceMode = new ItemReplaceMode();
		tsquareReplaceMode.initModel();
		
		tsquareEraser = new ItemEraser();
		tsquareEraser.initModel();		
		
		
		}
	

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}

		return item;
	}
	
	

}