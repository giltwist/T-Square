package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoBlockInfo {

	public static void activate(EntityPlayer player, boolean fullBlockState) {

		if (!player.isSwingInProgress) {

			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (fullBlockState) {
					player.addChatMessage(new TextComponentString("Block's state: " + player.worldObj.getBlockState(targetBlock).toString()));
				} else {

					player.addChatMessage(new TextComponentString("Block's material: " + player.worldObj.getBlockState(targetBlock).getBlock().getRegistryName().toString()));

				}
			}

		}
	}

}
