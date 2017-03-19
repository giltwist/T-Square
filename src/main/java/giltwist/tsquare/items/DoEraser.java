package giltwist.tsquare.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DoEraser {

	
	public static void material(EntityPlayer player) {
		ItemStack itemstack=player.getHeldItemMainhand();
		NBTTagCompound tagCompound = itemstack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			itemstack.setTagCompound(tagCompound);
		}
		if (!player.isSwingInProgress){
			if (player.isSneaking()){ 
				itemstack.getTagCompound().setString("mode", "mx");
				itemstack.setStackDisplayName("Eraser: Mx");
			//	itemstack.getItem().addPropertyOverride(new ResourceLocation("mode"),  new IItemPropertyGetter().);
			}else{
				itemstack.getTagCompound().setString("mode", "m");
				itemstack.setStackDisplayName("Eraser: M");
			}
		
	}
	}

	public static void blockstate(EntityPlayer player){
		ItemStack itemstack=player.getHeldItemMainhand();
		NBTTagCompound tagCompound = itemstack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			itemstack.setTagCompound(tagCompound);
		}
		if (player.isSneaking()){
			itemstack.setStackDisplayName("Eraser: Sx");
			itemstack.getTagCompound().setString("mode", "sx");
		}else{
			itemstack.setStackDisplayName("Eraser: S");
			itemstack.getTagCompound().setString("mode", "s");
		}
	}
	
}
