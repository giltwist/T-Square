package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoSquareCenter {

	public static void material(EntityPlayer player) {

		EnumFacing face;
		BlockPos center = FindLookedBlock.getBlockPos(player);
		if (center == null) {
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		} else {
			// player.addChatMessage(new TextComponentString("You are looking
			// at: " +
			// player.worldObj.getBlockState(center).getBlock().getLocalizedName()));
			// player.addChatMessage(new TextComponentString("Keys: " +
			// player.getEntityData().toString() ));
			if (player.getEntityData().hasKey("TSquarePlaceMaterial")) {

				IBlockState placematState = Block.getBlockFromName(player.getEntityData().getString("TSquarePlaceMaterial")).getDefaultState();
				// player.worldObj.setBlockState(center, placematState);
				face = FindLookedBlock.getBlockFace(player);
				int size = player.getHeldItemMainhand().stackSize;
				// Set<BlockPos> toReplace=new HashSet<BlockPos>();

				if (face != null) {
					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {

							if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
								// toReplace.add(new
								// BlockPos(center.getX()+i,center.getY(),center.getZ()+j));
								player.worldObj.setBlockState(new BlockPos(center.getX() + i, center.getY(), center.getZ() + j), placematState);
							}
							if (face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
								// toReplace.add(new
								// BlockPos(center.getX()+i,center.getY(),center.getZ()+j));
								player.worldObj.setBlockState(new BlockPos(center.getX() + i, center.getY() + j, center.getZ()), placematState);
							}
							if (face == EnumFacing.EAST || face == EnumFacing.WEST) {
								// toReplace.add(new
								// BlockPos(center.getX()+i,center.getY(),center.getZ()+j));
								player.worldObj.setBlockState(new BlockPos(center.getX(), center.getY() + i, center.getZ() + j), placematState);
							}
						}
					}
				}

			} else {
				player.addChatMessage(new TextComponentString("No material saved"));
			}

		}

	}

	public static void blockstate(EntityPlayer player) {

		EnumFacing face;
		BlockPos center = FindLookedBlock.getBlockPos(player);
		if (center == null) {
			player.addChatMessage(new TextComponentString("No block found within 200m"));
		} else {
			// player.addChatMessage(new TextComponentString("You are looking
			// at: " +
			// player.worldObj.getBlockState(center).getBlock().getLocalizedName()));
			// player.addChatMessage(new TextComponentString("Keys: " +
			// player.getEntityData().toString() ));
			if (player.getEntityData().hasKey("TSquarePlaceState")) {

				Block placemat;
				IBlockState placematState;
				
				placemat = net.minecraft.block.Block.getBlockFromName(player.getEntityData().getString("TSquarePlaceMaterial"));
				placematState = placemat.getStateFromMeta(player.getEntityData().getInteger("TSquarePlaceState"));
				// player.worldObj.setBlockState(center, placematState);
				face = FindLookedBlock.getBlockFace(player);
				int size = player.getHeldItemMainhand().stackSize;
				// Set<BlockPos> toReplace=new HashSet<BlockPos>();

				if (face != null) {
					for (int i = -1 * (size - 1); i <= (size - 1); i++) {
						for (int j = -1 * (size - 1); j <= (size - 1); j++) {

							if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
								// toReplace.add(new
								// BlockPos(center.getX()+i,center.getY(),center.getZ()+j));
								player.worldObj.setBlockState(new BlockPos(center.getX() + i, center.getY(), center.getZ() + j), placematState);
							}
							if (face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
								// toReplace.add(new
								// BlockPos(center.getX()+i,center.getY(),center.getZ()+j));
								player.worldObj.setBlockState(new BlockPos(center.getX() + i, center.getY() + j, center.getZ()), placematState);
							}
							if (face == EnumFacing.EAST || face == EnumFacing.WEST) {
								// toReplace.add(new
								// BlockPos(center.getX()+i,center.getY(),center.getZ()+j));
								player.worldObj.setBlockState(new BlockPos(center.getX(), center.getY() + i, center.getZ() + j), placematState);
							}
						}
					}
				}

			} else {
				player.addChatMessage(new TextComponentString("No blockstate saved"));
			}

		}
	}

}
