package giltwist.tsquare.items;


import giltwist.tsquare.TSquare;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item {

	protected String name;

	public ItemBase(String name) {
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
	
	@Override
	public ItemBase setMaxDamage(int i) {
		super.setMaxDamage(i);
		return this;
	}
	
	@Override
	public ItemBase setMaxStackSize(int i) {
		super.setMaxStackSize(i);
		return this;
	}

    @SideOnly(Side.CLIENT)
    public void initModel() {
    	TSquare.proxy.registerItemRenderer(this, 0, name);
    }
	
	
}