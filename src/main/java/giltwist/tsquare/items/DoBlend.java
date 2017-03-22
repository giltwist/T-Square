package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoBlend {

	public static void sphere(EntityPlayer player, boolean includeAir) {
		if (!player.isSwingInProgress) {
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				int size = player.getHeldItemMainhand().stackSize;

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
				BlockControl.blendBlocks(player, toReplace, includeAir);
			}

		}
	}

}
