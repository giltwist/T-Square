package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoLine {

	public static void setPoint(EntityPlayer player, int whichPoint) {

		BlockPos point = FindLookedBlock.getBlockPos(player);
		if (point==null){
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		}else{
		player.addChatMessage(new TextComponentString("Line point "+whichPoint+" saved."));
		player.getEntityData().setInteger("TSquareLine" + whichPoint + "X", point.getX());
		player.getEntityData().setInteger("TSquareLine" + whichPoint + "Y", point.getY());
		player.getEntityData().setInteger("TSquareLine" + whichPoint + "Z", point.getZ());
		}

	}

	public static void material(EntityPlayer player) {
		if (!player.isSwingInProgress) {

			if (!player.getEntityData().hasKey("TSquareLine1X") || !player.getEntityData().hasKey("TSquareLine2X")) {
				player.addChatMessage(new TextComponentString("Please set two points."));
			} else {

				BlockPos pointOne = new BlockPos(player.getEntityData().getInteger("TSquareLine1X"), player.getEntityData().getInteger("TSquareLine1Y"), player.getEntityData().getInteger("TSquareLine1Z"));
				BlockPos pointTwo = new BlockPos(player.getEntityData().getInteger("TSquareLine2X"), player.getEntityData().getInteger("TSquareLine2Y"), player.getEntityData().getInteger("TSquareLine2Z"));

				//BlockPos minPoint = new BlockPos(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()), Math.min(pointOne.getZ(), pointTwo.getZ()));
				//BlockPos maxPoint = new BlockPos(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()), Math.max(pointOne.getZ(), pointTwo.getZ()));

				int xLength = pointTwo.getX()-pointOne.getX();
				int yLength = pointTwo.getY()-pointOne.getY();
				int zLength = pointTwo.getZ()-pointOne.getZ();
				
				double linearDistance = Math.ceil(Math.sqrt(xLength*xLength+yLength*yLength+zLength*zLength));
				
				if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

					Set<BlockPos> blocksToChange = new HashSet<BlockPos>();

					for (int i = 0; i <= linearDistance; i++) {
					
								blocksToChange.add(new BlockPos(pointOne.getX() + i*xLength/linearDistance, pointOne.getY() + i*yLength/linearDistance, pointOne.getZ()+i*zLength/linearDistance));					
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

		if (!player.getEntityData().hasKey("TSquareLine1X") || !player.getEntityData().hasKey("TSquareLine2X")) {
			player.addChatMessage(new TextComponentString("Please set two points."));
		} else {

			BlockPos pointOne = new BlockPos(player.getEntityData().getInteger("TSquareLine1X"), player.getEntityData().getInteger("TSquareLine1Y"), player.getEntityData().getInteger("TSquareLine1Z"));
			BlockPos pointTwo = new BlockPos(player.getEntityData().getInteger("TSquareLine2X"), player.getEntityData().getInteger("TSquareLine2Y"), player.getEntityData().getInteger("TSquareLine2Z"));

			//BlockPos minPoint = new BlockPos(Math.min(pointOne.getX(), pointTwo.getX()), Math.min(pointOne.getY(), pointTwo.getY()), Math.min(pointOne.getZ(), pointTwo.getZ()));
			//BlockPos maxPoint = new BlockPos(Math.max(pointOne.getX(), pointTwo.getX()), Math.max(pointOne.getY(), pointTwo.getY()), Math.max(pointOne.getZ(), pointTwo.getZ()));

			int xLength = pointTwo.getX()-pointOne.getX();
			int yLength = pointTwo.getY()-pointOne.getY();
			int zLength = pointTwo.getZ()-pointOne.getZ();
			
			double linearDistance = Math.ceil(Math.sqrt(xLength*xLength+yLength*yLength+zLength*zLength));
			
			if (player.getEntityData().hasKey("TSquarePlaceState")) {

				Set<BlockPos> blocksToChange = new HashSet<BlockPos>();

				for (int i = 0; i <= linearDistance; i++) {
				
							blocksToChange.add(new BlockPos(pointOne.getX() + i*xLength/linearDistance, pointOne.getY() + i*yLength/linearDistance, pointOne.getZ()+i*zLength/linearDistance));					
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
