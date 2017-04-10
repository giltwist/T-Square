package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoRotateBlock {

	public static void activate(EntityPlayer player, boolean isRightClick) {

		if (!player.isSwingInProgress) {

			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);
			EnumFacing face = FindLookedBlock.getBlockFace(player);
			if (targetBlock == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (isRightClick) {
					player.getEntityWorld().getBlockState(targetBlock).getBlock().rotateBlock(player.getEntityWorld(), targetBlock, face.getOpposite());
				} else {
					player.getEntityWorld().getBlockState(targetBlock).getBlock().rotateBlock(player.getEntityWorld(), targetBlock, face);
				}

			}
		}
	}

}
