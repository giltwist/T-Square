package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import giltwist.tsquare.TSquare;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoColorWheel {

	@SuppressWarnings("deprecation")
	public static void setMeta(EntityPlayer player, int meta) {

		IBlockState placematState;
		Block placemat;

		if (!player.isSwingInProgress) {
			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (!TSquare.BLOCKBLACKLIST.contains(player.worldObj.getBlockState(targetBlock).getBlock())) {
					placemat = player.worldObj.getBlockState(targetBlock).getBlock();

					placematState = placemat.getStateFromMeta(meta);
					player.worldObj.setBlockState(targetBlock, placematState);
				} else {
					player.addChatMessage(new TextComponentString("Error: Target block is on global blacklist"));
				}

			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void adjustMeta(EntityPlayer player, int meta) {

		IBlockState placematState;
		Block placemat;

		if (!player.isSwingInProgress) {
			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {
				if (!TSquare.BLOCKBLACKLIST.contains(player.worldObj.getBlockState(targetBlock).getBlock())) {
					placemat = player.worldObj.getBlockState(targetBlock).getBlock();

					int newMeta = placemat.getMetaFromState(player.worldObj.getBlockState(targetBlock)) + meta;
					newMeta = Math.floorMod(newMeta, 16);
					placematState = placemat.getStateFromMeta(newMeta);
					player.worldObj.setBlockState(targetBlock, placematState);
				} else {
					player.addChatMessage(new TextComponentString("Error: Target block is on global blacklist"));
				}
			}
		}
	}

}
