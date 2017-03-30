package giltwist.tsquare.items;

import giltwist.tsquare.TSquare;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModifierBase extends Item {

	public ModifierBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(TSquare.creativeTab);
		setMaxStackSize(1);
		GameRegistry.register(this);
		
	}

	
	public void initModel() {
		
		TSquare.proxy.registerModifierRenderer(this,this.getRegistryName().toString());
	}
}
