package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoRuler {

	public static void setPoint(EntityPlayer player, boolean isRightClick) {
		if (!player.isSwingInProgress) {
			int whichPoint;

			if (isRightClick) {
				whichPoint = 2;
			} else {
				whichPoint = 1;
			}

			BlockPos point = FindLookedBlock.getBlockPos(player);
			if (point == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {
				player.sendMessage(new TextComponentString("Ruler point " + whichPoint + " saved."));
				player.getEntityData().setInteger("TSquareRuler" + whichPoint + "X", point.getX());
				player.getEntityData().setInteger("TSquareRuler" + whichPoint + "Y", point.getY());
				player.getEntityData().setInteger("TSquareRuler" + whichPoint + "Z", point.getZ());
			}
		}

	}

	public static void activate(EntityPlayer player) {
		if (!player.isSwingInProgress) {

			if (!player.getEntityData().hasKey("TSquareRuler1X") || !player.getEntityData().hasKey("TSquareRuler2X")) {
				player.sendMessage(new TextComponentString("Please set two points."));
			} else {

				BlockPos pointOne = new BlockPos(player.getEntityData().getInteger("TSquareRuler1X"), player.getEntityData().getInteger("TSquareRuler1Y"), player.getEntityData().getInteger("TSquareRuler1Z"));
				BlockPos pointTwo = new BlockPos(player.getEntityData().getInteger("TSquareRuler2X"), player.getEntityData().getInteger("TSquareRuler2Y"), player.getEntityData().getInteger("TSquareRuler2Z"));

				int xLength = Math.abs(pointTwo.getX() - pointOne.getX())+1; //Never have 0 dimension with +1
				int yLength = Math.abs(pointTwo.getY() - pointOne.getY())+1;
				int zLength = Math.abs(pointTwo.getZ() - pointOne.getZ())+1;
				double linearDistance = Math.sqrt(xLength * xLength + yLength * yLength + zLength * zLength);
				
				
				player.sendMessage(new TextComponentString("Distance:" +linearDistance));
				player.sendMessage(new TextComponentString("* X-axis: " +xLength));
				player.sendMessage(new TextComponentString("* Y-axis: " +yLength));
				player.sendMessage(new TextComponentString("* Z-axis: " +zLength));
				
			

			}

		}
	}


}
