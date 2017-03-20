package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoCuboid2Corners {

	public static void setPoint(EntityPlayer player, int whichPoint) {

		BlockPos point = FindLookedBlock.getBlockPos(player);
		if (point==null){
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		}else{
		player.addChatMessage(new TextComponentString("Cuboid point "+whichPoint+" saved."));
		player.getEntityData().setInteger("TSquareCuboid" + whichPoint + "X", point.getX());
		player.getEntityData().setInteger("TSquareCuboid" + whichPoint + "Y", point.getY());
		player.getEntityData().setInteger("TSquareCuboid" + whichPoint + "Z", point.getZ());
		}

	}

	public static void material(EntityPlayer player) {
		if (!player.isSwingInProgress) {

			if (!player.getEntityData().hasKey("TSquareCuboid1X") || !player.getEntityData().hasKey("TSquareCuboid2X")) {
				player.addChatMessage(new TextComponentString("Please set two points."));
			} else {

				BlockPos pointOne = new BlockPos(player.getEntityData().getInteger("TSquareCuboid1X"), player.getEntityData().getInteger("TSquareCuboid1Y"), player.getEntityData().getInteger("TSquareCuboid1Z"));
				BlockPos pointTwo = new BlockPos(player.getEntityData().getInteger("TSquareCuboid2X"), player.getEntityData().getInteger("TSquareCuboid2Y"), player.getEntityData().getInteger("TSquareCuboid2Z"));

				BlockPos minPoint = new BlockPos(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()), Math.min(pointOne.getZ(), pointTwo.getZ()));
				BlockPos maxPoint = new BlockPos(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()), Math.max(pointOne.getZ(), pointTwo.getZ()));

				int xLength = maxPoint.getX() - minPoint.getX();
				int yLength = maxPoint.getY() - minPoint.getY();
				int zLength = maxPoint.getZ() - minPoint.getZ();

				if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

					Set<BlockPos> blocksToChange = new HashSet<BlockPos>();

					for (int i = 0; i <= xLength; i++) {
						for (int j = 0; j <= yLength; j++) {
							for (int k = 0; k <= zLength; k++) {

								blocksToChange.add(new BlockPos(minPoint.getX() + i, minPoint.getY() + j, minPoint.getZ() + k));
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

		if (!player.getEntityData().hasKey("TSquareCuboid1X") || !player.getEntityData().hasKey("TSquareCuboid2X")) {
			player.addChatMessage(new TextComponentString("Please set two points."));
		} else {

			BlockPos pointOne = new BlockPos(player.getEntityData().getInteger("TSquareCuboid1X"), player.getEntityData().getInteger("TSquareCuboid1Y"), player.getEntityData().getInteger("TSquareCuboid1Z"));
			BlockPos pointTwo = new BlockPos(player.getEntityData().getInteger("TSquareCuboid2X"), player.getEntityData().getInteger("TSquareCuboid2Y"), player.getEntityData().getInteger("TSquareCuboid2Z"));

			BlockPos minPoint = new BlockPos(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()), Math.min(pointOne.getZ(), pointTwo.getZ()));
			BlockPos maxPoint = new BlockPos(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()), Math.max(pointOne.getZ(), pointTwo.getZ()));

			int xLength = maxPoint.getX() - minPoint.getX();
			int yLength = maxPoint.getY() - minPoint.getY();
			int zLength = maxPoint.getZ() - minPoint.getZ();

			if (player.getEntityData().hasKey("TSquarePlaceState")) {

				Set<BlockPos> blocksToChange = new HashSet<BlockPos>();
				for (int i = 0; i <= xLength; i++) {
					for (int j = 0; j <= yLength; j++) {
						for (int k = 0; k <= zLength; k++) {

							blocksToChange.add(new BlockPos(minPoint.getX() + i, minPoint.getY() + j, minPoint.getZ() + k));
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
