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
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {
				

				if (!TSquare.BLOCKBLACKLIST.contains(player.worldObj.getBlockState(targetBlock).getBlock())) {
					if (fullBlockState) {
						if (player.isSneaking()) {

							player.addChatMessage(new TextComponentString("Replace Blockstate: " + player.worldObj.getBlockState(targetBlock).toString()));
							player.getEntityData().setInteger("TSquareReplaceState", player.worldObj.getBlockState(targetBlock).getBlock().getMetaFromState(player.worldObj.getBlockState(targetBlock)));
							player.getEntityData().setString("TSquareReplaceMaterial", player.worldObj.getBlockState(targetBlock).getBlock().getRegistryName().toString());

						} else {
							player.addChatMessage(new TextComponentString("Place Blockstate: " + player.worldObj.getBlockState(targetBlock).toString()));
							player.getEntityData().setInteger("TSquarePlaceState", player.worldObj.getBlockState(targetBlock).getBlock().getMetaFromState(player.worldObj.getBlockState(targetBlock)));
							player.getEntityData().setString("TSquarePlaceMaterial", player.worldObj.getBlockState(targetBlock).getBlock().getRegistryName().toString());

						}

					} else {
						if (player.isSneaking()) {

							player.addChatMessage(new TextComponentString("Replace Material: " + player.worldObj.getBlockState(targetBlock).getBlock().getRegistryName().toString()));
							player.getEntityData().setString("TSquareReplaceMaterial", player.worldObj.getBlockState(targetBlock).getBlock().getRegistryName().toString());
						} else {
							player.addChatMessage(new TextComponentString("Place Material: " + player.worldObj.getBlockState(targetBlock).getBlock().getRegistryName().toString()));
							player.getEntityData().setString("TSquarePlaceMaterial", player.worldObj.getBlockState(targetBlock).getBlock().getRegistryName().toString());
						}
					}
				} else {
					player.addChatMessage(new TextComponentString("Error: Target block is on global blacklist"));
				}

			}
		}
	}
}
