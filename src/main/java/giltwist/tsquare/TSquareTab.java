package giltwist.tsquare;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class TSquareTab extends CreativeTabs {

	public TSquareTab() {
		super(TSquare.MODID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.tsquareMoveBlock);
	}

}