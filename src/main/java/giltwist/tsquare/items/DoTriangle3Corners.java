package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;

public class DoTriangle3Corners {

	public static void setPoint(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			int whichPoint;

			if (!player.getEntityData().hasKey("TSquareTriangle1X")) {
				whichPoint = 1;
			} else {
				if (!player.getEntityData().hasKey("TSquareTriangle2X")) {
					whichPoint = 2;
				} else {
					if (!player.getEntityData().hasKey("TSquareTriangle3X")) {
						whichPoint = 3;
					} else {
						whichPoint = 4;
					}
				}

			}

			BlockPos point = FindLookedBlock.getBlockPos(player);
			if (point == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (whichPoint == 4) {

					player.getEntityData().setInteger("TSquareTriangle1X", player.getEntityData().getInteger("TSquareTriangle2X"));
					player.getEntityData().setInteger("TSquareTriangle1Y", player.getEntityData().getInteger("TSquareTriangle2Y"));
					player.getEntityData().setInteger("TSquareTriangle1Z", player.getEntityData().getInteger("TSquareTriangle2Z"));

					player.getEntityData().setInteger("TSquareTriangle2X", player.getEntityData().getInteger("TSquareTriangle3X"));
					player.getEntityData().setInteger("TSquareTriangle2Y", player.getEntityData().getInteger("TSquareTriangle3Y"));
					player.getEntityData().setInteger("TSquareTriangle2Z", player.getEntityData().getInteger("TSquareTriangle3Z"));

					player.sendMessage(new TextComponentString("Deleted oldest point in favor of new point 3."));
					player.getEntityData().setInteger("TSquareTriangle3X", point.getX());
					player.getEntityData().setInteger("TSquareTriangle3Y", point.getY());
					player.getEntityData().setInteger("TSquareTriangle3Z", point.getZ());

				} else {

					player.sendMessage(new TextComponentString("Triangle point " + whichPoint + " saved."));
					player.getEntityData().setInteger("TSquareTriangle" + whichPoint + "X", point.getX());
					player.getEntityData().setInteger("TSquareTriangle" + whichPoint + "Y", point.getY());
					player.getEntityData().setInteger("TSquareTriangle" + whichPoint + "Z", point.getZ());
				}
			}
		}

	}

	public static void clearPoints(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			Set<String> toRemove = new HashSet<String>();
			for (String key : player.getEntityData().getKeySet()) {

				if (key.startsWith("TSquareTriangle")) {

					player.sendMessage(new TextComponentString("Removed: " + key.toString()));
					toRemove.add(key);
				}

			}
			player.getEntityData().getKeySet().removeAll(toRemove);

			player.sendMessage(new TextComponentString("Done."));
		}

	}

	public static void activate(EntityPlayer player, boolean fullBlockState) {
		if (!player.isSwingInProgress) {

			if (!player.getEntityData().hasKey("TSquareTriangle1X") || !player.getEntityData().hasKey("TSquareTriangle2X") || !player.getEntityData().hasKey("TSquareTriangle3X")) {
				player.sendMessage(new TextComponentString("Please set three points."));
			} else {

				BlockPos pointOne = new BlockPos(player.getEntityData().getInteger("TSquareTriangle1X"), player.getEntityData().getInteger("TSquareTriangle1Y"), player.getEntityData().getInteger("TSquareTriangle1Z"));
				BlockPos pointTwo = new BlockPos(player.getEntityData().getInteger("TSquareTriangle2X"), player.getEntityData().getInteger("TSquareTriangle2Y"), player.getEntityData().getInteger("TSquareTriangle2Z"));
				BlockPos pointThree = new BlockPos(player.getEntityData().getInteger("TSquareTriangle3X"), player.getEntityData().getInteger("TSquareTriangle3Y"), player.getEntityData().getInteger("TSquareTriangle3Z"));

				BlockPos minPoint = new BlockPos(Math.min(Math.min(pointOne.getX(), pointTwo.getX()), pointThree.getX()), Math.min(Math.min(pointOne.getY(), pointTwo.getY()), pointThree.getY()),
						Math.min(Math.min(pointOne.getZ(), pointTwo.getZ()), pointThree.getZ()));
				BlockPos maxPoint = new BlockPos(Math.max(Math.max(pointOne.getX(), pointTwo.getX()), pointThree.getX()), Math.max(Math.max(pointOne.getY(), pointTwo.getY()), pointThree.getY()),
						Math.max(Math.max(pointOne.getZ(), pointTwo.getZ()), pointThree.getZ()));

				int xLength = maxPoint.getX() - minPoint.getX();
				int yLength = maxPoint.getY() - minPoint.getY();
				int zLength = maxPoint.getZ() - minPoint.getZ();

				Vec3d oneTwo = new Vec3d(pointOne.getX() - pointTwo.getX(), pointOne.getY() - pointTwo.getY(), pointOne.getZ() - pointTwo.getZ());
				Vec3d oneThree = new Vec3d(pointOne.getX() - pointThree.getX(), pointOne.getY() - pointThree.getY(), pointOne.getZ() - pointThree.getZ());
				Vec3d twoCrossThree = oneTwo.crossProduct(oneThree);
				Vec3d threeCrossTwo = oneThree.crossProduct(oneTwo);
				Vec3d normalizedTwoCrossThree = twoCrossThree.normalize();

				Set<BlockPos> blocksToChange = new HashSet<BlockPos>();

				for (int i = 0; i <= xLength; i++) {
					for (int j = 0; j <= yLength; j++) {
						for (int k = 0; k <= zLength; k++) {

							boolean abovePlane = false;
							boolean belowPlane = false;
							boolean insideTriangle = false;

							BlockPos tempPos = new BlockPos(minPoint.getX() + i, minPoint.getY() + j, minPoint.getZ() + k);
							
							//check all 8 vertices of block
							// .866 adds slight directional bias to prevent double-counting
							for (int m = 0; m <= 1; m++) {
								for (int n = 0; n <= 1; n++) {
									for (int o = 0; o <= 1; o++) {
										Vec3d tempVec = new Vec3d(pointOne.getX() - tempPos.getX()-.866*m, pointOne.getY() - tempPos.getY()-.866*n, pointOne.getZ() - tempPos.getZ()-.866*o);
										Vec3d normalizedTempVec = tempVec.normalize();
										double nomalDotTemp = dotProduct(normalizedTwoCrossThree,normalizedTempVec);
										if (nomalDotTemp>=0){
											abovePlane=true;
										}
										if (nomalDotTemp<=0){
											belowPlane=true;
										}
										
										Vec3d twoCrossTemp=oneTwo.crossProduct(tempVec);
										Vec3d threeCrossTemp=oneThree.crossProduct(tempVec);
										
										if (dotProduct(twoCrossTemp,twoCrossThree)>=0&&dotProduct(threeCrossTemp,threeCrossTwo)>=0&&twoCrossTemp.lengthVector()+threeCrossTemp.lengthVector()<=twoCrossThree.lengthVector()){
											insideTriangle=true;
										}
									}
								}
							}

							// if one corner above plane and another below plane, then plane intersects
							if (abovePlane&&belowPlane&&insideTriangle) {
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
	
	private static double dotProduct(Vec3d a, Vec3d b) {
		double d= a.xCoord * b.xCoord + a.yCoord * b.yCoord + a.zCoord * b.zCoord;
		return d;
	}

}
