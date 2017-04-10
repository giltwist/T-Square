package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoFillDown {

	public static void activate(EntityPlayer player, boolean fullBlockState) {
		if (!player.isSwingInProgress) {
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				

					int size = player.getHeldItemMainhand().getCount();

					Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
					BlockPos tempPos = null;
					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {
							// the -1 <= below prevents "nipples"
							if ((i * i + j * j) - 1 <= (size - 1) * (size - 1)) {

								for (int d = 0; d <= center.getY(); d++) {
									tempPos = new BlockPos(center.getX() + i, center.getY() - d, center.getZ() + j);
									blocksToChange.add(tempPos);
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
