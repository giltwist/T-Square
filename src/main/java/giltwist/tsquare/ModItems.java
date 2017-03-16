package giltwist.tsquare;

import giltwist.tsquare.items.ItemBase;
import giltwist.tsquare.items.ItemReplaceMode;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static ItemBase tsquareMoveBlock;
	public static ItemBase tsquareEyeDropper;
	public static ItemBase tsquarePaintbrush;
	public static ItemBase tsquareResetAll;
	public static ItemBase tsquareRotateBlock;
	public static ItemBase tsquareBlockInfo;
	
	public static ItemReplaceMode tsquareReplaceMode;
	
	
		
	public static void init() {
		
		tsquareMoveBlock = register(new ItemBase("tsquareMoveBlock").setCreativeTab(TSquare.creativeTab));
		tsquareEyeDropper = register(new ItemBase("tsquareEyeDropper").setCreativeTab(TSquare.creativeTab));
		tsquarePaintbrush = register(new ItemBase("tsquarePaintbrush").setCreativeTab(TSquare.creativeTab));
		tsquareResetAll = register(new ItemBase("tsquareResetAll").setCreativeTab(TSquare.creativeTab));
		tsquareRotateBlock = register(new ItemBase("tsquareRotateBlock").setCreativeTab(TSquare.creativeTab));
		tsquareBlockInfo = register(new ItemBase("tsquareBlockInfo").setCreativeTab(TSquare.creativeTab));
		
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