package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
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
				
				placemat = player.worldObj.getBlockState(targetBlock).getBlock();

				placematState = placemat.getStateFromMeta(meta);
				player.worldObj.setBlockState(targetBlock, placematState);

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
				
				placemat = player.worldObj.getBlockState(targetBlock).getBlock();
				
				int newMeta=placemat.getMetaFromState(player.worldObj.getBlockState(targetBlock))+meta;
				newMeta=Math.floorMod(newMeta, 16);
				placematState = placemat.getStateFromMeta(newMeta);
				player.worldObj.setBlockState(targetBlock, placematState);

			}
		}
	}


}
