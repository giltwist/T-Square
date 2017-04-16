package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import giltwist.tsquare.TSquare;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoEyeDropper {

	public static void activate(EntityPlayer player, boolean fullBlockState) {

		if (!player.isSwingInProgress) {

		
			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.sendMessage(new TextComponentString("No block found within 200m"));
			} else {
				

				if (!TSquare.BLOCKBLACKLIST.contains(player.getEntityWorld().getBlockState(targetBlock).getBlock())) {
					if (fullBlockState) {
						if (player.isSneaking()) {

							player.sendMessage(new TextComponentString("Replace Blockstate: " + player.getEntityWorld().getBlockState(targetBlock).toString()));
							player.getEntityData().setInteger("TSquareReplaceState", player.getEntityWorld().getBlockState(targetBlock).getBlock().getMetaFromState(player.getEntityWorld().getBlockState(targetBlock)));
							player.getEntityData().setString("TSquareReplaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());

						} else {
							player.sendMessage(new TextComponentString("Place Blockstate: " + player.getEntityWorld().getBlockState(targetBlock).toString()));
							player.getEntityData().setInteger("TSquarePlaceState", player.getEntityWorld().getBlockState(targetBlock).getBlock().getMetaFromState(player.getEntityWorld().getBlockState(targetBlock)));
							player.getEntityData().setString("TSquarePlaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());

						}

					} else {
						if (player.isSneaking()) {

							player.sendMessage(new TextComponentString("Replace Material: " + player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString()));
							player.getEntityData().setString("TSquareReplaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());
						} else {
							player.sendMessage(new TextComponentString("Place Material: " + player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString()));
							player.getEntityData().setString("TSquarePlaceMaterial", player.getEntityWorld().getBlockState(targetBlock).getBlock().getRegistryName().toString());
						}
					}
				} else {
					player.sendMessage(new TextComponentString("Error: Target block is on global blacklist"));
				}

			}
		}
	}
}
