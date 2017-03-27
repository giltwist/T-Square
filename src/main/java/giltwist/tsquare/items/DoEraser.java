package giltwist.tsquare.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DoEraser {

	public static void activate(EntityPlayer player, boolean fullBlockState) {
		ItemStack itemstack = player.getHeldItemMainhand();
		NBTTagCompound tagCompound = itemstack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			itemstack.setTagCompound(tagCompound);
		}
		if (!player.isSwingInProgress) {

			if (fullBlockState) {
				if (player.isSneaking()) {
					itemstack.setStackDisplayName("Eraser: Sx");
					itemstack.getTagCompound().setString("mode", "sx");
				} else {
					itemstack.setStackDisplayName("Eraser: S");
					itemstack.getTagCompound().setString("mode", "s");
				}
			} else {

				if (player.isSneaking()) {
					itemstack.getTagCompound().setString("mode", "mx");
					itemstack.setStackDisplayName("Eraser: Mx");
					// itemstack.getItem().addPropertyOverride(new
					// ResourceLocation("mode"), new IItemPropertyGetter().);
				} else {
					itemstack.getTagCompound().setString("mode", "m");
					itemstack.setStackDisplayName("Eraser: M");
				}
			}

		}
	}

	

}
