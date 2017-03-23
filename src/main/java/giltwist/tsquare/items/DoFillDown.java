package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoFillDown {

	public static void material(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

					int size = player.getHeldItemMainhand().stackSize;

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
					BlockControl.changeBlocks(player, toReplace, false);

				} else {
					player.addChatMessage(new TextComponentString("No material saved"));
				}

			}

		}
	}

	public static void blockstate(EntityPlayer player) {

		
		BlockPos center = FindLookedBlock.getBlockPos(player);
		if (center == null) {
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		} else {

			if (player.getEntityData().hasKey("TSquarePlaceState")) {

				int size = player.getHeldItemMainhand().stackSize;

			

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
				BlockControl.changeBlocks(player, toReplace, true);

			} else {
				player.addChatMessage(new TextComponentString("No blockstate saved"));
			}

		}
	}

}
