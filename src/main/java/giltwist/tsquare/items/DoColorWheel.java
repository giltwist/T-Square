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
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {
				
				placemat = player.getEntityWorld().getBlockState(targetBlock).getBlock();

				placematState = placemat.getStateFromMeta(meta);
				player.getEntityWorld().setBlockState(targetBlock, placematState);

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
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {
				
				placemat = player.getEntityWorld().getBlockState(targetBlock).getBlock();
				
				int newMeta=placemat.getMetaFromState(player.getEntityWorld().getBlockState(targetBlock))+meta;
				newMeta=Math.floorMod(newMeta, 16);
				placematState = placemat.getStateFromMeta(newMeta);
				player.getEntityWorld().setBlockState(targetBlock, placematState);

			}
		}
	}


}
