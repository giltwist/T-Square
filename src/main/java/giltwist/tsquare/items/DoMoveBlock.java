package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import giltwist.tsquare.TSquare;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoMoveBlock {

	public static void activate(EntityPlayer player, boolean isRightClick) {

		if (!player.isSwingInProgress) {
			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);
			EnumFacing face = FindLookedBlock.getBlockFace(player);
			if (targetBlock == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {
				BlockPos movestart = targetBlock;
				BlockPos moveend;
				if (isRightClick) {
					moveend = targetBlock.add(face.getDirectionVec());
				} else {

					moveend = targetBlock.subtract(face.getDirectionVec());
				}
				IBlockState moveblock = player.worldObj.getBlockState(movestart);

				if (player.worldObj.getBlockState(moveend).getMaterial() == net.minecraft.block.material.Material.AIR || player.isSneaking()) {

					if (!TSquare.BLOCKBLACKLIST.contains(player.worldObj.getBlockState(movestart).getBlock())&&!TSquare.BLOCKBLACKLIST.contains(player.worldObj.getBlockState(moveend).getBlock())) {
					player.worldObj.setBlockToAir(movestart);
					player.worldObj.setBlockState(moveend, moveblock);
					} else {
						player.addChatMessage(new TextComponentString("Error: Either target or destination block is on global blacklist"));
					}
				}

			}

		}

	}
}
