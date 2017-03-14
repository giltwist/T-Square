package giltwist.tsquare.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class DoRotateBlock {

	public static void rotation(EntityPlayer player, BlockPos blockpos, EnumFacing face){
		
		if (!player.isSwingInProgress) {
			player.getEntityWorld().getBlockState(blockpos).getBlock().rotateBlock(player.getEntityWorld(), blockpos, face);
		}
	}
	
}
