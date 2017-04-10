package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoSphereCenter {

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
						Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
						for (int i = -1 * (size - 1); i <= (size - 1); i++) {
							for (int j = -1 * (size - 1); j <= (size - 1); j++) {
								for (int k = -1 * (size - 1); k <= (size - 1); k++) {
									if ((i * i + j * j + k * k) - 1 <= (size - 1) * (size - 1)) {

										blocksToChange.add(new BlockPos(center.getX() + i, center.getY() + j, center.getZ() + k));
									}
								}
							}
						}
						BlockPos[] toReplace = blocksToChange.toArray(new BlockPos[blocksToChange.size()]);
						BlockControl.logUndo(player, toReplace);
						BlockControl.changeBlocks(player, toReplace, fullBlockState);
					}

				

			}

		}
	}

	
}
