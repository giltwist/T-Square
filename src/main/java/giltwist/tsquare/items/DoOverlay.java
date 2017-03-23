package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoOverlay {

	public static void material(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			int depth;
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

					int size = player.getHeldItemMainhand().stackSize;

					if (player.isSneaking()) {
						depth = 3;
					} else {
						depth = 1;
					}

					Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
					int yCheck = 0;
					BlockPos tempPos = null;
					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {
							// the -1 <= below prevents "nipples"
							if ((i * i + j * j) - 1 <= (size - 1) * (size - 1)) {

								yCheck = 0;

								for (int n = 0; n <= 10; n++) {
									tempPos = new BlockPos(center.getX() + i, center.getY() - n, center.getZ() + j);
									if (yCheck < depth && player.worldObj.getBlockState(tempPos).getBlock() != net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
										yCheck++;
										blocksToChange.add(tempPos);
									}

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

		int depth;
		BlockPos center = FindLookedBlock.getBlockPos(player);
		if (center == null) {
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		} else {

			if (player.getEntityData().hasKey("TSquarePlaceState")) {

				
				int size = player.getHeldItemMainhand().stackSize;

				if (player.isSneaking()) {
					depth = 3;
				} else {
					depth = 1;
				}

				Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
				int yCheck = 0;
				BlockPos tempPos = null;
				for (int i = -1 * (size - 1); i <= (size - 1); i++) {
					for (int j = -1 * (size - 1); j <= (size - 1); j++) {
						// the -1 <= below prevents "nipples"
						if ((i * i + j * j) - 1 <= (size - 1) * (size - 1)) {

							yCheck = 0;

							for (int n = 0; n <= 10; n++) {
								tempPos = new BlockPos(center.getX() + i, center.getY() - n, center.getZ() + j);
								if (yCheck==0 && player.worldObj.getBlockState(tempPos).getBlock() != net.minecraft.block.Block.getBlockFromName("minecraft:air")&& player.worldObj.getBlockState(tempPos.offset(EnumFacing.UP)).getBlock() == net.minecraft.block.Block.getBlockFromName("minecraft:air")) {
									yCheck++;
									blocksToChange.add(tempPos);
									if (depth==3){
										blocksToChange.add(tempPos.offset(EnumFacing.DOWN));
										blocksToChange.add(tempPos.offset(EnumFacing.DOWN).offset(EnumFacing.DOWN));
									}
									
								}

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
