package giltwist.tsquare.items;

import java.util.HashSet;
import java.util.Set;

import giltwist.tsquare.BlockControl;
import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;

public class DoCircle3Points {

	public static void setPoint(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			int whichPoint;

			if (!player.getEntityData().hasKey("TSquareCircle1X")) {
				whichPoint = 1;
			} else {
				if (!player.getEntityData().hasKey("TSquareCircle2X")) {
					whichPoint = 2;
				} else {
					if (!player.getEntityData().hasKey("TSquareCircle3X")) {
						whichPoint = 3;
					} else {
						whichPoint = 4;
					}
				}

			}

			BlockPos point = FindLookedBlock.getBlockPos(player);
			if (point == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (whichPoint == 4) {

					player.getEntityData().setInteger("TSquareCircle1X", player.getEntityData().getInteger("TSquareCircle2X"));
					player.getEntityData().setInteger("TSquareCircle1Y", player.getEntityData().getInteger("TSquareCircle2Y"));
					player.getEntityData().setInteger("TSquareCircle1Z", player.getEntityData().getInteger("TSquareCircle2Z"));

					player.getEntityData().setInteger("TSquareCircle2X", player.getEntityData().getInteger("TSquareCircle3X"));
					player.getEntityData().setInteger("TSquareCircle2Y", player.getEntityData().getInteger("TSquareCircle3Y"));
					player.getEntityData().setInteger("TSquareCircle2Z", player.getEntityData().getInteger("TSquareCircle3Z"));

					player.addChatMessage(new TextComponentString("Deleted oldest point in favor of new point 3."));
					player.getEntityData().setInteger("TSquareCircle3X", point.getX());
					player.getEntityData().setInteger("TSquareCircle3Y", point.getY());
					player.getEntityData().setInteger("TSquareCircle3Z", point.getZ());

				} else {

					player.addChatMessage(new TextComponentString("Circle point " + whichPoint + " saved."));
					player.getEntityData().setInteger("TSquareCircle" + whichPoint + "X", point.getX());
					player.getEntityData().setInteger("TSquareCircle" + whichPoint + "Y", point.getY());
					player.getEntityData().setInteger("TSquareCircle" + whichPoint + "Z", point.getZ());
				}
			}
		}

	}

	public static void clearPoints(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			Set<String> toRemove = new HashSet<String>();
			for (String key : player.getEntityData().getKeySet()) {

				if (key.startsWith("TSquareCircle")) {

					player.addChatMessage(new TextComponentString("Removed: " + key.toString()));
					toRemove.add(key);
				}

			}
			player.getEntityData().getKeySet().removeAll(toRemove);

			player.addChatMessage(new TextComponentString("Done."));
		}

	}

	public static void activate(EntityPlayer player, boolean fullBlockState) {
		if (!player.isSwingInProgress) {

			if (!player.getEntityData().hasKey("TSquareCircle1X") || !player.getEntityData().hasKey("TSquareCircle2X") || !player.getEntityData().hasKey("TSquareCircle3X")) {
				player.addChatMessage(new TextComponentString("Please set three points."));
			} else {

				BlockPos pointOne = new BlockPos(player.getEntityData().getInteger("TSquareCircle1X"), player.getEntityData().getInteger("TSquareCircle1Y"), player.getEntityData().getInteger("TSquareCircle1Z"));
				BlockPos pointTwo = new BlockPos(player.getEntityData().getInteger("TSquareCircle2X"), player.getEntityData().getInteger("TSquareCircle2Y"), player.getEntityData().getInteger("TSquareCircle2Z"));
				BlockPos pointThree = new BlockPos(player.getEntityData().getInteger("TSquareCircle3X"), player.getEntityData().getInteger("TSquareCircle3Y"), player.getEntityData().getInteger("TSquareCircle3Z"));

				Vec3d oneTwo = new Vec3d(pointOne.getX() - pointTwo.getX(), pointOne.getY() - pointTwo.getY(), pointOne.getZ() - pointTwo.getZ());
				Vec3d oneThree = new Vec3d(pointOne.getX() - pointThree.getX(), pointOne.getY() - pointThree.getY(), pointOne.getZ() - pointThree.getZ());
				Vec3d twoThree = new Vec3d(pointTwo.getX() - pointThree.getX(), pointTwo.getY() - pointThree.getY(), pointTwo.getZ() - pointThree.getZ());
				Vec3d twoCrossThree = oneTwo.crossProduct(oneThree);

				Vec3d oneCrossThree = oneTwo.crossProduct(twoThree);
				Vec3d normalizedTwoCrossThree = twoCrossThree.normalize();

				int radius = (int) Math.ceil((oneTwo.lengthVector() * oneThree.lengthVector() * twoThree.lengthVector()) / (2 * oneCrossThree.lengthVector()));

				double alpha = (twoThree.lengthSquared() * dotProduct(oneTwo, oneThree)) / (2 * oneCrossThree.lengthSquared());
				double beta = (oneThree.lengthSquared() * dotProduct(oneTwo.scale(-1), twoThree)) / (2 * oneCrossThree.lengthSquared());
				double gamma = (oneTwo.lengthSquared() * dotProduct(oneThree.scale(-1), twoThree.scale(-1))) / (2 * oneCrossThree.lengthSquared());

				BlockPos circumcenter = new BlockPos(alpha * pointOne.getX() + beta * pointTwo.getX() + gamma * pointThree.getX(), alpha * pointOne.getY() + beta * pointTwo.getY() + gamma * pointThree.getY(),
						alpha * pointOne.getZ() + beta * pointTwo.getZ() + gamma * pointThree.getZ());
				//

				Set<BlockPos> blocksToChange = new HashSet<BlockPos>();

				for (int i = -radius; i <= radius; i++) {
					for (int j = -radius; j <= radius; j++) {
						for (int k = -radius; k <= radius; k++) {

							boolean abovePlane = false;
							boolean belowPlane = false;
							boolean insideCircle = false;

							BlockPos tempPos = new BlockPos(circumcenter.getX() + i, circumcenter.getY() + j, circumcenter.getZ() + k);
							if (i * i + j * j + k * k <= radius * radius) {
								insideCircle = true;
							}

							// check all 8 vertices of block
							// .866 adds slight directional bias to prevent
							// double-counting
							for (int m = 0; m <= 1; m++) {
								for (int n = 0; n <= 1; n++) {
									for (int o = 0; o <= 1; o++) {
										Vec3d tempVec = new Vec3d(pointOne.getX() - tempPos.getX() - .866 * m, pointOne.getY() - tempPos.getY() - .866 * n, pointOne.getZ() - tempPos.getZ() - .866 * o);
										Vec3d normalizedTempVec = tempVec.normalize();
										double nomalDotTemp = dotProduct(normalizedTwoCrossThree, normalizedTempVec);
										if (nomalDotTemp >= 0) {
											abovePlane = true;
										}
										if (nomalDotTemp <= 0) {
											belowPlane = true;
										}

									}
								}
							}

							// if one corner above plane and another below
							// plane, then plane intersects
							if (abovePlane && belowPlane && insideCircle) {
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
		double d = a.xCoord * b.xCoord + a.yCoord * b.yCoord + a.zCoord * b.zCoord;
		return d;
	}

}
