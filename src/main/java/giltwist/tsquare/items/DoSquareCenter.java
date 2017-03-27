package giltwist.tsquare.items;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoSquareCenter {

	public static void activate(EntityPlayer player, boolean fullBlockState) {
		if (!player.isSwingInProgress) {
			EnumFacing face;
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {



										
					face = FindLookedBlock.getBlockFace(player);
					int size = player.getHeldItemMainhand().stackSize;
					

					if (face != null) {
						if (player.isSneaking()) {
							center = center.offset(face);
						}
						BlockPos[] toReplace=new BlockPos[(2*size-1)*(2*size-1)];
						for (int i = -1 * (size - 1); i <= (size - 1); i++) {
							for (int j = -1 * (size - 1); j <= (size - 1); j++) {

								if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
									toReplace[(2*size-1)*(i+size-1)+(j+size-1)]=new BlockPos(center.getX() + i, center.getY(), center.getZ() + j);
								}
								if (face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
									toReplace[(2*size-1)*(i+size-1)+(j+size-1)]=new BlockPos(center.getX() + i, center.getY() + j, center.getZ());
								}
								if (face == EnumFacing.EAST || face == EnumFacing.WEST) {
									toReplace[(2*size-1)*(i+size-1)+(j+size-1)]=new BlockPos(center.getX(), center.getY() + i, center.getZ() + j);
								}
							}
						}
						BlockControl.logUndo(player, toReplace);
						BlockControl.changeBlocks(player, toReplace, fullBlockState);
					}
					
					

		

			}

		}
	}


}
