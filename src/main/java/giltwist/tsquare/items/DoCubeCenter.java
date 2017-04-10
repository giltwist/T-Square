package giltwist.tsquare.items;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoCubeCenter {

	public static void activate(EntityPlayer player, boolean fullBlockState) {
		if (!player.isSwingInProgress) {
			EnumFacing face;
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				

					face = FindLookedBlock.getBlockFace(player);
					int size = player.getHeldItemMainhand().getCount();

					if (face != null) {
						if (player.isSneaking()) {
							center = center.offset(face);
						}
						BlockPos[] toReplace = new BlockPos[(2 * size - 1) * (2 * size - 1)* (2 * size - 1)];
						int temp;
						for (int i = -1 * (size - 1); i <= (size - 1); i++) {
							for (int j = -1 * (size - 1); j <= (size - 1); j++) {
								for (int k = -1 * (size - 1); k <= (size - 1); k++) {
									temp = (2 * size - 1) * (2 * size - 1) * (i + size - 1) + (2 * size - 1) * (j + size - 1) + (k + size - 1);
									toReplace[temp] = new BlockPos(center.getX() + i, center.getY() + j, center.getZ() + k);
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
