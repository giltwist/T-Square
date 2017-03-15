package giltwist.tsquare;

import giltwist.tsquare.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static ItemBase itemMoveBlock;
	public static ItemBase itemEyeDropper;
	public static ItemBase itemPaintbrush;
	public static ItemBase itemResetAll;
	public static ItemBase itemRotateBlock;
	public static ItemBase itemBlockInfo;
	
	public static void init() {
		
		itemMoveBlock = register(new ItemBase("itemMoveBlock").setCreativeTab(TSquare.creativeTab));
		itemEyeDropper = register(new ItemBase("itemEyeDropper").setCreativeTab(TSquare.creativeTab));
		itemPaintbrush = register(new ItemBase("itemPaintbrush").setCreativeTab(TSquare.creativeTab));
		itemResetAll = register(new ItemBase("itemResetAll").setCreativeTab(TSquare.creativeTab));
		itemRotateBlock = register(new ItemBase("itemRotateBlock").setCreativeTab(TSquare.creativeTab));
		itemBlockInfo = register(new ItemBase("itemBlockInfo").setCreativeTab(TSquare.creativeTab));
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemBase) {
			((ItemBase)item).registerItemModel();
		}

		return item;
	}
	
	

}