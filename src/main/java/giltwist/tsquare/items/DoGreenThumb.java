package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoGreenThumb {

	public static void activate(EntityPlayer player) {
		if (!player.isSwingInProgress) {
			EnumFacing face;
			BlockPos center = FindLookedBlock.getBlockPos(player);
			if (center == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {

				

					face = FindLookedBlock.getBlockFace(player);
					int size = player.getHeldItemMainhand().getCount();

					if (face != null) {
						if (player.isSneaking()) {
							center = center.offset(face);
						}
				
						for (int i = -1 * (size - 1); i <= (size - 1); i++) {
							for (int j = -1 * (size - 1); j <= (size - 1); j++) {
								for (int k = -1 * (size - 1); k <= (size - 1); k++) {
									if ((i * i + j * j + k * k) - 1 <= (size - 1) * (size - 1)) {

										BlockPos target =  new BlockPos(center.getX() + i, center.getY() + j, center.getZ() + k);
										IBlockState iblockstate = player.getEntityWorld().getBlockState(target);
										
										
										//This section adapted from net.minecraft.item.ItemDye.applybonemeal
								        if (iblockstate.getBlock() instanceof IGrowable)
								        {
								            IGrowable igrowable = (IGrowable)iblockstate.getBlock();

								            if (igrowable.canGrow(player.getEntityWorld(), target, iblockstate, player.getEntityWorld().isRemote))
								            {
								                if (!player.getEntityWorld().isRemote)
								                {
								                    if (igrowable.canUseBonemeal(player.getEntityWorld(), player.getEntityWorld().rand, target, iblockstate))
								                    {
								                        igrowable.grow(player.getEntityWorld(), player.getEntityWorld().rand, target, iblockstate);
								                    }

								                    
								                }

								           
								            }
								        }
									}
								}
							}
						}

					}

				} 

			}

		}
	

	

}
