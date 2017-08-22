package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import giltwist.tsquare.TSquare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoGetBlock {

	public static void activate(EntityPlayer player, boolean isRightClick) {

		if (!player.isSwingInProgress&&!isRightClick) {

			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				if (!TSquare.BLOCKBLACKLIST.contains(player.getEntityWorld().getBlockState(targetBlock).getBlock())) {
					
					if (player.inventory.getFirstEmptyStack() != -1) {
						player.world.getBlockState(targetBlock).getBlock().dropBlockAsItem(player.world, player.getPosition(), player.world.getBlockState(targetBlock), 1);
						//tempStack.setCount(tempStack.getMaxStackSize());
						//player.inventory.addItemStackToInventory(tempStack);

					}
					else
					{
						player.sendMessage(new TextComponentString("Error: Inventory is full"));
					}
				} else {
					player.sendMessage(new TextComponentString("Error: Target block is on global blacklist"));
				}

			}
		}
	}
}
