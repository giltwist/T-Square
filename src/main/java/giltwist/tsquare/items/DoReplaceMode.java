package giltwist.tsquare.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DoReplaceMode {

	
	public static void material(EntityPlayer player, ItemStack itemstack) {
		NBTTagCompound tagCompound = itemstack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			itemstack.setTagCompound(tagCompound);
		}
		if (!player.isSwingInProgress){
			if (player.isSneaking()){ 
				itemstack.getTagCompound().setString("mode", "mx");
				itemstack.setStackDisplayName("Replace Mode: Mx");
			//	itemstack.getItem().addPropertyOverride(new ResourceLocation("mode"),  new IItemPropertyGetter().);
			}else{
				itemstack.getTagCompound().setString("mode", "m");
				itemstack.setStackDisplayName("Replace Mode: M");
			}
		
	}
	}

	public static void blockstate(EntityPlayer player, ItemStack itemstack){
		NBTTagCompound tagCompound = itemstack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			itemstack.setTagCompound(tagCompound);
		}
		if (player.isSneaking()){
			itemstack.setStackDisplayName("Replace Mode: Sx");
			itemstack.getTagCompound().setString("mode", "sx");
		}else{
			itemstack.setStackDisplayName("Replace Mode: S");
			itemstack.getTagCompound().setString("mode", "s");
		}
	}
	
}
