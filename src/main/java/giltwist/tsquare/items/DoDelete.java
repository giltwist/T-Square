package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import giltwist.tsquare.TSquare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoDelete {


	public static void activate(EntityPlayer player) {



		BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

		if (targetBlock == null) {
			player.sendMessage(new TextComponentString("No block found within 200m"));
		} else {

			if (!TSquare.BLOCKBLACKLIST.contains(player.getEntityWorld().getBlockState(targetBlock).getBlock())) {
				
				player.getEntityWorld().setBlockToAir(targetBlock);
			}else {
				player.sendMessage(new TextComponentString("Error: Target block is on global blacklist"));
			}

		}
	}

	

}
