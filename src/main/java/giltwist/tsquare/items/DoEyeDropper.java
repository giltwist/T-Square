package giltwist.tsquare.items;

import giltwist.tsquare.FindLookedBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class DoEyeDropper {

	public static void activate(EntityPlayer player, boolean fullBlockState) {

		if (!player.isSwingInProgress) {

			String itemUnlocal;
			BlockPos targetBlock = FindLookedBlock.getBlockPos(player);

			if (targetBlock == null) {
				player.addChatMessage(new TextComponentString("No block found within 200m"));
			} else {
				if (player.getHeldItemOffhand() == null) {
					itemUnlocal = "EmptyHand";
				} else {
					itemUnlocal = player.getHeldItemOffhand().getUnlocalizedName();
				}

				switch (itemUnlocal) {

				case "item.bucketWater":
					if (player.isSneaking()) {
						player.addChatMessage(new TextComponentString("Replace Material: Water"));
						player.getEntityData().setString("TSquareReplaceMaterial", "minecraft:water");						
					} else {
						player.addChatMessage(new TextComponentString("Place Material: Water"));
						player.getEntityData().setString("TSquarePlaceMaterial", "minecraft:water");
					}
					break;
				case "item.bucketLava":
					if (player.isSneaking()) {
						player.addChatMessage(new TextComponentString("Replace Material: Lava"));
						player.getEntityData().setString("TSquareReplaceMaterial", "minecraft:lava");						
					} else {
						player.addChatMessage(new TextComponentString("Place Material: Lava"));
						player.getEntityData().setString("TSquarePlaceMaterial", "minecraft:lava");
					}
					break;
				default:
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
					break;

				}
			}
		}
	}
}
