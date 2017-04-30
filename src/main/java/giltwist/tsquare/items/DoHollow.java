package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoHollow {

	public static void activate(EntityPlayer player, boolean completeState) {
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

								boolean shouldChange = true;
								BlockPos currentBlock = new BlockPos(center.getX() + i, center.getY() + j, center.getZ() + k);

								for (EnumFacing f : EnumFacing.values()) {
									IBlockState tempState = player.getEntityWorld().getBlockState(currentBlock.offset(f));

									if (tempState.getBlock() == net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
										shouldChange = false;
									}
									if (player.isSneaking()&&(tempState.getMaterial()==Material.WATER || tempState.getMaterial()==Material.LAVA)) {
										shouldChange = false;
									}

								}
								if (shouldChange) {
									blocksToChange.add(currentBlock);
								}
							}
						}
					}
				}
				BlockPos[] toReplace = blocksToChange.toArray(new BlockPos[blocksToChange.size()]);
				BlockControl.logUndo(player, toReplace);
				BlockControl.changeBlocks(player, toReplace, completeState);
			}

		}
	}

}
