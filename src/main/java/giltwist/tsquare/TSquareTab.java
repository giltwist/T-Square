package giltwist.tsquare;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class TSquareTab extends CreativeTabs {

	public TSquareTab() {
		super(TSquare.MODID);
	}

	@Override
	public Item getTabIconItem() {
		return ModItems.itemMoveBlock;
	}

}